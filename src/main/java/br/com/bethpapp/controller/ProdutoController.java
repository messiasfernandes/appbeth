package br.com.bethpapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.coversor.ProdutoConverter;
import br.com.bethpapp.dominio.dao.DaoProduto;
import br.com.bethpapp.modelo.dto.ProdutoDTo;


@RequestMapping("/produtos")
@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoConverter produtoConverter;
	@Autowired
	private DaoProduto daoProduto;
	
	@GetMapping
	public ResponseEntity<List<ProdutoDTo>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(produtoConverter.toCollectionDto(daoProduto.findAll()));
	}
	
	

}
