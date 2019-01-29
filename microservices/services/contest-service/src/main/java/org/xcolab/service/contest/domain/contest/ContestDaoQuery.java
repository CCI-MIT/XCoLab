package org.xcolab.service.contest.domain.contest;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.service.utils.PaginationHelper;

import java.util.ArrayList;
import java.util.List;

public class ContestDaoQuery {

    private final ContestDao contestDao;

    private PaginationHelper paginationHelper;
    private String contestUrlName;
    private Long contestYear;
    private Boolean active;
    private Boolean featured;
    private List<Long> contestTiers;
    private List<Long> focusAreaIds;
    private Long contestScheduleId;
    private Long proposalTemplateId;
    private List<Long> contestTypeIds;
    private Boolean contestPrivate;
    private String searchTerm;

    private ContestDaoQuery(ContestDao contestDao) {
        this.contestDao = contestDao;
    }

    public static ContestDaoQuery find(ContestDao contestDao) {
        return new ContestDaoQuery(contestDao);
    }

    public ContestDaoQuery withContestTypeIds(List<Long> contestTypeIds) {
        this.contestTypeIds = contestTypeIds;
        return this;
    }
    
    public ContestDaoQuery withContestScheduleId(Long contestScheduleId) {
        this.contestScheduleId = contestScheduleId;
        return this;
    }

    public ContestDaoQuery withProposalTemplateId(Long proposalTemplateId) {
        this.proposalTemplateId = proposalTemplateId;
        return this;
    }

    public ContestDaoQuery withContestPrivate(Boolean contestPrivate) {
        this.contestPrivate = contestPrivate;
        return this;
    }

    public ContestDaoQuery withSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
        return this;
    }

    public ContestDaoQuery withFocusAreaIds(List<Long> focusAreaIds) {
        this.focusAreaIds = focusAreaIds;
        return this;
    }

    public ContestDaoQuery withFeatured(Boolean featured) {
        this.featured = featured;
        return this;
    }

    public ContestDaoQuery withContestTiers(List<Long> contestTiers) {
        this.contestTiers = contestTiers;
        return this;
    }

    public ContestDaoQuery withContestTier(long contestTier) {
        if (contestTiers == null) {
            contestTiers = new ArrayList<>();
        }
        contestTiers.add(contestTier);
        return this;
    }

    public ContestDaoQuery withPaginationHelper(PaginationHelper paginationHelper) {
        this.paginationHelper = paginationHelper;
        return this;
    }

    public ContestDaoQuery withContestUrlName(String contestUrlName) {
        this.contestUrlName = contestUrlName;
        return this;
    }

    public ContestDaoQuery withContestYear(Long contestYear) {
        this.contestYear = contestYear;
        return this;
    }

    public ContestDaoQuery withActive(Boolean active) {
        this.active = active;
        return this;
    }

    public List<ContestWrapper> execute() {
        return contestDao.findByGiven(paginationHelper, contestUrlName, contestYear, active,
            featured, contestTiers, focusAreaIds, contestScheduleId, proposalTemplateId,
            contestTypeIds, contestPrivate, searchTerm);
    }
}
