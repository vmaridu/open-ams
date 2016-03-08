package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.openams.rest.jpa.entity.enums.UserStatus;


/**
 * The persistent class for the user database table.
 * 
 */
@ApiObject(name = "User")
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiObjectField
	@Id
	@Column(name="user_name")
	private String userName;

	@ApiObjectField(description = "Account Expiry Timestamp (EPOCH Milliseconds in GMT)")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="account_expire_dtt")
	private Date accountExpireDtt;

	@ApiObjectField(description = "Credential Expiry Timestamp (EPOCH Milliseconds in GMT)")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="credentials_expire_dtt")
	private Date credentialsExpireDtt;

	@ApiObjectField
	@Column(name="e_mail")
	private String eMail;

	@ApiObjectField(description = "Last Access Timestamp (EPOCH Milliseconds in GMT)")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_access_dtt")
	private Date lastAccessDtt;

	@ApiObjectField
	private String password;

	@ApiObjectField
	@Column(name="password_salt")
	private String passwordSalt;

	@ApiObjectField
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;

	//bi-directional many-to-many association to Role
	@ApiObjectField
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