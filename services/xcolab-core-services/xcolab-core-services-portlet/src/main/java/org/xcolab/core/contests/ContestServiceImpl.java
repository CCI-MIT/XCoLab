package org.xcolab.core.contests;

import java.util.List;

import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.DocumentEntityService;
import org.xcolab.core.documententity.impl.BaseDocumentEntityWrapperServiceImpl;

public class ContestServiceImpl extends BaseDocumentEntityWrapperServiceImpl<Contest, ContestImpl> implements ContestService {
	public ContestServiceImpl(DocumentEntityService documentEntityService) {
		super(documentEntityService);
	}

	private final static String CONTESTS_NODES_CONTEXT = "/contests";


	@Override
	protected String getContextName() {
		return CONTESTS_NODES_CONTEXT;
	}

	@Override
	protected Class<Contest> getEntityType() {
		return Contest.class;
	}

	@Override
	protected Class<ContestImpl> getEntityImplType() {
		return ContestImpl.class;
	}
	

}
