package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the course_schedule database table.
 * 
 */
@Entity
@Table(name="course_schedule")
@NamedQuery(name="CourseSchedule.findAll", query="SELECT c FROM CourseSchedule c")
public class CourseSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String desc;

	@Temporal(TemporalType.DATE)
	@Column(name="end_dt")
	private Date endDt;

	@Column(name="end_t")
	private Time endT;

	private String location;

	@Temporal(TemporalType.DATE)
	@Column(name="start_dt")
	private Date startDt;

	@Column(name="start_t")
	private Time startT;

	//TODO:List reasonable values and Create ENUM
	private byte status;

	private String term;

	//bi-directional many-to-one association to Course
	@ManyToOne
	private Course course;

	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="instructor_id")
	private Staff staff;

	//bi-directional many-to-one association to StudentCourseEnrollment
	@OneToMany(mappedBy="courseSchedule")
	private List<StudentCourseEnrollment> studentCourseEnrollments;

	public CourseSchedule() {
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

	public Date getEndDt() {
		return this.endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public Time getEndT() {
		return this.endT;
	}

	public void setEndT(Time endT) {
		this.endT = endT;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Time getStartT() {
		return this.startT;
	}

	public void setStartT(Time startT) {
		this.startT = startT;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public List<StudentCourseEnrollment> getStudentCourseEnrollments() {
		return this.studentCourseEnrollments;
	}

	public void setStudentCourseEnrollments(List<StudentCourseEnrollment> studentCourseEnrollments) {
		this.studentCourseEnrollments = studentCourseEnrollments;
	}

	public StudentCourseEnrollment addStudentCourseEnrollment(StudentCourseEnrollment studentCourseEnrollment) {
		getStudentCourseEnrollments().add(studentCourseEnrollment);
		studentCourseEnrollment.setCourseSchedule(this);

		return studentCourseEnrollment;
	}

	public StudentCourseEnrollment removeStudentCourseEnrollment(StudentCourseEnrollment studentCourseEnrollment) {
		getStudentCourseEnrollments().remove(studentCourseEnrollment);
		studentCourseEnrollment.setCourseSchedule(null);

		return studentCourseEnrollment;
	}

}