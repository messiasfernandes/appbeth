package br.com.bethpapp.dominio.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	public EntradaNotaCabecario imporxml(String xml, BigDecimal pMargem) {
		System.out.println(arquivo + local_arquivo_xml + xml);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		var entrada = new EntradaNotaCabecario();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(arquivo + local_arquivo_xml + xml);

			var dados = doc.getElementsByTagName("ide");
			for (int i = 0; i < dados.getLength(); i++) {
				Element dadoinit = (Element) dados.item(i);
				entrada.setData_emissao_nota(LocalDate
						.parse(dadoinit.getElementsByTagName("dhEmi").item(i).getTextContent().substring(0, 10)));
				entrada.setNumerodanota(dadoinit.getElementsByTagName("nNF").item(i).getTextContent());

			}
			entrada.setData_entrada(LocalDate.now());
			entrada.setArquivo_nota(xml);
			Element raiz = doc.getDocumentElement();
			System.out.println("Nota fiscal" + raiz.getNodeName());
			NodeList emitentes = raiz.getElementsByTagName("emit");
			entrada.setFornecedor(dadosFornecedor(emitentes));
			NodeList produtos = raiz.getElementsByTagName("det");
			entrada.setItems_entrada(adicionarProduto(produtos, pMargem));

		} catch (ParserConfigurationException e) {

			/// e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return salvar(entrada, pMargem);
	}

	private List<ItemEntradaNota> adicionarProduto(NodeList nodprouto, BigDecimal margem) {
		var fator = new BigDecimal(1);
		var marge2 = margem.divide(new BigDecimal(100));
		margem = marge2;
		margem = margem.add(fator);

		System.out.println(margem);

		List<ItemEntradaNota> entradas = new ArrayList<>();
		for (int k = 0; k < nodprouto.getLength(); k++) {

			Element produtos = (Element) nodprouto.item(k);

			NodeList produtolista = produtos.getElementsByTagName("prod");

			for (int j = 0; j < produtolista.getLength(); j++) {
				Element produto = (Element) produtolista.item(j);
				var intemProduto = new ItemEntradaNota();
				var p = new Produto();

				p.setCodigoEan13(produto.getElementsByTagName("cEAN").item(j).getTextContent());
//				p.setQtdeestoque(
//						new BigDecimal(produto.getElementsByTagName("qTrib").item(j).getTextContent().toString()));
				p.setCodigofabricante(produto.getElementsByTagName("cProd").item(j).getTextContent());
				p.setCodigoEan13(produto.getElementsByTagName("cEAN").item(j).getTextContent());
				p.setNomeproduto(produto.getElementsByTagName("xProd").item(j).getTextContent());
				BigDecimal qte = new BigDecimal((produto.getElementsByTagName("qTrib").item(j).getTextContent()));
				intemProduto.setQtde(Integer.valueOf(qte.intValueExact()));

				var precocusto = (new BigDecimal(produto.getElementsByTagName("vUnTrib").item(j).getTextContent()));
				// p.setPrecovenda(margem.multiply(p.getPrecocusto()));
				produto.getElementsByTagName("qTrib").item(j).getTextContent();
				p.setUnidade(produto.getElementsByTagName("uCom").item(j).getTextContent());
                p.setPrecocusto(precocusto);
                p.setCustomedio(precocusto);
                p.setPrecovenda(marge2.multiply(p.getPrecocusto()));
				intemProduto.setSubtotal(new BigDecimal(intemProduto.getQtde()).multiply(precocusto));
				/// p.setPrecovenda(margem.multiply(precocusto);

				intemProduto.setProduto(p);
				// var grade = new ProdutoGrade();
				// grade.setProduto(p);
				/// p.getProdutograde().add(grade);
				entradas.add(intemProduto);

			}

		}
		return entradas;

	}

	void entradaEstoquue(EntradaNotaCabecario pEntrada) {

		/// System.out.println(pEntrada.getItems_entrada().get(0).getProduto().getNomeproduto());
		serviceEstoqueMovimento.entradaEstoquue(pEntrada);

	}

	private Fornecedor dadosFornecedor(NodeList emitentes) {

		return serviceForncedorNotaFiscal.salvarfornecedorNota(emitentes);
	}

	@Transactional(rollbackOn = Exception.class)
	public EntradaNotaCabecario salvar(EntradaNotaCabecario entrada, BigDecimal margen) {
		entrada.getItems_entrada().forEach(e -> e.setEntradaNotafiscal(entrada));

		if ((daoEntradaNota.buscarnota(entrada.getFornecedor().getId(), entrada.getNumerodanota()) == true)) {

			throw new NegocioException("Nota  cadastrada já no banco de dados");
		} else {
			for (int i = 0; i < entrada.getItems_entrada().size(); i++) {
				System.out.println(entrada.getItems_entrada().get(i).getProduto().getCodigofabricante());
				var cont = serviceProduto
						.buscarCodFabricante(entrada.getItems_entrada().get(i).getProduto().getCodigofabricante());

				if (cont > 0l) {
					Produto produtoExistente = serviceProduto.buscarporCodFabricante(
							entrada.getItems_entrada().get(i).getProduto().getCodigofabricante());
					if (produtoExistente != null) {
						entrada.getItems_entrada().get(i).setProduto(produtoExistente);
						// produtos.save(produtoExistente);
					}

				} else {
					serviceProduto.salvar(entrada.getItems_entrada().get(i).getProduto());

				}
			}
			serviceEstoqueMovimento.entradaEstoquue(entrada);
			daoEntradaNota.save(entrada);
			return entrada;
		}

	}
}
