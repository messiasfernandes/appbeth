package br.com.bethpapp.query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.bethpapp.dominio.entidade.EstoqueMovimento;
import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.dominio.service.ServiceFuncoes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EstoqueMovmentoQueryImpl extends ServiceFuncoes implements EstoqueMovmentoQuery {
	@PersistenceContext
	EntityManager em;

	@Override
	public Page<EstoqueMovimento> buscar(String paramentro, LocalDate datanicio, LocalDate datafim, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<EstoqueMovimento> criteria = builder.createQuery(EstoqueMovimento.class);
		Root<EstoqueMovimento> root = criteria.from(EstoqueMovimento.class);
		
		//Join<EstoqueMovimento, Produto> joinproduto = root.join("produto", JoinType.INNER);
		
	//	Join<Produto, Produto> joinestoque = joinproduto.join("estoque", JoinType.LEFT);
		Predicate[] predicates = null;
   
		predicates = criarRestricoes(paramentro, datanicio, datafim, builder, root);
 
		  
		root.fetch("produto").fetch("estoque", JoinType.LEFT);
		criteria.multiselect(root
	  
				);
		criteria.groupBy(root);
		criteria.select(root);
		criteria.where ( predicates);
		
		System.out.println(root.get("produto").get("estoque").get("id"));
	
		criteria.orderBy(builder.asc(root.get("datamovimento")));
		TypedQuery<EstoqueMovimento> query = em.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(paramentro, datanicio, datafim));
	}

	private Long total(String paramentro, LocalDate datinicio, LocalDate datafim) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<EstoqueMovimento> root = criteria.from(EstoqueMovimento.class);
     
		criteria.where(criarRestricoes(paramentro, datinicio, datafim, builder, root));

		criteria.select(builder.count(root));
		
		return em.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<EstoqueMovimento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

	private Predicate[] criarRestricoes(String paramentro, LocalDate datainicio, LocalDate datafim,
			CriteriaBuilder builder, Root<EstoqueMovimento> root) {
		List<Predicate> predicates = new ArrayList<>();

		if ((datainicio != null && datafim != null && (qtdecaraceteres(paramentro) > 0))) {
		System.out.println("pasou aqui"+ datainicio);
			System.out.println("pasou aqui"+ datafim);
			System.out.println("pasou aqui"+ paramentro);
			predicates.add(builder.and(builder.like(root.get("produto").get("nomeproduto"), paramentro.toUpperCase() + "%"),
					builder.between(root.get("datamovimento"), datainicio, datafim) 
					

			));
		
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
