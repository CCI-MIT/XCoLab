package com.ext.portlet.service.impl.mock;

import com.ext.utils.PortalServicesHelper;
import com.liferay.counter.service.CounterLocalService;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.PermissionLocalService;
import com.liferay.portal.service.RoleLocalService;

public class PortalServicesHelperMock implements PortalServicesHelper {
    CounterLocalService counterService = new CounterLocalServiceImplMock();
    GroupService groupService = new GroupServiceImplMock(); 
    RoleLocalService roleService = new RoleLocalServiceImplMock();
    PermissionLocalService permissionService = new PermissionLocalServiceImplMock();

    @Override
    public CounterLocalService getCounterLocalService() {
        return counterService;
    }

    @Override
    public RoleLocalService getRoleLocalService() {
        return roleService;
    }

    @Override
    public PermissionLocalService getPermissionLocalService() {
        return permissionService;
    }

    @Override
    public GroupService getGroupService() {
        return groupService;
    }

}
