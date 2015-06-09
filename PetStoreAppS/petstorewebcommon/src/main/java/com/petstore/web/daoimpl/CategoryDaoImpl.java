/**
 * 
 */
package com.petstore.web.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petstore.web.beans.Category;
import com.petstore.web.dao.CategoryDao;

/**
 * @author Praveena BiYYam
 *
 */
@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	
	@PersistenceContext
	private EntityManager em;
	
	private static List<Category> categoryList = new ArrayList<Category>();
	
	@SuppressWarnings("unchecked")
	public Category findCategoryById(int id){
		System.out.println("inside findCategoryById method in dao");
		Query query = em.createNamedQuery("getCategoryById", Category.class).setParameter("categoryId", id);
		categoryList = (List<Category>)query.getResultList();
		return categoryList.get(0);
	}
	
	public void saveCategory(Category category){
		System.out.println("inside save method in dao");
		em.persist(category);
		em.flush();
	}
	
	public void deleteCategory(Category category){
		System.out.println("inside delete method in dao");
		em.remove(category);
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> findAllCategories(){
		System.out.println("inside findAllCategories method in dao");
		Query query = em.createNamedQuery("getAllCategories", Category.class);
		categoryList = (List<Category>)query.getResultList();
		return categoryList;
	}

	@Override
	public void updateCategory(Category category) {
		System.out.println("inside updateCategory method in dao");
		em.merge(category);
	}

}
