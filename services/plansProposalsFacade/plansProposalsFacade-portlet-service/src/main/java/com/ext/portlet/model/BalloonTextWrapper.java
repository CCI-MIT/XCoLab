package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BalloonText}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonText
 * @generated
 */
public class BalloonTextWrapper implements BalloonText,
    ModelWrapper<BalloonText> {
    private BalloonText _balloonText;

    public BalloonTextWrapper(BalloonText balloonText) {
        _balloonText = balloonText;
    }

    @Override
    public Class<?> getModelClass() {
        return BalloonText.class;
    }

    @Override
    public String getModelClassName() {
        return BalloonText.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("name", getName());
        attributes.put("textBeforeForm", getTextBeforeForm());
        attributes.put("textAfterForm", getTextAfterForm());
        attributes.put("textBeforeShareButtons", getTextBeforeShareButtons());
        attributes.put("textAfterShareButtons", getTextAfterShareButtons());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String textBeforeForm = (String) attributes.get("textBeforeForm");

        if (textBeforeForm != null) {
            setTextBeforeForm(textBeforeForm);
        }

        String textAfterForm = (String) attributes.get("textAfterForm");

        if (textAfterForm != null) {
            setTextAfterForm(textAfterForm);
        }

        String textBeforeShareButtons = (String) attributes.get(
                "textBeforeShareButtons");

        if (textBeforeShareButtons != null) {
            setTextBeforeShareButtons(textBeforeShareButtons);
        }

        String textAfterShareButtons = (String) attributes.get(
                "textAfterShareButtons");

        if (textAfterShareButtons != null) {
            setTextAfterShareButtons(textAfterShareButtons);
        }
    }

    /**
    * Returns the primary key of this balloon text.
    *
    * @return the primary key of this balloon text
    */
    @Override
    public long getPrimaryKey() {
        return _balloonText.getPrimaryKey();
    }

    /**
    * Sets the primary key of this balloon text.
    *
    * @param primaryKey the primary key of this balloon text
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _balloonText.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this balloon text.
    *
    * @return the ID of this balloon text
    */
    @Override
    public long getId() {
        return _balloonText.getId();
    }

    /**
    * Sets the ID of this balloon text.
    *
    * @param id the ID of this balloon text
    */
    @Override
    public void setId(long id) {
        _balloonText.setId(id);
    }

    /**
    * Returns the name of this balloon text.
    *
    * @return the name of this balloon text
    */
    @Override
    public java.lang.String getName() {
        return _balloonText.getName();
    }

    /**
    * Sets the name of this balloon text.
    *
    * @param name the name of this balloon text
    */
    @Override
    public void setName(java.lang.String name) {
        _balloonText.setName(name);
    }

    /**
    * Returns the text before form of this balloon text.
    *
    * @return the text before form of this balloon text
    */
    @Override
    public java.lang.String getTextBeforeForm() {
        return _balloonText.getTextBeforeForm();
    }

    /**
    * Sets the text before form of this balloon text.
    *
    * @param textBeforeForm the text before form of this balloon text
    */
    @Override
    public void setTextBeforeForm(java.lang.String textBeforeForm) {
        _balloonText.setTextBeforeForm(textBeforeForm);
    }

    /**
    * Returns the text after form of this balloon text.
    *
    * @return the text after form of this balloon text
    */
    @Override
    public java.lang.String getTextAfterForm() {
        return _balloonText.getTextAfterForm();
    }

    /**
    * Sets the text after form of this balloon text.
    *
    * @param textAfterForm the text after form of this balloon text
    */
    @Override
    public void setTextAfterForm(java.lang.String textAfterForm) {
        _balloonText.setTextAfterForm(textAfterForm);
    }

    /**
    * Returns the text before share buttons of this balloon text.
    *
    * @return the text before share buttons of this balloon text
    */
    @Override
    public java.lang.String getTextBeforeShareButtons() {
        return _balloonText.getTextBeforeShareButtons();
    }

    /**
    * Sets the text before share buttons of this balloon text.
    *
    * @param textBeforeShareButtons the text before share buttons of this balloon text
    */
    @Override
    public void setTextBeforeShareButtons(
        java.lang.String textBeforeShareButtons) {
        _balloonText.setTextBeforeShareButtons(textBeforeShareButtons);
    }

    /**
    * Returns the text after share buttons of this balloon text.
    *
    * @return the text after share buttons of this balloon text
    */
    @Override
    public java.lang.String getTextAfterShareButtons() {
        return _balloonText.getTextAfterShareButtons();
    }

    /**
    * Sets the text after share buttons of this balloon text.
    *
    * @param textAfterShareButtons the text after share buttons of this balloon text
    */
    @Override
    public void setTextAfterShareButtons(java.lang.String textAfterShareButtons) {
        _balloonText.setTextAfterShareButtons(textAfterShareButtons);
    }

    @Override
    public boolean isNew() {
        return _balloonText.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _balloonText.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _balloonText.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _balloonText.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _balloonText.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _balloonText.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _balloonText.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _balloonText.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _balloonText.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _balloonText.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _balloonText.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new BalloonTextWrapper((BalloonText) _balloonText.clone());
    }

    @Override
    public int compareTo(BalloonText balloonText) {
        return _balloonText.compareTo(balloonText);
    }

    @Override
    public int hashCode() {
        return _balloonText.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<BalloonText> toCacheModel() {
        return _balloonText.toCacheModel();
    }

    @Override
    public BalloonText toEscapedModel() {
        return new BalloonTextWrapper(_balloonText.toEscapedModel());
    }

    @Override
    public BalloonText toUnescapedModel() {
        return new BalloonTextWrapper(_balloonText.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _balloonText.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _balloonText.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _balloonText.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BalloonTextWrapper)) {
            return false;
        }

        BalloonTextWrapper balloonTextWrapper = (BalloonTextWrapper) obj;

        if (Validator.equals(_balloonText, balloonTextWrapper._balloonText)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public BalloonText getWrappedBalloonText() {
        return _balloonText;
    }

    @Override
    public BalloonText getWrappedModel() {
        return _balloonText;
    }

    @Override
    public void resetOriginalValues() {
        _balloonText.resetOriginalValues();
    }
}
