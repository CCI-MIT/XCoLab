package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanTemplateServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanTemplateServiceSoap
 * @generated
 */
public class PlanTemplateSoap implements Serializable {
    private Long _id;
    private String _name;

    public PlanTemplateSoap() {
    }

    public static PlanTemplateSoap toSoapModel(PlanTemplate model) {
        PlanTemplateSoap soapModel = new PlanTemplateSoap();

        soapModel.setId(model.getId());
        soapModel.setName(model.getName());

        return soapModel;
    }

    public static PlanTemplateSoap[] toSoapModels(PlanTemplate[] models) {
        PlanTemplateSoap[] soapModels = new PlanTemplateSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanTemplateSoap[][] toSoapModels(PlanTemplate[][] models) {
        PlanTemplateSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanTemplateSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanTemplateSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanTemplateSoap[] toSoapModels(List<PlanTemplate> models) {
        List<PlanTemplateSoap> soapModels = new ArrayList<PlanTemplateSoap>(models.size());

        for (PlanTemplate model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanTemplateSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long pk) {
        setId(pk);
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
}
