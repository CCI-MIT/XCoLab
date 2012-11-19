package org.xcolab.core.proposals;

import org.xcolab.core.documententity.DocumentEntityWrapper;
import org.xcolab.core.ontology.HasOntologyEntries;
import org.xcolab.core.proposals.template.ProposalTemplate;
import org.xcolab.core.proposals.template.ProposalTemplateSection;

import java.util.List;

public interface Proposal extends DocumentEntityWrapper, HasOntologyEntries {

    public ProposalTemplate getTemplate();

    public void setTemplate(ProposalTemplate template);

    public List<ProposalSection> getSections();

    public ProposalSection getSectionFor(ProposalTemplateSection templateSection);



    public List<String> getSupporters();

    public void addSupporter(String userId);

    public String removeSupporter(String userId);




}
