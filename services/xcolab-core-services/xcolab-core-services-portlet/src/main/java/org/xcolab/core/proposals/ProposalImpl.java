package org.xcolab.core.proposals;

import java.util.List;

import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.impl.BaseDocumentEntityWrapper;
import org.xcolab.core.ontology.OntologyDimension;
import org.xcolab.core.ontology.OntologyEntry;
import org.xcolab.core.proposals.template.ProposalTemplate;
import org.xcolab.core.proposals.template.ProposalTemplateSection;

public class ProposalImpl extends BaseDocumentEntityWrapper implements Proposal {

	public ProposalImpl(DocumentEntity entity) {
		super(entity);
	}
	
	public String toString() {
		return "ProposalImpl [" + wrapped + "]";
	}


    public ProposalTemplate getTemplate() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setTemplate(ProposalTemplate template) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<ProposalSection> getSections() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ProposalSection getSectionFor(ProposalTemplateSection templateSection) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<String> getSupporters() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addSupporter(String userId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String removeSupporter(String userId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addOntologyEntry(OntologyEntry entry) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<OntologyEntry> getOntologyEntries() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<OntologyEntry> getOntologyEntries(OntologyDimension dimension) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
