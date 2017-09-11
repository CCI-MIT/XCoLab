package org.xcolab.view.pages.contestmanagement.entities.massactions;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public abstract class ContestMassActionAdapter implements ContestMassAction {

    private final String displayName;

    public ContestMassActionAdapter(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public void setup(MassActionDataWrapper dataWrapper) {

    }

    @Override
    public void generateResponse(HttpServletResponse response)
            throws IOException, IllegalStateException {

    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
