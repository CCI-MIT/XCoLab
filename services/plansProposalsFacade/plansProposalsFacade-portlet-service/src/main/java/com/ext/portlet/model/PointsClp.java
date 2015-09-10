package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PointsLocalServiceUtil;

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


public class PointsClp extends BaseModelImpl<Points> implements Points {
    private long _id;
    private long _proposalId;
    private long _userId;
    private String _userUuid;
    private double _materializedPoints;
    private double _hypotheticalPoints;
    private long _pointsSourceId;
    private long _originatingContestPK;
    private long _originatingProposalId;
    private BaseModel<?> _pointsRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PointsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return Points.class;
    }

    @Override
    public String getModelClassName() {
        return Points.class.getName();
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
        attributes.put("userId", getUserId());
        attributes.put("materializedPoints", getMaterializedPoints());
        attributes.put("hypotheticalPoints", getHypotheticalPoints());
        attributes.put("pointsSourceId", getPointsSourceId());
        attributes.put("originatingContestPK", getOriginatingContestPK());
        attributes.put("originatingProposalId", getOriginatingProposalId());

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

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Double materializedPoints = (Double) attributes.get(
                "materializedPoints");

        if (materializedPoints != null) {
            setMaterializedPoints(materializedPoints);
        }

        Double hypotheticalPoints = (Double) attributes.get(
                "hypotheticalPoints");

        if (hypotheticalPoints != null) {
            setHypotheticalPoints(hypotheticalPoints);
        }

        Long pointsSourceId = (Long) attributes.get("pointsSourceId");

        if (pointsSourceId != null) {
            setPointsSourceId(pointsSourceId);
        }

        Long originatingContestPK = (Long) attributes.get(
                "originatingContestPK");

        if (originatingContestPK != null) {
            setOriginatingContestPK(originatingContestPK);
        }

        Long originatingProposalId = (Long) attributes.get(
                "originatingProposalId");

        if (originatingProposalId != null) {
            setOriginatingProposalId(originatingProposalId);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_pointsRemoteModel != null) {
            try {
                Class<?> clazz = _pointsRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_pointsRemoteModel, id);
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

        if (_pointsRemoteModel != null) {
            try {
                Class<?> clazz = _pointsRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_pointsRemoteModel, proposalId);
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

        if (_pointsRemoteModel != null) {
            try {
                Class<?> clazz = _pointsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_pointsRemoteModel, userId);
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
    public double getMaterializedPoints() {
        return _materializedPoints;
    }

    @Override
    public void setMaterializedPoints(double materializedPoints) {
        _materializedPoints = materializedPoints;

        if (_pointsRemoteModel != null) {
            try {
                Class<?> clazz = _pointsRemoteModel.getClass();

                Method method = clazz.getMethod("setMaterializedPoints",
                        double.class);

                method.invoke(_pointsRemoteModel, materializedPoints);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getHypotheticalPoints() {
        return _hypotheticalPoints;
    }

    @Override
    public void setHypotheticalPoints(double hypotheticalPoints) {
        _hypotheticalPoints = hypotheticalPoints;

        if (_pointsRemoteModel != null) {
            try {
                Class<?> clazz = _pointsRemoteModel.getClass();

                Method method = clazz.getMethod("setHypotheticalPoints",
                        double.class);

                method.invoke(_pointsRemoteModel, hypotheticalPoints);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPointsSourceId() {
        return _pointsSourceId;
    }

    @Override
    public void setPointsSourceId(long pointsSourceId) {
        _pointsSourceId = pointsSourceId;

        if (_pointsRemoteModel != null) {
            try {
                Class<?> clazz = _pointsRemoteModel.getClass();

                Method method = clazz.getMethod("setPointsSourceId", long.class);

                method.invoke(_pointsRemoteModel, pointsSourceId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getOriginatingContestPK() {
        return _originatingContestPK;
    }

    @Override
    public void setOriginatingContestPK(long originatingContestPK) {
        _originatingContestPK = originatingContestPK;

        if (_pointsRemoteModel != null) {
            try {
                Class<?> clazz = _pointsRemoteModel.getClass();

                Method method = clazz.getMethod("setOriginatingContestPK",
                        long.class);

                method.invoke(_pointsRemoteModel, originatingContestPK);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getOriginatingProposalId() {
        return _originatingProposalId;
    }

    @Override
    public void setOriginatingProposalId(long originatingProposalId) {
        _originatingProposalId = originatingProposalId;

        if (_pointsRemoteModel != null) {
            try {
                Class<?> clazz = _pointsRemoteModel.getClass();

                Method method = clazz.getMethod("setOriginatingProposalId",
                        long.class);

                method.invoke(_pointsRemoteModel, originatingProposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPointsRemoteModel() {
        return _pointsRemoteModel;
    }

    public void setPointsRemoteModel(BaseModel<?> pointsRemoteModel) {
        _pointsRemoteModel = pointsRemoteModel;
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

        Class<?> remoteModelClass = _pointsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_pointsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PointsLocalServiceUtil.addPoints(this);
        } else {
            PointsLocalServiceUtil.updatePoints(this);
        }
    }

    @Override
    public Points toEscapedModel() {
        return (Points) ProxyUtil.newProxyInstance(Points.class.getClassLoader(),
            new Class[] { Points.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PointsClp clone = new PointsClp();

        clone.setId(getId());
        clone.setProposalId(getProposalId());
        clone.setUserId(getUserId());
        clone.setMaterializedPoints(getMaterializedPoints());
        clone.setHypotheticalPoints(getHypotheticalPoints());
        clone.setPointsSourceId(getPointsSourceId());
        clone.setOriginatingContestPK(getOriginatingContestPK());
        clone.setOriginatingProposalId(getOriginatingProposalId());

        return clone;
    }

    @Override
    public int compareTo(Points points) {
        long primaryKey = points.getPrimaryKey();

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

        if (!(obj instanceof PointsClp)) {
            return false;
        }

        PointsClp points = (PointsClp) obj;

        long primaryKey = points.getPrimaryKey();

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
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", materializedPoints=");
        sb.append(getMaterializedPoints());
        sb.append(", hypotheticalPoints=");
        sb.append(getHypotheticalPoints());
        sb.append(", pointsSourceId=");
        sb.append(getPointsSourceId());
        sb.append(", originatingContestPK=");
        sb.append(getOriginatingContestPK());
        sb.append(", originatingProposalId=");
        sb.append(getOriginatingProposalId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.Points");
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
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>materializedPoints</column-name><column-value><![CDATA[");
        sb.append(getMaterializedPoints());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hypotheticalPoints</column-name><column-value><![CDATA[");
        sb.append(getHypotheticalPoints());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pointsSourceId</column-name><column-value><![CDATA[");
        sb.append(getPointsSourceId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>originatingContestPK</column-name><column-value><![CDATA[");
        sb.append(getOriginatingContestPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>originatingProposalId</column-name><column-value><![CDATA[");
        sb.append(getOriginatingProposalId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
