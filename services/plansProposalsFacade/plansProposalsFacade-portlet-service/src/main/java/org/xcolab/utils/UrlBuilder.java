package org.xcolab.utils;

public class UrlBuilder {
	private static String PROPOSAL_URL_PATTERN = "/web/guest/plans/-/plans/contestId/%d/planId/%d%s";
	
	public static String getProposalUrl(long contestId, long proposalId) {
		return String.format(PROPOSAL_URL_PATTERN, contestId, proposalId, "");
	}
	
	public static String getProposalCommentsUrl(long contestId, long proposalId) {
		return String.format(PROPOSAL_URL_PATTERN, contestId, proposalId, "/tab/COMMENTS");
	}
}
