/**
 * 
 */
package com.petstore.web.beans;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name=Commonconstants.ORDER_DETAILS_ENTITY)
@NamedQueries({
	@NamedQuery(name = "getAllOrderDetails", query = "SELECT OD from Product OD")
})
@JsonIgnoreProperties(ignoreUnknown=false)
@Proxy(lazy=false)
public class OrderDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORDERID" , nullable = false)
	private int orderId;
	
	@Column(name="ORDERDATE" , nullable = false)
	private Date orderDate;
	
	@Column(name="SHIPPINGADDR" , nullable = false)
	private String shippingAddr;
	
	@Column(name="SHIPPINGCITY" , nullable = false)
	private String shippingCity;
	
	@Column(name="PINCODE" , nullable = false)
	private long pinCode;
	
	@Column(name="ORDERSTATUS" , nullable = false)
	private String orderStatus;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="orders")
	private Set<Orders> orderSet = new HashSet<Orders>(0);

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAddr() {
		return shippingAddr;
	}

	public void setShippingAddr(String shippingAddr) {
		this.shippingAddr = shippingAddr;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Set<Orders> getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(Set<Orders> orderSet) {
		this.orderSet = orderSet;
	}

}
