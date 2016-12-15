package org.xcolab.portlets.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.CollectionCardWrapper;
import org.xcolab.portlets.contestmanagement.wrappers.OntologyTermWrapper;
import org.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ContestManagerCollectionCardTabController extends ContestManagerBaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestManagerCollectionCardTabController.class);
    static final private TabEnum tab = ContestManagerTabs.COLLECTION_CARDS;
    static final private String TAB_VIEW = "manager/collectionCardTab";
    private static final int BY_TOPIC_COLLECTION_CARD_ID = 2;
    private static final int BY_LOCATION_COLLECTION_CARD_ID = 3;
    private static final int WHAT_ONTOLOGY_SPACE_ID = 103;
    private static final int WHERE_ONTOLOGY_SPACE_ID = 104;
    private static final long ROOT_ONTOLOGY_TERM_ID = 0;


    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=COLLECTION_CARDS")
    public String showCollectionCardTabController(PortletRequest request, PortletResponse response, Model model,
            @RequestParam(required = false) String elementId) {
        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        model.addAttribute("collectionCardWrapperWhat", new CollectionCardWrapper(BY_TOPIC_COLLECTION_CARD_ID));
        model.addAttribute("collectionCardWrapperWhere", new CollectionCardWrapper(BY_LOCATION_COLLECTION_CARD_ID));

        List<OntologyTermWrapper> whatTerms = new ArrayList<>();
        List<OntologyTermWrapper> whereTerms = new ArrayList<>();
        for(OntologyTerm term : OntologyClientUtil.getChildOntologyTerms(ROOT_ONTOLOGY_TERM_ID)) {
            if(term.getOntologySpaceId()==WHAT_ONTOLOGY_SPACE_ID) {
                whatTerms.add(new OntologyTermWrapper(term));
            } else if(term.getOntologySpaceId()==WHERE_ONTOLOGY_SPACE_ID){
                whereTerms.add(new OntologyTermWrapper(term));
            }
        }
        model.addAttribute("ontologyTermsWhat", whatTerms);
        model.addAttribute("ontologyTermsWhere", whereTerms);
        setPageAttributes(request, model, tab);
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestCollectionCard")
    public void updateCollectionCardController(ActionRequest request, Model model,
            @ModelAttribute CollectionCardWrapper collectionCardWrapper,
            BindingResult result, ActionResponse response) {
        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            SetRenderParameterUtil.setErrorRenderParameter(response, "updateContestCollectionCard");
            return;
        }
        collectionCardWrapper.persist();
        SetRenderParameterUtil.addActionSuccessMessageToSession(request);
        try {
            response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tab.getName());
        } catch (IOException e) {
            _log.warn("Update CollectionCard failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = "action=deleteContestCollectionCard")
    public void deleteCollectionCardController(ActionRequest request,
            @RequestParam(required = true) String  collectionCardId, ActionResponse response) {
        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }
        ContestClientUtil.deleteContestCollectionCard(new Long(collectionCardId));
        SetRenderParameterUtil.addActionSuccessMessageToSession(request);
        try {
            response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tab.getName());
        } catch (IOException e) {
            _log.warn("Update CollectionCard failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

}