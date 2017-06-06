package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true, exclude = {"courseSchedules"})
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Staff extends Person implements Serializable {

	private static final long serialVersionUID = 1891001193776887983L;

	@Column(name = "alt_id")
	private String altId;

	private String desc;

	private String designation;

	// bi-directional many-to-one association to CourseSchedule
	@OneToMany(mappedBy = "staff")
	private List<CourseSchedule> courseSchedules;

}