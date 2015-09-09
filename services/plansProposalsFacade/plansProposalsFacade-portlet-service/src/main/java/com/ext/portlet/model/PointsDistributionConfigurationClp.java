package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil;

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


public class PointsDistributionConfigurationClp extends BaseModelImpl<PointsDistributionConfiguration>
    implements PointsDistributionConfiguration {
    private long _id;
    private long _proposalId;
    private long _pointTypeId;
    private long _targetUserId;
    private String _targetUserUuid;
    private long _targetSubProposalId;
    private double _percentage;
    private long _creator;
    private Date _createDate;
    private BaseModel<?> _pointsDistributionConfigurationRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PointsDistributionConfigurationClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PointsDistributionConfiguration.class;
    }

    @Override
    public String getModelClassName() {
        return PointsDistributionConfiguration.class.getName();
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
        attributes.put("proposalId", getProposalId());
        attributes.put("pointTypeId", getPointTypeId());
        attributes.put("targetUserId", getTargetUserId());
        attributes.put("targetSubProposalId", getTargetSubProposalId());
        attributes.put("percentage", getPercentage());
        attributes.put("creator", getCreator());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Long pointTypeId = (Long) attributes.get("pointTypeId");

        if (pointTypeId != null) {
            setPointTypeId(pointTypeId);
        }

        Long targetUserId = (Long) attributes.get("targetUserId");

        if (targetUserId != null) {
            setTargetUserId(targetUserId);
        }

        Long targetSubProposalId = (Long) attributes.get("targetSubProposalId");

        if (targetSubProposalId != null) {
            setTargetSubProposalId(targetSubProposalId);
        }

        Double percentage = (Double) attributes.get("percentage");

        if (percentage != null) {
            setPercentage(percentage);
        }

        Long creator = (Long) attributes.get("creator");

        if (creator != null) {
            setCreator(creator);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_pointsDistributionConfigurationRemoteModel != null) {
            try {
                Class<?> clazz = _pointsDistributionConfigurationRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_pointsDistributionConfigurationRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getProposalId() {
        return _proposalId;
    }

    @Override
    public void setProposalId(long proposalId) {
        _proposalId = proposalId;

        if (_pointsDistributionConfigurationRemoteModel != null) {
            try {
                Class<?> clazz = _pointsDistributionConfigurationRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_pointsDistributionConfigurationRemoteModel,
                    proposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPointTypeId() {
        return _pointTypeId;
    }

    @Override
    public void setPointTypeId(long pointTypeId) {
        _pointTypeId = pointTypeId;

        if (_pointsDistributionConfigurationRemoteModel != null) {
            try {
                Class<?> clazz = _pointsDistributionConfigurationRemoteModel.getClass();

                Method method = clazz.getMethod("setPointTypeId", long.class);

                method.invoke(_pointsDistributionConfigurationRemoteModel,
                    pointTypeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getTargetUserId() {
        return _targetUserId;
    }

    @Override
    public void setTargetUserId(long targetUserId) {
        _targetUserId = targetUserId;

        if (_pointsDistributionConfigurationRemoteModel != null) {
            try {
                Class<?> clazz = _pointsDistributionConfigurationRemoteModel.getClass();

                Method method = clazz.getMethod("setTargetUserId", long.class);

                method.invoke(_pointsDistributionConfigurationRemoteModel,
                    targetUserId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTargetUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getTargetUserId(), "uuid",
            _targetUserUuid);
    }

    @Override
    public void setTargetUserUuid(String targetUserUuid) {
        _targetUserUuid = targetUserUuid;
    }

    @Override
    public long getTargetSubProposalId() {
        return _targetSubProposalId;
    }

    @Override
    public void setTargetSubProposalId(long targetSubProposalId) {
        _targetSubProposalId = targetSubProposalId;

        if (_pointsDistributionConfigurationRemoteModel != null) {
            try {
                Class<?> clazz = _pointsDistributionConfigurationRemoteModel.getClass();

                Method method = clazz.getMethod("setTargetSubProposalId",
                        long.class);

                method.invoke(_pointsDistributionConfigurationRemoteModel,
                    targetSubProposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPercentage() {
        return _percentage;
    }

    @Override
    public void setPercentage(double percentage) {
        _percentage = percentage;

        if (_pointsDistributionConfigurationRemoteModel != null) {
            try {
                Class<?> clazz = _pointsDistributionConfigurationRemoteModel.getClass();

                Method method = clazz.getMethod("setPercentage", double.class);

                method.invoke(_pointsDistributionConfigurationRemoteModel,
                    percentage);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCreator() {
        return _creator;
    }

    @Override
    public void setCreator(long creator) {
        _creator = creator;

        if (_pointsDistributionConfigurationRemoteModel != null) {
            try {
                Class<?> clazz = _pointsDistributionConfigurationRemoteModel.getClass();

                Method method = clazz.getMethod("setCreator", long.class);

                method.invoke(_pointsDistributionConfigurationRemoteModel,
                    creator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreateDate() {
        return _createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        _createDate = createDate;

        if (_pointsDistributionConfigurationRemoteModel != null) {
            try {
                Class<?> clazz = _pointsDistributionConfigurationRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_pointsDistributionConfigurationRemoteModel,
                    createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPointsDistributionConfigurationRemoteModel() {
        return _pointsDistributionConfigurationRemoteModel;
    }

    public void setPointsDistributionConfigurationRemoteModel(
        BaseModel<?> pointsDistributionConfigurationRemoteModel) {
        _pointsDistributionConfigurationRemoteModel = pointsDistributionConfigurationRemoteModel;
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

        Class<?> remoteModelClass = _pointsDistributionConfigurationRemoteModel.getClass();

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

        Object returnValue = method.invoke(_pointsDistributionConfigurationRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PointsDistributionConfigurationLocalServiceUtil.addPointsDistributionConfiguration(this);
        } else {
            PointsDistributionConfigurationLocalServiceUtil.updatePointsDistributionConfiguration(this);
        }
    }

    @Override
    public PointsDistributionConfiguration toEscapedModel() {
        return (PointsDistributionConfiguration) ProxyUtil.newProxyInstance(PointsDistributionConfiguration.class.getClassLoader(),
            new Class[] { PointsDistributionConfiguration.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PointsDistributionConfigurationClp clone = new PointsDistributionConfigurationClp();

        clone.setId(getId());
        clone.setProposalId(getProposalId());
        clone.setPointTypeId(getPointTypeId());
        clone.setTargetUserId(getTargetUserId());
        clone.setTargetSubProposalId(getTargetSubProposalId());
        clone.setPercentage(getPercentage());
        clone.setCreator(getCreator());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    @Override
    public int compareTo(
        PointsDistributionConfiguration pointsDistributionConfiguration) {
        long primaryKey = pointsDistributionConfiguration.getPrimaryKey();

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

        if (!(obj instanceof PointsDistributionConfigurationClp)) {
            return false;
        }

        PointsDistributionConfigurationClp pointsDistributionConfiguration = (PointsDistributionConfigurationClp) obj;

        long primaryKey = pointsDistributionConfiguration.getPrimaryKey();

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
        StringBundler sb = new StringBundler(17);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", proposalId=");
        sb.append(getProposalId());
        sb.append(", pointTypeId=");
        sb.append(getPointTypeId());
        sb.append(", targetUserId=");
        sb.append(getTargetUserId());
        sb.append(", targetSubProposalId=");
        sb.append(getTargetSubProposalId());
        sb.append(", percentage=");
        sb.append(getPercentage());
        sb.append(", creator=");
        sb.append(getCreator());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PointsDistributionConfiguration");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pointTypeId</column-name><column-value><![CDATA[");
        sb.append(getPointTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>targetUserId</column-name><column-value><![CDATA[");
        sb.append(getTargetUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>targetSubProposalId</column-name><column-value><![CDATA[");
        sb.append(getTargetSubProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>percentage</column-name><column-value><![CDATA[");
        sb.append(getPercentage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>creator</column-name><column-value><![CDATA[");
        sb.append(getCreator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
