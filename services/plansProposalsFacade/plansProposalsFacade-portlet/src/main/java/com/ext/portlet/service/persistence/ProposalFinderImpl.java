package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Proposal;
import com.ext.utils.CustomSqlUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

/**
 * Created by johannes on 12/11/15.
 */
public class ProposalFinderImpl extends BasePersistenceImpl<Proposal> implements ProposalFinder {

    public static final String GET_PROPOSAL_MATERIALIZED_POINTS = ".getProposalMaterializedPoints";
    public static final String GET_PROPOSAL_HYPOTHETICAL_POINTS = ".getProposalHypotheticalPoints";

    @Override
    public int getProposalMaterializedPoints(final long proposalId) {
        final String queryIdentifier = ProposalFinder.class.getName() + GET_PROPOSAL_MATERIALIZED_POINTS;
        return CustomSqlUtil.getIntFromQuery(queryIdentifier, getDialect(),
                new CustomSqlUtil.QueryInitializer(false) {
            @Override
            public void fillFilters(QueryPos queryPos) {
                queryPos.add(Long.toString(proposalId));
            }
        });
    }

    @Override
    public int getProposalHypotheticalPoints(final long proposalId) {
        final String queryIdentifier = ProposalFinder.class.getName() + GET_PROPOSAL_HYPOTHETICAL_POINTS;
        return CustomSqlUtil.getIntFromQuery(queryIdentifier, getDialect(),
                new CustomSqlUtil.QueryInitializer(false) {
            @Override
            public void fillFilters(QueryPos queryPos) {
                queryPos.add(Long.toString(proposalId));
            }
        });
    }
}
