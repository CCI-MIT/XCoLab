package org.xcolab.portlets.admintasks;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Helper {

    public static void sendMessage(Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(severity, message, message));
    }
    
    public static void sendError(String message) {
        sendMessage(FacesMessage.SEVERITY_ERROR, message);
    }
    
    public static void sendMessage(String message) {
        sendMessage(FacesMessage.SEVERITY_INFO, message);
    }
}
