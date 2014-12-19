package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ContestEmailTemplateServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ContestEmailTemplateServiceSoap
 * @generated
 */
public class ContestEmailTemplateSoap implements Serializable {
    private String _type;
    private String _subject;
    private String _header;
    private String _footer;

    public ContestEmailTemplateSoap() {
    }

    public static ContestEmailTemplateSoap toSoapModel(
        ContestEmailTemplate model) {
        ContestEmailTemplateSoap soapModel = new ContestEmailTemplateSoap();

        soapModel.setType(model.getType());
        soapModel.setSubject(model.getSubject());
        soapModel.setHeader(model.getHeader());
        soapModel.setFooter(model.getFooter());

        return soapModel;
    }

    public static ContestEmailTemplateSoap[] toSoapModels(
        ContestEmailTemplate[] models) {
        ContestEmailTemplateSoap[] soapModels = new ContestEmailTemplateSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestEmailTemplateSoap[][] toSoapModels(
        ContestEmailTemplate[][] models) {
        ContestEmailTemplateSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestEmailTemplateSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestEmailTemplateSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestEmailTemplateSoap[] toSoapModels(
        List<ContestEmailTemplate> models) {
        List<ContestEmailTemplateSoap> soapModels = new ArrayList<ContestEmailTemplateSoap>(models.size());

        for (ContestEmailTemplate model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestEmailTemplateSoap[soapModels.size()]);
    }

    public String getPrimaryKey() {
        return _type;
    }

    public void setPrimaryKey(String pk) {
        setType(pk);
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }

    public String getSubject() {
        return _subject;
    }

    public void setSubject(String subject) {
        _subject = subject;
    }

    public String getHeader() {
        return _header;
    }

    public void setHeader(String header) {
        _header = header;
    }

    public String getFooter() {
        return _footer;
    }

    public void setFooter(String footer) {
        _footer = footer;
    }
}
