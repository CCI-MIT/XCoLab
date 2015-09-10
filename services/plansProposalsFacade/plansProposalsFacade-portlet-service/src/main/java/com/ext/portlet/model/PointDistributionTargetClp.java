package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PointDistributionTargetLocalServiceUtil;

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


public class PointDistributionTargetClp extends BaseModelImpl<PointDistributionTarget>
    implements PointDistributionTarget {
    private long _id;
    private long _contestId;
    private long _proposalId;
    private double _numberOfPoints;
    private long _pointTypeOverride;
    private BaseModel<?> _pointDistributionTargetRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PointDistributionTargetClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PointDistributionTarget.class;
    }

    @Override
    public String getModelClassName() {
        return PointDistributionTarget.class.getName();
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
        attributes.put("proposalId", getProposalId());
        attributes.put("numberOfPoints", getNumberOfPoints());
        attributes.put("pointTypeOverride", getPointTypeOverride());

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

        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Double numberOfPoints = (Double) attributes.get("numberOfPoints");

        if (numberOfPoints != null) {
            setNumberOfPoints(numberOfPoints);
        }

        Long pointTypeOverride = (Long) attributes.get("pointTypeOverride");

        if (pointTypeOverride != null) {
            setPointTypeOverride(pointTypeOverride);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_pointDistributionTargetRemoteModel != null) {
            try {
                Class<?> clazz = _pointDistributionTargetRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_pointDistributionTargetRemoteModel, id);
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

        if (_pointDistributionTargetRemoteModel != null) {
            try {
                Class<?> clazz = _pointDistributionTargetRemoteModel.getClass();

                Method method = clazz.getMethod("setContestId", long.class);

                method.invoke(_pointDistributionTargetRemoteModel, contestId);
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

        if (_pointDistributionTargetRemoteModel != null) {
            try {
                Class<?> clazz = _pointDistributionTargetRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_pointDistributionTargetRemoteModel, proposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNumberOfPoints() {
        return _numberOfPoints;
    }

    @Override
    public void setNumberOfPoints(double numberOfPoints) {
        _numberOfPoints = numberOfPoints;

        if (_pointDistributionTargetRemoteModel != null) {
            try {
                Class<?> clazz = _pointDistributionTargetRemoteModel.getClass();

                Method method = clazz.getMethod("setNumberOfPoints",
                        double.class);

                method.invoke(_pointDistributionTargetRemoteModel,
                    numberOfPoints);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPointTypeOverride() {
        return _pointTypeOverride;
    }

    @Override
    public void setPointTypeOverride(long pointTypeOverride) {
        _pointTypeOverride = pointTypeOverride;

        if (_pointDistributionTargetRemoteModel != null) {
            try {
                Class<?> clazz = _pointDistributionTargetRemoteModel.getClass();

                Method method = clazz.getMethod("setPointTypeOverride",
                        long.class);

                method.invoke(_pointDistributionTargetRemoteModel,
                    pointTypeOverride);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPointDistributionTargetRemoteModel() {
        return _pointDistributionTargetRemoteModel;
    }

    public void setPointDistributionTargetRemoteModel(
        BaseModel<?> pointDistributionTargetRemoteModel) {
        _pointDistributionTargetRemoteModel = pointDistributionTargetRemoteModel;
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

        Class<?> remoteModelClass = _pointDistributionTargetRemoteModel.getClass();

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

        Object returnValue = method.invoke(_pointDistributionTargetRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PointDistributionTargetLocalServiceUtil.addPointDistributionTarget(this);
        } else {
            PointDistributionTargetLocalServiceUtil.updatePointDistributionTarget(this);
        }
    }

    @Override
    public PointDistributionTarget toEscapedModel() {
        return (PointDistributionTarget) ProxyUtil.newProxyInstance(PointDistributionTarget.class.getClassLoader(),
            new Class[] { PointDistributionTarget.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PointDistributionTargetClp clone = new PointDistributionTargetClp();

        clone.setId(getId());
        clone.setContestId(getContestId());
        clone.setProposalId(getProposalId());
        clone.setNumberOfPoints(getNumberOfPoints());
        clone.setPointTypeOverride(getPointTypeOverride());

        return clone;
    }

    @Override
    public int compareTo(PointDistributionTarget pointDistributionTarget) {
        long primaryKey = pointDistributionTarget.getPrimaryKey();

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

        if (!(obj instanceof PointDistributionTargetClp)) {
            return false;
        }

        PointDistributionTargetClp pointDistributionTarget = (PointDistributionTargetClp) obj;

        long primaryKey = pointDistributionTarget.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", contestId=");
        sb.append(getContestId());
        sb.append(", proposalId=");
        sb.append(getProposalId());
        sb.append(", numberOfPoints=");
        sb.append(getNumberOfPoints());
        sb.append(", pointTypeOverride=");
        sb.append(getPointTypeOverride());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PointDistributionTarget");
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
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>numberOfPoints</column-name><column-value><![CDATA[");
        sb.append(getNumberOfPoints());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pointTypeOverride</column-name><column-value><![CDATA[");
        sb.append(getPointTypeOverride());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
