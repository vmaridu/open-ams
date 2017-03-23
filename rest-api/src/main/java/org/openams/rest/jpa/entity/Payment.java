package org.openams.rest.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Payment implements Serializable {

	private static final long serialVersionUID = 7556655698165998761L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private float amount;

	private String comment;

	private String name;
	
	//uni-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name = "fee_id")
	private Fee fee;

}