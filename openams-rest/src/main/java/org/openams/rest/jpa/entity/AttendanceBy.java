package org.openams.rest.jpa.entity;

import java.io.Serializable;
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
 * The persistent class for the attendance_by database table.
 * 
 */
@Entity
@Table(name="attendance_by")
@NamedQuery(name="AttendanceBy.findAll", query="SELECT a FROM AttendanceBy a")
public class AttendanceBy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="taken_dtt")
	private Date takenDtt;

	//bi-directional many-to-one association to Attendance
	@OneToMany(mappedBy="attendanceBy")
	private List<Attendance> attendances;

	//bi-directional many-to-one association to CourseSchedule
	@ManyToOne
	@JoinColumn(name="course_schedule_id")
	private CourseSchedule courseSchedule;

	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="taken_by")
	private Staff staff;

	public AttendanceBy() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTakenDtt() {
		return this.takenDtt;
	}

	public void setTakenDtt(Date takenDtt) {
		this.takenDtt = takenDtt;
	}

	public List<Attendance> getAttendances() {
		return this.attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public Attendance addAttendance(Attendance attendance) {
		getAttendances().add(attendance);
		attendance.setAttendanceBy(this);

		return attendance;
	}

	public Attendance removeAttendance(Attendance attendance) {
		getAttendances().remove(attendance);
		attendance.setAttendanceBy(null);

		return attendance;
	}

	public CourseSchedule getCourseSchedule() {
		return this.courseSchedule;
	}

	public void setCourseSchedule(CourseSchedule courseSchedule) {
		this.courseSchedule = courseSchedule;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}