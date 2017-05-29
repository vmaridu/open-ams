package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.sql.Time;
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
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"course", "staff"})
@Entity
@Table(name="course_schedule")
public class CourseSchedule implements Serializable {

	private static final long serialVersionUID = -4883123251674939156L;

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String desc;

	@Temporal(TemporalType.DATE)
	@Column(name="end_dt")
	private Date endDt;

	@Column(name="end_t")
	private Time endT;

	private String location;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_dtt")
	private Date modifiedDtt;

	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="start_dt")
	private Date startDt;

	@Column(name="start_t")
	private Time startT;

	private byte status;

	private String term;

	//uni-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;

	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="instructor_id")
	private Staff staff;


}