package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestPhaseColumnLocalServiceUtil;

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


public class ContestPhaseColumnClp extends BaseModelImpl<ContestPhaseColumn>
    implements ContestPhaseColumn {
    private long _id;
    private long _ContestPhasePK;
    private String _columnName;
    private int _columnWeight;
    private boolean _defaultSort;
    private BaseModel<?> _contestPhaseColumnRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ContestPhaseColumnClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContestPhaseColumn.class;
    }

    @Override
    public String getModelClassName() {
        return ContestPhaseColumn.class.getName();
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
        attributes.put("ContestPhasePK", getContestPhasePK());
        attributes.put("columnName", getColumnName());
        attributes.put("columnWeight", getColumnWeight());
        attributes.put("defaultSort", getDefaultSort());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long ContestPhasePK = (Long) attributes.get("ContestPhasePK");

        if (ContestPhasePK != null) {
            setContestPhasePK(ContestPhasePK);
        }

        String columnName = (String) attributes.get("columnName");

        if (columnName != null) {
            setColumnName(columnName);
        }

        Integer columnWeight = (Integer) attributes.get("columnWeight");

        if (columnWeight != null) {
            setColumnWeight(columnWeight);
        }

        Boolean defaultSort = (Boolean) attributes.get("defaultSort");

        if (defaultSort != null) {
            setDefaultSort(defaultSort);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_contestPhaseColumnRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseColumnRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_contestPhaseColumnRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getContestPhasePK() {
        return _ContestPhasePK;
    }

    @Override
    public void setContestPhasePK(long ContestPhasePK) {
        _ContestPhasePK = ContestPhasePK;

        if (_contestPhaseColumnRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseColumnRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPhasePK", long.class);

                method.invoke(_contestPhaseColumnRemoteModel, ContestPhasePK);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumnName() {
        return _columnName;
    }

    @Override
    public void setColumnName(String columnName) {
        _columnName = columnName;

        if (_contestPhaseColumnRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseColumnRemoteModel.getClass();

                Method method = clazz.getMethod("setColumnName", String.class);

                method.invoke(_contestPhaseColumnRemoteModel, columnName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getColumnWeight() {
        return _columnWeight;
    }

    @Override
    public void setColumnWeight(int columnWeight) {
        _columnWeight = columnWeight;

        if (_contestPhaseColumnRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseColumnRemoteModel.getClass();

                Method method = clazz.getMethod("setColumnWeight", int.class);

                method.invoke(_contestPhaseColumnRemoteModel, columnWeight);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getDefaultSort() {
        return _defaultSort;
    }

    @Override
    public boolean isDefaultSort() {
        return _defaultSort;
    }

    @Override
    public void setDefaultSort(boolean defaultSort) {
        _defaultSort = defaultSort;

        if (_contestPhaseColumnRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseColumnRemoteModel.getClass();

                Method method = clazz.getMethod("setDefaultSort", boolean.class);

                method.invoke(_contestPhaseColumnRemoteModel, defaultSort);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContestPhaseColumnRemoteModel() {
        return _contestPhaseColumnRemoteModel;
    }

    public void setContestPhaseColumnRemoteModel(
        BaseModel<?> contestPhaseColumnRemoteModel) {
        _contestPhaseColumnRemoteModel = contestPhaseColumnRemoteModel;
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

        Class<?> remoteModelClass = _contestPhaseColumnRemoteModel.getClass();

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

        Object returnValue = method.invoke(_contestPhaseColumnRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestPhaseColumnLocalServiceUtil.addContestPhaseColumn(this);
        } else {
            ContestPhaseColumnLocalServiceUtil.updateContestPhaseColumn(this);
        }
    }

    @Override
    public ContestPhaseColumn toEscapedModel() {
        return (ContestPhaseColumn) ProxyUtil.newProxyInstance(ContestPhaseColumn.class.getClassLoader(),
            new Class[] { ContestPhaseColumn.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestPhaseColumnClp clone = new ContestPhaseColumnClp();

        clone.setId(getId());
        clone.setContestPhasePK(getContestPhasePK());
        clone.setColumnName(getColumnName());
        clone.setColumnWeight(getColumnWeight());
        clone.setDefaultSort(getDefaultSort());

        return clone;
    }

    @Override
    public int compareTo(ContestPhaseColumn contestPhaseColumn) {
        int value = 0;

        if (getColumnWeight() < contestPhaseColumn.getColumnWeight()) {
            value = -1;
        } else if (getColumnWeight() > contestPhaseColumn.getColumnWeight()) {
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

        if (!(obj instanceof ContestPhaseColumnClp)) {
            return false;
        }

        ContestPhaseColumnClp contestPhaseColumn = (ContestPhaseColumnClp) obj;

        long primaryKey = contestPhaseColumn.getPrimaryKey();

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
        sb.append(", ContestPhasePK=");
        sb.append(getContestPhasePK());
        sb.append(", columnName=");
        sb.append(getColumnName());
        sb.append(", columnWeight=");
        sb.append(getColumnWeight());
        sb.append(", defaultSort=");
        sb.append(getDefaultSort());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ContestPhaseColumn");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPhasePK</column-name><column-value><![CDATA[");
        sb.append(getContestPhasePK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnName</column-name><column-value><![CDATA[");
        sb.append(getColumnName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnWeight</column-name><column-value><![CDATA[");
        sb.append(getColumnWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultSort</column-name><column-value><![CDATA[");
        sb.append(getDefaultSort());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
