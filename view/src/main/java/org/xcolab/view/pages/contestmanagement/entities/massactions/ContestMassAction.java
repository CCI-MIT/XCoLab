package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ContestMassAction {

    private String displayName;
    private String reverseDisplayName;

    abstract void execute(HttpServletRequest request, HttpServletResponse response,
            ContestOverviewWrapper contestOverviewWrapper);

    public String getDisplayName() {
        return displayName;
    }

    public String getReverseDisplayName() {
        return reverseDisplayName;
    }
}
