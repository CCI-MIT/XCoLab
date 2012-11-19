package org.xcolab.core.contests;


import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.DocumentEntityWrapper;
import org.xcolab.core.ontology.HasOntologyEntries;
import org.xcolab.core.proposals.template.ProposalTemplate;

public interface Contest extends DocumentEntityWrapper, HasOntologyEntries {
	
	String getName();
	String getShortName();
	String getDescription();
	
	void setName(String val);
	void setShortName(String val);
	void setDescription(String val);
	
	ContestPhase[] getPhases() throws DocumentEntityException;
	ContestPhase addPhase() throws DocumentEntityException;

    boolean isArchived();
    void setArchived(boolean b);

    ContestPhase getActivePhase();

    public void setTemplate(ProposalTemplate template);
    public ProposalTemplate getTemplate();

}
