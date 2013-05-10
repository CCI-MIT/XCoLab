package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link BalloonStatsEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       BalloonStatsEntry
 * @generated
 */
public class BalloonStatsEntryWrapper implements BalloonStatsEntry,
    ModelWrapper<BalloonStatsEntry> {
    private BalloonStatsEntry _balloonStatsEntry;

    public BalloonStatsEntryWrapper(BalloonStatsEntry balloonStatsEntry) {
        _balloonStatsEntry = balloonStatsEntry;
    }

    public Class<?> getModelClass() {
        return BalloonStatsEntry.class;
    }

    public String getModelClassName() {
        return BalloonStatsEntry.class.getName();
    }

    /**
    * Returns the primary key of this balloon stats entry.
    *
    * @return the primary key of this balloon stats entry
    */
    public long getPrimaryKey() {
        return _balloonStatsEntry.getPrimaryKey();
    }

    /**
    * Sets the primary key of this balloon stats entry.
    *
    * @param primaryKey the primary key of this balloon stats entry
    */
    public void setPrimaryKey(long primaryKey) {
        _balloonStatsEntry.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this balloon stats entry.
    *
    * @return the ID of this balloon stats entry
    */
    public long getId() {
        return _balloonStatsEntry.getId();
    }

    /**
    * Sets the ID of this balloon stats entry.
    *
    * @param id the ID of this balloon stats entry
    */
    public void setId(long id) {
        _balloonStatsEntry.setId(id);
    }

    /**
    * Returns the first contest ID of this balloon stats entry.
    *
    * @return the first contest ID of this balloon stats entry
    */
    public long getFirstContestId() {
        return _balloonStatsEntry.getFirstContestId();
    }

    /**
    * Sets the first contest ID of this balloon stats entry.
    *
    * @param firstContestId the first contest ID of this balloon stats entry
    */
    public void setFirstContestId(long firstContestId) {
        _balloonStatsEntry.setFirstContestId(firstContestId);
    }

    /**
    * Returns the second contest ID of this balloon stats entry.
    *
    * @return the second contest ID of this balloon stats entry
    */
    public long getSecondContestId() {
        return _balloonStatsEntry.getSecondContestId();
    }

    /**
    * Sets the second contest ID of this balloon stats entry.
    *
    * @param secondContestId the second contest ID of this balloon stats entry
    */
    public void setSecondContestId(long secondContestId) {
        _balloonStatsEntry.setSecondContestId(secondContestId);
    }

    /**
    * Returns the choosen contest of this balloon stats entry.
    *
    * @return the choosen contest of this balloon stats entry
    */
    public int getChoosenContest() {
        return _balloonStatsEntry.getChoosenContest();
    }

    /**
    * Sets the choosen contest of this balloon stats entry.
    *
    * @param choosenContest the choosen contest of this balloon stats entry
    */
    public void setChoosenContest(int choosenContest) {
        _balloonStatsEntry.setChoosenContest(choosenContest);
    }

    /**
    * Returns the cookie of this balloon stats entry.
    *
    * @return the cookie of this balloon stats entry
    */
    public java.lang.String getCookie() {
        return _balloonStatsEntry.getCookie();
    }

    /**
    * Sets the cookie of this balloon stats entry.
    *
    * @param cookie the cookie of this balloon stats entry
    */
    public void setCookie(java.lang.String cookie) {
        _balloonStatsEntry.setCookie(cookie);
    }

    /**
    * Returns the ip of this balloon stats entry.
    *
    * @return the ip of this balloon stats entry
    */
    public java.lang.String getIp() {
        return _balloonStatsEntry.getIp();
    }

    /**
    * Sets the ip of this balloon stats entry.
    *
    * @param ip the ip of this balloon stats entry
    */
    public void setIp(java.lang.String ip) {
        _balloonStatsEntry.setIp(ip);
    }

    /**
    * Returns the extra data of this balloon stats entry.
    *
    * @return the extra data of this balloon stats entry
    */
    public java.lang.String getExtraData() {
        return _balloonStatsEntry.getExtraData();
    }

    /**
    * Sets the extra data of this balloon stats entry.
    *
    * @param extraData the extra data of this balloon stats entry
    */
    public void setExtraData(java.lang.String extraData) {
        _balloonStatsEntry.setExtraData(extraData);
    }

    public boolean isNew() {
        return _balloonStatsEntry.isNew();
    }

    public void setNew(boolean n) {
        _balloonStatsEntry.setNew(n);
    }

    public boolean isCachedModel() {
        return _balloonStatsEntry.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _balloonStatsEntry.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _balloonStatsEntry.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _balloonStatsEntry.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _balloonStatsEntry.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _balloonStatsEntry.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _balloonStatsEntry.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new BalloonStatsEntryWrapper((BalloonStatsEntry) _balloonStatsEntry.clone());
    }

    public int compareTo(BalloonStatsEntry balloonStatsEntry) {
        return _balloonStatsEntry.compareTo(balloonStatsEntry);
    }

    @Override
    public int hashCode() {
        return _balloonStatsEntry.hashCode();
    }

    public com.liferay.portal.model.CacheModel<BalloonStatsEntry> toCacheModel() {
        return _balloonStatsEntry.toCacheModel();
    }

    public BalloonStatsEntry toEscapedModel() {
        return new BalloonStatsEntryWrapper(_balloonStatsEntry.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _balloonStatsEntry.toString();
    }

    public java.lang.String toXmlString() {
        return _balloonStatsEntry.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _balloonStatsEntry.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public BalloonStatsEntry getWrappedBalloonStatsEntry() {
        return _balloonStatsEntry;
    }

    public BalloonStatsEntry getWrappedModel() {
        return _balloonStatsEntry;
    }

    public void resetOriginalValues() {
        _balloonStatsEntry.resetOriginalValues();
    }
}
