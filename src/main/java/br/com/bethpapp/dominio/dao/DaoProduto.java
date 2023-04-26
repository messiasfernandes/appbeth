package br.com.bethpapp.dominio.dao;
import br.com.bethpapp.dominio.entidade.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DaoProduto extends JpaRepository<Produto, java.lang.Long> {

}
