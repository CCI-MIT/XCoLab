package org.xcolab.portlets.admintasks.migration;

import com.icesoft.faces.async.render.SessionRenderer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 9/24/13
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataIntegrityChecker implements Runnable {

    List<String> reference;

    public DataIntegrityChecker(List<String> reference){
        this.reference = reference;

    }

    public void run() {
        pushAjaxUpdate("Starting to check DB integrity");
        pushAjaxUpdate("Finished to check DB integrity");
    }

    private void pushAjaxUpdate(String message){
        reference.add(message);
        SessionRenderer.render("migration");
    }

    private void updateLastAjaxUpdate(String message){
        reference.remove(reference.size()-1);
        reference.add((message));
        SessionRenderer.render("migration");
    }
}
