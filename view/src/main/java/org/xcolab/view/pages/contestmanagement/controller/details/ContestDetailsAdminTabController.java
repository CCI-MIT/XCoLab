package org.xcolab.view.pages.contestmanagement.controller.details;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.TemplateReplacementUtil;
import org.xcolab.util.enums.contest.ContestTier;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.contestmanagement.beans.ContestAdminBean;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.pages.contestmanagement.entities.LabelStringValue;
import org.xcolab.view.pages.contestmanagement.entities.LabelValue;
import org.xcolab.view.pages.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.view.taglibs.xcolab.interfaces.TabEnum;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("view")
public class ContestDetailsAdminTabController extends ContestDetailsBaseTabController {

    private final static Logger _log =
            LoggerFactory.getLogger(ContestDetailsAdminTabController.class);
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

    private List<LabelValue> getContestLevelSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestTier contestLevel : ContestTier.values()) {
            selectItems.add(new LabelValue(contestLevel.getTierType(), contestLevel.getTierName()));
        }
        return selectItems;
    }

    @ModelAttribute("contestTypeSelectionItems")
    public List<LabelValue> populateContestTypeSelectionItems() {
        return getContestTypeSelectionItems();
    }

    private List<LabelValue> getContestTypeSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();

        for (ContestType contestType : ContestClientUtil.getAllContestTypes()) {
            selectItems.add(new LabelValue(contestType.getId_(),
                    String.format("%d - %s with %s", contestType.getId_(),
                            contestType.getContestName(), contestType.getProposalNamePlural())));
        }

        return selectItems;
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
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=ADMIN")
    public String showAdminTabController(HttpServletRequest request, HttpServletResponse response,
            Model model,
            @RequestParam(required = false) Long contestId) {

        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        setPageAttributes(request, model, tab);
        model.addAttribute("contestAdminBean", new ContestAdminBean(getContest()));
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestAdmin")
    public void updateAdminTabController(HttpServletRequest request, Model model,
            @ModelAttribute ContestAdminBean updateContestAdminBean,
            HttpServletResponse response) {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            Contest c = getContest();
            updateContestAdminBean.persist(c);
            SetRenderParameterUtil
                    .setSuccessRenderRedirectDetailsTab(response, getContestPK(), tab.getName());
        } catch (IOException e) {
            _log.warn("Update contest admin failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @ResourceMapping(value = "submitContest")
    public
    @ResponseBody
    void handleSubmitContest(HttpServletRequest request, HttpServletResponse response,
            @RequestParam long contestId, @RequestParam String tab)
            throws IOException {

        String contestUrl = ConfigurationAttributeKey.COLAB_URL.get()
                + "/web/guest/cms/-/contestmanagement/contestId/" + contestId;
        if (!tab.isEmpty()) {
            contestUrl += "/tab/" + tab;
        }
        contestUrl += "<br/>";

        Member user = MemberAuthUtil.getMemberOrThrow(request);
        String body = "The following <contest/>: <br />" + contestUrl +
                "was submitted by the user: " + user.getFullName() + "<br/>";

        InternetAddress fromEmail = TemplateReplacementUtil.getAdminFromEmailAddress();

        final String emailRecipient = ConfigurationAttributeKey.ADMIN_EMAIL.get();

        List<String> addressTo = new ArrayList<>();
        addressTo.add(emailRecipient);

        String subject = "<contest/> draft was submitted from the <contest/> management tool!";

        ContestType contestType = ContestClientUtil
                .getContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
        subject = TemplateReplacementUtil.replaceContestTypeStrings(subject, contestType);
        body = TemplateReplacementUtil.replaceContestTypeStrings(body, contestType);

        EmailClient.sendEmail(fromEmail.getAddress(), addressTo, subject, body, true, null);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(true));
    }

    @RequestMapping(params = {"action=updateContestAdmin", "error=true"})
    public String reportError(HttpServletRequest request, Model model) {
        return TAB_VIEW;
    }

    @RequestMapping
    public String updateTeamTabController(HttpServletRequest request, HttpServletResponse response,
            Model model) {
        return NOT_FOUND_TAB_VIEW;
    }

    @RequestMapping(params = {"action=showNoPermission", "error=true"})
    public String showNoPermissionErrorRenderParameter(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        return NO_PERMISSION_TAB_VIEW;
    }

    @RequestMapping(params = {"action=showNotFound", "error=true"})
    public String showNotFoundErrorRenderParameter(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        return NOT_FOUND_TAB_VIEW;
    }

}