package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.Plan2ProposalServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.Plan2ProposalServiceSoap
 * @generated
 */
public class Plan2ProposalSoap implements Serializable {
    private long _planId;
    private long _proposalId;

    public Plan2ProposalSoap() {
    }

    public static Plan2ProposalSoap toSoapModel(Plan2Proposal model) {
        Plan2ProposalSoap soapModel = new Plan2ProposalSoap();

        soapModel.setPlanId(model.getPlanId());
        soapModel.setProposalId(model.getProposalId());

        return soapModel;
    }

    public static Plan2ProposalSoap[] toSoapModels(Plan2Proposal[] models) {
        Plan2ProposalSoap[] soapModels = new Plan2ProposalSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static Plan2ProposalSoap[][] toSoapModels(Plan2Proposal[][] models) {
        Plan2ProposalSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new Plan2ProposalSoap[models.length][models[0].length];
        } else {
            soapModels = new Plan2ProposalSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static Plan2ProposalSoap[] toSoapModels(List<Plan2Proposal> models) {
        List<Plan2ProposalSoap> soapModels = new ArrayList<Plan2ProposalSoap>(models.size());

        for (Plan2Proposal model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new Plan2ProposalSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _planId;
    }

    public void setPrimaryKey(long pk) {
        setPlanId(pk);
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }
}
