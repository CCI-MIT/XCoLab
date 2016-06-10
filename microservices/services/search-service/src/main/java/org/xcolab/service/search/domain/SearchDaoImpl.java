package org.xcolab.service.search.domain;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.CustomField;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Comment;
import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.service.search.enums.SearchType;
import org.xcolab.service.search.pojo.SearchPojo;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import static org.xcolab.model.Tables.COMMENT;
import static org.xcolab.model.Tables.CONTEST;
import static org.xcolab.model.Tables.MEMBER;
import static org.xcolab.model.Tables.PROPOSAL_ATTRIBUTE;
import static org.xcolab.service.utils.db.jooq.CustomDsl.match;
import static org.jooq.impl.DSL.*;


@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<SearchPojo> findProposalAttribute(PaginationHelper paginationHelper,
            String query) {
        final Field<Double> relevance = match(PROPOSAL_ATTRIBUTE.STRING_VALUE).against(query)
                .as("relevance");
        Field<Long> searchTypeId = DSL.val(SearchType.PROPOSOSAL.getId()).as("searchTypeId");
        return dslContext.select(PROPOSAL_ATTRIBUTE.ID_.as("classPrimaryKey"), relevance, searchTypeId)
                .from(PROPOSAL_ATTRIBUTE)
                .where(match(PROPOSAL_ATTRIBUTE.STRING_VALUE).against(query))
                .orderBy(relevance.desc())
                .limit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord())
                .fetchInto(SearchPojo.class);
    }

    public List<SearchPojo> findMember(PaginationHelper paginationHelper,
                                                         String query) {
        final Field<Double> relevance = match(MEMBER.SHORT_BIO).against(query)
                .as("relevance");
        Field<Long> searchTypeId = DSL.val(SearchType.MEMBER.getId()).as("searchTypeId");
        return dslContext.select(MEMBER.ID_.as("classPrimaryKey"), relevance, searchTypeId)
                .from(MEMBER)
                .where(match(MEMBER.SHORT_BIO).against(query))
                .orderBy(relevance.desc())
                .limit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord())
                .fetchInto(SearchPojo.class);
    }

    public List<SearchPojo> findComment(PaginationHelper paginationHelper,
                                        String query) {
        final Field<Double> relevance = match(COMMENT.CONTENT).against(query)
                .as("relevance");

        Field<Long> searchTypeId = DSL.val(SearchType.DISCUSSION.getId()).as("searchTypeId");
        return dslContext.select(COMMENT.CONTENT, relevance, searchTypeId)
                .from(COMMENT)
                .where(match(COMMENT.CONTENT).against(query))
                .orderBy(relevance.desc())
                .limit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord())
                .fetchInto(SearchPojo.class);
    }

    public List<SearchPojo> findContest(PaginationHelper paginationHelper,
                                     String query) {
        final Field<Double> relevance = match(CONTEST.CONTEST_DESCRIPTION).against(query)
                .as("relevance");
        Field<Long> searchTypeId = DSL.val(SearchType.CONTEST.getId()).as("searchTypeId");
        return dslContext.select(CONTEST.CONTEST_DESCRIPTION, relevance, searchTypeId)
                .from(CONTEST)
                .where(match(CONTEST.CONTEST_DESCRIPTION).against(query))
                .orderBy(relevance.desc())
                .limit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord())
                .fetchInto(SearchPojo.class);
    }
}
