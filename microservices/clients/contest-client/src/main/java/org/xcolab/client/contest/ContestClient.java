package org.xcolab.client.contest;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestPhaseType;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.ContestTeamMember;
import org.xcolab.client.contest.pojo.ContestTeamMemberRole;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContestClient {

    private static final RestService contestService = new RestService("contest-service");

    private static final RestResource<Contest> contestResource = new RestResource<>(contestService,
            "contests", Contest.TYPES);

    private static final RestResource<ContestPhase> contestPhasesResource = new RestResource<>(contestService,
            "contestPhases", ContestPhase.TYPES);

    private static final RestResource<ContestPhaseType> contestPhaseTypesResource = new RestResource<>(contestService,
            "contestPhaseTypes", ContestPhaseType.TYPES);

    private static final RestResource<ContestType> contestTypeResource = new RestResource<>(contestService,
            "contestTypes", ContestType.TYPES);

    private static final RestResource<ContestTeamMember> contestTeamMemberResource = new RestResource<>(contestService,
            "contestTeamMembers", ContestTeamMember.TYPES);

    private static final RestResource<ContestTeamMemberRole> contestTeamMemberRoleResource = new RestResource<>(contestService,
            "contestTeamMemberRoles", ContestTeamMemberRole.TYPES);

    private static final RestResource<ContestSchedule> contestScheduleResource = new RestResource<>(contestService,
            "contestSchedules", ContestSchedule.TYPES);

    public static Contest getContest(long contestId) throws ContestNotFoundException {
        try {
            return contestResource.get(contestId)
                    .withCache(CacheKeys.of(Contest.class, contestId), CacheRetention.REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ContestNotFoundException(contestId);
        }
    }

    public static Contest createContest(Long userId, String name) {
        Contest c = new Contest();
        c.setAuthorId(userId);
        c.setContestName(name);
        c.setContestShortName(name);
        c.setContestUrlName(c.generateContestUrlName());
        c.setContestDescription("");
        c.setContestModelDescription("");
        c.setContestPositionsDescription("");
        c.setContestActive(false);
        c.setProposalCreationTemplateString("");
        c.setProposalVoteTemplateString("");
        c.setProposalVoteConfirmationTemplateString("");
        c.setVoteQuestionTemplateString("");
        c.setVoteTemplateString("");
        c.setFocusAreaId(0l);
        c.setContestTier(0l);
        c.setContestLogoId(0l);
        c.setFeatured_(false);
        c.setPlansOpenByDefault(false);
        c.setSponsorLogoId(0l);
        c.setSponsorLink("");
        c.setSponsorText("");
        c.setFlag(0);
        c.setFlagText("");
        c.setFlagTooltip("");
        c.setWeight(0);
        c.setResourcesUrl("");
        c.setUsePermissions(false);
        c.setContestCreationStatus("");
        c.setDefaultModelId(0l);
        c.setOtherModels("");
        c.setPoints(0.0);
        c.setDefaultParentPointType(0l);
        c.setPointDistributionStrategy("");
        c.setEmailTemplateUrl("");
        c.setHideRibbons(false);
        c.setResourceArticleId(0l);
        return createContest(c);
    }

    public static Contest createContest(Contest contest) {
        return contestResource.create(contest).execute();
    }


    public static boolean updateContest(Contest contest) {
        return contestResource.update(contest, contest.getContestPK())
                .execute();
    }

    public static Integer getProposalCount(Long contestId) {
        try {
            return contestResource.<Proposal, Integer>service(contestId, "proposalCountForActivePhase", Integer.class)
                    .withCache(CacheKeys.withClass(Proposal.class)
                            .withParameter("contestId", contestId).asCount(), CacheRetention.MEDIUM)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Contest getContest(String contestUrlName, long contestYear)
            throws ContestNotFoundException {
        final Contest contest = contestResource.list()
                .queryParam("contestUrlName", contestUrlName)
                .queryParam("contestYear", contestYear)
                .withCache(CacheKeys.withClass(Contest.class)
                        .withParameter("contestUrlName", contestUrlName)
                        .withParameter("contestYear", contestYear).asList(), CacheRetention.LONG)
                .executeWithResult().getFirstIfExists();
        if (contest == null) {
            throw new ContestNotFoundException(contestUrlName, contestYear);
        }
        return contest;
    }

    public static List<Contest> findContestsByActiveFeatured(Boolean active, Boolean featured) {
        return contestResource.list()
                .optionalQueryParam("active", active)
                .optionalQueryParam("featured", featured)
                .execute();
    }

    public static List<Contest> findContestsTierLevelAndOntologyTermIds(Long contestTier, List<Long> focusAreaOntologyTerms) {
        return contestResource.list()
                .optionalQueryParam("contestTier", contestTier)
                .optionalQueryParam("focusAreaOntologyTerms", focusAreaOntologyTerms)
                .execute();
    }

    public static List<Contest> getSubContestsByOntologySpaceId(Long contestId, Long ontologySpaceId) {

        try {
            //TODO: bad url design
            return contestResource.service(contestId, "getSubContestsByOntologySpaceId", List.class)
                    .optionalQueryParam("ontologySpaceId", ontologySpaceId)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public static List<Contest> getAllContests() {
        return contestResource.list().execute();
    }

    public static List<Contest> getContestsByPlanTemplateId(Long planTemplateId) {
        return contestResource
                .list()
                .queryParam("planTemplateId", planTemplateId)
                .execute();
    }

    public static List<Contest> getContestsByContestScheduleId(Long contestScheduleId) {
        return contestResource
                .list()
                .queryParam("contestScheduleId", contestScheduleId)
                .execute();
    }

    public static ContestSchedule createContestSchedule(ContestSchedule contestSchedule) {
        return contestScheduleResource.create(contestSchedule).execute();
    }

    public static boolean updateContestSchedule(ContestSchedule contestSchedule) {
        return contestScheduleResource.update(contestSchedule, contestSchedule.getId_())
                .execute();
    }

    public static ContestSchedule getContestSchedule(long id) {
        try {
            return contestScheduleResource.get(id)
                    .withCache(CacheKeys.of(ContestSchedule.class, id), CacheRetention.REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public static List<ContestSchedule> getAllContestSchedules() {
        return contestScheduleResource.list().execute();
    }


    public static List<ContestPhase> getVisibleContestPhases(Long contestPK) {
        List<ContestPhase> allPhases = getAllContestPhases(contestPK);

        List<ContestPhase> visiblePhases = new ArrayList<>();
        for (ContestPhase phase : allPhases) {
            ContestPhaseType phaseType = getContestPhaseType(phase.getContestPhaseType());
            if (!phaseType.getInvisible()) {
                visiblePhases.add(phase);
            }
        }

        return visiblePhases;
    }

    public static void deleteContestPhase(Long contestPhasePK) {
        contestPhasesResource.delete(contestPhasePK).execute();
    }

    public static boolean updateContestPhase(ContestPhase contestPhase) {
        return contestPhasesResource.update(contestPhase, contestPhase.getContestPhasePK())
                .execute();
    }

    public static ContestPhase createContestPhase(ContestPhase contestPhase) {
        return contestPhasesResource.create(contestPhase).execute();
    }

    public static List<ContestPhase> getAllContestPhases(Long contestPK) {
        return contestPhasesResource.list().queryParam("contestPK", contestPK).execute();
    }

    public static List<ContestPhase> getPhasesForContestScheduleId(Long contestScheduleId) {
        return contestPhasesResource.list()
                .queryParam("contestScheduleId", contestScheduleId)
                .execute();
    }

    public static List<ContestPhase> getPhasesForContestScheduleIdAndContest(Long contestScheduleId, Long contestPK) {
        return contestPhasesResource.list()
                .queryParam("contestPK", contestPK)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute();
    }


    public static ContestPhase getContestPhase(Long contestPhaseId) {
        try {
            return contestPhasesResource.get(contestPhaseId).executeChecked();
        } catch (EntityNotFoundException ignored) {
            return null;
        }
    }

    public static ContestPhase getActivePhase(Long contestId) {
        try {
            return contestResource.service(contestId, "activePhase", ContestPhase.class).getChecked();
        } catch (EntityNotFoundException ignored) {
            return null;
        }
    }


    public static ContestPhaseType getContestPhaseType(Long contestPhaseTypeId) {
        try {
            return contestPhaseTypesResource.get(contestPhaseTypeId)
                    .withCache(CacheKeys.of(ContestPhaseType.class, contestPhaseTypeId),
                            CacheRetention.MEDIUM)
                    .executeChecked();
        } catch (EntityNotFoundException ignored) {
            return null;
        }
    }

    public static List<ContestPhaseType> getAllContestPhaseTypes() {
        return contestPhaseTypesResource.list()
                .execute();
    }

    public static String getContestStatusStr(Long contestPhaseId) {
        return null;
    }

    public static ContestType getContestType(long id) {
        try {
            return contestTypeResource.get(id)
                    .withCache(CacheKeys.of(ContestType.class, id), CacheRetention.RUNTIME)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public static List<ContestType> getAllContestTypes() {
        return contestTypeResource.list()
                .withCache(CacheKeys.withClass(ContestType.class).asList(), CacheRetention.LONG)
                .execute();
    }

    public static List<Long> getRoleForContestTeam(Long contestId, Long roleId) {
        Map<Long, List<Long>> teamRoleToUsersMap = getContestTeamMembersByRole(contestId);
        List<Long> members = teamRoleToUsersMap.get(roleId);
        if (members == null) {
            return new ArrayList<>();
        } else {
            return members;
        }
    }
    public static List<Long> getAdvisorsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.ADVISOR.getRoleId());

    }

    public static List<Long> getJudgesForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.JUDGE.getRoleId());
    }

    public static List<Long> getFellowsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.FELLOW.getRoleId());
    }

    public static List<Long> getContestManagersForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.CONTEST_MANAGER.getRoleId());

    }

    public static Map<Long, List<Long>> getContestTeamMembersByRole(Long contestId) {
        Map<Long, List<Long>> teamRoleToUsersMap = new TreeMap<>();
        for (ContestTeamMember ctm : getTeamMembers(contestId)) {
            List<Long> roleUsers = teamRoleToUsersMap.get(ctm.getRoleId());

            if (roleUsers == null) {
                roleUsers = new ArrayList<>();
                teamRoleToUsersMap.put(ctm.getRoleId(), roleUsers);
            }

            roleUsers.add(ctm.getUserId());

        }
        return teamRoleToUsersMap;
    }

    public static List<ContestTeamMember> getTeamMembers(Long contestId) {
        return contestTeamMemberResource.list()
                .optionalQueryParam("contestId", contestId)
                .withCache(CacheKeys.withClass(ContestTeamMember.class)
                        .withParameter("contestId", contestId).asList(), CacheRetention.MEDIUM)
                .execute();
    }

    public static ContestTeamMember createContestTeamMember(ContestTeamMember contestTeamMember) {
        return contestTeamMemberResource.create(contestTeamMember).execute();
    }

    public static void deleteContestTeamMember(Long contestTeamMemberId) {
        contestTeamMemberResource.delete(contestTeamMemberId).execute();
    }

    public static ContestTeamMemberRole getContestTeamMemberRole(long id) {
        try {
            return contestTeamMemberRoleResource.get(id)
                    .withCache(CacheKeys.of(ContestTeamMemberRole.class, id), CacheRetention.LONG)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

}
