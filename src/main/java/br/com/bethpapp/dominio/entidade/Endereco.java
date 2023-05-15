package br.com.bethpapp.dominio.entidade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Embeddable
public class Endereco {
	
	@Column(length = 60)
	private String logradouro;
    @Size(max = 30 , message = "è permitdo no máximo 30 carecteres!!!")
	@Column(length = 30)
	private String complento;
	private String bairro;
	
	@Column(length = 9)
	private String cep;
	
	@JsonIgnoreProperties(value = { "nome", "estado" }, allowGetters = true)
	@ManyToOne()
	@JoinColumn(nullable = false)
	private Cidade cidade;

	
}
