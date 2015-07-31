package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.RolesCategoryLocalServiceUtil;

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


public class RolesCategoryClp extends BaseModelImpl<RolesCategory>
    implements RolesCategory {
    private long _roleId;
    private String _categoryName;
    private int _roleOrdinal;
    private BaseModel<?> _rolesCategoryRemoteModel;

    public RolesCategoryClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return RolesCategory.class;
    }

    @Override
    public String getModelClassName() {
        return RolesCategory.class.getName();
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
        attributes.put("categoryName", getCategoryName());
        attributes.put("roleOrdinal", getRoleOrdinal());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long roleId = (Long) attributes.get("roleId");

        if (roleId != null) {
            setRoleId(roleId);
        }

        String categoryName = (String) attributes.get("categoryName");

        if (categoryName != null) {
            setCategoryName(categoryName);
        }

        Integer roleOrdinal = (Integer) attributes.get("roleOrdinal");

        if (roleOrdinal != null) {
            setRoleOrdinal(roleOrdinal);
        }
    }

    @Override
    public long getRoleId() {
        return _roleId;
    }

    @Override
    public void setRoleId(long roleId) {
        _roleId = roleId;

        if (_rolesCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _rolesCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setRoleId", long.class);

                method.invoke(_rolesCategoryRemoteModel, roleId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCategoryName() {
        return _categoryName;
    }

    @Override
    public void setCategoryName(String categoryName) {
        _categoryName = categoryName;

        if (_rolesCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _rolesCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryName", String.class);

                method.invoke(_rolesCategoryRemoteModel, categoryName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRoleOrdinal() {
        return _roleOrdinal;
    }

    @Override
    public void setRoleOrdinal(int roleOrdinal) {
        _roleOrdinal = roleOrdinal;

        if (_rolesCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _rolesCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setRoleOrdinal", int.class);

                method.invoke(_rolesCategoryRemoteModel, roleOrdinal);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRolesCategoryRemoteModel() {
        return _rolesCategoryRemoteModel;
    }

    public void setRolesCategoryRemoteModel(
        BaseModel<?> rolesCategoryRemoteModel) {
        _rolesCategoryRemoteModel = rolesCategoryRemoteModel;
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

        Class<?> remoteModelClass = _rolesCategoryRemoteModel.getClass();

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

        Object returnValue = method.invoke(_rolesCategoryRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RolesCategoryLocalServiceUtil.addRolesCategory(this);
        } else {
            RolesCategoryLocalServiceUtil.updateRolesCategory(this);
        }
    }

    @Override
    public RolesCategory toEscapedModel() {
        return (RolesCategory) ProxyUtil.newProxyInstance(RolesCategory.class.getClassLoader(),
            new Class[] { RolesCategory.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        RolesCategoryClp clone = new RolesCategoryClp();

        clone.setRoleId(getRoleId());
        clone.setCategoryName(getCategoryName());
        clone.setRoleOrdinal(getRoleOrdinal());

        return clone;
    }

    @Override
    public int compareTo(RolesCategory rolesCategory) {
        long primaryKey = rolesCategory.getPrimaryKey();

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

        if (!(obj instanceof RolesCategoryClp)) {
            return false;
        }

        RolesCategoryClp rolesCategory = (RolesCategoryClp) obj;

        long primaryKey = rolesCategory.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{roleId=");
        sb.append(getRoleId());
        sb.append(", categoryName=");
        sb.append(getCategoryName());
        sb.append(", roleOrdinal=");
        sb.append(getRoleOrdinal());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.RolesCategory");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>roleId</column-name><column-value><![CDATA[");
        sb.append(getRoleId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryName</column-name><column-value><![CDATA[");
        sb.append(getCategoryName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>roleOrdinal</column-name><column-value><![CDATA[");
        sb.append(getRoleOrdinal());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
