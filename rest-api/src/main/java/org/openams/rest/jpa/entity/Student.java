package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Student extends Person implements Serializable {

	private static final long serialVersionUID = -5890884363598314953L;

	private String level;

	@Column(name = "parent_email")
	private String parentEmail;

	@Column(name = "roll_number")
	private String rollNumber;

	// bi-directional many-to-one association to StudentCourseEnrollment
	@OneToMany(mappedBy = "student")
	private List<StudentCourseEnrollment> studentCourseEnrollments;

}