package org.xcolab.proposals.events;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.liferay.portal.model.User;

public class ProposalAttributeRemovedEvent extends ProposalAttributeUpdatedEvent {

    public ProposalAttributeRemovedEvent(Proposal proposal, User user, String attributeName, ProposalAttribute oldAttribute) {
        super(proposal, user, attributeName, oldAttribute, null);
    }

}
