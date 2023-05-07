package br.com.bethpapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.coversor.SubCategoriaConverter;
import br.com.bethpapp.dominio.dao.DaoSubcategoria;
import br.com.bethpapp.modelo.dto.SubCategoriaDTO;

@RequestMapping("/subcategorias")
@RestController
public class SubCategoriaController {

	@Autowired
	private DaoSubcategoria daoSubcategoria;
	@Autowired
	private SubCategoriaConverter subCategoriaConverter;

	@GetMapping
	public ResponseEntity<List<SubCategoriaDTO>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(subCategoriaConverter.toCollectionDto( daoSubcategoria.findAll()));

	}
}
