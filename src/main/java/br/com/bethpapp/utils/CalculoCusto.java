package br.com.bethpapp.utils;

import java.math.BigDecimal;

import org.w3c.dom.Element;

public class CalculoCusto {

	
	 public BigDecimal caluculo (Element element,BigDecimal totalnota, Integer qtde) {
		 BigDecimal rateiofrete = BigDecimal.ZERO;
		 BigDecimal rateioOutrasdespeza = BigDecimal.ZERO;
		 BigDecimal rateioDesconto = BigDecimal.ZERO;
		 return null;
	 }
	 
	 private String obterTextoElementoOpcional(Element parentElement, String tagName) {
		    Element element = (Element) parentElement.getElementsByTagName(tagName).item(0);
		    return element != null ? element.getTextContent() : "";
		}
}
