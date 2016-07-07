package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.NoSuchContestPhaseException;
import com.ext.portlet.NoSuchContestPhaseTypeException;
import com.ext.portlet.NoSuchContestTypeException;
import com.ext.portlet.NoSuchPlanSectionDefinitionException;
import com.ext.portlet.NoSuchPointTypeException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PointType;
import com.ext.portlet.service.ContestLocalService;
import com.ext.portlet.service.ContestPhaseLocalService;
import com.ext.portlet.service.ContestPhaseTypeLocalService;
import com.ext.portlet.service.ContestTeamMemberLocalService;
import com.ext.portlet.service.ContestTypeLocalService;
import com.ext.portlet.service.PlanSectionDefinitionLocalService;
import com.ext.portlet.service.PointDistributionTargetLocalService;
import com.ext.portlet.service.PointTypeLocalService;
import com.ext.portlet.service.PointsDistributionConfigurationLocalService;
import com.ext.portlet.service.PointsLocalService;
import com.ext.portlet.service.Proposal2PhaseLocalService;
import com.ext.portlet.service.ProposalAttributeLocalService;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalService;
import com.ext.portlet.service.ProposalLocalService;
import com.ext.portlet.service.persistence.PointTypePersistence;
import com.liferay.portal.NoSuchContactException;
import com.liferay.portal.NoSuchPasswordPolicyException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.dao.jdbc.DataSourceFactoryImpl;
import com.liferay.portal.kernel.bean.BeanLocatorException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.permission.PermissionCheckerUtil;
import com.liferay.portal.service.ContactLocalService;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.MockContextProvider;
import com.liferay.portal.service.PasswordPolicyLocalService;
import com.liferay.portal.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.spring.aop.ServiceBeanAutoProxyCreator;
import com.liferay.portal.util.InitUtil;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manu on 17/09/14.
 */
public class XCoLabTest {
    protected static ContestPhaseTypeLocalService contestPhaseTypeLocalService;
    protected static ContactLocalService contactLocalService;
    protected static UserLocalService userLocalService;
    protected static GroupLocalService groupLocalService;
    protected static PasswordPolicyLocalService passwordPolicyLocalService;
    protected static ContestLocalService contestLocalService;
    protected static ContestPhaseLocalService contestPhaseLocalService;
    protected static ContestTeamMemberLocalService contestTeamMemberLocalService;
    protected static ProposalLocalService proposalLocalService;
    protected static ProposalAttributeLocalService proposalAttributeLocalService;
    protected static Proposal2PhaseLocalService proposal2PhaseLocalService;
    protected static ProposalContestPhaseAttributeLocalService proposalContestPhaseAttributeLocalService;
    protected static PointTypeLocalService pointTypeLocalService;
    protected static PointTypePersistence pointTypePersistence;
    protected static PointsLocalService pointsLocalService;
    protected static PointsDistributionConfigurationLocalService pointsDistributionConfigurationService;
    protected static PointDistributionTargetLocalService pointDistributionTargetService;
    protected static PlanSectionDefinitionLocalService planSectionDefinitionLocalService;
    protected static ContestTypeLocalService contestTypeLocalService;
    protected static long adminId = 10144L;

    protected int numberOfCreatedUsers;

    @BeforeClass
    public static void beforeTest() throws SystemException, BeanLocatorException, PortalException {
        new DataSourceFactoryImpl();

        new ServiceBeanAutoProxyCreator();
        new MockContextProvider();
        System.out.println("Before init");

        InitUtil.initWithSpring();
        System.out.println("after init with spring before ctx");
        ResourceActionLocalServiceUtil.checkResourceActions();
        CompanyThreadLocal.setCompanyId(10112L);

        System.out.println("initialized?");
        contestPhaseTypeLocalService = (ContestPhaseTypeLocalService) PortalBeanLocatorUtil.locate(ContestPhaseTypeLocalService.class.getName());
        passwordPolicyLocalService = (PasswordPolicyLocalService) PortalBeanLocatorUtil.locate(PasswordPolicyLocalService.class.getName());
        contactLocalService = (ContactLocalService) PortalBeanLocatorUtil.locate(ContactLocalService.class.getName());
        userLocalService = (UserLocalService) PortalBeanLocatorUtil.locate(UserLocalService.class.getName());
        groupLocalService = (GroupLocalService) PortalBeanLocatorUtil.locate(GroupLocalService.class.getName());
        contestLocalService = (ContestLocalService) PortalBeanLocatorUtil.locate(ContestLocalService.class.getName());
        contestTeamMemberLocalService = (ContestTeamMemberLocalService) PortalBeanLocatorUtil.locate(ContestTeamMemberLocalService.class.getName());
        contestPhaseLocalService = (ContestPhaseLocalService) PortalBeanLocatorUtil.locate(ContestPhaseLocalService.class.getName());
        proposalLocalService = (ProposalLocalService) PortalBeanLocatorUtil.locate(ProposalLocalService.class.getName());
        proposalAttributeLocalService = (ProposalAttributeLocalService) PortalBeanLocatorUtil.locate(ProposalAttributeLocalService.class.getName());
        proposal2PhaseLocalService = (Proposal2PhaseLocalService) PortalBeanLocatorUtil.locate(Proposal2PhaseLocalService.class.getName());
        proposalContestPhaseAttributeLocalService = (ProposalContestPhaseAttributeLocalService) PortalBeanLocatorUtil.locate(ProposalContestPhaseAttributeLocalService.class.getName());
        pointTypeLocalService = (PointTypeLocalService) PortalBeanLocatorUtil.locate(PointTypeLocalService.class.getName());
        pointTypePersistence = (PointTypePersistence) PortalBeanLocatorUtil.locate(PointTypePersistence.class.getName());
        pointsLocalService = (PointsLocalService) PortalBeanLocatorUtil.locate(PointsLocalService.class.getName());
        pointsDistributionConfigurationService = (PointsDistributionConfigurationLocalService) PortalBeanLocatorUtil.locate(PointsDistributionConfigurationLocalService.class.getName());
        pointDistributionTargetService = (PointDistributionTargetLocalService) PortalBeanLocatorUtil.locate(PointDistributionTargetLocalService.class.getName());
        planSectionDefinitionLocalService = (PlanSectionDefinitionLocalService) PortalBeanLocatorUtil.locate(PlanSectionDefinitionLocalService.class.getName());
        contestTypeLocalService = (ContestTypeLocalService) PortalBeanLocatorUtil.locate(ContestTypeLocalService.class.getName());

        PermissionCheckerUtil.setThreadValues(UserLocalServiceUtil.getUser(adminId));

        setupBasicDataset();
    }

    /**
     * Create a set of new user model objects
     * @param numberOfUsers number of objects that should be created
     * @throws PortalException
     * @throws SystemException
     */
    protected List<User> createUsers(int numberOfUsers) throws PortalException, SystemException {
        numberOfCreatedUsers = numberOfUsers;
        List<User> users = new ArrayList<>(numberOfUsers);
        for (int i = 0; i < numberOfUsers; i++) {
            users.add(createUser(i+1));
        }

        return users;
    }

    private User createUser(long id) throws SystemException, PortalException {
        try {
            contactLocalService.getContact(id);
        } catch (NoSuchContactException e) {
            Contact createContact = contactLocalService.createContact(id);
            contactLocalService.addContact(createContact);    
        }
        
        try {
            return userLocalService.getUser(id);
        } catch (NoSuchUserException e) {
            User user = userLocalService.createUser(id);
            user.setScreenName("thurner" + id);
            user.setEmailAddress("john.doe+" + id + "@example.com");
            user.setCompanyId(10112L);
            user.setPasswordUnencrypted("test11");
            user.setPasswordEncrypted(false);
            user.setContactId(id);
            userLocalService.addUser(user);

            // Create a matching group for the new user
            groupLocalService.addGroup(adminId, 0, "com.liferay.portal.model.User", id, 0, "", "User", 0, true, 0,
                    "/" + user.getScreenName(), false, true, new ServiceContext());

            return user;
        }
    }

    /**
     * Clean up all previously created user and utility model objects
     * @throws SystemException
     * @throws PortalException
     */
    protected void deleteUsers() throws SystemException, PortalException {
        // Only delete the created users/contacts/groups from the method createUser
        for (Contact contact : contactLocalService.getContacts(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
            if (contact.getContactId() >= 1 && contact.getContactId() <= numberOfCreatedUsers) {
                contactLocalService.deleteContact(contact);
            }
        }
        for (User user : userLocalService.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
            if (user.getUserId() >= 1 && user.getUserId() <= numberOfCreatedUsers) {
                userLocalService.deleteUser(user);
            }
        }
        for (Group userGroup : groupLocalService.getGroups(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
            if (userGroup.getClassNameId() == 10038L && userGroup.getClassPK() >= 1 && userGroup.getClassPK() <= numberOfCreatedUsers) {
                groupLocalService.deleteGroup(userGroup);
            }
        }
    }

    protected static void setupBasicDataset() throws SystemException, PortalException {
        try {
            userLocalService.getUser(10115L);
        } catch (NoSuchUserException e1) {
            try {
                passwordPolicyLocalService.getPasswordPolicyByUserId(10115L);
            } catch (NoSuchPasswordPolicyException e2) {
                //create default password policy
                passwordPolicyLocalService.addPasswordPolicy(10115L, true, "Default Password Policy", "Default Password Policy", true, false, 0L, false, true, 6, 0, 6, 0, 0, 0, "", false, 0, false, 8640000L, 0L, 0, false, 3, 600L, 0L, 0L, new ServiceContext());
            }
        }

        try {
            planSectionDefinitionLocalService.getPlanSectionDefinition(1300907);
        } catch (NoSuchPlanSectionDefinitionException e) {
            //plan section for subproposal list
            PlanSectionDefinition psd = planSectionDefinitionLocalService.createPlanSectionDefinition(1300907);
            psd.setType("PROPOSAL_LIST_TEXT_REFERENCE");
            psd.setAdminTitle("Sub-proposals Global");
            psd.setTitle("Sub-proposals");
            psd.setCharacterLimit(7000);
            psd.setFocusAreaId(0);
            psd.setLocked(false);
            planSectionDefinitionLocalService.addPlanSectionDefinition(psd);
        }

        createPointTypes();
        createContestPhaseTypes();
        createContest();
    }

    private static void createContest() throws SystemException, PortalException {
        try {
            contestTypeLocalService.getContestType(1);
        } catch (NoSuchContestTypeException e) {
            ContestType contestType = contestTypeLocalService.createContestType(1);
            contestType.setProposalName("This is a sample contest type");
            contestTypeLocalService.updateContestType(contestType);
        }
        try {
            contestLocalService.getContest(1);
        } catch (NoSuchContestException e) {
            Contest contest = contestLocalService.createContest(1);
            contest.setContestPK(1);
            contest.setContestTypeId(1);
            contestLocalService.updateContest(contest);
        }
        try {
            contestPhaseLocalService.getContestPhase(1);
        } catch (NoSuchContestPhaseException e) {
            ContestPhase contestPhase = contestPhaseLocalService.createContestPhase(1);
            contestPhase.setContestPK(1);
            contestPhase.setContestPhaseType(1);
            contestPhaseLocalService.updateContestPhase(contestPhase);
        }
    }

    protected static void createContestPhaseTypes() throws PortalException, SystemException {
        createContestPhaseTypesOPENFORSUBMISSION();
        createContestPhaseTypesVOTING();
        createContestPhaseTypesCLOSEDSEMI();
        createContestPhaseTypesCLOSEDWINNERS();
        createContestPhaseTypesOPENFOREDIT();
        createContestPhaseTypesCLOSEDFINALIST();
    }

    private static void createContestPhaseTypesCLOSEDFINALIST() throws PortalException, SystemException {
        try {
            contestPhaseTypeLocalService.getContestPhaseType(19);
        } catch (NoSuchContestPhaseTypeException e) {
            ContestPhaseType cpt = contestPhaseTypeLocalService.createContestPhaseType(19);
            cpt.setName("Finalist selection");
            cpt.setStatus("CLOSED");
            contestPhaseTypeLocalService.updateContestPhaseType(cpt);
        }
    }

    private static void createContestPhaseTypesOPENFOREDIT() throws PortalException, SystemException {
        try {
            contestPhaseTypeLocalService.getContestPhaseType(18);
        } catch (NoSuchContestPhaseTypeException e) {
            ContestPhaseType cpt = contestPhaseTypeLocalService.createContestPhaseType(18);
            cpt.setName("Proposal revisions");
            cpt.setStatus("OPENFOREDIT");
            contestPhaseTypeLocalService.updateContestPhaseType(cpt);
        }
    }

    private static void createContestPhaseTypesCLOSEDWINNERS() throws PortalException, SystemException {
        try {
            contestPhaseTypeLocalService.getContestPhaseType(17);
        } catch (NoSuchContestPhaseTypeException e) {
            ContestPhaseType cpt = contestPhaseTypeLocalService.createContestPhaseType(17);
            cpt.setName("Winners awarded");
            cpt.setStatus("CLOSED");
            contestPhaseTypeLocalService.updateContestPhaseType(cpt);
        }
    }

    private static void createContestPhaseTypesCLOSEDSEMI() throws PortalException, SystemException {
        try {
            contestPhaseTypeLocalService.getContestPhaseType(16);
        } catch (NoSuchContestPhaseTypeException e) {
            ContestPhaseType cpt = contestPhaseTypeLocalService.createContestPhaseType(16);
            cpt.setName("Semi-Finalist selection");
            cpt.setStatus("CLOSED");
            contestPhaseTypeLocalService.updateContestPhaseType(cpt);
        }
    }

    private static void createContestPhaseTypesVOTING() throws PortalException, SystemException {
        try {
            contestPhaseTypeLocalService.getContestPhaseType(15);
        } catch (NoSuchContestPhaseTypeException e) {
            ContestPhaseType cpt = contestPhaseTypeLocalService.createContestPhaseType(15);
            cpt.setName("Winners selection");
            cpt.setStatus("VOTING");
            contestPhaseTypeLocalService.updateContestPhaseType(cpt);
        }
    }

    private static void createContestPhaseTypesOPENFORSUBMISSION() throws PortalException, SystemException {
        try {
            contestPhaseTypeLocalService.getContestPhaseType(1);
        } catch (NoSuchContestPhaseTypeException e) {
            ContestPhaseType cpt = contestPhaseTypeLocalService.createContestPhaseType(1);
            cpt.setName("Proposal creation");
            cpt.setStatus("OPEN_FOR_SUBMISSION");
            contestPhaseTypeLocalService.updateContestPhaseType(cpt);
        }
    }

    protected static void createPointTypes() throws PortalException, SystemException {
        createPointTypesDEFINEDBYCHILDREN();
        createPointTypesUSERDEFINED();
        createPointTypesDEFINEDBYCHILDREN2();
        createPointTypesEQUALDIVISION();
        createPointTypesUSERDEFINED2();
        createPointTypesDEFINEDBYCHILDREN3();
        createPointTypesUSERDEFINED3();
        createPointTypesUSERDEFINED4();
    }

    private static void createPointTypesUSERDEFINED4() throws PortalException, SystemException {
        try {
            pointTypeLocalService.getPointType(8);
        } catch (NoSuchPointTypeException e) {
            PointType pt = pointTypeLocalService.createPointType(8);
            pt.setParentPointTypeId(6);
            pt.setPercentageOfParent(0.2);
            pt.setDistributionStrategy("USER_DEFINED");
            pt.setReceiverLimitationStrategy("ANY_NON_TEAM_MEMBER");
            pt.setSort(3);
            pointTypeLocalService.addPointType(pt);
        }
    }

    private static void createPointTypesUSERDEFINED3() throws PortalException, SystemException {
        try {
            pointTypeLocalService.getPointType(7);
        } catch (NoSuchPointTypeException e) {
            PointType pt = pointTypeLocalService.createPointType(7);
            pt.setParentPointTypeId(6);
            pt.setPercentageOfParent(0.8);
            pt.setDistributionStrategy("USER_DEFINED");
            pt.setReceiverLimitationStrategy("ANY_TEAM_MEMBER");
            pt.setSort(2);
            pointTypeLocalService.addPointType(pt);
        }
    }

    private static void createPointTypesDEFINEDBYCHILDREN3() throws PortalException, SystemException {
        try {
            pointTypeLocalService.getPointType(6);
        } catch (NoSuchPointTypeException e) {
            PointType pt = pointTypeLocalService.createPointType(6);
            pt.setParentPointTypeId(0);
            pt.setPercentageOfParent(1);
            pt.setDistributionStrategy("DEFINED_BY_CHILDREN_PERCENTAGES");
            pt.setReceiverLimitationStrategy("ANY_USER");
            pt.setSort(1);
            pointTypeLocalService.addPointType(pt);
        }
    }

    private static void createPointTypesUSERDEFINED2() throws PortalException, SystemException {
        try {
            pointTypeLocalService.getPointType(5);
        } catch (NoSuchPointTypeException e) {
            PointType pt = pointTypeLocalService.createPointType(5);
            pt.setParentPointTypeId(3);
            pt.setPercentageOfParent(0.1);
            pt.setDistributionStrategy("USER_DEFINED");
            pt.setReceiverLimitationStrategy("ANY_NON_TEAM_MEMBER");
            pt.setSort(5);
            pointTypeLocalService.addPointType(pt);
        }
    }

    private static void createPointTypesEQUALDIVISION() throws PortalException, SystemException {
        try {
            pointTypeLocalService.getPointType(4);
        } catch (NoSuchPointTypeException e) {
            PointType pt = pointTypeLocalService.createPointType(4);
            pt.setParentPointTypeId(1);
            pt.setPercentageOfParent(0.8);
            pt.setDistributionStrategy("EQUAL_DIVISION");
            pt.setReceiverLimitationStrategy("SUBPROPOSALS");
            pt.setSort(4);
            pointTypeLocalService.addPointType(pt);
        }
    }

    private static void createPointTypesDEFINEDBYCHILDREN2() throws PortalException, SystemException {
        try {
            pointTypeLocalService.getPointType(3);
        } catch (NoSuchPointTypeException e) {
            PointType pt = pointTypeLocalService.createPointType(3);
            pt.setParentPointTypeId(1);
            pt.setPercentageOfParent(0.2);
            pt.setDistributionStrategy("DEFINED_BY_CHILDREN_PERCENTAGES");
            pt.setReceiverLimitationStrategy("ANY_USER");
            pt.setSort(3);
            pointTypeLocalService.addPointType(pt);
        }
    }

    private static void createPointTypesUSERDEFINED() throws PortalException, SystemException {
        try {
            pointTypeLocalService.getPointType(2);
        } catch (NoSuchPointTypeException e) {
            PointType pt = pointTypeLocalService.createPointType(2);
            pt.setParentPointTypeId(3);
            pt.setPercentageOfParent(0.9);
            pt.setDistributionStrategy("USER_DEFINED");
            pt.setReceiverLimitationStrategy("ANY_TEAM_MEMBER");
            pt.setSort(2);
            pointTypeLocalService.addPointType(pt);
        }
    }

    private static void createPointTypesDEFINEDBYCHILDREN() throws PortalException, SystemException {
        try {
            pointTypeLocalService.getPointType(1);
        } catch (NoSuchPointTypeException e) {
            PointType pt = pointTypeLocalService.createPointType(1);
            pt.setParentPointTypeId(0);
            pt.setPercentageOfParent(1);
            pt.setDistributionStrategy("DEFINED_BY_CHILDREN_PERCENTAGES");
            pt.setReceiverLimitationStrategy("ANY_USER");
            pt.setSort(1);
            pointTypeLocalService.addPointType(pt);
        }
    }

}
