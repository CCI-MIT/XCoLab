package org.xcolab.portlets.proposals.view;



import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import edu.mit.cci.roma.client.comm.ModelNotFoundException;
import edu.mit.cci.roma.client.comm.ScenarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ImpactTemplateClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ImpactIteration;
import org.xcolab.client.contest.pojo.OntologyTerm;
import org.xcolab.client.proposals.ProposalUnversionedAttributeClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalUnversionedAttribute;
import org.xcolab.enums.ContestTier;
import org.xcolab.enums.ProposalUnversionedAttributeName;
import org.xcolab.portlets.proposals.utils.ProposalImpactUtil;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.IntegratedProposalImpactSeries;
import org.xcolab.portlets.proposals.wrappers.ProposalImpactScenarioCombinationWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalImpactSeries;
import org.xcolab.portlets.proposals.wrappers.ProposalImpactSeriesList;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletRequest;

@Controller
@RequestMapping("view")
public class ProposalImpactTabController extends BaseProposalTabController {

    private final static Log _log = LogFactoryUtil.getLog(ProposalImpactTabController.class);

    @Autowired
    private ProposalsContext proposalsContext;

    private Contest contest;
    private ProposalWrapper proposalWrapper;

    @RequestMapping(params = {"pageToDisplay=proposalDetails_IMPACT"})
    public String showImpactTab(PortletRequest request, Model model, @RequestParam(required = false) boolean edit)
            throws PortalException, SystemException, IOException, ScenarioNotFoundException, ModelNotFoundException {

        contest = proposalsContext.getContest(request);
        proposalWrapper = proposalsContext.getProposalWrapped(request);
        setCommonModelAndPageAttributes(request, model, ProposalTab.IMPACT);
        boolean userAllowedToEdit = false;

        if (edit) {
            userAllowedToEdit = canEditImpactTab(request);
            boolean userCanCommentAsAuthor = canCommentAsAuthor(request);
            model.addAttribute("canCommentAsAuthor", userCanCommentAsAuthor);
            boolean userCanCommentAsAIF = canCommentAsAIF(request);
            model.addAttribute("canCommentAsAIF", userCanCommentAsAIF);
        }

        List<ProposalUnversionedAttribute> unversionedAttributes = ProposalUnversionedAttributeClient.
                getProposalUnversionedAttributesByProposalId(proposalWrapper.getProposalId());
        if ( unversionedAttributes != null && ! unversionedAttributes.isEmpty()) {
            for (ProposalUnversionedAttribute pua : unversionedAttributes) {
                if (pua.getName().equals(ProposalUnversionedAttributeName.IMPACT_AUTHOR_COMMENT
                        .toString())) {
                    if (!Validator.isBlank(pua.getStringValue())) {
                        model.addAttribute("authorComment", pua);
                    }
                }
                if (pua.getName()
                        .equals(ProposalUnversionedAttributeName.IMPACT_IAF_COMMENT.toString())) {
                    if (!Validator.isBlank(pua.getStringValue())) {
                        model.addAttribute("iafComment", pua);
                    }
                }
            }
        }

        model.addAttribute("edit", userAllowedToEdit);
        model.addAttribute("isRegionalSectorContest", isRegionalSectorContest(contest));
        model.addAttribute("isRegionalContest", isRegionalContest(contest));
        model.addAttribute("isGlobalContest", isGlobalContest(contest));

        boolean tabUsesModeling = (isRegionalContest(contest) || isGlobalContest(contest));
        if (tabUsesModeling){
            model.addAttribute("availableModels", ContestLocalServiceUtil.getModelIdsAndNames(contest.getContestPK()));
            model.addAttribute("modelId", getModelIdIfProposalHasScenarioIdOrContestDefaultModelId());
            model.addAttribute("scenarioId", proposalWrapper.getScenarioId());
        }

        boolean showSubProposalListing = (isRegionalContest(contest));
        boolean showDataTable = (isRegionalContest(contest));
        if (showDataTable) {
            IntegratedProposalImpactSeries integratedProposalImpactSeries = new IntegratedProposalImpactSeries(proposalWrapper.getWrapped(), contest);
            model.addAttribute("impactSeries", integratedProposalImpactSeries);
            List<ImpactIteration> impactIterations = ImpactTemplateClient.getContestImpactIterations(contest);
            model.addAttribute("impactIterations", impactIterations);
        }

        if(showSubProposalListing){
            model.addAttribute("impactSerieses", getImpactTabBasicProposal(proposalWrapper.getWrapped()));
        }
        model.addAttribute("showSubProposalListing", showSubProposalListing);
        model.addAttribute("showDataTable", showDataTable);

        final ContestTier contestTier = ContestTier.getContestTierByTierType(contest.getContestTier());
        if (contestTier != null) {
            switch (contestTier) {
                case BASIC:
                    return showImpactTabBasic(request, model);
                case REGION_SECTOR:
                    //return showImpactTabRegionSector();
                    return "proposalImpactError";
                case REGION_AGGREGATE:
                    return showImpactTabRegionAggregate();
                case GLOBAL:
                    if(userAllowedToEdit) {
                        return showImpactTabEditGlobal(model);
                    } else {
                        return showImpactTabGlobal();
                    }
            }
        }
        _log.warn("Using default impact tab view since contest tier is not set for contest: " + contest.getContestPK());
        return "proposalImpactError";
    }

    private Long getModelIdIfProposalHasScenarioIdOrContestDefaultModelId() throws PortalException, SystemException {
        Long modelId = proposalWrapper.getModelId();
        boolean scenarioIdValid =
                Validator.isNotNull(proposalWrapper.getScenarioId()) && proposalWrapper.getScenarioId() > 0;
        if(scenarioIdValid){
            try {
                modelId = CollaboratoriumModelingService.repository()
                        .getScenario(proposalWrapper.getScenarioId()).getSimulation().getId();
            } catch (SystemException | PortalException | IOException e){
                _log.warn("Could not fetch simulation id for proposal scenario: ", e);
            }
        }
        return modelId;
    }

    private boolean canCommentAsAuthor(PortletRequest request) throws PortalException, SystemException {
        return proposalsContext.getPermissions(request).getCanAdminProposal();
    }
    private boolean canCommentAsAIF(PortletRequest request) throws PortalException, SystemException {
        return proposalsContext.getPermissions(request).getCanIAFActions()||proposalsContext.getPermissions(request).getCanAdminAll();
    }
    private boolean canEditImpactTab(PortletRequest request) throws PortalException, SystemException {
        return ProposalTab.IMPACT.getCanEdit(proposalsContext.getPermissions(request), proposalsContext, request);
    }

    private String showImpactTabRegionSector() {
        return "integratedProposalImpact";
    }

    private String showImpactTabRegionAggregate() {
        return "integratedProposalImpact";
    }

    private String showImpactTabGlobal() {
        return "integratedProposalImpact";
    }

    private String showImpactTabEditGlobal(Model model)
            throws PortalException, SystemException, IOException, ScenarioNotFoundException, ModelNotFoundException {

        List<Proposal> subProposals =
                ProposalsClient.getContestIntegrationRelevantSubproposals(proposalWrapper.getProposalId());
        ProposalImpactScenarioCombinationWrapper proposalImpactScenarioCombinationWrapper =
                new ProposalImpactScenarioCombinationWrapper(subProposals);
        boolean isConsolidationPossible =
                proposalImpactScenarioCombinationWrapper.isConsolidationOfScenariosPossible();
        boolean isProposalUsingCombinedScenario = false;

        if(isConsolidationPossible){
            boolean isValidScenario = (proposalWrapper.getScenarioId() != null && proposalWrapper.getScenarioId() != 0);
            if (isValidScenario) {
                Long proposalScenarioId = proposalWrapper.getScenarioId();
                boolean isScenarioUsingSameModelId = proposalImpactScenarioCombinationWrapper.isScenarioUsingSameModelId(proposalScenarioId);
                if (proposalWrapper.isConsolidatedScenario(proposalScenarioId)){
                    isProposalUsingCombinedScenario = true;
                    proposalImpactScenarioCombinationWrapper.calculateCombinedInputParameters();

                    if (!isScenarioUsingSameModelId || proposalImpactScenarioCombinationWrapper.
                            scenarioInputParameterAreDifferentThanAggregated(proposalScenarioId)) {
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
        } else{
            model.addAttribute("proposalToModelMap",
                    proposalImpactScenarioCombinationWrapper.getRegionToProposalSimulationScenarioMap());
        }

        model.addAttribute("isProposalUsingCombinedScenario", isProposalUsingCombinedScenario);
        model.addAttribute("consolidationPossible", isConsolidationPossible);
        model.addAttribute("consolidateOptions", getConsolidationOptions());

        return "integratedProposalImpact";
    }

    private Map<String, String[]> getConsolidationOptions() {
        return getConsolidateOptionsOnGlobalLevel();
    }

    private String showImpactTabBasic(PortletRequest request, Model model)
            throws PortalException, SystemException {


        try {
            List<ImpactIteration> impactIterations = ImpactTemplateClient.getContestImpactIterations(contest);
            model.addAttribute("impactIterations", impactIterations);

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
        try {

            org.xcolab.client.contest.pojo.Contest contestMicro = ContestClient.getContest(contest.getContestPK());
            ContestWrapper contestWrapper = new ContestWrapper(contestMicro);//contest
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
        }catch(ContestNotFoundException ignored){

        }
        return null;
    }


    private List<OntologyTerm> sortByName(Collection<OntologyTerm> collection) {
        List<OntologyTerm> list = new ArrayList<>(collection);
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
