package br.com.bethpapp.modelo.input;

import java.math.BigDecimal;
import java.util.List;

import br.com.bethpapp.dominio.entidade.Atributo;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class ProdutoInput{

	
	
	private Long id;
	@Setter(value = AccessLevel.NONE)
	@NotEmpty
	@Column(length = 120)
	private String nomeproduto;
	@Setter(value = AccessLevel.NONE)
	@Column(length = 60)
	private String marca;
	@Column(length = 3)
	private String unidade;
	@Column(length = 255)
	private String imagemPrincipal;
	@Column(length = 60)
	private String sku;

	@Column(length = 13)
	private String codigoEan13;
	@Column(length = 255)
	private String caracteristica;
	@Column(length = 13)
	private String codigofabricante;
	
	
	private BigDecimal precovenda;

	private BigDecimal precocusto;
	
	private BigDecimal customedio;
	private SubCategoriaInput subcategoria;
	private EstoqueInput estoque;

	private List<Atributo> atributos;

}
