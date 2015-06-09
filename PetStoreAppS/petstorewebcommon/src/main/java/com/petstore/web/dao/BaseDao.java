/**
 * 
 */
package com.petstore.web.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author Praveena BiYYam
 *
 */
public abstract class BaseDao<T> {

	@PersistenceContext
	private EntityManager em;
	
	private Class<T> entityClass;
	
	/**
	 * constructor
	 * This is setting entity class type
	 */
	public BaseDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	/**
	 * saves the entity in the database
	 * @param entity 
	 */
	public void save(T entity) {
		em.persist(entity);
	}

	public void delete(T entity) {
		T entityToBeRemoved = em.merge(entity);

		em.remove(entityToBeRemoved);
	}
	
	public T update(T entity) {
		return em.merge(entity);
	}
	
	public T find(int entityID) {
		em.clear();
		return em.find(entityClass, entityID);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	protected T getResultByParameter(String namedQuery, Map<String, Object> parameters) {
		T result = null;
		try {
			Query query = em.createNamedQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (T) query.getSingleResult();
		} catch (Exception e) {
			//LOGGER.log(Level.INFO, "Can not find search result for" + this.entityClass.getName());
		}
		return result;
	}
	
	private void populateQueryParameters(Query query,
		Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
}
