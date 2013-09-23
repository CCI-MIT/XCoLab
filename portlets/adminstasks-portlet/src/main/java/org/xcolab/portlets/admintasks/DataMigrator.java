package org.xcolab.portlets.admintasks;

import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.icesoft.faces.async.render.SessionRenderer;

import java.util.Iterator;
import java.util.List;
import com.ext.portlet.model.Proposal;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 9/17/13
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataMigrator implements Runnable {
    List<String> reference;

    public DataMigrator(List<String> reference){
        this.reference = reference;

    }

    public void run() {
        // code in the other thread, can reference "var" variable

        deleteAllProposals();
    }

    private boolean deleteAllProposals(){
        pushAjaxUpdate("Cleaning DB");
        int numberOfProposals = 0;
        try{
            numberOfProposals = ProposalLocalServiceUtil.getProposalsCount();
            pushAjaxUpdate("Deleting " + numberOfProposals + " Proposal(s)");
            List<Proposal> proposals = ProposalLocalServiceUtil.getProposals(0,numberOfProposals);
            for(Iterator<Proposal> i = proposals.iterator(); i.hasNext(); ) {
                Proposal proposal = i.next();
                ProposalLocalServiceUtil.deleteProposal(proposal.getProposalId());
            }
            numberOfProposals = ProposalLocalServiceUtil.getProposalsCount();
            if (numberOfProposals != 0) return false;
        } catch (Exception e){
            pushAjaxUpdate("Error while cleaning DB: " + e);
            return false;
        }
        pushAjaxUpdate("Successfully cleaned DB");
        return true;
    }


    private void pushAjaxUpdate(String message){
        reference.add(message);
        SessionRenderer.render("migration");
    }
}
