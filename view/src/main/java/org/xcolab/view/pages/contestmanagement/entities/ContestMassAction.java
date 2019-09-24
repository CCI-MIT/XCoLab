package org.xcolab.view.pages.contestmanagement.entities;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.view.pages.contestmanagement.entities.massactions.MassActionDataWrapper;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface ContestMassAction {
    
    void execute(List<ContestWrapper> contests, boolean actionConfirmed, MassActionDataWrapper dataWrapper,
            HttpServletResponse response)
            throws MassActionRequiresConfirmationException, IllegalArgumentException, IOException;

    String getDisplayName();
}
