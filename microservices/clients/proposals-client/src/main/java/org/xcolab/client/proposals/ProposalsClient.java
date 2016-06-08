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

    public static Proposal getProposal(Long proposalId) throws ProposalNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/proposals/" + proposalId + "");
        try {
            return RequestUtils.get(uriBuilder, Proposal.class, "proposalId_" + proposalId);
        } catch (EntityNotFoundException e) {
            throw new ProposalNotFoundException("Proposal with id " + proposalId + " not found.");
        }
    }

}
