package org.xcolab.core.proposals;

import java.util.List;

import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.impl.BaseDocumentEntityWrapper;

public class ProposalImpl extends BaseDocumentEntityWrapper implements Proposal {

	public ProposalImpl(DocumentEntity entity) {
		super(entity);
	}
	
	public String toString() {
		return "ProposalImpl [" + wrapped + "]";
	}


}
