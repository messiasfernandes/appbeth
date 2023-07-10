/**
 * 
 */
package br.com.bethpapp.dominio.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * @author messias.fernandes
 *
 */
@Getter
@Setter
@Entity

public class Insumo extends GeradorId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
    private Produto produto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()      
	private Componente componente;
}
