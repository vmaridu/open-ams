package org.openams.rest.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"studentCourseEnrollment"})
@Entity
public class Attendance implements Serializable {

	private static final long serialVersionUID = 6151665184375406098L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String comment;

	private byte status;

	//bi-directional many-to-one association to StudentCourseEnrollment
	@ManyToOne
	@JoinColumn(name="student_course_enrollment_id")
	private StudentCourseEnrollment studentCourseEnrollment;

}