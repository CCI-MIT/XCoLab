package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.taglibs.xcolab.controller.BaseTabController;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractTabController extends BaseTabController {

    static final String NO_PERMISSION_TAB_VIEW = "common/noPermissionTab";
    static final String NOT_FOUND_TAB_VIEW = "common/notFound";
    protected TabWrapper tabWrapper;

    @ModelAttribute("tabs")
    @Override
    public List<TabWrapper> populateTabs(Model model, HttpServletRequest request) {
        return getAllVisibleTabsWrapped(request, ContestManagerTabs.values());
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public abstract TabWrapper populateCurrentTabWrapped(HttpServletRequest request);

    public TabWrapper getTabWrapper() {
        return tabWrapper;
    }

    public void setTabWrapper(TabWrapper tabWrapper) {
        this.tabWrapper = tabWrapper;
    }

}
