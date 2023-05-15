package br.com.bethpapp.dominio.entidade;

import java.io.Serializable;

import br.com.bethpapp.dominio.enumerado.TipoPessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import lombok.Data;




@Data
@MappedSuperclass
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
   public Pessoa() {
	
}
	@Column(length = 100)
	private String nome;

	@Column(length = 14)
	private String cpfouCnpj;

	@Column(length = 12)
	private String telefone;


	@Email
	@Column(length = 150)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private TipoPessoa tipessoa;
	@Embedded
	private Endereco endereco;



}
