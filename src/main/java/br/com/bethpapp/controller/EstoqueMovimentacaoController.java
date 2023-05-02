package br.com.bethpapp.controller;

import java.time.LocalDate;
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

import br.com.bethpapp.coversor.EstoqueMovimemtoConvereter;
import br.com.bethpapp.dominio.dao.DaoMovementacaoEstoque;
import br.com.bethpapp.modelo.dto.EstoqueProdutoDTO;


@RequestMapping("/movimentacoesestoque")
@RestController
public class EstoqueMovimentacaoController {
	@Autowired
	private DaoMovementacaoEstoque daoMovementacaoEstoque;
	@Autowired
	private EstoqueMovimemtoConvereter estoqueMovimemtoConvereter;
//	@GetMapping
	public ResponseEntity<List<EstoqueProdutoDTO>> listra() {
		return ResponseEntity.status(HttpStatus.OK).body(estoqueMovimemtoConvereter.toCollectionDto(   daoMovementacaoEstoque.findAll()));
	}
	
	
	@GetMapping
	public ResponseEntity<Page<EstoqueProdutoDTO>> buscar(
			@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value="dataincio", required=false)LocalDate dataincio, @RequestParam(value="datafim",required = false)  LocalDate datafim,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer size, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(estoqueMovimemtoConvereter.topage(daoMovementacaoEstoque.buscar(paramentro, dataincio, datafim, page)));
	}
	
}

