package br.com.bethpapp.dominio.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Estoque extends GeradorId {

	private static final long serialVersionUID = 1L;

// @JsonIgnore

	@OneToOne(optional = false)
	@JoinColumn()
	private Produto produto;

	private Integer quantidade;

}
