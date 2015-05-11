package org.xcolab.portlets.proposals.view;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.ImpactDefaultSeries;
import com.ext.portlet.model.ImpactDefaultSeriesData;
import com.ext.portlet.model.ImpactIteration;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesDataLocalServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.ProposalImpactUtil;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalImpactSeries;
import org.xcolab.portlets.proposals.wrappers.ProposalImpactSeriesList;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by kmang on 12/03/15.
 */

@Controller
@RequestMapping("view")
public class ProposalImpactJSONController {

    private final static Log _log = LogFactoryUtil.getLog(ProposalImpactTabController.class);

    @Autowired
    private ProposalsContext proposalsContext;

    @ResourceMapping("proposalImpactGetRegions")
    public void proposalImpactGetRegions(
            ResourceRequest request,
            ResourceResponse response) throws IOException,
            SystemException, PortalException {

        Map<OntologyTerm, List<OntologyTerm>> ontologyMap = getOntologyMap(request);
        List<OntologyTerm> regionTerms = new ArrayList<>(ontologyMap.keySet());

        response.getPortletOutputStream().write(ontologyTermListToJSONArray(regionTerms).toString().getBytes());
    }

    @ResourceMapping("proposalImpactGetSectorsForRegion")
    public void proposalImpactGetSectorForRegions(
            ResourceRequest request,
            ResourceResponse response,
            @RequestParam(value = "regionTermId", required = true) Long regionTermId) throws IOException,
            SystemException, PortalException {

        Map<OntologyTerm, List<OntologyTerm>> ontologyMap = getOntologyMap(request);

        List<OntologyTerm> sectorTerms = ontologyMap.get(OntologyTermLocalServiceUtil.getOntologyTerm(regionTermId));
        response.getPortletOutputStream().write(ontologyTermListToJSONArray(sectorTerms).toString().getBytes());
    }

    @ResourceMapping("proposalImpactGetDataSeries")
    public void proposalImpactGetDataSeries(
            ResourceRequest request,
            ResourceResponse response,
            @RequestParam(value = "sectorTermId", required = true) Long sectorTermId,
            @RequestParam(value = "regionTermId", required = true) Long regionTermId) throws IOException,
            SystemException, PortalException {

        if (sectorTermId == 0 || regionTermId == 0) {
            return;
        }

        try {
            Contest contest = proposalsContext.getContest(request);
            OntologyTerm sectorOntologyTerm = OntologyTermLocalServiceUtil.getOntologyTerm(sectorTermId);
            OntologyTerm regionOntologyTerm = OntologyTermLocalServiceUtil.getOntologyTerm(regionTermId);

            // ProposalImpactSeriesList impactSeriesList = getProposalImpactSeriesList(request);
            FocusArea selectedFocusArea = new ProposalImpactUtil(contest).getFocusAreaAssociatedWithTerms(sectorOntologyTerm, regionOntologyTerm);

            // Create a impact series with all data series for one sector-region pair
            ProposalImpactSeries impactSeries = new ProposalImpactSeries(contest, proposalsContext.getProposal(request), selectedFocusArea);

            response.getPortletOutputStream().write(impactSeries.toJSONObject().toString().getBytes());
        } catch (Exception e) {
            _log.error("Could not load impact series for contestId " + proposalsContext.getContest(request).getContestPK(), e);
            JSONObject responseJSON = JSONFactoryUtil.createJSONObject();
            responseJSON.put("success", false);
            response.getPortletOutputStream().write(responseJSON.toString().getBytes());
        }
    }

    @ResourceMapping("proposalImpactSaveDataSeries")
    public void proposalImpactSaveDataSeries(
            ResourceRequest request,
            ResourceResponse response,
            @RequestParam(value = "focusAreaId", required = true) Long focusAreaId) throws IOException,
            SystemException, PortalException {


        JSONObject responseJSON = JSONFactoryUtil.createJSONObject();
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);

        if (!permissions.getCanEdit() && !permissions.getCanFellowActions() && !permissions.getCanIAFActions()) {
            responseJSON.put("success", false);
            response.getPortletOutputStream().write(responseJSON.toString().getBytes());
            return;
        }

        FocusArea focusArea = FocusAreaLocalServiceUtil.getFocusArea(focusAreaId);
        Contest contest = proposalsContext.getContest(request);
        List<ImpactIteration> impactIterations = ContestLocalServiceUtil.getContestImpactIterations(contest);

        JSONObject requestJson = JSONFactoryUtil.createJSONObject(request.getParameter("json"));
        try {
            ProposalImpactSeries impactSeries = new ProposalImpactSeries(contest, proposalsContext.getProposal(request), focusArea, requestJson);
            impactSeries.persistWithAuthor(proposalsContext.getUser(request));
        } catch (SystemException e) {
            _log.warn(e.getMessage(), e);
            responseJSON.put("success", false);
            response.getPortletOutputStream().write(responseJSON.toString().getBytes());
            return;
        }

        responseJSON.put("success", true);
        response.getPortletOutputStream().write(responseJSON.toString().getBytes());
    }

    @ResourceMapping("proposalImpactDeleteDataSeries")
    public void proposalImpactDeleteDataSeries(
            ResourceRequest request,
            ResourceResponse response,
            @RequestParam(value = "focusAreaId", required = true) Long focusAreaId) throws IOException,
            SystemException, PortalException {

        JSONObject responseJSON = JSONFactoryUtil.createJSONObject();
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);

        if (!permissions.getCanEdit() && !permissions.getCanFellowActions() && !permissions.getCanIAFActions()) {
            responseJSON.put("success", false);
            response.getPortletOutputStream().write(responseJSON.toString().getBytes());
            return;
        }

        FocusArea focusArea = FocusAreaLocalServiceUtil.getFocusArea(focusAreaId);
        Proposal proposal = proposalsContext.getProposal(request);

        for (ProposalAttribute proposalAttribute : ProposalLocalServiceUtil.getImpactProposalAttributes(proposal, focusArea)) {
            ProposalLocalServiceUtil.removeAttribute(proposalsContext.getUser(request).getUserId(), proposalAttribute);
        }

        responseJSON.put("success", true);
        response.getPortletOutputStream().write(responseJSON.toString().getBytes());
    }

    private JSONArray ontologyTermListToJSONArray(List<OntologyTerm> terms) {
        JSONArray array = JSONFactoryUtil.createJSONArray();

        if (Validator.isNull(terms)) {
            return array;
        }

        // Sort by order and id, which reflects the order in the outline view
        Collections.sort(terms, new Comparator<OntologyTerm>() {
            @Override
            public int compare(OntologyTerm o1, OntologyTerm o2) {
                if (o1.getOrder_() == o2.getOrder_()) {
                    return (int)(o1.getId() - o2.getId());
                } else {
                    return (int)(o1.getOrder_() - o2.getOrder_());
                }

            }
        });
        for (OntologyTerm term: terms) {
            JSONObject termJson = JSONFactoryUtil.createJSONObject();
            termJson.put("id", term.getId());
            termJson.put("name", term.getName());
            array.put(termJson);
        }

        return array;
    }



    private Map<OntologyTerm, List<OntologyTerm>> getOntologyMap(ResourceRequest request) throws SystemException, PortalException {
        Contest contest = proposalsContext.getContest(request);
        Proposal proposal = proposalsContext.getProposal(request);

        ProposalImpactSeriesList proposalImpactSeriesList = new ProposalImpactSeriesList(contest, proposal);
        return new ProposalImpactUtil(contest).calculateAvailableOntologyMap(proposalImpactSeriesList.getImpactSerieses());
    }

    private ProposalImpactSeriesList getProposalImpactSeriesList(ResourceRequest request) throws SystemException, PortalException {
        Contest contest = proposalsContext.getContest(request);
        ProposalWrapper proposal = proposalsContext.getProposalWrapped(request);

        return new ProposalImpactSeriesList(contest, proposal.getWrapped());
    }
}
