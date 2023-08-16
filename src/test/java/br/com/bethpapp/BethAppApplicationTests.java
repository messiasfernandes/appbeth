package br.com.bethpapp;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.bethpapp.dominio.entidade.EstoqueMovimento;
import br.com.bethpapp.dominio.enumerado.PagamentoStatus;

@SpringBootTest
class BethAppApplicationTests {

	void contextLoads() {
		Integer n = 5;
		BigDecimal n1 = new BigDecimal(10.0);
		Integer n2 = 0;
		n2 = n1.subtract(new BigDecimal(n)).intValueExact();
		System.out.println(n2);

		EstoqueMovimento estoqueMovimento = new EstoqueMovimento();
		estoqueMovimento.setDatamovimento(LocalDateTime.now());
		System.out.println(estoqueMovimento.getDatamovimento());
	}

	
	void EmprestimoCalculator() {
		BigDecimal valor= new BigDecimal(5000).setScale(4, RoundingMode.HALF_EVEN);
		Integer qtdeparcela=24;
		BigDecimal taxajuro= new BigDecimal("3.79").setScale(4, RoundingMode.HALF_EVEN);
	var parcela=	ValordaParcela(valor, qtdeparcela, taxajuro);
var	total= parcela.setScale(2, RoundingMode.HALF_EVEN);
var valparcela = total.divide(new BigDecimal(qtdeparcela).setScale(3),MathContext.DECIMAL128);
var valototal= valparcela.multiply(new BigDecimal(qtdeparcela));
var totalprova= valototal.setScale(2, RoundingMode.HALF_EVEN);
System.out.println("valor da Total = " + total);
  System.out.println("valor da parcela = " + valparcela);
  System.out.println("valor da Prova = " + totalprova);
  
	}

	public BigDecimal ValordaParcela(BigDecimal valoEmprestimo,Integer periodo, BigDecimal taxaJuros) {
		BigDecimal valorparcela= BigDecimal.ZERO;
		var montante = taxaJuros.divide(new BigDecimal(100));
		montante=montante.add(BigDecimal.ONE);
		montante= montante.pow(periodo);
		montante = montante.multiply(valoEmprestimo);
		//montante= montante.setScale(2, RoundingMode.HALF_EVEN);
		return montante;
	}
	//@Test
 void	calcularVAlor(){
	 BigDecimal valor= new BigDecimal(5000).setScale(4, RoundingMode.HALF_EVEN);
		BigDecimal qtdeparcela= new BigDecimal(24).setScale(4, RoundingMode.HALF_EVEN);
		BigDecimal taxajuro= new BigDecimal("3.79").setScale(4, RoundingMode.HALF_EVEN);
		
		var montante = taxajuro.divide(new BigDecimal(100), RoundingMode.HALF_EVEN);
		montante=montante.add(BigDecimal.ONE);
		montante= montante.pow(qtdeparcela.intValue());
		montante = montante.multiply(valor);
		montante= montante.setScale(2, RoundingMode.HALF_EVEN);
		//Integer qtde=qtdeparcela.intValue();
	//	var valorParcela= montante.divide(new BigDecimal(	qtde));
		
		System.out.println("valor total"+ montante);
	//	System.out.println("valor parecela"+ valorParcela);
	}
 @Test
  void CalcularprecovendaJuros() {
	  var precovenda = new BigDecimal("12221.89");
	  var taxaJurtos= new BigDecimal("3.7");
	  var qtdeparcela= 10;
	 var  dias=1350;
	 var prazoMedio =new BigDecimal(dias).divide(new BigDecimal(qtdeparcela));
	 var n = prazoMedio.divide(new BigDecimal(30));
	 System.out.println("Prazo medio"+ prazoMedio);
	 System.out.println("Prazo medio"+ n);
  }
 
}  
