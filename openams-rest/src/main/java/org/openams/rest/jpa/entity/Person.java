package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.openams.rest.jpa.entity.enums.EyeColor;
import org.openams.rest.jpa.entity.enums.Gender;
import org.openams.rest.jpa.entity.enums.HairColor;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
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

	@Column(name="picture_uri")
	private String pictureUri;

	private String prefix;

	private String race;

	@Enumerated(EnumType.ORDINAL)
	private Gender sex;

	private Integer ssn;

	private String suffix;

	private Float weight;

	//bi-directional many-to-one association to Contact
	@ManyToOne
	@JoinColumn(name="contact")
	private Contact contact1;

	//bi-directional many-to-one association to Contact
	@ManyToOne
	@JoinColumn(name="emr_contact")
	private Contact contact2;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="user_name")
	private User user;

	//bi-directional many-to-one association to TestScore
	@OneToMany(mappedBy="person")
	private List<TestScore> testScores;

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

	public Contact getContact1() {
		return this.contact1;
	}

	public void setContact1(Contact contact1) {
		this.contact1 = contact1;
	}

	public Contact getContact2() {
		return this.contact2;
	}

	public void setContact2(Contact contact2) {
		this.contact2 = contact2;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<TestScore> getTestScores() {
		return this.testScores;
	}

	public void setTestScores(List<TestScore> testScores) {
		this.testScores = testScores;
	}

	public TestScore addTestScore(TestScore testScore) {
		getTestScores().add(testScore);
		testScore.setPerson(this);

		return testScore;
	}

	public TestScore removeTestScore(TestScore testScore) {
		getTestScores().remove(testScore);
		testScore.setPerson(null);

		return testScore;
	}

}