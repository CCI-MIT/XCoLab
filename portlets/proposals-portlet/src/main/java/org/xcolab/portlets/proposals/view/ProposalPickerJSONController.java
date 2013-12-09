package org.xcolab.portlets.proposals.view;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.service.*;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.proposals.utils.ProposalPickerFilterUtil;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalSupporter;

import java.util.*;

import javax.portlet.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 03/12/13
 * Time: 09:46
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("view")
public class ProposalPickerJSONController {

    @Autowired
    private ProposalsContext proposalsContext;

    @ResourceMapping("mySubscriptions")
    public void ajaxHandler(ResourceRequest request, ResourceResponse response)
            throws IOException, SystemException, PortalException {

        String requestType = request.getParameter("type");
        String filterType = request.getParameter("filterKey");
        String filterText =  request.getParameter("filterText");
        int start = Integer.parseInt(request.getParameter("start"));
        int end = Integer.parseInt(request.getParameter("end"));
        String sortOrder =  request.getParameter("sortOrder");
        String sortColumn =  request.getParameter("sortColumn");

        String user = request.getRemoteUser();

        List<Proposal> proposals = new ArrayList<>();

        if (requestType.equalsIgnoreCase("subscriptions")){
            List <ActivitySubscription> activitySubscriptions = ActivitySubscriptionLocalServiceUtil.findByUser(Long.parseLong(user));
            for (ActivitySubscription as : activitySubscriptions){
                if (as.getClassNameId() == ClassNameLocalServiceUtil.getClassNameId(Proposal.class)){
                    proposals.add(ProposalLocalServiceUtil.getProposal(as.getClassPK()));
                }
            }
        } else if(requestType.equalsIgnoreCase("supporting")){
            for (ProposalSupporter ps : ProposalSupporterLocalServiceUtil.getProposals(Long.parseLong(user))){
                proposals.add(ProposalLocalServiceUtil.getProposal(ps.getProposalId()));
            }
        } else if(requestType.equalsIgnoreCase("all")){
            proposals = ProposalLocalServiceUtil.getProposals(0,Integer.MAX_VALUE);
        }

        proposals = ProposalPickerFilterUtil.getFilterByParameter(filterType).filter(proposals);
        if (filterText != null && filterText.length() > 0) proposals = ProposalPickerFilterUtil.TEXTBASED.filter(proposals,filterText);
        int totalCount = proposals.size();

        sortList(sortOrder,sortColumn,proposals);

        proposals = getPartition(proposals,start,end);

        int numberOfSubscriptions = ActivitySubscriptionLocalServiceUtil.findByUser(Long.parseLong(user)).size();
        int numberOfSupporting = ProposalSupporterLocalServiceUtil.getProposals(Long.parseLong(user)).size();
        int numberOfProposals = ProposalLocalServiceUtil.getProposalsCount();

        response.getPortletOutputStream().write(getJSONObjectMapping(proposals,totalCount,numberOfSubscriptions,numberOfSupporting,numberOfProposals).getBytes());
    }

    private String getJSONObjectMapping(List<?> list, int totalNumberOfProposals, int numberOfSubscriptions, int numberOfSupporting, int numberOfProposals) throws SystemException, PortalException {
        JSONObject wrapper = JSONFactoryUtil.createJSONObject();
        JSONArray proposalsJSON = JSONFactoryUtil.createJSONArray();

        if (list.get(0) instanceof Proposal){
            List<Proposal> proposals = (List<Proposal>) list;
            for (Proposal p : proposals){
                JSONObject o = JSONFactoryUtil.createJSONObject();
                o.put("id",p.getProposalId());
                o.put("proposalName", ProposalLocalServiceUtil.getAttribute(p.getProposalId(), ProposalAttributeKeys.NAME, 0l).getStringValue());
                o.put("contestName",Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getProposalId()).getContestName());
                o.put("dateSubscribed",new Date().getTime());
                proposalsJSON.put(o);
            }
        }
        wrapper.put("proposals", proposalsJSON);
        wrapper.put("totalCount", totalNumberOfProposals);
        wrapper.put("numberOfSubscriptions",numberOfSubscriptions);
        wrapper.put("numberOfSupporting",numberOfSupporting);
        wrapper.put("numberOfProposals",numberOfProposals);
        return wrapper.toString();
    }

    private List<Proposal> getPartition(List<Proposal> proposals, int start, int end){
        List<Proposal> partitionedProposals = new ArrayList<>();
        int elementCounter = 0;
        int offsetCounter = 0;
        for (Proposal p : proposals){
              if (start > offsetCounter){
                  offsetCounter++;
                  continue;
              }
            elementCounter++;
            if (elementCounter > (end-start)) break;
            partitionedProposals.add(p);
        }
        return partitionedProposals;
    }


    private void sortList(String sortOrder, String sortColumn, List<Proposal> proposals) {
        if (sortColumn.equalsIgnoreCase("Contest")){
           Collections.sort(proposals,new Comparator<Proposal>() {
               @Override
               public int compare(Proposal o1, Proposal o2) {
                   try{
                       return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(o1.getProposalId()).getContestName().compareTo(
                               Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(o2.getProposalId()).getContestName()
                       );
                   } catch (Exception e){
                       return 0;
                   }
               }
           });
        } else if(sortColumn.equalsIgnoreCase("Proposal")){
            Collections.sort(proposals,new Comparator<Proposal>() {
                @Override
                public int compare(Proposal o1, Proposal o2) {
                    try{
                        return ProposalLocalServiceUtil.getAttribute(o1.getProposalId(), ProposalAttributeKeys.NAME, 0l).getStringValue().compareTo(
                                ProposalLocalServiceUtil.getAttribute(o2.getProposalId(), ProposalAttributeKeys.NAME, 0l).getStringValue()
                        );
                    } catch (Exception e){
                        return 0;
                    }
                }
            });
        }


        if (sortOrder.equalsIgnoreCase("DESC")) Collections.reverse(proposals);
    }
}
