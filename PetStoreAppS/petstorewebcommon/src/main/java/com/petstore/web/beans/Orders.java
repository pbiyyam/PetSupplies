/**
 * 
 */
package com.petstore.web.beans;

import java.io.Serializable;
import java.math.BigDecimal;

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
import com.petstore.web.util.Commonconstants;

/**
 * @author pbiyyam
 *
 */

@Entity
@Table(name=Commonconstants.ORDERS_ENTITY)
@NamedQueries({
	@NamedQuery(name = Commonconstants.GET_ALL_ORDERS, query = "SELECT O from Product O")
})
@JsonIgnoreProperties(ignoreUnknown=false)
@Proxy(lazy=false)
public class Orders implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID" , nullable = false)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ORDERID", nullable=false)
	private OrderDetails orders;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRODUCTID", nullable=false)
	private Product products;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USERID", nullable=false)
	private UserDetails users;
	
	@Column(name="TOTALCOUNT" , nullable = false)
	private int totalCount;
	
	@Column(name="TOTALPRICE" , nullable = false)
	private BigDecimal totalPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrderDetails getOrders() {
		return orders;
	}

	public void setOrders(OrderDetails orders) {
		this.orders = orders;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public UserDetails getUsers() {
		return users;
	}

	public void setUsers(UserDetails users) {
		this.users = users;
	}

}
