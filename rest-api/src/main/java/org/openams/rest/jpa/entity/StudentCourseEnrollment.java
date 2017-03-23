package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "student_course_enrollment")
public class StudentCourseEnrollment implements Serializable {

	private static final long serialVersionUID = -6657553261161406381L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "enrolled_dtt")
	private Date enrolledDtt;

	private String grade;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "graded_dtt")
	private Date gradedDtt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_dtt")
	private Date modifiedDtt;

	private byte status;

	// uni-directional many-to-one association to CourseSchedule
	@ManyToOne
	@JoinColumn(name = "course_schedule_id")
	private CourseSchedule courseSchedule;

	// bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;


}