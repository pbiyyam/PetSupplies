/**
 * 
 */
package com.petstore.web.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petstore.web.beans.Category;
import com.petstore.web.dao.CategoryDao;
import com.petstore.web.util.Commonconstants;

/**
 * This class serves as a repository for all category related db operations
 * @author Praveena BiYYam
 *
 */
@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	
	@PersistenceContext
	private EntityManager em;
	
	private static List<Category> categoryList = new ArrayList<Category>();
	
	/**
	 * This method is used for finding category depending on id
	 * @param id int
	 * @return category
	 */
	public Category findCategoryById(int id){
		TypedQuery<Category> query = em.createNamedQuery(Commonconstants.GET_CATEGORY_BYID, Category.class).setParameter(Commonconstants.CATEGORY_ID, id);
		categoryList = (List<Category>)query.getResultList();
		return categoryList.get(0);
	}
	
	/**
	 * This method is used for saving a new category
	 * @param category
	 */
	public void saveCategory(Category category){
		em.persist(category);
		em.flush();
	}
	
	/**
	 * This method is used for deleting selected category
	 * @param category
	 */
	public void deleteCategory(Category category){
		em.remove(category);
	}
	
	/**
	 * This method is used for finding all available categories
	 * @return List<Category>
	 */
	public List<Category> findAllCategories(){
		TypedQuery<Category> query = em.createNamedQuery(Commonconstants.GET_ALL_CATEGORIES, Category.class);
		categoryList = (List<Category>)query.getResultList();
		return categoryList;
	}

	/**
	 * This method is used for updating existing category
	 * @param category
	 */
	@Override
	public void updateCategory(Category category) {
		em.merge(category);
	}

}
