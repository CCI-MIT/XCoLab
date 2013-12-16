package org.xcolab.portlets.proposals.view;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.*;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.proposals.utils.ProposalPickerFilterUtil;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import java.util.*;

import javax.portlet.*;
import java.io.IOException;

/**
 * User: patrickhiesel
 * Date: 03/12/13
 * Time: 09:46
 */

@Controller
@RequestMapping("view")
public class ProposalPickerJSONController {

    private static int MAXCHARS_FOR_NAMES = 75;

    @Autowired
    private ProposalsContext proposalsContext;

    @ResourceMapping("proposalPicker")
    public void proposalPicker(ResourceRequest request, ResourceResponse response)
            throws IOException, SystemException, PortalException {

        String requestType = request.getParameter("type");
        String filterType = request.getParameter("filterKey");
        String filterText =  request.getParameter("filterText");
        int start = Integer.parseInt(request.getParameter("start"));
        int end = Integer.parseInt(request.getParameter("end"));
        String sortOrder =  request.getParameter("sortOrder");
        String sortColumn =  request.getParameter("sortColumn");
        String sectionId =  request.getParameter("sectionId");
        String user = request.getRemoteUser();

        List<Pair<Proposal, Date>> proposals = null;

        if (requestType.equalsIgnoreCase("subscriptions")){
            proposals = getFilteredSubscribedProposalsForUser(Long.parseLong(user), filterType, Long.parseLong(sectionId));
        } else if(requestType.equalsIgnoreCase("supporting")){
            proposals = getFilteredSupportingProposalsForUser(Long.parseLong(user),filterType,Long.parseLong(sectionId));
        } else if(requestType.equalsIgnoreCase("all")){
            proposals = getFilteredAllProposalsForUser(Long.parseLong(user),filterType,Long.parseLong(sectionId));
        }

        if (filterText != null && filterText.length() > 0) ProposalPickerFilterUtil.TEXTBASED.filter(proposals,filterText);
        int totalCount = proposals.size();

        sortList(sortOrder,sortColumn,proposals);

        if (proposals.size()>(end-start)) proposals = proposals.subList(start,end);

        response.getPortletOutputStream().write(getJSONObjectMapping(proposals,totalCount).getBytes());
    }

    /**
     * Methode is used to fill the counting bubbles for each tab
     * @param request
     * @param response
     */
    @ResourceMapping("proposalPickerCounter")
    public void proposalPickerCounter(ResourceRequest request, ResourceResponse response) throws IOException, SystemException, PortalException {
        String filterType = request.getParameter("filterKey");
        long sectionId =  Long.parseLong(request.getParameter("sectionId"));
        long userId = Long.parseLong(request.getRemoteUser());

        int numberOfSubscriptions = getFilteredSubscribedProposalsForUser(userId,filterType,sectionId).size();
        int numberOfSupporting = getFilteredSupportingProposalsForUser(userId,filterType,sectionId).size();
        int numberOfProposals = getFilteredAllProposalsForUser(userId,filterType,sectionId).size();
        JSONObject wrapper = JSONFactoryUtil.createJSONObject();
        wrapper.put("numberOfSubscriptions",numberOfSubscriptions);
        wrapper.put("numberOfSupporting",numberOfSupporting);
        wrapper.put("numberOfProposals",numberOfProposals);
        response.getPortletOutputStream().write(wrapper.toString().getBytes());
    }

    private String getJSONObjectMapping(List<Pair<Proposal,Date>> proposals, int totalNumberOfProposals) throws SystemException, PortalException {
        JSONObject wrapper = JSONFactoryUtil.createJSONObject();
        JSONArray proposalsJSON = JSONFactoryUtil.createJSONArray();


        for (Pair<Proposal,Date> p : proposals){
            JSONObject o = JSONFactoryUtil.createJSONObject();
            o.put("id",p.getLeft().getProposalId());
            o.put("proposalName", StringUtils.abbreviate(ProposalLocalServiceUtil.getAttribute(p.getLeft().getProposalId(), ProposalAttributeKeys.NAME, 0l).getStringValue(),MAXCHARS_FOR_NAMES));
            o.put("contestName",StringUtils.abbreviate(Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getLeft().getProposalId()).getContestName(),MAXCHARS_FOR_NAMES));
            o.put("contestId",Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getLeft().getProposalId()).getPrimaryKey());
            o.put("dateSubscribed",p.getRight().getTime());
            proposalsJSON.put(o);
        }

        wrapper.put("proposals", proposalsJSON);
        wrapper.put("totalCount", totalNumberOfProposals);
        return wrapper.toString();
    }

    private void sortList(String sortOrder, String sortColumn, List<Pair<Proposal,Date>> proposals) {
        if (sortColumn.equalsIgnoreCase("Contest")){
           Collections.sort(proposals,new Comparator<Pair<Proposal,Date>>() {
               @Override
               public int compare(Pair<Proposal,Date> o1, Pair<Proposal,Date> o2) {
                   try{
                       return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(o1.getLeft().getProposalId()).getContestName().compareTo(
                               Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(o2.getLeft().getProposalId()).getContestName()
                       );
                   } catch (Exception e){
                       return 0;
                   }
               }
           });
        } else if(sortColumn.equalsIgnoreCase("Proposal")){
            Collections.sort(proposals,new Comparator<Pair<Proposal,Date>>() {
                @Override
                public int compare(Pair<Proposal,Date> o1, Pair<Proposal,Date> o2) {
                    try{
                        return ProposalLocalServiceUtil.getAttribute(o1.getLeft().getProposalId(), ProposalAttributeKeys.NAME, 0l).getStringValue().compareTo(
                                ProposalLocalServiceUtil.getAttribute(o2.getLeft().getProposalId(), ProposalAttributeKeys.NAME, 0l).getStringValue()
                        );
                    } catch (Exception e){
                        return 0;
                    }
                }
            });
        }
        else if(sortColumn.equalsIgnoreCase("Date")){
            Collections.sort(proposals,new Comparator<Pair<Proposal,Date>>() {
                @Override
                public int compare(Pair<Proposal,Date> o1, Pair<Proposal,Date> o2) {
                    return o1.getRight().compareTo(o2.getRight());
                }
            });
        }


        if (sortOrder.equalsIgnoreCase("DESC")) Collections.reverse(proposals);
    }

    private List<Pair<Proposal, Date>> getFilteredSubscribedProposalsForUser(long userId, String filterKey, long sectionId) throws SystemException, PortalException  {
        List<Pair<Proposal, Date>> proposals = new ArrayList<>();
        List<ActivitySubscription> activitySubscriptions = ActivitySubscriptionLocalServiceUtil.findByUser(userId);
        for (ActivitySubscription as : activitySubscriptions) {
            if (as.getClassNameId() == ClassNameLocalServiceUtil.getClassNameId(Proposal.class)) {
                proposals.add(Pair.of(ProposalLocalServiceUtil.getProposal(as.getClassPK()), as.getCreateDate()));
            }
        }
        ProposalPickerFilterUtil.getFilterByParameter(filterKey).filter(proposals);
        ProposalPickerFilterUtil.ONTOLOGY.filter(proposals,sectionId);
        return proposals;
    }

    private List<Pair<Proposal, Date>> getFilteredSupportingProposalsForUser(long userId, String filterKey, long sectionId) throws SystemException, PortalException  {
        List<Pair<Proposal, Date>> proposals = new ArrayList<>();
        for (ProposalSupporter ps : ProposalSupporterLocalServiceUtil.getProposals(userId)){
            proposals.add(Pair.of(ProposalLocalServiceUtil.getProposal(ps.getProposalId()),ps.getCreateDate()));
        }
        ProposalPickerFilterUtil.getFilterByParameter(filterKey).filter(proposals);
        ProposalPickerFilterUtil.ONTOLOGY.filter(proposals,sectionId);
        return proposals;
    }

    private List<Pair<Proposal, Date>> getFilteredAllProposalsForUser(long userId, String filterKey, long sectionId) throws SystemException, PortalException  {
        List<Pair<Proposal, Date>> proposals = new ArrayList<>();
        for (Proposal p : ProposalLocalServiceUtil.getProposals(0,Integer.MAX_VALUE)){
            proposals.add(Pair.of(p,new Date(0)));
        }
        ProposalPickerFilterUtil.getFilterByParameter(filterKey).filter(proposals);
        ProposalPickerFilterUtil.ONTOLOGY.filter(proposals,sectionId);
        return proposals;
    }


}
