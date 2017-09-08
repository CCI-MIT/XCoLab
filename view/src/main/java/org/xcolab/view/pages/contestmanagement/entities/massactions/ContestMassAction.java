package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ContestMassAction {

    private final String displayName;
    private final String reverseDisplayName;

    public ContestMassAction(String displayName, String reverseDisplayName) {
        this.displayName = displayName;
        this.reverseDisplayName = reverseDisplayName;
    }

    public ContestMassAction(String displayName) {
        this(displayName, null);
    }

    abstract void execute(HttpServletRequest request, HttpServletResponse response,
            ContestOverviewWrapper contestOverviewWrapper)
            throws IOException, MassActionRequiresConfirmationException;

    public String getDisplayName() {
        return displayName;
    }

    public String getReverseDisplayName() {
        return reverseDisplayName;
    }

    public boolean hasReverseAction() {
        return reverseDisplayName != null;
    }
}
