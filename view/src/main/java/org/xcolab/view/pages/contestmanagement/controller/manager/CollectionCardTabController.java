package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.wrappers.CollectionCardWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class CollectionCardTabController extends AbstractTabController {

    static final private ContestManagerTabs tab = ContestManagerTabs.COLLECTION_CARDS;
    static final private String TAB_VIEW = "contestmanagement/manager/collectionCardTab";
    private static final int BY_TOPIC_COLLECTION_CARD_ID = 2;
    private static final int BY_LOCATION_COLLECTION_CARD_ID = 3;
    private static final int WHAT_ONTOLOGY_SPACE_ID = 103;
    private static final int WHERE_ONTOLOGY_SPACE_ID = 104;
    private static final long ROOT_ONTOLOGY_TERM_ID = 0;


    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }

    @GetMapping("tab/COLLECTION_CARDS")
    public String showCollectionCardTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper member,
            @RequestParam(required = false) String elementId) {
        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        model.addAttribute("collectionCardWrapperWhat",
                new CollectionCardWrapper(BY_TOPIC_COLLECTION_CARD_ID));
        model.addAttribute("collectionCardWrapperWhere",
                new CollectionCardWrapper(BY_LOCATION_COLLECTION_CARD_ID));

        List<org.xcolab.view.pages.contestmanagement.wrappers.OntologyTermWrapper> whatTerms = new ArrayList<>();
        List<org.xcolab.view.pages.contestmanagement.wrappers.OntologyTermWrapper> whereTerms = new ArrayList<>();
        for (OntologyTermWrapper term : ontologyClient.getChildOntologyTerms(ROOT_ONTOLOGY_TERM_ID)) {
            if (term.getOntologySpaceId() == WHAT_ONTOLOGY_SPACE_ID) {
                whatTerms.add(new org.xcolab.view.pages.contestmanagement.wrappers.OntologyTermWrapper(term));
            } else if (term.getOntologySpaceId() == WHERE_ONTOLOGY_SPACE_ID) {
                whereTerms.add(new org.xcolab.view.pages.contestmanagement.wrappers.OntologyTermWrapper(term));
            }
        }
        model.addAttribute("ontologyTermsWhat", whatTerms);
        model.addAttribute("ontologyTermsWhere", whereTerms);
        return TAB_VIEW;
    }

    @PostMapping("tab/COLLECTION_CARDS/update")
    public String updateCollectionCardController(HttpServletRequest request, Model model,
            UserWrapper member, @ModelAttribute CollectionCardWrapper collectionCardWrapper,
            BindingResult result, HttpServletResponse response) {
        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (result.hasErrors()) {
            AlertMessage.danger("An error occurred").flash(request);
            return "redirect:" + tab.getTabUrl(collectionCardWrapper.getId());
        }
        collectionCardWrapper.persist();
        return "redirect:" + tab.getTabUrl(collectionCardWrapper.getId());
    }

    @PostMapping("tab/COLLECTION_CARDS/delete")
    public String deleteCollectionCardController(HttpServletRequest request, UserWrapper member,
            @RequestParam long collectionCardId, HttpServletResponse response) {
        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        contestClient.deleteContestCollectionCard(collectionCardId);
        return "redirect:" + tab.getTabUrl();
    }
}
