package org.xcolab.service.search.domain;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_ATTRIBUTE;
import static org.xcolab.service.utils.db.jooq.CustomDsl.match;

@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<ProposalAttribute> findProposalAttribute(PaginationHelper paginationHelper,
            String query) {
        final Field<Double> relevance = match(PROPOSAL_ATTRIBUTE.STRING_VALUE).against(query)
                .as("relevance");
        return dslContext.select(PROPOSAL_ATTRIBUTE.STRING_VALUE, relevance)
                .from(PROPOSAL_ATTRIBUTE)
                .where(match(PROPOSAL_ATTRIBUTE.STRING_VALUE).against(query))
                .orderBy(relevance.desc())
                .limit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord())
                .fetchInto(ProposalAttribute.class);
    }
}
