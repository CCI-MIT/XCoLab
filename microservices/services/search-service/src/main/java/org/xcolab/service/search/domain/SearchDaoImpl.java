package org.xcolab.service.search.domain;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.search.pojo.ISearchPojo;
import org.xcolab.client.search.pojo.tables.pojos.SearchPojo;
import org.xcolab.model.tables.UserTable;
import org.xcolab.service.search.enums.SearchType;
import org.xcolab.service.utils.PaginationHelper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.xcolab.model.Tables.COMMENT;
import static org.xcolab.model.Tables.CONTEST;
import static org.xcolab.model.Tables.PROPOSAL_ATTRIBUTE;
import static org.xcolab.model.Tables.USER;
import static org.xcolab.service.utils.db.jooq.CustomDsl.match;

@Repository
public class SearchDaoImpl implements SearchDao {

    private final DSLContext dslContext;

    @Autowired
    public SearchDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<ISearchPojo> findProposalAttribute(PaginationHelper paginationHelper,
                                                  String query) {
        return getProposalQueryForSearch(paginationHelper, query).fetchInto(SearchPojo.class);
    }
    private SelectQuery<Record3<Long, Double, Long>> getProposalQueryForSearch(
            PaginationHelper paginationHelper, String query) {
        final Field<Double> relevance = match(PROPOSAL_ATTRIBUTE.STRING_VALUE).against(query)
                .as("relevance");
        Field<Long> searchTypeId = DSL.val(SearchType.PROPOSAL.getId()).as("searchTypeId");
        return dslContext.select(PROPOSAL_ATTRIBUTE.ID.as("classPrimaryKey"), relevance, searchTypeId)
                .from(PROPOSAL_ATTRIBUTE)
                .where(match(PROPOSAL_ATTRIBUTE.STRING_VALUE).against(query))
                .groupBy(PROPOSAL_ATTRIBUTE.PROPOSAL_ID)
                .orderBy(relevance.desc())
                .limit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord())
                .getQuery();
    }
    private Integer getTotalForProposalCount(String query){
        return dslContext.fetchCount(
                dslContext.selectDistinct(PROPOSAL_ATTRIBUTE.PROPOSAL_ID)
                .from(PROPOSAL_ATTRIBUTE)
                .where(match(PROPOSAL_ATTRIBUTE.STRING_VALUE).against(query)));
    }

    private Collection<? extends Condition> getContestConditions() {
        return Collections.singletonList(CONTEST.CONTEST_PRIVATE.eq(false));
    }

    @Override
    public Integer findProposalAttributeCount(String query) {
        return getTotalForProposalCount(query);
    }

    @Override
    public List<ISearchPojo> findMember(PaginationHelper paginationHelper, String query) {
        return getQueryForSearch(paginationHelper, query, SearchType.USER.getId(), USER,
                USER.ID, UserTable.USER.SHORT_BIO, USER.FIRST_NAME, USER.LAST_NAME,
                USER.SCREEN_NAME)
                .fetchInto(SearchPojo.class);
    }

    @Override
    public Integer findMemberCount(String query) {
        return getTotalForCount(query, USER, UserTable.USER.SHORT_BIO, USER.FIRST_NAME,
                USER.LAST_NAME, USER.SCREEN_NAME);
    }

    @Override
    public List<ISearchPojo> findComment(PaginationHelper paginationHelper, String query) {
        return getQueryForSearch(paginationHelper, query, SearchType.DISCUSSION.getId(), COMMENT,
                COMMENT.ID, COMMENT.CONTENT)
                .fetchInto(SearchPojo.class);

    }

    @Override
    public Integer findCommentCount(String query) {
        return getTotalForCount(query, COMMENT, COMMENT.CONTENT);
    }

    @Override
    public List<ISearchPojo> findContest(PaginationHelper paginationHelper, String query) {
        return getQueryForSearch(paginationHelper, query, SearchType.CONTEST.getId(), CONTEST,
                CONTEST.ID, getContestConditions(),CONTEST.DESCRIPTION)
                .fetchInto(SearchPojo.class);
    }

    @Override
    public Integer findContestCount(String query) {
        return getTotalForCount(query, CONTEST, getContestConditions(), CONTEST.DESCRIPTION);
    }

    @Override
    public List<ISearchPojo> findAllSite(PaginationHelper paginationHelper, String query) {
        PaginationHelper unlimitedPagination = new PaginationHelper(0, Integer.MAX_VALUE, "");
        return dslContext.select()
                .from(getProposalQueryForSearch(unlimitedPagination, query)
                        .unionAll(getQueryForSearch(unlimitedPagination, query,
                                SearchType.USER.getId(), USER, USER.ID,
                                UserTable.USER.SHORT_BIO, USER.FIRST_NAME, USER.LAST_NAME,
                                USER.SCREEN_NAME))
                        .union(getQueryForSearch(unlimitedPagination, query,
                                SearchType.DISCUSSION.getId(), COMMENT, COMMENT.ID,
                                COMMENT.CONTENT))
                        .union(getQueryForSearch(unlimitedPagination, query,
                                SearchType.CONTEST.getId(), CONTEST, CONTEST.ID,
                                getContestConditions(), CONTEST.DESCRIPTION))
                )
                .limit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord())
                .fetchInto(SearchPojo.class);
    }

    @Override
    public Integer findAllSiteCount(String query) {
        Integer total = 0;
        total += findCommentCount(query);
        total += findProposalAttributeCount(query);
        total += findContestCount(query);
        total += findMemberCount(query);
        return total;
    }


    private SelectQuery<Record3<Long, Double, Long>> getQueryForSearch(
            PaginationHelper paginationHelper, String query, Long searchType, TableImpl table,
            Field primaryKey, Collection<? extends Condition> conditions, Field... fields) {
        final Field<Double> relevance = match(fields).against(query)
                .as("relevance");
        Field<Long> searchTypeId = DSL.val(searchType).as("searchTypeId");
        return dslContext.select((primaryKey.as("classPrimaryKey")), relevance, searchTypeId)
                .from(table)
                .where(conditions)
                .and(match(fields).against(query))
                .orderBy(relevance.desc())
                .limit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord())
                .getQuery();
    }


    private SelectQuery<Record3<Long, Double, Long>> getQueryForSearch(
            PaginationHelper paginationHelper, String query, Long searchType, TableImpl table,
            Field primaryKey, Field... fields) {
        return getQueryForSearch(paginationHelper, query, searchType, table, primaryKey,
                Collections.emptyList(), fields);
    }


    private Integer getTotalForCount(String query, TableImpl table,
            Collection<? extends Condition> conditions, Field... fields) {
        SelectQuery<Record1<Integer>> rec = getQueryForCount(query, table, fields);
        rec.addConditions(conditions);
        Integer ret = rec.fetchOne().into(Integer.class);
        if (ret == null) {
            return 0;
        } else {
            return ret;
        }
    }

    private Integer getTotalForCount(String query, TableImpl table, Field... fields) {
        return getTotalForCount(query, table, Collections.emptyList(), fields);
    }

    private SelectQuery<Record1<Integer>> getQueryForCount(String query, TableImpl table,
            Field... fields) {
        return dslContext.selectCount()
                .from(table)
                .where(match(fields).against(query))
                .getQuery();
    }
}
