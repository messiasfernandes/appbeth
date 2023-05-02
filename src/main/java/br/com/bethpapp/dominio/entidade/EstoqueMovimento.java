package br.com.bethpapp.dominio.entidade;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.bethpapp.dominio.enumerado.TipoMovimentacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EstoqueMovimento extends GeradorId {

	private static final long serialVersionUID = 1L;
	@Column(length = 15, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;

	@DateTimeFormat(pattern = " dd/MM/yyyy ")
	@JsonFormat(pattern = "dd/MM/yyyy ")
	private LocalDate datamovimento;
 //
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
    private Produto produto;
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonBackReference
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn
//	private Estoque estoque;
	private Integer qtde;
	private Integer saldoanterior;

}
