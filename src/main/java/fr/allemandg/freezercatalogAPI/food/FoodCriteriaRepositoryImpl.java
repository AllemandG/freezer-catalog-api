package fr.allemandg.freezercatalogAPI.food;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import fr.allemandg.freezercatalogAPI.util.AbstractJPARepository;


/**
 * FoodCriteriaRepositoryImpl is the class implementing the search by criteria method on <code>Food</code> items
 * 
 * 
 * @author Guillaume Allemand
 */
@Repository
public class FoodCriteriaRepositoryImpl extends AbstractJPARepository<Food> implements FoodCriteriaRepository {
	
	/**
	 * 
	 */
	@Override
	protected Class<Food> getEntityClass() {
		return Food.class;
	}
	
	/**
	 * Searches the database, using a <code>CriteriaQuery</code>, for a list of <code>Food</code> items corresponding 
	 * to the parameters.
	 * 
	 * Each parameter is optional.
	 * 
	 * The query is built with the use of <code>Predicate</code> and when the query is created using the EntityManager,
	 * then the parameters are set to their given value for the actual search.
	 * 
	 * @param name the name of Food searched
	 * @param type the type of Food searched
	 * @param date the date when the Food was added
	 * @return The list of all results
	 */
	@Override
	public List<Food> findByCriteria(String name, Type type, Date date) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Food> q = cb.createQuery(Food.class);
		Root<Food> f = q.from(Food.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		ParameterExpression<String> n = null;
		ParameterExpression<Type> t = null;
		ParameterExpression<Date> d = null;
		
		if ( !StringUtils.isEmpty(name) ) {
			n = cb.parameter(String.class);
			Predicate namePredicate = cb.like(f.get("name"), n);
			predicates.add(namePredicate);
		}
		
		if ( type != null ) {
			t = cb.parameter(Type.class);
			Predicate typePredicate = cb.equal(f.get("type"), t);
			predicates.add(typePredicate);
		}
		
		if ( date != null ) {
			d = cb.parameter(Date.class);
			Predicate datePredicate = cb.equal(f.get("date"), d);
			predicates.add(datePredicate);
		}
		
		q.select(f).where( predicates.toArray( new Predicate[]{} ) );
		
		
		TypedQuery<Food> query = em.createQuery(q);
		
		if ( !StringUtils.isEmpty(name) ) {
			query.setParameter(n, name);
		}
		if ( type != null ) {
			query.setParameter(t, type);
		}
		if ( date != null ) {
			query.setParameter(d, date);
		}
		
		return query.getResultList();
	}
}
