package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface ContestMassAction {

    void setup(MassActionDataWrapper dataWrapper);

    void execute(List<Contest> contests, boolean actionConfirmed)
            throws MassActionRequiresConfirmationException, IllegalStateException;

    void generateResponse(HttpServletResponse response) throws IOException, IllegalStateException;

    String getDisplayName();
}
