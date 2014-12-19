package com.ext.portlet.service.impl.mock;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.Team;
import com.liferay.portal.model.impl.RoleImpl;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.ServiceContext;

public class RoleLocalServiceImplMock implements RoleLocalService {

    @Override
    public Role addRole(Role role) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Role createRole(long roleId) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Role fetchRole(long roleId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Role getRole(long roleId) throws PortalException, SystemException {
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
    public List<Role> getRoles(int start, int end) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getRolesCount() throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getBeanIdentifier() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Role addRole(long userId, long companyId, String name, Map<Locale, String> titleMap,
            Map<Locale, String> descriptionMap, int type) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Role addRole(long userId, long companyId, String name, Map<Locale, String> titleMap,
            Map<Locale, String> descriptionMap, int type, String className, long classPK) throws PortalException,
            SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addUserRoles(long userId, long[] roleIds) throws PortalException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkSystemRoles() throws PortalException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkSystemRoles(long companyId) throws PortalException, SystemException {
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
    public Role updateRole(Role role) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBeanIdentifier(String beanIdentifier) {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Role fetchRole(long companyId, String name) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Role getDefaultGroupRole(long groupId) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getGroupRoles(long groupId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, List<String>> getResourceRoles(long companyId, String name, int scope, String primKey)
            throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getResourceRoles(long companyId, String name, int scope, String primKey, String actionId)
            throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Role getRole(long companyId, String name) throws PortalException, SystemException {
        Role r = new RoleImpl();
        r.setCompanyId(companyId);
        r.setName(name);
        return r;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getRoles(int type, String subtype) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getRoles(long companyId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getRoles(long[] roleIds) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getSubtypeRoles(String subtype) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getSubtypeRolesCount(String subtype) throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Role getTeamRole(long companyId, long teamId) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getUserGroupGroupRoles(long userId, long groupId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getUserGroupRoles(long userId, long groupId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getUserRelatedRoles(long userId, List<Group> groups) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getUserRelatedRoles(long userId, long groupId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getUserRelatedRoles(long userId, long[] groupIds) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getUserRoles(long userId) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserRole(long userId, long roleId) throws SystemException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserRole(long userId, long companyId, String name, boolean inherited) throws PortalException,
            SystemException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserRoles(long userId, long companyId, String[] names, boolean inherited) throws PortalException,
            SystemException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Role loadFetchRole(long companyId, String name) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Role loadGetRole(long companyId, String name) throws PortalException, SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> search(long companyId, String keywords, Integer[] types, int start, int end, OrderByComparator obc)
            throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> search(long companyId, String keywords, Integer[] types, LinkedHashMap<String, Object> params,
            int start, int end, OrderByComparator obc) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> search(long companyId, String name, String description, Integer[] types, int start, int end,
            OrderByComparator obc) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> search(long companyId, String name, String description, Integer[] types,
            LinkedHashMap<String, Object> params, int start, int end, OrderByComparator obc) throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int searchCount(long companyId, String keywords, Integer[] types) throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int searchCount(long companyId, String keywords, Integer[] types, LinkedHashMap<String, Object> params)
            throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int searchCount(long companyId, String name, String description, Integer[] types) throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int searchCount(long companyId, String name, String description, Integer[] types,
            LinkedHashMap<String, Object> params) throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setUserRoles(long userId, long[] roleIds) throws PortalException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void unsetUserRoles(long userId, long[] roleIds) throws PortalException, SystemException {
        // TODO Auto-generated method stub

    }
    
	@Override
	public Role deleteRole(long roleId) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role deleteRole(Role role) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DynamicQuery dynamicQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addGroupRole(long groupId, long roleId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGroupRole(long groupId, Role role) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGroupRoles(long groupId, long[] roleIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGroupRoles(long groupId, List<Role> Roles)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearGroupRoles(long groupId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGroupRole(long groupId, long roleId)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGroupRole(long groupId, Role role) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGroupRoles(long groupId, long[] roleIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGroupRoles(long groupId, List<Role> Roles)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserRole(long userId, long roleId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserRole(long userId, Role role) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserRoles(long userId, List<Role> Roles)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearUserRoles(long userId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserRole(long userId, long roleId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserRole(long userId, Role role) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserRoles(long userId, long[] roleIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserRoles(long userId, List<Role> Roles)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Role addRole(long userId, String className, long classPK,
			String name, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, int type, String subtype,
			ServiceContext serviceContext) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
			Projection projection) throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Role fetchRoleByUuidAndCompanyId(String uuid, long companyId)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role getRoleByUuidAndCompanyId(String uuid, long companyId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getGroupRoles(long groupId, int start, int end)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getGroupRoles(long groupId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupRolesCount(long groupId) throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasGroupRole(long groupId, long roleId)
			throws SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasGroupRoles(long groupId) throws SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGroupRoles(long groupId, long[] roleIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Role> getUserRoles(long userId, int start, int end)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getUserRoles(long userId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getUserRolesCount(long userId) throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasUserRoles(long userId) throws SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Role> getGroupRelatedRoles(long groupId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getResourceBlockRoles(long resourceBlockId,
			String className, String actionId) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getRoles(long companyId, int[] types)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Team, Role> getTeamRoleMap(long groupId) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getTeamRoles(long groupId) throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getTeamRoles(long groupId, long[] excludedRoleIds)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getTypeRoles(int type) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getTypeRoles(int type, int start, int end)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTypeRolesCount(int type) throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Role updateRole(long roleId, String name,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String subtype, ServiceContext serviceContext)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

}
