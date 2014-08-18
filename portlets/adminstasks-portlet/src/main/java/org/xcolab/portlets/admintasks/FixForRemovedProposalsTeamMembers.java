package org.xcolab.portlets.admintasks;

import java.io.IOException;
import java.io.InputStreamReader;

import au.com.bytecode.opencsv.CSVReader;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class FixForRemovedProposalsTeamMembers {
	
	private final static String removedMembersList =  "removedTeamMembers.csv";
	
	public String processRemovedMembers() throws IOException, PortalException, SystemException {
		CSVReader reader = new CSVReader(new InputStreamReader(FixForRemovedProposalsTeamMembers.class.getClassLoader().getResourceAsStream(removedMembersList)));
		
		String[] line = null;
		
		while ( ( line = reader.readNext()) != null) {
			Long proposalId = Long.parseLong(line[0]);
			Long memberId = Long.parseLong(line[1]);
			
			boolean isInActiveContest = false;
			
			for (Long contestPhasePK: Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(proposalId)) {
				ContestPhase cp = ContestPhaseLocalServiceUtil.getContestPhase(contestPhasePK);
				isInActiveContest |= ContestLocalServiceUtil.isActive(ContestLocalServiceUtil.getContest(cp.getContestPK()));
			}
			
			User user = UserLocalServiceUtil.getUser(memberId);
			
			if (! ProposalLocalServiceUtil.isUserAMember(proposalId, memberId)) {
				System.out.println(proposalId + "," + memberId + ",\"" + user.getScreenName() + "\",\"" + user.getFullName() + "\",\"" + user.getEmailAddress() + "\"," + isInActiveContest);
			}
		}
		
		
		return null;
	}

}
