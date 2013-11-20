package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.service.persistence.FocusAreaOntologyTermPK;

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


public class FocusAreaOntologyTermClp extends BaseModelImpl<FocusAreaOntologyTerm>
    implements FocusAreaOntologyTerm {
    private long _focusAreaId;
    private long _ontologyTermId;
    private int _order;
    private BaseModel<?> _focusAreaOntologyTermRemoteModel;

    public FocusAreaOntologyTermClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return FocusAreaOntologyTerm.class;
    }

    @Override
    public String getModelClassName() {
        return FocusAreaOntologyTerm.class.getName();
    }

    @Override
    public FocusAreaOntologyTermPK getPrimaryKey() {
        return new FocusAreaOntologyTermPK(_focusAreaId, _ontologyTermId);
    }

    @Override
    public void setPrimaryKey(FocusAreaOntologyTermPK primaryKey) {
        setFocusAreaId(primaryKey.focusAreaId);
        setOntologyTermId(primaryKey.ontologyTermId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new FocusAreaOntologyTermPK(_focusAreaId, _ontologyTermId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((FocusAreaOntologyTermPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("focusAreaId", getFocusAreaId());
        attributes.put("ontologyTermId", getOntologyTermId());
        attributes.put("order", getOrder());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long focusAreaId = (Long) attributes.get("focusAreaId");

        if (focusAreaId != null) {
            setFocusAreaId(focusAreaId);
        }

        Long ontologyTermId = (Long) attributes.get("ontologyTermId");

        if (ontologyTermId != null) {
            setOntologyTermId(ontologyTermId);
        }

        Integer order = (Integer) attributes.get("order");

        if (order != null) {
            setOrder(order);
        }
    }

    @Override
    public long getFocusAreaId() {
        return _focusAreaId;
    }

    @Override
    public void setFocusAreaId(long focusAreaId) {
        _focusAreaId = focusAreaId;

        if (_focusAreaOntologyTermRemoteModel != null) {
            try {
                Class<?> clazz = _focusAreaOntologyTermRemoteModel.getClass();

                Method method = clazz.getMethod("setFocusAreaId", long.class);

                method.invoke(_focusAreaOntologyTermRemoteModel, focusAreaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getOntologyTermId() {
        return _ontologyTermId;
    }

    @Override
    public void setOntologyTermId(long ontologyTermId) {
        _ontologyTermId = ontologyTermId;

        if (_focusAreaOntologyTermRemoteModel != null) {
            try {
                Class<?> clazz = _focusAreaOntologyTermRemoteModel.getClass();

                Method method = clazz.getMethod("setOntologyTermId", long.class);

                method.invoke(_focusAreaOntologyTermRemoteModel, ontologyTermId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getOrder() {
        return _order;
    }

    @Override
    public void setOrder(int order) {
        _order = order;

        if (_focusAreaOntologyTermRemoteModel != null) {
            try {
                Class<?> clazz = _focusAreaOntologyTermRemoteModel.getClass();

                Method method = clazz.getMethod("setOrder", int.class);

                method.invoke(_focusAreaOntologyTermRemoteModel, order);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getFocusAreaOntologyTermRemoteModel() {
        return _focusAreaOntologyTermRemoteModel;
    }

    public void setFocusAreaOntologyTermRemoteModel(
        BaseModel<?> focusAreaOntologyTermRemoteModel) {
        _focusAreaOntologyTermRemoteModel = focusAreaOntologyTermRemoteModel;
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

        Class<?> remoteModelClass = _focusAreaOntologyTermRemoteModel.getClass();

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

        Object returnValue = method.invoke(_focusAreaOntologyTermRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            FocusAreaOntologyTermLocalServiceUtil.addFocusAreaOntologyTerm(this);
        } else {
            FocusAreaOntologyTermLocalServiceUtil.updateFocusAreaOntologyTerm(this);
        }
    }

    @Override
    public FocusAreaOntologyTerm toEscapedModel() {
        return (FocusAreaOntologyTerm) ProxyUtil.newProxyInstance(FocusAreaOntologyTerm.class.getClassLoader(),
            new Class[] { FocusAreaOntologyTerm.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FocusAreaOntologyTermClp clone = new FocusAreaOntologyTermClp();

        clone.setFocusAreaId(getFocusAreaId());
        clone.setOntologyTermId(getOntologyTermId());
        clone.setOrder(getOrder());

        return clone;
    }

    @Override
    public int compareTo(FocusAreaOntologyTerm focusAreaOntologyTerm) {
        int value = 0;

        if (getOrder() < focusAreaOntologyTerm.getOrder()) {
            value = -1;
        } else if (getOrder() > focusAreaOntologyTerm.getOrder()) {
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

        if (!(obj instanceof FocusAreaOntologyTermClp)) {
            return false;
        }

        FocusAreaOntologyTermClp focusAreaOntologyTerm = (FocusAreaOntologyTermClp) obj;

        FocusAreaOntologyTermPK primaryKey = focusAreaOntologyTerm.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{focusAreaId=");
        sb.append(getFocusAreaId());
        sb.append(", ontologyTermId=");
        sb.append(getOntologyTermId());
        sb.append(", order=");
        sb.append(getOrder());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.FocusAreaOntologyTerm");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>focusAreaId</column-name><column-value><![CDATA[");
        sb.append(getFocusAreaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ontologyTermId</column-name><column-value><![CDATA[");
        sb.append(getOntologyTermId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>order</column-name><column-value><![CDATA[");
        sb.append(getOrder());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
