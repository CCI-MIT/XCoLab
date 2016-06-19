package org.xcolab.client.proposals;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

import java.util.List;

public final class ProposalsClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:" + RequestUtils.getServicesPort() + "/proposals-service";

    public static Proposal createProposal(Proposal proposal) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/proposals");
        return RequestUtils.post(uriBuilder, proposal, Proposal.class);
    }

    public static List<Proposal> listProposals(int start, int limit, Long contestId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/proposals")
                .queryParam("startRecord", start)
                .queryParam("limitRecord", limit);
        if (contestId != null) {
            uriBuilder.queryParam("contestId", contestId);
        }
        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Proposal>>() {
        });
    }

    public static Proposal getProposal(long proposalId) throws ProposalNotFoundException {
        return getProposal(proposalId, false);
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
