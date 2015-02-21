package org.xcolab.controller;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.xcolab.interfaces.TabContext;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.List;

@SessionAttributes("tabContext")
public abstract class BaseTabController extends BaseController {

    @Autowired
    protected TabContext tabContext;

    @ModelAttribute("tabContext")
    public TabContext getTabContext() {
        return tabContext;
    }

    @ModelAttribute("tabs")
    public abstract List<TabWrapper> populateTabs(Model model, PortletRequest request) throws PortalException, SystemException;

    @ModelAttribute("currentTabWrapped")
    public abstract TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException;

    public abstract void setPageAttributes(PortletRequest request, Model model, TabEnum tab) throws PortalException, SystemException;

    public List<TabWrapper> getAllVisibleTabsWrapped(Model model, PortletRequest request, TabEnum[] ContestDetailsTabs) throws PortalException, SystemException{

        List<TabWrapper> availableTabs = new ArrayList<TabWrapper>();
        for (TabEnum tab: ContestDetailsTabs) {
            TabWrapper tabWrapper = new TabWrapper(tab, request, tabContext);
            if (tabWrapper.getCanView()) {
                availableTabs.add(tabWrapper);
            }
        }
        return availableTabs;
    }
}
