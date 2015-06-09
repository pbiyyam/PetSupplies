package com.petstore.web.beans;

/**
 * 
 */

import java.io.Serializable;

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

/**
 * @author Praveena BiYYam
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "getUserInfo", query = " SELECT U FROM UserDetails U"),
	@NamedQuery(name="getEmailIdCount", query="SELECT U FROM UserDetails U WHERE U.emailId = :emailId"),
	@NamedQuery(name="getUserEmailIdPwd", query="SELECT U from UserDetails U WHERE U.emailId = :emailId"),
	@NamedQuery(name="getAdminEmailIdPwd", query="SELECT U from UserDetails U WHERE U.emailId = :emailId AND U.roles.roleId = 2")
})
@Table(name = "USER_DETAILS")
public class UserDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="ID", unique=true, nullable=false)
	private long id;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "FIRSTNAME")
	private String firstName;
	
	@Column(name = "LASTNAME")
	private String lastName;
	
	@Column(name = "USERADDRESS")
	private String userAddress;
	
	@Column(name = "CONTACTNUM")
	private String contactNum;
	
	@Column(name = "EMAILID")
	private String emailId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLEID", nullable = false)
	private RoleDetails roles;

	
	public RoleDetails getRoles() {
		return roles;
	}

	public void setRoles(RoleDetails roles) {
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastNamel) {
		this.lastName = lastNamel;
	}

	
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	
	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
