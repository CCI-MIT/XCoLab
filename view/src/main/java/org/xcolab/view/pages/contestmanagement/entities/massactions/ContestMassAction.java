package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface ContestMassAction {
    
    void execute(List<Contest> contests, boolean actionConfirmed, MassActionDataWrapper dataWrapper,
            HttpServletResponse response)
            throws MassActionRequiresConfirmationException, IllegalStateException, IOException;

    String getDisplayName();
}
