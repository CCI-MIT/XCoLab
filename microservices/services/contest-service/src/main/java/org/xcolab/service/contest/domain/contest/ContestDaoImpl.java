package org.xcolab.service.contest.domain.contest;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Select;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.SortColumn;
import org.xcolab.util.enums.activity.ActivityEntryType;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST;
import static org.xcolab.model.Tables.CONTEST_DISCUSSION;
import static org.xcolab.model.Tables.CONTEST_PHASE;
import static org.xcolab.model.Tables.CONTEST_TEAM_MEMBER;
import static org.xcolab.model.Tables.CONTEST_TRANSLATION;
import static org.xcolab.model.Tables.PROPOSAL_2_PHASE;
import static org.xcolab.service.proposal.domain.proposal.ProposalDaoImpl.deleteOrphanProposals;

@Repository
public class ContestDaoImpl implements ContestDao {

    private final DSLContext dslContext;

    @Autowired
    public ContestDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public Contest create(Contest contest) {

        this.dslContext.insertInto(CONTEST).set(CONTEST.CONTEST_PK, contest.getContestPK())
                .set(CONTEST.CONTEST_TYPE_ID, contest.getContestTypeId())
                .set(CONTEST.CONTEST_NAME, contest.getContestName())
                .set(CONTEST.CONTEST_SHORT_NAME, contest.getContestShortName())
                .set(CONTEST.CONTEST_URL_NAME, contest.getContestUrlName())
                .set(CONTEST.CONTEST_YEAR, contest.getContestYear())
                .set(CONTEST.CONTEST_DESCRIPTION, contest.getContestDescription())
                .set(CONTEST.CONTEST_MODEL_DESCRIPTION, contest.getContestModelDescription())
                .set(CONTEST.CONTEST_POSITIONS_DESCRIPTION,
                        contest.getContestPositionsDescription())
                .set(CONTEST.CREATED, contest.getCreated())
                .set(CONTEST.UPDATED, contest.getUpdated())
                .set(CONTEST.AUTHOR_ID, contest.getAuthorId())
                .set(CONTEST.CONTEST_ACTIVE, contest.getContestActive())
                .set(CONTEST.PLAN_TEMPLATE_ID, contest.getPlanTemplateId())
                .set(CONTEST.CONTEST_SCHEDULE_ID, contest.getContestScheduleId())
                .set(CONTEST.PROPOSAL_CREATION_TEMPLATE_STRING,
                        contest.getProposalCreationTemplateString())
                .set(CONTEST.VOTE_TEMPLATE_STRING, contest.getVoteTemplateString())
                .set(CONTEST.PROPOSAL_VOTE_TEMPLATE_STRING, contest.getProposalVoteTemplateString())
                .set(CONTEST.PROPOSAL_VOTE_CONFIRMATION_TEMPLATE_STRING,
                        contest.getProposalVoteConfirmationTemplateString())
                .set(CONTEST.VOTE_QUESTION_TEMPLATE_STRING, contest.getVoteQuestionTemplateString())
                .set(CONTEST.FOCUS_AREA_ID, contest.getFocusAreaId())
                .set(CONTEST.CONTEST_TIER, contest.getContestTier())
                .set(CONTEST.CONTEST_LOGO_ID, contest.getContestLogoId())
                .set(CONTEST.FEATURED_, contest.getFeatured_())
                .set(CONTEST.PLANS_OPEN_BY_DEFAULT, contest.getPlansOpenByDefault())
                .set(CONTEST.DEFAULT_PROPOSAL_LOGO_ID, contest.getDefaultProposalLogoId())
                .set(CONTEST.SPONSOR_LOGO_ID, contest.getSponsorLogoId())
                .set(CONTEST.SPONSOR_TEXT, contest.getSponsorText())
                .set(CONTEST.SPONSOR_LINK, contest.getSponsorLink())
                .set(CONTEST.FLAG, contest.getFlag()).set(CONTEST.FLAG_TEXT, contest.getFlagText())
                .set(CONTEST.FLAG_TOOLTIP, contest.getFlagTooltip())
                .set(CONTEST.DISCUSSION_GROUP_ID, contest.getDiscussionGroupId())
                .set(CONTEST.WEIGHT, contest.getWeight())
                .set(CONTEST.RESOURCES_URL, contest.getResourcesUrl())
                .set(CONTEST.CONTEST_PRIVATE, contest.getContestPrivate())
                .set(CONTEST.USE_PERMISSIONS, contest.getUsePermissions())
                .set(CONTEST.CONTEST_CREATION_STATUS, contest.getContestCreationStatus())
                .set(CONTEST.DEFAULT_MODEL_ID, contest.getDefaultModelId())
                .set(CONTEST.OTHER_MODELS, contest.getOtherModels())
                .set(CONTEST.DEFAULT_MODEL_SETTINGS, contest.getDefaultModelSettings())
                .set(CONTEST.POINTS, contest.getPoints())
                .set(CONTEST.DEFAULT_PARENT_POINT_TYPE, contest.getDefaultParentPointType())
                .set(CONTEST.POINT_DISTRIBUTION_STRATEGY, contest.getPointDistributionStrategy())
                .set(CONTEST.EMAIL_TEMPLATE_URL, contest.getEmailTemplateUrl())
                .set(CONTEST.SHOW_IN_TILE_VIEW, contest.getShow_in_tile_view())
                .set(CONTEST.SHOW_IN_LIST_VIEW, contest.getShow_in_list_view())
                .set(CONTEST.SHOW_IN_OUTLINE_VIEW, contest.getShow_in_outline_view())
                .set(CONTEST.HIDE_RIBBONS, contest.getHideRibbons())
                .set(CONTEST.RESOURCE_ARTICLE_ID, contest.getResourceArticleId())
                .set(CONTEST.IS_SHARED_CONTEST, contest.getIsSharedContest())
                .set(CONTEST.SHARED_ORIGIN, contest.getSharedOrigin()).execute();

        return contest;

    }

    @Override
    public boolean update(Contest contest) {
        return dslContext.update(CONTEST).set(CONTEST.CONTEST_PK, contest.getContestPK())
                .set(CONTEST.CONTEST_TYPE_ID, contest.getContestTypeId())
                .set(CONTEST.CONTEST_NAME, contest.getContestName())
                .set(CONTEST.CONTEST_SHORT_NAME, contest.getContestShortName())
                .set(CONTEST.CONTEST_URL_NAME, contest.getContestUrlName())
                .set(CONTEST.CONTEST_YEAR, contest.getContestYear())
                .set(CONTEST.CONTEST_DESCRIPTION, contest.getContestDescription())
                .set(CONTEST.CONTEST_MODEL_DESCRIPTION, contest.getContestModelDescription())
                .set(CONTEST.CONTEST_POSITIONS_DESCRIPTION,
                        contest.getContestPositionsDescription())
                .set(CONTEST.CREATED, contest.getCreated())
                .set(CONTEST.UPDATED, contest.getUpdated())
                .set(CONTEST.AUTHOR_ID, contest.getAuthorId())
                .set(CONTEST.CONTEST_ACTIVE, contest.getContestActive())
                .set(CONTEST.PLAN_TEMPLATE_ID, contest.getPlanTemplateId())
                .set(CONTEST.CONTEST_SCHEDULE_ID, contest.getContestScheduleId())
                .set(CONTEST.PROPOSAL_CREATION_TEMPLATE_STRING,
                        contest.getProposalCreationTemplateString())
                .set(CONTEST.VOTE_TEMPLATE_STRING, contest.getVoteTemplateString())
                .set(CONTEST.PROPOSAL_VOTE_TEMPLATE_STRING, contest.getProposalVoteTemplateString())
                .set(CONTEST.PROPOSAL_VOTE_CONFIRMATION_TEMPLATE_STRING,
                        contest.getProposalVoteConfirmationTemplateString())
                .set(CONTEST.VOTE_QUESTION_TEMPLATE_STRING, contest.getVoteQuestionTemplateString())
                .set(CONTEST.FOCUS_AREA_ID, contest.getFocusAreaId())
                .set(CONTEST.CONTEST_TIER, contest.getContestTier())
                .set(CONTEST.CONTEST_LOGO_ID, contest.getContestLogoId())
                .set(CONTEST.FEATURED_, contest.getFeatured_())
                .set(CONTEST.PLANS_OPEN_BY_DEFAULT, contest.getPlansOpenByDefault())
                .set(CONTEST.SPONSOR_LOGO_ID, contest.getSponsorLogoId())
                .set(CONTEST.DEFAULT_PROPOSAL_LOGO_ID, contest.getDefaultProposalLogoId())
                .set(CONTEST.SPONSOR_TEXT, contest.getSponsorText())
                .set(CONTEST.SPONSOR_LINK, contest.getSponsorLink())
                .set(CONTEST.FLAG, contest.getFlag()).set(CONTEST.FLAG_TEXT, contest.getFlagText())
                .set(CONTEST.FLAG_TOOLTIP, contest.getFlagTooltip())
                .set(CONTEST.DISCUSSION_GROUP_ID, contest.getDiscussionGroupId())
                .set(CONTEST.WEIGHT, contest.getWeight())
                .set(CONTEST.RESOURCES_URL, contest.getResourcesUrl())
                .set(CONTEST.CONTEST_PRIVATE, contest.getContestPrivate())
                .set(CONTEST.USE_PERMISSIONS, contest.getUsePermissions())
                .set(CONTEST.CONTEST_CREATION_STATUS, contest.getContestCreationStatus())
                .set(CONTEST.DEFAULT_MODEL_ID, contest.getDefaultModelId())
                .set(CONTEST.OTHER_MODELS, contest.getOtherModels())
                .set(CONTEST.DEFAULT_MODEL_SETTINGS, contest.getDefaultModelSettings())
                .set(CONTEST.POINTS, contest.getPoints())
                .set(CONTEST.DEFAULT_PARENT_POINT_TYPE, contest.getDefaultParentPointType())
                .set(CONTEST.POINT_DISTRIBUTION_STRATEGY, contest.getPointDistributionStrategy())
                .set(CONTEST.EMAIL_TEMPLATE_URL, contest.getEmailTemplateUrl())
                .set(CONTEST.SHOW_IN_TILE_VIEW, contest.getShow_in_tile_view())
                .set(CONTEST.SHOW_IN_LIST_VIEW, contest.getShow_in_list_view())
                .set(CONTEST.SHOW_IN_OUTLINE_VIEW, contest.getShow_in_outline_view())
                .set(CONTEST.HIDE_RIBBONS, contest.getHideRibbons())
                .set(CONTEST.RESOURCE_ARTICLE_ID, contest.getResourceArticleId())
                .set(CONTEST.IS_SHARED_CONTEST, contest.getIsSharedContest())
                .set(CONTEST.SHARED_ORIGIN, contest.getSharedOrigin())
                .where(CONTEST.CONTEST_PK.eq(contest.getContestPK())).execute() > 0;
    }

    @Override
    public boolean isShared(long contestId) {
        final Record1<Boolean> record = dslContext.select(CONTEST.IS_SHARED_CONTEST).from(CONTEST)
                .where(CONTEST.CONTEST_PK.eq(contestId)).fetchOne();
        return record != null && record.into(Boolean.class);
    }

    @Override
    public Contest get(Long contestId) throws NotFoundException {
        final Record record =
                dslContext.select().from(CONTEST).where(CONTEST.CONTEST_PK.eq(contestId))
                        .fetchOne();
        if (record == null) {
            throw new NotFoundException("Contest with id " + contestId + " was not found");
        }
        return record.into(Contest.class);
    }


    @Override
    public Contest getByThreadId(Long threadId) throws NotFoundException {
        final Record record =
                dslContext.select().from(CONTEST).where(CONTEST.DISCUSSION_GROUP_ID.eq(threadId))
                        .fetchOne();
        if (record == null) {
            throw new NotFoundException("Contest with thread id " + threadId + " was not found");
        }
        return record.into(Contest.class);
    }

    @Override
    public Contest getByResourceId(Long resourceId) throws NotFoundException {
        final Record record =
                dslContext.select().from(CONTEST).where(CONTEST.RESOURCE_ARTICLE_ID.eq(resourceId))
                        .fetchOne();
        if (record == null) {
            throw new NotFoundException(
                    "Contest with resource id " + resourceId + " was not found");
        }
        return record.into(Contest.class);
    }

    @Override
    public boolean isContestNameYearUnique(String contestShortName, Long year, Long currentContestId) {
        final SelectQuery<Record1<Integer>> query = dslContext.selectCount().from(CONTEST).getQuery();
        query.addConditions(CONTEST.CONTEST_SHORT_NAME.eq(contestShortName));
        query.addConditions(CONTEST.CONTEST_YEAR.eq(year));
        if (currentContestId != null) {
            query.addConditions(CONTEST.CONTEST_PK.notEqual(currentContestId));
        }
        return query.fetchOne().into(Integer.class).intValue() == 0;
    }

    @Override
    public List<Contest> findByGiven(PaginationHelper paginationHelper, String contestUrlName,
            Long contestYear, Boolean active, Boolean featured, List<Long> contestTiers,
            List<Long> focusAreaIds, Long contestScheduleId, Long planTemplateId,
            List<Long> contestTypeIds, Boolean contestPrivate, String searchTerm) {
        final SelectQuery<Record> query = dslContext.select().from(CONTEST).getQuery();

        if (contestPrivate != null) {
            query.addConditions(CONTEST.CONTEST_PRIVATE.eq(contestPrivate));
        }
        if (contestTiers != null) {
            query.addConditions(CONTEST.CONTEST_TIER.in(contestTiers));
        }
        if (contestScheduleId != null) {
            query.addConditions(CONTEST.CONTEST_SCHEDULE_ID.eq(contestScheduleId));
        }
        if (planTemplateId != null) {
            query.addConditions(CONTEST.PLAN_TEMPLATE_ID.eq(planTemplateId));
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            //TODO: replace wild card search with match...against
            query.addConditions(CONTEST.CONTEST_NAME.like("%" + searchTerm + "%")
                    .or(CONTEST.CONTEST_SHORT_NAME.like("%" + searchTerm + "%")));
        }
        if (!CollectionUtils.isEmpty(focusAreaIds)) {
            query.addConditions(CONTEST.FOCUS_AREA_ID.in(focusAreaIds));
        }
        if (contestTypeIds != null && !contestTypeIds.isEmpty()) {
            query.addConditions(CONTEST.CONTEST_TYPE_ID.in(contestTypeIds));
        }
        if (contestUrlName != null) {
            query.addConditions(CONTEST.CONTEST_URL_NAME.eq(contestUrlName));
        }
        if (contestYear != null) {
            query.addConditions(CONTEST.CONTEST_YEAR.eq(contestYear));
        }
        if (active != null) {
            query.addConditions(CONTEST.CONTEST_ACTIVE.eq(active));
        }
        if (featured != null) {
            query.addConditions(CONTEST.FEATURED_.eq(featured));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "createDate":
                    query.addOrderBy(sortColumn.isAscending() ? CONTEST.CREATED.asc()
                            : CONTEST.CREATED.desc());
                    break;
                case "ContestShortName":
                    query.addOrderBy(sortColumn.isAscending() ? CONTEST.CONTEST_SHORT_NAME.asc()
                            : CONTEST.CONTEST_SHORT_NAME.desc());
                    break;
                case "weight":
                    query.addOrderBy(sortColumn.isAscending() ? CONTEST.WEIGHT.asc()
                            : CONTEST.WEIGHT.desc());
                    break;

                default:
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Contest.class);
    }

    @Override
    public Integer countByGiven(String contestUrlName, Long contestYear, Boolean active,
            Boolean featured, Long contestTier, List<Long> focusAreaOntologyTerms,
            Long contestScheduleId, Long planTemplateId, Long contestTypeId,
            Boolean contestPrivate) {
        final SelectQuery<Record1<Integer>> query =
                dslContext.selectCount().from(CONTEST).getQuery();

        if (contestPrivate != null) {
            query.addConditions(CONTEST.CONTEST_PRIVATE.eq(contestPrivate));
        }

        if (contestTier != null) {
            query.addConditions(CONTEST.CONTEST_TIER.eq(contestTier));
        }

        if (contestScheduleId != null) {
            query.addConditions(CONTEST.CONTEST_SCHEDULE_ID.eq(contestScheduleId));
        }

        if (planTemplateId != null) {
            query.addConditions(CONTEST.PLAN_TEMPLATE_ID.eq(planTemplateId));
        }

        if (focusAreaOntologyTerms != null && !focusAreaOntologyTerms.isEmpty()) {
            query.addConditions(CONTEST.FOCUS_AREA_ID.in(focusAreaOntologyTerms));
        }

        if (contestTypeId != null) {
            query.addConditions(CONTEST.CONTEST_TYPE_ID.eq(contestTypeId));
        }

        if (contestUrlName != null) {
            query.addConditions(CONTEST.CONTEST_URL_NAME.eq(contestUrlName));
        }
        if (contestYear != null) {
            query.addConditions(CONTEST.CONTEST_YEAR.eq(contestYear));
        }
        if (active != null) {
            query.addConditions(CONTEST.CONTEST_ACTIVE.eq(active));
        }
        if (featured != null) {
            query.addConditions(CONTEST.FEATURED_.eq(featured));
        }
        query.addOrderBy(CONTEST.CREATED.desc());
        return query.fetchOne(0, Integer.class);
    }

    @Override
    public List<Long> getContestYears() {
        //select ContestYear from xcolab_Contest group by ContestYear order by ContestYear desc;
        final SelectQuery<Record1<Long>> query =
                dslContext.select(CONTEST.CONTEST_YEAR).from(CONTEST).getQuery();
        query.addGroupBy(CONTEST.CONTEST_YEAR);
        query.addOrderBy(CONTEST.CONTEST_YEAR.desc());
        return query.fetchInto(Long.class);
    }

    @Override
    public boolean existsWithScheduleId(long contestScheduleId) {
        return dslContext.fetchExists(DSL.select().from(CONTEST)
                .where(CONTEST.CONTEST_SCHEDULE_ID.eq(contestScheduleId)));
    }

    @Override
    public boolean delete(long contestPK) {
        final boolean[] result = new boolean[1];
        dslContext.transaction(configuration -> {
            DSLContext ctx = DSL.using(configuration);

            deleteContestData(ctx, contestPK);
            deleteContestPhases(ctx, contestPK);
            deleteOrphanProposals(ctx);

            result[0] = deleteContest(ctx, contestPK);
        });
        return result[0];
    }

    private static boolean deleteContest(DSLContext ctx, long contestPK) {
        return ctx.deleteFrom(CONTEST)
                .where(CONTEST.CONTEST_PK.eq(contestPK))
                .execute() > 0;
    }

    private static void deleteContestData(DSLContext ctx, long contestPK) {
        // Delete team members
        ctx.deleteFrom(CONTEST_TEAM_MEMBER)
                .where(CONTEST_TEAM_MEMBER.CONTEST_ID.eq(contestPK))
                .execute();
        // Delete contest translations
        ctx.deleteFrom(CONTEST_TRANSLATION)
                .where(CONTEST_TRANSLATION.CONTEST_ID.eq(contestPK))
                .execute();
        // Delete contest discussions
        ctx.deleteFrom(CONTEST_DISCUSSION)
                .where(CONTEST_DISCUSSION.CONTEST_ID.eq(contestPK))
                .execute();
        // Retrieve contest discussion thread id.
        Long threadId = ctx.select(CONTEST.DISCUSSION_GROUP_ID)
                .from(CONTEST)
                .where(CONTEST.CONTEST_PK.eq(contestPK))
                .fetchOne()
                .value1();
        // Delete contest discussion comments.
        ctx.deleteFrom(COMMENT)
                .where(COMMENT.THREAD_ID.eq(threadId))
                .execute();
        // Delete contest discussion thread.
        ctx.deleteFrom(THREAD)
                .where(THREAD.THREAD_ID.eq(threadId))
                .execute();
        // Delete contest subscriptions.
        ctx.deleteFrom(ACTIVITY_SUBSCRIPTION)
                .where(ACTIVITY_SUBSCRIPTION.CLASS_NAME_ID.eq(ActivityEntryType.CONTEST.getPrimaryTypeId()))
                .and(ACTIVITY_SUBSCRIPTION.CLASS_PK.eq(contestPK));
        // Delete contest activity entries.
        ctx.deleteFrom(ACTIVITY_ENTRY)
                .where(ACTIVITY_ENTRY.PRIMARY_TYPE.eq(ActivityEntryType.CONTEST.getPrimaryTypeId()))
                .and(ACTIVITY_ENTRY.CLASS_PRIMARY_KEY.eq(contestPK));
    }

    private static void deleteContestPhases(DSLContext ctx, long contestPK) {
        // Select query for contest's phases
        Select<Record1<Long>> contestPhases = ctx.select(CONTEST_PHASE.CONTEST_PHASE_PK)
                .from(CONTEST_PHASE)
                .where(CONTEST_PHASE.CONTEST_PK.eq(contestPK));
        // Delete Proposal2Phase rows associated with this contest.
        ctx.deleteFrom(PROPOSAL_2_PHASE)
                .where(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.in(contestPhases))
                .execute();
        // Delete contest's phases.
        ctx.deleteFrom(CONTEST_PHASE)
                .where(CONTEST_PHASE.CONTEST_PK.eq(contestPK))
                .execute();
    }
}
