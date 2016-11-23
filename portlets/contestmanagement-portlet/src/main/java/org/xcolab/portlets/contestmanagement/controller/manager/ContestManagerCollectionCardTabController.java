package org.xcolab.portlets.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.CollectionCardWrapper;
import org.xcolab.wrapper.TabWrapper;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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

    @RequestMapping(params = "action=updateContestCollectionCard")
    public void updateCollectionCardController(ActionRequest request, Model model,
            @ModelAttribute CollectionCardWrapper collectionCardWrapper,
            BindingResult result, ActionResponse response) {
        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            return;
        }
        collectionCardWrapper.persist();
        SetRenderParameterUtil.addActionSuccessMessageToSession(request);
        try {
            response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tab.getName());
        } catch (IOException e) {
            _log.warn("Update CollectionCard failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = "action=deleteContestCollectionCard")
    public void deleteCollectionCardController(ActionRequest request,
            @RequestParam(required = true) String  collectionCardId, ActionResponse response) {
        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }
        ContestClientUtil.deleteContestCollectionCard(new Long(collectionCardId));
        SetRenderParameterUtil.addActionSuccessMessageToSession(request);
        try {
            response.sendRedirect("/web/guest/cms/-/contestmanagement/manager/tab/" + tab.getName());
        } catch (IOException e) {
            _log.warn("Update CollectionCard failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

}