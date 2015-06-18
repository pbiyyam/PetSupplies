/**
 * 
 */
package com.petstore.web.beans;

import java.io.Serializable;

/**
 * @author Praveena BiYYam
 *
 */
public class ShipmentDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int orderId;
	private String userId;
	private int productId;
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

	
}
