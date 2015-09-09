package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.StaffMemberLocalServiceUtil;

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


public class StaffMemberClp extends BaseModelImpl<StaffMember>
    implements StaffMember {
    private long _id;
    private long _userId;
    private String _userUuid;
    private long _categoryId;
    private String _firstNames;
    private String _lastName;
    private String _url;
    private String _photoUrl;
    private String _role;
    private String _organization;
    private int _sort;
    private BaseModel<?> _staffMemberRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public StaffMemberClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StaffMember.class;
    }

    @Override
    public String getModelClassName() {
        return StaffMember.class.getName();
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
        attributes.put("userId", getUserId());
        attributes.put("categoryId", getCategoryId());
        attributes.put("firstNames", getFirstNames());
        attributes.put("lastName", getLastName());
        attributes.put("url", getUrl());
        attributes.put("photoUrl", getPhotoUrl());
        attributes.put("role", getRole());
        attributes.put("organization", getOrganization());
        attributes.put("sort", getSort());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long categoryId = (Long) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }

        String firstNames = (String) attributes.get("firstNames");

        if (firstNames != null) {
            setFirstNames(firstNames);
        }

        String lastName = (String) attributes.get("lastName");

        if (lastName != null) {
            setLastName(lastName);
        }

        String url = (String) attributes.get("url");

        if (url != null) {
            setUrl(url);
        }

        String photoUrl = (String) attributes.get("photoUrl");

        if (photoUrl != null) {
            setPhotoUrl(photoUrl);
        }

        String role = (String) attributes.get("role");

        if (role != null) {
            setRole(role);
        }

        String organization = (String) attributes.get("organization");

        if (organization != null) {
            setOrganization(organization);
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

        if (_staffMemberRemoteModel != null) {
            try {
                Class<?> clazz = _staffMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_staffMemberRemoteModel, id);
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

        if (_staffMemberRemoteModel != null) {
            try {
                Class<?> clazz = _staffMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_staffMemberRemoteModel, userId);
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
    public long getCategoryId() {
        return _categoryId;
    }

    @Override
    public void setCategoryId(long categoryId) {
        _categoryId = categoryId;

        if (_staffMemberRemoteModel != null) {
            try {
                Class<?> clazz = _staffMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryId", long.class);

                method.invoke(_staffMemberRemoteModel, categoryId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFirstNames() {
        return _firstNames;
    }

    @Override
    public void setFirstNames(String firstNames) {
        _firstNames = firstNames;

        if (_staffMemberRemoteModel != null) {
            try {
                Class<?> clazz = _staffMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setFirstNames", String.class);

                method.invoke(_staffMemberRemoteModel, firstNames);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLastName() {
        return _lastName;
    }

    @Override
    public void setLastName(String lastName) {
        _lastName = lastName;

        if (_staffMemberRemoteModel != null) {
            try {
                Class<?> clazz = _staffMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setLastName", String.class);

                method.invoke(_staffMemberRemoteModel, lastName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUrl() {
        return _url;
    }

    @Override
    public void setUrl(String url) {
        _url = url;

        if (_staffMemberRemoteModel != null) {
            try {
                Class<?> clazz = _staffMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setUrl", String.class);

                method.invoke(_staffMemberRemoteModel, url);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPhotoUrl() {
        return _photoUrl;
    }

    @Override
    public void setPhotoUrl(String photoUrl) {
        _photoUrl = photoUrl;

        if (_staffMemberRemoteModel != null) {
            try {
                Class<?> clazz = _staffMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setPhotoUrl", String.class);

                method.invoke(_staffMemberRemoteModel, photoUrl);
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

        if (_staffMemberRemoteModel != null) {
            try {
                Class<?> clazz = _staffMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setRole", String.class);

                method.invoke(_staffMemberRemoteModel, role);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrganization() {
        return _organization;
    }

    @Override
    public void setOrganization(String organization) {
        _organization = organization;

        if (_staffMemberRemoteModel != null) {
            try {
                Class<?> clazz = _staffMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganization", String.class);

                method.invoke(_staffMemberRemoteModel, organization);
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

        if (_staffMemberRemoteModel != null) {
            try {
                Class<?> clazz = _staffMemberRemoteModel.getClass();

                Method method = clazz.getMethod("setSort", int.class);

                method.invoke(_staffMemberRemoteModel, sort);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStaffMemberRemoteModel() {
        return _staffMemberRemoteModel;
    }

    public void setStaffMemberRemoteModel(BaseModel<?> staffMemberRemoteModel) {
        _staffMemberRemoteModel = staffMemberRemoteModel;
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

        Class<?> remoteModelClass = _staffMemberRemoteModel.getClass();

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

        Object returnValue = method.invoke(_staffMemberRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StaffMemberLocalServiceUtil.addStaffMember(this);
        } else {
            StaffMemberLocalServiceUtil.updateStaffMember(this);
        }
    }

    @Override
    public StaffMember toEscapedModel() {
        return (StaffMember) ProxyUtil.newProxyInstance(StaffMember.class.getClassLoader(),
            new Class[] { StaffMember.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StaffMemberClp clone = new StaffMemberClp();

        clone.setId(getId());
        clone.setUserId(getUserId());
        clone.setCategoryId(getCategoryId());
        clone.setFirstNames(getFirstNames());
        clone.setLastName(getLastName());
        clone.setUrl(getUrl());
        clone.setPhotoUrl(getPhotoUrl());
        clone.setRole(getRole());
        clone.setOrganization(getOrganization());
        clone.setSort(getSort());

        return clone;
    }

    @Override
    public int compareTo(StaffMember staffMember) {
        int value = 0;

        if (getSort() < staffMember.getSort()) {
            value = -1;
        } else if (getSort() > staffMember.getSort()) {
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

        if (!(obj instanceof StaffMemberClp)) {
            return false;
        }

        StaffMemberClp staffMember = (StaffMemberClp) obj;

        long primaryKey = staffMember.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", categoryId=");
        sb.append(getCategoryId());
        sb.append(", firstNames=");
        sb.append(getFirstNames());
        sb.append(", lastName=");
        sb.append(getLastName());
        sb.append(", url=");
        sb.append(getUrl());
        sb.append(", photoUrl=");
        sb.append(getPhotoUrl());
        sb.append(", role=");
        sb.append(getRole());
        sb.append(", organization=");
        sb.append(getOrganization());
        sb.append(", sort=");
        sb.append(getSort());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.StaffMember");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryId</column-name><column-value><![CDATA[");
        sb.append(getCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>firstNames</column-name><column-value><![CDATA[");
        sb.append(getFirstNames());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastName</column-name><column-value><![CDATA[");
        sb.append(getLastName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>url</column-name><column-value><![CDATA[");
        sb.append(getUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>photoUrl</column-name><column-value><![CDATA[");
        sb.append(getPhotoUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>role</column-name><column-value><![CDATA[");
        sb.append(getRole());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organization</column-name><column-value><![CDATA[");
        sb.append(getOrganization());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sort</column-name><column-value><![CDATA[");
        sb.append(getSort());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
