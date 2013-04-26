package org.xcolab.core.contests;


import org.xcolab.core.xcolabententity.XColabEntityException;
import org.xcolab.core.xcolabententity.XColabEntityWrapper;

public interface Contest extends XColabEntityWrapper {
	
	String getName();
	String getShortName();
	String getDescription();
	
	void setName(String val);
	void setShortName(String val);
	void setDescription(String val);
	
	ContestPhase[] getPhases() throws XColabEntityException;
	ContestPhase addPhase() throws XColabEntityException;

    boolean isArchived();
    void setArchived(boolean b);

    ContestPhase getActivePhase();


}
