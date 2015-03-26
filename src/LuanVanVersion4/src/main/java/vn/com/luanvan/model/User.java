package vn.com.luanvan.model;

// default package
// Generated Jan 31, 2015 7:09:10 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
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
@Table(name = "user", catalog = "luanvan")
public class User implements java.io.Serializable {

	private String username;
	private String password;
	private boolean enabled;
	private String email;
	private String phone;
	private String address;
	private String image;
	private String fullname;
	private String idconfirm;
	private Set<Project> projects = new HashSet<Project>(0);
	private Set<UserRole> userroles = new HashSet<UserRole>(0);

	public User() {
	}

	public User(String username, String password, boolean enabled, String email, String idconfirm) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.idconfirm = idconfirm;
	}

	public User(String username, String password, boolean enabled, String idconfirm,
			String email, String phone, String address, String image,
			String fullname, Set<Project> projects, Set<UserRole> userroles) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.image = image;
		this.fullname = fullname;
		this.projects = projects;
		this.userroles = userroles;
		this.idconfirm = idconfirm;
	}

	@Id
	@Column(name = "USERNAME", unique = true, nullable = false, length = 255)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false, length = 255)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ENABLED", nullable = false, precision = 1, scale = 0)
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "EMAIL", nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PHONE", length = 12)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ADDRESS", length = 999)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "IMAGE", length = 999)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "FULLNAME")
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	@Column(name = "IDCONFIRM")
	public String getIdconfirm() {
		return this.idconfirm;
	}

	public void setIdconfirm(String id) {
		this.idconfirm = id;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserRole> getUserroles() {
		return this.userroles;
	}

	public void setUserroles(Set<UserRole> userroles) {
		this.userroles = userroles;
	}

}
