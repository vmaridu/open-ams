package org.openams.rest.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the attendance database table.
 * 
 */
@Entity
@NamedQuery(name="Attendance.findAll", query="SELECT a FROM Attendance a")
public class Attendance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String comment;

	private byte status;

	//bi-directional many-to-one association to AttendanceBy
	@ManyToOne
	@JoinColumn(name="taken_by")
	private AttendanceBy attendanceBy;

	//bi-directional many-to-one association to StudentCourseEnrollment
	@ManyToOne
	@JoinColumn(name="student_course_enrollment_id")
	private StudentCourseEnrollment studentCourseEnrollment;

	public Attendance() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public AttendanceBy getAttendanceBy() {
		return this.attendanceBy;
	}

	public void setAttendanceBy(AttendanceBy attendanceBy) {
		this.attendanceBy = attendanceBy;
	}

	public StudentCourseEnrollment getStudentCourseEnrollment() {
		return this.studentCourseEnrollment;
	}

	public void setStudentCourseEnrollment(StudentCourseEnrollment studentCourseEnrollment) {
		this.studentCourseEnrollment = studentCourseEnrollment;
	}

}