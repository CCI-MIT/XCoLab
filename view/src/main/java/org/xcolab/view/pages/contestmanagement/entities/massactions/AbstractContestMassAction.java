package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.view.pages.contestmanagement.entities.ContestMassAction;

public abstract class AbstractContestMassAction implements ContestMassAction {

    private final String displayName;

    public AbstractContestMassAction(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
