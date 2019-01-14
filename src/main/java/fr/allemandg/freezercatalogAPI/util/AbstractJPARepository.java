package fr.allemandg.freezercatalogAPI.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;


/**
 * AbstractJPARepository<T> is a class made to be versatile and extended by other repositories
 * to provide them with an EntityManager and an easy access to the Session if needed.
 * 
 * 
 * @author Guillaume Allemand
 *
 * @param <T> the class to which the repository will be assigned
 */
public abstract class AbstractJPARepository<T> {
	protected Class<T> entityClass;
	
	@PersistenceContext
	protected EntityManager em;
	
	protected void init() {
		entityClass = getEntityClass();
	}
	
	protected abstract Class<T> getEntityClass();
	
	protected Session getSession() {
		return em.unwrap(Session.class);
	}
}
