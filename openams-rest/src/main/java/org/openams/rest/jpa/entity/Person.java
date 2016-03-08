package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.openams.rest.jpa.entity.enums.EyeColor;
import org.openams.rest.jpa.entity.enums.Gender;
import org.openams.rest.jpa.entity.enums.HairColor;


/**
 * The persistent class for the person database table.
 * 
 */
@ApiObject
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiObjectField
	@Id
	private String id;

	@ApiObjectField(description = "Date of Birth (EPOCH Milliseconds in GMT)")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dob;

	@ApiObjectField
	@Column(name="eye_color")
	@Enumerated(EnumType.ORDINAL)
	private EyeColor eyeColor;

	@ApiObjectField
	@Column(name="f_name")
	private String fName;

	@ApiObjectField
	@Column(name="hair_color")
	@Enumerated(EnumType.ORDINAL)
	private HairColor hairColor;

	@ApiObjectField
	private Float height;

	@ApiObjectField(description = "Joining Date (EPOCH Milliseconds in GMT)")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="joining_dtt")
	private Date joiningDtt;

	@ApiObjectField
	@Column(name="l_name")
	private String lName;

	@ApiObjectField
	@Column(name="m_name")
	private String mName;

	@Column(name="picture_uri")
	private String pictureUri;

	@ApiObjectField
	private String prefix;

	@ApiObjectField
	private String race;

	@ApiObjectField
	@Enumerated(EnumType.ORDINAL)
	private Gender sex;

	@ApiObjectField
	private Integer ssn;

	@ApiObjectField
	private String suffix;

	@ApiObjectField
	private Float weight;

	//bi-directional many-to-one association to Contact
	@ApiObjectField
	@ManyToOne
	@JoinColumn(name="contact")
	private Contact contact;

	//bi-directional many-to-one association to Contact
	@ApiObjectField
	@ManyToOne
	@JoinColumn(name="emr_contact")
	private Contact emrContact;

	//bi-directional many-to-one association to User
	@ApiObjectField
	@ManyToOne
	@JoinColumn(name="user_name")
	private User user;

	public Person() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public EyeColor getEyeColor() {
		return this.eyeColor;
	}

	public void setEyeColor(EyeColor eyeColor) {
		this.eyeColor = eyeColor;
	}

	public String getFName() {
		return this.fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public HairColor getHairColor() {
		return this.hairColor;
	}

	public void setHairColor(HairColor hairColor) {
		this.hairColor = hairColor;
	}

	public Float getHeight() {
		return this.height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Date getJoiningDtt() {
		return this.joiningDtt;
	}

	public void setJoiningDtt(Date joiningDtt) {
		this.joiningDtt = joiningDtt;
	}

	public String getLName() {
		return this.lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getMName() {
		return this.mName;
	}

	public void setMName(String mName) {
		this.mName = mName;
	}

	public String getPictureUri() {
		return this.pictureUri;
	}

	public void setPictureUri(String pictureUri) {
		this.pictureUri = pictureUri;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getRace() {
		return this.race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public Gender getSex() {
		return this.sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

	public Integer getSsn() {
		return this.ssn;
	}

	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Float getWeight() {
		return this.weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Contact getEmrContact() {
		return emrContact;
	}

	public void setEmrContact(Contact emrContact) {
		this.emrContact = emrContact;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}