package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ImpactIteration;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.enums.ContestTier;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
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

    @RequestMapping(params = {"pageToDisplay=proposalDetails_IMPACT"})
    public String showImpactTab(PortletRequest request, Model model, @RequestParam(required = false) boolean edit)
            throws Exception {

        Contest contest = proposalsContext.getContest(request);
        setCommonModelAndPageAttributes(request, model, ProposalTab.IMPACT);

        try {
            List<ImpactIteration> impactIterations = ContestLocalServiceUtil.getContestImpactIterations(contest);
            model.addAttribute("impactIterations", impactIterations);
        } catch (PortalException e) {
            _log.warn("Using default impact tab view since no impact iteration are associated with the contest: "+ contest.getContestPK());
            return "proposalImpactError";
        }

        switch (ContestTier.getContestTierByTierType(contest.getContestTier())) {
            case BASIC:
                return showImpactTabBasicProposal(request, model);
            case REGION_SECTOR:
                return "proposalImpactError";
            case REGION_AGGREGATE:
            case GLOBAL:
                return showImpactTabIntegratedProposal(request, model, edit);
            default:
                _log.warn("Using default impact tab view since contest tier is not set for contest: "+ contest.getContestPK());
                return "proposalImpactError";
        }
    }

    private String showImpactTabIntegratedProposal(PortletRequest request, Model model, Boolean edit)
            throws Exception {
        
        Proposal proposal = proposalsContext.getProposal(request);
        ProposalWrapper proposalWrapper = proposalsContext.getProposalWrapped(request);
        Contest contest = proposalsContext.getContest(request);

        boolean isGlobalContest = isGlobalContest(contest);
        boolean isRegionalSectorContest = isRegionalSectorContest(contest);
        model.addAttribute("isGlobalContest", isGlobalContest);
        model.addAttribute("isRegionalSectorContest", isRegionalSectorContest);
        model.addAttribute("isRegionalContest", isRegionalContest(contest));

        if(!isGlobalContest) {
            IntegratedProposalImpactSeries integratedProposalImpactSeries = new IntegratedProposalImpactSeries(proposal, contest);
            model.addAttribute("impactSeries", integratedProposalImpactSeries);
        }

        if (edit && !isRegionalSectorContest) {
            if(isGlobalContest) {
                List<Proposal> subProposals =  ProposalLocalServiceUtil.getContestIntegrationRelevantSubproposals(proposal.getProposalId());

                ProposalImpactScenarioCombinationWrapper proposalImpactScenarioCombinationWrapper =
                        new ProposalImpactScenarioCombinationWrapper(subProposals);

                boolean isConsolidationPossible = proposalImpactScenarioCombinationWrapper.isConsolidationOfScenariosPossible();
                model.addAttribute("CONSOLIDATE", isConsolidationPossible);

                if(!isConsolidationPossible){
                    model.addAttribute("proposalToModelMap", proposalImpactScenarioCombinationWrapper.getProposalNameToModelScenarioRegionMap());
                    populateModelOptions(model, request);
                } else {

                    Long consolidatedScenarioId;
                    Long consolidatedModelId;

                    if(proposalWrapper.getScenarioId() != null && proposalWrapper.getScenarioId() != 0) {

                        Long proposalScenarioId = proposalWrapper.getScenarioId();
                        boolean isCombinedScenario = proposalImpactScenarioCombinationWrapper.isCombinedScenario(proposalScenarioId);

                        if(isCombinedScenario){
                            if(proposalImpactScenarioCombinationWrapper.scenarioInputParameterAreDifferentThanAggregated(proposalScenarioId)){

                                proposalImpactScenarioCombinationWrapper.runCombinedScenarioSimulation();
                                consolidatedScenarioId = proposalImpactScenarioCombinationWrapper.getOutputScenarioId();
                                consolidatedModelId = proposalImpactScenarioCombinationWrapper.getOutputModelId();

                            } else {
                                consolidatedScenarioId = proposalScenarioId;
                                consolidatedModelId = proposalImpactScenarioCombinationWrapper.getModelIdForScenarioId(proposalScenarioId);
                            }

                            model.addAttribute("consolidatedScenarioId", consolidatedScenarioId);
                            model.addAttribute("consolidatedModelId", consolidatedModelId);
                        }

                    } else {
                        proposalImpactScenarioCombinationWrapper.runCombinedScenarioSimulation();
                        consolidatedScenarioId = proposalImpactScenarioCombinationWrapper.getOutputScenarioId();
                        consolidatedModelId = proposalImpactScenarioCombinationWrapper.getOutputModelId();
                        model.addAttribute("consolidatedScenarioId", consolidatedScenarioId);
                        model.addAttribute("consolidatedModelId", consolidatedModelId);
                    }
                }

                populateConsolidationOptions(model);
            }

            populateModelOptions(model, request);
        }

        model.addAttribute("edit", edit);

        boolean showSubProposalListing = false;
        if(showSubProposalListing) {
            populateImpactTabBasicProposal(model, contest, proposal);
        }
        model.addAttribute("showSubProposalListing", showSubProposalListing);

        return "integratedProposalImpact";
    }

    private void populateModelOptions(Model model, PortletRequest request){
        try {
            Long contestId = proposalsContext.getContest(request).getContestPK();
            Map<Long, String> modelIdsWithNames = ContestLocalServiceUtil.getModelIdsAndNames(contestId);
            if (modelIdsWithNames.size() > 1) {
                model.addAttribute("availableModels", modelIdsWithNames);
            }
        } catch (Exception e){
            _log.warn("Could not populateModelOptions", e);
        }
    }

    private void populateConsolidationOptions(Model model){
        Map<String, String[]> consolidateOptions = getConsolidateOptionsOnGlobalLevel();
        if (consolidateOptions.size() > 1) {
            model.addAttribute("consolidateOptions", consolidateOptions);
        }
    }

    private String showImpactTabBasicProposal(PortletRequest request, Model model)
            throws PortalException, SystemException {

        Contest contest = proposalsContext.getContest(request);
        ProposalWrapper proposal = proposalsContext.getProposalWrapped(request);

        try {
            List<ImpactIteration> impactIterations = ContestLocalServiceUtil.getContestImpactIterations(contest);
            model.addAttribute("impactIterations", impactIterations);

            // All filled out impact series
            List<ProposalAttribute> impactProposalAttributes =
                    ProposalLocalServiceUtil.getImpactProposalAttributes(proposalsContext.getProposal(request));

            ProposalImpactSeriesList proposalImpactSeriesList = new ProposalImpactSeriesList(contest, proposal.getWrapped());
            model.addAttribute("impactSerieses", proposalImpactSeriesList.getImpactSerieses());

            Map<OntologyTerm, List<OntologyTerm>> ontologyMap = new ProposalImpactUtil(contest).calculateAvailableOntologyMap(proposalImpactSeriesList.getImpactSerieses());
            model.addAttribute("regionTerms", sortByName(ontologyMap.keySet()));

            model.addAttribute("proposalsPermissions", proposalsContext.getPermissions(request));
        } catch (PortalException e) {
            _log.error("Error retrieving impact serieses for contest with contest ID " + contest.getContestPK(), e);
        }

        return "basicProposalImpact";
    }

    private void populateImpactTabBasicProposal(Model model, Contest contest, Proposal proposalParent) throws PortalException, SystemException{
        Set<Proposal> referencedSubProposals = IntegratedProposalImpactSeries.getSubProposalsOnContestTier(proposalParent, ContestTier.BASIC.getTierType());
        ContestWrapper contestWrapper = new ContestWrapper(contest);
        List<OntologyTerm> ontologyTermList = contestWrapper.getWhere();

        List<ProposalImpactSeries> proposalImpactSerieses = new ArrayList<>();
        for(Proposal proposal : referencedSubProposals) {
            ProposalImpactSeriesList proposalImpactSeriesList = new ProposalImpactSeriesList(contest, proposal);
            for(ProposalImpactSeries proposalImpactSeries : proposalImpactSeriesList.getImpactSerieses()){
                if(proposalImpactSeries.getWhereTerm().equals(ontologyTermList.get(0))) {
                    proposalImpactSerieses.add(proposalImpactSeries);
                }
            }

        }
        model.addAttribute("impactSerieses", proposalImpactSerieses);
    }

    private List<OntologyTerm> sortByName(Collection<OntologyTerm> collection) {
        List <OntologyTerm> list = new ArrayList<OntologyTerm>(collection);
        Collections.sort(list, new Comparator<OntologyTerm>() {

            @Override
            public int compare(OntologyTerm o1, OntologyTerm o2) {
                return o1.getName().compareTo(o2.getName());
            }

        });

        return list;
    }

    private boolean hasImpactTabPermission(PortletRequest request) throws SystemException, PortalException{
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        // If not admin or fellow or IAF return false
        return (permissions.getCanAdminAll() || permissions.getCanFellowActions() || permissions.getCanIAFActions());
    }

    private boolean isGlobalContest(Contest contest) throws PortalException, SystemException{
        return contest.getContestTier() == ContestTier.GLOBAL.getTierType();
    }
    private boolean isRegionalContest(Contest contest) throws PortalException, SystemException{
        return contest.getContestTier() == ContestTier.REGION_AGGREGATE.getTierType();
    }
    private boolean isRegionalSectorContest(Contest contest) throws PortalException, SystemException{
        return contest.getContestTier() == ContestTier.REGION_SECTOR.getTierType();
    }

    private Map<String, String[]> getConsolidateOptionsOnGlobalLevel(){
        Map<String, String[]> consolidateOptions = new LinkedHashMap<>();

        String[] consolidated = {"USE VALUES FROM THE REGIONAL PLANS", "The GHG emissions from your regional plans will be automatically used as inputs for the global simulation model."};
        String[] separate = {"SPECIFY NEW VALUES", "Use the options below to calculate the impact of your global plan. These results will be independent of the GHG emissions from the regional plans you included."};

        consolidateOptions.put("CONSOLIDATE", consolidated);
        consolidateOptions.put("SEPARATE", separate);
        return consolidateOptions;
    }

}
