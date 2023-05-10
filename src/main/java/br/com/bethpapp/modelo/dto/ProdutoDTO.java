package br.com.bethpapp.modelo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data

public class ProdutoDTO {

	private SubCategoriaDTO subcategoria;

	private Long id;

	private String nomeproduto;

	private String marca;

	private String unidade;

	private String imagemPrincipal;

	private String sku;
	private Integer estoqueminimo;
    private String descricao;

	private Boolean ativo;
	private String codigoEan13;

	private String caracteristica;

	private String codigofabricante;

	private BigDecimal precovenda;

	private BigDecimal precocusto;

	private BigDecimal customedio;
	private EstoqueDTO estoque;

	private List<AtributoDTO> atributos = new ArrayList<>();
	private List<ComponeteDTO> componentes;
}
