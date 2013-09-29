package com.ext.utils;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.portal.service.PermissionLocalService;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;


public class PortalServicesHelperImpl implements PortalServicesHelper {
    
    @Override
    public CounterLocalService getCounterLocalService() {
        return CounterLocalServiceUtil.getService();
    }
    @Override
    public RoleLocalService getRoleLocalService() {
        return RoleLocalServiceUtil.getService();
    }
    
    @Override
    public PermissionLocalService getPermissionLocalService() {
        return PermissionLocalServiceUtil.getService();
    }

    @Override
    public GroupService getGroupService() {
        return GroupServiceUtil.getService();
    }

}
