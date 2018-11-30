package org.xcolab.service.members.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.Reader;
import java.lang.reflect.Constructor;

public class XColabErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.body() != null) {
                ObjectMapper mapper = new ObjectMapper();
                Reader reader = response.body().asReader();
                MessageBody body = mapper.readValue(reader, MessageBody.class);
                return body.toException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Default().decode(methodKey, response);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class MessageBody {

        /*
        This JSON object is contained by Response#body():

        {
            "timestamp":1543531725279,
            "status":500,
            "error":"Internal Server Error",
            "exception":"org.xcolab.client.tracking.exceptions
            .BalloonUserTrackingNotFoundException",
            "message":"BalloonUserTracking f5f33575-47ac-440e-8d1c-c3498065dd20 does not exist",
            "path":"/balloonUserTrackings/f5f33575-47ac-440e-8d1c-c3498065dd20"
        }
        */

        private long timestamp;
        private int status;
        private String error;
        private String exception;
        private String message;
        private String path;

        public Exception toException() throws Exception {
            Class<?> clazz = Class.forName(exception);
            Constructor<?> constructor = clazz.getConstructor(String.class);
            Object instance = constructor.newInstance(message);
            return (Exception) instance;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getException() {
            return exception;
        }

        public void setException(String exception) {
            this.exception = exception;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
