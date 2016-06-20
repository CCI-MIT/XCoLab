package org.xcolab.service.search.domain;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.service.search.enums.SearchType;
import org.xcolab.service.search.pojo.SearchPojo;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import static org.xcolab.model.Tables.COMMENT;
import static org.xcolab.model.Tables.CONTEST;
import static org.xcolab.model.Tables.MEMBER;
import static org.xcolab.model.Tables.PROPOSAL_ATTRIBUTE;
import static org.xcolab.service.utils.db.jooq.CustomDsl.match;


@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<SearchPojo> findProposalAttribute(PaginationHelper paginationHelper,
                                                  String query) {
        return getQueryForSearch(paginationHelper, query, SearchType.PROPOSAL.getId(), PROPOSAL_ATTRIBUTE, PROPOSAL_ATTRIBUTE.ID_, PROPOSAL_ATTRIBUTE.STRING_VALUE)
                .fetchInto(SearchPojo.class);
    }

    public List<SearchPojo> findMember(PaginationHelper paginationHelper,
                                       String query) {

        return getQueryForSearch(paginationHelper, query, SearchType.MEMBER.getId(), MEMBER, MEMBER.ID_, MEMBER.MEMBER.SHORT_BIO, MEMBER.FIRST_NAME, MEMBER.LAST_NAME, MEMBER.SCREEN_NAME)
                .fetchInto(SearchPojo.class);
    }

    public List<SearchPojo> findComment(PaginationHelper paginationHelper,
                                        String query) {

        return getQueryForSearch(paginationHelper, query, SearchType.DISCUSSION.getId(), COMMENT, COMMENT.COMMENT_ID, COMMENT.CONTENT)
                .fetchInto(SearchPojo.class);

    }

    public List<SearchPojo> findContest(PaginationHelper paginationHelper,
                                        String query) {

        return getQueryForSearch(paginationHelper, query, SearchType.CONTEST.getId(), CONTEST, CONTEST.CONTEST_PK, CONTEST.CONTEST_DESCRIPTION)
                .fetchInto(SearchPojo.class);

    }

    public List<SearchPojo> findAllSite(PaginationHelper paginationHelper,
                                        String query) {
        PaginationHelper unlimitedPagination = new PaginationHelper(0, Integer.MAX_VALUE, "");
        return
                dslContext.select().from(
                        getQueryForSearch(unlimitedPagination, query, SearchType.PROPOSAL.getId(), PROPOSAL_ATTRIBUTE, PROPOSAL_ATTRIBUTE.ID_, PROPOSAL_ATTRIBUTE.STRING_VALUE)
                                .unionAll(getQueryForSearch(unlimitedPagination, query, SearchType.MEMBER.getId(), MEMBER, MEMBER.ID_, MEMBER.MEMBER.SHORT_BIO, MEMBER.FIRST_NAME, MEMBER.LAST_NAME, MEMBER.SCREEN_NAME))
                                .union(getQueryForSearch(unlimitedPagination, query, SearchType.DISCUSSION.getId(), COMMENT, COMMENT.COMMENT_ID, COMMENT.CONTENT))
                                .union(getQueryForSearch(unlimitedPagination, query, SearchType.CONTEST.getId(), CONTEST, CONTEST.CONTEST_PK, CONTEST.CONTEST_DESCRIPTION))
                ).limit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord())
                        .fetchInto(SearchPojo.class);
    }


    private SelectQuery<Record3<Long, Double, Long>> getQueryForSearch(PaginationHelper paginationHelper, String query, Long searchType, TableImpl table, Field primaryKey, Field... fields) {
        final Field<Double> relevance = match(fields).against(query)
                .as("relevance");
        Field<Long> searchTypeId = DSL.val(searchType).as("searchTypeId");
        return dslContext.select(primaryKey.as("classPrimaryKey"), relevance, searchTypeId)
                .from(table)
                .where(match(fields).against(query))
                .orderBy(relevance.desc())
                .limit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord())
                .getQuery();
    }
}
