package org.xcolab.portlets.proposals.view;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PortalUtil;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.FocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.ontology.OntologySpace;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.portlets.proposals.utils.ContestsColumn;
import org.xcolab.portlets.proposals.utils.context.ClientHelper;
import org.xcolab.portlets.proposals.wrappers.ContestsSortFilterBean;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.Cookie;

@Controller
@RequestMapping("view")
public class ContestsIndexController extends BaseProposalsController {

    private static final Log _log = LogFactoryUtil.getLog(ContestsIndexController.class);

    private final static String COOKIE_VIEW_TYPE = "cc_contests_viewType";
    private final static String VIEW_TYPE_GRID = "GRID";
    private final static String VIEW_TYPE_LIST = "LIST";
    private final static String VIEW_TYPE_OUTLINE = "OUTLINE";
    private final static String VIEW_TYPE_DEFAULT = VIEW_TYPE_GRID;
    private static final int MIN_SIZE_CONTEST_FILTER = 9;

    @RequestMapping
    public String showContestsIndex(PortletRequest request, PortletResponse response, Model model,
            @RequestParam(required = false) String viewType, 
            @RequestParam(required = false, defaultValue="true") boolean showActiveContests,
            @RequestParam(required = false, defaultValue="false") boolean showAllContests,
            SortFilterPage sortFilterPage) 
                    throws PortalException, SystemException {

        ProposalsPreferencesWrapper preferences = new ProposalsPreferencesWrapper(request);
        ContestType contestType = preferences.getContestType();

        if (contestType.getSuggestionContestId() > 0) {
            try {
                Contest c = ContestClientUtil.getContest(contestType.getSuggestionContestId());
                String link = c.getContestLinkUrl();
                model.addAttribute("suggestionContestLink", link);
            }catch (ContestNotFoundException ignored){

            }
        }

        if (viewType == null) {
            // view type wasn't set
            final Cookie[] cookies = request.getCookies(); //null if cookies are disabled
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(COOKIE_VIEW_TYPE)) {
                        viewType = cookie.getValue();
                    }
                }
            }
        }
        else {
            // we need to change the view type
            if (viewType.equals(VIEW_TYPE_GRID) || viewType.equals(VIEW_TYPE_LIST) || viewType.equals(VIEW_TYPE_OUTLINE)) {
                // we should set the cookie but it doesn't work currently https://issues.liferay.com/browse/LPS-25733
                // it should be handled in the view
                response.addProperty(new Cookie(COOKIE_VIEW_TYPE, viewType));
            }
        }
        if (viewType == null) {
            viewType = VIEW_TYPE_DEFAULT;
        }
        List<Contest> contests = new ArrayList<>();
        List<Contest> contestsToWrap = showAllContests ? ContestClientUtil.getContestsByContestTypeId(contestType.getId_()) :
        	ContestClientUtil.getContestsByActivePrivateType(showActiveContests, false, contestType.getId_());
        List<Contest> priorContests = ContestClientUtil.getContestsByActivePrivateType(false, false,
                contestType.getId_());

        if (contestsToWrap.size() == 1) {
            final Contest contest = contestsToWrap.get(0);
            final String contestLinkUrl = (contest).getContestLinkUrl();
            try {
                PortalUtil.getHttpServletResponse(response).sendRedirect(contestLinkUrl);
                return "contestsIndex"; //won't be shown, but avoid null pointer exception during redirection
            } catch (IOException e) {
                _log.error("Failed to redirect to only contest in this contest type", e);
            }
        }

        for (Contest contest: contestsToWrap) {
        	if (! contest.getContestPrivate()) {
                if(contest.getIsSharedContestInForeignColab()){
                    ClientHelper ch = new ClientHelper(contest);
                    try {
                        Contest foreignContest =
                                ch.getContestClient().getContest(contest.getContestPK());
                        contests.add(foreignContest);

                    }catch (ContestNotFoundException notFound){

                    }
                }else {
                    contests.add((contest));
                }

            }
        }

        model.addAttribute("contests", contests);
        model.addAttribute("showFilter", contests.size() >= MIN_SIZE_CONTEST_FILTER);
        model.addAttribute("priorContestsExist", !priorContests.isEmpty());
        model.addAttribute("contestsSorted", new ContestsSortFilterBean(contests, sortFilterPage,
                showActiveContests ? null : ContestsColumn.REFERENCE_DATE));
        model.addAttribute("viewType", viewType);
        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("showActiveContests", showActiveContests);
        model.addAttribute("showAllContests", showAllContests);

        boolean showContestManagementLink = PermissionsClient
                .canAdminAll(proposalsContext.getMemberId(request)) ;
        model.addAttribute("showContestManagementLink", showContestManagementLink);

        model.addAttribute("showContestDisplayOptions",
                ConfigurationAttributeKey.SHOW_CONTESTS_DISPLAY_OPTIONS.get());

        setSeoTexts(request, showAllContests ? "All contests" : showActiveContests ? "Active contests" : "Prior contests", null, null);
        
        if (viewType.equals(VIEW_TYPE_OUTLINE)) {
        	List<OntologySpace> ontologySpacesRaw = OntologyClientUtil.getAllOntologySpaces();
        	List<OntologyTerm> ontologyTermsRaw = OntologyClientUtil.getAllOntologyTerms();
        	List<FocusArea> focusAreasRaw = OntologyClientUtil.getAllFocusAreas();
        	List<FocusAreaOntologyTerm> focusAreasOntologyTermsRaw = OntologyClientUtil.getAllFocusAreaOntologyTerms();
        	
        	Map<Long, FocusArea> focusAreas = new TreeMap<>();
        	Map<Long, OntologySpace> ontologySpaces = new HashMap<>();
        	Map<Long, OntologyTerm> ontologyTerms = new TreeMap<>();
        	
        	for (FocusArea area: focusAreasRaw) {
        		focusAreas.put(area.getId_(), (area));
        	}
        	
        	for (OntologySpace space: ontologySpacesRaw) {
        		ontologySpaces.put(space.getId_(), (space));
        	}
        	
        	for (OntologyTerm term: ontologyTermsRaw) {
        		OntologyTerm termWrapped = new OntologyTerm(term);
        		ontologySpaces.get(term.getOntologySpaceId()).addTerm(termWrapped);
        		ontologyTerms.put(term.getId_(), termWrapped);
        	}

        	for (OntologyTerm term: ontologyTermsRaw) {
        		if (term.getParentId() > 0) {
        			ontologyTerms.get(term.getId_()).setParent(ontologyTerms.get(term.getParentId()));
        		}
        	}
        	
        	for (FocusAreaOntologyTerm faTerm: focusAreasOntologyTermsRaw) {
        		focusAreas.get(faTerm.getFocusAreaId()).addOntologyTerm(ontologyTerms.get(faTerm.getOntologyTermId()));
        	}

            List<Contest> otherContests = new ArrayList<>();
            for (Contest contest: ContestClientUtil
                    .getContestsByActivePrivate(!showActiveContests, false)) {
                    otherContests.add(contest);//contest

            }
        	List<OntologySpace> sortedSpaces = new ArrayList<>(ontologySpaces.values());
        	Collections.sort(sortedSpaces, new Comparator<OntologySpace>() {

				@Override
				public int compare(OntologySpace o1,
						OntologySpace o2) {
					return o1.getOrder() - o2.getOrder();
				}
        		
        	});
        	model.addAttribute("focusAreas", focusAreas.values());
        	model.addAttribute("ontologyTerms", ontologyTerms.values());
        	model.addAttribute("ontologySpaces", sortedSpaces);
        	model.addAttribute("otherContests", otherContests);
        	model.addAttribute("contestType", contestType);
        }
        
        return "contestsIndex";
    }

}
