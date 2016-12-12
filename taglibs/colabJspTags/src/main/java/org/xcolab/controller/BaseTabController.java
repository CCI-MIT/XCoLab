package org.xcolab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.xcolab.interfaces.TabContext;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.wrapper.TabWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;

@SessionAttributes("tabContext")
public abstract class BaseTabController extends BaseController {

    @Autowired
    protected TabContext tabContext;

    @ModelAttribute("tabContext")
    public TabContext getTabContext() {
        return tabContext;
    }

    @ModelAttribute("tabs")
    public abstract List<TabWrapper> populateTabs(Model model, PortletRequest request);

    @ModelAttribute("currentTabWrapped")
    public abstract TabWrapper populateCurrentTabWrapped(PortletRequest request);

    public abstract void setPageAttributes(PortletRequest request, Model model, TabEnum tab);

    public List<TabWrapper> getAllVisibleTabsWrapped(PortletRequest request, TabEnum[] Tabs) {

        List<TabWrapper> availableTabs = new ArrayList<>();
        for (TabEnum tab: Tabs) {
            TabWrapper tabWrapper = new TabWrapper(tab, request, tabContext);
            if (tabWrapper.getCanView()) {
                availableTabs.add(tabWrapper);
            }
        }
        return availableTabs;
    }

    public static long getContestIdFromRequest(PortletRequest request){
        String contestIdParameter = request.getParameter("contestId");
        return Long.parseLong(contestIdParameter);
    }

}
