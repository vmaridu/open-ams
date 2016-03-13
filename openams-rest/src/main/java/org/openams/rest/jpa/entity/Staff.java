package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;


/**
 * The persistent class for the staff database table.
 * 
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
@NamedQuery(name="Staff.findAll", query="SELECT s FROM Staff s")
public class Staff extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String desc;

	private String designation;

	//bi-directional many-to-one association to CourseSchedule
	@OneToMany(mappedBy="staff")
	private List<CourseSchedule> courseSchedules;

	public Staff() {
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List<CourseSchedule> getCourseSchedules() {
		return this.courseSchedules;
	}

	public void setCourseSchedules(List<CourseSchedule> courseSchedules) {
		this.courseSchedules = courseSchedules;
	}

	public CourseSchedule addCourseSchedule(CourseSchedule courseSchedule) {
		getCourseSchedules().add(courseSchedule);
		courseSchedule.setStaff(this);

		return courseSchedule;
	}

	public CourseSchedule removeCourseSchedule(CourseSchedule courseSchedule) {
		getCourseSchedules().remove(courseSchedule);
		courseSchedule.setStaff(null);

		return courseSchedule;
	}
}