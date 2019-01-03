package org.xcolab.util.http.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class EntityNotFoundException extends Exception {

//    private static final String JSON_FORMAT = "{ 'exception': '%s', 'message': '%s' }";
    private static final String JSON_FORMAT = "{ \"exception\": \"%s\", \"message\": \"%s\" }";

    public EntityNotFoundException(String message) {
        this(message, EntityNotFoundException.class);
    }

    public EntityNotFoundException(String message, Class<? extends EntityNotFoundException> exceptionType) {
        super(String.format(JSON_FORMAT, exceptionType.getCanonicalName(), message));
//        super(createJSON(exceptionType.getCanonicalName(), message));
    }

    private static String createJSON(String exception, String message) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonMessage = mapper.createObjectNode();
        jsonMessage.put("exception", exception);
        jsonMessage.put("message", message);
        return jsonMessage.toString();
    }
}
