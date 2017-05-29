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
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"person", "test"})
@Entity
@Table(name="test_score")
public class TestScore implements Serializable {

	private static final long serialVersionUID = -6728754879564218921L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_dtt")
	private Date endDtt;

	private String grade;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_dtt")
	private Date modifiedDtt;

	private String notes;

	private int score;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_dtt")
	private Date startDtt;

	//uni-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	//bi-directional many-to-one association to Test
	@ManyToOne
	private Test test;

}