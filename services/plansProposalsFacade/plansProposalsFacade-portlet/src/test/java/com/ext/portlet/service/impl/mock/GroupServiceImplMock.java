package com.ext.portlet.service.impl.mock;

import java.util.Collection;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.impl.GroupImpl;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.ServiceContext;

public class GroupServiceImplMock implements GroupService {
    private long counter = 1;

    @Override
    public Group addGroup(long liveGroupId, String name, String description, int type, String friendlyURL,
            boolean site, boolean active, ServiceContext serviceContext) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return addDummyGroup();
    }

    @Override
    public Group addGroup(String name, String description, int type, String friendlyURL, boolean site, boolean active,
            ServiceContext serviceContext) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return addDummyGroup();
    }

    private Group addDummyGroup() {
        Group g = new GroupImpl();
        g.setGroupId(counter++);
        g.setCompanyId(1);
        return g;
    }
    @Override
    public void addRoleGroups(long roleId, long[] groupIds) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteGroup(long groupId) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getGroup(long groupId) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getGroup(long companyId, String name) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getManageableSites(Collection<Portlet> portlets, int max) throws PortalException,
            SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getOrganizationsGroups(List<Organization> organizations) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getUserGroup(long companyId, long userId) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getUserGroupsGroups(List<UserGroup> userGroups) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getUserOrganizationsGroups(long userId, int start, int end) throws PortalException,
            SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getUserPlaces(long userId, String[] classNames, boolean includeControlPanel, int max)
            throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getUserPlaces(long userId, String[] classNames, int max) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getUserPlaces(String[] classNames, int max) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getUserSites() throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserGroup(long userId, long groupId) throws SystemException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> search(long companyId, String name, String description, String[] params, int start, int end)
            throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int searchCount(long companyId, String name, String description, String[] params) throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setRoleGroups(long roleId, long[] groupIds) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unsetRoleGroups(long roleId, long[] groupIds) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Group updateFriendlyURL(long groupId, String friendlyURL) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Group updateGroup(long groupId, String typeSettings) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Group updateGroup(long groupId, String name, String description, int type, String friendlyURL,
            boolean active, ServiceContext serviceContext) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

}
