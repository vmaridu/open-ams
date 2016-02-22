package org.openams.rest.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.openams.rest.jpa.entity.enums.UserStatus;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_name")
	private String userName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="account_expire_dtt")
	private Date accountExpireDtt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="credentials_expire_dtt")
	private Date credentialsExpireDtt;

	@Column(name="e_mail")
	private String eMail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_access_dtt")
	private Date lastAccessDtt;

	private String password;

	@Column(name="password_salt")
	private String passwordSalt;

	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="user_in_role"
		, joinColumns={
			@JoinColumn(name="user_name")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private List<Role> roles;

	public User() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getAccountExpireDtt() {
		return this.accountExpireDtt;
	}

	public void setAccountExpireDtt(Date accountExpireDtt) {
		this.accountExpireDtt = accountExpireDtt;
	}

	public Date getCredentialsExpireDtt() {
		return this.credentialsExpireDtt;
	}

	public void setCredentialsExpireDtt(Date credentialsExpireDtt) {
		this.credentialsExpireDtt = credentialsExpireDtt;
	}

	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public Date getLastAccessDtt() {
		return this.lastAccessDtt;
	}

	public void setLastAccessDtt(Date lastAccessDtt) {
		this.lastAccessDtt = lastAccessDtt;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public UserStatus getStatus() {
		return this.status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}