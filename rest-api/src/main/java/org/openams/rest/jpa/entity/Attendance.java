package org.openams.rest.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.openams.rest.jpa.entity.enums.AttendanceStatus;

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
	
	//bi-directional many-to-one association to 
	@ManyToOne
	@JoinColumn(name="ATTENDANCE_BY_ID")
	private AttendanceBy attendanceBy;

	@Enumerated(EnumType.ORDINAL)
	private AttendanceStatus status;

	//uni-directional many-to-one association to StudentCourseEnrollment
	@ManyToOne
	@JoinColumn(name="student_course_enrollment_id")
	private StudentCourseEnrollment studentCourseEnrollment;

}