package br.com.bethpapp.dominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.bethpapp.dominio.dao.DaoCidade;
import br.com.bethpapp.dominio.dao.DaoForncedor;
import br.com.bethpapp.dominio.entidade.Cidade;
import br.com.bethpapp.dominio.entidade.Endereco;
import br.com.bethpapp.dominio.entidade.Fornecedor;
import br.com.bethpapp.dominio.enumerado.TipoPessoa;

@Service
public class ServiceForncedorNotaFiscal {
	@Autowired
	private DaoForncedor daoForncedor;
	@Autowired
	private DaoCidade daoCidade;

	public Fornecedor salvarfornecedorNota(NodeList emitentes) {
		Fornecedor forncedor = new Fornecedor();
		
		var cidade = new Cidade();
		for (int i = 0; i < emitentes.getLength(); i++) {
			Element emitente = (Element) emitentes.item(i);
			forncedor.setCpfouCnpj(emitente.getElementsByTagName("CNPJ").item(i).getTextContent());
			forncedor.setNome(emitente.getElementsByTagName("xNome").item(i).getTextContent());
			/// String fone= emitente.getElementsByTagName("fone").toString();
			if (!emitente.getElementsByTagName("fone").toString().isEmpty()) {
				forncedor.setTelefone(emitente.getElementsByTagName("fone").item(i).getTextContent());
			}

			forncedor.setLogradouro(emitente.getElementsByTagName("xLgr").item(i).getTextContent() + ","
					+ emitente.getElementsByTagName("nro").item(i).getTextContent());
			cidade = daoCidade.findById(Long.parseLong(emitente.getElementsByTagName("cMun").item(i).getTextContent()))
					.get();
			System.out.println(cidade);
			forncedor.setBairro(emitente.getElementsByTagName("xBairro").item(i).getTextContent());
			forncedor.setCep(emitente.getElementsByTagName("CEP").item(i).getTextContent());
			forncedor.setRg_Inscricao(emitente.getElementsByTagName("IE").item(i).getTextContent());
			forncedor.setCidade(cidade);
		///	forncedor.setEndereco(endreco);

		}

		if (forncedor.getCpfouCnpj().length() == 14) {
			forncedor.setTipessoa(TipoPessoa.JURÍDICA);
		}
		System.out.println(forncedor.getCpfouCnpj());
		var cont = daoForncedor.isCppouCnpjExit(forncedor.getCpfouCnpj());
		if (cont > 0l) {
			Fornecedor fonecedorexistente = daoForncedor.findCpfouCnpj(forncedor.getCpfouCnpj());
			if (fonecedorexistente != null) {
				return fonecedorexistente;
			}

		}
		return daoForncedor.save(forncedor);
	}

	public Fornecedor buscar(String pCpfouCnpj) {
		Fornecedor fonecedorexistente = daoForncedor.findCpfouCnpj(pCpfouCnpj);

		return fonecedorexistente;
	}

}
