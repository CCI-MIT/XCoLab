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
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContestClient {

    private static final RestService contestService = new RestService("contest-service");

    private static final RestResource1<Contest, Long> contestResource = new RestResource1<>(contestService,
            "contests", Contest.TYPES);

    private static final RestResource<ContestPhase, Long> contestPhasesResource = new RestResource1<>(contestService,
            "contestPhases", ContestPhase.TYPES);

    private static final RestResource<ContestPhaseType, Long> contestPhaseTypesResource = new RestResource1<>(contestService,
            "contestPhaseTypes", ContestPhaseType.TYPES);

    private static final RestResource<ContestType, Long> contestTypeResource = new RestResource1<>(contestService,
            "contestTypes", ContestType.TYPES);

    private static final RestResource<ContestTeamMember, Long> contestTeamMemberResource = new RestResource1<>(contestService,
            "contestTeamMembers", ContestTeamMember.TYPES);

    private static final RestResource<ContestTeamMemberRole, Long> contestTeamMemberRoleResource = new RestResource1<>(contestService,
            "contestTeamMemberRoles", ContestTeamMemberRole.TYPES);

    private static final RestResource1<ContestSchedule, Long> contestScheduleResource = new RestResource1<>(contestService,
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
        c.setFocusAreaId(0L);
        c.setContestTier(0L);
        c.setContestLogoId(0L);
        c.setFeatured_(false);
        c.setPlansOpenByDefault(false);
        c.setSponsorLogoId(0L);
        c.setSponsorLink("");
        c.setSponsorText("");
        c.setFlag(0);
        c.setFlagText("");
        c.setFlagTooltip("");
        c.setWeight(0);
        c.setResourcesUrl("");
        c.setUsePermissions(false);
        c.setContestCreationStatus("");
        c.setDefaultModelId(0L);
        c.setOtherModels("");
        c.setPoints(0.0);
        c.setDefaultParentPointType(0L);
        c.setPointDistributionStrategy("");
        c.setEmailTemplateUrl("");
        c.setHideRibbons(false);
        c.setResourceArticleId(0L);
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
                .queryParam("contestTier", contestTier)
                .queryParam("focusAreaOntologyTerms", focusAreaOntologyTerms.toArray())
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
        return contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("sort", "weight")
                .execute();
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
        return contestScheduleResource.get(id)
                .withCache(CacheKeys.of(ContestSchedule.class, id), CacheRetention.REQUEST)
                .execute();
    }

    public static boolean isContestScheduleUsed(long contestScheduleId) {
        return contestScheduleResource.getSubServiceResource(contestScheduleId, "isUsed")
                .query(Boolean.class)
                .get();
    }

    public static List<ContestSchedule> getAllContestSchedules() {
        return contestScheduleResource.list().execute();
    }


    public static List<ContestPhase> getVisibleContestPhases(Long contestId) {
        return contestResource.getSubRestResource(contestId, "visiblePhases", ContestPhase.TYPES)
                .list()
                .withCache(CacheKeys.withClass(ContestPhase.class)
                    .withParameter("contestId", contestId)
                    .withParameter("visible", true).asList(),
                        CacheRetention.MEDIUM)
                .execute();
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
        return contestPhasesResource.list()
                .queryParam("contestPK", contestPK)
                .execute();
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

    public static List<ContestPhase> getTemplatePhasesForContestScheduleId(Long contestScheduleId) {
        return contestPhasesResource.list()
                .queryParam("contestPK", ContestPhase.SCHEDULE_TEMPLATE_PHASE_CONTEST_ID)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute();
    }


    public static ContestPhase getContestPhase(Long contestPhaseId) {
        return contestPhasesResource.get(contestPhaseId).execute();
    }

    public static ContestPhase getActivePhase(Long contestId) {
        return contestResource.service(contestId, "activePhase", ContestPhase.class).get();
    }

    public static ContestPhaseType getContestPhaseType(Long contestPhaseTypeId) {
        return contestPhaseTypesResource.get(contestPhaseTypeId)
                .withCache(CacheKeys.of(ContestPhaseType.class, contestPhaseTypeId),
                        CacheRetention.MEDIUM)
                .execute();
    }

    public static List<ContestPhaseType> getAllContestPhaseTypes() {
        return contestPhaseTypesResource.list()
                .execute();
    }

    public static String getContestStatusStr(Long contestPhaseId) {
        return null;
    }

    public static ContestType getContestType(long id) {
        return contestTypeResource.get(id)
                .withCache(CacheKeys.of(ContestType.class, id), CacheRetention.RUNTIME)
                .execute();
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
        return contestTeamMemberRoleResource.get(id)
                .withCache(CacheKeys.of(ContestTeamMemberRole.class, id), CacheRetention.LONG)
                .execute();
    }

}
