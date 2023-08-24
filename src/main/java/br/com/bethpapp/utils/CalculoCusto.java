package br.com.bethpapp.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CalculoCusto {

	public BigDecimal calcularRateioImpostos(NodeList total, Integer qtde, BigDecimal subtotal) {
		BigDecimal rateioImposto = BigDecimal.ZERO;
		BigDecimal rateioFrete = BigDecimal.ZERO;
		BigDecimal rateioSeguro = BigDecimal.ZERO;
		BigDecimal rateioDesconto = BigDecimal.ZERO;
		for (int i = 0; i < total.getLength(); i++) {
			Element element = (Element) total.item(i);
			BigDecimal totalProduto = obterValorElemento(element, "vProd");

			BigDecimal totalImposto = rateioImpostos(element);
			System.out.println(totalImposto +"totalimposot");
			if (!totalImposto.equals(BigDecimal.ZERO)) {
				   BigDecimal rateioImpostoProduto = totalImposto.divide(totalProduto, 4, RoundingMode.HALF_EVEN);
				     rateioImposto = rateioImpostoProduto.multiply(subtotal).divide(new BigDecimal(qtde), 4, RoundingMode.HALF_EVEN);
				    System.out.println(rateioImposto + "rateio");
//				rateioImposto = subtotal.divide(totalProduto).multiply(totalImposto).divide(new BigDecimal(qtde));
//				System.out.println(rateioImposto+"rateio");
			}
			BigDecimal frete = rateioFrete(element);
			if (!frete.equals(BigDecimal.ZERO)) {
				BigDecimal rateioFreteProduto = frete.divide(totalProduto, 4, RoundingMode.HALF_EVEN);
				 rateioFrete = rateioFreteProduto.multiply(subtotal).divide(new BigDecimal(qtde), 4, RoundingMode.HALF_EVEN);
				//rateioFrete = subtotal.divide(totalProduto).multiply(frete).divide(new BigDecimal(qtde));
			}
			BigDecimal seguro = rateioSeguro(element);
			if (!seguro.equals(BigDecimal.ZERO)) {
				BigDecimal rateioSeguroProduto = seguro.divide(totalProduto, 4, RoundingMode.HALF_EVEN);
				 rateioSeguro = rateioSeguroProduto.multiply(subtotal).divide(new BigDecimal(qtde), 4, RoundingMode.HALF_EVEN);
			//	rateioSeguro = subtotal.divide(totalProduto).multiply(seguro).divide(new BigDecimal(qtde));
			}
			BigDecimal desconto = rateioDesconto(element);
			if (!desconto.equals(BigDecimal.ZERO)) {
				BigDecimal rateioDescointoProduto = desconto.divide(totalProduto, 4, RoundingMode.HALF_EVEN);
				rateioDesconto = rateioDescointoProduto.divide(totalProduto).multiply(desconto).divide(new BigDecimal(qtde));
			}
		}
		return rateioImposto.add(rateioSeguro).add(rateioFrete).subtract(rateioDesconto);
	}

	private BigDecimal obterValorElemento(Element parentElement, String tagName) {
		Element element = (Element) parentElement.getElementsByTagName(tagName).item(0);
		return element != null ? new BigDecimal(element.getTextContent()) : BigDecimal.ZERO;
	}

	private BigDecimal rateioImpostos(Element imposto) {

		BigDecimal vICMSDeson = obterValorElemento(imposto, "vICMSDeson");
		
				//new BigDecimal(imposto.getElementsByTagName("vICMSDeson").item(0).getTextContent());
		BigDecimal vII = obterValorElemento(imposto, "vII");
			//	new BigDecimal(imposto.getElementsByTagName("vII").item(0).getTextContent());
		BigDecimal vIPI =obterValorElemento(imposto, "vIPI");
				//new BigDecimal(imposto.getElementsByTagName("vIPI").item(0).getTextContent());

		return vIPI.add(vII).add(vICMSDeson.setScale(4, RoundingMode.HALF_EVEN));
	}

	private BigDecimal rateioFrete(Element imposto) {

		BigDecimal vFrete = new BigDecimal(imposto.getElementsByTagName("vFrete").item(0).getTextContent());

		return vFrete.setScale(4, RoundingMode.HALF_EVEN);

	}

	private BigDecimal rateioSeguro(Element imposto) {

		BigDecimal vSeg = new BigDecimal(imposto.getElementsByTagName("vSeg").item(0).getTextContent());

		return vSeg.setScale(4, RoundingMode.HALF_EVEN);

	}
	
	private BigDecimal rateioDesconto(Element imposto) {

		BigDecimal vDesc = new BigDecimal(imposto.getElementsByTagName("vDesc").item(0).getTextContent());

		return vDesc.setScale(4, RoundingMode.HALF_EVEN);

	}
}
