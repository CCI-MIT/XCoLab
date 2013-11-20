package com.ext.portlet.service.impl.mock;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceAction;
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.service.ResourcePermissionLocalService;

public class PermissionLocalServiceImplMock implements ResourcePermissionLocalService {

	@Override
	public ResourcePermission addResourcePermission(
			ResourcePermission resourcePermission) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourcePermission createResourcePermission(long resourcePermissionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourcePermission deleteResourcePermission(long resourcePermissionId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourcePermission deleteResourcePermission(
			ResourcePermission resourcePermission) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DynamicQuery dynamicQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourcePermission fetchResourcePermission(long resourcePermissionId)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourcePermission getResourcePermission(long resourcePermissionId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourcePermission> getResourcePermissions(int start, int end)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBeanIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addResourcePermission(long companyId, String name, int scope,
			String primKey, long roleId, String actionId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addResourcePermissions(String resourceName, String roleName,
			int scope, long resourceActionBitwiseValue) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteResourcePermissions(long companyId, String name,
			int scope, long primKey) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteResourcePermissions(long companyId, String name,
			int scope, String primKey) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List dynamicQuery(DynamicQuery dynamicQuery) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
			throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
			Projection projection) throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getResourcePermissionsCount() throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResourcePermission updateResourcePermission(
			ResourcePermission resourcePermission) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAvailableResourcePermissionActionIds(long companyId,
			String name, int scope, String primKey, long roleId,
			Collection<String> actionIds) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Long, Set<String>> getAvailableResourcePermissionActionIds(
			long companyId, String name, int scope, String primKey,
			long[] roleIds, Collection<String> actionIds)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourcePermission getResourcePermission(long companyId,
			String name, int scope, String primKey, long roleId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourcePermission> getResourcePermissions(long companyId,
			String name, int scope, String primKey) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getResourcePermissionsCount(long companyId, String name,
			int scope, String primKey) throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ResourcePermission> getResourceResourcePermissions(
			long companyId, long groupId, String name, String primKey)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourcePermission> getRoleResourcePermissions(long roleId)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourcePermission> getRoleResourcePermissions(long roleId,
			int[] scopes, int start, int end) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourcePermission> getScopeResourcePermissions(int[] scopes)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasActionId(ResourcePermission resourcePermission,
			ResourceAction resourceAction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasResourcePermission(List<Resource> resources,
			long[] roleIds, String actionId) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasResourcePermission(long companyId, String name,
			int scope, String primKey, long roleId, String actionId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasResourcePermission(long companyId, String name,
			int scope, String primKey, long[] roleIds, String actionId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] hasResourcePermissions(long companyId, String name,
			int scope, String primKey, long[] roleIds, String actionId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasScopeResourcePermission(long companyId, String name,
			int scope, long roleId, String actionId) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mergePermissions(long fromRoleId, long toRoleId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reassignPermissions(long resourcePermissionId, long toRoleId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeResourcePermission(long companyId, String name,
			int scope, String primKey, long roleId, String actionId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeResourcePermissions(long companyId, String name,
			int scope, long roleId, String actionId) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOwnerResourcePermissions(long companyId, String name,
			int scope, String primKey, long roleId, long ownerId,
			String[] actionIds) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResourcePermissions(long companyId, String name, int scope,
			String primKey, long roleId, String[] actionIds)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResourcePermissions(long companyId, String name, int scope,
			String primKey, Map<Long, String[]> roleIdsToActionIds)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

}
