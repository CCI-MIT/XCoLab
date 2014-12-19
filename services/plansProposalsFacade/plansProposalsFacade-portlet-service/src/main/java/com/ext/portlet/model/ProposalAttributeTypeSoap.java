package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalAttributeTypeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ProposalAttributeTypeServiceSoap
 * @generated
 */
public class ProposalAttributeTypeSoap implements Serializable {
    private String _name;
    private boolean _visibleInVersionHistory;
    private boolean _copyOnPromote;

    public ProposalAttributeTypeSoap() {
    }

    public static ProposalAttributeTypeSoap toSoapModel(
        ProposalAttributeType model) {
        ProposalAttributeTypeSoap soapModel = new ProposalAttributeTypeSoap();

        soapModel.setName(model.getName());
        soapModel.setVisibleInVersionHistory(model.getVisibleInVersionHistory());
        soapModel.setCopyOnPromote(model.getCopyOnPromote());

        return soapModel;
    }

    public static ProposalAttributeTypeSoap[] toSoapModels(
        ProposalAttributeType[] models) {
        ProposalAttributeTypeSoap[] soapModels = new ProposalAttributeTypeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalAttributeTypeSoap[][] toSoapModels(
        ProposalAttributeType[][] models) {
        ProposalAttributeTypeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalAttributeTypeSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalAttributeTypeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalAttributeTypeSoap[] toSoapModels(
        List<ProposalAttributeType> models) {
        List<ProposalAttributeTypeSoap> soapModels = new ArrayList<ProposalAttributeTypeSoap>(models.size());

        for (ProposalAttributeType model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalAttributeTypeSoap[soapModels.size()]);
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

    public boolean getVisibleInVersionHistory() {
        return _visibleInVersionHistory;
    }

    public boolean isVisibleInVersionHistory() {
        return _visibleInVersionHistory;
    }

    public void setVisibleInVersionHistory(boolean visibleInVersionHistory) {
        _visibleInVersionHistory = visibleInVersionHistory;
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
