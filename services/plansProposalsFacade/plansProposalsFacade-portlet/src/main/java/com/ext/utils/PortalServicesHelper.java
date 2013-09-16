package com.ext.utils;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.PermissionLocalService;
import com.liferay.portal.service.RoleLocalService;
/**
 * <p>Purpose of this class is to provide getters for XLocalServices and XServices from LiferayPortal 
 * or other services that are used across this service. Thanks to that it will be possible to
 * remove direct calls to static *LocalServiceUtil XServices classes and make it possible to mock out 
 * LR services when testing classes from this project.</p>
 * 
 *  
 * @author janusz
 *
 */
public interface PortalServicesHelper {

    CounterLocalService getCounterLocalService();
    RoleLocalService getRoleLocalService();
    PermissionLocalService getPermissionLocalService();
    GroupService getGroupService();

}