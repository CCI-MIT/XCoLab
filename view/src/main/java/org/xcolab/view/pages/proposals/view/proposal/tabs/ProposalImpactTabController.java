package org.xcolab.view.pages.proposals.view.proposal.tabs;

import edu.mit.cci.roma.client.comm.ModelNotFoundException;
import edu.mit.cci.roma.client.comm.ScenarioNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.IImpactIteration;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalAttributeClient;
import org.xcolab.client.contest.proposals.enums.ProposalUnversionedAttributeName;
import org.xcolab.client.contest.proposals.helpers.ProposalUnversionedAttributeHelper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.util.enums.contest.ContestTier;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.impact.IntegratedProposalImpactSeries;
import org.xcolab.view.pages.proposals.impact.ProposalImpactScenarioCombinationWrapper;
import org.xcolab.view.pages.proposals.impact.ProposalImpactSeries;
import org.xcolab.view.pages.proposals.impact.ProposalImpactSeriesList;
import org.xcolab.view.pages.proposals.impact.ProposalImpactUtil;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalImpactTabController extends BaseProposalTabController {

    private static final Logger _log = LoggerFactory.getLogger(ProposalImpactTabController.class);

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=IMPACT")
    public String showImpactTab(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper currentMember,
            @PathVariable Long contestYear, @PathVariable String contestUrlName,
            @PathVariable Long proposalId, @RequestParam(defaultValue = "false") boolean edit)
            throws IOException, ScenarioNotFoundException, ModelNotFoundException  {

        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanView()) {
            return new AccessDeniedPage(currentMember).toViewName(response);
        }

        ContestWrapper contest = proposalContext.getContest();
        ProposalWrapper proposal = proposalContext.getProposal();
        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.IMPACT);

        boolean userAllowedToEdit = false;

        if (edit) {
            userAllowedToEdit = canEditImpactTab(proposalContext);
            boolean userCanCommentAsAuthor = canCommentAsAuthor(proposalContext);
            model.addAttribute("canCommentAsAuthor", userCanCommentAsAuthor);
            boolean userCanCommentAsAIF = canCommentAsIAF(proposalContext);
            model.addAttribute("canCommentAsIAF", userCanCommentAsAIF);
        }

        final ClientHelper clients = proposalContext.getClients();
        ProposalUnversionedAttributeHelper unversionedAttributeHelper =
                new ProposalUnversionedAttributeHelper(proposal,
                        clients.getProposalAttributeClient());

        final String authorComment = proposal.getImpactCommentAuthor();
        if (StringUtils.isNotBlank(authorComment)) {
            model.addAttribute("authorComment", authorComment);
        }
        final String iafComment = proposal.getImpactCommentIaf();
        if (StringUtils.isNotBlank(iafComment)) {
            model.addAttribute("iafComment", iafComment);
        }

        model.addAttribute("edit", userAllowedToEdit);
        model.addAttribute("isRegionalSectorContest", isRegionalSectorContest(contest));
        model.addAttribute("isRegionalContest", isRegionalContest(contest));
        model.addAttribute("isGlobalContest", isGlobalContest(contest));

        boolean tabUsesModeling = (isRegionalContest(contest) || isGlobalContest(contest));
        if (tabUsesModeling){
            model.addAttribute("availableModels", contestClient.getModelIdsAndNames(contest.getId()));
            model.addAttribute("modelId", getModelIdIfProposalHasScenarioIdOrContestDefaultModelId(
                    proposal));
            model.addAttribute("scenarioId", proposal.getScenarioId());
        }

        boolean showSubProposalListing = (isRegionalContest(contest));
        boolean showDataTable = (isRegionalContest(contest));
        if (showDataTable) {
            IntegratedProposalImpactSeries integratedProposalImpactSeries =
                    new IntegratedProposalImpactSeries(proposalContext, proposal, contest);
            model.addAttribute("impactSeries", integratedProposalImpactSeries);
            List<IImpactIteration> impactIterations = impactClient.getContestImpactIterations(contest);
            model.addAttribute("impactIterations", impactIterations);
        }

        if (showSubProposalListing) {
            model.addAttribute("impactSerieses", getImpactTabBasicProposal(proposalContext,
                    proposal, contest));
        }
        model.addAttribute("showSubProposalListing", showSubProposalListing);
        model.addAttribute("showDataTable", showDataTable);

        final ContestTier contestTier = ContestTier.getContestTierByTierType(contest.getContestTier());
        if (contestTier != null) {
            switch (contestTier) {
                case BASIC:
                    return showImpactTabBasic(model, proposalContext, contest, proposal);
                case REGION_SECTOR:
                    //return showImpactTabRegionSector();
                    return "proposalImpactError";
                case REGION_AGGREGATE:
                    return showImpactTabRegionAggregate();
                case GLOBAL:
                    if(userAllowedToEdit) {
                        return showImpactTabEditGlobal(model, proposalContext, proposal);
                    } else {
                        return showImpactTabGlobal();
                    }
                default:
            }
        }
        _log.warn("Using default impact tab view since contest tier is not set for contest: {}",
                contest.getId());
        return "/proposals/proposalImpactError";
    }

    private Long getModelIdIfProposalHasScenarioIdOrContestDefaultModelId(
            ProposalWrapper proposalWrapper) {
        Long modelId = proposalWrapper.getModelId();
        boolean scenarioIdValid =
               proposalWrapper.getScenarioId() != null && proposalWrapper.getScenarioId() > 0;
        if(scenarioIdValid){
            try {
                modelId = RomaClientUtil.client()
                        .getScenario(proposalWrapper.getScenarioId()).getSimulation().getId();
            } catch (IOException e){
                _log.warn("Could not fetch simulation id for proposal scenario: ", e);
            }
        }
        return modelId;
    }

    private boolean canCommentAsAuthor(ProposalContext proposalContext) {
        return proposalContext.getPermissions().getCanAdminProposal();
    }

    private boolean canCommentAsIAF(ProposalContext proposalContext) {
        return proposalContext.getPermissions().getCanIAFActions()
                || proposalContext.getPermissions().getCanAdminAll();
    }

    private boolean canEditImpactTab(ProposalContext proposalContext) {
        return ProposalTab.IMPACT.getCanEdit(proposalContext);
    }

    private String showImpactTabRegionSector() {
        return "/proposals/integratedProposalImpact";
    }

    private String showImpactTabRegionAggregate() {
        return "/proposals/integratedProposalImpact";
    }

    private String showImpactTabGlobal() {
        return "/proposals/integratedProposalImpact";
    }

    private String showImpactTabEditGlobal(Model model, ProposalContext proposalContext,
            ProposalWrapper proposal)
            throws IOException, ScenarioNotFoundException, ModelNotFoundException {

        List<ProposalWrapper> subProposals =
                proposalContext.getClients().getProposalClient().getContestIntegrationRelevantSubproposals(proposal.getId());
        ProposalImpactScenarioCombinationWrapper proposalImpactScenarioCombinationWrapper =
                new ProposalImpactScenarioCombinationWrapper(subProposals);
        boolean isConsolidationPossible =
                proposalImpactScenarioCombinationWrapper.isConsolidationOfScenariosPossible();
        boolean isProposalUsingCombinedScenario = false;

        if (isConsolidationPossible) {
            boolean isValidScenario = (proposal.getScenarioId() != null && proposal.getScenarioId() != 0);
            if (isValidScenario) {
                Long proposalScenarioId = proposal.getScenarioId();
                boolean isScenarioUsingSameModelId = proposalImpactScenarioCombinationWrapper.isScenarioUsingSameModelId(proposalScenarioId);
                if (proposal.isConsolidatedScenario(proposalScenarioId)){
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

        return "/proposals/integratedProposalImpact";
    }

    private Map<String, String[]> getConsolidationOptions() {
        return getConsolidateOptionsOnGlobalLevel();
    }

    private String showImpactTabBasic(Model model, ProposalContext proposalContext,
            ContestWrapper contest, ProposalWrapper proposal) {

        List<IImpactIteration> impactIterations = impactClient.getContestImpactIterations(contest);
        model.addAttribute("impactIterations", impactIterations);

        ProposalImpactSeriesList proposalImpactSeriesList =
                new ProposalImpactSeriesList(contest, proposal);
        model.addAttribute("impactSerieses", proposalImpactSeriesList.getImpactSerieses());

        Map<OntologyTermWrapper, List<OntologyTermWrapper>> ontologyMap =
                new ProposalImpactUtil(contest).calculateAvailableOntologyMap(proposalImpactSeriesList.getImpactSerieses());
        model.addAttribute("regionTerms", sortByName(ontologyMap.keySet()));
        model.addAttribute("proposalsPermissions", proposalContext.getPermissions());

        return "/proposals/basicProposalImpact";
    }

    private List<ProposalImpactSeries> getImpactTabBasicProposal(ProposalContext proposalContext,
            ProposalWrapper proposalParent, ContestWrapper contest) {
        Set<ProposalWrapper> referencedSubProposals =
                IntegratedProposalImpactSeries.getSubProposalsOnContestTier(proposalContext,
                        proposalParent,
                        ContestTier.BASIC.getTierType());
        try {
            List<OntologyTermWrapper> ontologyTermList = contest.getWhere();
            List<ProposalImpactSeries> proposalImpactSerieses = new ArrayList<>();
            for (ProposalWrapper proposal : referencedSubProposals) {
                ProposalImpactSeriesList proposalImpactSeriesList = new ProposalImpactSeriesList(
                        contest, proposal);
                for (ProposalImpactSeries proposalImpactSeries : proposalImpactSeriesList.getImpactSerieses()) {
                    if (proposalImpactSeries.getWhereTerm().equals(ontologyTermList.get(0))) {
                        proposalImpactSerieses.add(proposalImpactSeries);
                    }
                }
            }
            return proposalImpactSerieses;
        } catch(ContestNotFoundException ignored) {

        }
        return null;
    }

    private List<OntologyTermWrapper> sortByName(Collection<OntologyTermWrapper> collection) {
        List<OntologyTermWrapper> list = new ArrayList<>(collection);
        list.sort(Comparator.comparing(o -> o.getName()));
        return list;
    }

    private boolean isGlobalContest(ContestWrapper contest) {
        return contest.getContestTier() == ContestTier.GLOBAL.getTierType();
    }

    private boolean isRegionalContest(ContestWrapper contest) {
        return contest.getContestTier() == ContestTier.REGION_AGGREGATE.getTierType();
    }

    private boolean isRegionalSectorContest(ContestWrapper contest) {
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

    @PostMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=IMPACT")
    public String saveImpactTab(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper currentMember,
            @PathVariable Long contestYear, @PathVariable String contestUrlName,
            @PathVariable Long proposalId,
            @RequestParam long scenarioId,
            @RequestParam long modelId,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String impactAuthorComment,
            @RequestParam(required = false) String impactIAFComment,
            @RequestParam(required = false) Boolean isConsolidatedScenario)
            throws ProposalsAuthorizationException, IOException, ScenarioNotFoundException,
            ModelNotFoundException {

        if (proposalContext.getProposal() != null && !canEditImpactTab(proposalContext)) {
            return new AccessDeniedPage(currentMember).toViewName(response);
        }

        ProposalWrapper proposal = proposalContext.getProposal();
        Long consolidatedScenario = isConsolidatedScenario != null && isConsolidatedScenario ? 1L : 0L;
        proposal.setScenarioId(scenarioId, consolidatedScenario, currentMember.getId());

        if (StringUtils.isNotBlank(region)) {
            proposal.setModelRegion(region, currentMember.getId());
        }

        IProposalAttributeClient proposalAttributeClient = proposalContext.getClients()
                .getProposalAttributeClient();

        if (impactAuthorComment != null || impactIAFComment != null) {
            if (impactAuthorComment != null) {

                proposalAttributeClient.createOrUpdateUnversionedStringAttribute(
                        proposal.getId(),
                        ProposalUnversionedAttributeName.IMPACT_AUTHOR_COMMENT.toString(),
                        currentMember.getId(),
                        HtmlUtil.cleanAll(impactAuthorComment));
            }
            if (impactIAFComment != null) {
                proposalAttributeClient.createOrUpdateUnversionedStringAttribute(
                        proposal.getId(),
                        ProposalUnversionedAttributeName.IMPACT_IAF_COMMENT.toString(),
                        currentMember.getId(),
                        HtmlUtil.cleanAll(impactIAFComment));
            }
        }
        AlertMessage.CHANGES_SAVED.flash(request);
        return showImpactTab(request, response, model, proposalContext, currentMember, contestYear,
                contestUrlName, proposalId, false);
    }
}
