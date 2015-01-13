package vn.com.luanvan.model;
// default package
// Generated Sep 9, 2014 4:09:16 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;




/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "users")
public class User implements java.io.Serializable {

	private String username;
	private String password;
	private String email;
	private Boolean enabled;
	private Set<UserRole> userroles = new HashSet<UserRole>(0);
	

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String email,
			Boolean enabled, Set<UserRole> userroles) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.userroles = userroles;
	}

	@Id
	@Column(name = "USERNAME", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL", length = 255)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ENABLED")
	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	public Set<UserRole> getUserroles() {
		return this.userroles;
	}

	public void setUserroles(Set<UserRole> userroles) {
		this.userroles = userroles;
	}


	

	

}
