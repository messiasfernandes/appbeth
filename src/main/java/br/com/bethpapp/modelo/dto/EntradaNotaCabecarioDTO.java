package br.com.bethpapp.modelo.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EntradaNotaCabecarioDTO {
	private Long id;
	@Column(length = 25)
	private String numerodanota;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_entrada;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_emissao_nota;

	private FornecedorDTO fornecedor;


	private List<ItemEntradaNotaDTO> items_entrada = new ArrayList<>();
	@Column(length = 255)
	private String arquivo_nota;
}
