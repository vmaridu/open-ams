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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.openams.rest.jpa.entity.enums.UserStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"roles"})
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 7108735475760929898L;

	@Id
	@Column(name = "user_name")
	private String userName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "account_expire_dtt")
	private Date accountExpireDtt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "credentials_expire_dtt")
	private Date credentialsExpireDtt;

	@Column(name = "e_mail")
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_access_dtt")
	private Date lastAccessDtt;

	private String password;

	@Column(name = "password_salt")
	private String passwordSalt;

	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;

	// bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(name = "user_in_role", joinColumns = { @JoinColumn(name = "user_name") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private List<Role> roles;

}