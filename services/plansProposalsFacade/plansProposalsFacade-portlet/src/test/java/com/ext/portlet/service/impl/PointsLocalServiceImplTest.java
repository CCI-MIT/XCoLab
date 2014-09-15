package com.ext.portlet.service.impl;


import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.ext.portlet.service.impl.mock.UserLocalServiceMock;
import com.liferay.portal.dao.jdbc.DataSourceFactoryImpl;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.permission.PermissionCheckerUtil;
import com.liferay.portal.service.*;
import com.liferay.portal.spring.aop.ServiceBeanAutoProxyCreator;
import com.liferay.portal.util.InitUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.xcolab.services.EventBusService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PointsLocalServiceImplTest {
	private static UserLocalService userLocalService;
	private static PasswordPolicyLocalService passwordPolicyLocalService;
	private static ContestLocalService contestLocalService;
	private static ContestPhaseLocalService contestPhaseLocalService;
	private static ProposalLocalService proposalLocalService;
	private static Proposal2PhaseLocalService proposal2PhaseLocalService;
    private static ProposalContestPhaseAttributeLocalService proposalContestPhaseAttributeLocalService;
	private static PointsLocalService pointsLocalService;
	private static PointsDistributionConfigurationLocalService pointsDistributionConfigurationService;
	private static PlanSectionDefinitionLocalService planSectionDefinitionLocalService;

    private static SimpleDateFormat dateFormat;
    private static int contestPhaseIdCount = 0;

	private static AbstractApplicationContext ctx;
	private Random rand = new Random();

    @BeanReference(type = EventBusService.class)
    private EventBusService eventBus;
	
	@BeforeClass
	public static void beforeTest() throws Exception {
        dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s", Locale.ENGLISH);
	    
	    new DataSourceFactoryImpl();
	    
	    new ServiceBeanAutoProxyCreator();
	    new MockContextProvider();
	    System.out.println("Before init");
	    
	    InitUtil.initWithSpring();
	    System.out.println("after init with spring before ctx");
        ResourceActionLocalServiceUtil.checkResourceActions();
        CompanyThreadLocal.setCompanyId(10112l);

        System.out.println("initialized?");
        passwordPolicyLocalService = (PasswordPolicyLocalService) PortalBeanLocatorUtil.locate(PasswordPolicyLocalService.class.getName());
        userLocalService = (UserLocalService) PortalBeanLocatorUtil.locate(UserLocalService.class.getName());
        contestLocalService = (ContestLocalService) PortalBeanLocatorUtil.locate(ContestLocalService.class.getName());
        contestPhaseLocalService = (ContestPhaseLocalService) PortalBeanLocatorUtil.locate(ContestPhaseLocalService.class.getName());
        proposalLocalService = (ProposalLocalService) PortalBeanLocatorUtil.locate(ProposalLocalService.class.getName());
        proposal2PhaseLocalService = (Proposal2PhaseLocalService) PortalBeanLocatorUtil.locate(Proposal2PhaseLocalService.class.getName());
        proposalContestPhaseAttributeLocalService = (ProposalContestPhaseAttributeLocalService) PortalBeanLocatorUtil.locate(ProposalContestPhaseAttributeLocalService.class.getName());
        pointsLocalService = (PointsLocalService) PortalBeanLocatorUtil.locate(PointsLocalService.class.getName());
        pointsDistributionConfigurationService = (PointsDistributionConfigurationLocalService) PortalBeanLocatorUtil.locate(PointsDistributionConfigurationLocalService.class.getName());
        planSectionDefinitionLocalService = (PlanSectionDefinitionLocalService) PortalBeanLocatorUtil.locate(PlanSectionDefinitionLocalService.class.getName());
	}

    @Test
    public void testGlobalContestHypotheticalPoints() throws SystemException, PortalException, ParseException {
        long adminId = 10144L;
        PermissionCheckerUtil.setThreadValues(UserLocalServiceUtil.getUser(adminId));

        //create default password policy
        passwordPolicyLocalService.addPasswordPolicy(10115L, true, "Default Password Policy", "Default Password Policy", true, false, 0L, false, true, 6, 0, 6, 0, 0, 0, "", false, 0, false, 8640000L, 0L, 0, false, 3, 600L, 0L, 0L, new ServiceContext());

        //create 10 different authors
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = userLocalService.createUser(50000+i);
            user.setScreenName("thurner"+i);
            user.setEmailAddress("john.doe+"+i+"@example.com");
            user.setCompanyId(10112L);
            user.setPasswordUnencrypted("test11");
            user.setPasswordEncrypted(false);
            user.setPasswordEncrypted(false);

            userLocalService.addUser(user);

            /*User user = userLocalService.addUserWithWorkflow(
                    10144L, 10112L, false,
                    "test11",
                    "test11", false,
                    "thurner"+i,
                    "john.doe+"+i+"@example.com", 0L, "",
                    Locale.ENGLISH,
                    "John", "",
                    "Doe", 0, 0, true, 1, 1,
                    1970, "", new long[] {}, new long[] {},
                    new long[] {}, new long[] {}, true, new ServiceContext());
            users.add(user);*/
        }


        //create global contest
        Contest globalContest = contestLocalService.createNewContest(adminId, "Test-Global-Contest");
        globalContest.setPoints(10000);
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

        //create 10 proposals, advance half of them to the last phase.
        List<Proposal> globalProposals = new ArrayList<Proposal>();
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
        List<Proposal> sideProposals = new ArrayList<Proposal>();
        List<Contest> sideContests = new ArrayList<Contest>();
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

            for (int j = 0; j < 3; i++) {
                Proposal proposal = proposalLocalService.create(users.get(j+(i*3)).getUserId(), sCp1.getContestPhasePK());
                sideProposals.add(proposal);

                //copy to first phases
                copyProposalToPhase(proposal, sCp2);
            }
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
        //ANY NON-TEAMMEMBER
        pointsDistributionConfigurationService.addDistributionConfiguration(
                globalProposals.get(6).getProposalId(),
                5, //any non-teammember
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
        //ANY NON-TEAMMEMBER
        pointsDistributionConfigurationService.addDistributionConfiguration(
                globalProposals.get(7).getProposalId(),
                5, //any non-teammember
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
                0.50,
                users.get(0).getUserId()
        );
        pointsDistributionConfigurationService.addDistributionConfiguration(
                sideProposals.get(1).getProposalId(),
                7, //any team member
                users.get(2).getUserId(),
                0L,
                0.50,
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

        //assert the points

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
