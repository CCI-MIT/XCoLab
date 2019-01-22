package org.xcolab.view.taglibs.xcolab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.OntologyClient;
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
    protected ContestClient contestClient;

    @Autowired
    protected OntologyClient ontologyClient;

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
