package org.xcolab.core.proposals;

import org.xcolab.core.documententity.DocumentEntityWrapper;
import org.xcolab.core.proposals.template.ProposalTemplate;

public interface Proposal extends DocumentEntityWrapper {

    public ProposalTemplate getTemplate();

    public void setTemplate(ProposalTemplate template);

}
