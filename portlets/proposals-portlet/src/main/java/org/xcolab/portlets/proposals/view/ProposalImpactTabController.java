package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ImpactIteration;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.enums.ContestTier;
import org.xcolab.portlets.proposals.wrappers.ProposalImpactScenarioCombinationWrapper;
import org.xcolab.portlets.proposals.utils.ProposalImpactUtil;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.*;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by kmang on 12/03/15.
 */

@Controller
@RequestMapping("view")
public class ProposalImpactTabController extends BaseProposalTabController {

    private final static Log _log = LogFactoryUtil.getLog(ProposalImpactTabController.class);

    @Autowired
    private ProposalsContext proposalsContext;

    private Contest contest = null;
    private ProposalWrapper proposalWrapper = null;

    @RequestMapping(params = {"pageToDisplay=proposalDetails_IMPACT"})
    public String showImpactTab(PortletRequest request, Model model, @RequestParam(required = false) boolean edit)
            throws Exception {

        boolean userAllowedToEdit = false;
        contest = proposalsContext.getContest(request);
        proposalWrapper = proposalsContext.getProposalWrapped(request);
        setCommonModelAndPageAttributes(request, model, ProposalTab.IMPACT);
        if (edit) {
            userAllowedToEdit = canEditImpactTab(request);
        }

        model.addAttribute("edit", userAllowedToEdit);
        model.addAttribute("isRegionalSectorContest", isRegionalSectorContest(contest));
        model.addAttribute("isRegionalContest", isRegionalContest(contest));
        model.addAttribute("isGlobalContest", isGlobalContest(contest));

        boolean tabUsesModeling = (isRegionalContest(contest) || isGlobalContest(contest));
        if(tabUsesModeling){
            model.addAttribute("availableModels", ContestLocalServiceUtil.getModelIdsAndNames(contest.getContestPK()));
            model.addAttribute("modelId", getModelIdIfProposalHasScenarioIdOrContestDefaultModelId());
            model.addAttribute("scenarioId", proposalWrapper.getScenarioId());
        }

        boolean showSubProposalListing = (isRegionalSectorContest(contest));
        if (showSubProposalListing) {
            model.addAttribute("impactSerieses", getImpactTabBasicProposal(proposalWrapper.getWrapped()));
        }
        model.addAttribute("showSubProposalListing", showSubProposalListing);

        switch (ContestTier.getContestTierByTierType(contest.getContestTier())) {
            case BASIC:
                return showImpactTabBasic(request, model);
            case REGION_SECTOR:
                //return showImpactTabRegionSector(request, model);
                return "proposalImpactError";
            case REGION_AGGREGATE:
                return showImpactTabRegionAggregate(request, model);
            case GLOBAL:
                if(userAllowedToEdit)
                    return showImpactTabEditGlobal(request, model);
                else
                    return showImpactTabGlobal(request, model);
            default:
                _log.warn("Using default impact tab view since contest tier is not set for contest: " + contest.getContestPK());
                return "proposalImpactError";
        }
    }

    private Long getModelIdIfProposalHasScenarioIdOrContestDefaultModelId() throws Exception{
        Long modelId = proposalWrapper.getModelId();
        boolean scenarioIdValid =
                Validator.isNotNull(proposalWrapper.getScenarioId()) && proposalWrapper.getScenarioId() > 0;
        if(scenarioIdValid){
            try {
                modelId = CollaboratoriumModelingService.repository()
                        .getScenario(proposalWrapper.getScenarioId()).getSimulation().getId();
            } catch (Exception e){
                _log.warn("Could not fetch simulation id for proposal scenario: ", e);
            }
        }
        return modelId;
    }

    private boolean canEditImpactTab(PortletRequest request) throws Exception {
        return ProposalTab.IMPACT.getCanEdit(proposalsContext.getPermissions(request), proposalsContext, request);
    }

    private String showImpactTabRegionSector(PortletRequest request, Model model, Boolean edit) throws Exception {
        return "integratedProposalImpact";
    }

    private String showImpactTabRegionAggregate(PortletRequest request, Model model)
            throws Exception {
        return "integratedProposalImpact";
    }

    private String showImpactTabGlobal(PortletRequest request, Model model)
            throws Exception {
        return "integratedProposalImpact";
    }

    private String showImpactTabEditGlobal(PortletRequest request, Model model)
            throws Exception {

        List<Proposal> subProposals =
                ProposalLocalServiceUtil.getContestIntegrationRelevantSubproposals(proposalWrapper.getProposalId());
        ProposalImpactScenarioCombinationWrapper proposalImpactScenarioCombinationWrapper =
                new ProposalImpactScenarioCombinationWrapper(subProposals);
        boolean isConsolidationPossible =
                proposalImpactScenarioCombinationWrapper.isConsolidationOfScenariosPossible();

        if(isConsolidationPossible){
            boolean isProposalUsingCombinedScenario = false;
            boolean isValidScenario = (proposalWrapper.getScenarioId() != null && proposalWrapper.getScenarioId() != 0);
            if (isValidScenario) {

                Long proposalScenarioId = proposalWrapper.getScenarioId();
                if (proposalWrapper.isConsolidatedScenario(proposalScenarioId)) {
                    isProposalUsingCombinedScenario = true;
                    proposalImpactScenarioCombinationWrapper.calculateCombinedInputParameters();

                    if (proposalImpactScenarioCombinationWrapper.scenarioInputParameterAreDifferentThanAggregated(proposalScenarioId)) {
                        proposalImpactScenarioCombinationWrapper.runCombinedScenarioSimulation();
                        model.addAttribute("consolidatedScenarioId",
                                proposalImpactScenarioCombinationWrapper.getOutputScenarioId());
                        model.addAttribute("consolidatedModelId",
                                proposalImpactScenarioCombinationWrapper.getOutputModelId());
                    } else {
                        model.addAttribute("consolidatedScenarioId", proposalScenarioId);
                        model.addAttribute("consolidatedModelId",
                                proposalImpactScenarioCombinationWrapper.getModelIdForScenarioId(proposalScenarioId));
                    }
                } else {
                    proposalImpactScenarioCombinationWrapper.runCombinedScenarioSimulation();
                    model.addAttribute("consolidatedScenarioId",
                            proposalImpactScenarioCombinationWrapper.getOutputScenarioId());
                    model.addAttribute("consolidatedModelId",
                            proposalImpactScenarioCombinationWrapper.getOutputModelId());
                }
            } else {
                proposalImpactScenarioCombinationWrapper.runCombinedScenarioSimulation();
                model.addAttribute("consolidatedScenarioId",
                        proposalImpactScenarioCombinationWrapper.getOutputScenarioId());
                model.addAttribute("consolidatedModelId",
                        proposalImpactScenarioCombinationWrapper.getOutputModelId());
            }
            model.addAttribute("isProposalUsingCombinedScenario", isProposalUsingCombinedScenario);
        } else{
            model.addAttribute("proposalToModelMap",
                    proposalImpactScenarioCombinationWrapper.getRegionToProposalSimulationScenarioMap());
        }

        model.addAttribute("consolidationPossible", isConsolidationPossible);
        model.addAttribute("consolidateOptions", getConsolidationOptions());

        return "integratedProposalImpact";
    }

    private Map<String, String[]> getConsolidationOptions() {
        Map<String, String[]> consolidateOptions = getConsolidateOptionsOnGlobalLevel();
        return consolidateOptions;
    }

    private String showImpactTabBasic(PortletRequest request, Model model)
            throws PortalException, SystemException {

        try {
            List<ImpactIteration> impactIterations = ContestLocalServiceUtil.getContestImpactIterations(contest);
            model.addAttribute("impactIterations", impactIterations);
        } catch (PortalException e) {
            _log.warn("Using default impact tab view since no impact iteration are associated with the contest: " + contest.getContestPK());
            return "proposalImpactError";
        }

        try {
            List<ImpactIteration> impactIterations = ContestLocalServiceUtil.getContestImpactIterations(contest);
            model.addAttribute("impactIterations", impactIterations);

            // All filled out impact series
            List<ProposalAttribute> impactProposalAttributes =
                    ProposalLocalServiceUtil.getImpactProposalAttributes(proposalsContext.getProposal(request));

            ProposalImpactSeriesList proposalImpactSeriesList =
                    new ProposalImpactSeriesList(contest, proposalWrapper.getWrapped());
            model.addAttribute("impactSerieses", proposalImpactSeriesList.getImpactSerieses());

            Map<OntologyTerm, List<OntologyTerm>> ontologyMap =
                    new ProposalImpactUtil(contest).calculateAvailableOntologyMap(proposalImpactSeriesList.getImpactSerieses());
            model.addAttribute("regionTerms", sortByName(ontologyMap.keySet()));
            model.addAttribute("proposalsPermissions", proposalsContext.getPermissions(request));
        } catch (PortalException e) {
            _log.error("Error retrieving impact serieses for contest with contest ID " + contest.getContestPK(), e);
        }

        return "basicProposalImpact";
    }

    private List<ProposalImpactSeries> getImpactTabBasicProposal(Proposal proposalParent)
            throws PortalException, SystemException {
        Set<Proposal> referencedSubProposals =
                IntegratedProposalImpactSeries.getSubProposalsOnContestTier(proposalParent, ContestTier.BASIC.getTierType());
        ContestWrapper contestWrapper = new ContestWrapper(contest);
        List<OntologyTerm> ontologyTermList = contestWrapper.getWhere();
        List<ProposalImpactSeries> proposalImpactSerieses = new ArrayList<>();
        for (Proposal proposal : referencedSubProposals) {
            ProposalImpactSeriesList proposalImpactSeriesList = new ProposalImpactSeriesList(contest, proposal);
            for (ProposalImpactSeries proposalImpactSeries : proposalImpactSeriesList.getImpactSerieses()) {
                if (proposalImpactSeries.getWhereTerm().equals(ontologyTermList.get(0))) {
                    proposalImpactSerieses.add(proposalImpactSeries);
                }
            }
        }
        return proposalImpactSerieses;
    }

    private List<OntologyTerm> sortByName(Collection<OntologyTerm> collection) {
        List<OntologyTerm> list = new ArrayList<OntologyTerm>(collection);
        Collections.sort(list, new Comparator<OntologyTerm>() {

            @Override
            public int compare(OntologyTerm o1, OntologyTerm o2) {
                return o1.getName().compareTo(o2.getName());
            }

        });

        return list;
    }

    private boolean isGlobalContest(Contest contest) throws PortalException, SystemException {
        return contest.getContestTier() == ContestTier.GLOBAL.getTierType();
    }

    private boolean isRegionalContest(Contest contest) throws PortalException, SystemException {
        return contest.getContestTier() == ContestTier.REGION_AGGREGATE.getTierType();
    }

    private boolean isRegionalSectorContest(Contest contest) throws PortalException, SystemException {
        return contest.getContestTier() == ContestTier.REGION_SECTOR.getTierType();
    }

    private Map<String, String[]> getConsolidateOptionsOnGlobalLevel() {
        Map<String, String[]> consolidateOptions = new LinkedHashMap<>();
        String[] consolidated = {"USE VALUES FROM THE REGIONAL PLANS", "The values from your regional plans will be automatically used as inputs for the global simulation model."};
        String[] separate = {"SPECIFY NEW VALUES", "Use the options below to calculate the impact of your global plan. These results will be independent of the values from the regional plans you included."};
        consolidateOptions.put("CONSOLIDATE", consolidated);
        consolidateOptions.put("SEPARATE", separate);
        return consolidateOptions;
    }

}
