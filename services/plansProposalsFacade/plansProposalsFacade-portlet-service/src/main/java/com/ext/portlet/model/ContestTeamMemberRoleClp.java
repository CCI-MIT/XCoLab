package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestTeamMemberRoleLocalServiceUtil;

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


public class ContestTeamMemberRoleClp extends BaseModelImpl<ContestTeamMemberRole>
    implements ContestTeamMemberRole {
    private long _id;
    private String _role;
    private int _sort;
    private BaseModel<?> _contestTeamMemberRoleRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ContestTeamMemberRoleClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContestTeamMemberRole.class;
    }

    @Override
    public String getModelClassName() {
        return ContestTeamMemberRole.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _id;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _id;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("role", getRole());
        attributes.put("sort", getSort());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String role = (String) attributes.get("role");

        if (role != null) {
            setRole(role);
        }

        Integer sort = (Integer) attributes.get("sort");

        if (sort != null) {
            setSort(sort);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_contestTeamMemberRoleRemoteModel != null) {
            try {
                Class<?> clazz = _contestTeamMemberRoleRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_contestTeamMemberRoleRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRole() {
        return _role;
    }

    @Override
    public void setRole(String role) {
        _role = role;

        if (_contestTeamMemberRoleRemoteModel != null) {
            try {
                Class<?> clazz = _contestTeamMemberRoleRemoteModel.getClass();

                Method method = clazz.getMethod("setRole", String.class);

                method.invoke(_contestTeamMemberRoleRemoteModel, role);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSort() {
        return _sort;
    }

    @Override
    public void setSort(int sort) {
        _sort = sort;

        if (_contestTeamMemberRoleRemoteModel != null) {
            try {
                Class<?> clazz = _contestTeamMemberRoleRemoteModel.getClass();

                Method method = clazz.getMethod("setSort", int.class);

                method.invoke(_contestTeamMemberRoleRemoteModel, sort);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContestTeamMemberRoleRemoteModel() {
        return _contestTeamMemberRoleRemoteModel;
    }

    public void setContestTeamMemberRoleRemoteModel(
        BaseModel<?> contestTeamMemberRoleRemoteModel) {
        _contestTeamMemberRoleRemoteModel = contestTeamMemberRoleRemoteModel;
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

        Class<?> remoteModelClass = _contestTeamMemberRoleRemoteModel.getClass();

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

        Object returnValue = method.invoke(_contestTeamMemberRoleRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestTeamMemberRoleLocalServiceUtil.addContestTeamMemberRole(this);
        } else {
            ContestTeamMemberRoleLocalServiceUtil.updateContestTeamMemberRole(this);
        }
    }

    @Override
    public ContestTeamMemberRole toEscapedModel() {
        return (ContestTeamMemberRole) ProxyUtil.newProxyInstance(ContestTeamMemberRole.class.getClassLoader(),
            new Class[] { ContestTeamMemberRole.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestTeamMemberRoleClp clone = new ContestTeamMemberRoleClp();

        clone.setId(getId());
        clone.setRole(getRole());
        clone.setSort(getSort());

        return clone;
    }

    @Override
    public int compareTo(ContestTeamMemberRole contestTeamMemberRole) {
        int value = 0;

        if (getId() < contestTeamMemberRole.getId()) {
            value = -1;
        } else if (getId() > contestTeamMemberRole.getId()) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestTeamMemberRoleClp)) {
            return false;
        }

        ContestTeamMemberRoleClp contestTeamMemberRole = (ContestTeamMemberRoleClp) obj;

        long primaryKey = contestTeamMemberRole.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    public Class<?> getClpSerializerClass() {
        return _clpSerializerClass;
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", role=");
        sb.append(getRole());
        sb.append(", sort=");
        sb.append(getSort());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ContestTeamMemberRole");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>role</column-name><column-value><![CDATA[");
        sb.append(getRole());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sort</column-name><column-value><![CDATA[");
        sb.append(getSort());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
