package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.BalloonStatsEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.BalloonStatsEntryServiceSoap
 * @generated
 */
public class BalloonStatsEntrySoap implements Serializable {
    private long _id;
    private long _firstContestId;
    private long _secondContestId;
    private int _choosenContest;
    private String _cookie;
    private String _ip;
    private String _extraData;

    public BalloonStatsEntrySoap() {
    }

    public static BalloonStatsEntrySoap toSoapModel(BalloonStatsEntry model) {
        BalloonStatsEntrySoap soapModel = new BalloonStatsEntrySoap();

        soapModel.setId(model.getId());
        soapModel.setFirstContestId(model.getFirstContestId());
        soapModel.setSecondContestId(model.getSecondContestId());
        soapModel.setChoosenContest(model.getChoosenContest());
        soapModel.setCookie(model.getCookie());
        soapModel.setIp(model.getIp());
        soapModel.setExtraData(model.getExtraData());

        return soapModel;
    }

    public static BalloonStatsEntrySoap[] toSoapModels(
        BalloonStatsEntry[] models) {
        BalloonStatsEntrySoap[] soapModels = new BalloonStatsEntrySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static BalloonStatsEntrySoap[][] toSoapModels(
        BalloonStatsEntry[][] models) {
        BalloonStatsEntrySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new BalloonStatsEntrySoap[models.length][models[0].length];
        } else {
            soapModels = new BalloonStatsEntrySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static BalloonStatsEntrySoap[] toSoapModels(
        List<BalloonStatsEntry> models) {
        List<BalloonStatsEntrySoap> soapModels = new ArrayList<BalloonStatsEntrySoap>(models.size());

        for (BalloonStatsEntry model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new BalloonStatsEntrySoap[soapModels.size()]);
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

    public long getFirstContestId() {
        return _firstContestId;
    }

    public void setFirstContestId(long firstContestId) {
        _firstContestId = firstContestId;
    }

    public long getSecondContestId() {
        return _secondContestId;
    }

    public void setSecondContestId(long secondContestId) {
        _secondContestId = secondContestId;
    }

    public int getChoosenContest() {
        return _choosenContest;
    }

    public void setChoosenContest(int choosenContest) {
        _choosenContest = choosenContest;
    }

    public String getCookie() {
        return _cookie;
    }

    public void setCookie(String cookie) {
        _cookie = cookie;
    }

    public String getIp() {
        return _ip;
    }

    public void setIp(String ip) {
        _ip = ip;
    }

    public String getExtraData() {
        return _extraData;
    }

    public void setExtraData(String extraData) {
        _extraData = extraData;
    }
}
