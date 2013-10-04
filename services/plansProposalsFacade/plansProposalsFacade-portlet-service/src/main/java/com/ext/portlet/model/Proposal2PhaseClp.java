package com.ext.portlet.model;

import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.persistence.Proposal2PhasePK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class Proposal2PhaseClp extends BaseModelImpl<Proposal2Phase>
    implements Proposal2Phase {
    private long _proposalId;
    private long _contestPhaseId;
    private long _ribbonTypeId;
    private int _versionFrom;
    private int _versionTo;
    private int _sortWeight;
    private boolean _autopromoteCandidate;

    public Proposal2PhaseClp() {
    }

    public Class<?> getModelClass() {
        return Proposal2Phase.class;
    }

    public String getModelClassName() {
        return Proposal2Phase.class.getName();
    }

    public Proposal2PhasePK getPrimaryKey() {
        return new Proposal2PhasePK(_proposalId, _contestPhaseId);
    }

    public void setPrimaryKey(Proposal2PhasePK primaryKey) {
        setProposalId(primaryKey.proposalId);
        setContestPhaseId(primaryKey.contestPhaseId);
    }

    public Serializable getPrimaryKeyObj() {
        return new Proposal2PhasePK(_proposalId, _contestPhaseId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((Proposal2PhasePK) primaryKeyObj);
    }

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }

    public long getContestPhaseId() {
        return _contestPhaseId;
    }

    public void setContestPhaseId(long contestPhaseId) {
        _contestPhaseId = contestPhaseId;
    }

    public long getRibbonTypeId() {
        return _ribbonTypeId;
    }

    public void setRibbonTypeId(long ribbonTypeId) {
        _ribbonTypeId = ribbonTypeId;
    }

    public int getVersionFrom() {
        return _versionFrom;
    }

    public void setVersionFrom(int versionFrom) {
        _versionFrom = versionFrom;
    }

    public int getVersionTo() {
        return _versionTo;
    }

    public void setVersionTo(int versionTo) {
        _versionTo = versionTo;
    }

    public int getSortWeight() {
        return _sortWeight;
    }

    public void setSortWeight(int sortWeight) {
        _sortWeight = sortWeight;
    }

    public boolean getAutopromoteCandidate() {
        return _autopromoteCandidate;
    }

    public boolean isAutopromoteCandidate() {
        return _autopromoteCandidate;
    }

    public void setAutopromoteCandidate(boolean autopromoteCandidate) {
        _autopromoteCandidate = autopromoteCandidate;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            Proposal2PhaseLocalServiceUtil.addProposal2Phase(this);
        } else {
            Proposal2PhaseLocalServiceUtil.updateProposal2Phase(this);
        }
    }

    @Override
    public Proposal2Phase toEscapedModel() {
        return (Proposal2Phase) Proxy.newProxyInstance(Proposal2Phase.class.getClassLoader(),
            new Class[] { Proposal2Phase.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        Proposal2PhaseClp clone = new Proposal2PhaseClp();

        clone.setProposalId(getProposalId());
        clone.setContestPhaseId(getContestPhaseId());
        clone.setRibbonTypeId(getRibbonTypeId());
        clone.setVersionFrom(getVersionFrom());
        clone.setVersionTo(getVersionTo());
        clone.setSortWeight(getSortWeight());
        clone.setAutopromoteCandidate(getAutopromoteCandidate());

        return clone;
    }

    public int compareTo(Proposal2Phase proposal2Phase) {
        Proposal2PhasePK primaryKey = proposal2Phase.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        Proposal2PhaseClp proposal2Phase = null;

        try {
            proposal2Phase = (Proposal2PhaseClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Proposal2PhasePK primaryKey = proposal2Phase.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{proposalId=");
        sb.append(getProposalId());
        sb.append(", contestPhaseId=");
        sb.append(getContestPhaseId());
        sb.append(", ribbonTypeId=");
        sb.append(getRibbonTypeId());
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

    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

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
            "<column><column-name>ribbonTypeId</column-name><column-value><![CDATA[");
        sb.append(getRibbonTypeId());
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
