package org.openams.rest.jpa.envars;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "REVINFO")
@RevisionEntity(AuditListener.class)
public class RevInfo {

	@Id
	@SequenceGenerator(name = "SEQ_GEN", sequenceName = "REVINFO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	@Column(name = "REV")
	@RevisionNumber
	private long id;

	@Column(name = "REVTSTMP")
	@RevisionTimestamp
	private long timestamp;
	
	@Column(name = "USERNAME")
	private String userName;

}
