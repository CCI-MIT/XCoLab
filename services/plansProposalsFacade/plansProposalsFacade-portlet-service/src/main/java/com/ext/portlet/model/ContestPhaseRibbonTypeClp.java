package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;

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


public class ContestPhaseRibbonTypeClp extends BaseModelImpl<ContestPhaseRibbonType>
    implements ContestPhaseRibbonType {
    private long _id;
    private int _ribbon;
    private String _hoverText;
    private String _description;
    private boolean _copyOnPromote;
    private BaseModel<?> _contestPhaseRibbonTypeRemoteModel;

    public ContestPhaseRibbonTypeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContestPhaseRibbonType.class;
    }

    @Override
    public String getModelClassName() {
        return ContestPhaseRibbonType.class.getName();
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
        attributes.put("ribbon", getRibbon());
        attributes.put("hoverText", getHoverText());
        attributes.put("description", getDescription());
        attributes.put("copyOnPromote", getCopyOnPromote());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Integer ribbon = (Integer) attributes.get("ribbon");

        if (ribbon != null) {
            setRibbon(ribbon);
        }

        String hoverText = (String) attributes.get("hoverText");

        if (hoverText != null) {
            setHoverText(hoverText);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Boolean copyOnPromote = (Boolean) attributes.get("copyOnPromote");

        if (copyOnPromote != null) {
            setCopyOnPromote(copyOnPromote);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_contestPhaseRibbonTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRibbonTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_contestPhaseRibbonTypeRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRibbon() {
        return _ribbon;
    }

    @Override
    public void setRibbon(int ribbon) {
        _ribbon = ribbon;

        if (_contestPhaseRibbonTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRibbonTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setRibbon", int.class);

                method.invoke(_contestPhaseRibbonTypeRemoteModel, ribbon);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getHoverText() {
        return _hoverText;
    }

    @Override
    public void setHoverText(String hoverText) {
        _hoverText = hoverText;

        if (_contestPhaseRibbonTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRibbonTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setHoverText", String.class);

                method.invoke(_contestPhaseRibbonTypeRemoteModel, hoverText);
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

        if (_contestPhaseRibbonTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRibbonTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_contestPhaseRibbonTypeRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCopyOnPromote() {
        return _copyOnPromote;
    }

    @Override
    public boolean isCopyOnPromote() {
        return _copyOnPromote;
    }

    @Override
    public void setCopyOnPromote(boolean copyOnPromote) {
        _copyOnPromote = copyOnPromote;

        if (_contestPhaseRibbonTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseRibbonTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setCopyOnPromote",
                        boolean.class);

                method.invoke(_contestPhaseRibbonTypeRemoteModel, copyOnPromote);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContestPhaseRibbonTypeRemoteModel() {
        return _contestPhaseRibbonTypeRemoteModel;
    }

    public void setContestPhaseRibbonTypeRemoteModel(
        BaseModel<?> contestPhaseRibbonTypeRemoteModel) {
        _contestPhaseRibbonTypeRemoteModel = contestPhaseRibbonTypeRemoteModel;
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

        Class<?> remoteModelClass = _contestPhaseRibbonTypeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_contestPhaseRibbonTypeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestPhaseRibbonTypeLocalServiceUtil.addContestPhaseRibbonType(this);
        } else {
            ContestPhaseRibbonTypeLocalServiceUtil.updateContestPhaseRibbonType(this);
        }
    }

    @Override
    public ContestPhaseRibbonType toEscapedModel() {
        return (ContestPhaseRibbonType) ProxyUtil.newProxyInstance(ContestPhaseRibbonType.class.getClassLoader(),
            new Class[] { ContestPhaseRibbonType.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestPhaseRibbonTypeClp clone = new ContestPhaseRibbonTypeClp();

        clone.setId(getId());
        clone.setRibbon(getRibbon());
        clone.setHoverText(getHoverText());
        clone.setDescription(getDescription());
        clone.setCopyOnPromote(getCopyOnPromote());

        return clone;
    }

    @Override
    public int compareTo(ContestPhaseRibbonType contestPhaseRibbonType) {
        long primaryKey = contestPhaseRibbonType.getPrimaryKey();

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

        if (!(obj instanceof ContestPhaseRibbonTypeClp)) {
            return false;
        }

        ContestPhaseRibbonTypeClp contestPhaseRibbonType = (ContestPhaseRibbonTypeClp) obj;

        long primaryKey = contestPhaseRibbonType.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", ribbon=");
        sb.append(getRibbon());
        sb.append(", hoverText=");
        sb.append(getHoverText());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", copyOnPromote=");
        sb.append(getCopyOnPromote());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ContestPhaseRibbonType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ribbon</column-name><column-value><![CDATA[");
        sb.append(getRibbon());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hoverText</column-name><column-value><![CDATA[");
        sb.append(getHoverText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>copyOnPromote</column-name><column-value><![CDATA[");
        sb.append(getCopyOnPromote());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
