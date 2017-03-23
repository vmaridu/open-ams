package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1615966070553807895L;

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dob;

	@Column(name="eye_color")
	private byte eyeColor;

	@Column(name="f_name")
	private String fName;

	@Column(name="hair_color")
	private byte hairColor;

	private float height;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="joining_dtt")
	private Date joiningDtt;

	@Column(name="l_name")
	private String lName;

	@Column(name="m_name")
	private String mName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_dtt")
	private Date modifiedDtt;

	@Column(name="picture_uri")
	private String pictureUri;

	private String prefix;

	private String race;

	private byte sex;

	private int ssn;

	private String suffix;

	private float weight;

	//bi-directional many-to-one association to Contact
	@ManyToOne
	@JoinColumn(name="contact")
	private Contact contact;

	//bi-directional many-to-one association to Contact
	@ManyToOne
	@JoinColumn(name="emr_contact")
	private Contact emrContact;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_name")
	private User user;


}