package org.xcolab.view.pages.redballon.web.beans;

import org.xcolab.client.balloons.pojo.BalloonText;

public class AddEditBalloonTextBean {

    private long balloonTextId;
    private String name;
    private String textBeforeForm;
    private String textAfterForm;
    private String textBeforeShareButtons;
    private String textAfterShareButtons;
    private String acceptTosText;
    private String emailTemplate;
    private String emailSubjectTemplate;
    private String facebookDescription;
    private String facebookTitle;
    private String twitterDescription;
    private String twitterTitle;
    private boolean enabled;

    public AddEditBalloonTextBean() {
    }

    public AddEditBalloonTextBean(BalloonText text) {
        balloonTextId = text.getId_();
        name = text.getName();
        textBeforeForm = text.getTextBeforeForm();
        textAfterForm = text.getTextAfterForm();
        textBeforeShareButtons = text.getTextBeforeShareButtons();
        textAfterShareButtons = text.getTextAfterShareButtons();
        acceptTosText = text.getAcceptTosText();
        enabled = text.getEnabled();
        emailTemplate = text.getEmailTemplate();
        emailSubjectTemplate = text.getEmailSubjectTemplate();
        facebookDescription = text.getFacebookDescription();
        facebookTitle = text.getFacebookSubject();
        twitterDescription = text.getTwitterDescription();
        twitterTitle = text.getTwitterSubject();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextBeforeForm() {
        return textBeforeForm;
    }

    public void setTextBeforeForm(String textBeforeForm) {
        this.textBeforeForm = textBeforeForm;
    }

    public String getTextAfterForm() {
        return textAfterForm;
    }

    public void setTextAfterForm(String textAfterForm) {
        this.textAfterForm = textAfterForm;
    }

    public long getBalloonTextId() {
        return balloonTextId;
    }

    public void setBalloonTextId(long balloonTextId) {
        this.balloonTextId = balloonTextId;
    }

    @Override
    public String toString() {
        return "AddEditBalloonTextBean [balloonTextId=" + balloonTextId + ", name=" + name
                + ", textBeforeForm=" + textBeforeForm + ", textAfterForm=" + textAfterForm + "]";
    }

    public String getTextBeforeShareButtons() {
        return textBeforeShareButtons;
    }

    public void setTextBeforeShareButtons(String textBeforeShareButtons) {
        this.textBeforeShareButtons = textBeforeShareButtons;
    }

    public String getTextAfterShareButtons() {
        return textAfterShareButtons;
    }

    public void setTextAfterShareButtons(String textAfterShareButtons) {
        this.textAfterShareButtons = textAfterShareButtons;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAcceptTosText() {
        return acceptTosText;
    }

    public void setAcceptTosText(String acceptTosText) {
        this.acceptTosText = acceptTosText;
    }

    public String getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(String emailTemplate) {
        this.emailTemplate = emailTemplate;
    }

    public String getEmailSubjectTemplate() {
        return emailSubjectTemplate;
    }

    public void setEmailSubjectTemplate(String emailSubjectTemplate) {
        this.emailSubjectTemplate = emailSubjectTemplate;
    }

    public String getFacebookDescription() {
        return facebookDescription;
    }

    public void setFacebookDescription(String facebookDescription) {
        this.facebookDescription = facebookDescription;
    }

    public String getFacebookTitle() {
        return facebookTitle;
    }

    public void setFacebookTitle(String facebookTitle) {
        this.facebookTitle = facebookTitle;
    }

    public String getTwitterDescription() {
        return twitterDescription;
    }

    public void setTwitterDescription(String twitterDescription) {
        this.twitterDescription = twitterDescription;
    }

    public String getTwitterTitle() {
        return twitterTitle;
    }

    public void setTwitterTitle(String twitterTitle) {
        this.twitterTitle = twitterTitle;
    }

}
