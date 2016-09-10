package org.xcolab.portlets.contestmanagement.controller.manager;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.controller.BaseTabController;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.wrapper.TabWrapper;

import java.util.List;

import javax.portlet.PortletRequest;


public abstract class ContestManagerBaseTabController extends BaseTabController {

    protected TabWrapper tabWrapper;

    static final String NO_PERMISSION_TAB_VIEW = "common/noPermissionTab";
    static final String NOT_FOUND_TAB_VIEW = "common/notFound";

    @ModelAttribute("tabs")
    @Override
    public List<TabWrapper> populateTabs(Model model, PortletRequest request) throws PortalException, SystemException {
        return getAllVisibleTabsWrapped(request, ContestManagerTabs.values());
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public abstract TabWrapper populateCurrentTabWrapped(PortletRequest request)
            throws PortalException, SystemException;

    @Override
    public void setPageAttributes(PortletRequest request, Model model, TabEnum tab) {
        String pageTitle = "Contest Management Tool";
        String pageSubTitle = tab.getDisplayName() + " - " + pageTitle;
        String pageDescription = "Contest details for " + pageTitle;
        setSeoTexts(request, pageTitle, pageSubTitle, pageDescription);
    }

    public TabWrapper getTabWrapper() {
        return tabWrapper;
    }

    public void setTabWrapper(TabWrapper tabWrapper) {
        this.tabWrapper = tabWrapper;
    }

}
