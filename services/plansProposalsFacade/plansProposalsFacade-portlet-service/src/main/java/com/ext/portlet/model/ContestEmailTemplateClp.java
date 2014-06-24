package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;

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


public class ContestEmailTemplateClp extends BaseModelImpl<ContestEmailTemplate>
    implements ContestEmailTemplate {
    private String _type;
    private String _header;
    private String _footer;
    private BaseModel<?> _contestEmailTemplateRemoteModel;

    public ContestEmailTemplateClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContestEmailTemplate.class;
    }

    @Override
    public String getModelClassName() {
        return ContestEmailTemplate.class.getName();
    }

    @Override
    public String getPrimaryKey() {
        return _type;
    }

    @Override
    public void setPrimaryKey(String primaryKey) {
        setType(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _type;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((String) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("type", getType());
        attributes.put("header", getHeader());
        attributes.put("footer", getFooter());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String type = (String) attributes.get("type");

        if (type != null) {
            setType(type);
        }

        String header = (String) attributes.get("header");

        if (header != null) {
            setHeader(header);
        }

        String footer = (String) attributes.get("footer");

        if (footer != null) {
            setFooter(footer);
        }
    }

    @Override
    public String getType() {
        return _type;
    }

    @Override
    public void setType(String type) {
        _type = type;

        if (_contestEmailTemplateRemoteModel != null) {
            try {
                Class<?> clazz = _contestEmailTemplateRemoteModel.getClass();

                Method method = clazz.getMethod("setType", String.class);

                method.invoke(_contestEmailTemplateRemoteModel, type);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getHeader() {
        return _header;
    }

    @Override
    public void setHeader(String header) {
        _header = header;

        if (_contestEmailTemplateRemoteModel != null) {
            try {
                Class<?> clazz = _contestEmailTemplateRemoteModel.getClass();

                Method method = clazz.getMethod("setHeader", String.class);

                method.invoke(_contestEmailTemplateRemoteModel, header);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFooter() {
        return _footer;
    }

    @Override
    public void setFooter(String footer) {
        _footer = footer;

        if (_contestEmailTemplateRemoteModel != null) {
            try {
                Class<?> clazz = _contestEmailTemplateRemoteModel.getClass();

                Method method = clazz.getMethod("setFooter", String.class);

                method.invoke(_contestEmailTemplateRemoteModel, footer);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContestEmailTemplateRemoteModel() {
        return _contestEmailTemplateRemoteModel;
    }

    public void setContestEmailTemplateRemoteModel(
        BaseModel<?> contestEmailTemplateRemoteModel) {
        _contestEmailTemplateRemoteModel = contestEmailTemplateRemoteModel;
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

        Class<?> remoteModelClass = _contestEmailTemplateRemoteModel.getClass();

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

        Object returnValue = method.invoke(_contestEmailTemplateRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestEmailTemplateLocalServiceUtil.addContestEmailTemplate(this);
        } else {
            ContestEmailTemplateLocalServiceUtil.updateContestEmailTemplate(this);
        }
    }

    @Override
    public ContestEmailTemplate toEscapedModel() {
        return (ContestEmailTemplate) ProxyUtil.newProxyInstance(ContestEmailTemplate.class.getClassLoader(),
            new Class[] { ContestEmailTemplate.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestEmailTemplateClp clone = new ContestEmailTemplateClp();

        clone.setType(getType());
        clone.setHeader(getHeader());
        clone.setFooter(getFooter());

        return clone;
    }

    @Override
    public int compareTo(ContestEmailTemplate contestEmailTemplate) {
        String primaryKey = contestEmailTemplate.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestEmailTemplateClp)) {
            return false;
        }

        ContestEmailTemplateClp contestEmailTemplate = (ContestEmailTemplateClp) obj;

        String primaryKey = contestEmailTemplate.getPrimaryKey();

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

        sb.append("{type=");
        sb.append(getType());
        sb.append(", header=");
        sb.append(getHeader());
        sb.append(", footer=");
        sb.append(getFooter());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ContestEmailTemplate");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>type</column-name><column-value><![CDATA[");
        sb.append(getType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>header</column-name><column-value><![CDATA[");
        sb.append(getHeader());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>footer</column-name><column-value><![CDATA[");
        sb.append(getFooter());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
