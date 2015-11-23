package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.enums.ContestPhaseTypeValue;
import org.xcolab.portlets.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.utils.ProposalGroupingUtil;
import org.xcolab.wrappers.BaseProposalWrapper;
import org.xcolab.wrappers.ContestTypeProposalWrapper;

import javax.portlet.PortletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("view")
public class ProposalSectionsTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = "pageToDisplay=proposalDetails")
    public String showProposalDetails(
            @RequestParam(value="planId") Long proposalId, 
            @RequestParam Long contestId, 
            @RequestParam(required = false) Long phaseId, 
            @RequestParam(defaultValue="false") boolean edit,
            @RequestParam(defaultValue="false") boolean move,
            @RequestParam(defaultValue="false") boolean hideOnMove,
            @RequestParam(required = false) Long moveFromContestPhaseId,
            @RequestParam(defaultValue="false") boolean voted,
            Model model, PortletRequest request) 
            throws PortalException, SystemException {
        
        //findEntitiesAndPopulateModel(proposalId, contestId, phaseId, model);

        setCommonModelAndPageAttributes(request, model, ProposalTab.DESCRIPTION);

        boolean editValidated = false;
        if(edit && proposalsContext.getPermissions(request).getCanEdit()){
            editValidated = true;
        }
        model.addAttribute("edit", editValidated);
        model.addAttribute("voted", voted);

        final Proposal proposal = proposalsContext.getProposal(request);
        Contest baseContest = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());

        if (voted) {
            Date votingDeadline = getVotingDeadline(baseContest);
            if (Validator.isNotNull(votingDeadline)) {
                final DateFormat customDateFormat = new SimpleDateFormat("MMMM dd, YYYY", Locale.US);
                model.addAttribute("votingDeadline", customDateFormat.format(votingDeadline));
            } else {
                model.addAttribute("votingDeadline", "");
            }
        }

        if (move) {
        	// get base proposal from base contest
        	ContestPhase contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(baseContest);
        	
        	ProposalWrapper baseProposalWrapped = new ProposalWrapper(proposal, proposal.getCurrentVersion(), baseContest, contestPhase, null);
        	model.addAttribute("baseProposal", baseProposalWrapped);
        	model.addAttribute("baseContest", new ContestWrapper(baseContest));
        	model.addAttribute("move", true);
        	
        	UpdateProposalDetailsBean updateProposalDetailsBean = 
        			new UpdateProposalDetailsBean(proposalsContext.getProposalWrapped(request), baseProposalWrapped, true);

            updateProposalDetailsBean.setHideOnMove(hideOnMove);
            updateProposalDetailsBean.setMoveFromContestPhaseId(moveFromContestPhaseId);
        	// find sections that can't be mapped without user interaction

            Set<Long> newContestSections = new HashSet<>();
        	
        	for (ProposalSectionWrapper section: proposalsContext.getProposalWrapped(request).getSections()) {
        		newContestSections.add(section.getSectionDefinitionId());
        	}


            boolean hasNotMappedSections = false;
            for (ProposalSectionWrapper section: baseProposalWrapped.getSections()) {
        		if (section.getContent() != null && !section.getContent().trim().isEmpty()) {
        			// we have non empty section in base proposal, check if such
        			// section exists in target contest
        			if (! newContestSections.contains(section.getSectionDefinitionId())) {
        				hasNotMappedSections = true;
        			}
        		}
        		
        	}
        	
        	updateProposalDetailsBean.setMoveToContestPhaseId(contestPhase.getContestPhasePK());
        	model.addAttribute("updateProposalSectionsBean", updateProposalDetailsBean);
        	model.addAttribute("hasNotMappedSections", hasNotMappedSections);
        } else {
            model.addAttribute("updateProposalSectionsBean", new UpdateProposalDetailsBean(proposalsContext.getProposalWrapped(request)));
        }

        
        if (editValidated || move) {
            return "proposalDetails_edit";
        }

        ProposalWrapper proposalWrapper = proposalsContext.getProposalWrapped(request);
        ProposalJudgeWrapper proposalJudgeWrapper = new ProposalJudgeWrapper(proposalWrapper, proposalsContext.getUser(request));
        JudgeProposalFeedbackBean judgeProposalBean = new JudgeProposalFeedbackBean(proposalJudgeWrapper);
        Long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
        judgeProposalBean.setContestPhaseId(contestPhaseId);

        model.addAttribute("judgeProposalBean", judgeProposalBean);

        List<Proposal> linkedProposals = ProposalLocalServiceUtil.getSubproposals(proposal.getProposalId(), true);
        Map<ContestType, List<Proposal>> proposalsByContestType = ProposalGroupingUtil.groupByContestType(linkedProposals);
        Map<Long, ContestTypeProposalWrapper> contestTypeProposalWrappersByContestTypeId = new HashMap<>();
        for (ContestType contestType : ContestTypeLocalServiceUtil.getActiveContestTypes()) {
            contestTypeProposalWrappersByContestTypeId.put(contestType.getId(), new ContestTypeProposalWrapper(contestType));
            final List<Proposal> proposalsInContestType = proposalsByContestType.get(contestType);
            for (Proposal p : proposalsInContestType) {
                contestTypeProposalWrappersByContestTypeId.get(contestType.getId()).getProposals().add(new BaseProposalWrapper(p));
            }
        }
        model.addAttribute("linkedProposalContestTypeProposalWrappersByContestTypeId", contestTypeProposalWrappersByContestTypeId);

        return "proposalDetails";
    }

    private Date getVotingDeadline(Contest contest) throws SystemException, PortalException {
        List<ContestPhase> contestPhases = ContestLocalServiceUtil.getAllPhases(contest);
        try {
            return getActiveVotingPhase(contestPhases).getPhaseEndDate();
        }
        // No active proposal creation phase could be found -
        // should never be the case unless an admin is creating a proposal in a non-creation phase
        catch(SystemException exception) {
            return null;
        }
    }

    private ContestPhase getActiveVotingPhase(List<ContestPhase> contestPhases) throws SystemException {
        for (ContestPhase phase : contestPhases) {
            if (phase.getContestPhaseType() == ContestPhaseTypeValue.SELECTION_OF_WINNERS.getTypeId() ||
                    phase.getContestPhaseType() == ContestPhaseTypeValue.SELECTION_OF_WINNERS_NEW.getTypeId() ||
                    phase.getContestPhaseType() == ContestPhaseTypeValue.WINNERS_SELECTION.getTypeId()) {
                if (ContestPhaseLocalServiceUtil.getPhaseActive(phase)) {
                    return phase;
                }
            }
        }

        throw new SystemException("Active voting phase was not found");
    }

}
