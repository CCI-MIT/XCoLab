package org.xcolab.portlets.models.jsintegration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import net.sf.json.JSONObject;

public class JSIntegrationBean implements JSEventManager {

    private String eventOutput;
    private String eventInput;
    private Map<String, List<JSEventHandler>> handlers = new HashMap<String, List<JSEventHandler>>();
    private long lastEventTimestamp;

    public JSIntegrationBean() {
    }

    public void processEvent(ActionEvent _e) {

        JSONObject jsonObject = JSONObject.fromObject(eventInput);
        JSEvent event = (JSEvent) JSONObject.toBean(jsonObject, JSEvent.class);

        if (event.getTimestamp() > lastEventTimestamp) {
            String eventId = event.getId();
            if (handlers.containsKey(eventId)) {
                for (JSEventHandler handler: handlers.get(eventId)) {
                    handler.onJsEvent(event);
                }
            }
            lastEventTimestamp = event.getTimestamp();
        }
        eventInput = "";
    }

    public void sendEvent(JSEvent event) {
        eventOutput = JSONObject.fromObject(event).toString();
    }

    public String getEventOutput() {
        return eventOutput;
    }

    public void setEventOutput(String dataOutput) {
        this.eventOutput = dataOutput;
    }

    public String getEventInput() {
        return eventInput;
    }

    public void setEventInput(String dataInput) {
        this.eventInput = dataInput;
    }

    @Override
    public void addJsEventHandler(JSEventHandler handler, String eventName) {
        if (!handlers.containsKey(eventName)) {
            handlers.put(eventName, new ArrayList<JSEventHandler>());
        }
        handlers.get(eventName).add(handler);
    }

    @Override
    public void sendEvent(JSEvent event, boolean forcepush) {
        // TODO Auto-generated method stub

    }

}