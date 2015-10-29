package org.xcolab.utils;

import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class UrlBuilder {
	private static final String CONTEST_TAB_URL_PATTERN = "/web/guest/cms/-/cms/contestId/%d/tab/%s";
	
	public static String getProposalCommentsUrl(long proposalId) throws SystemException, PortalException {
		return ProposalLocalServiceUtil.getProposalLinkUrl(proposalId) + "/tab/COMMENTS";
	}

	public static String getContestCreationTabCommentsUrl(long contestId, String tabName) {
		return String.format(CONTEST_TAB_URL_PATTERN, contestId, tabName);
	}
}
