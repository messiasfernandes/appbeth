package br.com.bethpapp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.bethpapp.dominio.entidade.EstoqueMovimento;

@SpringBootTest
class BethAppApplicationTests {

	@Test
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
	

}
