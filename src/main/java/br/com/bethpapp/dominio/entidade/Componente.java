package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Componente extends GeradorId {

	private static final long serialVersionUID = 1L;
	@Digits(integer = 6, fraction = 3)
	@NotNull
	@Column
	private BigDecimal qtde;
	   @ManyToOne
	    @JoinColumn()
	    private Produto produto;

	    @ManyToMany(mappedBy = "componentes")
	    private List<Produto> produtos;
}
