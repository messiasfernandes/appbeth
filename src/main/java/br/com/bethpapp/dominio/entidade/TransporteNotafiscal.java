/**
 * 
 */
package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
@Entity
public class TransporteNotafiscal  extends GeradorId{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(length = 14)
    private String cnpj;
	@Column(length = 60)
	private String nomeTransporte;
	private Integer qtevolume;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	private BigDecimal pesoBruto;
	@Column(length = 60)
	private String enderreco;
	@Column(length = 60)
	private String placaVeiculo;
	@Column(length = 30)
	private String incricaoEstadual;
	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto.setScale(4,RoundingMode.HALF_EVEN);
	}
}
