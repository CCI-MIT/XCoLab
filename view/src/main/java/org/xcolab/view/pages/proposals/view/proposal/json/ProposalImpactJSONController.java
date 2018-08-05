package org.xcolab.view.pages.proposals.view.proposal.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ImpactClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.impact.ImpactIteration;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.enums.ImpactSeriesType;
import org.xcolab.client.proposals.enums.ProposalUnversionedAttributeName;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttribute;
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

    @GetMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/IMPACT/proposalImpactGetRegions")
    public void proposalImpactGetRegions(HttpServletResponse response,
            ProposalContext proposalContext) throws IOException {

        Map<OntologyTerm, List<OntologyTerm>> ontologyMap = getOntologyMap(proposalContext);
        List<OntologyTerm> regionTerms = new ArrayList<>(ontologyMap.keySet());

        response.getOutputStream().write(ontologyTermListToJSONArray(regionTerms).toString().getBytes());
    }


    @GetMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/IMPACT/proposalImpactGetSectorsForRegion")
    public void proposalImpactGetSectorForRegions(HttpServletResponse response,
            ProposalContext proposalContext,
            @RequestParam(value = "regionTermId") Long regionTermId) throws IOException {

        Map<OntologyTerm, List<OntologyTerm>> ontologyMap = getOntologyMap(proposalContext);

        List<OntologyTerm> sectorTerms = ontologyMap.get(OntologyClientUtil.getOntologyTerm(regionTermId));
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
            Contest contest = proposalContext.getContest();
            OntologyTerm sectorOntologyTerm = OntologyClientUtil.getOntologyTerm(sectorTermId);
            OntologyTerm regionOntologyTerm = OntologyClientUtil.getOntologyTerm(regionTermId);

            // ProposalImpactSeriesList impactSeriesList = getProposalImpactSeriesList(request);
            FocusArea selectedFocusArea = new ProposalImpactUtil(contest).getFocusAreaAssociatedWithTerms(sectorOntologyTerm, regionOntologyTerm);

            // Create a impact series with all data series for one sector-region pair
            ProposalImpactSeries impactSeries = new ProposalImpactSeries(contest, proposalContext.getProposal(), selectedFocusArea);

            response.getOutputStream().write(impactSeries.toJSONObject() .toString().getBytes());
        } catch (IOException e) {
            _log.error("Could not load impact series for contestId {}",
                    proposalContext.getContest().getContestPK(), e);
            JSONObject responseJSON = new JSONObject();
            responseJSON.put("success", false);
            response.getOutputStream().write(responseJSON.toString().getBytes());
        }
    }


    @PostMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/IMPACT/proposalImpactSaveDataSeries")
    public void proposalImpactSaveDataSeries(HttpServletRequest request,
            HttpServletResponse response, ProposalContext proposalContext, Member member,
            @RequestParam(value = "focusAreaId") Long focusAreaId) throws IOException {

        JSONObject responseJSON = new JSONObject();
        ProposalsPermissions permissions = proposalContext.getPermissions();

        if (!permissions.getCanEdit() && !permissions.getCanFellowActions() && !permissions.getCanIAFActions()) {
            responseJSON.put("success", false);
            response.getOutputStream().write(responseJSON.toString().getBytes());
            return;
        }

        FocusArea focusArea = OntologyClientUtil.getFocusArea(focusAreaId);
        Contest contest = proposalContext.getContest();

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
        final ProposalAttributeClient proposalAttributeClient =
                clients.getProposalAttributeClient();

        JSONObject responseJSON = new JSONObject();
        ProposalsPermissions permissions = proposalContext.getPermissions();

        if (!permissions.getCanEdit() && !permissions.getCanFellowActions() && !permissions.getCanIAFActions()) {
            responseJSON.put("success", false);
            response.getOutputStream().write(responseJSON.toString().getBytes());
            return;
        }

        Proposal proposal = proposalContext.getProposal();

        final List<ImpactIteration> iterations =
                ImpactClientUtil.getContestImpactIterations(proposalContext.getContest());
        final List<ProposalAttribute> impactAttributes = new ArrayList<>();
        for (ImpactIteration iteration : iterations) {
            impactAttributes.addAll(proposalAttributeClient
                    .getAllProposalAttributes(proposal.getProposalId(),
                            ImpactSeriesType.IMPACT_ADOPTION_RATE.getAttributeName(iteration.getYear()),
                            focusAreaId));
            impactAttributes.addAll(proposalAttributeClient
                    .getAllProposalAttributes(proposal.getProposalId(),
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
            HttpServletResponse response, ProposalContext proposalContext, Member member)
            throws IOException {

        JSONObject responseJSON = new JSONObject();
        ProposalsPermissions permissions = proposalContext.getPermissions();

        if (!permissions.getCanIAFActions() && !permissions.getCanAdminProposal()) {
            responseJSON.put("success", false);
            responseJSON.put("message", "You don't have the required permission to perform this action!");
            response.getOutputStream().write(responseJSON.toString().getBytes());
            return;
        }

        Proposal proposal = proposalContext.getProposal();
        Contest contest = proposalContext.getContest();
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
            HttpServletResponse response, ProposalContext proposalContext, Member currentMember,
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
        Proposal proposal = proposalContext.getProposal();

        List<ProposalUnversionedAttribute> unversionedAttributes = ProposalAttributeClientUtil
                .getProposalUnversionedAttributesByProposalId(proposal.getProposalId());

        if (impactAuthorComment != null || impactIAFComment != null) {
            final long userId = currentMember.getUserId();
            final ClientHelper clients = proposalContext.getClients();
            if (impactAuthorComment != null) {
                clients.getProposalAttributeClient().createOrUpdateUnversionedStringAttribute(
                        proposal.getProposalId(),
                        ProposalUnversionedAttributeName.IMPACT_AUTHOR_COMMENT.toString(), userId,
                        HtmlUtil.cleanAll(impactAuthorComment));
            }

            if (impactIAFComment != null) {
                clients.getProposalAttributeClient().createOrUpdateUnversionedStringAttribute(
                        proposal.getProposalId(),
                        ProposalUnversionedAttributeName.IMPACT_IAF_COMMENT.toString(), userId,
                        HtmlUtil.cleanAll(impactIAFComment));
            }
        }


        responseJSON.put("success", true);
        response.getOutputStream().write(responseJSON.toString().getBytes());
    }


    private JSONArray ontologyTermListToJSONArray(List<OntologyTerm> terms) {
        JSONArray array = new JSONArray();

        if (terms == null || terms.isEmpty()) {
            return array;
        }

        // Sort by order and id, which reflects the order in the outline view
        terms.sort((o1, o2) -> {
            if (o1.getOrder_() == o2.getOrder_().longValue()) {
                return (int) (o1.getId() - o2.getId());
            } else {
                return (o1.getOrder_() - o2.getOrder_());
            }

        });
        for (OntologyTerm term: terms) {
            JSONObject termJson = new JSONObject();
            termJson.put("id", term.getId());
            termJson.put("name", term.getName());
            array.put(termJson);
        }

        return array;
    }

    private Map<OntologyTerm, List<OntologyTerm>> getOntologyMap(ProposalContext proposalContext) {
        Contest contest = proposalContext.getContest();
        Proposal proposal = proposalContext.getProposal();

        ProposalImpactSeriesList proposalImpactSeriesList = new ProposalImpactSeriesList(contest, proposal);
        return new ProposalImpactUtil(contest).calculateAvailableOntologyMap(proposalImpactSeriesList.getImpactSerieses());
    }

    private ProposalImpactSeriesList getProposalImpactSeriesList(ProposalContext proposalContext) {
        Contest contest = proposalContext.getContest();
        Proposal proposal = proposalContext.getProposal();

        return new ProposalImpactSeriesList(contest, proposal.getWrapped());
    }
}
