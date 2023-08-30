package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bethpapp.dominio.entidade.ContasPagar;

public interface DaoContasApagar extends JpaRepository<ContasPagar, java.lang.Long> {

}
