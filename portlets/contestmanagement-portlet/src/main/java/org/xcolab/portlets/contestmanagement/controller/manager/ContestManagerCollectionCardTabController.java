package org.xcolab.portlets.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.portlets.contestmanagement.wrappers.CollectionCardWrapper;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ContestManagerCollectionCardTabController extends ContestManagerBaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestManagerCollectionCardTabController.class);
    static final private TabEnum tab = ContestManagerTabs.COLLECTION_CARDS;
    static final private String TAB_VIEW = "manager/collectionCardTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=COLLECTION_CARDS")
    public String showCollectionCardTabController(PortletRequest request, PortletResponse response, Model model,
            @RequestParam(required = false) String elementId) {
        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        model.addAttribute("collectionCardWrapperWhat", new CollectionCardWrapper(2));
        model.addAttribute("collectionCardWrapperWhere", new CollectionCardWrapper(3));
        setPageAttributes(request, model, tab);
        return TAB_VIEW;
    }

}