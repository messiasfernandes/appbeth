/**
 * 
 */
package br.com.bethpapp.modelo.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.bethpapp.dominio.entidade.Cidade;
import br.com.bethpapp.dominio.enumerado.TipoPessoa;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * @author messias
 *
 */
@Data
public class FornecedorInput {

	private Long id;

	private String nome;

	private String cpfouCnpj;

	private String telefone;

	private String email;

	private TipoPessoa tipessoa;

	private String rg_Inscricao;

	private String logradouro;

	private String complento;
	private String bairro;

	private String cep;

	private Cidade cidade;

}
