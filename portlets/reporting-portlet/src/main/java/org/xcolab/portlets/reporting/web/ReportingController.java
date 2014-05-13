package org.xcolab.portlets.reporting.web;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.reporting.beans.UserActivityReportBean;

import au.com.bytecode.opencsv.CSVWriter;

import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

@RequestMapping("view")
@Controller
public class ReportingController {
	
	@RequestMapping
	public String showHomePage(RenderRequest request) {
		return "index";
	}
	
	@RequestMapping(params="report=userActivitiesReport")
	public void generateUserActivitiesReport(ResourceRequest request, ResourceResponse response) throws IOException, SystemException, PortalException {

		Map<Long, UserActivityReportBean> userActivities = new HashMap<>();
		
		for (User user: UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE)) {
			userActivities.put(user.getUserId(), new UserActivityReportBean(user));
		}
		for (SocialActivity activity: SocialActivityLocalServiceUtil.getSocialActivities(0, Integer.MAX_VALUE)) {
			if (! userActivities.containsKey(activity.getUserId())) continue;
			userActivities.get(activity.getUserId()).addActivity();
		}
		
		for (DiscussionMessage message: DiscussionMessageLocalServiceUtil.getDiscussionMessages(0, Integer.MAX_VALUE)) {
			if (! userActivities.containsKey(message.getAuthorId())) continue;
			userActivities.get(message.getAuthorId()).addComment();
		}
		Map<Long, Long> proposalToUser = new HashMap<>();
		
		for (Proposal proposal: ProposalLocalServiceUtil.getProposals(0, Integer.MAX_VALUE)) {
			if (! userActivities.containsKey(proposal.getAuthorId())) continue;
			
			userActivities.get(proposal.getAuthorId()).addProposal();
			proposalToUser.put(proposal.getProposalId(), proposal.getAuthorId());
			
		}
		
		Set<Long> winningRibbonTypes = new HashSet<>();
		for (ContestPhaseRibbonType cprt: ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonTypes(0, Integer.MAX_VALUE)) {
			if (cprt.getRibbon() == 1) winningRibbonTypes.add(cprt.getId());
		}
		
		Set<Long> finalistsContestPhases = new HashSet<>();
		for (ContestPhase cp: ContestPhaseLocalServiceUtil.getContestPhases(0, Integer.MAX_VALUE)) {
			if (ContestStatus.valueOf(ContestPhaseLocalServiceUtil.getContestStatusStr(cp)).isCanVote()) {
				finalistsContestPhases.add(cp.getContestPhasePK());
			}
		}
		
		
		
		for (ProposalContestPhaseAttribute pcpa: ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttributes(0, Integer.MAX_VALUE)) {
			UserActivityReportBean uarb = userActivities.get( proposalToUser.get(pcpa.getProposalId()) );
			if (uarb == null) continue;
			if (ProposalContestPhaseAttributeKeys.RIBBON.equals(pcpa.getName()) && winningRibbonTypes.contains(pcpa.getNumericValue())) {
				uarb.addProposalWinner();
			}
		}
		
		for (Proposal2Phase p2p: Proposal2PhaseLocalServiceUtil.getProposal2Phases(0, Integer.MAX_VALUE)) {
			UserActivityReportBean uarb = userActivities.get( proposalToUser.get(p2p.getProposalId()) );
			if (uarb == null) continue;
			if (finalistsContestPhases.contains(p2p.getContestPhaseId())) {
				uarb.addProposalFinalist();
			}
		}
		
		
		
		
		List<UserActivityReportBean> userActivityReportingBeans = new ArrayList<>();
		Collections.sort(userActivityReportingBeans);
		

		Writer w = response.getWriter();
		CSVWriter csvWriter = new CSVWriter(w);
		
		csvWriter.writeNext(new String[] {"userId", "screenName", "emailAddress", "fullName", "commentsCount", "proposalsCount", "proposalFinalistsCount", "proposalWinnersCount", "totalActivityCount"});
		for (UserActivityReportBean uarb: userActivities.values()) {
			User u = uarb.getUser();
			csvWriter.writeNext(new String[] {String.valueOf(u.getUserId()), u.getScreenName(),
					(u.getEmailAddresses().isEmpty() ? "" : u.getEmailAddresses().get(0).getAddress()), u.getFullName(),
					String.valueOf(uarb.getCommentsCount()), String.valueOf(uarb.getProposalsCount()), String.valueOf(uarb.getProposalFinalistsCount()), 
					String.valueOf(uarb.getProposalWinnersCount()), String.valueOf(uarb.getTotalActivityCount())});
		}
		
		
		
		

		response.setContentType("text/csv");
		response.addProperty("Content-Disposition", "attachment;filename=userActivitiesReport.csv");
		

		w.close();
	}

}
