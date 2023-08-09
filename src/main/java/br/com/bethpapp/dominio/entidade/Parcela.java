package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Parcela {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private FormadePagmamento formadePagmamento;
	@Column(nullable = false, columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	private BigDecimal percentual;
	@Column( columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	private BigDecimal juros;
	@Transient
	private BigDecimal totalpercentual;
	@NotNull
	private Integer numeroparcela;
	@NotNull
	private Integer dias;
}
