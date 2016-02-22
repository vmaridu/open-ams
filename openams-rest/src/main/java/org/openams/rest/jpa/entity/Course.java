package org.openams.rest.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the course database table.
 * 
 */
@Entity
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte credits;

	private String dept;

	private String desc;

	private String name;

	@OneToMany(mappedBy="course")
	private List<CourseSchedule> courseSchedules;

	public Course() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getCredits() {
		return this.credits;
	}

	public void setCredits(byte credits) {
		this.credits = credits;
	}

	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CourseSchedule> getCourseSchedules() {
		return this.courseSchedules;
	}

	public void setCourseSchedules(List<CourseSchedule> courseSchedules) {
		this.courseSchedules = courseSchedules;
	}

	public CourseSchedule addCourseSchedule(CourseSchedule courseSchedule) {
		getCourseSchedules().add(courseSchedule);
		courseSchedule.setCourse(this);

		return courseSchedule;
	}

	public CourseSchedule removeCourseSchedule(CourseSchedule courseSchedule) {
		getCourseSchedules().remove(courseSchedule);
		courseSchedule.setCourse(null);

		return courseSchedule;
	}

}