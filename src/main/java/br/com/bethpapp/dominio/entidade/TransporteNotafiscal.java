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
public class TransporteNotafiscal extends GeradorId {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Setter(value = AccessLevel.NONE)
	@Column(length = 14)
	private String cnpj;
	@Setter(value = AccessLevel.NONE)
	@Column(length = 60)
	private String nomeTransporte;
	private Integer qtevolume;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	private BigDecimal pesoBruto;
	@Setter(value = AccessLevel.NONE)
	@Column(length = 60)
	private String enderreco;
	@Setter(value = AccessLevel.NONE)
	@Column(length = 60)
	private String placaVeiculo;
	@Setter(value = AccessLevel.NONE)
	@Column(length = 30)
	private String incricaoEstadual;

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto.setScale(4, RoundingMode.HALF_EVEN);
	}

	public void setCnpj(String cnpj) {
		if (cnpj == null) {
			this.cnpj = "";
		} else {
			this.cnpj = cnpj;
		}

	}

	public void setEnderreco(String enderreco) {
		if (enderreco == null) {
			this.enderreco = "";
		} else {

			this.enderreco = enderreco;
		}
	}
	public void setNomeTransporte(String nomeTransporte) {
		if(nomeTransporte==null) {
			this.nomeTransporte="";
		}else {
			this.nomeTransporte = nomeTransporte;
		}
		
	}
	public void setIncricaoEstadual(String incricaoEstadual) {
		if(incricaoEstadual==null) {
			this.incricaoEstadual="";
			
		}else {
			this.incricaoEstadual = incricaoEstadual;
		}
	
	}
	public void setPlacaVeiculo(String placaVeiculo) {
		if(placaVeiculo==null) {
			this.placaVeiculo="";
		}else {
			this.placaVeiculo = placaVeiculo;
		}
	
	}
}
