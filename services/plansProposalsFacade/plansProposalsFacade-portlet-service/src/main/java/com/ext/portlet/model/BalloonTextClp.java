package com.ext.portlet.model;

import com.ext.portlet.service.BalloonTextLocalServiceUtil;
import com.ext.portlet.service.ClpSerializer;

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


public class BalloonTextClp extends BaseModelImpl<BalloonText>
    implements BalloonText {
    private long _id;
    private String _name;
    private String _textBeforeForm;
    private String _textAfterForm;
    private String _textBeforeShareButtons;
    private String _textAfterShareButtons;
    private boolean _enabled;
    private BaseModel<?> _balloonTextRemoteModel;

    public BalloonTextClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return BalloonText.class;
    }

    @Override
    public String getModelClassName() {
        return BalloonText.class.getName();
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
        attributes.put("name", getName());
        attributes.put("textBeforeForm", getTextBeforeForm());
        attributes.put("textAfterForm", getTextAfterForm());
        attributes.put("textBeforeShareButtons", getTextBeforeShareButtons());
        attributes.put("textAfterShareButtons", getTextAfterShareButtons());
        attributes.put("enabled", getEnabled());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String textBeforeForm = (String) attributes.get("textBeforeForm");

        if (textBeforeForm != null) {
            setTextBeforeForm(textBeforeForm);
        }

        String textAfterForm = (String) attributes.get("textAfterForm");

        if (textAfterForm != null) {
            setTextAfterForm(textAfterForm);
        }

        String textBeforeShareButtons = (String) attributes.get(
                "textBeforeShareButtons");

        if (textBeforeShareButtons != null) {
            setTextBeforeShareButtons(textBeforeShareButtons);
        }

        String textAfterShareButtons = (String) attributes.get(
                "textAfterShareButtons");

        if (textAfterShareButtons != null) {
            setTextAfterShareButtons(textAfterShareButtons);
        }

        Boolean enabled = (Boolean) attributes.get("enabled");

        if (enabled != null) {
            setEnabled(enabled);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_balloonTextRemoteModel != null) {
            try {
                Class<?> clazz = _balloonTextRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_balloonTextRemoteModel, id);
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

        if (_balloonTextRemoteModel != null) {
            try {
                Class<?> clazz = _balloonTextRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_balloonTextRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTextBeforeForm() {
        return _textBeforeForm;
    }

    @Override
    public void setTextBeforeForm(String textBeforeForm) {
        _textBeforeForm = textBeforeForm;

        if (_balloonTextRemoteModel != null) {
            try {
                Class<?> clazz = _balloonTextRemoteModel.getClass();

                Method method = clazz.getMethod("setTextBeforeForm",
                        String.class);

                method.invoke(_balloonTextRemoteModel, textBeforeForm);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTextAfterForm() {
        return _textAfterForm;
    }

    @Override
    public void setTextAfterForm(String textAfterForm) {
        _textAfterForm = textAfterForm;

        if (_balloonTextRemoteModel != null) {
            try {
                Class<?> clazz = _balloonTextRemoteModel.getClass();

                Method method = clazz.getMethod("setTextAfterForm", String.class);

                method.invoke(_balloonTextRemoteModel, textAfterForm);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTextBeforeShareButtons() {
        return _textBeforeShareButtons;
    }

    @Override
    public void setTextBeforeShareButtons(String textBeforeShareButtons) {
        _textBeforeShareButtons = textBeforeShareButtons;

        if (_balloonTextRemoteModel != null) {
            try {
                Class<?> clazz = _balloonTextRemoteModel.getClass();

                Method method = clazz.getMethod("setTextBeforeShareButtons",
                        String.class);

                method.invoke(_balloonTextRemoteModel, textBeforeShareButtons);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTextAfterShareButtons() {
        return _textAfterShareButtons;
    }

    @Override
    public void setTextAfterShareButtons(String textAfterShareButtons) {
        _textAfterShareButtons = textAfterShareButtons;

        if (_balloonTextRemoteModel != null) {
            try {
                Class<?> clazz = _balloonTextRemoteModel.getClass();

                Method method = clazz.getMethod("setTextAfterShareButtons",
                        String.class);

                method.invoke(_balloonTextRemoteModel, textAfterShareButtons);
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

        if (_balloonTextRemoteModel != null) {
            try {
                Class<?> clazz = _balloonTextRemoteModel.getClass();

                Method method = clazz.getMethod("setEnabled", boolean.class);

                method.invoke(_balloonTextRemoteModel, enabled);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getBalloonTextRemoteModel() {
        return _balloonTextRemoteModel;
    }

    public void setBalloonTextRemoteModel(BaseModel<?> balloonTextRemoteModel) {
        _balloonTextRemoteModel = balloonTextRemoteModel;
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

        Class<?> remoteModelClass = _balloonTextRemoteModel.getClass();

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

        Object returnValue = method.invoke(_balloonTextRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            BalloonTextLocalServiceUtil.addBalloonText(this);
        } else {
            BalloonTextLocalServiceUtil.updateBalloonText(this);
        }
    }

    @Override
    public BalloonText toEscapedModel() {
        return (BalloonText) ProxyUtil.newProxyInstance(BalloonText.class.getClassLoader(),
            new Class[] { BalloonText.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        BalloonTextClp clone = new BalloonTextClp();

        clone.setId(getId());
        clone.setName(getName());
        clone.setTextBeforeForm(getTextBeforeForm());
        clone.setTextAfterForm(getTextAfterForm());
        clone.setTextBeforeShareButtons(getTextBeforeShareButtons());
        clone.setTextAfterShareButtons(getTextAfterShareButtons());
        clone.setEnabled(getEnabled());

        return clone;
    }

    @Override
    public int compareTo(BalloonText balloonText) {
        long primaryKey = balloonText.getPrimaryKey();

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

        if (!(obj instanceof BalloonTextClp)) {
            return false;
        }

        BalloonTextClp balloonText = (BalloonTextClp) obj;

        long primaryKey = balloonText.getPrimaryKey();

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
        sb.append(", name=");
        sb.append(getName());
        sb.append(", textBeforeForm=");
        sb.append(getTextBeforeForm());
        sb.append(", textAfterForm=");
        sb.append(getTextAfterForm());
        sb.append(", textBeforeShareButtons=");
        sb.append(getTextBeforeShareButtons());
        sb.append(", textAfterShareButtons=");
        sb.append(getTextAfterShareButtons());
        sb.append(", enabled=");
        sb.append(getEnabled());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.BalloonText");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>textBeforeForm</column-name><column-value><![CDATA[");
        sb.append(getTextBeforeForm());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>textAfterForm</column-name><column-value><![CDATA[");
        sb.append(getTextAfterForm());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>textBeforeShareButtons</column-name><column-value><![CDATA[");
        sb.append(getTextBeforeShareButtons());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>textAfterShareButtons</column-name><column-value><![CDATA[");
        sb.append(getTextAfterShareButtons());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>enabled</column-name><column-value><![CDATA[");
        sb.append(getEnabled());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
