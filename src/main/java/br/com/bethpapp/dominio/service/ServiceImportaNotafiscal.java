package br.com.bethpapp.dominio.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.bethpapp.dominio.dao.DaoEntradaNota;
import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.dominio.entidade.Fornecedor;
import br.com.bethpapp.dominio.entidade.ItemEntradaNota;
import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.dominio.service.exeption.NegocioException;
import br.com.bethpapp.utils.LeituraXml;
import jakarta.transaction.Transactional;

@Service
public class ServiceImportaNotafiscal {

	@Value("${storage.disco}")
	private String arquivo;
	@Value("${storage.xml}")
	private String local_arquivo_xml;
	@Autowired
	private ServiceProduto serviceProduto;
	@Autowired
	private DaoEntradaNota daoEntradaNota;
	@Autowired
	private ServiceEstoqueMovimento serviceEstoqueMovimento;
	@Autowired
	private ServiceForncedorNotaFiscal serviceForncedorNotaFiscal;
	@Autowired
	private ServiceContasaPagar serviceContasaPagar;
	private LeituraXml leituraXml =new LeituraXml();
	private LocalDate dataemisao;

	public EntradaNotaCabecario imporxml(String xml, BigDecimal pMargem, Long idforma, Integer qtdeparcelas) {
		System.out.println(arquivo + local_arquivo_xml + xml);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		var entrada = new EntradaNotaCabecario();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(arquivo + local_arquivo_xml + xml);

			var dados = doc.getElementsByTagName("ide");
			entrada= leituraXml.lerxml(xml, dados);
			dataemisao= entrada.getData_emissao_nota();
			Element raiz = doc.getDocumentElement();
			
			NodeList emitentes = raiz.getElementsByTagName("emit");
			entrada.setFornecedor(dadosFornecedor(emitentes));
			NodeList produtos = raiz.getElementsByTagName("det");
			entrada.setItems_entrada(leituraXml.adicionarProduto(produtos, pMargem));

		} catch (ParserConfigurationException e) {

			/// e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return salvar(entrada, pMargem, idforma, qtdeparcelas);
	}

	

	private Fornecedor dadosFornecedor(NodeList emitentes) {

		return serviceForncedorNotaFiscal.adiconafornecedorXml(emitentes);
	}

	@Transactional(rollbackOn = Exception.class)
	public EntradaNotaCabecario salvar(EntradaNotaCabecario entrada, BigDecimal margen, Long idforma,
			Integer qtdeparcelas) {
		entrada.getItems_entrada().forEach(e -> e.setEntradaNotafiscal(entrada));

		if ((daoEntradaNota.buscarnota(entrada.getFornecedor().getId(), entrada.getNumerodanota()) == true)) {

			throw new NegocioException("Nota  cadastrada já no banco de dados");
		} else {
			   Map<String, Produto> produtosExistentes = new HashMap<>();
		        
		        for (int i = 0; i < entrada.getItems_entrada().size(); i++) {
		            System.out.println(entrada.getItems_entrada().get(i).getProduto().getCodigofabricante());
		            String codigoFabricante = entrada.getItems_entrada().get(i).getProduto().getCodigofabricante();
		            long cont = serviceProduto.buscarCodFabricante(codigoFabricante);

		            if (cont > 0L) {
		                Produto produtoExistente = produtosExistentes.computeIfAbsent(codigoFabricante,
		                        key -> serviceProduto.buscarporCodFabricante(key));
		                entrada.getItems_entrada().get(i).setProduto(produtoExistente);
		            } else {
		                serviceProduto.salvar(entrada.getItems_entrada().get(i).getProduto());
		            }
		        }
		        
		        Long titulo = Long.parseLong(entrada.getNumerodanota());
		        BigDecimal totalnota = entrada.getItems_entrada().stream()
		                .map(ItemEntradaNota::getSubtotal)
		                .reduce(BigDecimal.ZERO, BigDecimal::add);
			serviceEstoqueMovimento.entradaEstoquue(entrada);
			serviceForncedorNotaFiscal.salvarfornecedorXml(entrada.getFornecedor());
		var contas=	serviceContasaPagar.addconta(qtdeparcelas, titulo, entrada.getFornecedor(), totalnota, idforma, dataemisao);
		serviceContasaPagar.salvar(contas);
		daoEntradaNota.save(entrada);
			return entrada;
		}

	}
}
