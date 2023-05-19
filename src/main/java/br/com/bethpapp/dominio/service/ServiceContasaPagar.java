package br.com.bethpapp.dominio.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bethpapp.dominio.dao.DaoContasApagar;
import br.com.bethpapp.dominio.dao.DaoFormaDePagamento;
import br.com.bethpapp.dominio.entidade.ContasPagar;
import br.com.bethpapp.dominio.entidade.ContasPagarDetalhe;
import br.com.bethpapp.dominio.entidade.FormadePagmamento;
import br.com.bethpapp.dominio.entidade.Fornecedor;
import br.com.bethpapp.dominio.enumerado.StatusPagamento;
import jakarta.transaction.Transactional;


@Service
public class ServiceContasaPagar implements ServiceModel<ContasPagar> {
	@Autowired
    private  DaoContasApagar daoContasApagar;
	@Autowired
	private DaoFormaDePagamento daoFormaDePagamento;
	
	@Transactional
	public ContasPagar addconta(Integer qtepacerla,Long titulo,  Fornecedor fornecedor, BigDecimal valortotalconta, Long idForma, LocalDate  dataEmisao) {
		ContasPagar contaContasaPagar = new ContasPagar();
		contaContasaPagar.setFornecedor(fornecedor);
		   contaContasaPagar.setDatalancamento(dataEmisao);
		     Integer numeroparcela=0;
		     BigDecimal valoparcela = valortotalconta.divide(new BigDecimal(qtepacerla).setScale(3, RoundingMode.HALF_UP));
		   
		FormadePagmamento formadePagmamento = daoFormaDePagamento.findById(idForma).get();
	     for (int i=0; i< qtepacerla;i++) {
	    	 ContasPagarDetalhe contasaPagarDetalhe = new ContasPagarDetalhe();
	    	 numeroparcela=i+1;
	    	 contasaPagarDetalhe.setContasaPagar(contaContasaPagar);
	         contasaPagarDetalhe.setNumparcela(numeroparcela);	
	         contasaPagarDetalhe.setFormadePagamento(formadePagmamento);
	         contasaPagarDetalhe.setStatusPagmaento(StatusPagamento.PENDENTE);
	         contasaPagarDetalhe.setValoparcela(valoparcela);
	         contasaPagarDetalhe.setValorapagar(valoparcela);
	         contasaPagarDetalhe.setValoprago(new BigDecimal(0));
	         contasaPagarDetalhe.setNumtitulo(titulo);
	         if (formadePagmamento.getId()==1l) {
	          	 contasaPagarDetalhe.setDataVencimento(contaContasaPagar.getDatalancamento().plusDays((numeroparcela* 0)));
	         }else {
	        	 contasaPagarDetalhe.setDataVencimento(contaContasaPagar.getDatalancamento().plusDays((numeroparcela* 30))); 
	         }
	         
	         contaContasaPagar.getContasaPagarDetalhes().add(contasaPagarDetalhe);
		}
	     
	     return salvar(contaContasaPagar);
	}


	@Override
	public Page<ContasPagar> buscar(String nome, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ContasPagar buccarporid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional

	@Override
	public ContasPagar salvar(ContasPagar objeto) {
	
		return daoContasApagar.save(objeto);
	}


	



}
