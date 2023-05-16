package br.com.bethpapp.dominio.entidade;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tab_estado")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long cod_estado;
	@Column(length = 100)
	private String nome;
	@Column(length = 2)
	private String sigla;

	






}
