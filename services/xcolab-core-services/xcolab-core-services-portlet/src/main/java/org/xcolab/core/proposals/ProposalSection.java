package org.xcolab.core.proposals;

import org.xcolab.core.documententity.DocumentEntityWrapper;
import org.xcolab.core.ontology.HasOntologyEntries;
import org.xcolab.core.proposals.template.ProposalTemplateSection;

/**
 * User: jintrone
 * Date: 11/14/12
 * Time: 10:19 PM
 */
public interface ProposalSection extends DocumentEntityWrapper, HasOntologyEntries {

    public ProposalTemplateSection getTemplateSection();

    public void setTemplateSection(ProposalTemplateSection section);


}
