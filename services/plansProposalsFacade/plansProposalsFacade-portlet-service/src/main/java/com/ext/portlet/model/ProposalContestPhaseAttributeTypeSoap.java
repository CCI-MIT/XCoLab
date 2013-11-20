package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalContestPhaseAttributeTypeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ProposalContestPhaseAttributeTypeServiceSoap
 * @generated
 */
public class ProposalContestPhaseAttributeTypeSoap implements Serializable {
    private String _name;
    private boolean _copyOnPromote;

    public ProposalContestPhaseAttributeTypeSoap() {
    }

    public static ProposalContestPhaseAttributeTypeSoap toSoapModel(
        ProposalContestPhaseAttributeType model) {
        ProposalContestPhaseAttributeTypeSoap soapModel = new ProposalContestPhaseAttributeTypeSoap();

        soapModel.setName(model.getName());
        soapModel.setCopyOnPromote(model.getCopyOnPromote());

        return soapModel;
    }

    public static ProposalContestPhaseAttributeTypeSoap[] toSoapModels(
        ProposalContestPhaseAttributeType[] models) {
        ProposalContestPhaseAttributeTypeSoap[] soapModels = new ProposalContestPhaseAttributeTypeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalContestPhaseAttributeTypeSoap[][] toSoapModels(
        ProposalContestPhaseAttributeType[][] models) {
        ProposalContestPhaseAttributeTypeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalContestPhaseAttributeTypeSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalContestPhaseAttributeTypeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalContestPhaseAttributeTypeSoap[] toSoapModels(
        List<ProposalContestPhaseAttributeType> models) {
        List<ProposalContestPhaseAttributeTypeSoap> soapModels = new ArrayList<ProposalContestPhaseAttributeTypeSoap>(models.size());

        for (ProposalContestPhaseAttributeType model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalContestPhaseAttributeTypeSoap[soapModels.size()]);
    }

    public String getPrimaryKey() {
        return _name;
    }

    public void setPrimaryKey(String pk) {
        setName(pk);
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public boolean getCopyOnPromote() {
        return _copyOnPromote;
    }

    public boolean isCopyOnPromote() {
        return _copyOnPromote;
    }

    public void setCopyOnPromote(boolean copyOnPromote) {
        _copyOnPromote = copyOnPromote;
    }
}
