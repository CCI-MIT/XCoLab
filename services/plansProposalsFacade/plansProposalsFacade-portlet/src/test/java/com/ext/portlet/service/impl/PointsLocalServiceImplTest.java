package com.ext.portlet.service.impl;


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
	}

    @Test
    public void testGlobalContestHypotheticalPoints() throws SystemException, PortalException, ParseException {
        long authorId = 10144L;
        PermissionCheckerUtil.setThreadValues(UserLocalServiceUtil.getUser(authorId));


        //create contest
        Contest c = contestLocalService.createNewContest(authorId, "Test-Global-Contest");
        //create phases
        ContestPhase cp1 = createContestPhase(c, 1, false, "PROMOTE_DONE",  "2014-08-01 00:00:00", "2014-08-10 00:00:00");
        ContestPhase cp2 = createContestPhase(c, 16, true, "PROMOTE_DONE",  "2014-08-10 00:00:01", "2014-08-14 00:00:00");
        ContestPhase cp3 = createContestPhase(c, 18, false, "PROMOTE_DONE", "2014-08-14 00:00:01", "2014-08-16 00:00:00");
        ContestPhase cp4 = createContestPhase(c, 19, true, "PROMOTE_DONE",  "2014-08-16 00:00:01", "2014-08-20 00:00:00");
        ContestPhase cp5 = createContestPhase(c, 15, false, "PROMOTE_DONE", "2014-08-20 00:00:01", "2015-09-24 00:00:00");
        //this contest did not expire yet.
        ContestPhase cp6 = createContestPhase(c, 17, false, "", "2015-09-24 00:00:01", null);

        //create 10 proposals, advance half of them to the last phase.
        List<Proposal> proposals = new ArrayList<Proposal>();
        for (int i = 0; i < 10; i++) {
            Proposal p = proposalLocalService.create(authorId, cp1.getContestPhasePK());
            proposals.add(p);
        }

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
