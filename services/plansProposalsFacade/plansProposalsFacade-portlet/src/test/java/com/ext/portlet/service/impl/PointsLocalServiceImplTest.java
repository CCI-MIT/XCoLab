package com.ext.portlet.service.impl;


import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.persistence.PointsPersistence;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionCheckerUtil;
import com.liferay.portal.service.*;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.*;


public class PointsLocalServiceImplTest extends XCoLabTest {
    private int contestPhaseIdCount = 0;
    private List<Proposal> sideProposals;
    private List<Proposal> globalProposals;
    private Contest globalContest;
    private List<Contest> sideContests;
    private List<User> users;

    @Test
    public void testGlobalContestHypotheticalPoints() throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
        this.setupBasicDataset();

        long adminId = 10144L;
        final int pointsToBeDistributed = 10000;
        PermissionCheckerUtil.setThreadValues(UserLocalServiceUtil.getUser(adminId));

        //create 10 different authors
        users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            users.add(this.createUser(50000+i));
        }

        //create global contest
        globalContest = contestLocalService.createNewContest(adminId, "Test-Global-Contest");
        globalContest.setPoints(pointsToBeDistributed);
        globalContest.setDefaultParentPointType(1);
        contestLocalService.updateContest(globalContest);
        //create phases
        ContestPhase gCp1 = createContestPhase(globalContest, 1, false, "PROMOTE_DONE",  "2014-08-01 00:00:00", "2014-08-10 00:00:00");
        ContestPhase gCp2 = createContestPhase(globalContest, 16, true, "PROMOTE_DONE",  "2014-08-10 00:00:01", "2014-08-14 00:00:00");
        ContestPhase gCp3 = createContestPhase(globalContest, 18, false, "PROMOTE_DONE", "2014-08-14 00:00:01", "2014-08-16 00:00:00");
        ContestPhase gCp4 = createContestPhase(globalContest, 19, true, "PROMOTE_DONE",  "2014-08-16 00:00:01", "2014-08-20 00:00:00");
        ContestPhase gCp5 = createContestPhase(globalContest, 15, false, "PROMOTE_DONE", "2014-08-20 00:00:01", "2015-09-24 00:00:00");
        //this contest did not expire yet.
        ContestPhase gCp6 = createContestPhase(globalContest, 17, false, "", "2015-09-24 00:00:01", null);

        //create 10 proposals, authored by user i, advance half of them to the last phase.
        globalProposals = new ArrayList<Proposal>();
        for (int i = 0; i < 10; i++) {
            Proposal proposal = proposalLocalService.create(users.get(i).getUserId(), gCp1.getContestPhasePK());
            globalProposals.add(proposal);

            //copy to first phases
            copyProposalToPhase(proposal, gCp2);
            //copy half of the proposals to other phases
            if (i > 4) {
                copyProposalToPhase(proposal, gCp3);
                copyProposalToPhase(proposal, gCp4);
                copyProposalToPhase(proposal, gCp5);
            }
        }

        //create side contests
        sideProposals = new ArrayList<Proposal>();
        sideContests = new ArrayList<Contest>();
        for (int i = 0; i < 2; i++) {
            Contest sideContest = contestLocalService.createNewContest(adminId, "Test-Side-Contest-"+(i+1));
            sideContest.setPoints(0);
            sideContest.setDefaultParentPointType(6);
            //create phases
            ContestPhase sCp1 = createContestPhase(sideContest, 1, false, "PROMOTE_DONE", "2014-08-01 00:00:00", "2014-08-10 00:00:00");
            ContestPhase sCp2 = createContestPhase(sideContest, 16, true, "PROMOTE_DONE", "2014-08-10 00:00:01", "2014-08-14 00:00:00");
            ContestPhase sCp3 = createContestPhase(sideContest, 18, false, "PROMOTE_DONE", "2014-08-14 00:00:01", "2014-08-16 00:00:00");
            ContestPhase sCp4 = createContestPhase(sideContest, 19, true, "PROMOTE_DONE", "2014-08-16 00:00:01", "2014-08-20 00:00:00");
            ContestPhase sCp5 = createContestPhase(sideContest, 15, false, "PROMOTE_DONE", "2014-08-20 00:00:01", "2015-09-24 00:00:00");
            //this contest did not expire yet.
            ContestPhase sCp6 = createContestPhase(sideContest, 17, false, "", "2015-09-24 00:00:01", null);

            for (int j = 0; j < 3; j++) {
                Proposal proposal = proposalLocalService.create(users.get(j+(i*3)).getUserId(), sCp1.getContestPhasePK());
                sideProposals.add(proposal);

                //copy to first phases
                copyProposalToPhase(proposal, sCp2);
            }
            sideContests.add(sideContest);
        }

        //create some links from global proposals to side proposals
        String sectionText = "These are the subproposals we link to:\n"+
            "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/"+sideContests.get(0).getContestPK()+"/planId/"+sideProposals.get(0).getProposalId()+"\n\n"+
            "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/"+sideContests.get(0).getContestPK()+"/planId/"+sideProposals.get(1).getProposalId()+"\n\n"+
            "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/"+sideContests.get(1).getContestPK()+"/planId/"+sideProposals.get(4).getProposalId()+" and "+
            "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/"+sideContests.get(1).getContestPK()+"/planId/"+sideProposals.get(5).getProposalId()+" and "+
            "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/"+globalContest.getContestPK()+"/planId/"+globalProposals.get(3).getProposalId();
        //1300907 is the sub proposal plan section definition
        proposalLocalService.setAttribute(globalProposals.get(6).getAuthorId(), globalProposals.get(6).getProposalId(), ProposalAttributeKeys.SECTION, 1300907L, sectionText);
        String sectionText2 = "These are the subproposals we link to:\n"+
                "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/"+sideContests.get(0).getContestPK()+"/planId/"+sideProposals.get(2).getProposalId()+"\n\n"+
                "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/"+sideContests.get(0).getContestPK()+"/planId/"+sideProposals.get(1).getProposalId()+"\n\n"+
                "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/"+sideContests.get(1).getContestPK()+"/planId/"+sideProposals.get(3).getProposalId()+" and "+
                "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/"+globalContest.getContestPK()+"/planId/"+globalProposals.get(6).getProposalId();
        proposalLocalService.setAttribute(globalProposals.get(7).getAuthorId(), globalProposals.get(7).getProposalId(), ProposalAttributeKeys.SECTION, 1300907L, sectionText2);

        //add team members to some proposals
        userLocalService.addGroupUsers(globalProposals.get(6).getGroupId(), new long[] {users.get(2).getUserId()});
        userLocalService.addGroupUsers(globalProposals.get(6).getGroupId(), new long[] {users.get(4).getUserId()});
        userLocalService.addGroupUsers(globalProposals.get(7).getGroupId(), new long[] {users.get(1).getUserId()});
        userLocalService.addGroupUsers(sideProposals.get(0).getGroupId(), new long[] {users.get(1).getUserId()});
        userLocalService.addGroupUsers(sideProposals.get(1).getGroupId(), new long[] {users.get(2).getUserId()});
        userLocalService.addGroupUsers(sideProposals.get(4).getGroupId(), new long[] {users.get(2).getUserId()});



        //set some point distributions
        //GLOBAL PROPOSAL 6
        //TEAM MEMBERS
        pointsDistributionConfigurationService.addDistributionConfiguration(
                globalProposals.get(6).getProposalId(),
                2, //team member point type
                users.get(6).getUserId(),
                0L,
                0.30,
                users.get(6).getUserId()
        );
        pointsDistributionConfigurationService.addDistributionConfiguration(
                globalProposals.get(6).getProposalId(),
                2, //team member point type
                users.get(2).getUserId(),
                0L,
                0.50,
                users.get(6).getUserId()
        );
        pointsDistributionConfigurationService.addDistributionConfiguration(
                globalProposals.get(6).getProposalId(),
                2, //team member point type
                users.get(4).getUserId(),
                0L,
                0.20,
                users.get(6).getUserId()
        );
        //ANY NON-TEAM-MEMBER
        pointsDistributionConfigurationService.addDistributionConfiguration(
                globalProposals.get(6).getProposalId(),
                5, //any non-team-member
                users.get(8).getUserId(),
                0L,
                1,
                users.get(6).getUserId()
        );
        //GLOBAL PROPOSAL 7
        //TEAM MEMBERS
        pointsDistributionConfigurationService.addDistributionConfiguration(
                globalProposals.get(7).getProposalId(),
                2, //team member point type
                users.get(7).getUserId(),
                0L,
                0.60,
                users.get(7).getUserId()
        );
        pointsDistributionConfigurationService.addDistributionConfiguration(
                globalProposals.get(7).getProposalId(),
                2, //team member point type
                users.get(1).getUserId(),
                0L,
                0.40,
                users.get(7).getUserId()
        );
        //ANY NON-TEAM-MEMBER
        pointsDistributionConfigurationService.addDistributionConfiguration(
                globalProposals.get(7).getProposalId(),
                5, //any non-team-member
                users.get(8).getUserId(),
                0L,
                1,
                users.get(7).getUserId()
        );

        //SIDE PROPOSAL 0
        pointsDistributionConfigurationService.addDistributionConfiguration(
                sideProposals.get(0).getProposalId(),
                7, //any team member
                users.get(0).getUserId(),
                0L,
                0.50,
                users.get(0).getUserId()
        );
        pointsDistributionConfigurationService.addDistributionConfiguration(
                sideProposals.get(0).getProposalId(),
                7, //any team member
                users.get(1).getUserId(),
                0L,
                0.50,
                users.get(0).getUserId()
        );
        pointsDistributionConfigurationService.addDistributionConfiguration(
                sideProposals.get(0).getProposalId(),
                8, //any non-team member
                users.get(4).getUserId(),
                0L,
                1,
                users.get(0).getUserId()
        );
        //SIDE PROPOSAL 1
        pointsDistributionConfigurationService.addDistributionConfiguration(
                sideProposals.get(1).getProposalId(),
                7, //any team member
                users.get(1).getUserId(),
                0L,
                0.10,
                users.get(0).getUserId()
        );
        pointsDistributionConfigurationService.addDistributionConfiguration(
                sideProposals.get(1).getProposalId(),
                7, //any team member
                users.get(2).getUserId(),
                0L,
                0.90,
                users.get(0).getUserId()
        );
        pointsDistributionConfigurationService.addDistributionConfiguration(
                sideProposals.get(0).getProposalId(),
                8, //any non-team member
                users.get(5).getUserId(),
                0L,
                1,
                users.get(0).getUserId()
        );



        //run the hypothetical points distribution now.
        pointsLocalService.distributePoints(globalContest.getContestPK());

        //assert the points in two ways:
        // First, make sure that the users have the right amount of hypothetical points
        // Second, assure that the individual Point data entries are correct
        List<Points> points = pointsLocalService.getPointses(0, Integer.MAX_VALUE);

        for (int i = 5; i < 10; i++) {
            if (i != 6 && i != 7) {
                this.assertBlankGlobalProposalPoints(i, points, pointsToBeDistributed, 0);
            }
        }
        this.assertProposal6Points(points, pointsToBeDistributed, 0);
        this.assertProposal7Points(points, pointsToBeDistributed, 0);
        //all points should be popped and verified after this.
        assertTrue(points.isEmpty());
    }

    private void assertBlankGlobalProposalPoints(int proposalNumber, List<Points> points, double pointsToBeDistributed, long sourceId) {
        //pointsToBeDistributed * 20% * 90% go to the proposal author
        assertNotNull(popPointEntryInList(points, globalProposals.get(proposalNumber).getProposalId(), users.get(proposalNumber).getUserId(), sourceId, 0, pointsToBeDistributed*0.2*0.9));
        //no other points distributed
    }

    private void assertProposal7Points(List<Points> points, double pointsToBeDistributed, long sourceId) {
        //Proposal 7 TEAM
        assertNotNull(popPointEntryInList(points, globalProposals.get(7).getProposalId(), users.get(7).getUserId(), 0, 0, 1080));
        assertNotNull(popPointEntryInList(points, globalProposals.get(7).getProposalId(), users.get(1).getUserId(), 0, 0, 720));
        //Proposal 7 NON-TEAM
        assertNotNull(popPointEntryInList(points, globalProposals.get(7).getProposalId(), users.get(8).getUserId(), 0, 0, 200));
        //Proposal 7 SUB-PROPOSALS
        List<Long> childrenProposalIds = new ArrayList<Long>();
        childrenProposalIds.add(sideProposals.get(1).getProposalId());
        childrenProposalIds.add(sideProposals.get(2).getProposalId());
        childrenProposalIds.add(sideProposals.get(3).getProposalId());
        childrenProposalIds.add(globalProposals.get(6).getProposalId());

        for (int i = 0; i < 4; i++) {
            Points subProposalSourcePoints = popPointEntryInList(points, globalProposals.get(7).getProposalId(), 0, 0, 0, 2000);
            assertNotNull(subProposalSourcePoints);
            Long proposalId = getProposalIdByPointsSourceIdInList(points, subProposalSourcePoints.getId());
            assertNotNull(proposalId);
            if (childrenProposalIds.contains(proposalId)) {
                childrenProposalIds.remove(proposalId);
            } else {
                throw new RuntimeException("Wrong sub-proposal "+proposalId);
            }

            //first referenced sub-proposal: 1
            if (proposalId.equals(sideProposals.get(1).getProposalId())) {
                assertNotNull(popPointEntryInList(points, proposalId, users.get(1).getUserId(), subProposalSourcePoints.getId(), 0, 160));
                assertNotNull(popPointEntryInList(points, proposalId, users.get(2).getUserId(), subProposalSourcePoints.getId(), 0, 1440));
                assertNotNull(popPointEntryInList(points, proposalId, users.get(5).getUserId(), subProposalSourcePoints.getId(), 0, 400));
            }
            //second referenced sub-proposal: 2
            if (proposalId.equals(sideProposals.get(2).getProposalId())) {
                assertNotNull(popPointEntryInList(points, proposalId, users.get(2).getUserId(), subProposalSourcePoints.getId(), 0, 1600));
                //no other points distributed
            }
            if (proposalId.equals(sideProposals.get(3).getProposalId())) {
                assertNotNull(popPointEntryInList(points, proposalId, users.get(3).getUserId(), subProposalSourcePoints.getId(), 0, 1600));
            }
            if (proposalId.equals(sideProposals.get(6).getProposalId())) {
                this.assertProposal6Points(points, 2000, subProposalSourcePoints.getId());
            }
        }
        //all children proposals must have been traversed.
        assertTrue(childrenProposalIds.isEmpty());
    }


    private void assertProposal6Points(List<Points> points, double pointsToBeDistributed, long sourceId) {
        double teamPoints = pointsToBeDistributed*0.2*0.9;
        double nonTeamPoints = pointsToBeDistributed*0.2*0.1;

        //Proposal 6 TEAM
        assertNotNull(popPointEntryInList(points, globalProposals.get(6).getProposalId(), users.get(2).getUserId(), sourceId, 0, teamPoints*0.5));
        assertNotNull(popPointEntryInList(points, globalProposals.get(6).getProposalId(), users.get(4).getUserId(), sourceId, 0, teamPoints*0.2));
        assertNotNull(popPointEntryInList(points, globalProposals.get(6).getProposalId(), users.get(6).getUserId(), sourceId, 0, teamPoints*0.3));
        //Proposal 6 NON-TEAM
        assertNotNull(popPointEntryInList(points, globalProposals.get(6).getProposalId(), users.get(8).getUserId(), sourceId, 0, nonTeamPoints));
        //Proposal 6 SUB-PROPOSALS
        List<Long> childrenProposalIds = new ArrayList<Long>();
        childrenProposalIds.add(sideProposals.get(0).getProposalId());
        childrenProposalIds.add(sideProposals.get(1).getProposalId());
        childrenProposalIds.add(sideProposals.get(4).getProposalId());
        childrenProposalIds.add(sideProposals.get(5).getProposalId());
        childrenProposalIds.add(globalProposals.get(3).getProposalId());
        double subProposalPoints = (pointsToBeDistributed*0.8)/childrenProposalIds.size();

        for (int i = 0; i < 5; i++) {
            Points subProposalSourcePoints = popPointEntryInList(points, globalProposals.get(6).getProposalId(), 0, sourceId, 0, subProposalPoints);
            assertNotNull(subProposalSourcePoints);
            Long proposalId = getProposalIdByPointsSourceIdInList(points, subProposalSourcePoints.getId());
            assertNotNull(proposalId);
            if (childrenProposalIds.contains(proposalId)) {
                childrenProposalIds.remove(proposalId);
            } else {
                throw new RuntimeException("Wrong sub-proposal "+proposalId);
            }

            //first referenced sub-proposal: 0
            if (proposalId.equals(sideProposals.get(0).getProposalId())) {
                //640 points for user 0 (team)
                assertNotNull(popPointEntryInList(points, proposalId, users.get(0).getUserId(), subProposalSourcePoints.getId(), 0, subProposalPoints*0.4));
                //640 points for user 1 (team)
                assertNotNull(popPointEntryInList(points, proposalId, users.get(1).getUserId(), subProposalSourcePoints.getId(), 0, subProposalPoints*0.4));
                //320 points for user 4
                assertNotNull(popPointEntryInList(points, proposalId, users.get(4).getUserId(), subProposalSourcePoints.getId(), 0, subProposalPoints*0.2));
            }
            //second referenced sub-proposal: 1
            if (proposalId.equals(sideProposals.get(1).getProposalId())) {
                //128 points for user 1 (team)
                assertNotNull(popPointEntryInList(points, proposalId, users.get(1).getUserId(), subProposalSourcePoints.getId(), 0, subProposalPoints*0.8*0.1));
                //1152 points for user 2 (team)
                assertNotNull(popPointEntryInList(points, proposalId, users.get(2).getUserId(), subProposalSourcePoints.getId(), 0, subProposalPoints*0.8*0.9));
                //320 points for user 4
                assertNotNull(popPointEntryInList(points, proposalId, users.get(5).getUserId(), subProposalSourcePoints.getId(), 0, subProposalPoints*0.2));
            }
            //distributions for proposals 4 and 5 are not defined explicitly: the team points should be still distributed though!
            if (proposalId.equals(sideProposals.get(4).getProposalId())) {
                //640 points for user 4 (team)
                assertNotNull(popPointEntryInList(points, proposalId, users.get(4).getUserId(), subProposalSourcePoints.getId(), 0, subProposalPoints*0.4));
                //640 points for user 2 (team)
                assertNotNull(popPointEntryInList(points, proposalId, users.get(2).getUserId(), subProposalSourcePoints.getId(), 0, subProposalPoints*0.4));
                //no other points distributed
            }
            if (proposalId.equals(sideProposals.get(5).getProposalId())) {
                //1280 points for user 5 (team)
                assertNotNull(popPointEntryInList(points, proposalId, users.get(5).getUserId(), subProposalSourcePoints.getId(), 0, subProposalPoints*0.8));
                //no other points distributed
            }

            if (proposalId.equals(globalProposals.get(3).getProposalId())) {
                this.assertBlankGlobalProposalPoints(3, points, subProposalPoints, subProposalSourcePoints.getId());
            }
        }
        //all children proposals must have been traversed.
        assertTrue(childrenProposalIds.isEmpty());
    }

    private Long getProposalIdByPointsSourceIdInList(List<Points> points, long pointsSourceId) {
        for (Points p : points) {
            if (p.getPointsSourceId() == pointsSourceId) {
                return p.getProposalId();
            }
        }
        return null;
    }

    private Points popPointEntryInList(List<Points> points, long proposalId, long userId, long pointsSourceId, double materializedPoints, double hypotheticalPoints) {
        Points find = null;
        for (Points p : points) {
            if (p.getProposalId() == proposalId &&
                    p.getUserId() == userId &&
                    p.getMaterializedPoints() == materializedPoints &&
                    p.getHypotheticalPoints() == hypotheticalPoints &&
                    p.getPointsSourceId() == pointsSourceId) {
                find = p;
                break;
            }
        }
        if (find != null) points.remove(find);
        return find;
    }

    private void copyProposalToPhase(Proposal p, ContestPhase cp) throws SystemException {
        Proposal2Phase p2p = proposal2PhaseLocalService.create(p.getProposalId(), cp.getContestPhasePK());
        p2p.setVersionFrom(1);
        p2p.setVersionTo(1);
        proposal2PhaseLocalService.updateProposal2Phase(p2p);
    }

    private ContestPhase createContestPhase(Contest c, long type, boolean fellowScreeningActive, String autoPromote, String startDate, String endDate) throws SystemException, ParseException {
        ContestPhase cp = contestPhaseLocalService.createContestPhase(100000+contestPhaseIdCount++);
        cp.setContestPK(c.getContestPK());
        cp.setContestPhaseType(type);
        cp.setFellowScreeningActive(fellowScreeningActive);
        cp.setContestPhaseAutopromote(autoPromote);
        cp.setPhaseActiveOverride(false);
        cp.setPhaseInactiveOverride(false);
        cp.setPhaseStartDate(dateFormat.parse(startDate));
        if (endDate != null) {
            cp.setPhaseEndDate(dateFormat.parse(endDate));
        }
        contestPhaseLocalService.updateContestPhase(cp);

        return cp;
    }




}
