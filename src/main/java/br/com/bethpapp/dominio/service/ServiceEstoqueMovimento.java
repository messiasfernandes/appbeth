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
import br.com.bethpapp.dominio.service.exeption.NegocioException;
import jakarta.transaction.Transactional;

@Service
public class ServiceEstoqueMovimento extends ServiceFuncoes implements ServiceModel<EstoqueMovimento> {
	@Autowired
	private DaoMovementacaoEstoque daoMovementacaoEstoque;
	@Autowired
	private ServiceEstoque serviceEstoque;


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

		verificarMovimento(objeto);
		objeto.setDatamovimento(LocalDate.now());
		return daoMovementacaoEstoque.save(objeto);
	}

	public Page<EstoqueMovimento> listar(String paramentro, String tipo, LocalDate datanicio, LocalDate datafim, Pageable pageable) {
		return daoMovementacaoEstoque.buscar(paramentro,tipo, datanicio, datafim, pageable);
	}

	private EstoqueMovimento verificarMovimento(EstoqueMovimento movimento) {
		if (movimento.getTipoMovimentacao() == TipoMovimentacao.Entrada) {
			if (movimento.getProduto().getEstoque() != null) {

				SomarEstoque(movimento);

				serviceEstoque.salvar(movimento.getProduto().getEstoque());
			} else {

				serviceEstoque.salvar(adicionarEstoque(movimento));
			}
		} else {
			serviceEstoque.salvar(baxarEstoque(movimento).getProduto().getEstoque());
		}

		return movimento;

	}

	private EstoqueMovimento baxarEstoque(EstoqueMovimento movimento) {
		if (movimento.getProduto().getEstoque()==null) {
			throw new NegocioException("Não possivel baixar estoque de um produto que não tenha estoque");
		}
		movimento.setSaldoanterior(movimento.getProduto().getEstoque().getQuantidade());
		movimento.getProduto().getEstoque().setQuantidade(movimento.getSaldoanterior() - movimento.getQtde());
		movimento.getProduto().getEstoque().setProduto(movimento.getProduto());
		return movimento;
	}

	private EstoqueMovimento SomarEstoque(EstoqueMovimento movimento) {
		movimento.setSaldoanterior(movimento.getProduto().getEstoque().getQuantidade());
		movimento.getProduto().getEstoque().setQuantidade(movimento.getSaldoanterior() + movimento.getQtde());
		movimento.getProduto().getEstoque().setProduto(movimento.getProduto());

		return movimento;

	}

	private Estoque adicionarEstoque(EstoqueMovimento movimento) {
		var estoque = new Estoque();
		estoque.setProduto(movimento.getProduto());
		estoque.setQuantidade(movimento.getQtde());
		return estoque;
	}
}
