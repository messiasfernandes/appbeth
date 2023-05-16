package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bethpapp.dominio.entidade.ContasPagar;
@Repository
public interface DaoContasApagar extends JpaRepository<ContasPagar, java.lang.Long> {

}
