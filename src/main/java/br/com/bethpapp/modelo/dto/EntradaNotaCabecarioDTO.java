package br.com.bethpapp.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bethpapp.dominio.entidade.TransporteNotafiscal;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EntradaNotaCabecarioDTO {
	private Long id;
	@Column(length = 25)
	private String numerodanota;

	private String naturezaopercao;

	private String modelo;

	private String serie;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_entrada;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_emissao_nota;

	private BigDecimal totalInf;
	private Integer qtevolume;
	 private TransporteNotafiscal transporteNotafiscal;
	private BigDecimal pesoBruto;
	private FornecedorDTO fornecedor;

	private List<ItemEntradaNotaDTO> items_entrada = new ArrayList<>();
	@Column(length = 255)
	private String arquivo_nota;
	

}
