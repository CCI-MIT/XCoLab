package org.xcolab.core.proposals;

import java.util.List;

import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.impl.BaseDocumentEntityWrapper;
import org.xcolab.core.proposals.template.ProposalTemplate;

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
}
