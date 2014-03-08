package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.BalloonTextServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.BalloonTextServiceSoap
 * @generated
 */
public class BalloonTextSoap implements Serializable {
    private long _id;
    private String _name;
    private String _textBeforeForm;
    private String _textAfterForm;
    private String _textBeforeShareButtons;
    private String _textAfterShareButtons;

    public BalloonTextSoap() {
    }

    public static BalloonTextSoap toSoapModel(BalloonText model) {
        BalloonTextSoap soapModel = new BalloonTextSoap();

        soapModel.setId(model.getId());
        soapModel.setName(model.getName());
        soapModel.setTextBeforeForm(model.getTextBeforeForm());
        soapModel.setTextAfterForm(model.getTextAfterForm());
        soapModel.setTextBeforeShareButtons(model.getTextBeforeShareButtons());
        soapModel.setTextAfterShareButtons(model.getTextAfterShareButtons());

        return soapModel;
    }

    public static BalloonTextSoap[] toSoapModels(BalloonText[] models) {
        BalloonTextSoap[] soapModels = new BalloonTextSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static BalloonTextSoap[][] toSoapModels(BalloonText[][] models) {
        BalloonTextSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new BalloonTextSoap[models.length][models[0].length];
        } else {
            soapModels = new BalloonTextSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static BalloonTextSoap[] toSoapModels(List<BalloonText> models) {
        List<BalloonTextSoap> soapModels = new ArrayList<BalloonTextSoap>(models.size());

        for (BalloonText model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new BalloonTextSoap[soapModels.size()]);
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

    public String getTextBeforeForm() {
        return _textBeforeForm;
    }

    public void setTextBeforeForm(String textBeforeForm) {
        _textBeforeForm = textBeforeForm;
    }

    public String getTextAfterForm() {
        return _textAfterForm;
    }

    public void setTextAfterForm(String textAfterForm) {
        _textAfterForm = textAfterForm;
    }

    public String getTextBeforeShareButtons() {
        return _textBeforeShareButtons;
    }

    public void setTextBeforeShareButtons(String textBeforeShareButtons) {
        _textBeforeShareButtons = textBeforeShareButtons;
    }

    public String getTextAfterShareButtons() {
        return _textAfterShareButtons;
    }

    public void setTextAfterShareButtons(String textAfterShareButtons) {
        _textAfterShareButtons = textAfterShareButtons;
    }
}
