package br.com.bethpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.coversor.SubCategoriaConverter;
import br.com.bethpapp.dominio.dao.DaoSubcategoria;
import br.com.bethpapp.dominio.service.ServiceSubCategoria;
import br.com.bethpapp.modelo.dto.SubCategoriaDTO;
@CrossOrigin
@RequestMapping("/subcategorias")
@RestController
public class SubCategoriaController {


	@Autowired
	private ServiceSubCategoria subcategoriaService;
	@Autowired
	private SubCategoriaConverter subCategoriaConverter;

	@GetMapping
	public ResponseEntity<Page<SubCategoriaDTO>> listar(@RequestParam(value = "parametro", required = false, defaultValue = "") String parametro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sort", defaultValue = "nomeSubcategoria") String orderBy,
	@RequestParam(value = "direction", defaultValue = "DESC") String direction,org.springframework.data.domain.Pageable page) {

		return ResponseEntity.status(HttpStatus.OK).body(subCategoriaConverter.topage( subcategoriaService.buscar(parametro, page)));

	}
}
