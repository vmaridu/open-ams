package org.openams.rest.jpa.envars;

import org.hibernate.envers.RevisionListener;
import org.openams.rest.utils.AuthUtil;

public class AuditListener implements RevisionListener{

	@Override
	public void newRevision(Object revisionEntity) {
		RevInfo customRevisionEntity = (RevInfo) revisionEntity;
		customRevisionEntity.setUserName(AuthUtil.getPrincipal().orElse(null));
	}

}
