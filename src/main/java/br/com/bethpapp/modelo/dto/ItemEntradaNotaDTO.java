package br.com.bethpapp.modelo.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ItemEntradaNotaDTO {
	private Integer qtde;

	private BigDecimal subtotal;
	
	private ProdutoDTO produto;
	
	
}