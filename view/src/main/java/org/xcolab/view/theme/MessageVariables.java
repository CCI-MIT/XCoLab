package org.xcolab.view.theme;

import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.util.entity.flash.AnalyticsAttribute;
import org.xcolab.commons.servlet.flash.ErrorPage;
import org.xcolab.commons.servlet.flash.InfoPage;

import javax.servlet.http.HttpServletRequest;

public class MessageVariables {
    private final AlertMessage alertMessage;
    private final AnalyticsAttribute analyticsAttribute;
    private final ErrorPage errorPage;
    private final InfoPage infoPage;

    public MessageVariables(HttpServletRequest request) {
        this.alertMessage = AlertMessage.extract(request);
        this.analyticsAttribute = AnalyticsAttribute.extract(request);
        this.errorPage = ErrorPage.extract(request);
        this.infoPage = InfoPage.extract(request);
    }

    public AlertMessage getAlertMessage() {
        return alertMessage;
    }

    public AnalyticsAttribute getAnalyticsAttribute() {
        return analyticsAttribute;
    }

    public ErrorPage getErrorPage() {
        return errorPage;
    }

    public InfoPage getInfoPage() {
        return infoPage;
    }
}
