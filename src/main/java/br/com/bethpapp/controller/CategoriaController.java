package br.com.bethpapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.coversor.CategoriaConverter;
import br.com.bethpapp.dominio.dao.DaoCategoria;
import br.com.bethpapp.dominio.entidade.Categoria;
import br.com.bethpapp.modelo.dto.CategoriaDTO;
import br.com.bethpapp.modelo.input.CategoriaInput;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RequestMapping("/categorias")
@RestController
public class CategoriaController {
	@Autowired
	private DaoCategoria daoCategoria;
	@Autowired
	private CategoriaConverter categoriaConverter;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(categoriaConverter.toCollectionDto(daoCategoria.findAll()));
	}

	@PostMapping

	public ResponseEntity<CategoriaDTO> adicionar(@Valid @RequestBody CategoriaInput categoria,
			HttpServletResponse response) {

		Categoria categoriasalva = daoCategoria.save(categoriaConverter.toEntity(categoria));
		/// publisher.publishEvent(new RecursoCriadoEvent(this, response,
		/// categoriasalva.getIdcategoria()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaConverter.toDto(categoriasalva));

	}
}
