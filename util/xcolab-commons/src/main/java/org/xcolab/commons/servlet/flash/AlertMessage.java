package org.xcolab.commons.servlet.flash;

import org.xcolab.commons.servlet.flash.impl.FlashMessageStore;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class AlertMessage implements Serializable {

    private static final long serialVersionUID = 1234324324L;

    public static final AlertMessage CHANGES_SAVED = AlertMessage.success("Changes saved");
    public static final AlertMessage NOT_SAVED = AlertMessage.danger("Changes NOT saved");
    public static final AlertMessage CREATED = AlertMessage.success("Element created");
    public static final AlertMessage DELETED = AlertMessage.success("Element deleted");

    public static final AlertMessage ERROR = AlertMessage.danger("An error occurred");

    private static final FlashMessageStore MESSAGE_STORE = new FlashMessageStore();

    private final String message;
    private final Type type;

    private AlertMessage(String message, Type type) {
        super();
        this.message = message;
        this.type = type;
    }

    public static AlertMessage danger(String message) {
        return new AlertMessage(message, Type.DANGER);
    }

    public static AlertMessage warning(String message) {
        return new AlertMessage(message, Type.WARNING);
    }

    public static AlertMessage info(String message) {
        return new AlertMessage(message, Type.INFO);
    }

    public static AlertMessage success(String message) {
        return new AlertMessage(message, Type.SUCCESS);
    }

    public static AlertMessage extract(HttpServletRequest request) {
        return MESSAGE_STORE.pop(request, AlertMessage.class);
    }

    public void flash(HttpServletRequest request) {
        MESSAGE_STORE.put(request, this);
    }

    public String getMessage() {
        return message;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "AlertMessage [message=" + message + ", type=" + type + "]";
    }

    public enum Type {
        DANGER("error"),
        WARNING("warning"),
        INFO("information"),
        SUCCESS("success"),
        ALERT("alert");

        private final String notyType;

        Type(String notyType) {

            this.notyType = notyType;
        }

        public String getNotyType() {
            return notyType;
        }
    }
}
