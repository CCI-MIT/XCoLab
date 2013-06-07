package org.climatecollaboratorium.facelets.discussions.permissions;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.facelets.discussions.DiscussionBean;
import org.climatecollaboratorium.utils.Helper;

import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;

public class DiscussionsPermissionsConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String RESOURCE_NAME = DiscussionCategoryGroup.class
			.getName();
	// private static final Long companyId =
	// Helper.getThemeDisplay().getCompanyId();
	private static Long companyId;
	private Resource resource = null;
	private String primKey;
	private List<PermissionItem> permissionItems;
	private int scope = ResourceConstants.SCOPE_GROUP;
	private Long groupId;
	private DiscussionBean discussionBean;

	private static Log _log = LogFactoryUtil
			.getLog(DiscussionsPermissionsConfig.class);

	public DiscussionsPermissionsConfig(DiscussionBean discussionBean)
			throws SystemException {
		// check if resource has been added, if not, add it
		groupId = discussionBean.getOwningGroupId();
		primKey = groupId.toString();
		this.discussionBean = discussionBean;
		long companyId = Helper.getThemeDisplay().getCompanyId();

		try {
			resource = ResourceLocalServiceUtil.getResource(companyId,
					RESOURCE_NAME, scope, primKey);
		} catch (Exception e) {
			_log.debug(e);
			resource = ResourceLocalServiceUtil.addResource(companyId,
					RESOURCE_NAME, scope, primKey);
		}

	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		_log.info("writing discussion permissions");
		System.out.println("writing discussion permissions");
		out.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		_log.info("reading discussion permissions");
		System.out.println("reading discussion permissions");
		in.defaultReadObject();
	}

	public class PermissionItem implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Role role;
		private String[] actionIds;

		public PermissionItem(Role role) {
			this.role = role;
		}

		public String getName() {
			return role.getName();
		}

		public String[] getActionIds() throws SystemException {
			Resource res = getResource();
			List<Permission> permissions = PermissionLocalServiceUtil
					.getRolePermissions(role.getRoleId(), getResource()
							.getResourceId());
			actionIds = new String[permissions.size()];
			int i = 0;
			for (Permission perm : permissions) {
				actionIds[i++] = perm.getActionId();
			}
			return actionIds;
		}

		public void setActionIds(String[] actionIds) {
			this.actionIds = actionIds;
		}

		public void updatePermissions() throws PortalException, SystemException {
			String[] newActionIds = actionIds;
			String[] oldActionIds = getActionIds();

			Arrays.sort(newActionIds);
			Arrays.sort(oldActionIds);

			// remove all actions that have been disabled
			for (int i = 0; i < oldActionIds.length; i++) {
				if (Arrays.binarySearch(newActionIds, oldActionIds[i]) < 0) {
					PermissionLocalServiceUtil.unsetRolePermission(
							role.getRoleId(), companyId, RESOURCE_NAME, scope,
							primKey, oldActionIds[i]);
				}
			}

			PermissionLocalServiceUtil.setRolePermissions(role.getRoleId(),
					companyId, RESOURCE_NAME, scope, primKey(), newActionIds);
		}
	}

	public List<PermissionItem> getItems() throws SystemException,
			PortalException {
		String[] supportedRolesNames = { RoleConstants.OWNER,
				RoleConstants.ADMINISTRATOR, RoleConstants.USER,
				RoleConstants.USER, RoleConstants.GUEST, "Moderator" };
		if (permissionItems == null) {
			permissionItems = new ArrayList<PermissionItem>();
			for (String roleName : supportedRolesNames) {
				Role role = RoleLocalServiceUtil.getRole(companyId, roleName);
				permissionItems.add(new PermissionItem(role));
			}
		}
		return permissionItems;
	}

	public SelectItem[] getActionItems() {
		DiscussionActions[] actions = DiscussionActions.values();
		SelectItem[] items = new SelectItem[actions.length];
		for (int i = 0; i < items.length; i++) {
			items[i] = new SelectItem(actions[i].name(),
					actions[i].getPrintName());
		}
		return items;
	}

	public void update(ActionEvent e) throws PortalException, SystemException {
		for (PermissionItem item : permissionItems) {
			item.updatePermissions();
		}
		Helper.sendInfoMessage("Permissions updated");
	}

	private String primKey() {
		return discussionBean.getOwningGroupId().toString();
	}

	public Resource getResource() {
		try {
			return ResourceLocalServiceUtil.getResource(companyId,
					RESOURCE_NAME, scope, discussionBean.getOwningGroupId()
							.toString());
		} catch (PortalException e) {
			_log.error(e);
		} catch (SystemException e) {
			_log.error(e);
		}
		return null;
	}
}
