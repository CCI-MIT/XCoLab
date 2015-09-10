package com.ext.portlet.service.impl;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PointType;
import com.ext.portlet.service.ContestLocalService;
import com.ext.portlet.service.ContestPhaseLocalService;
import com.ext.portlet.service.ContestPhaseTypeLocalService;
import com.ext.portlet.service.ContestTeamMemberLocalService;
import com.ext.portlet.service.PlanSectionDefinitionLocalService;
import com.ext.portlet.service.PointDistributionTargetLocalService;
import com.ext.portlet.service.PointTypeLocalService;
import com.ext.portlet.service.PointsDistributionConfigurationLocalService;
import com.ext.portlet.service.PointsLocalService;
import com.ext.portlet.service.Proposal2PhaseLocalService;
import com.ext.portlet.service.ProposalAttributeLocalService;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalService;
import com.ext.portlet.service.ProposalLocalService;
import com.liferay.portal.dao.jdbc.DataSourceFactoryImpl;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.permission.PermissionCheckerUtil;
import com.liferay.portal.service.ContactLocalService;
import com.liferay.portal.service.MockContextProvider;
import com.liferay.portal.service.PasswordPolicyLocalService;
import com.liferay.portal.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.spring.aop.ServiceBeanAutoProxyCreator;
import com.liferay.portal.util.InitUtil;
import org.junit.BeforeClass;

/**
 * Created by manu on 17/09/14.
 */
public class XCoLabTest {
    protected static ContestPhaseTypeLocalService contestPhaseTypeLocalService;
    protected static ContactLocalService contactLocalService;
    protected static UserLocalService userLocalService;
    protected static PasswordPolicyLocalService passwordPolicyLocalService;
    protected static ContestLocalService contestLocalService;
    protected static ContestPhaseLocalService contestPhaseLocalService;
    protected static ContestTeamMemberLocalService contestTeamMemberLocalService;
    protected static ProposalLocalService proposalLocalService;
    protected static ProposalAttributeLocalService proposalAttributeLocalService;
    protected static Proposal2PhaseLocalService proposal2PhaseLocalService;
    protected static ProposalContestPhaseAttributeLocalService proposalContestPhaseAttributeLocalService;
    protected static PointTypeLocalService pointTypeLocalService;
    protected static PointsLocalService pointsLocalService;
    protected static PointsDistributionConfigurationLocalService pointsDistributionConfigurationService;
    protected static PointDistributionTargetLocalService pointDistributionTargetService;
    protected static PlanSectionDefinitionLocalService planSectionDefinitionLocalService;
    protected static long adminId = 10144L;

    @BeforeClass
    public static void beforeTest() throws Exception {
        new DataSourceFactoryImpl();

        new ServiceBeanAutoProxyCreator();
        new MockContextProvider();
        System.out.println("Before init");

        InitUtil.initWithSpring();
        System.out.println("after init with spring before ctx");
        ResourceActionLocalServiceUtil.checkResourceActions();
        CompanyThreadLocal.setCompanyId(10112l);

        System.out.println("initialized?");
        contestPhaseTypeLocalService = (ContestPhaseTypeLocalService) PortalBeanLocatorUtil.locate(ContestPhaseTypeLocalService.class.getName());
        passwordPolicyLocalService = (PasswordPolicyLocalService) PortalBeanLocatorUtil.locate(PasswordPolicyLocalService.class.getName());
        contactLocalService = (ContactLocalService) PortalBeanLocatorUtil.locate(ContactLocalService.class.getName());
        userLocalService = (UserLocalService) PortalBeanLocatorUtil.locate(UserLocalService.class.getName());
        contestLocalService = (ContestLocalService) PortalBeanLocatorUtil.locate(ContestLocalService.class.getName());
        contestTeamMemberLocalService = (ContestTeamMemberLocalService) PortalBeanLocatorUtil.locate(ContestTeamMemberLocalService.class.getName());
        contestPhaseLocalService = (ContestPhaseLocalService) PortalBeanLocatorUtil.locate(ContestPhaseLocalService.class.getName());
        proposalLocalService = (ProposalLocalService) PortalBeanLocatorUtil.locate(ProposalLocalService.class.getName());
        proposalAttributeLocalService = (ProposalAttributeLocalService) PortalBeanLocatorUtil.locate(ProposalAttributeLocalService.class.getName());
        proposal2PhaseLocalService = (Proposal2PhaseLocalService) PortalBeanLocatorUtil.locate(Proposal2PhaseLocalService.class.getName());
        proposalContestPhaseAttributeLocalService = (ProposalContestPhaseAttributeLocalService) PortalBeanLocatorUtil.locate(ProposalContestPhaseAttributeLocalService.class.getName());
        pointTypeLocalService = (PointTypeLocalService) PortalBeanLocatorUtil.locate(PointTypeLocalService.class.getName());
        pointsLocalService = (PointsLocalService) PortalBeanLocatorUtil.locate(PointsLocalService.class.getName());
        pointsDistributionConfigurationService = (PointsDistributionConfigurationLocalService) PortalBeanLocatorUtil.locate(PointsDistributionConfigurationLocalService.class.getName());
        pointDistributionTargetService = (PointDistributionTargetLocalService) PortalBeanLocatorUtil.locate(PointDistributionTargetLocalService.class.getName());
        planSectionDefinitionLocalService = (PlanSectionDefinitionLocalService) PortalBeanLocatorUtil.locate(PlanSectionDefinitionLocalService.class.getName());

        PermissionCheckerUtil.setThreadValues(UserLocalServiceUtil.getUser(adminId));
    }

    protected User createUser(long id) throws SystemException {
        contactLocalService.addContact(contactLocalService.createContact(id));

        User user = userLocalService.createUser(id);
        user.setScreenName("thurner" + id);
        user.setEmailAddress("john.doe+" + id + "@example.com");
        user.setCompanyId(10112L);
        user.setPasswordUnencrypted("test11");
        user.setPasswordEncrypted(false);
        user.setContactId(id);
        userLocalService.addUser(user);

        return user;
    }

    protected void setupBasicDataset() throws SystemException, PortalException {
        //create default password policy
        passwordPolicyLocalService.addPasswordPolicy(10115L, true, "Default Password Policy", "Default Password Policy", true, false, 0L, false, true, 6, 0, 6, 0, 0, 0, "", false, 0, false, 8640000L, 0L, 0, false, 3, 600L, 0L, 0L, new ServiceContext());

        //plan section for subproposal list
        PlanSectionDefinition psd = planSectionDefinitionLocalService.createPlanSectionDefinition(1300907);
        psd.setType("PROPOSAL_LIST_TEXT_REFERENCE");
        psd.setAdminTitle("Sub-proposals Global");
        psd.setTitle("Sub-proposals");
        psd.setCharacterLimit(7000);
        psd.setFocusAreaId(0);
        psd.setLocked(false);
        planSectionDefinitionLocalService.addPlanSectionDefinition(psd);

        this.createPointTypes();
        this.createContestPhaseTypes();
    }

    protected void createContestPhaseTypes() throws SystemException {
        ContestPhaseType cpt = contestPhaseTypeLocalService.createContestPhaseType(1);
        cpt.setName("Proposal creation");
        cpt.setStatus("OPEN_FOR_SUBMISSION");
        contestPhaseTypeLocalService.updateContestPhaseType(cpt);

        cpt = contestPhaseTypeLocalService.createContestPhaseType(15);
        cpt.setName("Winners selection");
        cpt.setStatus("VOTING");
        contestPhaseTypeLocalService.updateContestPhaseType(cpt);

        cpt = contestPhaseTypeLocalService.createContestPhaseType(16);
        cpt.setName("Semi-Finalist selection");
        cpt.setStatus("CLOSED");
        contestPhaseTypeLocalService.updateContestPhaseType(cpt);

        cpt = contestPhaseTypeLocalService.createContestPhaseType(17);
        cpt.setName("Winners awarded");
        cpt.setStatus("CLOSED");
        contestPhaseTypeLocalService.updateContestPhaseType(cpt);

        cpt = contestPhaseTypeLocalService.createContestPhaseType(18);
        cpt.setName("Proposal revisions");
        cpt.setStatus("OPEN_FOR_EDIT");
        contestPhaseTypeLocalService.updateContestPhaseType(cpt);

        cpt = contestPhaseTypeLocalService.createContestPhaseType(19);
        cpt.setName("Finalist selection");
        cpt.setStatus("CLOSED");
        contestPhaseTypeLocalService.updateContestPhaseType(cpt);
    }

    protected void createPointTypes() throws SystemException {
        PointType pt = pointTypeLocalService.createPointType(1);
        pt.setParentPointTypeId(0);
        pt.setPercentageOfParent(1);
        pt.setDistributionStrategy("DEFINED_BY_CHILDREN_PERCENTAGES");
        pt.setReceiverLimitationStrategy("ANY_USER");
        pt.setSort(1);
        pointTypeLocalService.addPointType(pt);

        pt = pointTypeLocalService.createPointType(2);
        pt.setParentPointTypeId(3);
        pt.setPercentageOfParent(0.9);
        pt.setDistributionStrategy("USER_DEFINED");
        pt.setReceiverLimitationStrategy("ANY_TEAM_MEMBER");
        pt.setSort(2);
        pointTypeLocalService.addPointType(pt);

        pt = pointTypeLocalService.createPointType(3);
        pt.setParentPointTypeId(1);
        pt.setPercentageOfParent(0.2);
        pt.setDistributionStrategy("DEFINED_BY_CHILDREN_PERCENTAGES");
        pt.setReceiverLimitationStrategy("ANY_USER");
        pt.setSort(3);
        pointTypeLocalService.addPointType(pt);

        pt = pointTypeLocalService.createPointType(4);
        pt.setParentPointTypeId(1);
        pt.setPercentageOfParent(0.8);
        pt.setDistributionStrategy("EQUAL_DIVISION");
        pt.setReceiverLimitationStrategy("SUBPROPOSALS");
        pt.setSort(4);
        pointTypeLocalService.addPointType(pt);

        pt = pointTypeLocalService.createPointType(5);
        pt.setParentPointTypeId(3);
        pt.setPercentageOfParent(0.1);
        pt.setDistributionStrategy("USER_DEFINED");
        pt.setReceiverLimitationStrategy("ANY_NON_TEAM_MEMBER");
        pt.setSort(5);
        pointTypeLocalService.addPointType(pt);

        pt = pointTypeLocalService.createPointType(6);
        pt.setParentPointTypeId(0);
        pt.setPercentageOfParent(1);
        pt.setDistributionStrategy("DEFINED_BY_CHILDREN_PERCENTAGES");
        pt.setReceiverLimitationStrategy("ANY_USER");
        pt.setSort(1);
        pointTypeLocalService.addPointType(pt);

        pt = pointTypeLocalService.createPointType(7);
        pt.setParentPointTypeId(6);
        pt.setPercentageOfParent(0.8);
        pt.setDistributionStrategy("USER_DEFINED");
        pt.setReceiverLimitationStrategy("ANY_TEAM_MEMBER");
        pt.setSort(2);
        pointTypeLocalService.addPointType(pt);

        pt = pointTypeLocalService.createPointType(8);
        pt.setParentPointTypeId(6);
        pt.setPercentageOfParent(0.2);
        pt.setDistributionStrategy("USER_DEFINED");
        pt.setReceiverLimitationStrategy("ANY_NON_TEAM_MEMBER");
        pt.setSort(3);
        pointTypeLocalService.addPointType(pt);
    }

}
