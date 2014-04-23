package org.xcolab.portlets.redballoon.web.beans;

import com.ext.portlet.model.BalloonText;

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
	private boolean enabled;
	
	public AddEditBalloonTextBean() {
		
	}
	
	public AddEditBalloonTextBean(BalloonText text) {
		balloonTextId = text.getId();
		name = text.getName();
		textBeforeForm = text.getTextBeforeForm();
		textAfterForm = text.getTextAfterForm();
		textBeforeShareButtons = text.getTextBeforeShareButtons();
		textAfterShareButtons = text.getTextAfterShareButtons();
		acceptTosText = text.getAcceptTosText();
		enabled = text.getEnabled();
		emailTemplate = text.getEmailTemplate();
		emailSubjectTemplate = text.getEmailSubjectTemplate();
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
		return "AddEditBalloonTextBean [balloonTextId=" + balloonTextId
				+ ", name=" + name + ", textBeforeForm=" + textBeforeForm
				+ ", textAfterForm=" + textAfterForm + "]";
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
	
	

}
