package org.xcolab.client.proposals;


import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

public final class ProposalsClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:" + RequestUtils.getServicesPort() + "/proposals-service";

    public static Proposal createProposal(Proposal proposal) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/proposals");
        return RequestUtils.post(uriBuilder, proposal, Proposal.class);
    }

    public static void updateProposal(Proposal proposal) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/proposals/" + proposal.getProposalId());
        RequestUtils.put(uriBuilder, proposal);
    }

}
