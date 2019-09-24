package org.xcolab.view.pages.proposals.view.proposal.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.IImpactClient;
import org.xcolab.client.contest.IOntologyClient;
import org.xcolab.client.contest.pojo.IImpactIteration;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalAttributeClient;
import org.xcolab.client.contest.proposals.enums.ImpactSeriesType;
import org.xcolab.client.contest.proposals.enums.ProposalUnversionedAttributeName;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.view.pages.proposals.exceptions.ProposalImpactDataParserException;
import org.xcolab.view.pages.proposals.impact.ProposalImpactDataParser;
import org.xcolab.view.pages.proposals.impact.ProposalImpactSeries;
import org.xcolab.view.pages.proposals.impact.ProposalImpactSeriesList;
import org.xcolab.view.pages.proposals.impact.ProposalImpactUtil;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ProposalImpactJSONController {

    private static final Logger _log = LoggerFactory.getLogger(ProposalImpactJSONController.class);

    @Autowired
    private IImpactClient impactClient;

    @Autowired
    private IOntologyClient ontologyClient;

    @Autowired
    private IProposalAttributeClient proposalAttributeClient;

    @GetMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/IMPACT/proposalImpactGetRegions")
    public void proposalImpactGetRegions(HttpServletResponse response,
            ProposalContext proposalContext) throws IOException {

        Map<OntologyTermWrapper, List<OntologyTermWrapper>> ontologyMap = getOntologyMap(proposalContext);
        List<OntologyTermWrapper> regionTerms = new ArrayList<>(ontologyMap.keySet());

        response.getOutputStream().write(ontologyTermListToJSONArray(regionTerms).toString().getBytes());
    }


    @GetMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/IMPACT/proposalImpactGetSectorsForRegion")
    public void proposalImpactGetSectorForRegions(HttpServletResponse response,
            ProposalContext proposalContext,
            @RequestParam(value = "regionTermId") Long regionTermId) throws IOException {

        Map<OntologyTermWrapper, List<OntologyTermWrapper>> ontologyMap = getOntologyMap(proposalContext);

        List<OntologyTermWrapper> sectorTerms = ontologyMap.get(ontologyClient.getOntologyTerm(regionTermId));
        response.getOutputStream().write(ontologyTermListToJSONArray(sectorTerms).toString().getBytes());
    }



    @GetMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/IMPACT/proposalImpactGetDataSeries")
    public void proposalImpactGetDataSeries(HttpServletRequest request,
            HttpServletResponse response, ProposalContext proposalContext,
            @RequestParam(value = "sectorTermId") Long sectorTermId,
            @RequestParam(value = "regionTermId") Long regionTermId) throws IOException {

        if (sectorTermId == 0 || regionTermId == 0) {
            return;
        }

        try {
            ContestWrapper contest = proposalContext.getContest();
            OntologyTermWrapper sectorOntologyTerm = ontologyClient.getOntologyTerm(sectorTermId);
            OntologyTermWrapper regionOntologyTerm = ontologyClient.getOntologyTerm(regionTermId);

            // ProposalImpactSeriesList impactSeriesList = getProposalImpactSeriesList(request);
            FocusAreaWrapper selectedFocusArea = new ProposalImpactUtil(contest).getFocusAreaAssociatedWithTerms(sectorOntologyTerm, regionOntologyTerm);

            // Create a impact series with all data series for one sector-region pair
            ProposalImpactSeries impactSeries = new ProposalImpactSeries(contest, proposalContext.getProposal(), selectedFocusArea);

            response.getOutputStream().write(impactSeries.toJSONObject() .toString().getBytes());
        } catch (IOException e) {
            _log.error("Could not load impact series for contestId {}",
                    proposalContext.getContest().getId(), e);
            JSONObject responseJSON = new JSONObject();
            responseJSON.put("success", false);
            response.getOutputStream().write(responseJSON.toString().getBytes());
        }
    }


    @PostMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/IMPACT/proposalImpactSaveDataSeries")
    public void proposalImpactSaveDataSeries(HttpServletRequest request,
            HttpServletResponse response, ProposalContext proposalContext, UserWrapper member,
            @RequestParam(value = "focusAreaId") Long focusAreaId) throws IOException {

        JSONObject responseJSON = new JSONObject();
        ProposalsPermissions permissions = proposalContext.getPermissions();

        if (!permissions.getCanEdit() && !permissions.getCanFellowActions() && !permissions.getCanIAFActions()) {
            responseJSON.put("success", false);
            response.getOutputStream().write(responseJSON.toString().getBytes());
            return;
        }

        FocusAreaWrapper focusArea = ontologyClient.getFocusArea(focusAreaId);
        ContestWrapper contest = proposalContext.getContest();

        JSONObject requestJson = new JSONObject(request.getParameter("json"));
        ProposalImpactSeries impactSeries = new ProposalImpactSeries(contest, proposalContext.getProposal(), focusArea, requestJson);
        impactSeries.persistWithAuthor(member);

        responseJSON.put("success", true);
        response.getOutputStream().write(responseJSON.toString().getBytes());
    }



    @PostMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/IMPACT/proposalImpactDeleteDataSeries")
    public void proposalImpactDeleteDataSeries(HttpServletRequest request,
            HttpServletResponse response, ProposalContext proposalContext,
            @RequestParam(value = "focusAreaId") long focusAreaId) throws IOException {

        final ClientHelper clients = proposalContext.getClients();
        final IProposalAttributeClient proposalAttributeClient =
                clients.getProposalAttributeClient();

        JSONObject responseJSON = new JSONObject();
        ProposalsPermissions permissions = proposalContext.getPermissions();

        if (!permissions.getCanEdit() && !permissions.getCanFellowActions() && !permissions.getCanIAFActions()) {
            responseJSON.put("success", false);
            response.getOutputStream().write(responseJSON.toString().getBytes());
            return;
        }

        ProposalWrapper proposal = proposalContext.getProposal();

        final List<IImpactIteration> iterations =
                impactClient.getContestImpactIterations(proposalContext.getContest());
        final List<ProposalAttribute> impactAttributes = new ArrayList<>();
        for (IImpactIteration iteration : iterations) {
            impactAttributes.addAll(proposalAttributeClient
                    .getAllProposalAttributes(proposal.getId(),
                            ImpactSeriesType.IMPACT_ADOPTION_RATE.getAttributeName(iteration.getYear()),
                            focusAreaId));
            impactAttributes.addAll(proposalAttributeClient
                    .getAllProposalAttributes(proposal.getId(),
                            ImpactSeriesType.IMPACT_REDUCTION.getAttributeName(iteration.getYear()),
                            focusAreaId));
        }

        for (ProposalAttribute proposalAttribute : impactAttributes) {
            proposalAttributeClient.deleteProposalAttribute(proposalAttribute.getId());
        }

        responseJSON.put("success", true);
        response.getOutputStream().write(responseJSON.toString().getBytes());
    }



    @PostMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/IMPACT/proposalImpactUpdateAllSeries")
    public void proposalImpactUpdateAllDataSeries(HttpServletRequest request,
            HttpServletResponse response, ProposalContext proposalContext, UserWrapper member)
            throws IOException {

        JSONObject responseJSON = new JSONObject();
        ProposalsPermissions permissions = proposalContext.getPermissions();

        if (!permissions.getCanIAFActions() && !permissions.getCanAdminProposal()) {
            responseJSON.put("success", false);
            responseJSON.put("message", "You don't have the required permission to perform this action!");
            response.getOutputStream().write(responseJSON.toString().getBytes());
            return;
        }

        ProposalWrapper proposal = proposalContext.getProposal();
        ContestWrapper contest = proposalContext.getContest();
        JSONObject requestJson = new JSONObject(request.getParameter("json"));

        try {
            ProposalImpactDataParser dataParser = new ProposalImpactDataParser(requestJson.getString("data"), proposal, contest);
            ProposalImpactSeriesList impactSeriesList = dataParser.parse();

            impactSeriesList.persistImpactSeriesesWithAuthor(member);

            responseJSON.put("success", true);
        } catch(ProposalImpactDataParserException e) {
            _log.info("Could not parse input", e);
            responseJSON.put("success", false);
            responseJSON.put("message", e.getMessage());
        }

        response.getOutputStream().write(responseJSON.toString().getBytes());
    }


    @PostMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/IMPACT/proposalImpactSaveBasicProposalComment")
    public void proposalImpactSaveBasicProposalComment(HttpServletRequest request,
            HttpServletResponse response, ProposalContext proposalContext, UserWrapper currentMember,
            @RequestParam(required = false) String impactAuthorComment,
            @RequestParam(required = false) String impactIAFComment)
            throws IOException {

        JSONObject responseJSON = new JSONObject();
        ProposalsPermissions permissions = proposalContext.getPermissions();

        if (!permissions.getCanEdit() && !permissions.getCanFellowActions() && !permissions.getCanIAFActions()) {
            responseJSON.put("success", false);
            response.getOutputStream().write(responseJSON.toString().getBytes());
            return;
        }
        ProposalWrapper proposal = proposalContext.getProposal();

        List<ProposalUnversionedAttribute> unversionedAttributes = proposalAttributeClient
                .getProposalUnversionedAttributesByProposalId(proposal.getId());

        if (impactAuthorComment != null || impactIAFComment != null) {
            final long userId = currentMember.getId();
            final ClientHelper clients = proposalContext.getClients();
            if (impactAuthorComment != null) {
                clients.getProposalAttributeClient().createOrUpdateUnversionedStringAttribute(
                        proposal.getId(),
                        ProposalUnversionedAttributeName.IMPACT_AUTHOR_COMMENT.toString(), userId,
                        HtmlUtil.cleanAll(impactAuthorComment));
            }

            if (impactIAFComment != null) {
                clients.getProposalAttributeClient().createOrUpdateUnversionedStringAttribute(
                        proposal.getId(),
                        ProposalUnversionedAttributeName.IMPACT_IAF_COMMENT.toString(), userId,
                        HtmlUtil.cleanAll(impactIAFComment));
            }
        }


        responseJSON.put("success", true);
        response.getOutputStream().write(responseJSON.toString().getBytes());
    }


    private JSONArray ontologyTermListToJSONArray(List<OntologyTermWrapper> terms) {
        JSONArray array = new JSONArray();

        if (terms == null || terms.isEmpty()) {
            return array;
        }

        // Sort by order and id, which reflects the order in the outline view
        terms.sort((o1, o2) -> {
            if (o1.getSortOrder() == o2.getSortOrder().longValue()) {
                return (int) (o1.getId() - o2.getId());
            } else {
                return (o1.getSortOrder() - o2.getSortOrder());
            }

        });
        for (OntologyTermWrapper term: terms) {
            JSONObject termJson = new JSONObject();
            termJson.put("id", term.getId());
            termJson.put("name", term.getName());
            array.put(termJson);
        }

        return array;
    }

    private Map<OntologyTermWrapper, List<OntologyTermWrapper>> getOntologyMap(ProposalContext proposalContext) {
        ContestWrapper contest = proposalContext.getContest();
        ProposalWrapper proposal = proposalContext.getProposal();

        ProposalImpactSeriesList proposalImpactSeriesList = new ProposalImpactSeriesList(contest, proposal);
        return new ProposalImpactUtil(contest).calculateAvailableOntologyMap(proposalImpactSeriesList.getImpactSerieses());
    }

    private ProposalImpactSeriesList getProposalImpactSeriesList(ProposalContext proposalContext) {
        ContestWrapper contest = proposalContext.getContest();
        ProposalWrapper proposal = proposalContext.getProposal();

        return new ProposalImpactSeriesList(contest, proposal);
    }
}
