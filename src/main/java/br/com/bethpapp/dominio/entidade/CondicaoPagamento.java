package br.com.bethpapp.dominio.entidade;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CondicaoPagamento extends GeradorId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer qtdeparcela;
	@NotNull(message = "o Campo descrição é obrigatório!!")
	@Column(length = 50)
	private String descricao;
	@Fetch(FetchMode.SUBSELECT)
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "condicao_parcelas", joinColumns = @JoinColumn(name = "codicao_id"))

	private List<Parcela> parcelas = new ArrayList<>();

}
