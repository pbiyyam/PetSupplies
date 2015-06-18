/**
 * 
 */
package com.petstore.web.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.petstore.web.util.Commonconstants;

/**
 * @author pbiyyam
 *
 */
@Entity
@Table(name=Commonconstants.CATEGORY_ENTITY)
@NamedQueries({
	@NamedQuery(name = Commonconstants.GET_ALL_CATEGORIES, query = "SELECT C from Category C"),
	@NamedQuery(name=Commonconstants.GET_CATEGORY_BYID, query="SELECT C FROM Category C WHERE C.categoryId = :categoryId")
})
@JsonIgnoreProperties(ignoreUnknown=false)
@Proxy(lazy=false)
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CATEGORYID", unique=true, nullable=false)
	private int categoryId;
	
	@Column(name="CATEGORYNAME", unique=true, nullable=false, length=30)
	private String categoryName;
	
	@Column(name="CATEGORYDESC", nullable=false, length=50)
	private String categoryDesc;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="category")
	private Set<Product> productSet = new HashSet<Product>(0);
	
	public Category(){
		
	}
	
	public Category(String categoryName, String categoryDesc){
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
	}
	
	public Category(String categoryName, String categoryDesc, Set<Product> productSet){
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.productSet = productSet;
	}
	
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	public Set<Product> getProductSet() {
		return productSet;
	}

	public void setProductSet(Set<Product> productSet) {
		this.productSet = productSet;
	}

	
	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result
				+ ((categoryName == null) ? 0 : categoryName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Category)) {
			return false;
		}
		Category other = (Category) obj;
		if (categoryId != other.categoryId) {
			return false;
		}
		if (categoryName == null) {
			if (other.categoryName != null) {
				return false;
			}
		} else if (!categoryName.equals(other.categoryName)) {
			return false;
		}
		return true;
	}
	
}
