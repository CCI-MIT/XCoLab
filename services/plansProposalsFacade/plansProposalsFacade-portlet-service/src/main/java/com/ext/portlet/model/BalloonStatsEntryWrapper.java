package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BalloonStatsEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonStatsEntry
 * @generated
 */
public class BalloonStatsEntryWrapper implements BalloonStatsEntry,
    ModelWrapper<BalloonStatsEntry> {
    private BalloonStatsEntry _balloonStatsEntry;

    public BalloonStatsEntryWrapper(BalloonStatsEntry balloonStatsEntry) {
        _balloonStatsEntry = balloonStatsEntry;
    }

    @Override
    public Class<?> getModelClass() {
        return BalloonStatsEntry.class;
    }

    @Override
    public String getModelClassName() {
        return BalloonStatsEntry.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("firstContestId", getFirstContestId());
        attributes.put("secondContestId", getSecondContestId());
        attributes.put("choosenContest", getChoosenContest());
        attributes.put("cookie", getCookie());
        attributes.put("ip", getIp());
        attributes.put("extraData", getExtraData());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long firstContestId = (Long) attributes.get("firstContestId");

        if (firstContestId != null) {
            setFirstContestId(firstContestId);
        }

        Long secondContestId = (Long) attributes.get("secondContestId");

        if (secondContestId != null) {
            setSecondContestId(secondContestId);
        }

        Integer choosenContest = (Integer) attributes.get("choosenContest");

        if (choosenContest != null) {
            setChoosenContest(choosenContest);
        }

        String cookie = (String) attributes.get("cookie");

        if (cookie != null) {
            setCookie(cookie);
        }

        String ip = (String) attributes.get("ip");

        if (ip != null) {
            setIp(ip);
        }

        String extraData = (String) attributes.get("extraData");

        if (extraData != null) {
            setExtraData(extraData);
        }
    }

    /**
    * Returns the primary key of this balloon stats entry.
    *
    * @return the primary key of this balloon stats entry
    */
    @Override
    public long getPrimaryKey() {
        return _balloonStatsEntry.getPrimaryKey();
    }

    /**
    * Sets the primary key of this balloon stats entry.
    *
    * @param primaryKey the primary key of this balloon stats entry
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _balloonStatsEntry.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this balloon stats entry.
    *
    * @return the ID of this balloon stats entry
    */
    @Override
    public long getId() {
        return _balloonStatsEntry.getId();
    }

    /**
    * Sets the ID of this balloon stats entry.
    *
    * @param id the ID of this balloon stats entry
    */
    @Override
    public void setId(long id) {
        _balloonStatsEntry.setId(id);
    }

    /**
    * Returns the first contest ID of this balloon stats entry.
    *
    * @return the first contest ID of this balloon stats entry
    */
    @Override
    public long getFirstContestId() {
        return _balloonStatsEntry.getFirstContestId();
    }

    /**
    * Sets the first contest ID of this balloon stats entry.
    *
    * @param firstContestId the first contest ID of this balloon stats entry
    */
    @Override
    public void setFirstContestId(long firstContestId) {
        _balloonStatsEntry.setFirstContestId(firstContestId);
    }

    /**
    * Returns the second contest ID of this balloon stats entry.
    *
    * @return the second contest ID of this balloon stats entry
    */
    @Override
    public long getSecondContestId() {
        return _balloonStatsEntry.getSecondContestId();
    }

    /**
    * Sets the second contest ID of this balloon stats entry.
    *
    * @param secondContestId the second contest ID of this balloon stats entry
    */
    @Override
    public void setSecondContestId(long secondContestId) {
        _balloonStatsEntry.setSecondContestId(secondContestId);
    }

    /**
    * Returns the choosen contest of this balloon stats entry.
    *
    * @return the choosen contest of this balloon stats entry
    */
    @Override
    public int getChoosenContest() {
        return _balloonStatsEntry.getChoosenContest();
    }

    /**
    * Sets the choosen contest of this balloon stats entry.
    *
    * @param choosenContest the choosen contest of this balloon stats entry
    */
    @Override
    public void setChoosenContest(int choosenContest) {
        _balloonStatsEntry.setChoosenContest(choosenContest);
    }

    /**
    * Returns the cookie of this balloon stats entry.
    *
    * @return the cookie of this balloon stats entry
    */
    @Override
    public java.lang.String getCookie() {
        return _balloonStatsEntry.getCookie();
    }

    /**
    * Sets the cookie of this balloon stats entry.
    *
    * @param cookie the cookie of this balloon stats entry
    */
    @Override
    public void setCookie(java.lang.String cookie) {
        _balloonStatsEntry.setCookie(cookie);
    }

    /**
    * Returns the ip of this balloon stats entry.
    *
    * @return the ip of this balloon stats entry
    */
    @Override
    public java.lang.String getIp() {
        return _balloonStatsEntry.getIp();
    }

    /**
    * Sets the ip of this balloon stats entry.
    *
    * @param ip the ip of this balloon stats entry
    */
    @Override
    public void setIp(java.lang.String ip) {
        _balloonStatsEntry.setIp(ip);
    }

    /**
    * Returns the extra data of this balloon stats entry.
    *
    * @return the extra data of this balloon stats entry
    */
    @Override
    public java.lang.String getExtraData() {
        return _balloonStatsEntry.getExtraData();
    }

    /**
    * Sets the extra data of this balloon stats entry.
    *
    * @param extraData the extra data of this balloon stats entry
    */
    @Override
    public void setExtraData(java.lang.String extraData) {
        _balloonStatsEntry.setExtraData(extraData);
    }

    @Override
    public boolean isNew() {
        return _balloonStatsEntry.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _balloonStatsEntry.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _balloonStatsEntry.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _balloonStatsEntry.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _balloonStatsEntry.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _balloonStatsEntry.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _balloonStatsEntry.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _balloonStatsEntry.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _balloonStatsEntry.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _balloonStatsEntry.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _balloonStatsEntry.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new BalloonStatsEntryWrapper((BalloonStatsEntry) _balloonStatsEntry.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.BalloonStatsEntry balloonStatsEntry) {
        return _balloonStatsEntry.compareTo(balloonStatsEntry);
    }

    @Override
    public int hashCode() {
        return _balloonStatsEntry.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.BalloonStatsEntry> toCacheModel() {
        return _balloonStatsEntry.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.BalloonStatsEntry toEscapedModel() {
        return new BalloonStatsEntryWrapper(_balloonStatsEntry.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.BalloonStatsEntry toUnescapedModel() {
        return new BalloonStatsEntryWrapper(_balloonStatsEntry.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _balloonStatsEntry.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _balloonStatsEntry.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _balloonStatsEntry.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BalloonStatsEntryWrapper)) {
            return false;
        }

        BalloonStatsEntryWrapper balloonStatsEntryWrapper = (BalloonStatsEntryWrapper) obj;

        if (Validator.equals(_balloonStatsEntry,
                    balloonStatsEntryWrapper._balloonStatsEntry)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public BalloonStatsEntry getWrappedBalloonStatsEntry() {
        return _balloonStatsEntry;
    }

    @Override
    public BalloonStatsEntry getWrappedModel() {
        return _balloonStatsEntry;
    }

    @Override
    public void resetOriginalValues() {
        _balloonStatsEntry.resetOriginalValues();
    }
}
