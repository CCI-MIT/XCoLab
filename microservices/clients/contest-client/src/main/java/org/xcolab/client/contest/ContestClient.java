package org.xcolab.client.contest;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.*;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

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


    public static Contest getContest(long contestId) throws ContestNotFoundException {
        try {
            return contestResource.get(contestId)
                    .cacheIdentifier("contestId_" + contestId)
                    .execute();
        } catch (EntityNotFoundException e) {
            throw new ContestNotFoundException(contestId);
        }
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

    public static List<Contest> findByActiveFeatured(Boolean active, Boolean featured) {
        return contestResource.list()
                .optionalQueryParam("active", active)
                .optionalQueryParam("featured", featured)
                .execute();
    }

    public static List<ContestPhase> getAllContestPhases(Long contestPK) {
        return contestPhasesResource.list().queryParam("contestPK", contestPK).execute();
    }

    public static Integer getProposalCountForActiveContestPhase(Long contestId) {
        try {
            return contestResource.service(contestId, "proposalCountForActivePhase", Integer.class).get();
        } catch (EntityNotFoundException ignored) {
            return 0;
        }
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

    public static List<ContestTeamMember> getTeamMembers(Long contestId) {
        return contestTeamMemberResource.list()
                .optionalQueryParam("contestId", contestId)
                .execute();
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
