package org.xcolab.core.proposals;

import org.xcolab.core.documententity.DocumentEntityService;
import org.xcolab.core.documententity.impl.BaseDocumentEntityWrapperServiceImpl;

public class ProposalsServiceImpl extends BaseDocumentEntityWrapperServiceImpl<Proposal, ProposalImpl> implements ProposalsService {
	private final static String PROPOSALS_NODES_CONTEXT = "/proposals";
	
	public ProposalsServiceImpl(DocumentEntityService documentEntityService) {
		super(documentEntityService);
	}

	@Override
	protected String getContextName() {
		return PROPOSALS_NODES_CONTEXT;
	}

	@Override
	protected Class<Proposal> getEntityType() {
		return Proposal.class;
	}

	@Override
	protected Class<ProposalImpl> getEntityImplType() {
		return ProposalImpl.class;
	}

}
