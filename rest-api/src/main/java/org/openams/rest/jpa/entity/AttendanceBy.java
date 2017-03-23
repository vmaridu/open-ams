package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="attendance_by")
public class AttendanceBy implements Serializable {

	private static final long serialVersionUID = 2301979878254953277L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="taken_dtt")
	private Date takenDtt;

	//uni-directional many-to-one association to Attendance
	@OneToMany
	@JoinColumn(name="taken_by", referencedColumnName="taken_by")
	private List<Attendance> attendances;

	//uni-directional many-to-one association to CourseSchedule
	@ManyToOne
	@JoinColumn(name="course_schedule_id")
	private CourseSchedule courseSchedule;

	//uni-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="taken_by")
	private Staff staff;

}