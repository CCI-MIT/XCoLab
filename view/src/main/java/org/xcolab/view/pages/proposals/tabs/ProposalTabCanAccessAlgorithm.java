package org.xcolab.view.pages.proposals.tabs;

import org.xcolab.view.pages.proposals.utils.context.ProposalsContextWrapper;

public interface ProposalTabCanAccessAlgorithm {

	boolean canAccess(ProposalsContextWrapper contextWrapper);

}