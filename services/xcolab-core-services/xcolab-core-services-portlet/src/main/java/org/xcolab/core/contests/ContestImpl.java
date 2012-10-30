package org.xcolab.core.contests;

import java.util.List;

import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.impl.BaseDocumentEntityWrapper;

public class ContestImpl extends BaseDocumentEntityWrapper implements Contest {
	public final static String NAME = "name";
	public final static String SHORT_NAME = "shortName";
	public final static String DESCRIPTION = "description";
	public final static String PHASES = "phases";

	public ContestImpl(DocumentEntity entity) {
		super(entity);
	}

	public String getName() {
		return wrapped.getString(NAME);
	}

	public String getShortName() {
		return wrapped.getString(SHORT_NAME);
	}

	public String getDescription() {
		return wrapped.getString(DESCRIPTION);
	}

	public ContestPhase[] getPhases() throws DocumentEntityException {
		return super.getChildren(PHASES, ContestPhase.class, ContestPhaseImpl.class);
	}

	public ContestPhase addPhase() throws DocumentEntityException {
		return super.addEntity(PHASES, ContestPhaseImpl.class);
	}

	public void setName(String val) {
		wrapped.setString(NAME, val);
		
	}

	public void setShortName(String val) {
		wrapped.setString(SHORT_NAME, val);
		
	}

	public void setDescription(String val) {
		wrapped.setString(DESCRIPTION, val);
		
	}
	

}
