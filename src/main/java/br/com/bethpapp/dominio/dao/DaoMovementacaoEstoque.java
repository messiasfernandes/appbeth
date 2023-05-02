package br.com.bethpapp.dominio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bethpapp.dominio.entidade.EstoqueMovimento;
import br.com.bethpapp.query.EstoqueMovmentoQuery;


@Repository
public interface DaoMovementacaoEstoque extends JpaRepository<EstoqueMovimento, Long>, EstoqueMovmentoQuery {

}
