package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanItemGroupServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.PlanItemGroupServiceSoap
 * @generated
 */
public class PlanItemGroupSoap implements Serializable {
    private long _planId;
    private long _groupId;

    public PlanItemGroupSoap() {
    }

    public static PlanItemGroupSoap toSoapModel(PlanItemGroup model) {
        PlanItemGroupSoap soapModel = new PlanItemGroupSoap();

        soapModel.setPlanId(model.getPlanId());
        soapModel.setGroupId(model.getGroupId());

        return soapModel;
    }

    public static PlanItemGroupSoap[] toSoapModels(PlanItemGroup[] models) {
        PlanItemGroupSoap[] soapModels = new PlanItemGroupSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanItemGroupSoap[][] toSoapModels(PlanItemGroup[][] models) {
        PlanItemGroupSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanItemGroupSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanItemGroupSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanItemGroupSoap[] toSoapModels(List<PlanItemGroup> models) {
        List<PlanItemGroupSoap> soapModels = new ArrayList<PlanItemGroupSoap>(models.size());

        for (PlanItemGroup model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanItemGroupSoap[soapModels.size()]);
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

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }
}
