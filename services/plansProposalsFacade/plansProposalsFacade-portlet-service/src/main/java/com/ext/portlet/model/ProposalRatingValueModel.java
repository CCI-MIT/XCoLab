package com.ext.portlet.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the ProposalRatingValue service. Represents a row in the &quot;xcolab_ProposalRatingValue&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ext.portlet.model.impl.ProposalRatingValueModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ext.portlet.model.impl.ProposalRatingValueImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingValue
 * @see com.ext.portlet.model.impl.ProposalRatingValueImpl
 * @see com.ext.portlet.model.impl.ProposalRatingValueModelImpl
 * @generated
 */
public interface ProposalRatingValueModel extends BaseModel<ProposalRatingValue> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a proposal rating value model instance should use the {@link ProposalRatingValue} interface instead.
     */

    /**
     * Returns the primary key of this proposal rating value.
     *
     * @return the primary key of this proposal rating value
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this proposal rating value.
     *
     * @param primaryKey the primary key of this proposal rating value
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the ID of this proposal rating value.
     *
     * @return the ID of this proposal rating value
     */
    public long getId();

    /**
     * Sets the ID of this proposal rating value.
     *
     * @param id the ID of this proposal rating value
     */
    public void setId(long id);

    /**
     * Returns the rating type ID of this proposal rating value.
     *
     * @return the rating type ID of this proposal rating value
     */
    public long getRatingTypeId();

    /**
     * Sets the rating type ID of this proposal rating value.
     *
     * @param ratingTypeId the rating type ID of this proposal rating value
     */
    public void setRatingTypeId(long ratingTypeId);

    /**
     * Returns the value of this proposal rating value.
     *
     * @return the value of this proposal rating value
     */
    public long getValue();

    /**
     * Sets the value of this proposal rating value.
     *
     * @param value the value of this proposal rating value
     */
    public void setValue(long value);

    /**
     * Returns the name of this proposal rating value.
     *
     * @return the name of this proposal rating value
     */
    @AutoEscape
    public String getName();

    /**
     * Sets the name of this proposal rating value.
     *
     * @param name the name of this proposal rating value
     */
    public void setName(String name);

    /**
     * Returns the description of this proposal rating value.
     *
     * @return the description of this proposal rating value
     */
    @AutoEscape
    public String getDescription();

    /**
     * Sets the description of this proposal rating value.
     *
     * @param description the description of this proposal rating value
     */
    public void setDescription(String description);

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
    public int compareTo(
        com.ext.portlet.model.ProposalRatingValue proposalRatingValue);

    @Override
    public int hashCode();

    @Override
    public CacheModel<com.ext.portlet.model.ProposalRatingValue> toCacheModel();

    @Override
    public com.ext.portlet.model.ProposalRatingValue toEscapedModel();

    @Override
    public com.ext.portlet.model.ProposalRatingValue toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
