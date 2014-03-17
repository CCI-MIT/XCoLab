package com.ext.portlet.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the BalloonText service. Represents a row in the &quot;xcolab_BalloonText&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ext.portlet.model.impl.BalloonTextModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ext.portlet.model.impl.BalloonTextImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonText
 * @see com.ext.portlet.model.impl.BalloonTextImpl
 * @see com.ext.portlet.model.impl.BalloonTextModelImpl
 * @generated
 */
public interface BalloonTextModel extends BaseModel<BalloonText> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a balloon text model instance should use the {@link BalloonText} interface instead.
     */

    /**
     * Returns the primary key of this balloon text.
     *
     * @return the primary key of this balloon text
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this balloon text.
     *
     * @param primaryKey the primary key of this balloon text
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the ID of this balloon text.
     *
     * @return the ID of this balloon text
     */
    public long getId();

    /**
     * Sets the ID of this balloon text.
     *
     * @param id the ID of this balloon text
     */
    public void setId(long id);

    /**
     * Returns the name of this balloon text.
     *
     * @return the name of this balloon text
     */
    @AutoEscape
    public String getName();

    /**
     * Sets the name of this balloon text.
     *
     * @param name the name of this balloon text
     */
    public void setName(String name);

    /**
     * Returns the text before form of this balloon text.
     *
     * @return the text before form of this balloon text
     */
    @AutoEscape
    public String getTextBeforeForm();

    /**
     * Sets the text before form of this balloon text.
     *
     * @param textBeforeForm the text before form of this balloon text
     */
    public void setTextBeforeForm(String textBeforeForm);

    /**
     * Returns the text after form of this balloon text.
     *
     * @return the text after form of this balloon text
     */
    @AutoEscape
    public String getTextAfterForm();

    /**
     * Sets the text after form of this balloon text.
     *
     * @param textAfterForm the text after form of this balloon text
     */
    public void setTextAfterForm(String textAfterForm);

    /**
     * Returns the text before share buttons of this balloon text.
     *
     * @return the text before share buttons of this balloon text
     */
    @AutoEscape
    public String getTextBeforeShareButtons();

    /**
     * Sets the text before share buttons of this balloon text.
     *
     * @param textBeforeShareButtons the text before share buttons of this balloon text
     */
    public void setTextBeforeShareButtons(String textBeforeShareButtons);

    /**
     * Returns the text after share buttons of this balloon text.
     *
     * @return the text after share buttons of this balloon text
     */
    @AutoEscape
    public String getTextAfterShareButtons();

    /**
     * Sets the text after share buttons of this balloon text.
     *
     * @param textAfterShareButtons the text after share buttons of this balloon text
     */
    public void setTextAfterShareButtons(String textAfterShareButtons);

    /**
     * Returns the enabled of this balloon text.
     *
     * @return the enabled of this balloon text
     */
    public boolean getEnabled();

    /**
     * Returns <code>true</code> if this balloon text is enabled.
     *
     * @return <code>true</code> if this balloon text is enabled; <code>false</code> otherwise
     */
    public boolean isEnabled();

    /**
     * Sets whether this balloon text is enabled.
     *
     * @param enabled the enabled of this balloon text
     */
    public void setEnabled(boolean enabled);

    @Override
    public boolean isNew();

    @Override
    public void setNew(boolean n);

    @Override
    public boolean isCachedModel();

    @Override
    public void setCachedModel(boolean cachedModel);

    @Override
    public boolean isEscapedModel();

    @Override
    public Serializable getPrimaryKeyObj();

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    @Override
    public ExpandoBridge getExpandoBridge();

    @Override
    public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

    @Override
    public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    @Override
    public Object clone();

    @Override
    public int compareTo(BalloonText balloonText);

    @Override
    public int hashCode();

    @Override
    public CacheModel<BalloonText> toCacheModel();

    @Override
    public BalloonText toEscapedModel();

    @Override
    public BalloonText toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
