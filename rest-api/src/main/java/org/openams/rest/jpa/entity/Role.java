package org.openams.rest.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Data
public class Role implements Serializable {
	
	private static final long serialVersionUID = -5301575988735395497L;

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;

	private String name;

}