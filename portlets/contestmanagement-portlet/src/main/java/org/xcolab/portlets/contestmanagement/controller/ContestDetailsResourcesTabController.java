package org.xcolab.portlets.contestmanagement.controller;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestWrapper;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.wiki.NoSuchPageException;
import com.liferay.portlet.wiki.NoSuchPageResourceException;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageResourceLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.beans.ContestResourcesBean;
import org.xcolab.portlets.contestmanagement.views.ContestDetailsTabs;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.List;

/**
 * Created by Thomas on 2/13/2015.
 */

@Controller
@RequestMapping("view")
public class ContestDetailsResourcesTabController extends ContestDetailsBaseTabController {

    @Autowired
    private Validator validator;
    static final private TabEnum tab = ContestDetailsTabs.RESOURCES;
    static final private String TAB_VIEW = "details/resourcesTab";

    private WikiPage wikiPage;
    private WikiPageResource wikiPageResource;
    private String contestTitle;

    final static Long WIKI_NODE_ID = 18855L;
    final static Long WIKI_GROUP_ID = 10136L;
    final static Long DEFAULT_COMPANY_ID = 10112L;
    final static Long WIKI_USER_ID  = 10144L;

    @InitBinder("contestResourcesBean")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException{
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=RESOURCES")
    public String showDescriptionTabController(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {

        if(!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }
        contestTitle = getContest().getContestShortName();
        try {
            setPageAttributes(request, model, tab);
            initWikiPageResourceAndCreateIfNoneExistsForThisContest();
            initWikiPageAndCreateIfNoneExistsForThisContest();
            model.addAttribute("contestResourcesBean", getContestResourcesBean());
            return TAB_VIEW;
        } catch (Exception e){
        }
        return NOT_FOUND_TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestResources")
    public void showDescriptionTabController(ActionRequest request, Model model, ActionResponse response,
                                             @ModelAttribute ContestResourcesBean updatedContestResourcesBean, BindingResult result) {

        if(!tabWrapper.getCanEdit()) {
            setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            setErrorRenderParameter(response, "updateContestResources");
            return;
        }

        try{
            updateWikiPage(updatedContestResourcesBean);
            setSuccessRenderRedirect(response, tab.getName());
        } catch(Exception e){
            e.printStackTrace();
            setNotFoundErrorRenderParameter(response);
        }

    }

    @RequestMapping(params = {"action=updateContestResources", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

    private void initWikiPageResourceAndCreateIfNoneExistsForThisContest() throws Exception{
        try {
            wikiPageResource = WikiPageResourceLocalServiceUtil.getPageResource(WIKI_NODE_ID, contestTitle);
        } catch(NoSuchPageResourceException e){
            createWikiPageResource();
        }
    }

    private void createWikiPageResource() throws Exception{
        wikiPageResource = WikiPageResourceLocalServiceUtil.addPageResource(WIKI_NODE_ID, contestTitle);
    }

    private void initWikiPageAndCreateIfNoneExistsForThisContest() throws Exception{
        try {
            wikiPage = WikiPageLocalServiceUtil.getPage(wikiPageResource.getResourcePrimKey());
        } catch(NoSuchPageException e){
            createWikiPage();
        }
    }
    private void createWikiPage() throws Exception {
        double version = 1;
        String content = "";
        addWikiPage(version, content);
    }
    private void addWikiPage(double version, String content) throws Exception{
        ServiceContext serviceContext = new ServiceContext();
        if(wikiPageResource != null) initWikiPageResourceAndCreateIfNoneExistsForThisContest();
        Long resourcePrimaryKey = wikiPageResource.getPrimaryKey();
        String summary = "";
        boolean minorEdit = false;
        boolean head = true;
        wikiPage = WikiPageLocalServiceUtil.addPage(
                WIKI_USER_ID,
                WIKI_NODE_ID,
                contestTitle,
                version,
                content,
                summary,
                minorEdit,
                "html",
                head,
                "", "",
                serviceContext);

        wikiPage.setResourcePrimKey(resourcePrimaryKey);
        wikiPage.persist();
        WikiPageLocalServiceUtil.updateWikiPage(wikiPage);
    }

    private ContestResourcesBean getContestResourcesBean() throws Exception{
        ContestResourcesBean contestResourcesBean = new ContestResourcesBean();
        String resourcesContent = wikiPage.getContent();
        contestResourcesBean.fillSectionsWithContent(resourcesContent);
        return contestResourcesBean;
    }

    private void updateWikiPage(ContestResourcesBean updatedContestResourcesBean) throws Exception{
        updatedContestResourcesBean.fillOverviewSectionContent(getContest());
        String updatedResourcesContent = updatedContestResourcesBean.getSectionsAsHtml();
        double currentVersion = wikiPage.getVersion();
        double newVersion = currentVersion + 0.1;

        removeHeadFlagFromCurrentWikiPage();
        addWikiPage(newVersion, updatedResourcesContent);

    }

    private void removeHeadFlagFromCurrentWikiPage() throws Exception{
        wikiPage.setHead(false);
        wikiPage.persist();
        WikiPageLocalServiceUtil.updateWikiPage(wikiPage);
    }
}