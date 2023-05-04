package br.com.bethpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.controller.documentacao.ProdutoOpenApi;
import br.com.bethpapp.coversor.ProdutoConverter;
import br.com.bethpapp.dominio.service.ServiceProduto;
import br.com.bethpapp.modelo.dto.ProdutoDTO;
import br.com.bethpapp.modelo.input.ProdutoInput;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RequestMapping("/produtos")
@RestController
public class ProdutoController implements ProdutoOpenApi {

	@Autowired
	private ProdutoConverter produtoConverter;

	@Autowired
	private ServiceProduto serviceProduto;

	@GetMapping
	@Override
	public ResponseEntity<Page<ProdutoDTO>> listar(
			@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer size, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(produtoConverter.topage(serviceProduto.buscar(paramentro, page)));
	}

	@Override
	public ResponseEntity<Void> remover(Long iproduto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ProdutoDTO> buscar(Long idproduto) {
		// TODO Auto-generated method stub
		return null;
	}

	@PutMapping("/{id}")
	@Override
	public ResponseEntity<ProdutoDTO> Atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoInput produto,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping
	@Override
	public ResponseEntity<ProdutoDTO> criar(@Valid @RequestBody ProdutoInput produto, HttpServletResponse response) {
		System.out.println(produto.getNomeproduto());
		var produtosalvo = serviceProduto.salvar(produtoConverter.toEntity(produto));
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoConverter.toDto(produtosalvo));
	}

}
