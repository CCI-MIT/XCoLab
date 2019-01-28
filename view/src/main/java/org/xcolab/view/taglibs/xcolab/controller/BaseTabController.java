package org.xcolab.view.taglibs.xcolab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.IOntologyClient;
import org.xcolab.client.contest.IProposalTemplateClient;
import org.xcolab.client.contest.proposals.IPointsClient;
import org.xcolab.view.taglibs.xcolab.interfaces.TabContext;
import org.xcolab.view.taglibs.xcolab.interfaces.TabEnum;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseTabController {

    @Autowired
    protected TabContext tabContext;

    @Autowired
    protected IContestTypeClient contestTypeClient;

    @Autowired
    protected IContestClient contestClient;

    @Autowired
    protected IOntologyClient ontologyClient;

    @Autowired
    protected IProposalTemplateClient proposalTemplateClient;

    @Autowired
    protected IPointsClient pointsClient;

    @ModelAttribute("tabContext")
    public TabContext getTabContext() {
        return tabContext;
    }

    @ModelAttribute("tabs")
    public abstract List<TabWrapper> populateTabs(Model model, HttpServletRequest request);

    @ModelAttribute("currentTabWrapped")
    public abstract TabWrapper populateCurrentTabWrapped(HttpServletRequest request);

    protected List<TabWrapper> getAllVisibleTabsWrapped(HttpServletRequest request,
            TabEnum[] Tabs) {

        List<TabWrapper> availableTabs = new ArrayList<>();
        for (TabEnum tab : Tabs) {
            TabWrapper tabWrapper = new TabWrapper(tab, request, tabContext);
            if (tabWrapper.getCanView() && (!tab.getName().equals("COLLECTION_CARDS")
                    || ConfigurationAttributeKey.COLAB_USES_CARDS.get())) {
                availableTabs.add(tabWrapper);
            }
        }
        return availableTabs;
    }
}
