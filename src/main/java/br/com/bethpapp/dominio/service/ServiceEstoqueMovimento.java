package br.com.bethpapp.dominio.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bethpapp.dominio.dao.DaoMovementacaoEstoque;
import br.com.bethpapp.dominio.entidade.Estoque;
import br.com.bethpapp.dominio.entidade.EstoqueMovimento;
import br.com.bethpapp.dominio.enumerado.TipoMovimentacao;
import jakarta.transaction.Transactional;

@Service
public class ServiceEstoqueMovimento extends ServiceFuncoes implements ServiceModel<EstoqueMovimento> {
	@Autowired
	private DaoMovementacaoEstoque daoMovementacaoEstoque;
	@Autowired
	private ServiceEstoque serviceEstoque;
	@Autowired
     private ServiceProduto serviceProduto;
	@Override
	public Page<EstoqueMovimento> buscar(String nome, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public EstoqueMovimento buccarporid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
    @Transactional
	@Override
	public EstoqueMovimento salvar(EstoqueMovimento objeto) {
    	
   	objeto=	 verificarMovimento(objeto);
        objeto.setDatamovimento(LocalDate.now());
		return daoMovementacaoEstoque.save(objeto);
	}

	public Page<EstoqueMovimento> listar(String paramentro, LocalDate datanicio, LocalDate datafim, Pageable pageable) {
		return daoMovementacaoEstoque.buscar(paramentro, datanicio, datafim, pageable);
	}
  private EstoqueMovimento verificarMovimento (EstoqueMovimento movimento) {
	  if (movimento.getTipoMovimentacao() == TipoMovimentacao.Entrada ) {
		  if (movimento.getProduto().getEstoque() != null) {
			  System.out.println("pasou aqui");
		    	movimento.setSaldoanterior(movimento.getProduto().getEstoque().getQuantidade());
		    	movimento.getProduto().getEstoque().setQuantidade(movimento.getQtde()+movimento.getSaldoanterior());
		    	movimento.getProduto().getEstoque().setProduto(movimento.getProduto());
		    	serviceEstoque.salvar(movimento.getProduto().getEstoque());
		    	
		    	System.out.println(movimento.getProduto().getId());
		    	System.out.println(movimento.getProduto().getNomeproduto());
		    	System.out.println(movimento.getProduto().getEstoque().getQuantidade());
		    	
		    }else {
		    	var estoque = new Estoque();
		    	estoque.setProduto(movimento.getProduto());
		    	estoque.setQuantidade(movimento.getQtde());
		    	serviceEstoque.salvar(estoque);
		    }
	  }
	  return movimento;
	  
  }
}
