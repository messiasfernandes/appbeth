package br.com.bethpapp.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.dominio.entidade.ItemEntradaNota;
import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.dominio.service.exeption.NegocioException;

public class LeituraXml {

	public EntradaNotaCabecario lerxml(String xml, NodeList notaentrada) {
		var entrada = new EntradaNotaCabecario();
		for (int i = 0; i < notaentrada.getLength(); i++) {
			Element dadoinit = (Element) notaentrada.item(i);
			entrada.setData_emissao_nota(
					LocalDate.parse(dadoinit.getElementsByTagName("dhEmi").item(i).getTextContent().substring(0, 10)));
			entrada.setNumerodanota(dadoinit.getElementsByTagName("nNF").item(i).getTextContent());

		}

		entrada.setData_entrada(LocalDate.now());
		entrada.setArquivo_nota(xml);
		return entrada;
	}

	public List<ItemEntradaNota> adicionarProduto(NodeList nodprouto, BigDecimal margem) {
		//var fator = new BigDecimal(1);
	//	var marge2 = margem.divide(new BigDecimal(100));
		margem =margem.divide(new BigDecimal(100));
	//	margem = marge2;
		margem = margem.add(BigDecimal.ONE);

		System.out.println( "margem"+margem);

		List<ItemEntradaNota> entradas = new ArrayList<>();
		try {
			for (int k = 0; k < nodprouto.getLength(); k++) {

				Element produtos = (Element) nodprouto.item(k);

				NodeList produtolista = produtos.getElementsByTagName("prod");

				for (int j = 0; j < produtolista.getLength(); j++) {
					Element produto = (Element) produtolista.item(j);
					var intemProduto = new ItemEntradaNota();
					var p = new Produto();

					p.setCodigoEan13(produto.getElementsByTagName("cEAN").item(j).getTextContent());

					p.setCodigofabricante(produto.getElementsByTagName("cProd").item(j).getTextContent());
					p.setCodigoEan13(produto.getElementsByTagName("cEAN").item(j).getTextContent());
					p.setNomeproduto(produto.getElementsByTagName("xProd").item(j).getTextContent());
					BigDecimal qte = new BigDecimal((produto.getElementsByTagName("qTrib").item(j).getTextContent()));
					intemProduto.setQtde(Integer.valueOf(qte.intValueExact()));

					var precocusto = (new BigDecimal(produto.getElementsByTagName("vUnTrib").item(j).getTextContent()));
				
					produto.getElementsByTagName("qTrib").item(j).getTextContent();
					p.setUnidade(produto.getElementsByTagName("uCom").item(j).getTextContent());
					p.setPrecocusto(precocusto);
					p.setCustomedio(precocusto);
					p.setPrecovenda(margem.multiply(p.getPrecocusto()));
					System.out.println(p.getPrecovenda());
					p.setAtivo(true);
					intemProduto.setSubtotal(new BigDecimal(intemProduto.getQtde()).multiply(precocusto));
			

					intemProduto.setProduto(p);
		
					entradas.add(intemProduto);

				}

			}
		} catch (Exception e) {
			throw new NegocioException("Erro adicionar produto");
		}
      return entradas;
	}

}
