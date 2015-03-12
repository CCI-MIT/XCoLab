package com.ext.portlet.model;

import com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ImpactTemplateMaxFocusAreaServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ImpactTemplateMaxFocusAreaServiceSoap
 * @generated
 */
public class ImpactTemplateMaxFocusAreaSoap implements Serializable {
    private long _focusAreaListId;
    private long _focusAreaId;

    public ImpactTemplateMaxFocusAreaSoap() {
    }

    public static ImpactTemplateMaxFocusAreaSoap toSoapModel(
        ImpactTemplateMaxFocusArea model) {
        ImpactTemplateMaxFocusAreaSoap soapModel = new ImpactTemplateMaxFocusAreaSoap();

        soapModel.setFocusAreaListId(model.getFocusAreaListId());
        soapModel.setFocusAreaId(model.getFocusAreaId());

        return soapModel;
    }

    public static ImpactTemplateMaxFocusAreaSoap[] toSoapModels(
        ImpactTemplateMaxFocusArea[] models) {
        ImpactTemplateMaxFocusAreaSoap[] soapModels = new ImpactTemplateMaxFocusAreaSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ImpactTemplateMaxFocusAreaSoap[][] toSoapModels(
        ImpactTemplateMaxFocusArea[][] models) {
        ImpactTemplateMaxFocusAreaSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ImpactTemplateMaxFocusAreaSoap[models.length][models[0].length];
        } else {
            soapModels = new ImpactTemplateMaxFocusAreaSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ImpactTemplateMaxFocusAreaSoap[] toSoapModels(
        List<ImpactTemplateMaxFocusArea> models) {
        List<ImpactTemplateMaxFocusAreaSoap> soapModels = new ArrayList<ImpactTemplateMaxFocusAreaSoap>(models.size());

        for (ImpactTemplateMaxFocusArea model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ImpactTemplateMaxFocusAreaSoap[soapModels.size()]);
    }

    public ImpactTemplateMaxFocusAreaPK getPrimaryKey() {
        return new ImpactTemplateMaxFocusAreaPK(_focusAreaListId, _focusAreaId);
    }

    public void setPrimaryKey(ImpactTemplateMaxFocusAreaPK pk) {
        setFocusAreaListId(pk.focusAreaListId);
        setFocusAreaId(pk.focusAreaId);
    }

    public long getFocusAreaListId() {
        return _focusAreaListId;
    }

    public void setFocusAreaListId(long focusAreaListId) {
        _focusAreaListId = focusAreaListId;
    }

    public long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(long focusAreaId) {
        _focusAreaId = focusAreaId;
    }
}
