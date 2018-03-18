package org.xcolab.util.http.exceptions.translation.facebook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookErrorResponseObject {

    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(
            Error error) {
        this.error = error;
    }

    public static class Error {
        private String message;
        private String type;
        private int code;
        private String fbtrace_id;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getFbtrace_id() {
            return fbtrace_id;
        }

        public void setFbtrace_id(String fbtrace_id) {
            this.fbtrace_id = fbtrace_id;
        }
    }
}
