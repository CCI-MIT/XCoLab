package com.ext.portlet.service.impl;


import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.liferay.portal.dao.jdbc.DataSourceFactoryImpl;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.permission.PermissionCheckerUtil;
import com.liferay.portal.service.MockContextProvider;
import com.liferay.portal.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
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
	private static ContestLocalService contestLocalService;
	private static ContestPhaseLocalService contestPhaseLocalService;
	private static ProposalLocalService proposalLocalService;
	private static Proposal2PhaseLocalService proposal2PhaseLocalService;
    private static ProposalContestPhaseAttributeLocalService proposalContestPhaseAttributeLocalService;
	private static PointsLocalService pointsLocalService;
	private static PlanSectionDefinitionLocalService planSectionDefinitionLocalService;

    private static SimpleDateFormat dateFormat;

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
        contestLocalService = (ContestLocalService) PortalBeanLocatorUtil.locate(ContestLocalService.class.getName());
        contestPhaseLocalService = (ContestPhaseLocalService) PortalBeanLocatorUtil.locate(ContestPhaseLocalService.class.getName());
        proposalLocalService = (ProposalLocalService) PortalBeanLocatorUtil.locate(ProposalLocalService.class.getName());
        proposal2PhaseLocalService = (Proposal2PhaseLocalService) PortalBeanLocatorUtil.locate(Proposal2PhaseLocalService.class.getName());
        proposalContestPhaseAttributeLocalService = (ProposalContestPhaseAttributeLocalService) PortalBeanLocatorUtil.locate(ProposalContestPhaseAttributeLocalService.class.getName());
        pointsLocalService = (PointsLocalService) PortalBeanLocatorUtil.locate(PointsLocalService.class.getName());
        planSectionDefinitionLocalService = (PlanSectionDefinitionLocalService) PortalBeanLocatorUtil.locate(PlanSectionDefinitionLocalService.class.getName());
	}

    @Test
    public void testGlobalContestHypotheticalPoints() throws SystemException, PortalException, ParseException {
        long authorId = 10144L;
        PermissionCheckerUtil.setThreadValues(UserLocalServiceUtil.getUser(authorId));

        //create global contest
        Contest globalContest = contestLocalService.createNewContest(authorId, "Test-Global-Contest");
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
            Proposal proposal = proposalLocalService.create(authorId, gCp1.getContestPhasePK());
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
            sideContests.add(contestLocalService.createNewContest(authorId, "Test-Side-Contest-"+(i+1)));
            //create phases
            ContestPhase sCp1 = createContestPhase(globalContest, 1, false, "PROMOTE_DONE", "2014-08-01 00:00:00", "2014-08-10 00:00:00");
            ContestPhase sCp2 = createContestPhase(globalContest, 16, true, "PROMOTE_DONE", "2014-08-10 00:00:01", "2014-08-14 00:00:00");
            ContestPhase sCp3 = createContestPhase(globalContest, 18, false, "PROMOTE_DONE", "2014-08-14 00:00:01", "2014-08-16 00:00:00");
            ContestPhase sCp4 = createContestPhase(globalContest, 19, true, "PROMOTE_DONE", "2014-08-16 00:00:01", "2014-08-20 00:00:00");
            ContestPhase sCp5 = createContestPhase(globalContest, 15, false, "PROMOTE_DONE", "2014-08-20 00:00:01", "2015-09-24 00:00:00");
            //this contest did not expire yet.
            ContestPhase sCp6 = createContestPhase(globalContest, 17, false, "", "2015-09-24 00:00:01", null);

            for (int j = 0; j < 3; i++) {
                Proposal proposal = proposalLocalService.create(authorId, gCp1.getContestPhasePK());
                sideProposals.add(proposal);

                //copy to first phases
                copyProposalToPhase(proposal, gCp2);
                //copy half of the proposals to other phases
                if (i > 4) {
                    copyProposalToPhase(proposal, gCp3);
                    copyProposalToPhase(proposal, gCp4);
                    copyProposalToPhase(proposal, gCp5);
                }
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
        proposalLocalService.setAttribute(authorId, globalProposals.get(6).getProposalId(), ProposalAttributeKeys.SECTION, 1300907L, sectionText);

        //set some point distributions

        //test the hypothetical points distribution now.

        //assert the points

    }

    private void copyProposalToPhase(Proposal p, ContestPhase cp) throws SystemException {
        Proposal2Phase p2p = proposal2PhaseLocalService.create(p.getProposalId(), cp.getContestPhasePK());
        p2p.setVersionFrom(1);
        p2p.setVersionTo(1);
        proposal2PhaseLocalService.updateProposal2Phase(p2p);
    }

    private ContestPhase createContestPhase(Contest c, long type, boolean fellowScreeningActive, String autoPromote, String startDate, String endDate) throws SystemException, ParseException {
        ContestPhase cp = contestPhaseLocalService.createContestPhase(100000);
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
