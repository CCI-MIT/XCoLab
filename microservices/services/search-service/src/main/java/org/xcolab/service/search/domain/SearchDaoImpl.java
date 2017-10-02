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

import org.xcolab.model.tables.MemberTable;
import org.xcolab.service.search.enums.SearchType;
import org.xcolab.service.search.pojo.SearchPojo;
import org.xcolab.service.utils.PaginationHelper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.xcolab.model.Tables.COMMENT;
import static org.xcolab.model.Tables.CONTEST;
import static org.xcolab.model.Tables.MEMBER;
import static org.xcolab.model.Tables.PROPOSAL_ATTRIBUTE;
import static org.xcolab.service.utils.db.jooq.CustomDsl.match;


@Repository
public class SearchDaoImpl implements SearchDao {

    private final DSLContext dslContext;

    @Autowired
    public SearchDaoImpl(DSLContext dslContext) {this.dslContext = dslContext;}

    @Override
    public List<SearchPojo> findProposalAttribute(PaginationHelper paginationHelper,
                                                  String query) {
        return getProposalQueryForSearch(paginationHelper, query).fetchInto(SearchPojo.class);
    }
    private SelectQuery<Record3<Long, Double, Long>> getProposalQueryForSearch(PaginationHelper paginationHelper, String query){
        final Field<Double> relevance = match(PROPOSAL_ATTRIBUTE.STRING_VALUE).against(query)
                .as("relevance");
        Field<Long> searchTypeId = DSL.val(SearchType.PROPOSAL.getId()).as("searchTypeId");
        return dslContext.select((PROPOSAL_ATTRIBUTE.ID_.as("classPrimaryKey")), relevance, searchTypeId)
                .from(PROPOSAL_ATTRIBUTE)
                .where(match(PROPOSAL_ATTRIBUTE.STRING_VALUE).against(query))
                .groupBy(PROPOSAL_ATTRIBUTE.PROPOSAL_ID)
                .orderBy(relevance.desc())
                .limit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord())
                .getQuery();
    }
    private Integer getTotalForProposalCount(String query){

        Integer ret = dslContext.fetchCount(
                dslContext.selectDistinct(PROPOSAL_ATTRIBUTE.PROPOSAL_ID)
                .from(PROPOSAL_ATTRIBUTE)
                .where(match(PROPOSAL_ATTRIBUTE.STRING_VALUE).against(query)));
        if (ret == null) {
            return 0;
        } else {
            return ret;
        }
    }

    private Collection<? extends Condition> getContestConditions() {
        return Collections.singletonList(CONTEST.CONTEST_PRIVATE.eq(false));
    }

    @Override
    public Integer findProposalAttributeCount(String query) {
        return getTotalForProposalCount(query);
    }

    @Override
    public List<SearchPojo> findMember(PaginationHelper paginationHelper,
                                       String query) {

        return getQueryForSearch(paginationHelper, query, SearchType.MEMBER.getId(), MEMBER, MEMBER.ID_, MemberTable.MEMBER.SHORT_BIO, MEMBER.FIRST_NAME, MEMBER.LAST_NAME, MEMBER.SCREEN_NAME)
                .fetchInto(SearchPojo.class);
    }

    @Override
    public Integer findMemberCount(String query) {
        return getTotalForCount(query, MEMBER, MemberTable.MEMBER.SHORT_BIO, MEMBER.FIRST_NAME, MEMBER.LAST_NAME, MEMBER.SCREEN_NAME);

    }

    @Override
    public List<SearchPojo> findComment(PaginationHelper paginationHelper,
                                        String query) {

        return getQueryForSearch(paginationHelper, query, SearchType.DISCUSSION.getId(), COMMENT, COMMENT.COMMENT_ID, COMMENT.CONTENT)
                .fetchInto(SearchPojo.class);

    }

    @Override
    public Integer findCommentCount(String query) {
        return getTotalForCount(query, COMMENT, COMMENT.CONTENT);
    }

    @Override
    public List<SearchPojo> findContest(PaginationHelper paginationHelper,
                                        String query) {

        return getQueryForSearch(paginationHelper, query, SearchType.CONTEST.getId(), CONTEST, CONTEST.CONTEST_PK, getContestConditions(),CONTEST.CONTEST_DESCRIPTION)
                .fetchInto(SearchPojo.class);

    }

    @Override
    public Integer findContestCount(String query) {
        return getTotalForCount(query, CONTEST, getContestConditions(), CONTEST.CONTEST_DESCRIPTION);
    }

    @Override
    public List<SearchPojo> findAllSite(PaginationHelper paginationHelper,
                                        String query) {
        PaginationHelper unlimitedPagination = new PaginationHelper(0, Integer.MAX_VALUE, "");
        return
                dslContext.select().from(
                                getProposalQueryForSearch(unlimitedPagination, query)
                                .unionAll(getQueryForSearch(unlimitedPagination, query, SearchType.MEMBER.getId(), MEMBER, MEMBER.ID_, MemberTable.MEMBER.SHORT_BIO, MEMBER.FIRST_NAME, MEMBER.LAST_NAME, MEMBER.SCREEN_NAME))
                                .union(getQueryForSearch(unlimitedPagination, query, SearchType.DISCUSSION.getId(), COMMENT, COMMENT.COMMENT_ID, COMMENT.CONTENT))
                                .union(getQueryForSearch(unlimitedPagination, query, SearchType.CONTEST.getId(), CONTEST, CONTEST.CONTEST_PK, getContestConditions(),CONTEST.CONTEST_DESCRIPTION))
                ).limit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord())
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


    private SelectQuery<Record3<Long, Double, Long>> getQueryForSearch(PaginationHelper paginationHelper, String query, Long searchType, TableImpl table, Field primaryKey, Collection<? extends Condition> conditions, Field... fields) {
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


    private SelectQuery<Record3<Long, Double, Long>> getQueryForSearch(PaginationHelper paginationHelper, String query, Long searchType, TableImpl table, Field primaryKey, Field... fields) {
        return getQueryForSearch(paginationHelper, query, searchType, table, primaryKey, Collections.emptyList(), fields);
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

    private SelectQuery<Record1<Integer>> getQueryForCount(String query, TableImpl table, Field... fields) {

        return dslContext.selectCount()
                .from(table)
                .where(match(fields).against(query))
                .getQuery();
    }
}
