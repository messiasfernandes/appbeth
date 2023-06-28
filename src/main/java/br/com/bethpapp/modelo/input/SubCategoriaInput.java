package br.com.bethpapp.modelo.input;

import lombok.Data;

@Data
public class SubCategoriaInput {
	private Long id;
	private String nomeSubCategoria;
	private CategoriaInput categoria;
}
