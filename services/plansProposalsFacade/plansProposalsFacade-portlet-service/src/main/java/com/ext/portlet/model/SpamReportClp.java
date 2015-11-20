package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.SpamReportLocalServiceUtil;

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


public class SpamReportClp extends BaseModelImpl<SpamReport>
    implements SpamReport {
    private long _id_;
    private long _spamUserId;
    private String _spamUserUuid;
    private long _reporterUserId;
    private String _reporterUserUuid;
    private long _discussionMessageId;
    private boolean _isAdminReport;
    private BaseModel<?> _spamReportRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public SpamReportClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return SpamReport.class;
    }

    @Override
    public String getModelClassName() {
        return SpamReport.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _id_;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setId_(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _id_;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id_", getId_());
        attributes.put("spamUserId", getSpamUserId());
        attributes.put("reporterUserId", getReporterUserId());
        attributes.put("discussionMessageId", getDiscussionMessageId());
        attributes.put("isAdminReport", getIsAdminReport());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id_ = (Long) attributes.get("id_");

        if (id_ != null) {
            setId_(id_);
        }

        Long spamUserId = (Long) attributes.get("spamUserId");

        if (spamUserId != null) {
            setSpamUserId(spamUserId);
        }

        Long reporterUserId = (Long) attributes.get("reporterUserId");

        if (reporterUserId != null) {
            setReporterUserId(reporterUserId);
        }

        Long discussionMessageId = (Long) attributes.get("discussionMessageId");

        if (discussionMessageId != null) {
            setDiscussionMessageId(discussionMessageId);
        }

        Boolean isAdminReport = (Boolean) attributes.get("isAdminReport");

        if (isAdminReport != null) {
            setIsAdminReport(isAdminReport);
        }
    }

    @Override
    public long getId_() {
        return _id_;
    }

    @Override
    public void setId_(long id_) {
        _id_ = id_;

        if (_spamReportRemoteModel != null) {
            try {
                Class<?> clazz = _spamReportRemoteModel.getClass();

                Method method = clazz.getMethod("setId_", long.class);

                method.invoke(_spamReportRemoteModel, id_);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getSpamUserId() {
        return _spamUserId;
    }

    @Override
    public void setSpamUserId(long spamUserId) {
        _spamUserId = spamUserId;

        if (_spamReportRemoteModel != null) {
            try {
                Class<?> clazz = _spamReportRemoteModel.getClass();

                Method method = clazz.getMethod("setSpamUserId", long.class);

                method.invoke(_spamReportRemoteModel, spamUserId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSpamUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getSpamUserId(), "uuid", _spamUserUuid);
    }

    @Override
    public void setSpamUserUuid(String spamUserUuid) {
        _spamUserUuid = spamUserUuid;
    }

    @Override
    public long getReporterUserId() {
        return _reporterUserId;
    }

    @Override
    public void setReporterUserId(long reporterUserId) {
        _reporterUserId = reporterUserId;

        if (_spamReportRemoteModel != null) {
            try {
                Class<?> clazz = _spamReportRemoteModel.getClass();

                Method method = clazz.getMethod("setReporterUserId", long.class);

                method.invoke(_spamReportRemoteModel, reporterUserId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReporterUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getReporterUserId(), "uuid",
            _reporterUserUuid);
    }

    @Override
    public void setReporterUserUuid(String reporterUserUuid) {
        _reporterUserUuid = reporterUserUuid;
    }

    @Override
    public long getDiscussionMessageId() {
        return _discussionMessageId;
    }

    @Override
    public void setDiscussionMessageId(long discussionMessageId) {
        _discussionMessageId = discussionMessageId;

        if (_spamReportRemoteModel != null) {
            try {
                Class<?> clazz = _spamReportRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscussionMessageId",
                        long.class);

                method.invoke(_spamReportRemoteModel, discussionMessageId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getIsAdminReport() {
        return _isAdminReport;
    }

    @Override
    public boolean isIsAdminReport() {
        return _isAdminReport;
    }

    @Override
    public void setIsAdminReport(boolean isAdminReport) {
        _isAdminReport = isAdminReport;

        if (_spamReportRemoteModel != null) {
            try {
                Class<?> clazz = _spamReportRemoteModel.getClass();

                Method method = clazz.getMethod("setIsAdminReport",
                        boolean.class);

                method.invoke(_spamReportRemoteModel, isAdminReport);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getSpamReportRemoteModel() {
        return _spamReportRemoteModel;
    }

    public void setSpamReportRemoteModel(BaseModel<?> spamReportRemoteModel) {
        _spamReportRemoteModel = spamReportRemoteModel;
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

        Class<?> remoteModelClass = _spamReportRemoteModel.getClass();

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

        Object returnValue = method.invoke(_spamReportRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            SpamReportLocalServiceUtil.addSpamReport(this);
        } else {
            SpamReportLocalServiceUtil.updateSpamReport(this);
        }
    }

    @Override
    public SpamReport toEscapedModel() {
        return (SpamReport) ProxyUtil.newProxyInstance(SpamReport.class.getClassLoader(),
            new Class[] { SpamReport.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        SpamReportClp clone = new SpamReportClp();

        clone.setId_(getId_());
        clone.setSpamUserId(getSpamUserId());
        clone.setReporterUserId(getReporterUserId());
        clone.setDiscussionMessageId(getDiscussionMessageId());
        clone.setIsAdminReport(getIsAdminReport());

        return clone;
    }

    @Override
    public int compareTo(SpamReport spamReport) {
        long primaryKey = spamReport.getPrimaryKey();

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

        if (!(obj instanceof SpamReportClp)) {
            return false;
        }

        SpamReportClp spamReport = (SpamReportClp) obj;

        long primaryKey = spamReport.getPrimaryKey();

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

        sb.append("{id_=");
        sb.append(getId_());
        sb.append(", spamUserId=");
        sb.append(getSpamUserId());
        sb.append(", reporterUserId=");
        sb.append(getReporterUserId());
        sb.append(", discussionMessageId=");
        sb.append(getDiscussionMessageId());
        sb.append(", isAdminReport=");
        sb.append(getIsAdminReport());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.SpamReport");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id_</column-name><column-value><![CDATA[");
        sb.append(getId_());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>spamUserId</column-name><column-value><![CDATA[");
        sb.append(getSpamUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reporterUserId</column-name><column-value><![CDATA[");
        sb.append(getReporterUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discussionMessageId</column-name><column-value><![CDATA[");
        sb.append(getDiscussionMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isAdminReport</column-name><column-value><![CDATA[");
        sb.append(getIsAdminReport());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
