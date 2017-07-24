package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"testScores"})
@Entity
public class Test implements Serializable {

	private static final long serialVersionUID = -2136711396208229838L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String desc;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_dtt")
	private Date endDtt;

	@Column(name = "max_score")
	private Float maxScore;
	
	@Column(name = "max_grade")
	private String maxGrade;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_dtt")
	private Date modifiedDtt;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_dtt")
	private Date startDtt;

	@Column(name = "test_type")
	private String testType;

	@Column(name = "ref_id")
	private String refId;

	// bi-directional many-to-one association to TestScore
	@OneToMany(mappedBy = "test")
	private List<TestScore> testScores;

}