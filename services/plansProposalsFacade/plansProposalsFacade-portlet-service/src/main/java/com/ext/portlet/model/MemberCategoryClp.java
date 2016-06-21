package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.MemberCategoryLocalServiceUtil;

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


public class MemberCategoryClp extends BaseModelImpl<MemberCategory>
    implements MemberCategory {
    private long _roleId;
    private String _displayName;
    private String _categoryName;
    private long _sortOrder;
    private boolean _showInList;
    private String _imageName;
    private String _description;
    private BaseModel<?> _memberCategoryRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public MemberCategoryClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MemberCategory.class;
    }

    @Override
    public String getModelClassName() {
        return MemberCategory.class.getName();
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
        attributes.put("displayName", getDisplayName());
        attributes.put("categoryName", getCategoryName());
        attributes.put("sortOrder", getSortOrder());
        attributes.put("showInList", getShowInList());
        attributes.put("imageName", getImageName());
        attributes.put("description", getDescription());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long roleId = (Long) attributes.get("roleId");

        if (roleId != null) {
            setRoleId(roleId);
        }

        String displayName = (String) attributes.get("displayName");

        if (displayName != null) {
            setDisplayName(displayName);
        }

        String categoryName = (String) attributes.get("categoryName");

        if (categoryName != null) {
            setCategoryName(categoryName);
        }

        Long sortOrder = (Long) attributes.get("sortOrder");

        if (sortOrder != null) {
            setSortOrder(sortOrder);
        }

        Boolean showInList = (Boolean) attributes.get("showInList");

        if (showInList != null) {
            setShowInList(showInList);
        }

        String imageName = (String) attributes.get("imageName");

        if (imageName != null) {
            setImageName(imageName);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }
    }

    @Override
    public long getRoleId() {
        return _roleId;
    }

    @Override
    public void setRoleId(long roleId) {
        _roleId = roleId;

        if (_memberCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _memberCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setRoleId", long.class);

                method.invoke(_memberCategoryRemoteModel, roleId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDisplayName() {
        return _displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        _displayName = displayName;

        if (_memberCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _memberCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setDisplayName", String.class);

                method.invoke(_memberCategoryRemoteModel, displayName);
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

        if (_memberCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _memberCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryName", String.class);

                method.invoke(_memberCategoryRemoteModel, categoryName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getSortOrder() {
        return _sortOrder;
    }

    @Override
    public void setSortOrder(long sortOrder) {
        _sortOrder = sortOrder;

        if (_memberCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _memberCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setSortOrder", long.class);

                method.invoke(_memberCategoryRemoteModel, sortOrder);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getShowInList() {
        return _showInList;
    }

    @Override
    public boolean isShowInList() {
        return _showInList;
    }

    @Override
    public void setShowInList(boolean showInList) {
        _showInList = showInList;

        if (_memberCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _memberCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setShowInList", boolean.class);

                method.invoke(_memberCategoryRemoteModel, showInList);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getImageName() {
        return _imageName;
    }

    @Override
    public void setImageName(String imageName) {
        _imageName = imageName;

        if (_memberCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _memberCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setImageName", String.class);

                method.invoke(_memberCategoryRemoteModel, imageName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;

        if (_memberCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _memberCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_memberCategoryRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMemberCategoryRemoteModel() {
        return _memberCategoryRemoteModel;
    }

    public void setMemberCategoryRemoteModel(
        BaseModel<?> memberCategoryRemoteModel) {
        _memberCategoryRemoteModel = memberCategoryRemoteModel;
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

        Class<?> remoteModelClass = _memberCategoryRemoteModel.getClass();

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

        Object returnValue = method.invoke(_memberCategoryRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MemberCategoryLocalServiceUtil.addMemberCategory(this);
        } else {
            MemberCategoryLocalServiceUtil.updateMemberCategory(this);
        }
    }

    @Override
    public MemberCategory toEscapedModel() {
        return (MemberCategory) ProxyUtil.newProxyInstance(MemberCategory.class.getClassLoader(),
            new Class[] { MemberCategory.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MemberCategoryClp clone = new MemberCategoryClp();

        clone.setRoleId(getRoleId());
        clone.setDisplayName(getDisplayName());
        clone.setCategoryName(getCategoryName());
        clone.setSortOrder(getSortOrder());
        clone.setShowInList(getShowInList());
        clone.setImageName(getImageName());
        clone.setDescription(getDescription());

        return clone;
    }

    @Override
    public int compareTo(MemberCategory memberCategory) {
        long primaryKey = memberCategory.getPrimaryKey();

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

        if (!(obj instanceof MemberCategoryClp)) {
            return false;
        }

        MemberCategoryClp memberCategory = (MemberCategoryClp) obj;

        long primaryKey = memberCategory.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{roleId=");
        sb.append(getRoleId());
        sb.append(", displayName=");
        sb.append(getDisplayName());
        sb.append(", categoryName=");
        sb.append(getCategoryName());
        sb.append(", sortOrder=");
        sb.append(getSortOrder());
        sb.append(", showInList=");
        sb.append(getShowInList());
        sb.append(", imageName=");
        sb.append(getImageName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.MemberCategory");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>roleId</column-name><column-value><![CDATA[");
        sb.append(getRoleId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>displayName</column-name><column-value><![CDATA[");
        sb.append(getDisplayName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryName</column-name><column-value><![CDATA[");
        sb.append(getCategoryName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sortOrder</column-name><column-value><![CDATA[");
        sb.append(getSortOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>showInList</column-name><column-value><![CDATA[");
        sb.append(getShowInList());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imageName</column-name><column-value><![CDATA[");
        sb.append(getImageName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
