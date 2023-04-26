package br.com.bethpapp.coversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.modelo.dto.ProdutoDTo;
import br.com.bethpapp.modelo.input.ProdutoInput;

;

@Component
public class ProdutoConverter {

	@Autowired
	private ModelMapper modelMapper;

	public ProdutoDTo toDto(Produto objeto) {

		return modelMapper.map(objeto, ProdutoDTo.class);
	}

	public Produto toEntity(ProdutoInput objeto) {

		return modelMapper.map(objeto, Produto.class);
	}

	public Page<ProdutoDTo> topage(Page<Produto> objetos) {

		return objetos.map(obj -> toDto(obj));
	}

	public List<ProdutoDTo> toCollectionDto(List<Produto> categorias) {
		return categorias.stream().map(this::toDto).collect(Collectors.toList());
	}

}
