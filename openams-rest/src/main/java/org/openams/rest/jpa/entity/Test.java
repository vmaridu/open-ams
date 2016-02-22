package org.openams.rest.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the test database table.
 * 
 */
@Entity
@NamedQuery(name="Test.findAll", query="SELECT t FROM Test t")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String desc;

	@Column(name="end_dtt")
	private String endDtt;

	@Column(name="max_score")
	private String maxScore;

	private String name;

	@Column(name="start_dtt")
	private String startDtt;

	@Column(name="test_type")
	private String testType;

	//bi-directional many-to-one association to Course
	@ManyToOne
	private Course course;

	//bi-directional many-to-one association to TestScore
	@OneToMany(mappedBy="test")
	private List<TestScore> testScores;

	public Test() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getEndDtt() {
		return this.endDtt;
	}

	public void setEndDtt(String endDtt) {
		this.endDtt = endDtt;
	}

	public String getMaxScore() {
		return this.maxScore;
	}

	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDtt() {
		return this.startDtt;
	}

	public void setStartDtt(String startDtt) {
		this.startDtt = startDtt;
	}

	public String getTestType() {
		return this.testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<TestScore> getTestScores() {
		return this.testScores;
	}

	public void setTestScores(List<TestScore> testScores) {
		this.testScores = testScores;
	}

	public TestScore addTestScore(TestScore testScore) {
		getTestScores().add(testScore);
		testScore.setTest(this);

		return testScore;
	}

	public TestScore removeTestScore(TestScore testScore) {
		getTestScores().remove(testScore);
		testScore.setTest(null);

		return testScore;
	}

}