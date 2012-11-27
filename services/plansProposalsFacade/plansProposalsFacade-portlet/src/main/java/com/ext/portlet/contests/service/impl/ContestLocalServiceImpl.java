package com.ext.portlet.contests.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ext.portlet.contests.NoSuchContestException;
import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.contests.service.base.ContestLocalServiceBaseImpl;
import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the contest local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.contests.service.ContestLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.contests.service.base.ContestLocalServiceBaseImpl
 * @see com.ext.portlet.contests.service.ContestLocalServiceUtil
 */
public class ContestLocalServiceImpl extends ContestLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.contests.service.ContestLocalServiceUtil} to access the contest local service.
     */

    public static final String DEFAULT_GROUP_DESCRIPTION = "Group working on contest %s";
    private Random rand = new Random();
    
    public Contest getContestByActiveFlag(boolean contestActive) throws NoSuchContestException, SystemException {
        return contestPersistence.findBycontestActive(contestActive);
    }
    
    public Contest createNewContest(Long userId, String name) throws SystemException, PortalException {
        Contest c = ContestLocalServiceUtil.createContest(CounterLocalServiceUtil.increment(Contest.class.getName()));
        

        c.setAuthorId(userId);
        c.setContestName(name);
        c.setContestShortName(name);
        
        setGroupAndDiscussionForContest(c);
        
        c.store();
        
        return c;
    }
    
    public void updateContestGroupsAndDiscussions() throws SystemException, PortalException {
        for (Contest c: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            if (c.getGroupId() == null || c.getGroupId() == 0) {
                setGroupAndDiscussionForContest(c);
            }
        }
    }
    
    private void setGroupAndDiscussionForContest(Contest c) throws PortalException, SystemException {

        ServiceContext groupServiceContext = new ServiceContext();
        groupServiceContext.setUserId(c.getAuthorId());

        String groupName = c.getContestName() + "_" + System.currentTimeMillis() + "_" + rand.nextLong();
        Group group = null;
        
        /*
        group = GroupServiceUtil.add.addGroup("CONTEST:  " + c.getContestShortName(), String.format(DEFAULT_GROUP_DESCRIPTION, groupName),
                    GroupConstants.TYPE_COMMUNITY_RESTRICTED, null, true, groupServiceContext);
        */
        group = GroupLocalServiceUtil.addGroup(c.getAuthorId(), null, 0, "CONTEST:  " + c.getContestShortName(), 
                String.format(DEFAULT_GROUP_DESCRIPTION, groupName),  
                GroupConstants.TYPE_SITE_RESTRICTED, null, true, true, groupServiceContext);
        
        DiscussionCategoryGroup categoryGroup = DiscussionCategoryGroupLocalServiceUtil
                .createDiscussionCategoryGroup(c.getContestName() + " discussion");

        categoryGroup.setUrl("/web/guest/plans/-/plans/contestId/" + c.getContestPK() + "/page/discussion");
        
        categoryGroup.store();
        
        // set up permissions

        Long companyId = group.getCompanyId();
        Role owner = RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_OWNER);
        Role admin = RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_ADMINISTRATOR);
        Role member = RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_MEMBER);
        Role userRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
        Role guest = RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST);
        Role moderator = RoleLocalServiceUtil.getRole(companyId, "Moderator");

        String[] ownerActions = { DiscussionActions.ADMIN.name(), DiscussionActions.ADD_CATEGORY.name(),
                DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(),
                DiscussionActions.ADMIN_CATEGORIES.name(), DiscussionActions.ADMIN_MESSAGES.name(),
                DiscussionActions.ADD_COMMENT.name() };

        String[] adminActions = { DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name(), DiscussionActions.ADD_COMMENT.name() };

        String[] moderatorActions = { DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name(), DiscussionActions.ADD_COMMENT.name() };

        String[] memberActions = { DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADD_COMMENT.name() };

        String[] userActions = { DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(),
                DiscussionActions.ADD_COMMENT.name() };

        String[] guestActions = {};

        Map<Role, String[]> rolesActionsMap = new HashMap<Role, String[]>();

        rolesActionsMap.put(owner, ownerActions);
        rolesActionsMap.put(admin, adminActions);
        rolesActionsMap.put(member, memberActions);
        rolesActionsMap.put(userRole, userActions);
        rolesActionsMap.put(guest, guestActions);
        rolesActionsMap.put(moderator, moderatorActions);

        for (Role role : rolesActionsMap.keySet()) {
            PermissionLocalServiceUtil.setRolePermissions(role.getRoleId(), companyId,
                    DiscussionCategoryGroup.class.getName(), ResourceConstants.SCOPE_GROUP,
                    String.valueOf(group.getGroupId()), rolesActionsMap.get(role));
        }

        c.setGroupId(group.getGroupId());
        c.setDiscussionGroupId(categoryGroup.getPrimaryKey());
        c.store();
    }
    
    public List<Contest> findByActiveFeatured(boolean active, boolean featured) throws SystemException {
        return contestPersistence.findByActiveFeatured(active, featured);
    }
    
    public List<Contest> findByActiveFlag(boolean active, int flag) throws SystemException {
        return contestPersistence.findByActiveFlag(active, flag);
    }
    public List<Contest> findByActiveFlagText(boolean active, String flagText) throws SystemException {
        return contestPersistence.findByActiveFlagText(active, flagText);
    }
}
