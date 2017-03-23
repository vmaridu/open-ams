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
@Table(name = "school_schedule")
public class SchoolSchedule implements Serializable {

	private static final long serialVersionUID = 8176143175158372620L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_dtt")
	private Date endDtt;

	@Column(name = "event_name")
	private String eventName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_dtt")
	private Date modifiedDtt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_dtt")
	private Date startDtt;

	private byte status;

	// uni-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name = "annonced_by")
	private Staff staff;

}