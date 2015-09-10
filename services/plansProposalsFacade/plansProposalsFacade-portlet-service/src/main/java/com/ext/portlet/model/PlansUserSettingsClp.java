package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlansUserSettingsLocalServiceUtil;

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


public class PlansUserSettingsClp extends BaseModelImpl<PlansUserSettings>
    implements PlansUserSettings {
    private long _planUserSettingsId;
    private long _userId;
    private String _userUuid;
    private long _planTypeId;
    private String _sortColumn;
    private String _sortDirection;
    private boolean _filterEnabled;
    private boolean _filterPositionsAll;
    private BaseModel<?> _plansUserSettingsRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PlansUserSettingsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlansUserSettings.class;
    }

    @Override
    public String getModelClassName() {
        return PlansUserSettings.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _planUserSettingsId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setPlanUserSettingsId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _planUserSettingsId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planUserSettingsId", getPlanUserSettingsId());
        attributes.put("userId", getUserId());
        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("sortColumn", getSortColumn());
        attributes.put("sortDirection", getSortDirection());
        attributes.put("filterEnabled", getFilterEnabled());
        attributes.put("filterPositionsAll", getFilterPositionsAll());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planUserSettingsId = (Long) attributes.get("planUserSettingsId");

        if (planUserSettingsId != null) {
            setPlanUserSettingsId(planUserSettingsId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        String sortColumn = (String) attributes.get("sortColumn");

        if (sortColumn != null) {
            setSortColumn(sortColumn);
        }

        String sortDirection = (String) attributes.get("sortDirection");

        if (sortDirection != null) {
            setSortDirection(sortDirection);
        }

        Boolean filterEnabled = (Boolean) attributes.get("filterEnabled");

        if (filterEnabled != null) {
            setFilterEnabled(filterEnabled);
        }

        Boolean filterPositionsAll = (Boolean) attributes.get(
                "filterPositionsAll");

        if (filterPositionsAll != null) {
            setFilterPositionsAll(filterPositionsAll);
        }
    }

    @Override
    public long getPlanUserSettingsId() {
        return _planUserSettingsId;
    }

    @Override
    public void setPlanUserSettingsId(long planUserSettingsId) {
        _planUserSettingsId = planUserSettingsId;

        if (_plansUserSettingsRemoteModel != null) {
            try {
                Class<?> clazz = _plansUserSettingsRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanUserSettingsId",
                        long.class);

                method.invoke(_plansUserSettingsRemoteModel, planUserSettingsId);
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

        if (_plansUserSettingsRemoteModel != null) {
            try {
                Class<?> clazz = _plansUserSettingsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_plansUserSettingsRemoteModel, userId);
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
    public long getPlanTypeId() {
        return _planTypeId;
    }

    @Override
    public void setPlanTypeId(long planTypeId) {
        _planTypeId = planTypeId;

        if (_plansUserSettingsRemoteModel != null) {
            try {
                Class<?> clazz = _plansUserSettingsRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanTypeId", long.class);

                method.invoke(_plansUserSettingsRemoteModel, planTypeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSortColumn() {
        return _sortColumn;
    }

    @Override
    public void setSortColumn(String sortColumn) {
        _sortColumn = sortColumn;

        if (_plansUserSettingsRemoteModel != null) {
            try {
                Class<?> clazz = _plansUserSettingsRemoteModel.getClass();

                Method method = clazz.getMethod("setSortColumn", String.class);

                method.invoke(_plansUserSettingsRemoteModel, sortColumn);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSortDirection() {
        return _sortDirection;
    }

    @Override
    public void setSortDirection(String sortDirection) {
        _sortDirection = sortDirection;

        if (_plansUserSettingsRemoteModel != null) {
            try {
                Class<?> clazz = _plansUserSettingsRemoteModel.getClass();

                Method method = clazz.getMethod("setSortDirection", String.class);

                method.invoke(_plansUserSettingsRemoteModel, sortDirection);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getFilterEnabled() {
        return _filterEnabled;
    }

    @Override
    public boolean isFilterEnabled() {
        return _filterEnabled;
    }

    @Override
    public void setFilterEnabled(boolean filterEnabled) {
        _filterEnabled = filterEnabled;

        if (_plansUserSettingsRemoteModel != null) {
            try {
                Class<?> clazz = _plansUserSettingsRemoteModel.getClass();

                Method method = clazz.getMethod("setFilterEnabled",
                        boolean.class);

                method.invoke(_plansUserSettingsRemoteModel, filterEnabled);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getFilterPositionsAll() {
        return _filterPositionsAll;
    }

    @Override
    public boolean isFilterPositionsAll() {
        return _filterPositionsAll;
    }

    @Override
    public void setFilterPositionsAll(boolean filterPositionsAll) {
        _filterPositionsAll = filterPositionsAll;

        if (_plansUserSettingsRemoteModel != null) {
            try {
                Class<?> clazz = _plansUserSettingsRemoteModel.getClass();

                Method method = clazz.getMethod("setFilterPositionsAll",
                        boolean.class);

                method.invoke(_plansUserSettingsRemoteModel, filterPositionsAll);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlansUserSettingsRemoteModel() {
        return _plansUserSettingsRemoteModel;
    }

    public void setPlansUserSettingsRemoteModel(
        BaseModel<?> plansUserSettingsRemoteModel) {
        _plansUserSettingsRemoteModel = plansUserSettingsRemoteModel;
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

        Class<?> remoteModelClass = _plansUserSettingsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_plansUserSettingsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlansUserSettingsLocalServiceUtil.addPlansUserSettings(this);
        } else {
            PlansUserSettingsLocalServiceUtil.updatePlansUserSettings(this);
        }
    }

    @Override
    public PlansUserSettings toEscapedModel() {
        return (PlansUserSettings) ProxyUtil.newProxyInstance(PlansUserSettings.class.getClassLoader(),
            new Class[] { PlansUserSettings.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlansUserSettingsClp clone = new PlansUserSettingsClp();

        clone.setPlanUserSettingsId(getPlanUserSettingsId());
        clone.setUserId(getUserId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setSortColumn(getSortColumn());
        clone.setSortDirection(getSortDirection());
        clone.setFilterEnabled(getFilterEnabled());
        clone.setFilterPositionsAll(getFilterPositionsAll());

        return clone;
    }

    @Override
    public int compareTo(PlansUserSettings plansUserSettings) {
        long primaryKey = plansUserSettings.getPrimaryKey();

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

        if (!(obj instanceof PlansUserSettingsClp)) {
            return false;
        }

        PlansUserSettingsClp plansUserSettings = (PlansUserSettingsClp) obj;

        long primaryKey = plansUserSettings.getPrimaryKey();

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

        sb.append("{planUserSettingsId=");
        sb.append(getPlanUserSettingsId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", sortColumn=");
        sb.append(getSortColumn());
        sb.append(", sortDirection=");
        sb.append(getSortDirection());
        sb.append(", filterEnabled=");
        sb.append(getFilterEnabled());
        sb.append(", filterPositionsAll=");
        sb.append(getFilterPositionsAll());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlansUserSettings");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planUserSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanUserSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sortColumn</column-name><column-value><![CDATA[");
        sb.append(getSortColumn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sortDirection</column-name><column-value><![CDATA[");
        sb.append(getSortDirection());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>filterEnabled</column-name><column-value><![CDATA[");
        sb.append(getFilterEnabled());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>filterPositionsAll</column-name><column-value><![CDATA[");
        sb.append(getFilterPositionsAll());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
