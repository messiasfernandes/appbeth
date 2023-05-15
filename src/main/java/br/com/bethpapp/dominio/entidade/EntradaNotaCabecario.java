package br.com.bethpapp.dominio.entidade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EntradaNotaCabecario extends GeradorId {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(length = 25)
	private String numerodanota;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_entrada;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_emissao_nota;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;
	@JsonManagedReference
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entradaNotafiscal", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemEntradaNota> items_entrada = new ArrayList<>();
	@Column(length = 255)
	private String arquivo_nota;
}
