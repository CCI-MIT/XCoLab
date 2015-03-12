package org.xcolab.portlets.proposals.view;

import com.ext.portlet.NoSuchImpactTemplateSeriesException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ImpactIteration;
import com.ext.portlet.model.OntologyTerm;
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
import org.xcolab.portlets.proposals.utils.ProposalImpactUtil;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalImpactSeriesList;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
        ProposalWrapper proposal = proposalsContext.getProposalWrapped(request);

        List<ImpactIteration> impactIterations = ContestLocalServiceUtil.getContestImpactIterations(contest);
        model.addAttribute("impactIterations", impactIterations);

        Map<OntologyTerm, List<OntologyTerm>> ontologyMap = ProposalImpactUtil.calculateOntologyMap(contest);
        model.addAttribute("sectorTerms", sortByName(ontologyMap.keySet()));

        // All filled out impact series
        List<ProposalAttribute> impactProposalAttributes =
                ProposalLocalServiceUtil.getImpactProposalAttributes(proposalsContext.getProposal(request));
        ProposalImpactSeriesList proposalImpactSeriesList = new ProposalImpactSeriesList(impactProposalAttributes, impactIterations);
        model.addAttribute("impactSerieses", proposalImpactSeriesList.getImpactDefaultSerieses());

        return "proposalImpact";
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
}
