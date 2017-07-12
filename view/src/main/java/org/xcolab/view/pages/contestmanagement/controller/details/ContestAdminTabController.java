package org.xcolab.view.pages.contestmanagement.controller.details;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.TemplateReplacementUtil;
import org.xcolab.util.enums.contest.ContestTier;
import org.xcolab.util.html.LabelStringValue;
import org.xcolab.util.html.LabelValue;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.contestmanagement.beans.ContestAdminBean;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/details/contestId/{contestId}/tab/ADMIN")
public class ContestAdminTabController extends AbstractTabController {

    static final private ContestDetailsTabs tab = ContestDetailsTabs.ADMIN;
    static final private String TAB_VIEW = "contestmanagement/details/adminTab";

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

        for (ContestType contestType : ContestTypeClient.getAllContestTypes()) {
            selectItems.add(new LabelValue(contestType.getId(),
                    String.format("%d - %s with %s", contestType.getId(),
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

    @GetMapping
    public String showAdminTabController(HttpServletRequest request, HttpServletResponse response,
            Model model,
            @RequestParam(required = false) Long contestId) {

        if (!tabWrapper.getCanView()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        setPageAttributes(request, model, tab);
        model.addAttribute("contestAdminBean", new ContestAdminBean(getContest()));
        return TAB_VIEW;
    }

    @PostMapping("update")
    public String updateAdminTabController(HttpServletRequest request, HttpServletResponse response,
            Model model, @ModelAttribute ContestAdminBean updateContestAdminBean,
            @PathVariable long contestId) {

        if (!tabWrapper.getCanEdit()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        Contest c = getContest();
        updateContestAdminBean.persist(c);
        return "redirect:" + tab.getTabUrl(contestId);
    }

    @PostMapping("submit")
    public @ResponseBody void handleSubmitContest(HttpServletRequest request,
            HttpServletResponse response, @PathVariable long contestId, @RequestParam String tab,
            Member member)
            throws IOException {

        String contestUrl = PlatformAttributeKey.COLAB_URL.get()
                + "/admin/contest/details/contestId/" + contestId;
        if (!tab.isEmpty()) {
            contestUrl += "/tab/" + tab;
        }
        contestUrl += "<br/>";

        String body = "The following <contest/>: <br />" + contestUrl +
                "was submitted by the member: " + member.getFullName() + "<br/>";

        InternetAddress fromEmail = TemplateReplacementUtil.getAdminFromEmailAddress();

        final String emailRecipient = ConfigurationAttributeKey.ADMIN_EMAIL.get();

        List<String> addressTo = new ArrayList<>();
        addressTo.add(emailRecipient);

        String subject = "<contest/> draft was submitted from the <contest/> management tool!";

        ContestType contestType = ContestTypeClient
                .getContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
        subject = TemplateReplacementUtil.replaceContestTypeStrings(subject, contestType);
        body = TemplateReplacementUtil.replaceContestTypeStrings(body, contestType);

        EmailClient.sendEmail(fromEmail.getAddress(), addressTo, subject, body, true, null,contestId);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(true));
    }
}
