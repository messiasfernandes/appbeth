package br.com.bethpapp;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BethAppApplicationTests {

	@Test
	void contextLoads() {
		Integer n=5;
		BigDecimal n1= new BigDecimal(10.0);
		Integer n2=0;
		n2= n1.subtract(new BigDecimal(n)).intValueExact();
	   System.out.println(	n2);
	}
	

}
