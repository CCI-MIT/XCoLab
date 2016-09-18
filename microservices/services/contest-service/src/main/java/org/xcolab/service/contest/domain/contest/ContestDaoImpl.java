package org.xcolab.service.contest.domain.contest;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.model.tables.records.ContestRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.service.utils.PaginationHelper.SortColumn;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST;

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

        ContestRecord ret = this.dslContext.insertInto(CONTEST)
                //.set(CONTEST.CONTEST_PK, contest.getContestPK())
                .set(CONTEST.CONTEST_TYPE_ID, contest.getContestTypeId())
                .set(CONTEST.CONTEST_NAME, contest.getContestName())
                .set(CONTEST.CONTEST_SHORT_NAME, contest.getContestShortName())
                .set(CONTEST.CONTEST_URL_NAME, contest.getContestUrlName())
                .set(CONTEST.CONTEST_YEAR, contest.getContestYear())
                .set(CONTEST.CONTEST_DESCRIPTION, contest.getContestDescription())
                .set(CONTEST.CONTEST_MODEL_DESCRIPTION, contest.getContestModelDescription())
                .set(CONTEST.CONTEST_POSITIONS_DESCRIPTION, contest.getContestPositionsDescription())
                .set(CONTEST.CREATED, contest.getCreated())
                .set(CONTEST.UPDATED, contest.getUpdated())
                .set(CONTEST.AUTHOR_ID, contest.getAuthorId())
                .set(CONTEST.CONTEST_ACTIVE, contest.getContestActive())
                .set(CONTEST.PLAN_TEMPLATE_ID, contest.getPlanTemplateId())
                .set(CONTEST.CONTEST_SCHEDULE_ID, contest.getContestScheduleId())
                .set(CONTEST.PROPOSAL_CREATION_TEMPLATE_STRING, contest.getProposalCreationTemplateString())
                .set(CONTEST.VOTE_TEMPLATE_STRING, contest.getVoteTemplateString())
                .set(CONTEST.PROPOSAL_VOTE_TEMPLATE_STRING, contest.getProposalVoteTemplateString())
                .set(CONTEST.PROPOSAL_VOTE_CONFIRMATION_TEMPLATE_STRING, contest.getProposalVoteConfirmationTemplateString())
                .set(CONTEST.VOTE_QUESTION_TEMPLATE_STRING, contest.getVoteQuestionTemplateString())
                .set(CONTEST.FOCUS_AREA_ID, contest.getFocusAreaId())
                .set(CONTEST.CONTEST_TIER, contest.getContestTier())
                .set(CONTEST.CONTEST_LOGO_ID, contest.getContestLogoId())
                .set(CONTEST.FEATURED_, contest.getFeatured_())
                .set(CONTEST.PLANS_OPEN_BY_DEFAULT, contest.getPlansOpenByDefault())
                .set(CONTEST.SPONSOR_LOGO_ID, contest.getSponsorLogoId())
                .set(CONTEST.SPONSOR_TEXT, contest.getSponsorText())
                .set(CONTEST.SPONSOR_LINK, contest.getSponsorLink())
                .set(CONTEST.FLAG, contest.getFlag())
                .set(CONTEST.FLAG_TEXT, contest.getFlagText())
                .set(CONTEST.FLAG_TOOLTIP, contest.getFlagTooltip())
                .set(CONTEST.GROUP_ID, contest.getGroupId())
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
                .returning(CONTEST.CONTEST_PK)
                .fetchOne();
        if (ret != null) {
            contest.setContestPK(ret.getValue(CONTEST.CONTEST_PK));
            return contest;
        } else {
            return null;
        }

    }

    public boolean update(Contest contest) {
        return dslContext.update(CONTEST)
                .set(CONTEST.CONTEST_PK, contest.getContestPK())
                .set(CONTEST.CONTEST_TYPE_ID, contest.getContestTypeId())
                .set(CONTEST.CONTEST_NAME, contest.getContestName())
                .set(CONTEST.CONTEST_SHORT_NAME, contest.getContestShortName())
                .set(CONTEST.CONTEST_URL_NAME, contest.getContestUrlName())
                .set(CONTEST.CONTEST_YEAR, contest.getContestYear())
                .set(CONTEST.CONTEST_DESCRIPTION, contest.getContestDescription())
                .set(CONTEST.CONTEST_MODEL_DESCRIPTION, contest.getContestModelDescription())
                .set(CONTEST.CONTEST_POSITIONS_DESCRIPTION, contest.getContestPositionsDescription())
                .set(CONTEST.CREATED, contest.getCreated())
                .set(CONTEST.UPDATED, contest.getUpdated())
                .set(CONTEST.AUTHOR_ID, contest.getAuthorId())
                .set(CONTEST.CONTEST_ACTIVE, contest.getContestActive())
                .set(CONTEST.PLAN_TEMPLATE_ID, contest.getPlanTemplateId())
                .set(CONTEST.CONTEST_SCHEDULE_ID, contest.getContestScheduleId())
                .set(CONTEST.PROPOSAL_CREATION_TEMPLATE_STRING, contest.getProposalCreationTemplateString())
                .set(CONTEST.VOTE_TEMPLATE_STRING, contest.getVoteTemplateString())
                .set(CONTEST.PROPOSAL_VOTE_TEMPLATE_STRING, contest.getProposalVoteTemplateString())
                .set(CONTEST.PROPOSAL_VOTE_CONFIRMATION_TEMPLATE_STRING, contest.getProposalVoteConfirmationTemplateString())
                .set(CONTEST.VOTE_QUESTION_TEMPLATE_STRING, contest.getVoteQuestionTemplateString())
                .set(CONTEST.FOCUS_AREA_ID, contest.getFocusAreaId())
                .set(CONTEST.CONTEST_TIER, contest.getContestTier())
                .set(CONTEST.CONTEST_LOGO_ID, contest.getContestLogoId())
                .set(CONTEST.FEATURED_, contest.getFeatured_())
                .set(CONTEST.PLANS_OPEN_BY_DEFAULT, contest.getPlansOpenByDefault())
                .set(CONTEST.SPONSOR_LOGO_ID, contest.getSponsorLogoId())
                .set(CONTEST.SPONSOR_TEXT, contest.getSponsorText())
                .set(CONTEST.SPONSOR_LINK, contest.getSponsorLink())
                .set(CONTEST.FLAG, contest.getFlag())
                .set(CONTEST.FLAG_TEXT, contest.getFlagText())
                .set(CONTEST.FLAG_TOOLTIP, contest.getFlagTooltip())
                .set(CONTEST.GROUP_ID, contest.getGroupId())
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
                .where(CONTEST.CONTEST_PK.eq(contest.getContestPK()))
                .execute() > 0;
    }

    @Override
    public boolean isShared(long contestId) {
        final Record1<Boolean> record = dslContext.select(CONTEST.IS_SHARED_CONTEST)
                .from(CONTEST)
                .where(CONTEST.CONTEST_PK.eq(contestId))
                .fetchOne();
        return record != null && record.into(Boolean.class);
    }

    @Override
    public Contest get(Long contestId) throws NotFoundException {
        final Record record = dslContext.select()
                .from(CONTEST)
                .where(CONTEST.CONTEST_PK.eq(contestId))
                .fetchOne();
        if (record == null) {
            throw new NotFoundException("Contest with id " + contestId + " was not found");
        }
        return record.into(Contest.class);
    }

    @Override
    public List<Contest> findByGiven(PaginationHelper paginationHelper, String contestUrlName,
            Long contestYear, Boolean active, Boolean featured, Long contestTier,
            List<Long> focusAreaOntologyTerms, Long contestScheduleId, Long planTemplateId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST).getQuery();

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
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTEST.CREATED.asc() : CONTEST.CREATED.desc());
                    break;
                case "weight":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTEST.WEIGHT.asc() : CONTEST.WEIGHT.desc());
                    break;

                default:
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Contest.class);
    }

    @Override
    public boolean existsWithScheduleId(long contestScheduleId) {
        return dslContext.fetchExists(DSL.select()
                .from(CONTEST)
                .where(CONTEST.CONTEST_SCHEDULE_ID.eq(contestScheduleId)));
    }
}
