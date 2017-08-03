package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.openams.rest.jpa.entity.enums.EyeColor;
import org.openams.rest.jpa.entity.enums.Gender;
import org.openams.rest.jpa.entity.enums.HairColor;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"contact", "emrContact", "user"})
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
	@Enumerated(EnumType.ORDINAL)
	private EyeColor eyeColor;

	@Column(name="f_name")
	private String fName;

	@Column(name="hair_color")
	@Enumerated(EnumType.ORDINAL)
	private HairColor hairColor;

	private Float height;

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

	@Enumerated(EnumType.ORDINAL)
	private Gender gender;

	private Integer ssn;

	private String suffix;

	private Float weight;

	//bi-directional many-to-one association to Contact
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="contact")
	private Contact contact;

	//bi-directional many-to-one association to Contact
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="emr_contact")
	private Contact emrContact;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "USER_NAME")
	private User user;


}