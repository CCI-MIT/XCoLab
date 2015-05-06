package org.xcolab.portlets.contestmanagement.controller.manager;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.xcolab.controller.BaseTabController;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import java.util.List;


public abstract class ContestManagerBaseTabController extends BaseTabController {

    protected TabWrapper tabWrapper;

    static final String NO_PERMISSION_TAB_VIEW = "details/noPermissionTab";
    static final String NOT_FOUND_TAB_VIEW = "notFound";

    @ModelAttribute("tabs")
    @Override
    public List<TabWrapper> populateTabs(Model model, PortletRequest request) throws PortalException, SystemException {
        return getAllVisibleTabsWrapped(model, request, ContestManagerTabs.values());
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public abstract TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException;

    public void setPageAttributes(PortletRequest request, Model model, TabEnum tab)
            throws PortalException, SystemException {

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

    public void setSuccessRenderRedirect (ActionResponse response, String tabName) throws Exception{
        response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tabName);
    }

    public void setSuccessRenderRedirect (ActionResponse response, String tabName, Long elementId) throws Exception{
        response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tabName + "/elementId/" + elementId);
    }
}
