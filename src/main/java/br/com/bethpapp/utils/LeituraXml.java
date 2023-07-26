package br.com.bethpapp.utils;

import java.time.LocalDate;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.dominio.entidade.Fornecedor;

public class LeituraXml {
	
	public EntradaNotaCabecario lerxml (String xml, NodeList notaentrada) {
		var entrada = new EntradaNotaCabecario();
		for (int i = 0; i < notaentrada.getLength(); i++) {
			Element dadoinit = (Element) notaentrada.item(i);
			entrada.setData_emissao_nota(LocalDate
					.parse(dadoinit.getElementsByTagName("dhEmi").item(i).getTextContent().substring(0, 10)));
			entrada.setNumerodanota(dadoinit.getElementsByTagName("nNF").item(i).getTextContent());

		}
	
		entrada.setData_entrada(LocalDate.now());
		entrada.setArquivo_nota(xml);
		return entrada;
	}
	public Fornecedor  fonecedorxml() {
		var fornecedor = new Fornecedor();
		return fornecedor;
		
	}
   
}
