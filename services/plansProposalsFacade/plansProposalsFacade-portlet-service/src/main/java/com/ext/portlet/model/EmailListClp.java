package com.ext.portlet.model;

import com.ext.portlet.service.EmailListLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class EmailListClp extends BaseModelImpl<EmailList> implements EmailList {
    private long _id;
    private String _name;
    private String _email;

    public EmailListClp() {
    }

    public Class<?> getModelClass() {
        return EmailList.class;
    }

    public String getModelClassName() {
        return EmailList.class.getName();
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

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            EmailListLocalServiceUtil.addEmailList(this);
        } else {
            EmailListLocalServiceUtil.updateEmailList(this);
        }
    }

    @Override
    public EmailList toEscapedModel() {
        return (EmailList) Proxy.newProxyInstance(EmailList.class.getClassLoader(),
            new Class[] { EmailList.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        EmailListClp clone = new EmailListClp();

        clone.setId(getId());
        clone.setName(getName());
        clone.setEmail(getEmail());

        return clone;
    }

    public int compareTo(EmailList emailList) {
        long primaryKey = emailList.getPrimaryKey();

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

        EmailListClp emailList = null;

        try {
            emailList = (EmailListClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = emailList.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", email=");
        sb.append(getEmail());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.EmailList");
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
            "<column><column-name>email</column-name><column-value><![CDATA[");
        sb.append(getEmail());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
