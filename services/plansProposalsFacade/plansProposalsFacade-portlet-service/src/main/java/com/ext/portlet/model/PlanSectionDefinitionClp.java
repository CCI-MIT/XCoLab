package com.ext.portlet.model;

import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanSectionDefinitionClp extends BaseModelImpl<PlanSectionDefinition>
    implements PlanSectionDefinition {
    private long _id;
    private String _type;
    private String _adminTitle;
    private String _title;
    private String _defaultText;
    private String _helpText;
    private int _characterLimit;
    private long _focusAreaId;
    private boolean _locked;

    public PlanSectionDefinitionClp() {
    }

    public Class<?> getModelClass() {
        return PlanSectionDefinition.class;
    }

    public String getModelClassName() {
        return PlanSectionDefinition.class.getName();
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }

    public String getAdminTitle() {
        return _adminTitle;
    }

    public void setAdminTitle(String adminTitle) {
        _adminTitle = adminTitle;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getDefaultText() {
        return _defaultText;
    }

    public void setDefaultText(String defaultText) {
        _defaultText = defaultText;
    }

    public String getHelpText() {
        return _helpText;
    }

    public void setHelpText(String helpText) {
        _helpText = helpText;
    }

    public int getCharacterLimit() {
        return _characterLimit;
    }

    public void setCharacterLimit(int characterLimit) {
        _characterLimit = characterLimit;
    }

    public long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(long focusAreaId) {
        _focusAreaId = focusAreaId;
    }

    public boolean getLocked() {
        return _locked;
    }

    public boolean isLocked() {
        return _locked;
    }

    public void setLocked(boolean locked) {
        _locked = locked;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanSectionDefinitionLocalServiceUtil.addPlanSectionDefinition(this);
        } else {
            PlanSectionDefinitionLocalServiceUtil.updatePlanSectionDefinition(this);
        }
    }

    @Override
    public PlanSectionDefinition toEscapedModel() {
        return (PlanSectionDefinition) Proxy.newProxyInstance(PlanSectionDefinition.class.getClassLoader(),
            new Class[] { PlanSectionDefinition.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanSectionDefinitionClp clone = new PlanSectionDefinitionClp();

        clone.setId(getId());
        clone.setType(getType());
        clone.setAdminTitle(getAdminTitle());
        clone.setTitle(getTitle());
        clone.setDefaultText(getDefaultText());
        clone.setHelpText(getHelpText());
        clone.setCharacterLimit(getCharacterLimit());
        clone.setFocusAreaId(getFocusAreaId());
        clone.setLocked(getLocked());

        return clone;
    }

    public int compareTo(PlanSectionDefinition planSectionDefinition) {
        long primaryKey = planSectionDefinition.getPrimaryKey();

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
        if (obj == null) {
            return false;
        }

        PlanSectionDefinitionClp planSectionDefinition = null;

        try {
            planSectionDefinition = (PlanSectionDefinitionClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = planSectionDefinition.getPrimaryKey();

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
        StringBundler sb = new StringBundler(19);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", type=");
        sb.append(getType());
        sb.append(", adminTitle=");
        sb.append(getAdminTitle());
        sb.append(", title=");
        sb.append(getTitle());
        sb.append(", defaultText=");
        sb.append(getDefaultText());
        sb.append(", helpText=");
        sb.append(getHelpText());
        sb.append(", characterLimit=");
        sb.append(getCharacterLimit());
        sb.append(", focusAreaId=");
        sb.append(getFocusAreaId());
        sb.append(", locked=");
        sb.append(getLocked());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanSectionDefinition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>type</column-name><column-value><![CDATA[");
        sb.append(getType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adminTitle</column-name><column-value><![CDATA[");
        sb.append(getAdminTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>title</column-name><column-value><![CDATA[");
        sb.append(getTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultText</column-name><column-value><![CDATA[");
        sb.append(getDefaultText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>helpText</column-name><column-value><![CDATA[");
        sb.append(getHelpText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>characterLimit</column-name><column-value><![CDATA[");
        sb.append(getCharacterLimit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>focusAreaId</column-name><column-value><![CDATA[");
        sb.append(getFocusAreaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>locked</column-name><column-value><![CDATA[");
        sb.append(getLocked());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
