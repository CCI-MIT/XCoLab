package org.xcolab.core.contests;


import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.DocumentEntityWrapper;

public interface Contest extends DocumentEntityWrapper {
	
	String getName();
	String getShortName();
	String getDescription();
	
	void setName(String val);
	void setShortName(String val);
	void setDescription(String val);
	
	ContestPhase[] getPhases() throws DocumentEntityException;
	ContestPhase addPhase() throws DocumentEntityException;
}
