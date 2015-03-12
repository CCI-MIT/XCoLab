package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ImpactTemplateFocusAreaListServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ImpactTemplateFocusAreaListServiceSoap
 * @generated
 */
public class ImpactTemplateFocusAreaListSoap implements Serializable {
    private long _focusAreaListId;
    private String _name;

    public ImpactTemplateFocusAreaListSoap() {
    }

    public static ImpactTemplateFocusAreaListSoap toSoapModel(
        ImpactTemplateFocusAreaList model) {
        ImpactTemplateFocusAreaListSoap soapModel = new ImpactTemplateFocusAreaListSoap();

        soapModel.setFocusAreaListId(model.getFocusAreaListId());
        soapModel.setName(model.getName());

        return soapModel;
    }

    public static ImpactTemplateFocusAreaListSoap[] toSoapModels(
        ImpactTemplateFocusAreaList[] models) {
        ImpactTemplateFocusAreaListSoap[] soapModels = new ImpactTemplateFocusAreaListSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ImpactTemplateFocusAreaListSoap[][] toSoapModels(
        ImpactTemplateFocusAreaList[][] models) {
        ImpactTemplateFocusAreaListSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ImpactTemplateFocusAreaListSoap[models.length][models[0].length];
        } else {
            soapModels = new ImpactTemplateFocusAreaListSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ImpactTemplateFocusAreaListSoap[] toSoapModels(
        List<ImpactTemplateFocusAreaList> models) {
        List<ImpactTemplateFocusAreaListSoap> soapModels = new ArrayList<ImpactTemplateFocusAreaListSoap>(models.size());

        for (ImpactTemplateFocusAreaList model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ImpactTemplateFocusAreaListSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _focusAreaListId;
    }

    public void setPrimaryKey(long pk) {
        setFocusAreaListId(pk);
    }

    public long getFocusAreaListId() {
        return _focusAreaListId;
    }

    public void setFocusAreaListId(long focusAreaListId) {
        _focusAreaListId = focusAreaListId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
}
