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

    public static Proposal getProposal(long proposalId, boolean includeDeleted) throws ProposalNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/proposals/" + proposalId)
                    .queryParam("includeDeleted", includeDeleted);
        try {
            return RequestUtils.get(uriBuilder, Proposal.class,
                    "proposalId_" + proposalId + "_includeDeleted_" + includeDeleted);
        } catch (EntityNotFoundException e) {
            throw new ProposalNotFoundException(proposalId);
        }
    }

    public static boolean updateProposal(Proposal proposal) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/proposals/" + proposal.getProposalId());
        return RequestUtils.put(uriBuilder, proposal);
    }

    public static boolean deleteProposal(long proposalId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/proposals/" + proposalId);
        return RequestUtils.delete(uriBuilder);
    }

}
