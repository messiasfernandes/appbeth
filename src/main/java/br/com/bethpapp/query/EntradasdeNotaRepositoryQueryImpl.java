package br.com.bethpapp.query;

import java.util.ArrayList;

import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EntradasdeNotaRepositoryQueryImpl  implements EntradasdeNotaRepositoryQuery {
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
	
	
	

}
