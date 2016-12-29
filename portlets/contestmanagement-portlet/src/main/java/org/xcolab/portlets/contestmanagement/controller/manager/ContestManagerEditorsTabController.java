package org.xcolab.portlets.contestmanagement.controller.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ContestManagerEditorsTabController extends ContestManagerBaseTabController {

    private final static Logger _log = LoggerFactory.getLogger(ContestManagerEditorsTabController.class);
    static final private TabEnum tab = ContestManagerTabs.EDITORS;

    static final private String TAB_VIEW = "manager/editorsTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }
    @RequestMapping(params = "tab=EDITORS")
    public String showEditorsTabController(PortletRequest request, PortletResponse response, Model model) {
        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }
        setPageAttributes(request, model, tab);
        return TAB_VIEW;
    }
}
