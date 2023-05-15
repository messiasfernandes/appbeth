package br.com.bethpapp.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bethpapp.controller.documentacao.EntradaNotafiscalOpenApi;
import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.dominio.service.ServiceImportaNotafiscal;
@RequestMapping("/importarnotasfiscais")
@RestController
public class EntradaNotaFiscalController implements EntradaNotafiscalOpenApi {
	
	@Autowired
	private ServiceImportaNotafiscal importar_notasevice;
	@PostMapping
	public ResponseEntity<EntradaNotaCabecario> importanota(@RequestParam(value = "xml", required = true) 
	String xml,@RequestParam(value = "margem", required = true) BigDecimal margen) {

		return ResponseEntity.status(HttpStatus.CREATED).body(importar_notasevice.imporxml(xml,margen));
	}

}
