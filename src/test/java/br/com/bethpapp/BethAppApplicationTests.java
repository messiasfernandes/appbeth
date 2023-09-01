package br.com.bethpapp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.bethpapp.dominio.entidade.EstoqueMovimento;

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
		BigDecimal valor= new BigDecimal(1221.89).setScale(4, RoundingMode.HALF_EVEN);
		Integer qtdeparcela=10;
		BigDecimal taxajuro= new BigDecimal("1.65").setScale(4, RoundingMode.HALF_EVEN);
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
	//igDecimal valorparcela= BigDecimal.ZERO;
		var montante = taxaJuros.divide(new BigDecimal(100));
		montante=montante.add(BigDecimal.ONE);
		montante= montante.pow(periodo);
		montante = montante.multiply(valoEmprestimo);
		//montante= montante.setScale(2, RoundingMode.HALF_EVEN);
		return montante;
	}

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

  void CalcularprecovendaJuros() {
	  var precovenda = new BigDecimal("1221.89");
	  var taxaJurtos= new BigDecimal("3.7");
	  var qtdeparcela= 10;
	 var  dias=1350;
	 var prazoMedio =new BigDecimal(dias).divide(new BigDecimal(qtdeparcela));
	 var n = prazoMedio.divide(new BigDecimal(30));
///	 var custoFincaeiroMedio= new BigDecimal("");
	 taxaJurtos= taxaJurtos.divide(new BigDecimal(100));
	 taxaJurtos= taxaJurtos.add(BigDecimal.ONE);
     var	 custoFincaeiroMedio= precovenda.multiply(taxaJurtos).pow(qtdeparcela);
     custoFincaeiroMedio.divide( new BigDecimal(1).pow(qtdeparcela).subtract(new BigDecimal(1)));
	 System.out.println("Prazo juros"+ taxaJurtos);
	 
	 System.out.println("Prazo medio"+ n);
	 System.out.println("Custo Medio"+ custoFincaeiroMedio);
  }
 @Test
 void datahora() {
	  String dateString = "2019-03-01T15:00:00-03:00";

      DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
      OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, inputFormatter);

      LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();

      DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
      String formattedDateTime = localDateTime.format(outputFormatter);

      System.out.println("Original OffsetDateTime: " + offsetDateTime);
      System.out.println("Converted LocalDateTime: " + formattedDateTime);
  }
 
}  
