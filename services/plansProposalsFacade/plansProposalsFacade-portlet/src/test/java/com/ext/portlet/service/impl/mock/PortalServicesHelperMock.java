package com.ext.portlet.service.impl.mock;

import com.ext.utils.PortalServicesHelper;
import com.liferay.counter.service.CounterLocalService;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.PermissionLocalService;
import com.liferay.portal.service.RoleLocalService;

public class PortalServicesHelperMock implements PortalServicesHelper {

    @Override
    public CounterLocalService getCounterLocalService() {
        return new CounterLocalServiceImplMock();
    }

    @Override
    public RoleLocalService getRoleLocalService() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PermissionLocalService getPermissionLocalService() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GroupService getGroupService() {
        // TODO Auto-generated method stub
        return null;
    }

}
