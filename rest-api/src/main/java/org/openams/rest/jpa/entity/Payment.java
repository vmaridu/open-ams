package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"fee"})
@Entity
@Audited
public class Payment implements Serializable {

	private static final long serialVersionUID = 7556655698165998761L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private float amount;

	private String comment;

	private String name;
	
	@ManyToOne
	@JoinColumn(name = "fee_id")
	private Fee fee;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_dtt")
	private Date modifiedDtt;

}