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
    private String _acceptTosText;
    private String _emailTemplate;
    private String _emailSubjectTemplate;
    private String _twitterDescription;
    private String _twitterSubject;
    private String _facebookDescription;
    private String _facebookSubject;
    private boolean _enabled;

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
        soapModel.setAcceptTosText(model.getAcceptTosText());
        soapModel.setEmailTemplate(model.getEmailTemplate());
        soapModel.setEmailSubjectTemplate(model.getEmailSubjectTemplate());
        soapModel.setTwitterDescription(model.getTwitterDescription());
        soapModel.setTwitterSubject(model.getTwitterSubject());
        soapModel.setFacebookDescription(model.getFacebookDescription());
        soapModel.setFacebookSubject(model.getFacebookSubject());
        soapModel.setEnabled(model.getEnabled());

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

    public String getAcceptTosText() {
        return _acceptTosText;
    }

    public void setAcceptTosText(String acceptTosText) {
        _acceptTosText = acceptTosText;
    }

    public String getEmailTemplate() {
        return _emailTemplate;
    }

    public void setEmailTemplate(String emailTemplate) {
        _emailTemplate = emailTemplate;
    }

    public String getEmailSubjectTemplate() {
        return _emailSubjectTemplate;
    }

    public void setEmailSubjectTemplate(String emailSubjectTemplate) {
        _emailSubjectTemplate = emailSubjectTemplate;
    }

    public String getTwitterDescription() {
        return _twitterDescription;
    }

    public void setTwitterDescription(String twitterDescription) {
        _twitterDescription = twitterDescription;
    }

    public String getTwitterSubject() {
        return _twitterSubject;
    }

    public void setTwitterSubject(String twitterSubject) {
        _twitterSubject = twitterSubject;
    }

    public String getFacebookDescription() {
        return _facebookDescription;
    }

    public void setFacebookDescription(String facebookDescription) {
        _facebookDescription = facebookDescription;
    }

    public String getFacebookSubject() {
        return _facebookSubject;
    }

    public void setFacebookSubject(String facebookSubject) {
        _facebookSubject = facebookSubject;
    }

    public boolean getEnabled() {
        return _enabled;
    }

    public boolean isEnabled() {
        return _enabled;
    }

    public void setEnabled(boolean enabled) {
        _enabled = enabled;
    }
}
