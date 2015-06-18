/**
 * 
 */
package com.petstore.web.beans;

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

import com.petstore.web.util.Commonconstants;

/**
 * @author pbiyyam
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Commonconstants.GET_ROLES , query="SELECT R from RoleDetails R WHERE R.roleId = :roleId")
})
@Table(name = Commonconstants.ROLE_DETAILS_ENTITY)
public class RoleDetails {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROLEID")
	private int roleId;
	
	@Column(name="ROLENAME")
	private String roleName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private Set<UserDetails> users = new HashSet<UserDetails>(0);

	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Set<UserDetails> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDetails> users) {
		this.users = users;
	}
	
}
