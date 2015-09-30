package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlansFilterLocalServiceUtil;
import com.ext.portlet.service.persistence.PlansFilterPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PlansFilterClp extends BaseModelImpl<PlansFilter>
    implements PlansFilter {
    private long _userId;
    private String _userUuid;
    private long _planTypeId;
    private String _name;
    private String _creator;
    private String _description;
    private Double _CO2From;
    private Double _CO2To;
    private Double _votesFrom;
    private Double _votesTo;
    private Double _damageFrom;
    private Double _damageTo;
    private Double _mitigationFrom;
    private Double _mitigationTo;
    private Date _dateFrom;
    private Date _dateTo;
    private boolean _filterPositionsAll;
    private boolean _enabled;
    private BaseModel<?> _plansFilterRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PlansFilterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlansFilter.class;
    }

    @Override
    public String getModelClassName() {
        return PlansFilter.class.getName();
    }

    @Override
    public PlansFilterPK getPrimaryKey() {
        return new PlansFilterPK(_userId, _planTypeId);
    }

    @Override
    public void setPrimaryKey(PlansFilterPK primaryKey) {
        setUserId(primaryKey.userId);
        setPlanTypeId(primaryKey.planTypeId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new PlansFilterPK(_userId, _planTypeId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlansFilterPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("userId", getUserId());
        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("name", getName());
        attributes.put("creator", getCreator());
        attributes.put("description", getDescription());
        attributes.put("CO2From", getCO2From());
        attributes.put("CO2To", getCO2To());
        attributes.put("votesFrom", getVotesFrom());
        attributes.put("votesTo", getVotesTo());
        attributes.put("damageFrom", getDamageFrom());
        attributes.put("damageTo", getDamageTo());
        attributes.put("mitigationFrom", getMitigationFrom());
        attributes.put("mitigationTo", getMitigationTo());
        attributes.put("dateFrom", getDateFrom());
        attributes.put("dateTo", getDateTo());
        attributes.put("filterPositionsAll", getFilterPositionsAll());
        attributes.put("enabled", getEnabled());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String creator = (String) attributes.get("creator");

        if (creator != null) {
            setCreator(creator);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Double CO2From = (Double) attributes.get("CO2From");

        if (CO2From != null) {
            setCO2From(CO2From);
        }

        Double CO2To = (Double) attributes.get("CO2To");

        if (CO2To != null) {
            setCO2To(CO2To);
        }

        Double votesFrom = (Double) attributes.get("votesFrom");

        if (votesFrom != null) {
            setVotesFrom(votesFrom);
        }

        Double votesTo = (Double) attributes.get("votesTo");

        if (votesTo != null) {
            setVotesTo(votesTo);
        }

        Double damageFrom = (Double) attributes.get("damageFrom");

        if (damageFrom != null) {
            setDamageFrom(damageFrom);
        }

        Double damageTo = (Double) attributes.get("damageTo");

        if (damageTo != null) {
            setDamageTo(damageTo);
        }

        Double mitigationFrom = (Double) attributes.get("mitigationFrom");

        if (mitigationFrom != null) {
            setMitigationFrom(mitigationFrom);
        }

        Double mitigationTo = (Double) attributes.get("mitigationTo");

        if (mitigationTo != null) {
            setMitigationTo(mitigationTo);
        }

        Date dateFrom = (Date) attributes.get("dateFrom");

        if (dateFrom != null) {
            setDateFrom(dateFrom);
        }

        Date dateTo = (Date) attributes.get("dateTo");

        if (dateTo != null) {
            setDateTo(dateTo);
        }

        Boolean filterPositionsAll = (Boolean) attributes.get(
                "filterPositionsAll");

        if (filterPositionsAll != null) {
            setFilterPositionsAll(filterPositionsAll);
        }

        Boolean enabled = (Boolean) attributes.get("enabled");

        if (enabled != null) {
            setEnabled(enabled);
        }
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_plansFilterRemoteModel, userId);
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

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanTypeId", long.class);

                method.invoke(_plansFilterRemoteModel, planTypeId);
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

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_plansFilterRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCreator() {
        return _creator;
    }

    @Override
    public void setCreator(String creator) {
        _creator = creator;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreator", String.class);

                method.invoke(_plansFilterRemoteModel, creator);
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

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_plansFilterRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Double getCO2From() {
        return _CO2From;
    }

    @Override
    public void setCO2From(Double CO2From) {
        _CO2From = CO2From;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setCO2From", Double.class);

                method.invoke(_plansFilterRemoteModel, CO2From);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Double getCO2To() {
        return _CO2To;
    }

    @Override
    public void setCO2To(Double CO2To) {
        _CO2To = CO2To;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setCO2To", Double.class);

                method.invoke(_plansFilterRemoteModel, CO2To);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Double getVotesFrom() {
        return _votesFrom;
    }

    @Override
    public void setVotesFrom(Double votesFrom) {
        _votesFrom = votesFrom;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setVotesFrom", Double.class);

                method.invoke(_plansFilterRemoteModel, votesFrom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Double getVotesTo() {
        return _votesTo;
    }

    @Override
    public void setVotesTo(Double votesTo) {
        _votesTo = votesTo;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setVotesTo", Double.class);

                method.invoke(_plansFilterRemoteModel, votesTo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Double getDamageFrom() {
        return _damageFrom;
    }

    @Override
    public void setDamageFrom(Double damageFrom) {
        _damageFrom = damageFrom;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setDamageFrom", Double.class);

                method.invoke(_plansFilterRemoteModel, damageFrom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Double getDamageTo() {
        return _damageTo;
    }

    @Override
    public void setDamageTo(Double damageTo) {
        _damageTo = damageTo;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setDamageTo", Double.class);

                method.invoke(_plansFilterRemoteModel, damageTo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Double getMitigationFrom() {
        return _mitigationFrom;
    }

    @Override
    public void setMitigationFrom(Double mitigationFrom) {
        _mitigationFrom = mitigationFrom;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setMitigationFrom",
                        Double.class);

                method.invoke(_plansFilterRemoteModel, mitigationFrom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Double getMitigationTo() {
        return _mitigationTo;
    }

    @Override
    public void setMitigationTo(Double mitigationTo) {
        _mitigationTo = mitigationTo;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setMitigationTo", Double.class);

                method.invoke(_plansFilterRemoteModel, mitigationTo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getDateFrom() {
        return _dateFrom;
    }

    @Override
    public void setDateFrom(Date dateFrom) {
        _dateFrom = dateFrom;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setDateFrom", Date.class);

                method.invoke(_plansFilterRemoteModel, dateFrom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getDateTo() {
        return _dateTo;
    }

    @Override
    public void setDateTo(Date dateTo) {
        _dateTo = dateTo;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setDateTo", Date.class);

                method.invoke(_plansFilterRemoteModel, dateTo);
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

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setFilterPositionsAll",
                        boolean.class);

                method.invoke(_plansFilterRemoteModel, filterPositionsAll);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getEnabled() {
        return _enabled;
    }

    @Override
    public boolean isEnabled() {
        return _enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        _enabled = enabled;

        if (_plansFilterRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setEnabled", boolean.class);

                method.invoke(_plansFilterRemoteModel, enabled);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlansFilterRemoteModel() {
        return _plansFilterRemoteModel;
    }

    public void setPlansFilterRemoteModel(BaseModel<?> plansFilterRemoteModel) {
        _plansFilterRemoteModel = plansFilterRemoteModel;
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

        Class<?> remoteModelClass = _plansFilterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_plansFilterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlansFilterLocalServiceUtil.addPlansFilter(this);
        } else {
            PlansFilterLocalServiceUtil.updatePlansFilter(this);
        }
    }

    @Override
    public PlansFilter toEscapedModel() {
        return (PlansFilter) ProxyUtil.newProxyInstance(PlansFilter.class.getClassLoader(),
            new Class[] { PlansFilter.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlansFilterClp clone = new PlansFilterClp();

        clone.setUserId(getUserId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setName(getName());
        clone.setCreator(getCreator());
        clone.setDescription(getDescription());
        clone.setCO2From(getCO2From());
        clone.setCO2To(getCO2To());
        clone.setVotesFrom(getVotesFrom());
        clone.setVotesTo(getVotesTo());
        clone.setDamageFrom(getDamageFrom());
        clone.setDamageTo(getDamageTo());
        clone.setMitigationFrom(getMitigationFrom());
        clone.setMitigationTo(getMitigationTo());
        clone.setDateFrom(getDateFrom());
        clone.setDateTo(getDateTo());
        clone.setFilterPositionsAll(getFilterPositionsAll());
        clone.setEnabled(getEnabled());

        return clone;
    }

    @Override
    public int compareTo(PlansFilter plansFilter) {
        PlansFilterPK primaryKey = plansFilter.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlansFilterClp)) {
            return false;
        }

        PlansFilterClp plansFilter = (PlansFilterClp) obj;

        PlansFilterPK primaryKey = plansFilter.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
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
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(35);

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", creator=");
        sb.append(getCreator());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", CO2From=");
        sb.append(getCO2From());
        sb.append(", CO2To=");
        sb.append(getCO2To());
        sb.append(", votesFrom=");
        sb.append(getVotesFrom());
        sb.append(", votesTo=");
        sb.append(getVotesTo());
        sb.append(", damageFrom=");
        sb.append(getDamageFrom());
        sb.append(", damageTo=");
        sb.append(getDamageTo());
        sb.append(", mitigationFrom=");
        sb.append(getMitigationFrom());
        sb.append(", mitigationTo=");
        sb.append(getMitigationTo());
        sb.append(", dateFrom=");
        sb.append(getDateFrom());
        sb.append(", dateTo=");
        sb.append(getDateTo());
        sb.append(", filterPositionsAll=");
        sb.append(getFilterPositionsAll());
        sb.append(", enabled=");
        sb.append(getEnabled());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(55);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlansFilter");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>creator</column-name><column-value><![CDATA[");
        sb.append(getCreator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>CO2From</column-name><column-value><![CDATA[");
        sb.append(getCO2From());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>CO2To</column-name><column-value><![CDATA[");
        sb.append(getCO2To());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>votesFrom</column-name><column-value><![CDATA[");
        sb.append(getVotesFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>votesTo</column-name><column-value><![CDATA[");
        sb.append(getVotesTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>damageFrom</column-name><column-value><![CDATA[");
        sb.append(getDamageFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>damageTo</column-name><column-value><![CDATA[");
        sb.append(getDamageTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mitigationFrom</column-name><column-value><![CDATA[");
        sb.append(getMitigationFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mitigationTo</column-name><column-value><![CDATA[");
        sb.append(getMitigationTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dateFrom</column-name><column-value><![CDATA[");
        sb.append(getDateFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dateTo</column-name><column-value><![CDATA[");
        sb.append(getDateTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>filterPositionsAll</column-name><column-value><![CDATA[");
        sb.append(getFilterPositionsAll());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>enabled</column-name><column-value><![CDATA[");
        sb.append(getEnabled());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
