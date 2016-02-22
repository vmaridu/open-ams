package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student extends Person implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String level;

	@Column(name="parent_email")
	private String parentEmail;

	//bi-directional many-to-one association to StudentCourseEnrollment
	@OneToMany(mappedBy="student")
	private List<StudentCourseEnrollment> studentCourseEnrollments;

	public Student() {
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getParentEmail() {
		return this.parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public List<StudentCourseEnrollment> getStudentCourseEnrollments() {
		return this.studentCourseEnrollments;
	}

	public void setStudentCourseEnrollments(List<StudentCourseEnrollment> studentCourseEnrollments) {
		this.studentCourseEnrollments = studentCourseEnrollments;
	}

	public StudentCourseEnrollment addStudentCourseEnrollment(StudentCourseEnrollment studentCourseEnrollment) {
		getStudentCourseEnrollments().add(studentCourseEnrollment);
		studentCourseEnrollment.setStudent(this);

		return studentCourseEnrollment;
	}

	public StudentCourseEnrollment removeStudentCourseEnrollment(StudentCourseEnrollment studentCourseEnrollment) {
		getStudentCourseEnrollments().remove(studentCourseEnrollment);
		studentCourseEnrollment.setStudent(null);

		return studentCourseEnrollment;
	}

}