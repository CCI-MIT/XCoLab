package org.xcolab.client.proposals;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class ProposalsClient {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final RestResource proposalResource = new RestResource(proposalService,
            "proposals");

    public static Proposal createProposal(Proposal proposal) {
        final UriBuilder uriBuilder = proposalResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, proposal, Proposal.class);
    }

    public static List<Proposal> listProposals(long contestId) {
        return listProposals(0, Integer.MAX_VALUE, contestId, null, null, null);
    }

    public static List<Proposal> listProposals(int start, int limit, Long contestId,
            Boolean visible, Long contestPhaseId, Integer ribbon) {
        final UriBuilder uriBuilder = proposalResource.getResourceUrl()
                .addRange(start, limit)
                .optionalQueryParam("contestId", contestId)
                .optionalQueryParam("visible", visible)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("ribbon", ribbon);

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Proposal>>() {
        });
    }

    public static Proposal getProposal(long proposalId) throws ProposalNotFoundException {
        return getProposal(proposalId, false);
    }

    public static Proposal getProposal(long proposalId, boolean includeDeleted) throws ProposalNotFoundException {
        final UriBuilder uriBuilder = proposalResource.getResourceUrl(proposalId)
                    .queryParam("includeDeleted", includeDeleted);
        try {
            return RequestUtils.get(uriBuilder, Proposal.class,
                    "proposalId_" + proposalId + "_includeDeleted_" + includeDeleted);
        } catch (EntityNotFoundException e) {
            throw new ProposalNotFoundException(proposalId);
        }
    }

    public static boolean updateProposal(Proposal proposal) {
        final UriBuilder uriBuilder = proposalResource.getResourceUrl(proposal.getProposalId());
        return RequestUtils.put(uriBuilder, proposal);
    }

    public static boolean deleteProposal(long proposalId) {
        final UriBuilder uriBuilder = proposalResource.getResourceUrl(proposalId);
        return RequestUtils.delete(uriBuilder);
    }
}
