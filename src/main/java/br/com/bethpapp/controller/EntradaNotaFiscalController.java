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
import br.com.bethpapp.coversor.EntradaNotaFiscalConverter;
import br.com.bethpapp.dominio.service.ServiceImportaNotafiscal;
import br.com.bethpapp.modelo.dto.EntradaNotaCabecarioDTO;

@RequestMapping("/importarnotasfiscais")
@RestController
public class EntradaNotaFiscalController implements EntradaNotafiscalOpenApi {
	
	@Autowired
	private ServiceImportaNotafiscal importar_notasevice;
	@Autowired
	private EntradaNotaFiscalConverter entradanfConverter;
	@PostMapping
	public ResponseEntity<EntradaNotaCabecarioDTO> importanota(@RequestParam(value = "xml", required = true) String xml,
			@RequestParam(value = "margem", required = true) BigDecimal margen, @RequestParam(value = "idforma", required =  true) Long idforma,
			@RequestParam(value = "qtdeparecla", required =  true) Integer qtdeparecla) {

		return ResponseEntity.status(HttpStatus.CREATED).body(entradanfConverter.toDto(  importar_notasevice.imporxml(xml,margen,idforma,qtdeparecla)));
	}


}
