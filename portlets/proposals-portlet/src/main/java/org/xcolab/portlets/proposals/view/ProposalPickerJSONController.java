package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.proposals.utils.ProposalPickerFilter;
import org.xcolab.portlets.proposals.utils.ProposalPickerFilterUtil;
import org.xcolab.portlets.proposals.utils.ProposalPickerSortingUtil;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * User: patrickhiesel Date: 03/12/13 Time: 09:46
 */

@Controller
@RequestMapping("view")
public class ProposalPickerJSONController {

	private static final int MAX_CHARS_FOR_NAMES = 75;
	private static final Log _log = LogFactoryUtil.getLog(ProposalPickerJSONController.class);

	@Autowired
	private ProposalsContext proposalsContext;

	@ResourceMapping("proposalPicker")
	public void proposalPicker(
			ResourceRequest request,
			ResourceResponse response,
			@RequestParam(value = "type", required = false) String requestType,
			@RequestParam(value = "filterKey", required = false) String filterType,
			@RequestParam(required = false) String filterText,
			@RequestParam(required = false) int start,
			@RequestParam(required = false) int end,
			@RequestParam(required = false) String sortOrder,
			@RequestParam(required = false) String sortColumn,
			@RequestParam(required = false) Long sectionId,
			@RequestParam(required = false) long contestPK) throws IOException,
			SystemException, PortalException {

		List<Pair<Proposal, Date>> proposals;
		final long userId = Long.parseLong(request.getRemoteUser());

		switch (requestType.toUpperCase()) {
			case "SUBSCRIPTIONSANDSUPPORTING":
				proposals = ProposalPickerFilterUtil.getFilteredSubscribedSupportingProposalsForUser(
						userId, filterType, sectionId, request, proposalsContext);
				break;
			case "SUBSCRIPTIONS":
				proposals = ProposalPickerFilterUtil.getFilteredSubscribedProposalsForUser(
						userId, filterType, sectionId, request, proposalsContext);
				break;
			case "SUPPORTING":
				proposals = ProposalPickerFilterUtil.getFilteredSupportingProposalsForUser(
						userId, filterType, sectionId, request, proposalsContext);
				break;
			case "ALL":
			case "CONTESTS":
				proposals = ProposalPickerFilterUtil.getFilteredAllProposals(filterType,
						sectionId, contestPK, request, proposalsContext);
				break;
			default:
				_log.error("Proposal picker was loaded with unknown requestType " + requestType);
				throw new PortalException("Unknown requestType " + requestType);
		}
		if (filterText != null && !filterText.isEmpty()) {
			ProposalPickerFilter.TEXT_BASED.filter(proposals, filterText);
		}
		final int totalCount = proposals.size();

		ProposalPickerSortingUtil.sortProposalsList(sortOrder, sortColumn, proposals);
		if (end >= totalCount && totalCount > 0) {
			end = totalCount;
		}
		if (totalCount > (end - start)) {
			proposals = proposals.subList(start, end);
		}
		response.getPortletOutputStream().write(
				getJSONObjectMapping(proposals, totalCount).getBytes());
	}

	@ResourceMapping("proposalPickerContests")
	public void proposalPickerContests(
			ResourceRequest request,
			ResourceResponse response,
			@RequestParam(value = "type", required = false) String requestType,
			@RequestParam(value = "filterKey", required = false) String filterType,
			@RequestParam(required = false) String filterText,
			@RequestParam(required = false) int start,
			@RequestParam(required = false) int end,
			@RequestParam(required = false) String sortOrder,
			@RequestParam(required = false, value = "contestSortColumn") String sortColumn,
			@RequestParam(required = false) Long sectionId) throws IOException,
			SystemException, PortalException {

		List<Pair<ContestWrapper, Date>> contests = ProposalPickerFilterUtil.getFilteredContests(
				sectionId, request, proposalsContext);

		if (filterText != null && !filterText.isEmpty()) {
			ProposalPickerFilter.TEXT_BASED.filterContests(contests,
					filterText);
		}
		int totalCount = contests.size();

		if (end >= contests.size() && !contests.isEmpty()) {
			end = contests.size();
		}
		ProposalPickerSortingUtil.sortContestsList(sortOrder, sortColumn, contests);
		if (contests.size() > (end - start)) {
			contests = contests.subList(start, end);
		}

		response.getPortletOutputStream().write(
				getJSONObjectMappingContests(contests, totalCount).getBytes());
	}

	/**
	 * This method is used to fill the counting bubbles for each tab
	 */
	@ResourceMapping("proposalPickerCounter")
	public void proposalPickerCounter(ResourceRequest request,
									  ResourceResponse response) throws IOException, SystemException,
			PortalException {
		String filterType = request.getParameter("filterKey");
		long sectionId = Long.parseLong(request.getParameter("sectionId"));
		long userId = Long.parseLong(request.getRemoteUser());

		int numberOfSubscriptions = ProposalPickerFilterUtil.getFilteredSubscribedProposalsForUser(
				userId, filterType, sectionId, request, proposalsContext).size();
		int numberOfSupporting = ProposalPickerFilterUtil.getFilteredSupportingProposalsForUser(userId,
				filterType, sectionId, request, proposalsContext).size();
		int numberOfProposals = ProposalPickerFilterUtil.getFilteredAllProposals(filterType,
				sectionId, 0L, request, proposalsContext).size();
		int numberOfSubscriptionsSupporting = ProposalPickerFilterUtil.getFilteredSubscribedSupportingProposalsForUser(
				userId, filterType, sectionId, request, proposalsContext).size();
		int numberOfContests = ProposalPickerFilterUtil.getFilteredContests(sectionId, request, proposalsContext).size();

		JSONObject wrapper = JSONFactoryUtil.createJSONObject();
		wrapper.put("numberOfSubscriptions", numberOfSubscriptions);
		wrapper.put("numberOfSupporting", numberOfSupporting);
		wrapper.put("numberOfProposals", numberOfProposals);
		wrapper.put("numberOfSubscriptionsSupporting",
				numberOfSubscriptionsSupporting);
		wrapper.put("numberOfContests", numberOfContests);
		response.getPortletOutputStream().write(wrapper.toString().getBytes());
	}

	private String getJSONObjectMapping(List<Pair<Proposal, Date>> proposals,
										int totalNumberOfProposals) throws SystemException, PortalException {
		JSONObject wrapper = JSONFactoryUtil.createJSONObject();
		JSONArray proposalsJSON = JSONFactoryUtil.createJSONArray();

		for (Pair<Proposal, Date> p : proposals) {
			ProposalWrapper wrappedProposal = new ProposalWrapper(p.getLeft());
			JSONObject o = JSONFactoryUtil.createJSONObject();
			o.put("id", p.getLeft().getProposalId());
			o.put("proposalName", StringUtils.abbreviate(
					StringEscapeUtils.unescapeXml(wrappedProposal.getName()), MAX_CHARS_FOR_NAMES));
			o.put("contestName", StringUtils.abbreviate(wrappedProposal
					.getContest().getContestShortName(), MAX_CHARS_FOR_NAMES));
			o.put("linkUrl", ProposalLocalServiceUtil.getProposalLinkUrl(wrappedProposal.getContest(), wrappedProposal.getWrapped()));
			o.put("contestId", wrappedProposal.getContest().getContestPK());
			if (StringUtils.isNotBlank(wrappedProposal.getTeam())) {
				o.put("team", wrappedProposal.getTeam());
			}
			o.put("authorName", wrappedProposal.getAuthorName());
			o.put("authorId", wrappedProposal.getAuthorId());
			o.put("dateSubscribed", p.getRight().getTime());
			o.put("commentsCount", wrappedProposal.getCommentsCount());
			o.put("supportersCount", wrappedProposal.getSupportersCount());
			o.put("pitch", wrappedProposal.getPitch());
			o.put("ribbon", wrappedProposal.getRibbonWrapper().getRibbon());
			o.put("ribbonText", wrappedProposal.getRibbonWrapper().getRibbonText());
			o.put("featured", wrappedProposal.isFeatured());

			JSONArray proposalContests = JSONFactoryUtil.createJSONArray();
			// for now there can be only one contest
			JSONObject contest = JSONFactoryUtil.createJSONObject();
			contest.put("name", wrappedProposal.getContest()
					.getContestShortName());
			contest.put("id", wrappedProposal.getContest().getContestPK());
			contest.put("contestLogoId", wrappedProposal.getContest()
					.getContestLogoId());
			proposalContests.put(contest);

			o.put("contests", proposalContests);
			proposalsJSON.put(o);
		}

		wrapper.put("proposals", proposalsJSON);
		wrapper.put("totalCount", totalNumberOfProposals);
		return wrapper.toString();
	}

	private String getJSONObjectMappingContests(
			List<Pair<ContestWrapper, Date>> contests, int totalNumberOfContests)
			throws SystemException, PortalException {
		JSONObject wrapper = JSONFactoryUtil.createJSONObject();
		JSONArray proposalsJSON = JSONFactoryUtil.createJSONArray();

		for (Pair<ContestWrapper, Date> p : contests) {
			ContestWrapper wrapped = p.getLeft();
			JSONObject o = JSONFactoryUtil.createJSONObject();

			o.put("id", p.getLeft().getContestPK());
			o.put("contestShortName", StringUtils.abbreviate(
					wrapped.getContestShortName(), MAX_CHARS_FOR_NAMES));
			o.put("contestName", StringUtils.abbreviate(
					wrapped.getContestName(), MAX_CHARS_FOR_NAMES));
			o.put("contestPK", wrapped.getContestPK());
			o.put("flagText", wrapped.getFlagText());
			o.put("flag", wrapped.getFlag());
			o.put("flagTooltip", wrapped.getFlagTooltip());
			o.put("proposalsCount", wrapped.getProposalsCount());
			o.put("totalCommentsCount", wrapped.getTotalCommentsCount());
			o.put("what", wrapped.getWhatName());
			o.put("who", wrapped.getWhoName());
			o.put("where", wrapped.getWhereName());
			o.put("date", p.getRight().getTime());
			proposalsJSON.put(o);
		}

		wrapper.put("contests", proposalsJSON);
		wrapper.put("totalCount", totalNumberOfContests);
		return wrapper.toString();
	}
}
