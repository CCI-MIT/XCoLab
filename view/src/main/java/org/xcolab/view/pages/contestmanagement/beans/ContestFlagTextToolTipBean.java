package org.xcolab.view.pages.contestmanagement.beans;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.commons.html.LabelValue;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ContestFlagTextToolTipBean implements Serializable {

    private static final List<LabelValue> FLAG_OPTIONS = Arrays.asList(
            new LabelValue(-1L, "Hide flag"),
            new LabelValue(0L, "Phase default"),
            new LabelValue(1L, "Green theme"),
            new LabelValue(2L, "Grey theme"));

    private int flagNumber;
    private String flagText;
    private String flagTooltip;

    public ContestFlagTextToolTipBean() {
        this.flagNumber = 0;
        this.flagText = "";
        this.flagTooltip = "";
    }

    public ContestFlagTextToolTipBean(int flagNumber, String flagText) {
        this.flagNumber = flagNumber;
        this.flagText = flagText;
        this.flagTooltip = "";
    }

    public ContestFlagTextToolTipBean(int flagNumber, String flagText, String flagTooltip) {
        this.flagNumber = flagNumber;
        this.flagText = flagText;
        this.flagTooltip = flagTooltip;
    }

    public ContestFlagTextToolTipBean(ContestWrapper contest) {
        this.flagNumber = contest.getFlag();
        this.flagText = contest.getFlagText();
    }

    public static List<LabelValue> getFlagOptions() {
        return FLAG_OPTIONS;
    }

    public void persist(ContestWrapper contest) {
        contest.setFlag(flagNumber);
        contest.setFlagText(flagText);
        contest.setFlagTooltip(flagTooltip);
        StaticContestContext.getContestClient().updateContest(contest);
    }

    public int getFlagNumber() {
        return flagNumber;
    }

    public void setFlagNumber(int flagNumber) {
        this.flagNumber = flagNumber;
    }

    public String getFlagText() {
        return flagText;
    }

    public void setFlagText(String flagText) {
        this.flagText = flagText;
    }

    public String getFlagTooltip() {
        return flagTooltip;
    }

    public void setFlagTooltip(String flagTooltip) {
        this.flagTooltip = flagTooltip;
    }
}
