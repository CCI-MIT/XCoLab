package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.persistence.Proposal2PhasePK;

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


public class Proposal2PhaseClp extends BaseModelImpl<Proposal2Phase>
    implements Proposal2Phase {
    private long _proposalId;
    private long _contestPhaseId;
    private int _versionFrom;
    private int _versionTo;
    private int _sortWeight;
    private boolean _autopromoteCandidate;
    private BaseModel<?> _proposal2PhaseRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public Proposal2PhaseClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return Proposal2Phase.class;
    }

    @Override
    public String getModelClassName() {
        return Proposal2Phase.class.getName();
    }

    @Override
    public Proposal2PhasePK getPrimaryKey() {
        return new Proposal2PhasePK(_proposalId, _contestPhaseId);
    }

    @Override
    public void setPrimaryKey(Proposal2PhasePK primaryKey) {
        setProposalId(primaryKey.proposalId);
        setContestPhaseId(primaryKey.contestPhaseId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new Proposal2PhasePK(_proposalId, _contestPhaseId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((Proposal2PhasePK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalId", getProposalId());
        attributes.put("contestPhaseId", getContestPhaseId());
        attributes.put("versionFrom", getVersionFrom());
        attributes.put("versionTo", getVersionTo());
        attributes.put("sortWeight", getSortWeight());
        attributes.put("autopromoteCandidate", getAutopromoteCandidate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Long contestPhaseId = (Long) attributes.get("contestPhaseId");

        if (contestPhaseId != null) {
            setContestPhaseId(contestPhaseId);
        }

        Integer versionFrom = (Integer) attributes.get("versionFrom");

        if (versionFrom != null) {
            setVersionFrom(versionFrom);
        }

        Integer versionTo = (Integer) attributes.get("versionTo");

        if (versionTo != null) {
            setVersionTo(versionTo);
        }

        Integer sortWeight = (Integer) attributes.get("sortWeight");

        if (sortWeight != null) {
            setSortWeight(sortWeight);
        }

        Boolean autopromoteCandidate = (Boolean) attributes.get(
                "autopromoteCandidate");

        if (autopromoteCandidate != null) {
            setAutopromoteCandidate(autopromoteCandidate);
        }
    }

    @Override
    public long getProposalId() {
        return _proposalId;
    }

    @Override
    public void setProposalId(long proposalId) {
        _proposalId = proposalId;

        if (_proposal2PhaseRemoteModel != null) {
            try {
                Class<?> clazz = _proposal2PhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_proposal2PhaseRemoteModel, proposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getContestPhaseId() {
        return _contestPhaseId;
    }

    @Override
    public void setContestPhaseId(long contestPhaseId) {
        _contestPhaseId = contestPhaseId;

        if (_proposal2PhaseRemoteModel != null) {
            try {
                Class<?> clazz = _proposal2PhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPhaseId", long.class);

                method.invoke(_proposal2PhaseRemoteModel, contestPhaseId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getVersionFrom() {
        return _versionFrom;
    }

    @Override
    public void setVersionFrom(int versionFrom) {
        _versionFrom = versionFrom;

        if (_proposal2PhaseRemoteModel != null) {
            try {
                Class<?> clazz = _proposal2PhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionFrom", int.class);

                method.invoke(_proposal2PhaseRemoteModel, versionFrom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getVersionTo() {
        return _versionTo;
    }

    @Override
    public void setVersionTo(int versionTo) {
        _versionTo = versionTo;

        if (_proposal2PhaseRemoteModel != null) {
            try {
                Class<?> clazz = _proposal2PhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionTo", int.class);

                method.invoke(_proposal2PhaseRemoteModel, versionTo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSortWeight() {
        return _sortWeight;
    }

    @Override
    public void setSortWeight(int sortWeight) {
        _sortWeight = sortWeight;

        if (_proposal2PhaseRemoteModel != null) {
            try {
                Class<?> clazz = _proposal2PhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setSortWeight", int.class);

                method.invoke(_proposal2PhaseRemoteModel, sortWeight);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getAutopromoteCandidate() {
        return _autopromoteCandidate;
    }

    @Override
    public boolean isAutopromoteCandidate() {
        return _autopromoteCandidate;
    }

    @Override
    public void setAutopromoteCandidate(boolean autopromoteCandidate) {
        _autopromoteCandidate = autopromoteCandidate;

        if (_proposal2PhaseRemoteModel != null) {
            try {
                Class<?> clazz = _proposal2PhaseRemoteModel.getClass();

                Method method = clazz.getMethod("setAutopromoteCandidate",
                        boolean.class);

                method.invoke(_proposal2PhaseRemoteModel, autopromoteCandidate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposal2PhaseRemoteModel() {
        return _proposal2PhaseRemoteModel;
    }

    public void setProposal2PhaseRemoteModel(
        BaseModel<?> proposal2PhaseRemoteModel) {
        _proposal2PhaseRemoteModel = proposal2PhaseRemoteModel;
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

        Class<?> remoteModelClass = _proposal2PhaseRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposal2PhaseRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            Proposal2PhaseLocalServiceUtil.addProposal2Phase(this);
        } else {
            Proposal2PhaseLocalServiceUtil.updateProposal2Phase(this);
        }
    }

    @Override
    public Proposal2Phase toEscapedModel() {
        return (Proposal2Phase) ProxyUtil.newProxyInstance(Proposal2Phase.class.getClassLoader(),
            new Class[] { Proposal2Phase.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        Proposal2PhaseClp clone = new Proposal2PhaseClp();

        clone.setProposalId(getProposalId());
        clone.setContestPhaseId(getContestPhaseId());
        clone.setVersionFrom(getVersionFrom());
        clone.setVersionTo(getVersionTo());
        clone.setSortWeight(getSortWeight());
        clone.setAutopromoteCandidate(getAutopromoteCandidate());

        return clone;
    }

    @Override
    public int compareTo(Proposal2Phase proposal2Phase) {
        Proposal2PhasePK primaryKey = proposal2Phase.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Proposal2PhaseClp)) {
            return false;
        }

        Proposal2PhaseClp proposal2Phase = (Proposal2PhaseClp) obj;

        Proposal2PhasePK primaryKey = proposal2Phase.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
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
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{proposalId=");
        sb.append(getProposalId());
        sb.append(", contestPhaseId=");
        sb.append(getContestPhaseId());
        sb.append(", versionFrom=");
        sb.append(getVersionFrom());
        sb.append(", versionTo=");
        sb.append(getVersionTo());
        sb.append(", sortWeight=");
        sb.append(getSortWeight());
        sb.append(", autopromoteCandidate=");
        sb.append(getAutopromoteCandidate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.Proposal2Phase");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestPhaseId</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionFrom</column-name><column-value><![CDATA[");
        sb.append(getVersionFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionTo</column-name><column-value><![CDATA[");
        sb.append(getVersionTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sortWeight</column-name><column-value><![CDATA[");
        sb.append(getSortWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>autopromoteCandidate</column-name><column-value><![CDATA[");
        sb.append(getAutopromoteCandidate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
