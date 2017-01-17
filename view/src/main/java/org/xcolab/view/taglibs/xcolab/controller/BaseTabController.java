package org.xcolab.view.taglibs.xcolab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.view.taglibs.xcolab.interfaces.TabContext;
import org.xcolab.view.taglibs.xcolab.interfaces.TabEnum;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@SessionAttributes("tabContext")
public abstract class BaseTabController extends BaseController {

    @Autowired
    protected TabContext tabContext;

    @ModelAttribute("tabContext")
    public TabContext getTabContext() {
        return tabContext;
    }

    @ModelAttribute("tabs")
    public abstract List<TabWrapper> populateTabs(Model model, HttpServletRequest request);

    @ModelAttribute("currentTabWrapped")
    public abstract TabWrapper populateCurrentTabWrapped(HttpServletRequest request);

    public abstract void setPageAttributes(HttpServletRequest request, Model model, TabEnum tab);

    public List<TabWrapper> getAllVisibleTabsWrapped(HttpServletRequest request, TabEnum[] Tabs) {

        List<TabWrapper> availableTabs = new ArrayList<>();
        for (TabEnum tab: Tabs) {
            TabWrapper tabWrapper = new TabWrapper(tab, request, tabContext);
            if (tabWrapper.getCanView() && (!tab.getName().equals("COLLECTION_CARDS") || ConfigurationAttributeKey.COLAB_USES_CARDS.get())) {
                availableTabs.add(tabWrapper);
            }
        }
        return availableTabs;
    }

    public static long getContestIdFromRequest(HttpServletRequest request){
        String contestIdParameter = request.getParameter("contestId");
        return Long.parseLong(contestIdParameter);
    }

}
