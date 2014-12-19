package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.EmailListServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.EmailListServiceSoap
 * @generated
 */
public class EmailListSoap implements Serializable {
    private long _id;
    private String _name;
    private String _email;

    public EmailListSoap() {
    }

    public static EmailListSoap toSoapModel(EmailList model) {
        EmailListSoap soapModel = new EmailListSoap();

        soapModel.setId(model.getId());
        soapModel.setName(model.getName());
        soapModel.setEmail(model.getEmail());

        return soapModel;
    }

    public static EmailListSoap[] toSoapModels(EmailList[] models) {
        EmailListSoap[] soapModels = new EmailListSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static EmailListSoap[][] toSoapModels(EmailList[][] models) {
        EmailListSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new EmailListSoap[models.length][models[0].length];
        } else {
            soapModels = new EmailListSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static EmailListSoap[] toSoapModels(List<EmailList> models) {
        List<EmailListSoap> soapModels = new ArrayList<EmailListSoap>(models.size());

        for (EmailList model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new EmailListSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long pk) {
        setId(pk);
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }
}
