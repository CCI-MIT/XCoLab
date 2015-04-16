package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.Role_LocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class Role_Clp extends BaseModelImpl<Role_> implements Role_ {
    private long _roleId;
    private String _name;
    private BaseModel<?> _role_RemoteModel;

    public Role_Clp() {
    }

    @Override
    public Class<?> getModelClass() {
        return Role_.class;
    }

    @Override
    public String getModelClassName() {
        return Role_.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _roleId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setRoleId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _roleId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("roleId", getRoleId());
        attributes.put("name", getName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long roleId = (Long) attributes.get("roleId");

        if (roleId != null) {
            setRoleId(roleId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }
    }

    @Override
    public long getRoleId() {
        return _roleId;
    }

    @Override
    public void setRoleId(long roleId) {
        _roleId = roleId;

        if (_role_RemoteModel != null) {
            try {
                Class<?> clazz = _role_RemoteModel.getClass();

                Method method = clazz.getMethod("setRoleId", long.class);

                method.invoke(_role_RemoteModel, roleId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;

        if (_role_RemoteModel != null) {
            try {
                Class<?> clazz = _role_RemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_role_RemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRole_RemoteModel() {
        return _role_RemoteModel;
    }

    public void setRole_RemoteModel(BaseModel<?> role_RemoteModel) {
        _role_RemoteModel = role_RemoteModel;
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

        Class<?> remoteModelClass = _role_RemoteModel.getClass();

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

        Object returnValue = method.invoke(_role_RemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            Role_LocalServiceUtil.addRole_(this);
        } else {
            Role_LocalServiceUtil.updateRole_(this);
        }
    }

    @Override
    public Role_ toEscapedModel() {
        return (Role_) ProxyUtil.newProxyInstance(Role_.class.getClassLoader(),
            new Class[] { Role_.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        Role_Clp clone = new Role_Clp();

        clone.setRoleId(getRoleId());
        clone.setName(getName());

        return clone;
    }

    @Override
    public int compareTo(Role_ role_) {
        long primaryKey = role_.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Role_Clp)) {
            return false;
        }

        Role_Clp role_ = (Role_Clp) obj;

        long primaryKey = role_.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{roleId=");
        sb.append(getRoleId());
        sb.append(", name=");
        sb.append(getName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.Role_");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>roleId</column-name><column-value><![CDATA[");
        sb.append(getRoleId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
