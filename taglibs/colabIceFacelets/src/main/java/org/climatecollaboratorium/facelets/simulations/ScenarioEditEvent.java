package org.climatecollaboratorium.facelets.simulations;

import org.climatecollaboratorium.events.Event;

public class ScenarioEditEvent implements Event {
    private boolean editing;
    
    public ScenarioEditEvent(boolean editing) {
        this.editing = editing;
    }
    
    public boolean isEditing() {
        return editing;
    }

}
