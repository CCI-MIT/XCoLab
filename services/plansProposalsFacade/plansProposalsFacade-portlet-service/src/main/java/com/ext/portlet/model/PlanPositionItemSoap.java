package com.ext.portlet.model;

import com.ext.portlet.service.persistence.PlanPositionItemPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanPositionItemServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.PlanPositionItemServiceSoap
 * @generated
 */
public class PlanPositionItemSoap implements Serializable {
    private long _planPositionsId;
    private long _positionId;

    public PlanPositionItemSoap() {
    }

    public static PlanPositionItemSoap toSoapModel(PlanPositionItem model) {
        PlanPositionItemSoap soapModel = new PlanPositionItemSoap();

        soapModel.setPlanPositionsId(model.getPlanPositionsId());
        soapModel.setPositionId(model.getPositionId());

        return soapModel;
    }

    public static PlanPositionItemSoap[] toSoapModels(PlanPositionItem[] models) {
        PlanPositionItemSoap[] soapModels = new PlanPositionItemSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanPositionItemSoap[][] toSoapModels(
        PlanPositionItem[][] models) {
        PlanPositionItemSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanPositionItemSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanPositionItemSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanPositionItemSoap[] toSoapModels(
        List<PlanPositionItem> models) {
        List<PlanPositionItemSoap> soapModels = new ArrayList<PlanPositionItemSoap>(models.size());

        for (PlanPositionItem model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanPositionItemSoap[soapModels.size()]);
    }

    public PlanPositionItemPK getPrimaryKey() {
        return new PlanPositionItemPK(_planPositionsId, _positionId);
    }

    public void setPrimaryKey(PlanPositionItemPK pk) {
        setPlanPositionsId(pk.planPositionsId);
        setPositionId(pk.positionId);
    }

    public long getPlanPositionsId() {
        return _planPositionsId;
    }

    public void setPlanPositionsId(long planPositionsId) {
        _planPositionsId = planPositionsId;
    }

    public long getPositionId() {
        return _positionId;
    }

    public void setPositionId(long positionId) {
        _positionId = positionId;
    }
}
