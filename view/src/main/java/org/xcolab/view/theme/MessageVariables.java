package org.xcolab.view.theme;

import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.view.util.entity.flash.AnalyticsAttribute;
import org.xcolab.view.util.entity.flash.ErrorMessage;
import org.xcolab.view.util.entity.flash.InfoMessage;

import javax.servlet.http.HttpServletRequest;

public class MessageVariables {
    public AlertMessage _alertMessage;
    public AnalyticsAttribute _analyticsAttribute;
    public ErrorMessage _errorMessage;
    public InfoMessage _infoMessage;

    public MessageVariables(HttpServletRequest request) {
        this._alertMessage = AlertMessage.extract(request);
        this._analyticsAttribute = AnalyticsAttribute.extract(request);
        this._errorMessage = ErrorMessage.extract(request);
        this._infoMessage = InfoMessage.extract(request);
    }
}
