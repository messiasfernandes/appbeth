package br.com.bethpapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.coversor.ComandaConverter;
import br.com.bethpapp.dominio.dao.DaoComanda;
import br.com.bethpapp.modelo.dto.ComandaDTO;

@RestController
@RequestMapping("/comandas")
public class ComandaController {
	@Autowired
	private DaoComanda daoComanda;
	@Autowired
	private ComandaConverter comandaconverter;
	@GetMapping
	public  ResponseEntity< List<ComandaDTO> >listarTudo(){
		return ResponseEntity.status(HttpStatus.OK).body(comandaconverter.toCollectionDto( daoComanda.findAll()));
	}

}
