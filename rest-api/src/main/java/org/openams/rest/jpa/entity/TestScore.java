package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the test_score database table.
 * 
 */
@Entity
@Table(name="test_score")
@NamedQuery(name="TestScore.findAll", query="SELECT t FROM TestScore t")
public class TestScore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_dtt")
	private Date endDtt;

	private String grade;

	private String notes;

	private int score;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_dtt")
	private Date startDtt;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	//bi-directional many-to-one association to Test
	@ManyToOne
	private Test test;

	public TestScore() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEndDtt() {
		return this.endDtt;
	}

	public void setEndDtt(Date endDtt) {
		this.endDtt = endDtt;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getStartDtt() {
		return this.startDtt;
	}

	public void setStartDtt(Date startDtt) {
		this.startDtt = startDtt;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

}