package org.xcolab.portlets.proposals.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalTabWrapper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import javax.portlet.RenderRequest;

public class BaseProposalTabController {


    @Autowired
    private ProposalsContext proposalsContext;
    
    @ModelAttribute
    public void getTabs(Model model, RenderRequest request) throws PortalException, SystemException {
        // populate available tabs
        model.addAttribute("tabs", ProposalTabWrapper.createAll(proposalsContext.getPermissions(request)));
    }

}
