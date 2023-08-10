package br.com.bethpapp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.bethpapp.dominio.entidade.ContasPagar;
import br.com.bethpapp.dominio.entidade.ContasPagarDetalhe;
import br.com.bethpapp.dominio.entidade.EstoqueMovimento;
import br.com.bethpapp.dominio.enumerado.StatusPagamento;

@SpringBootTest
class BethAppApplicationTests {

	
	void contextLoads() {
		Integer n=5;
		BigDecimal n1= new BigDecimal(10.0);
		Integer n2=0;
		n2= n1.subtract(new BigDecimal(n)).intValueExact();
	   System.out.println(	n2);
	   
	  EstoqueMovimento estoqueMovimento = new EstoqueMovimento();
	  estoqueMovimento.setDatamovimento(LocalDateTime.now());
	  System.out.println(estoqueMovimento.getDatamovimento());
	}
	@Test
  void enustring(){
	  ContasPagarDetalhe contasaPagarDetalhe = new ContasPagarDetalhe();
	contasaPagarDetalhe.setStatusPagmaento(StatusPagamento.valueOf("Pendente"));
	System.out.println(contasaPagarDetalhe.getStatusPagmaento());
  }
}
