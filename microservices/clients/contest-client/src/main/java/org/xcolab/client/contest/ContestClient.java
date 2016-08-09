package org.xcolab.client.contest;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.*;
import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.util.exceptions.ReferenceResolutionException;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
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
                    .cacheIdentifier("contestId_" + contestId)
                    .execute();
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
            return contestResource.service(contestId, "proposalCountForActivePhase", Integer.class).get();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Contest getContest(String contestUrlName, long contestYear)
            throws ContestNotFoundException {
        final Contest contest = contestResource.list()
                .queryParam("contestUrlName", contestUrlName)
                .queryParam("contestYear", contestYear)
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
    public static List<Contest> getSubContestsByOntologySpaceId(Long contestId, Long ontologySpaceId){

        try{
            return contestResource.service(contestId, "getSubContestsByOntologySpaceId",List.class)
                    .optionalQueryParam("ontologySpaceId", ontologySpaceId)
                    .get();
        }catch (EntityNotFoundException e){
            return new ArrayList<>();
        }
    }

    public static List<Contest> getAllContests() {
        return contestResource.list().execute();
    }
    public static List<Contest> getContestsByPlanTemplateId(Long planTemplateId){
        return contestResource
                .list()
                .queryParam("planTemplateId", planTemplateId)
                .execute();
    }

    public static List<Contest> getContestsByContestScheduleId(Long contestScheduleId){
        return contestResource
                .list()
                .queryParam("contestScheduleId", contestScheduleId)
                .execute();
    }

    public static  ContestSchedule createContestSchedule(ContestSchedule contestSchedule) {
        return contestScheduleResource.create(contestSchedule).execute();
    }
    public static boolean updateContestSchedule(ContestSchedule contestSchedule) {
        return contestScheduleResource.update(contestSchedule, contestSchedule.getId_())
                .execute();
    }
    public static ContestSchedule getContestSchedule(long Id_) {
        try {
            return contestScheduleResource.get(Id_)
                    .cacheIdentifier("contestScheduleId_" + Id_)
                    .execute();
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

    public static void deleteContestPhase(Long contestPhasePK){
        contestPhasesResource.delete(contestPhasePK);
    }
    public static boolean updateContestPhase(ContestPhase contestPhase) {
        return contestPhasesResource.update(contestPhase, contestPhase.getContestPhasePK())
                .execute();
    }
    public static  ContestPhase createContestPhase(ContestPhase contestPhase) {
        return contestPhasesResource.create(contestPhase).execute();
    }

    public static List<ContestPhase> getAllContestPhases(Long contestPK) {
        return contestPhasesResource.list().queryParam("contestPK", contestPK).execute();
    }

    public static List<ContestPhase> getPhasesForContestScheduleId(Long contestScheduleId){
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
            return contestPhasesResource.get(contestPhaseId).execute();
        } catch (EntityNotFoundException ignored) {
            return null;
        }
    }

    public static ContestPhase getActivePhase(Long contestId) {
        try {
            return contestResource.service(contestId, "activePhase", ContestPhase.class).get();
        } catch (EntityNotFoundException ignored) {
            return null;
        }
    }


    public static ContestPhaseType getContestPhaseType(Long contestPhaseTypeId) {
        try {
            return contestPhaseTypesResource.get(contestPhaseTypeId).execute();
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

    public static ContestType getContestType(long Id_) {
        try {
            return contestTypeResource.get(Id_)
                    .cacheIdentifier("contestTypeId_" + Id_)
                    .execute();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }
    public static List<ContestType> getAllContestTypes(){
         return contestTypeResource.list().execute();
    }
    public static List<Long> getAdvisorsForContest(Long contestId){
        Map<Long, List<Long>> teamRoleToUsersMap = getContestTeamMembersByRole(contestId);
        return teamRoleToUsersMap.get(MemberRole.ADVISOR);

    }
    public static List<Long> getJudgesForContest(Long contestId){
        Map<Long, List<Long>> teamRoleToUsersMap = getContestTeamMembersByRole(contestId);
        return teamRoleToUsersMap.get(MemberRole.JUDGE);
    }
    public static List<Long> getFellowsForContest(Long contestId){
        Map<Long, List<Long>> teamRoleToUsersMap = getContestTeamMembersByRole(contestId);
        return teamRoleToUsersMap.get(MemberRole.FELLOW);
    }
    public static List<Long> getContestManagersForContest(Long contestId){
        Map<Long, List<Long>> teamRoleToUsersMap = getContestTeamMembersByRole(contestId);
        return teamRoleToUsersMap.get(MemberRole.ADMINISTRATOR);

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
                .execute();
    }

    public static  ContestTeamMember createContestTeamMember(ContestTeamMember contestTeamMember) {
        return contestTeamMemberResource.create(contestTeamMember).execute();
    }

    public static void deleteContestTeamMember(Long contestTeamMemberId){
        contestTeamMemberResource.delete(contestTeamMemberId);
    }

    public static ContestTeamMemberRole getContestTeamMemberRole(long Id_) {
        try {
            return contestTeamMemberRoleResource.get(Id_)
                    .cacheIdentifier("contestTeamMemberRoleId_" + Id_)
                    .execute();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

}
