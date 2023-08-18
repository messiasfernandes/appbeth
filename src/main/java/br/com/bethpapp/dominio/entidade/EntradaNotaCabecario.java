package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
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
	@Column(length = 60)
	private String naturezaopercao;
	@Column(length = 10)
	private String modelo;
	@Column(length = 10)
	private String serie;
	@Setter(value = AccessLevel.NONE)
	@Getter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	@Transient
	private BigDecimal totalInf;
	@ManyToOne( optional = true, cascade = CascadeType.ALL)
    private TransporteNotafiscal transporteNotafiscal;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;
	@JsonManagedReference
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entradaNotafiscal", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemEntradaNota> items_entrada = new ArrayList<>();
	@Column(length = 255)
	private String arquivo_nota;
	public void setTotalInf(BigDecimal totalInf) {
		this.totalInf = totalInf.setScale(4, RoundingMode.HALF_EVEN);
	}
	
	
	@Transient
	private BigDecimal somarTotalnota() {
		totalInf=BigDecimal.ZERO;
	for (int i = 0; i < items_entrada.size(); i++) {
	this.totalInf=	totalInf.add(  items_entrada.get(i).getSubtotal());
		System.out.println(totalInf);
	}
	
	return totalInf;
	}
	public BigDecimal getTotalInf() {
		return somarTotalnota();
	}
}
