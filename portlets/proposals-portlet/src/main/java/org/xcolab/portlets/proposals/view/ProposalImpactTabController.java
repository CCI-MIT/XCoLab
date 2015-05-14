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
import org.xcolab.enums.ContestTier;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.ProposalImpactUtil;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.IntegratedProposalImpactSeries;
import org.xcolab.portlets.proposals.wrappers.ProposalImpactSeriesList;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
    public String showImpactTab(PortletRequest request, Model model)
            throws PortalException, SystemException {

        Contest contest = proposalsContext.getContest(request);
        setCommonModelAndPageAttributes(request, model, ProposalTab.IMPACT);

        try {
            List<ImpactIteration> impactIterations = ContestLocalServiceUtil.getContestImpactIterations(contest);
            model.addAttribute("impactIterations", impactIterations);
        } catch (PortalException e) {
            // No impact iteration associated with the contest -> return default view
            return "proposalImpactError";
        }


        switch (ContestTier.getContestTierByTierType(contest.getContestTier())) {
            case BASIC:
                return showImpactTabBasicProposal(request, model);
            case REGION_SECTOR:
            case REGION_AGGREGATE:
            case GLOBAL:
                // return showImpactTabIntegratedProposal(request, model);
            default:
                return "proposalImpactError";
        }
    }

    private String showImpactTabIntegratedProposal(PortletRequest request, Model model)
            throws PortalException, SystemException {

        if (!hasImpactTabPermission(request)) {
            return "proposalImpactError";
        }

        Proposal proposal = proposalsContext.getProposal(request);
        IntegratedProposalImpactSeries integratedProposalImpactSeries = new IntegratedProposalImpactSeries(proposal);
        model.addAttribute("impactSeries", integratedProposalImpactSeries);

        return "integratedProposalImpact";
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
}
