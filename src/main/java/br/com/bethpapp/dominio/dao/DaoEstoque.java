package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bethpapp.dominio.entidade.Estoque;
@Repository
public interface DaoEstoque  extends JpaRepository<Estoque, Long>{

}
