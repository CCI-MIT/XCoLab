package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ContestPhaseClp extends BaseModelImpl<ContestPhase>
    implements ContestPhase {
    private long _ContestPhasePK;
    private long _ContestPK;
    private long _ContestPhaseType;
    private long _contestScheduleId;
    private boolean _fellowScreeningActive;
    private String _contestPhaseAutopromote;
    private String _ContestPhaseDescriptionOverride;
    private boolean _phaseActiveOverride;
    private boolean _phaseInactiveOverride;
    private Date _PhaseStartDate;
    private Date _PhaseEndDate;
    private Date _PhaseBufferEndDated;
    private String _nextStatus;
    private Date _created;
    private Date _updated;
    private long _authorId;
    private BaseModel<?> _contestPhaseRemoteModel;

    public ContestPhaseClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContestPhase.class;
    }

    @Override
    public String getModelClassName() {
        return ContestPhase.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _ContestPhasePK;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setContestPhasePK(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ContestPhasePK;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ContestPhasePK", getContestPhasePK());
        attributes.put("ContestPK", getContestPK());
        attributes.put("ContestPhaseType", getContestPhaseType());
        attributes.put("contestScheduleId", getContestScheduleId());
        attributes.put("fellowScreeningActive", getFellowScreeningActive());
        attributes.put("contestPhaseAutopromote", getContestPhaseAutopromote());
        attributes.put("ContestPhaseDescriptionOverride",
            getContestPhaseDescriptionOverride());
        attributes.put("phaseActiveOverride", getPhaseActiveOverride());
        attributes.put("phaseInactiveOverride", getPhaseInactiveOverride());
        attributes.put("PhaseStartDate", getPhaseStartDate());
        attributes.put("PhaseEndDate", getPhaseEndDate());
        attributes.put("PhaseBufferEndDated", getPhaseBufferEndDated());
        attributes.put("nextStatus", getNextStatus());
        attributes.put("created", getCreated());
        attributes.put("updated", getUpdated());
        attributes.put("authorId", getAuthorId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long ContestPhasePK = (Long) attributes.get("ContestPhasePK");

        if (ContestPhasePK != null) {
            setContestPhasePK(ContestPhasePK);
        }

        Long ContestPK = (Long) attributes.get("ContestPK");

        if (ContestPK != null) {
            setContestPK(ContestPK);
        }

        Long ContestPhaseType = (Long) attributes.get("ContestPhaseType");

        if (ContestPhaseType != null) {
            setContestPhaseType(ContestPhaseType);
        }

        Long contestScheduleId = (Long) attributes.get("contestScheduleId");

        if (contestScheduleId != null) {
            setContestScheduleId(contestScheduleId);
        }

        Boolean fellowScreeningActive = (Boolean) attributes.get(
                "fellowScreeningActive");

        if (fellowScreeningActive != null) {
            setFellowScreeningActive(fellowScreeningActive);
        }

        String contestPhaseAutopromote = (String) attributes.get(
                "contestPhaseAutopromote");

        if (contestPhaseAutopromote != null) {
            setContestPhaseAutopromote(contestPhaseAutopromote);
        }

        String ContestPhaseDescriptionOverride = (String) attributes.get(
                "ContestPhaseDescriptionOverride");

        if (ContestPhaseDescriptionOverride != null) {
            setContestPhaseDescriptionOverride(ContestPhaseDescriptionOverride);
        }

        Boolean phaseActiveOverride = (Boolean) attributes.get(
                "phaseActiveOverride");

        if (phaseActiveOverride != null) {
            setPhaseActiveOverride(phaseActiveOverride);
        }

        Boolean phaseInactiveOverride = (Boolean) attributes.get(
                "phaseInactiveOverride");

        if (phaseInactiveOverride != null) {
            setPhaseInactiveOverride(phaseInactiveOverride);
        }

        Date PhaseStartDate = (Date) attributes.get("PhaseStartDate");

        if (PhaseStartDate != null) {
            setPhaseStartDate(PhaseStartDate);
        }

        Date PhaseEndDate = (Date) attributes.get("PhaseEndDate");

        if (PhaseEndDate != null) {
            setPhaseEndDate(PhaseEndDate);
        }

        Date PhaseBufferEndDated = (Date) attributes.get("PhaseBufferEndDated");

        if (PhaseBufferEndDated != null) {
            setPhaseBufferEndDated(PhaseBufferEndDated);
        }

        String nextStatus = (String) attributes.get("nextStatus");

        if (nextStatus != null) {
            setNextStatus(nextStatus);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Date updated = (Date) attributes.get("updated");

        if (updated != null) {
            setUpdated(updated);
        }

        Long authorId = (Long) attributes.get("authorId");

        if (authorId != null) {
            setAuthorId(authorId);
        }
    }

    @Override
    public long getContestPhasePK() {
        return _ContestPhasePK;
    }

    @Override
    public void setContestPhasePK(long ContestPhasePK) {
        _ContestPhasePK = ContestPhasePK;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPhasePK", long.class);

                method.invoke(_contestPhaseRemoteModel, ContestPhasePK);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getContestPK() {
        return _ContestPK;
    }

    @Override
    public void setContestPK(long ContestPK) {
        _ContestPK = ContestPK;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPK", long.class);

                method.invoke(_contestPhaseRemoteModel, ContestPK);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getContestPhaseType() {
        return _ContestPhaseType;
    }

    @Override
    public void setContestPhaseType(long ContestPhaseType) {
        _ContestPhaseType = ContestPhaseType;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPhaseType",
                        long.class);

                method.invoke(_contestPhaseRemoteModel, ContestPhaseType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getContestScheduleId() {
        return _contestScheduleId;
    }

    @Override
    public void setContestScheduleId(long contestScheduleId) {
        _contestScheduleId = contestScheduleId;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setContestScheduleId",
                        long.class);

                method.invoke(_contestPhaseRemoteModel, contestScheduleId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getFellowScreeningActive() {
        return _fellowScreeningActive;
    }

    @Override
    public boolean isFellowScreeningActive() {
        return _fellowScreeningActive;
    }

    @Override
    public void setFellowScreeningActive(boolean fellowScreeningActive) {
        _fellowScreeningActive = fellowScreeningActive;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setFellowScreeningActive",
                        boolean.class);

                method.invoke(_contestPhaseRemoteModel, fellowScreeningActive);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContestPhaseAutopromote() {
        return _contestPhaseAutopromote;
    }

    @Override
    public void setContestPhaseAutopromote(String contestPhaseAutopromote) {
        _contestPhaseAutopromote = contestPhaseAutopromote;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPhaseAutopromote",
                        String.class);

                method.invoke(_contestPhaseRemoteModel, contestPhaseAutopromote);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContestPhaseDescriptionOverride() {
        return _ContestPhaseDescriptionOverride;
    }

    @Override
    public void setContestPhaseDescriptionOverride(
        String ContestPhaseDescriptionOverride) {
        _ContestPhaseDescriptionOverride = ContestPhaseDescriptionOverride;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPhaseDescriptionOverride",
                        String.class);

                method.invoke(_contestPhaseRemoteModel,
                    ContestPhaseDescriptionOverride);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getPhaseActiveOverride() {
        return _phaseActiveOverride;
    }

    @Override
    public boolean isPhaseActiveOverride() {
        return _phaseActiveOverride;
    }

    @Override
    public void setPhaseActiveOverride(boolean phaseActiveOverride) {
        _phaseActiveOverride = phaseActiveOverride;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setPhaseActiveOverride",
                        boolean.class);

                method.invoke(_contestPhaseRemoteModel, phaseActiveOverride);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getPhaseInactiveOverride() {
        return _phaseInactiveOverride;
    }

    @Override
    public boolean isPhaseInactiveOverride() {
        return _phaseInactiveOverride;
    }

    @Override
    public void setPhaseInactiveOverride(boolean phaseInactiveOverride) {
        _phaseInactiveOverride = phaseInactiveOverride;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setPhaseInactiveOverride",
                        boolean.class);

                method.invoke(_contestPhaseRemoteModel, phaseInactiveOverride);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPhaseStartDate() {
        return _PhaseStartDate;
    }

    @Override
    public void setPhaseStartDate(Date PhaseStartDate) {
        _PhaseStartDate = PhaseStartDate;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setPhaseStartDate", Date.class);

                method.invoke(_contestPhaseRemoteModel, PhaseStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPhaseEndDate() {
        return _PhaseEndDate;
    }

    @Override
    public void setPhaseEndDate(Date PhaseEndDate) {
        _PhaseEndDate = PhaseEndDate;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setPhaseEndDate", Date.class);

                method.invoke(_contestPhaseRemoteModel, PhaseEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPhaseBufferEndDated() {
        return _PhaseBufferEndDated;
    }

    @Override
    public void setPhaseBufferEndDated(Date PhaseBufferEndDated) {
        _PhaseBufferEndDated = PhaseBufferEndDated;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setPhaseBufferEndDated",
                        Date.class);

                method.invoke(_contestPhaseRemoteModel, PhaseBufferEndDated);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNextStatus() {
        return _nextStatus;
    }

    @Override
    public void setNextStatus(String nextStatus) {
        _nextStatus = nextStatus;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setNextStatus", String.class);

                method.invoke(_contestPhaseRemoteModel, nextStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreated() {
        return _created;
    }

    @Override
    public void setCreated(Date created) {
        _created = created;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setCreated", Date.class);

                method.invoke(_contestPhaseRemoteModel, created);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getUpdated() {
        return _updated;
    }

    @Override
    public void setUpdated(Date updated) {
        _updated = updated;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdated", Date.class);

                method.invoke(_contestPhaseRemoteModel, updated);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getAuthorId() {
        return _authorId;
    }

    @Override
    public void setAuthorId(long authorId) {
        _authorId = authorId;

        if (_contestPhaseRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorId", long.class);

                method.invoke(_contestPhaseRemoteModel, authorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContestPhaseRemoteModel() {
        return _contestPhaseRemoteModel;
    }

    public void setContestPhaseRemoteModel(BaseModel<?> contestPhaseRemoteModel) {
        _contestPhaseRemoteModel = contestPhaseRemoteModel;
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

        Class<?> remoteModelClass = _contestPhaseRemoteModel.getClass();

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

        Object returnValue = method.invoke(_contestPhaseRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestPhaseLocalServiceUtil.addContestPhase(this);
        } else {
            ContestPhaseLocalServiceUtil.updateContestPhase(this);
        }
    }

    @Override
    public ContestPhase toEscapedModel() {
        return (ContestPhase) ProxyUtil.newProxyInstance(ContestPhase.class.getClassLoader(),
            new Class[] { ContestPhase.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestPhaseClp clone = new ContestPhaseClp();

        clone.setContestPhasePK(getContestPhasePK());
        clone.setContestPK(getContestPK());
        clone.setContestPhaseType(getContestPhaseType());
        clone.setContestScheduleId(getContestScheduleId());
        clone.setFellowScreeningActive(getFellowScreeningActive());
        clone.setContestPhaseAutopromote(getContestPhaseAutopromote());
        clone.setContestPhaseDescriptionOverride(getContestPhaseDescriptionOverride());
        clone.setPhaseActiveOverride(getPhaseActiveOverride());
        clone.setPhaseInactiveOverride(getPhaseInactiveOverride());
        clone.setPhaseStartDate(getPhaseStartDate());
        clone.setPhaseEndDate(getPhaseEndDate());
        clone.setPhaseBufferEndDated(getPhaseBufferEndDated());
        clone.setNextStatus(getNextStatus());
        clone.setCreated(getCreated());
        clone.setUpdated(getUpdated());
        clone.setAuthorId(getAuthorId());

        return clone;
    }

    @Override
    public int compareTo(ContestPhase contestPhase) {
        int value = 0;

        value = DateUtil.compareTo(getPhaseStartDate(),
                contestPhase.getPhaseStartDate());

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

        if (!(obj instanceof ContestPhaseClp)) {
            return false;
        }

        ContestPhaseClp contestPhase = (ContestPhaseClp) obj;

        long primaryKey = contestPhase.getPrimaryKey();

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
        StringBundler sb = new StringBundler(33);

        sb.append("{ContestPhasePK=");
        sb.append(getContestPhasePK());
        sb.append(", ContestPK=");
        sb.append(getContestPK());
        sb.append(", ContestPhaseType=");
        sb.append(getContestPhaseType());
        sb.append(", contestScheduleId=");
        sb.append(getContestScheduleId());
        sb.append(", fellowScreeningActive=");
        sb.append(getFellowScreeningActive());
        sb.append(", contestPhaseAutopromote=");
        sb.append(getContestPhaseAutopromote());
        sb.append(", ContestPhaseDescriptionOverride=");
        sb.append(getContestPhaseDescriptionOverride());
        sb.append(", phaseActiveOverride=");
        sb.append(getPhaseActiveOverride());
        sb.append(", phaseInactiveOverride=");
        sb.append(getPhaseInactiveOverride());
        sb.append(", PhaseStartDate=");
        sb.append(getPhaseStartDate());
        sb.append(", PhaseEndDate=");
        sb.append(getPhaseEndDate());
        sb.append(", PhaseBufferEndDated=");
        sb.append(getPhaseBufferEndDated());
        sb.append(", nextStatus=");
        sb.append(getNextStatus());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(52);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ContestPhase");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ContestPhasePK</column-name><column-value><![CDATA[");
        sb.append(getContestPhasePK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPK</column-name><column-value><![CDATA[");
        sb.append(getContestPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPhaseType</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestScheduleId</column-name><column-value><![CDATA[");
        sb.append(getContestScheduleId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fellowScreeningActive</column-name><column-value><![CDATA[");
        sb.append(getFellowScreeningActive());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestPhaseAutopromote</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseAutopromote());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPhaseDescriptionOverride</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseDescriptionOverride());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>phaseActiveOverride</column-name><column-value><![CDATA[");
        sb.append(getPhaseActiveOverride());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>phaseInactiveOverride</column-name><column-value><![CDATA[");
        sb.append(getPhaseInactiveOverride());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>PhaseStartDate</column-name><column-value><![CDATA[");
        sb.append(getPhaseStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>PhaseEndDate</column-name><column-value><![CDATA[");
        sb.append(getPhaseEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>PhaseBufferEndDated</column-name><column-value><![CDATA[");
        sb.append(getPhaseBufferEndDated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nextStatus</column-name><column-value><![CDATA[");
        sb.append(getNextStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
