package org.xcolab.utils;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public final class UrlBuilder {
	private static final String CONTEST_TAB_URL_PATTERN = "/web/guest/cms/-/cms/contestId/%d/tab/%s";

	private UrlBuilder() { }

	public static String getProposalCommentsUrl(Contest contest, Proposal proposal) throws SystemException, PortalException {
		return ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposal) + "/tab/COMMENTS";
	}

	public static String getContestCreationTabCommentsUrl(long contestId, String tabName) {
		return String.format(CONTEST_TAB_URL_PATTERN, contestId, tabName);
	}
}
