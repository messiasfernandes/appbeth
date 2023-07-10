package br.com.bethpapp.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.dominio.service.ServiceFuncoes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EntradasdeNotaRepositoryQueryImpl extends ServiceFuncoes implements EntradasdeNotaRepositoryQuery {
	@PersistenceContext
	private EntityManager maneger;

	@Override
	public Boolean buscarnota(Long codigoFonecedor, String numeroNota) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();

		CriteriaQuery<EntradaNotaCabecario> criteria = builder.createQuery(EntradaNotaCabecario.class);
		Root<EntradaNotaCabecario> root = criteria.from(EntradaNotaCabecario.class);
		var predicates = new ArrayList<Predicate>();
		predicates.add(builder.equal(root.get("fornecedor").get("id"), codigoFonecedor));
		predicates.add(builder.equal(root.get("numerodanota"), numeroNota));
		criteria.where(predicates.toArray(new Predicate[0]));
		return !maneger.createQuery(criteria).getResultList().isEmpty();
	}

	@Override
	public Page<EntradaNotaCabecario> buscar(String paramentro, Pageable pageable) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<EntradaNotaCabecario> criteria = builder.createQuery(EntradaNotaCabecario.class);
		Root<EntradaNotaCabecario> root = criteria.from(EntradaNotaCabecario.class);
		Predicate[] predicates = null;

		predicates = criarRestricoes(paramentro, builder, root);

		root.fetch("fornecedor", JoinType.INNER);

		criteria.select(root);

		criteria.where(predicates);

	
		TypedQuery<EntradaNotaCabecario> query = maneger.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(paramentro));
	}

	private Long total(String paramentro) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();

		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<EntradaNotaCabecario> root = criteria.from(EntradaNotaCabecario.class);

		criteria.where(criarRestricoes(paramentro, builder, root));

		criteria.select(builder.count(root));
		return maneger.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<EntradaNotaCabecario> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

	private Predicate[] criarRestricoes(String paramentro, CriteriaBuilder builder, Root<EntradaNotaCabecario> root) {
		List<Predicate> predicates = new ArrayList<>();

		if ((!ehnumero(paramentro) && (qtdecaraceteres(paramentro) > 0))) {
			System.out.println("pasou fornecedor"+ paramentro);
			predicates.add(builder.like(root.get("fornecedor").get("nome"), paramentro.toUpperCase() + "%")

			);

		}

		if ((ehnumero(paramentro)) && (qtdecaraceteres(paramentro) <11)) {
			System.out.println("pasou aqui"+ paramentro);
			//Long id = Sonumero(paramentro);
			predicates.add(builder.or(builder.equal(root.get("numerodanota"), paramentro)));
		}

		if ((ehnumero(paramentro)) && (qtdecaraceteres(paramentro) == 14)||(qtdecaraceteres(paramentro) == 11)) {
			predicates.add(builder.like(root.get("fornecedor").get("cpfouCnpj"), paramentro + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
