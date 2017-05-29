package org.openams.rest.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Contact implements Serializable {

	private static final long serialVersionUID = 4914572646662947205L;

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "address_line_1")
	private String addressLine1;

	@Column(name = "address_line_2")
	private String addressLine2;

	private String apartment;

	private String city;

	private String country;

	@Column(name = "e_mail")
	private String eMail;

	@Column(name = "home_phone")
	private String homePhone;

	private String name;

	private String notes;

	private String phone;

	private String state;

	private String street;

	private int zip;

}