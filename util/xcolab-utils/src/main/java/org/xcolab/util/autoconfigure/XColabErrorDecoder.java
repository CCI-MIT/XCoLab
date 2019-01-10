package org.xcolab.util.autoconfigure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.util.Date;

public class XColabErrorDecoder implements ErrorDecoder {

    private static final Logger _log = LoggerFactory.getLogger(XColabErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.body() != null) {
                ObjectMapper mapper = new ObjectMapper();
                String s = getStringFromInputStream(response.body().asInputStream());
                if (s.contains("\"exception\":")) {
                    MessageBody body = mapper.readValue(s, MessageBody.class);
                    return body.toException();
                }
            }
        } catch (Exception e) {
            _log.error("Error while decoding error message.", e);
        }

        return new Default().decode(methodKey, response);
    }

    private static String getStringFromInputStream(InputStream is) {
        String s = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            s = sb.toString();
            s = s.replace("\"{", "{");
            s = s.replace("}\"", "}");
            s = s.replace("\\", "");
        } catch (IOException e) {
            _log.error("Error reading Response.body()", e);
        }
        return s;
    }

    /*
    This JSON object is contained by Response#body():

    {
        "timestamp":2018-12-28T17:01:11.856+0000,
        "status":500,
        "error":"Internal Server Error",
        "message":"{
            \"exception\":\"org.xcolab.client.tracking.exceptions
            .BalloonUserTrackingNotFoundException\",
            \"message\":\"BalloonUserTracking f5f33575-47ac-440e-8d1c-c3498065dd20 does not exist\"
        },
        "path":"/balloonUserTrackings/f5f33575-47ac-440e-8d1c-c3498065dd20"
    }
    */

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Message {

        private String exception;
        private String message;

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
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class MessageBody {

        private Date timestamp;
        private int status;
        private String error;
        private Message message;
        private String path;

        public Exception toException() throws Exception {
            Class<?> clazz = Class.forName(message.exception);
            Constructor<?> constructor = clazz.getConstructor(String.class);
            Object instance = constructor.newInstance(message.message);
            return (Exception) instance;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
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

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
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
