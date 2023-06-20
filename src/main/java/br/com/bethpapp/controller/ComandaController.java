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

import br.com.bethpapp.controller.documentacao.ComandaOpenApi;
import br.com.bethpapp.coversor.ComandaConverter;
import br.com.bethpapp.dominio.dao.DaoComanda;
import br.com.bethpapp.modelo.dto.ComandaDTO;

@RestController
@RequestMapping("/comandas")
public class ComandaController  implements ComandaOpenApi {
	@Autowired
	private DaoComanda daoComanda;
	@Autowired
	private ComandaConverter comandaconverter;
	
	public  ResponseEntity< List<ComandaDTO> >listarTudo(){
		return ResponseEntity.status(HttpStatus.OK).body(comandaconverter.toCollectionDto( daoComanda.findAll()));
	}
	@GetMapping
	@Override
	public ResponseEntity<Page<ComandaDTO>> listar(
			@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer size, Pageable page) {
		
		return ResponseEntity.status(HttpStatus.OK).body(comandaconverter.topage(daoComanda.buscar(paramentro, page)));
	}

}
