package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PointTypeLocalServiceUtil;

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


public class PointTypeClp extends BaseModelImpl<PointType> implements PointType {
    private long _id;
    private long _parentPointTypeId;
    private double _percentageOfParent;
    private String _distributionStrategy;
    private String _receiverLimitationStrategy;
    private String _name;
    private long _sort;
    private BaseModel<?> _pointTypeRemoteModel;

    public PointTypeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PointType.class;
    }

    @Override
    public String getModelClassName() {
        return PointType.class.getName();
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
        attributes.put("parentPointTypeId", getParentPointTypeId());
        attributes.put("percentageOfParent", getPercentageOfParent());
        attributes.put("distributionStrategy", getDistributionStrategy());
        attributes.put("receiverLimitationStrategy",
            getReceiverLimitationStrategy());
        attributes.put("name", getName());
        attributes.put("sort", getSort());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long parentPointTypeId = (Long) attributes.get("parentPointTypeId");

        if (parentPointTypeId != null) {
            setParentPointTypeId(parentPointTypeId);
        }

        Double percentageOfParent = (Double) attributes.get(
                "percentageOfParent");

        if (percentageOfParent != null) {
            setPercentageOfParent(percentageOfParent);
        }

        String distributionStrategy = (String) attributes.get(
                "distributionStrategy");

        if (distributionStrategy != null) {
            setDistributionStrategy(distributionStrategy);
        }

        String receiverLimitationStrategy = (String) attributes.get(
                "receiverLimitationStrategy");

        if (receiverLimitationStrategy != null) {
            setReceiverLimitationStrategy(receiverLimitationStrategy);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Long sort = (Long) attributes.get("sort");

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

        if (_pointTypeRemoteModel != null) {
            try {
                Class<?> clazz = _pointTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_pointTypeRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getParentPointTypeId() {
        return _parentPointTypeId;
    }

    @Override
    public void setParentPointTypeId(long parentPointTypeId) {
        _parentPointTypeId = parentPointTypeId;

        if (_pointTypeRemoteModel != null) {
            try {
                Class<?> clazz = _pointTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setParentPointTypeId",
                        long.class);

                method.invoke(_pointTypeRemoteModel, parentPointTypeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPercentageOfParent() {
        return _percentageOfParent;
    }

    @Override
    public void setPercentageOfParent(double percentageOfParent) {
        _percentageOfParent = percentageOfParent;

        if (_pointTypeRemoteModel != null) {
            try {
                Class<?> clazz = _pointTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setPercentageOfParent",
                        double.class);

                method.invoke(_pointTypeRemoteModel, percentageOfParent);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDistributionStrategy() {
        return _distributionStrategy;
    }

    @Override
    public void setDistributionStrategy(String distributionStrategy) {
        _distributionStrategy = distributionStrategy;

        if (_pointTypeRemoteModel != null) {
            try {
                Class<?> clazz = _pointTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setDistributionStrategy",
                        String.class);

                method.invoke(_pointTypeRemoteModel, distributionStrategy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReceiverLimitationStrategy() {
        return _receiverLimitationStrategy;
    }

    @Override
    public void setReceiverLimitationStrategy(String receiverLimitationStrategy) {
        _receiverLimitationStrategy = receiverLimitationStrategy;

        if (_pointTypeRemoteModel != null) {
            try {
                Class<?> clazz = _pointTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setReceiverLimitationStrategy",
                        String.class);

                method.invoke(_pointTypeRemoteModel, receiverLimitationStrategy);
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

        if (_pointTypeRemoteModel != null) {
            try {
                Class<?> clazz = _pointTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_pointTypeRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getSort() {
        return _sort;
    }

    @Override
    public void setSort(long sort) {
        _sort = sort;

        if (_pointTypeRemoteModel != null) {
            try {
                Class<?> clazz = _pointTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setSort", long.class);

                method.invoke(_pointTypeRemoteModel, sort);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPointTypeRemoteModel() {
        return _pointTypeRemoteModel;
    }

    public void setPointTypeRemoteModel(BaseModel<?> pointTypeRemoteModel) {
        _pointTypeRemoteModel = pointTypeRemoteModel;
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

        Class<?> remoteModelClass = _pointTypeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_pointTypeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PointTypeLocalServiceUtil.addPointType(this);
        } else {
            PointTypeLocalServiceUtil.updatePointType(this);
        }
    }

    @Override
    public PointType toEscapedModel() {
        return (PointType) ProxyUtil.newProxyInstance(PointType.class.getClassLoader(),
            new Class[] { PointType.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PointTypeClp clone = new PointTypeClp();

        clone.setId(getId());
        clone.setParentPointTypeId(getParentPointTypeId());
        clone.setPercentageOfParent(getPercentageOfParent());
        clone.setDistributionStrategy(getDistributionStrategy());
        clone.setReceiverLimitationStrategy(getReceiverLimitationStrategy());
        clone.setName(getName());
        clone.setSort(getSort());

        return clone;
    }

    @Override
    public int compareTo(PointType pointType) {
        long primaryKey = pointType.getPrimaryKey();

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

        if (!(obj instanceof PointTypeClp)) {
            return false;
        }

        PointTypeClp pointType = (PointTypeClp) obj;

        long primaryKey = pointType.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", parentPointTypeId=");
        sb.append(getParentPointTypeId());
        sb.append(", percentageOfParent=");
        sb.append(getPercentageOfParent());
        sb.append(", distributionStrategy=");
        sb.append(getDistributionStrategy());
        sb.append(", receiverLimitationStrategy=");
        sb.append(getReceiverLimitationStrategy());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", sort=");
        sb.append(getSort());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PointType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentPointTypeId</column-name><column-value><![CDATA[");
        sb.append(getParentPointTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>percentageOfParent</column-name><column-value><![CDATA[");
        sb.append(getPercentageOfParent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>distributionStrategy</column-name><column-value><![CDATA[");
        sb.append(getDistributionStrategy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>receiverLimitationStrategy</column-name><column-value><![CDATA[");
        sb.append(getReceiverLimitationStrategy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sort</column-name><column-value><![CDATA[");
        sb.append(getSort());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
