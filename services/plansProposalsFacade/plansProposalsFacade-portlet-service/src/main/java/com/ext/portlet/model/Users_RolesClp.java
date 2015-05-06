package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.Users_RolesLocalServiceUtil;
import com.ext.portlet.service.persistence.Users_RolesPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class Users_RolesClp extends BaseModelImpl<Users_Roles>
    implements Users_Roles {
    private long _userId;
    private String _userUuid;
    private long _roleId;
    private BaseModel<?> _users_RolesRemoteModel;

    public Users_RolesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return Users_Roles.class;
    }

    @Override
    public String getModelClassName() {
        return Users_Roles.class.getName();
    }

    @Override
    public Users_RolesPK getPrimaryKey() {
        return new Users_RolesPK(_userId, _roleId);
    }

    @Override
    public void setPrimaryKey(Users_RolesPK primaryKey) {
        setUserId(primaryKey.userId);
        setRoleId(primaryKey.roleId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new Users_RolesPK(_userId, _roleId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((Users_RolesPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("userId", getUserId());
        attributes.put("roleId", getRoleId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long roleId = (Long) attributes.get("roleId");

        if (roleId != null) {
            setRoleId(roleId);
        }
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_users_RolesRemoteModel != null) {
            try {
                Class<?> clazz = _users_RolesRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_users_RolesRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    @Override
    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    @Override
    public long getRoleId() {
        return _roleId;
    }

    @Override
    public void setRoleId(long roleId) {
        _roleId = roleId;

        if (_users_RolesRemoteModel != null) {
            try {
                Class<?> clazz = _users_RolesRemoteModel.getClass();

                Method method = clazz.getMethod("setRoleId", long.class);

                method.invoke(_users_RolesRemoteModel, roleId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getUsers_RolesRemoteModel() {
        return _users_RolesRemoteModel;
    }

    public void setUsers_RolesRemoteModel(BaseModel<?> users_RolesRemoteModel) {
        _users_RolesRemoteModel = users_RolesRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _users_RolesRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_users_RolesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            Users_RolesLocalServiceUtil.addUsers_Roles(this);
        } else {
            Users_RolesLocalServiceUtil.updateUsers_Roles(this);
        }
    }

    @Override
    public Users_Roles toEscapedModel() {
        return (Users_Roles) ProxyUtil.newProxyInstance(Users_Roles.class.getClassLoader(),
            new Class[] { Users_Roles.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        Users_RolesClp clone = new Users_RolesClp();

        clone.setUserId(getUserId());
        clone.setRoleId(getRoleId());

        return clone;
    }

    @Override
    public int compareTo(Users_Roles users_Roles) {
        Users_RolesPK primaryKey = users_Roles.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Users_RolesClp)) {
            return false;
        }

        Users_RolesClp users_Roles = (Users_RolesClp) obj;

        Users_RolesPK primaryKey = users_Roles.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", roleId=");
        sb.append(getRoleId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.Users_Roles");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>roleId</column-name><column-value><![CDATA[");
        sb.append(getRoleId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
