package br.com.bethpapp.coversor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.modelo.dto.EntradaNotaCabecarioDTO;

@Component
public class EntradaNotaFiscalConverter {
	@Autowired
	private ModelMapper modelMapper;

	public EntradaNotaCabecarioDTO toDto(EntradaNotaCabecario objeto) {

		return modelMapper.map(objeto, EntradaNotaCabecarioDTO.class);
	}

}
