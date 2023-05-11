package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ItemEntradaNota extends GeradorId {

	private static final long serialVersionUID = 1L;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal qtde;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal subtotal;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Produto produto;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private EntradaNotaCabecario entradaNotafiscal;
}
