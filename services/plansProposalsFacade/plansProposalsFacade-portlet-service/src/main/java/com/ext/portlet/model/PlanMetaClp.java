package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanMetaLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PlanMetaClp extends BaseModelImpl<PlanMeta> implements PlanMeta {
    private long _id;
    private long _planId;
    private long _planTypeId;
    private long _planCreated;
    private long _authorId;
    private int _votes;
    private long _planGroupId;
    private boolean _open;
    private String _status;
    private long _mbCategoryId;
    private long _categoryGroupId;
    private long _version;
    private long _planVersion;
    private Date _created;
    private long _updateAuthorId;
    private long _modelId;
    private boolean _promoted;
    private long _previousContestPhase;
    private long _contestPhase;
    private BaseModel<?> _planMetaRemoteModel;

    public PlanMetaClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanMeta.class;
    }

    @Override
    public String getModelClassName() {
        return PlanMeta.class.getName();
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
        attributes.put("planId", getPlanId());
        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("planCreated", getPlanCreated());
        attributes.put("authorId", getAuthorId());
        attributes.put("votes", getVotes());
        attributes.put("planGroupId", getPlanGroupId());
        attributes.put("open", getOpen());
        attributes.put("status", getStatus());
        attributes.put("mbCategoryId", getMbCategoryId());
        attributes.put("categoryGroupId", getCategoryGroupId());
        attributes.put("version", getVersion());
        attributes.put("planVersion", getPlanVersion());
        attributes.put("created", getCreated());
        attributes.put("updateAuthorId", getUpdateAuthorId());
        attributes.put("modelId", getModelId());
        attributes.put("promoted", getPromoted());
        attributes.put("previousContestPhase", getPreviousContestPhase());
        attributes.put("contestPhase", getContestPhase());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        Long planCreated = (Long) attributes.get("planCreated");

        if (planCreated != null) {
            setPlanCreated(planCreated);
        }

        Long authorId = (Long) attributes.get("authorId");

        if (authorId != null) {
            setAuthorId(authorId);
        }

        Integer votes = (Integer) attributes.get("votes");

        if (votes != null) {
            setVotes(votes);
        }

        Long planGroupId = (Long) attributes.get("planGroupId");

        if (planGroupId != null) {
            setPlanGroupId(planGroupId);
        }

        Boolean open = (Boolean) attributes.get("open");

        if (open != null) {
            setOpen(open);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Long mbCategoryId = (Long) attributes.get("mbCategoryId");

        if (mbCategoryId != null) {
            setMbCategoryId(mbCategoryId);
        }

        Long categoryGroupId = (Long) attributes.get("categoryGroupId");

        if (categoryGroupId != null) {
            setCategoryGroupId(categoryGroupId);
        }

        Long version = (Long) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Long planVersion = (Long) attributes.get("planVersion");

        if (planVersion != null) {
            setPlanVersion(planVersion);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Long updateAuthorId = (Long) attributes.get("updateAuthorId");

        if (updateAuthorId != null) {
            setUpdateAuthorId(updateAuthorId);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        Boolean promoted = (Boolean) attributes.get("promoted");

        if (promoted != null) {
            setPromoted(promoted);
        }

        Long previousContestPhase = (Long) attributes.get(
                "previousContestPhase");

        if (previousContestPhase != null) {
            setPreviousContestPhase(previousContestPhase);
        }

        Long contestPhase = (Long) attributes.get("contestPhase");

        if (contestPhase != null) {
            setContestPhase(contestPhase);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_planMetaRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanId() {
        return _planId;
    }

    @Override
    public void setPlanId(long planId) {
        _planId = planId;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_planMetaRemoteModel, planId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanTypeId() {
        return _planTypeId;
    }

    @Override
    public void setPlanTypeId(long planTypeId) {
        _planTypeId = planTypeId;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanTypeId", long.class);

                method.invoke(_planMetaRemoteModel, planTypeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanCreated() {
        return _planCreated;
    }

    @Override
    public void setPlanCreated(long planCreated) {
        _planCreated = planCreated;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanCreated", long.class);

                method.invoke(_planMetaRemoteModel, planCreated);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getAuthorId() {
        return _authorId;
    }

    @Override
    public void setAuthorId(long authorId) {
        _authorId = authorId;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorId", long.class);

                method.invoke(_planMetaRemoteModel, authorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getVotes() {
        return _votes;
    }

    @Override
    public void setVotes(int votes) {
        _votes = votes;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setVotes", int.class);

                method.invoke(_planMetaRemoteModel, votes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanGroupId() {
        return _planGroupId;
    }

    @Override
    public void setPlanGroupId(long planGroupId) {
        _planGroupId = planGroupId;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanGroupId", long.class);

                method.invoke(_planMetaRemoteModel, planGroupId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getOpen() {
        return _open;
    }

    @Override
    public boolean isOpen() {
        return _open;
    }

    @Override
    public void setOpen(boolean open) {
        _open = open;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setOpen", boolean.class);

                method.invoke(_planMetaRemoteModel, open);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStatus() {
        return _status;
    }

    @Override
    public void setStatus(String status) {
        _status = status;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setStatus", String.class);

                method.invoke(_planMetaRemoteModel, status);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getMbCategoryId() {
        return _mbCategoryId;
    }

    @Override
    public void setMbCategoryId(long mbCategoryId) {
        _mbCategoryId = mbCategoryId;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setMbCategoryId", long.class);

                method.invoke(_planMetaRemoteModel, mbCategoryId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCategoryGroupId() {
        return _categoryGroupId;
    }

    @Override
    public void setCategoryGroupId(long categoryGroupId) {
        _categoryGroupId = categoryGroupId;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryGroupId", long.class);

                method.invoke(_planMetaRemoteModel, categoryGroupId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getVersion() {
        return _version;
    }

    @Override
    public void setVersion(long version) {
        _version = version;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setVersion", long.class);

                method.invoke(_planMetaRemoteModel, version);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanVersion() {
        return _planVersion;
    }

    @Override
    public void setPlanVersion(long planVersion) {
        _planVersion = planVersion;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanVersion", long.class);

                method.invoke(_planMetaRemoteModel, planVersion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreated() {
        return _created;
    }

    @Override
    public void setCreated(Date created) {
        _created = created;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setCreated", Date.class);

                method.invoke(_planMetaRemoteModel, created);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    @Override
    public void setUpdateAuthorId(long updateAuthorId) {
        _updateAuthorId = updateAuthorId;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdateAuthorId", long.class);

                method.invoke(_planMetaRemoteModel, updateAuthorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getModelId() {
        return _modelId;
    }

    @Override
    public void setModelId(long modelId) {
        _modelId = modelId;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setModelId", long.class);

                method.invoke(_planMetaRemoteModel, modelId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getPromoted() {
        return _promoted;
    }

    @Override
    public boolean isPromoted() {
        return _promoted;
    }

    @Override
    public void setPromoted(boolean promoted) {
        _promoted = promoted;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setPromoted", boolean.class);

                method.invoke(_planMetaRemoteModel, promoted);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPreviousContestPhase() {
        return _previousContestPhase;
    }

    @Override
    public void setPreviousContestPhase(long previousContestPhase) {
        _previousContestPhase = previousContestPhase;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setPreviousContestPhase",
                        long.class);

                method.invoke(_planMetaRemoteModel, previousContestPhase);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getContestPhase() {
        return _contestPhase;
    }

    @Override
    public void setContestPhase(long contestPhase) {
        _contestPhase = contestPhase;

        if (_planMetaRemoteModel != null) {
            try {
                Class<?> clazz = _planMetaRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPhase", long.class);

                method.invoke(_planMetaRemoteModel, contestPhase);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanMetaRemoteModel() {
        return _planMetaRemoteModel;
    }

    public void setPlanMetaRemoteModel(BaseModel<?> planMetaRemoteModel) {
        _planMetaRemoteModel = planMetaRemoteModel;
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

        Class<?> remoteModelClass = _planMetaRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planMetaRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanMetaLocalServiceUtil.addPlanMeta(this);
        } else {
            PlanMetaLocalServiceUtil.updatePlanMeta(this);
        }
    }

    @Override
    public PlanMeta toEscapedModel() {
        return (PlanMeta) ProxyUtil.newProxyInstance(PlanMeta.class.getClassLoader(),
            new Class[] { PlanMeta.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanMetaClp clone = new PlanMetaClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setPlanCreated(getPlanCreated());
        clone.setAuthorId(getAuthorId());
        clone.setVotes(getVotes());
        clone.setPlanGroupId(getPlanGroupId());
        clone.setOpen(getOpen());
        clone.setStatus(getStatus());
        clone.setMbCategoryId(getMbCategoryId());
        clone.setCategoryGroupId(getCategoryGroupId());
        clone.setVersion(getVersion());
        clone.setPlanVersion(getPlanVersion());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());
        clone.setModelId(getModelId());
        clone.setPromoted(getPromoted());
        clone.setPreviousContestPhase(getPreviousContestPhase());
        clone.setContestPhase(getContestPhase());

        return clone;
    }

    @Override
    public int compareTo(PlanMeta planMeta) {
        int value = 0;

        if (getId() < planMeta.getId()) {
            value = -1;
        } else if (getId() > planMeta.getId()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

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

        if (!(obj instanceof PlanMetaClp)) {
            return false;
        }

        PlanMetaClp planMeta = (PlanMetaClp) obj;

        long primaryKey = planMeta.getPrimaryKey();

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
        StringBundler sb = new StringBundler(39);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", planCreated=");
        sb.append(getPlanCreated());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", votes=");
        sb.append(getVotes());
        sb.append(", planGroupId=");
        sb.append(getPlanGroupId());
        sb.append(", open=");
        sb.append(getOpen());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", mbCategoryId=");
        sb.append(getMbCategoryId());
        sb.append(", categoryGroupId=");
        sb.append(getCategoryGroupId());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", planVersion=");
        sb.append(getPlanVersion());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", promoted=");
        sb.append(getPromoted());
        sb.append(", previousContestPhase=");
        sb.append(getPreviousContestPhase());
        sb.append(", contestPhase=");
        sb.append(getContestPhase());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(61);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanMeta");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planCreated</column-name><column-value><![CDATA[");
        sb.append(getPlanCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>votes</column-name><column-value><![CDATA[");
        sb.append(getVotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planGroupId</column-name><column-value><![CDATA[");
        sb.append(getPlanGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>open</column-name><column-value><![CDATA[");
        sb.append(getOpen());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mbCategoryId</column-name><column-value><![CDATA[");
        sb.append(getMbCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryGroupId</column-name><column-value><![CDATA[");
        sb.append(getCategoryGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planVersion</column-name><column-value><![CDATA[");
        sb.append(getPlanVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>promoted</column-name><column-value><![CDATA[");
        sb.append(getPromoted());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>previousContestPhase</column-name><column-value><![CDATA[");
        sb.append(getPreviousContestPhase());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestPhase</column-name><column-value><![CDATA[");
        sb.append(getContestPhase());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
