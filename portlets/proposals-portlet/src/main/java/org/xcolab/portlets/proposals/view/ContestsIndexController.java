package org.xcolab.portlets.proposals.view;



import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PortalUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.portlets.proposals.utils.ContestsColumn;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ContestsSortFilterBean;
import org.xcolab.portlets.proposals.wrappers.FocusAreaWrapper;
import org.xcolab.portlets.proposals.wrappers.OntologySpaceWrapper;
import org.xcolab.portlets.proposals.wrappers.OntologyTermWrapper;
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
                Contest c = ContestClient.getContest(contestType.getSuggestionContestId());
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
        List<ContestWrapper> contests = new ArrayList<>();
        List<Contest> contestsToWrap = showAllContests ? ContestLocalServiceUtil.getContestsByContestType(contestType.getId_()) :
        	ContestLocalServiceUtil.getContestsByActivePrivateType(showActiveContests, false, contestType.getId_());
        List<Contest> priorContests = ContestLocalServiceUtil.getContestsByActivePrivateType(false, false,
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
                try {
                    org.xcolab.client.contest.pojo.Contest contestMicro = ContestClient.getContest(contest.getContestPK());
                    contests.add(new ContestWrapper(contestMicro));//contest
                }catch (ContestNotFoundException ignored){

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

        //PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();

        boolean showContestManagementLink = PermissionsClient
                .canAdminAll(proposalsContext.getUser(request).getUserId()) ; //permissionChecker.isOmniadmin();
        model.addAttribute("showContestManagementLink", showContestManagementLink);

        model.addAttribute("showContestDisplayOptions",
                ConfigurationAttributeKey.SHOW_CONTESTS_DISPLAY_OPTIONS.get());

        setSeoTexts(request, showAllContests ? "All contests" : showActiveContests ? "Active contests" : "Prior contests", null, null);
        
        if (viewType.equals(VIEW_TYPE_OUTLINE)) {
        	List<OntologySpace> ontologySpacesRaw = OntologySpaceLocalServiceUtil.getOntologySpaces(0, Integer.MAX_VALUE);
        	List<OntologyTerm> ontologyTermsRaw = OntologyTermLocalServiceUtil.getOntologyTerms(0, Integer.MAX_VALUE);
        	List<FocusArea> focusAreasRaw = FocusAreaLocalServiceUtil.getFocusAreas(0, Integer.MAX_VALUE);
        	List<FocusAreaOntologyTerm> focusAreasOntologyTermsRaw = FocusAreaOntologyTermLocalServiceUtil.getFocusAreaOntologyTerms(0, Integer.MAX_VALUE);
        	
        	Map<Long, FocusAreaWrapper> focusAreas = new TreeMap<>();
        	Map<Long, OntologySpaceWrapper> ontologySpaces = new HashMap<>();
        	Map<Long, OntologyTermWrapper> ontologyTerms = new TreeMap<>();
        	
        	for (FocusArea area: focusAreasRaw) {
        		focusAreas.put(area.getId(), new FocusAreaWrapper(area));
        	}
        	
        	for (OntologySpace space: ontologySpacesRaw) {
        		ontologySpaces.put(space.getId(), new OntologySpaceWrapper(space));
        	}
        	
        	for (OntologyTerm term: ontologyTermsRaw) {
        		OntologyTermWrapper termWrapped = new OntologyTermWrapper(term);
        		ontologySpaces.get(term.getOntologySpaceId()).addTerm(termWrapped);
        		ontologyTerms.put(term.getId(), termWrapped);
        	}

        	for (OntologyTerm term: ontologyTermsRaw) {
        		if (term.getParentId() > 0) {
        			ontologyTerms.get(term.getId()).setParent(ontologyTerms.get(term.getParentId()));
        		}
        	}
        	
        	for (FocusAreaOntologyTerm faTerm: focusAreasOntologyTermsRaw) {
        		focusAreas.get(faTerm.getFocusAreaId()).addOntologyTerm(ontologyTerms.get(faTerm.getOntologyTermId()));
        	}

            List<ContestWrapper> otherContests = new ArrayList<>();
            for (Contest contest: ContestLocalServiceUtil.getContestsByActivePrivate(!showActiveContests, false)) {
                try {
                    org.xcolab.client.contest.pojo.Contest contestMicro = ContestClient.getContest(contest.getContestPK());
                    otherContests.add(new ContestWrapper(contestMicro));//contest
                }catch (ContestNotFoundException ignored){

                }
            }
        	List<OntologySpaceWrapper> sortedSpaces = new ArrayList<>(ontologySpaces.values());
        	Collections.sort(sortedSpaces, new Comparator<OntologySpaceWrapper>() {

				@Override
				public int compare(OntologySpaceWrapper o1,
						OntologySpaceWrapper o2) {
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
