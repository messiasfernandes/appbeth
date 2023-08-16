package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Parcela {
	
	@ManyToOne()
	@JoinColumn
	private FormadePagmamento formadePagmamento;
	@Setter(value = AccessLevel.NONE)
	@Column(nullable = false, columnDefinition = "DECIMAL(9,4) DEFAULT 0.0000")
	private BigDecimal percentual;
	@Column( columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	private BigDecimal taxadeJuro;
	@Transient
	private BigDecimal totalpercentual;
	@NotNull
	private Integer numeroparcela;
	@NotNull
	private Integer dias;
}
