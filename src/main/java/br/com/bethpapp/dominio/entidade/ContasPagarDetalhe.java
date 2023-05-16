package br.com.bethpapp.dominio.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bethpapp.dominio.enumerado.StatusPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
public class ContasPagarDetalhe extends GeradorId implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@NotNull
	private Long numtitulo;
	@NotNull
	private Integer numparcela;
	@Column(columnDefinition = "DECIMAL(9,3) DEFAULT 0.000")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valoparcela;
	@Column(columnDefinition = "DECIMAL(9,3) DEFAULT 0.000")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valoprago;
	@Column(columnDefinition = "DECIMAL(9,3) DEFAULT 0.000")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorapagar;
	@ManyToOne
	private ContasPagar contasaPagar;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;
	
	@ManyToOne
	private FormadePagmamento formadePagamento;
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(length = 25)
	private StatusPagamento statusPagmaento;

	
}
