package org.openams.rest.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
public class Course implements Serializable {

	private static final long serialVersionUID = -2822793823333715461L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private byte credits;

	private String dept;

	private String desc;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_dtt")
	private Date modifiedDtt;

	private String name;

}