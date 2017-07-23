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

	@Column(name = "end_dtt")
	private String endDtt;

	@Column(name = "max_score")
	private String maxScore;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_dtt")
	private Date modifiedDtt;

	private String name;

	@Column(name = "start_dtt")
	private String startDtt;

	@Column(name = "test_type")
	private String testType;

	@Column(name = "ref_id")
	private String refId;

	// bi-directional many-to-one association to TestScore
	@OneToMany(mappedBy = "test")
	private List<TestScore> testScores;

}