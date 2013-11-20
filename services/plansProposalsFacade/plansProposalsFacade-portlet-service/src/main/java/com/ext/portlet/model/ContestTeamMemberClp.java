package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;

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


public class ContestTeamMemberClp extends BaseModelImpl<ContestTeamMember>
    implements ContestTeamMember {
    private long _id;
    private long _contestId;
    private long _userId;
    private String _userUuid;
    private String _role;
    private BaseModel<?> _contestTeamMemberRemoteModel;

    public ContestTeamMemberClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContestTeamMember.class;
    }

    @Override
    public String getModelClassName() {
        return ContestTeamMember.class.getName();
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
        attributes.put("contestId", getContestId());
        attributes.put("userId", getUserId());
        attributes.put("role", getRole());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long contestId = (Long) attributes.get("contestId");

        if (contestId != null) {
            setContestId(contestId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String role = (String) attributes.get("role");

        if (role != null) {
            setRole(role);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_contestTeamMemberRemoteModel != null) {
            try {
                Class<?> clazz = _contestTeamMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_contestTeamMemberRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getContestId() {
        return _contestId;
    }

    @Override
    public void setContestId(long contestId) {
        _contestId = contestId;

        if (_contestTeamMemberRemoteModel != null) {
            try {
                Class<?> clazz = _contestTeamMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setContestId", long.class);

                method.invoke(_contestTeamMemberRemoteModel, contestId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_contestTeamMemberRemoteModel != null) {
            try {
                Class<?> clazz = _contestTeamMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_contestTeamMemberRemoteModel, userId);
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
    public String getRole() {
        return _role;
    }

    @Override
    public void setRole(String role) {
        _role = role;

        if (_contestTeamMemberRemoteModel != null) {
            try {
                Class<?> clazz = _contestTeamMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setRole", String.class);

                method.invoke(_contestTeamMemberRemoteModel, role);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContestTeamMemberRemoteModel() {
        return _contestTeamMemberRemoteModel;
    }

    public void setContestTeamMemberRemoteModel(
        BaseModel<?> contestTeamMemberRemoteModel) {
        _contestTeamMemberRemoteModel = contestTeamMemberRemoteModel;
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

        Class<?> remoteModelClass = _contestTeamMemberRemoteModel.getClass();

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

        Object returnValue = method.invoke(_contestTeamMemberRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestTeamMemberLocalServiceUtil.addContestTeamMember(this);
        } else {
            ContestTeamMemberLocalServiceUtil.updateContestTeamMember(this);
        }
    }

    @Override
    public ContestTeamMember toEscapedModel() {
        return (ContestTeamMember) ProxyUtil.newProxyInstance(ContestTeamMember.class.getClassLoader(),
            new Class[] { ContestTeamMember.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestTeamMemberClp clone = new ContestTeamMemberClp();

        clone.setId(getId());
        clone.setContestId(getContestId());
        clone.setUserId(getUserId());
        clone.setRole(getRole());

        return clone;
    }

    @Override
    public int compareTo(ContestTeamMember contestTeamMember) {
        int value = 0;

        if (getId() < contestTeamMember.getId()) {
            value = -1;
        } else if (getId() > contestTeamMember.getId()) {
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

        if (!(obj instanceof ContestTeamMemberClp)) {
            return false;
        }

        ContestTeamMemberClp contestTeamMember = (ContestTeamMemberClp) obj;

        long primaryKey = contestTeamMember.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", contestId=");
        sb.append(getContestId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", role=");
        sb.append(getRole());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ContestTeamMember");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestId</column-name><column-value><![CDATA[");
        sb.append(getContestId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>role</column-name><column-value><![CDATA[");
        sb.append(getRole());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
