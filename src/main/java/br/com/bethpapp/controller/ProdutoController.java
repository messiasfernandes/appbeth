package br.com.bethpapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.coversor.ProdutoConverter;
import br.com.bethpapp.dominio.dao.DaoProduto;
import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.dominio.service.ServiceProduto;
import br.com.bethpapp.modelo.dto.ProdutoDTO;


@RequestMapping("/produtos")
@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoConverter produtoConverter;
	@Autowired
	private DaoProduto daoProduto;
	@Autowired
	private ServiceProduto serviceProduto;
	//@GetMapping
	public ResponseEntity<List<Produto>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body((daoProduto.findAll()));
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> buscar(
			@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer size, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(produtoConverter.topage(serviceProduto.buscar(paramentro, page)));
	}

}
