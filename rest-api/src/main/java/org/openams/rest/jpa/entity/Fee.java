package org.openams.rest.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"student"})
@Entity
public class Fee implements Serializable {

	private static final long serialVersionUID = -7816811617606757427L;

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name="academic_term")
	private String academicTerm;

	private float amount;

	private String comment;

	private String name;

	//uni-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

}