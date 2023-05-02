package br.com.bethpapp.modelo.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bethpapp.dominio.enumerado.TipoMovimentacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class EstoqueProdutoDTO {
	//@Column(length = 15, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;

	@DateTimeFormat(pattern = " dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datamovimento;
	private Integer qtde;
	private Integer saldoanterior;
	private ProdutoLigtDTO produto;

}
