package org.xcolab.view.theme;

import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.view.util.entity.flash.AnalyticsAttribute;
import org.xcolab.view.util.entity.flash.ErrorMessage;
import org.xcolab.view.util.entity.flash.InfoMessage;

import javax.servlet.http.HttpServletRequest;

public class MessageVariables {
    final private AlertMessage alertMessage;
    final private AnalyticsAttribute analyticsAttribute;
    final  private ErrorMessage errorMessage;
    final private InfoMessage infoMessage;

    public MessageVariables(HttpServletRequest request) {
        this.alertMessage = AlertMessage.extract(request);
        this.analyticsAttribute = AnalyticsAttribute.extract(request);
        this.errorMessage = ErrorMessage.extract(request);
        this.infoMessage = InfoMessage.extract(request);
    }

    public AlertMessage getAlertMessage() {
        return alertMessage;
    }

    public AnalyticsAttribute getAnalyticsAttribute() {
        return analyticsAttribute;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public InfoMessage getInfoMessage() {
        return infoMessage;
    }
}
