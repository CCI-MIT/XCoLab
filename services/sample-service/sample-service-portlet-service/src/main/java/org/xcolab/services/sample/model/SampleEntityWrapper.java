package org.xcolab.services.sample.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SampleEntity}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SampleEntity
 * @generated
 */
public class SampleEntityWrapper implements SampleEntity,
    ModelWrapper<SampleEntity> {
    private SampleEntity _sampleEntity;

    public SampleEntityWrapper(SampleEntity sampleEntity) {
        _sampleEntity = sampleEntity;
    }

    public Class<?> getModelClass() {
        return SampleEntity.class;
    }

    public String getModelClassName() {
        return SampleEntity.class.getName();
    }

    /**
    * Returns the primary key of this sample entity.
    *
    * @return the primary key of this sample entity
    */
    public long getPrimaryKey() {
        return _sampleEntity.getPrimaryKey();
    }

    /**
    * Sets the primary key of this sample entity.
    *
    * @param primaryKey the primary key of this sample entity
    */
    public void setPrimaryKey(long primaryKey) {
        _sampleEntity.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this sample entity.
    *
    * @return the ID of this sample entity
    */
    public long getId() {
        return _sampleEntity.getId();
    }

    /**
    * Sets the ID of this sample entity.
    *
    * @param id the ID of this sample entity
    */
    public void setId(long id) {
        _sampleEntity.setId(id);
    }

    /**
    * Returns the content of this sample entity.
    *
    * @return the content of this sample entity
    */
    public java.lang.String getContent() {
        return _sampleEntity.getContent();
    }

    /**
    * Sets the content of this sample entity.
    *
    * @param content the content of this sample entity
    */
    public void setContent(java.lang.String content) {
        _sampleEntity.setContent(content);
    }

    /**
    * Returns the created of this sample entity.
    *
    * @return the created of this sample entity
    */
    public java.util.Date getCreated() {
        return _sampleEntity.getCreated();
    }

    /**
    * Sets the created of this sample entity.
    *
    * @param created the created of this sample entity
    */
    public void setCreated(java.util.Date created) {
        _sampleEntity.setCreated(created);
    }

    /**
    * Returns the author ID of this sample entity.
    *
    * @return the author ID of this sample entity
    */
    public long getAuthorId() {
        return _sampleEntity.getAuthorId();
    }

    /**
    * Sets the author ID of this sample entity.
    *
    * @param authorId the author ID of this sample entity
    */
    public void setAuthorId(long authorId) {
        _sampleEntity.setAuthorId(authorId);
    }

    public boolean isNew() {
        return _sampleEntity.isNew();
    }

    public void setNew(boolean n) {
        _sampleEntity.setNew(n);
    }

    public boolean isCachedModel() {
        return _sampleEntity.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _sampleEntity.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _sampleEntity.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _sampleEntity.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _sampleEntity.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _sampleEntity.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _sampleEntity.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new SampleEntityWrapper((SampleEntity) _sampleEntity.clone());
    }

    public int compareTo(SampleEntity sampleEntity) {
        return _sampleEntity.compareTo(sampleEntity);
    }

    @Override
    public int hashCode() {
        return _sampleEntity.hashCode();
    }

    public com.liferay.portal.model.CacheModel<SampleEntity> toCacheModel() {
        return _sampleEntity.toCacheModel();
    }

    public SampleEntity toEscapedModel() {
        return new SampleEntityWrapper(_sampleEntity.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _sampleEntity.toString();
    }

    public java.lang.String toXmlString() {
        return _sampleEntity.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _sampleEntity.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public SampleEntity getWrappedSampleEntity() {
        return _sampleEntity;
    }

    public SampleEntity getWrappedModel() {
        return _sampleEntity;
    }

    public void resetOriginalValues() {
        _sampleEntity.resetOriginalValues();
    }
}
