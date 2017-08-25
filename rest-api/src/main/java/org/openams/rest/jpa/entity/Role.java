package org.openams.rest.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Audited
public class Role implements Serializable {

	private static final long serialVersionUID = -5301575988735395497L;

	public Role() {}
	
	public Role(String id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String name;
	
}