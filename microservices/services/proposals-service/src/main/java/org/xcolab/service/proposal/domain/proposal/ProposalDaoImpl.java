package org.xcolab.service.proposal.domain.proposal;

import org.jooq.DSLContext;
import org.jooq.JoinType;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.ProposalContestPhaseAttributeTable;
import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.records.ProposalRecord;
import org.xcolab.service.proposal.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST;
import static org.xcolab.model.Tables.CONTEST_PHASE;
import static org.xcolab.model.Tables.CONTEST_PHASE_RIBBON_TYPE;
import static org.xcolab.model.Tables.POINTS;
import static org.xcolab.model.Tables.PROPOSAL;
import static org.xcolab.model.Tables.PROPOSAL_2_PHASE;
import static org.xcolab.model.Tables.PROPOSAL_ATTRIBUTE;
import static org.xcolab.model.Tables.PROPOSAL_CONTEST_PHASE_ATTRIBUTE;

import static org.jooq.impl.DSL.sum;

@Repository
public class ProposalDaoImpl implements ProposalDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext);
        this.dslContext = dslContext;
    }

    @Override
    public List<Proposal> findLinkedProposalIdsByGivenProposalId(Long proposalId) {
        final SelectQuery<Record> query = dslContext.selectDistinct(PROPOSAL.fields())
                .from(PROPOSAL)
                .getQuery();

        query.addJoin(POINTS,PROPOSAL.PROPOSAL_ID.eq( POINTS.ORIGINATING_PROPOSAL_ID));
        query.addConditions(POINTS.ORIGINATING_PROPOSAL_ID.notEqual(POINTS.PROPOSAL_ID));
        query.addConditions(POINTS.PROPOSAL_ID.eq(proposalId));
        return query.fetchInto(Proposal.class);
    }

    @Override
    public List<Proposal> findByGiven(PaginationHelper paginationHelper, String filterText,
            List<Long> contestIds, Boolean visible, Long contestPhaseId, Integer ribbon,
            List<Long> contestTypeIds, List<Long> contestTierIds) {
        final SelectQuery<Record> query = dslContext.selectDistinct(PROPOSAL.fields())
                .from(PROPOSAL)
                .getQuery();

        if (contestIds != null || contestTypeIds != null || contestTierIds != null
                || contestPhaseId != null || ribbon != null || (visible != null && visible)) {
            //TODO: these joins causes duplicate entries
            query.addJoin(PROPOSAL_2_PHASE, PROPOSAL.PROPOSAL_ID.eq(PROPOSAL_2_PHASE.PROPOSAL_ID));
            query.addJoin(CONTEST_PHASE,
                    CONTEST_PHASE.CONTEST_PHASE_PK.eq(PROPOSAL_2_PHASE.CONTEST_PHASE_ID));
        }

        if (contestTypeIds != null || contestTierIds != null) {
            query.addJoin(CONTEST, CONTEST.CONTEST_PK.eq(CONTEST_PHASE.CONTEST_PK));
        }

        if (ribbon != null) {
            final ProposalContestPhaseAttributeTable ribbonAttribute =
                    PROPOSAL_CONTEST_PHASE_ATTRIBUTE.as("ribbonAttribute");
            query.addJoin(ribbonAttribute,
                    ribbonAttribute.PROPOSAL_ID.eq(PROPOSAL.PROPOSAL_ID)
                            .and(ribbonAttribute.CONTEST_PHASE_ID
                                    .eq(CONTEST_PHASE.CONTEST_PHASE_PK)));
            query.addJoin(CONTEST_PHASE_RIBBON_TYPE,
                    ribbonAttribute.NAME.eq(
                            ProposalContestPhaseAttributeKeys.RIBBON)
                            .and(CONTEST_PHASE_RIBBON_TYPE.ID_
                                    .eq(ribbonAttribute.NUMERIC_VALUE)));
        }

        if (contestPhaseId != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_PHASE_PK.eq(contestPhaseId));
        }

        if(filterText != null) {
            //TODO: replace wild carded search with match...against
            query.addJoin(PROPOSAL_ATTRIBUTE, JoinType.JOIN, PROPOSAL.PROPOSAL_ID.eq(PROPOSAL_ATTRIBUTE.PROPOSAL_ID)
                    .and(PROPOSAL_ATTRIBUTE.STRING_VALUE.like("%" + filterText + "%"))
                        .and(PROPOSAL_ATTRIBUTE.NAME.eq("NAME")));
        }

        if (visible != null) {
            query.addConditions(PROPOSAL.VISIBLE.eq(visible));
            if (visible) {
                isVisibleInCurrentPhase(query);
            }
        }

        if (contestIds != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_PK.in(contestIds));
        }
        if (contestTypeIds != null) {
            query.addConditions(CONTEST.CONTEST_TYPE_ID.in(contestTypeIds));
        }
        if (contestTierIds != null) {
            query.addConditions(CONTEST.CONTEST_TIER.in(contestTierIds));
        }

        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Proposal.class);
    }

    private void isVisibleInCurrentPhase(SelectQuery<?> query) {
        final ProposalContestPhaseAttributeTable visibleAttribute =
                PROPOSAL_CONTEST_PHASE_ATTRIBUTE.as("visibleAttribute");
        query.addJoin(visibleAttribute, JoinType.LEFT_OUTER_JOIN,
                visibleAttribute.PROPOSAL_ID.eq(PROPOSAL.PROPOSAL_ID)
                        .and(visibleAttribute.CONTEST_PHASE_ID
                                .eq(CONTEST_PHASE.CONTEST_PHASE_PK))
                        .and(visibleAttribute.NAME
                                .eq(ProposalContestPhaseAttributeKeys.VISIBLE))
                        .and(visibleAttribute.NUMERIC_VALUE.eq(0L)));
        query.addConditions(visibleAttribute.ID_.isNull());
    }

    @Override
    public List<Long> findIdsByGiven(PaginationHelper paginationHelper, Long contestId,
            Boolean visible, Long contestPhaseId, Integer ribbon) {
        final SelectQuery<Record1<Long>> query = dslContext.select(PROPOSAL.PROPOSAL_ID)
                .from(PROPOSAL)
                .getQuery();

        if (contestId != null || contestPhaseId != null || ribbon != null || (visible != null && visible)) {
            query.addJoin(PROPOSAL_2_PHASE, PROPOSAL.PROPOSAL_ID.eq(PROPOSAL_2_PHASE.PROPOSAL_ID));
            query.addJoin(CONTEST_PHASE,
                    CONTEST_PHASE.CONTEST_PHASE_PK.eq(PROPOSAL_2_PHASE.CONTEST_PHASE_ID));
        }


        if (ribbon != null) {
            final ProposalContestPhaseAttributeTable ribbonAttribute =
                    PROPOSAL_CONTEST_PHASE_ATTRIBUTE.as("ribbonAttribute");
            query.addJoin(ribbonAttribute,
                    ribbonAttribute.PROPOSAL_ID.eq(PROPOSAL.PROPOSAL_ID)
                            .and(ribbonAttribute.CONTEST_PHASE_ID
                                    .eq(CONTEST_PHASE.CONTEST_PHASE_PK)));
            query.addJoin(CONTEST_PHASE_RIBBON_TYPE,
                    ribbonAttribute.NAME.eq(
                            ProposalContestPhaseAttributeKeys.RIBBON)
                            .and(CONTEST_PHASE_RIBBON_TYPE.ID_
                                    .eq(ribbonAttribute.NUMERIC_VALUE)));
        }

        if (contestPhaseId != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_PHASE_PK.eq(contestPhaseId));
        }

        if (visible != null) {
            query.addConditions(PROPOSAL.VISIBLE.eq(visible));
            if (visible) {
                isVisibleInCurrentPhase(query);
            }
        }

        if (contestId != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_PK.eq(contestId));
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Long.class);
    }

    @Override
    public List<Long> findThreadIdsByGiven(PaginationHelper paginationHelper, Long contestId,
            Boolean visible, Long contestPhaseId, Integer ribbon) {
        final SelectQuery<Record1<Long>> query = dslContext.select(PROPOSAL.DISCUSSION_ID)
                .from(PROPOSAL)
                .getQuery();

        if (contestId != null || contestPhaseId != null || ribbon != null || (visible != null && visible)) {
            query.addJoin(PROPOSAL_2_PHASE, PROPOSAL.PROPOSAL_ID.eq(PROPOSAL_2_PHASE.PROPOSAL_ID));
            query.addJoin(CONTEST_PHASE,
                    CONTEST_PHASE.CONTEST_PHASE_PK.eq(PROPOSAL_2_PHASE.CONTEST_PHASE_ID));
        }


        if (ribbon != null) {
            final ProposalContestPhaseAttributeTable ribbonAttribute =
                    PROPOSAL_CONTEST_PHASE_ATTRIBUTE.as("ribbonAttribute");
            query.addJoin(ribbonAttribute,
                    ribbonAttribute.PROPOSAL_ID.eq(PROPOSAL.PROPOSAL_ID)
                            .and(ribbonAttribute.CONTEST_PHASE_ID
                                    .eq(CONTEST_PHASE.CONTEST_PHASE_PK)));
            query.addJoin(CONTEST_PHASE_RIBBON_TYPE,
                    ribbonAttribute.NAME.eq(
                            ProposalContestPhaseAttributeKeys.RIBBON)
                            .and(CONTEST_PHASE_RIBBON_TYPE.ID_
                                    .eq(ribbonAttribute.NUMERIC_VALUE)));
        }

        if (contestPhaseId != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_PHASE_PK.eq(contestPhaseId));
        }

        if (visible != null) {
            query.addConditions(PROPOSAL.VISIBLE.eq(visible));
            if (visible) {
                isVisibleInCurrentPhase(query);
            }
        }

        if (contestId != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_PK.eq(contestId));
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Long.class);
    }

    @Override
    public Proposal create(Proposal proposal) {

        ProposalRecord ret = this.dslContext.insertInto(PROPOSAL)
                .set(PROPOSAL.CREATE_DATE, proposal.getCreateDate())
                .set(PROPOSAL.UPDATED_DATE, proposal.getUpdatedDate())
                .set(PROPOSAL.CURRENT_VERSION, proposal.getCurrentVersion())
                .set(PROPOSAL.AUTHOR_ID, proposal.getAuthorId())
                .set(PROPOSAL.VISIBLE, proposal.getVisible())
                .set(PROPOSAL.DISCUSSION_ID, proposal.getDiscussionId())
                .set(PROPOSAL.RESULTS_DISCUSSION_ID, proposal.getResultsDiscussionId())
                .set(PROPOSAL.JUDGE_DISCUSSION_ID, proposal.getJudgeDiscussionId())
                .set(PROPOSAL.FELLOW_DISCUSSION_ID, proposal.getFellowDiscussionId())
                .set(PROPOSAL.ADVISOR_DISCUSSION_ID, proposal.getAdvisorDiscussionId())
                .set(PROPOSAL.GROUP_ID, proposal.getGroupId())
                .returning(PROPOSAL.PROPOSAL_ID)
                .fetchOne();
        if (ret != null) {
            proposal.setProposalId(ret.getValue(PROPOSAL.PROPOSAL_ID));
            return proposal;
        } else {
            return null;
        }

    }

    @Override
    public boolean update(Proposal proposal) {

        return dslContext.update(PROPOSAL)
                .set(PROPOSAL.CREATE_DATE, proposal.getCreateDate())
                .set(PROPOSAL.UPDATED_DATE, proposal.getUpdatedDate())
                .set(PROPOSAL.CURRENT_VERSION, proposal.getCurrentVersion())
                .set(PROPOSAL.AUTHOR_ID, proposal.getAuthorId())
                .set(PROPOSAL.VISIBLE, proposal.getVisible())
                .set(PROPOSAL.DISCUSSION_ID, proposal.getDiscussionId())
                .set(PROPOSAL.RESULTS_DISCUSSION_ID, proposal.getResultsDiscussionId())
                .set(PROPOSAL.JUDGE_DISCUSSION_ID, proposal.getJudgeDiscussionId())
                .set(PROPOSAL.FELLOW_DISCUSSION_ID, proposal.getFellowDiscussionId())
                .set(PROPOSAL.ADVISOR_DISCUSSION_ID, proposal.getAdvisorDiscussionId())
                .set(PROPOSAL.GROUP_ID, proposal.getGroupId())
                .where(PROPOSAL.PROPOSAL_ID.eq(proposal.getProposalId()))
                .execute() > 0;
    }

    @Override
    public Proposal getByGroupId(Long groupId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL)
                .where(PROPOSAL.GROUP_ID.eq(groupId)).fetchOne();

        if (record == null) {
            throw new NotFoundException("Proposal with groupid " + groupId + " does not exist");
        }
        return record.into(Proposal.class);

    }

    @Override
    public Proposal get(Long proposalId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL)
                .where(PROPOSAL.PROPOSAL_ID.eq(proposalId)).fetchOne();

        if (record == null) {
            throw new NotFoundException("Proposal with id " + proposalId + " does not exist");
        }
        return record.into(Proposal.class);

    }

    @Override
    public Integer getProposalMaterializedPoints(Long proposalId) {

        return dslContext.select(sum(POINTS.MATERIALIZED_POINTS)).from(POINTS).where(POINTS.PROPOSAL_ID.eq(proposalId)).fetchOne(0, Integer.class);
    }

}
