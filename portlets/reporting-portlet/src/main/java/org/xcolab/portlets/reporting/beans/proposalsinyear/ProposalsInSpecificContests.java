package org.xcolab.portlets.reporting.beans.proposalsinyear;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.reporting.beans.contests.ContestFetcher;
import org.xcolab.portlets.reporting.beans.proposalsinyear.proposalversiondeterminer.NewestProposal;
import org.xcolab.portlets.reporting.beans.proposalsinyear.proposalversiondeterminer.ProposalVersionDeterminer;

import java.util.*;

/**
 * @author pdeboer
 *         First created on 20/06/14 at 15:50
 */
public class ProposalsInSpecificContests {
	List<ContestPhaseRibbonType> ribbons;
	ProposalVersionDeterminer proposalVersionDeterminer = new NewestProposal();
	List<Proposal2Phase> proposals2Phases;
	Set<Long> movedProposalIds = new HashSet<>();

	public ProposalsInSpecificContests() {
		try {
			ribbons = ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonTypes(0, Integer.MAX_VALUE);
			proposals2Phases = Proposal2PhaseLocalServiceUtil.getProposal2Phases(0, Integer.MAX_VALUE);
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

	public void setProposalVersionDeterminer(ProposalVersionDeterminer proposalVersionDeterminer) {
		this.proposalVersionDeterminer = proposalVersionDeterminer;
	}

	public List<ProposalWithFinalistAndContent> get() throws Exception {
		return get(ContestFetcher.getContestsIn2013(), false);
	}

	public List<ProposalWithFinalistAndContent> get(List<Contest> targetContests, boolean takeAllProposalsFromCreationPhase) throws Exception {
		List<ProposalWithFinalistAndContent> ret = new LinkedList<>();
		Map<Integer, String> sections = getSectionTypeIDToTextMap();

		for (Contest contest : targetContests) {
			List<ContestPhase> phasesForContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());

			ContestPhase creationPhase = null;
			ContestPhase completedPhase = null;

			for (ContestPhase contestPhase : phasesForContest) {
				if (contestPhase.getPhaseEndDate() == null) completedPhase = contestPhase;
				if (contestPhase.getContestPhaseType() == 1L)
					creationPhase = contestPhase;
			}

			if (completedPhase == null || creationPhase == null) {
				System.out.println("skipped " + contest);
				continue; //invalid contest
			}
			ContestPhase proposalSourcePhase = takeAllProposalsFromCreationPhase ? creationPhase : completedPhase;
			List<Proposal> visibleProposals = getVisibleProposals(
					proposalSourcePhase,
					ProposalLocalServiceUtil.getProposalsInContestPhase(
							proposalSourcePhase.getContestPhasePK()));

			for (Proposal visibleProposal : visibleProposals) {
				ProposalWithFinalistAndContent pte = new ProposalWithFinalistAndContent();


				int targetVersion = proposalVersionDeterminer.getTargetVersion(visibleProposal);
				if (targetVersion < 0) continue;
				pte.setContest(contest);
				pte.setId(visibleProposal.getProposalId());
				pte.setProposalMoved(movedProposalIds.contains(visibleProposal.getProposalId()));
				pte.setUrl("http://climatecolab.org/web/guest/plans/-/plans/contestId/" + contest.getContestPK() + "/planId/" + visibleProposal.getProposalId());
				pte.setUsedVersion(targetVersion);
				pte.setProposalRibbon(getProposalRibbon(visibleProposal, completedPhase));

				List<ProposalAttribute> attributes = ProposalLocalServiceUtil.getAttributes(visibleProposal.getProposalId(), targetVersion);
				for (ProposalAttribute attribute : attributes) {
					if (attribute.getName().equals("NAME")) {
						pte.setProposalName(attribute.getStringValue());
					}
					if (attributeTypeAllowed(attribute)) {
						String htmlValue = attribute.getStringValue();
						pte.setContent(pte.getContent() + "\n" + htmlValue);
						String sectionTitle = sections.get((int) attribute.getAdditionalId());
						if (attribute.getName().equals("DESCRIPTION"))
							sectionTitle = "Description";
						else if (attribute.getName().equals("PITCH"))
							sectionTitle = "Pitch";

						pte.setContentWithSectionTitles(pte.getContentWithSectionTitles() + "\n<br/><h1>" + sectionTitle + "</h1><br/>\n" + htmlValue);
						// class="sectiontitle"
					}
				}

				ret.add(pte);
			}
		}
		return ret;
	}

	private Map<Integer, String> getSectionTypeIDToTextMap() throws SystemException {
		Map<Integer, String> sections = new HashMap<>();
		List<PlanSectionDefinition> planSectionDefinitions = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinitions(0, Integer.MAX_VALUE);
		for (PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
			sections.put(
					(int) planSectionDefinition.getId(), planSectionDefinition.getTitle());
		}
		return sections;
	}

	private List<Proposal> getVisibleProposals(ContestPhase phase, List<Proposal> proposals) {
		List<Proposal> targetProposals = new LinkedList<>();
		for (Proposal proposal : proposals) {
			if (!proposal.getVisible()) continue;

			ProposalContestPhaseAttribute proposalContestPhaseAttribute = null;
			try {
				proposalContestPhaseAttribute = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposal.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.VISIBLE);
				if (proposalContestPhaseAttribute.getNumericValue() == 0) {
					//only skip this proposal if it's not affected by the moving-proposals bug

					List<ContestPhase> phasesOfThisContest = ContestLocalServiceUtil.getAllPhases(ContestLocalServiceUtil.getContest(phase.getContestPK()));
					Set<Long> phaseIDsOfThisContest = new HashSet<>();
					for (ContestPhase contestPhase : phasesOfThisContest) {
						phaseIDsOfThisContest.add(contestPhase.getContestPK());
					}

					boolean proposalMovedToOtherContest = false;
					//check if proposal got moved in later phase
					for (Proposal2Phase p2p : proposals2Phases) {
						if (p2p.getProposalId() == proposal.getProposalId()) {
							if (!phaseIDsOfThisContest.contains(p2p.getContestPhaseId())) {
								proposalMovedToOtherContest = true;
								movedProposalIds.add(proposal.getProposalId());
								break;
							}
						}
					}
					if (!proposalMovedToOtherContest)
						continue;
				}

			} catch (SystemException e) {
				e.printStackTrace();
			} catch (NoSuchProposalContestPhaseAttributeException e) {
				e.printStackTrace();
			} catch (PortalException e) {
				e.printStackTrace();
			}
			targetProposals.add(proposal);
		}
		return targetProposals;
	}

	private int getEndVersionOfCreationPhase(ContestPhase creationPhase, Proposal visibleProposal) throws SystemException, PortalException {
		Proposal2Phase proposal2Phase = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(visibleProposal.getProposalId(), creationPhase.getContestPhasePK());
		return proposal2Phase.getVersionTo();

	}


	private int getProposalRibbon(Proposal proposal, ContestPhase phase) {
		ProposalContestPhaseAttribute proposalContestPhaseAttribute = null;
		try {
			proposalContestPhaseAttribute = getRibbonAttribute(proposal, phase);
			return (int) proposalContestPhaseAttribute.getNumericValue();

		} catch (Exception e) {
			return 0;
		}
	}
	/*
	private int getProposalRibbon(Proposal proposal) {
		try {
			List<ProposalContestPhaseAttribute> attributesForProposal = ProposalContestPhaseAttributeLocalServiceUtil.dynamicQuery().add(PropertyFactoryUtil.forName("proposalId").eq(proposal.getProposalId())).list();
			for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : attributesForProposal) {
				if (proposalContestPhaseAttribute.getName().equals(ProposalContestPhaseAttributeKeys.RIBBON)) {
					return (int) proposalContestPhaseAttribute.getNumericValue();
				}
			}
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}*/

	private boolean attributeTypeAllowed(ProposalAttribute attribute) {
		Set<String> allowedTypes = new HashSet<>();
		for (String s : new String[]{"SECTION", "PITCH", "DESCRIPTION"}) {
			allowedTypes.add(s);
		}
		return allowedTypes.contains(attribute.getName());
	}

	private ProposalContestPhaseAttribute getRibbonAttribute(Proposal proposal, ContestPhase phase) {
		try {
			ProposalContestPhaseAttribute proposalContestPhaseAttribute = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposal.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.RIBBON);
			return proposalContestPhaseAttribute;
		} catch (Exception e) {
			return null;
		}
	}
}
