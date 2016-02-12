package org.xcolab.portlets.contestmanagement.beans;

import com.ext.portlet.model.Contest;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * Created by steve on 12/02/16.
 */
public class ContestAdminBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String emailTemplateUrl;
    private ContestModelSettingsBean contestModelSettings;

    private boolean hideRibbons;

    @NotNull(message = "A contest tier must be selected.")
    private Long contestTier;

    @NotNull(message = "A contest type must be selected.")
    private Long contestType;

    public ContestAdminBean() { }

    public ContestAdminBean(Contest contest) {
        if (contest != null) {
            contestTier = contest.getContestTier();
            contestType = contest.getContestTypeId();
            hideRibbons = contest.getHideRibbons();
            emailTemplateUrl = contest.getEmailTemplateUrl();
            contestModelSettings = new ContestModelSettingsBean(contest);
        }
    }

    public void persist(Contest contest) throws SystemException, UnsupportedEncodingException, PortalException {
        updateContestDescription(contest);
    }

    public String getEmailTemplateUrl() {
        if (emailTemplateUrl != null) {
            return emailTemplateUrl;
        } else {
            return "";
        }
    }

    public void setEmailTemplateUrl(String emailTemplateUrl) {
        this.emailTemplateUrl = emailTemplateUrl;
    }

    public Long getContestTier() {
        return contestTier;
    }

    public void setContestTier(Long contestTier) {
        this.contestTier = contestTier;
    }

    public Long getContestType() { return contestType; }

    public void setContestType(Long contestType) {
        this.contestType = contestType;
    }

    public ContestModelSettingsBean getContestModelSettings() {
        return contestModelSettings;
    }

    public void setContestModelSettings(ContestModelSettingsBean contestModelSettings) {
        this.contestModelSettings = contestModelSettings;
    }

    public boolean isHideRibbons() {
        return hideRibbons;
    }

    public void setHideRibbons(boolean hideRibbons) {
        this.hideRibbons = hideRibbons;
    }

    private void updateContestDescription(Contest contest) throws SystemException, PortalException {
        contest.setEmailTemplateUrl(emailTemplateUrl);
        contest.setContestTier(contestTier);
        contest.setContestTypeId(contestType);
        contest.setHideRibbons(hideRibbons);
        contest.persist();
        contestModelSettings.persist(contest);
    }

}
