/**
 * 
 */
package com.petstore.web.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author pbiyyam
 *
 */
@Entity
@Table(name="PRODUCT_DETAILS")
@NamedQueries({
	@NamedQuery(name = "getAllProducts", query = "SELECT P from Product P"),
	@NamedQuery(name="getProductById", query="SELECT P FROM Product P WHERE P.productId = :productId")
})
@JsonIgnoreProperties(ignoreUnknown=false)
@Proxy(lazy=false)
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PRODUCTID")
	private int productId;
	
	@Column(name="PRODUCTNAME")
	private String productName;
	
	@Column(name="PRODUCTPRICE")
	private float productPrice;
	
	@Column(name="PRODUCTDESC")
	private String productDesc;
	
	
	@Column(name="PRODUCTSTATUS")
	private String productStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATEGORYID", nullable=false)
	private Category category;
	
	public Product(){
		
	}
	
	public Product(Category category,String productName,float productPrice, String productDesc, String productStatus){
		this.category = category;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDesc = productDesc;
		this.productStatus = productStatus;
	}
	
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int
			productId) {
		this.productId = productId;
	}
	
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	public float getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	
	
	public String getProductDesc() {
		return productDesc;
	}
	
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	
	public String getProductStatus() {
		return productStatus;
	}
	
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Product other = (Product) obj;
		if (productId != other.productId) {
			return false;
		}
		if (productName == null) {
			if (other.productName != null) {
				return false;
			}
		} else if (!productName.equals(other.productName)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productName="
				+ productName + ", productDesc=" + productDesc + ", productPrices="
				+ productPrice + "]";
	}
		
}
