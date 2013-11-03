package com.ext.portlet.service.impl.mock;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.model.Resource;
import com.liferay.portal.security.permission.PermissionCheckerBag;
import com.liferay.portal.service.PermissionLocalService;

public class PermissionLocalServiceImplMock implements PermissionLocalService {

    @Override
    public Permission addPermission(Permission permission) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Permission createPermission(long permissionId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deletePermission(long permissionId) throws PortalException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deletePermission(Permission permission) throws SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Permission fetchPermission(long permissionId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Permission getPermission(long permissionId) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PersistedModel getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Permission> getPermissions(int start, int end) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPermissionsCount() throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getBeanIdentifier() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Permission addPermission(long companyId, String actionId, long resourceId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Permission> addPermissions(long companyId, List<String> actionIds, long resourceId)
            throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Permission> addPermissions(long companyId, String name, long resourceId, boolean portletActions)
            throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addUserPermissions(long userId, String[] actionIds, long resourceId) throws PortalException,
            SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkPermissions(String name, List<String> actionIds) throws PortalException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public List dynamicQuery(DynamicQuery arg0) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List dynamicQuery(DynamicQuery arg0, int arg1, int arg2) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List dynamicQuery(DynamicQuery arg0, int arg1, int arg2, OrderByComparator arg3) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long dynamicQueryCount(DynamicQuery arg0) throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Permission updatePermission(Permission permission) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Permission updatePermission(Permission permission, boolean merge) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBeanIdentifier(String beanIdentifier) {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<String> getActions(List<Permission> permissions) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Permission> getGroupPermissions(long groupId, long resourceId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Permission> getGroupPermissions(long groupId, long companyId, String name, int scope, String primKey)
            throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getLatestPermissionId() throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Permission> getOrgGroupPermissions(long organizationId, long groupId, long resourceId)
            throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Permission> getPermissions(long companyId, String[] actionIds, long resourceId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Permission> getRolePermissions(long roleId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Permission> getRolePermissions(long roleId, int[] scopes) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Permission> getRolePermissions(long roleId, long resourceId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Permission> getUserPermissions(long userId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Permission> getUserPermissions(long userId, long resourceId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Permission> getUserPermissions(long userId, long companyId, String name, int scope, String primKey)
            throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasGroupPermission(long groupId, String actionId, long resourceId) throws SystemException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasRolePermission(long roleId, long companyId, String name, int scope, String actionId)
            throws SystemException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasRolePermission(long roleId, long companyId, String name, int scope, String primKey,
            String actionId) throws SystemException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserPermission(long userId, String actionId, long resourceId) throws SystemException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserPermissions(long userId, long groupId, List<Resource> resources, String actionId,
            PermissionCheckerBag permissionCheckerBag) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setContainerResourcePermissions(String name, String roleName, String actionId) throws PortalException,
            SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setGroupPermissions(long groupId, String[] actionIds, long resourceId) throws PortalException,
            SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setGroupPermissions(String className, String classPK, long groupId, String[] actionIds, long resourceId)
            throws PortalException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setOrgGroupPermissions(long organizationId, long groupId, String[] actionIds, long resourceId)
            throws PortalException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRolePermission(long roleId, long companyId, String name, int scope, String primKey, String actionId)
            throws PortalException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRolePermissions(long roleId, long companyId, String name, int scope, String primKey,
            String[] actionIds) throws PortalException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRolePermissions(long roleId, String[] actionIds, long resourceId) throws PortalException,
            SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRolesPermissions(long companyId, Map<Long, String[]> roleIdsToActionIds, long resourceId)
            throws SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRolesPermissions(long companyId, Map<Long, String[]> roleIdsToActionIds, String name, int scope,
            String primKey) throws SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setUserPermissions(long userId, String[] actionIds, long resourceId) throws PortalException,
            SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void unsetRolePermission(long roleId, long permissionId) throws SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void unsetRolePermission(long roleId, long companyId, String name, int scope, String primKey, String actionId)
            throws SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void unsetRolePermissions(long roleId, long companyId, String name, int scope, String actionId)
            throws SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void unsetUserPermissions(long userId, String[] actionIds, long resourceId) throws SystemException {
        // TODO Auto-generated method stub

    }

}
