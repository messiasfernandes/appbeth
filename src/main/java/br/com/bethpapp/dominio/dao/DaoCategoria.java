package br.com.bethpapp.dominio.dao;

import org.modelmapper.internal.bytebuddy.implementation.Implementation.SpecialMethodInvocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.bethpapp.dominio.entidade.Categoria;


@Repository
public interface DaoCategoria extends JpaRepository <Categoria, Long>, JpaSpecificationExecutor<Categoria>  {

}
