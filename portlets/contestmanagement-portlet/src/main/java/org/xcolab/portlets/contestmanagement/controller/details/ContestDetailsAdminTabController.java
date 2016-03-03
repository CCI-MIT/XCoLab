package org.xcolab.portlets.contestmanagement.controller.details;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.enums.ContestTier;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.beans.ContestAdminBean;
import org.xcolab.portlets.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.portlets.contestmanagement.entities.LabelStringValue;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.utils.TemplateReplacementUtil;
import org.xcolab.wrapper.TabWrapper;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class ContestDetailsAdminTabController extends ContestDetailsBaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestDetailsAdminTabController.class);
    static final private TabEnum tab = ContestDetailsTabs.ADMIN;
    static final private String TAB_VIEW = "details/adminTab";

    @Autowired
    private Validator validator;

    @InitBinder("contestAdminBean")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ModelAttribute("contestLevelSelectionItems")
    public List<LabelValue> populateContestLevelSelectionItems() {
        return getContestLevelSelectionItems();
    }

    @ModelAttribute("contestTypeSelectionItems")
    public List<LabelValue> populateContestTypeSelectionItems() {
        return getContestTypeSelectionItems();
    }

    @ModelAttribute("modelIdsSelectionItems")
    public List<LabelValue> populateModelIdsSelectionItems() {
        return ContestModelSettingsBean.getAllModelIds();
    }

    @ModelAttribute("modelRegionsSelectionItems")
    public List<LabelStringValue> populateModelRegionsSelectionItems() {
        return ContestModelSettingsBean.getAllModelRegions();
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=ADMIN")
    public String showAdminTabController(PortletRequest request, PortletResponse response, Model model,
            @RequestParam(required = false) Long contestId)
            throws PortalException, SystemException {

        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        setPageAttributes(request, model, tab);
        model.addAttribute("contestAdminBean", new ContestAdminBean(getContest()));
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestAdmin")
    public void updateAdminTabController(ActionRequest request, Model model,
            @ModelAttribute ContestAdminBean updateContestAdminBean,
            ActionResponse response) {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            Contest c = getContest();
            updateContestAdminBean.persist(c);
            SetRenderParameterUtil.setSuccessRenderRedirectDetailsTab(response, getContestPK(), tab.getName());
        } catch (SystemException | PortalException | IOException e) {
            _log.warn("Update contest admin failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @ResourceMapping(value = "submitContest")
    public
    @ResponseBody
    void handleSubmitContest(ResourceRequest request, ResourceResponse response,
            @RequestParam("contestId") Long contestId, @RequestParam("tab") String tab)
            throws IOException {

        boolean success = true;
        try {
            String contestUrl = "http://www.climatecolab.org/web/guest/cms/-/contestmanagement/contestId/" + contestId;
            if (!tab.isEmpty()) {
                contestUrl += "/tab/" + tab;
            }
            contestUrl += "<br/>";

            User user = UserLocalServiceUtil.getUser(Long.parseLong(request.getRemoteUser()));
            String body = "The following <contest/>: <br />" +
                    contestUrl +
                    "was submitted by the user: " + user.getFullName() + "<br/>";

            InternetAddress fromEmail = TemplateReplacementUtil.getAdminFromEmailAddress();

            String emailRecipients = "pdeboer@mit.edu,lfi@mit.edu";
            String[] recipients = emailRecipients.split(",");

            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }

            String subject = "<contest/> draft was submitted from the <contest/> management tool!";

            //TODO Phase 2: use configured CMS contest type?
            subject = TemplateReplacementUtil.replaceContestTypeStrings(subject, null);
            body = TemplateReplacementUtil.replaceContestTypeStrings(body, null);

            MailEngine.send(fromEmail, addressTo, subject, body, true);
        } catch (SystemException | PortalException | AddressException | MailEngineException e) {
            success = false;
        }
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(success));
    }


    @RequestMapping(params = {"action=updateContestAdmin", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

    @RequestMapping
    public String updateTeamTabController(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {
        return NOT_FOUND_TAB_VIEW;
    }

    @RequestMapping(params = {"action=showNoPermission", "error=true"})
    public String showNoPermissionErrorRenderParameter(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {
        return NO_PERMISSION_TAB_VIEW;
    }

    @RequestMapping(params = {"action=showNotFound", "error=true"})
    public String showNotFoundErrorRenderParameter(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {
        return NOT_FOUND_TAB_VIEW;
    }

    private List<LabelValue> getContestLevelSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestTier contestLevel : ContestTier.values()) {
            selectItems.add(new LabelValue(contestLevel.getTierType(), contestLevel.getTierName()));
        }
        return selectItems;
    }

    private List<LabelValue> getContestTypeSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        try {
            for (ContestType contestType : ContestTypeLocalServiceUtil
                    .getContestTypes(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
                selectItems.add(new LabelValue(contestType.getId(),
                        String.format("%d - %s with %s", contestType.getId(),
                                contestType.getContestName(), contestType.getProposalNamePlural())));
            }
        } catch (SystemException e) {
            _log.warn("Could not get contest type selection items: " + e);
        }
        return selectItems;
    }

}