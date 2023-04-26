package br.com.bethpapp.modelo.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.bethpapp.dominio.entidade.Atributo;
import br.com.bethpapp.dominio.entidade.Estoque;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ProdutoDTo {


	private SubCategoriaDTO subcategoria;



	private String nomeproduto;

	private String marca;

	private String unidade;

	private String imagemPrincipal;

	private String sku;


	private String codigoEan13;

	private String caracteristica;
	
	private String codigofabricante;

	private BigDecimal precovenda;

	private BigDecimal precocusto;

	private BigDecimal customedio;
	 private Estoque estoque;

	private List<Atributo> atributos;

}
