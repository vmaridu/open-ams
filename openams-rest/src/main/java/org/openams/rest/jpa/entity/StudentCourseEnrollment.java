package org.openams.rest.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the student_course_enrollment database table.
 * 
 */
@Entity
@Table(name="student_course_enrollment")
@NamedQuery(name="StudentCourseEnrollment.findAll", query="SELECT s FROM StudentCourseEnrollment s")
public class StudentCourseEnrollment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="enrolled_dtt")
	private Date enrolledDtt;

	private String grade;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="graded_dtt")
	private Date gradedDtt;

	//TODO:List reasonable values and Create ENUM
	private byte status;

	//bi-directional many-to-one association to Attendance
	@OneToMany(mappedBy="studentCourseEnrollment")
	private List<Attendance> attendances;

	//bi-directional many-to-one association to CourseSchedule
	@ManyToOne
	@JoinColumn(name="course_schedule_id")
	private CourseSchedule courseSchedule;

	//bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;

	public StudentCourseEnrollment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEnrolledDtt() {
		return this.enrolledDtt;
	}

	public void setEnrolledDtt(Date enrolledDtt) {
		this.enrolledDtt = enrolledDtt;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getGradedDtt() {
		return this.gradedDtt;
	}

	public void setGradedDtt(Date gradedDtt) {
		this.gradedDtt = gradedDtt;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public List<Attendance> getAttendances() {
		return this.attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public Attendance addAttendance(Attendance attendance) {
		getAttendances().add(attendance);
		attendance.setStudentCourseEnrollment(this);

		return attendance;
	}

	public Attendance removeAttendance(Attendance attendance) {
		getAttendances().remove(attendance);
		attendance.setStudentCourseEnrollment(null);

		return attendance;
	}

	public CourseSchedule getCourseSchedule() {
		return this.courseSchedule;
	}

	public void setCourseSchedule(CourseSchedule courseSchedule) {
		this.courseSchedule = courseSchedule;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}