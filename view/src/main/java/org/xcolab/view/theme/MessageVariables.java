package org.xcolab.view.theme;

import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.view.util.entity.flash.AnalyticsAttribute;
import org.xcolab.view.util.entity.flash.ErrorMessage;
import org.xcolab.view.util.entity.flash.InfoMessage;

import javax.servlet.http.HttpServletRequest;

public class MessageVariables {
    final private AlertMessage _alertMessage;
    final private AnalyticsAttribute _analyticsAttribute;
    final  private ErrorMessage _errorMessage;
    final private InfoMessage _infoMessage;

    public MessageVariables(HttpServletRequest request) {
        this._alertMessage = AlertMessage.extract(request);
        this._analyticsAttribute = AnalyticsAttribute.extract(request);
        this._errorMessage = ErrorMessage.extract(request);
        this._infoMessage = InfoMessage.extract(request);
    }

    public AlertMessage get_alertMessage() {
        return _alertMessage;
    }

    public AnalyticsAttribute get_analyticsAttribute() {
        return _analyticsAttribute;
    }

    public ErrorMessage get_errorMessage() {
        return _errorMessage;
    }

    public InfoMessage get_infoMessage() {
        return _infoMessage;
    }
}
