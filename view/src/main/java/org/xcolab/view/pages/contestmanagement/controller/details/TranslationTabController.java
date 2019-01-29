package org.xcolab.view.pages.contestmanagement.controller.details;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.beans.ContestTranslationBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/contest/details/contestId/{contestId}")
public class TranslationTabController extends AbstractTabController {

    private static final ContestDetailsTabs tab = ContestDetailsTabs.TRANSLATIONS;
    private static final String TAB_VIEW = "contestmanagement/details/translationTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }

    @GetMapping("tab/TRANSLATIONS")
    public String showTranslationTab(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @PathVariable long contestId) {

        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        if (!model.containsAttribute("contestTranslationBean")) {
            ContestWrapper contest = contestClient.getContest(contestId);
            model.addAttribute("contestTranslationBean", new ContestTranslationBean(contest));
        }
        model.addAttribute("i18nOptions", I18nUtils.getSelectList());
        return TAB_VIEW;
    }

    @PostMapping("tab/TRANSLATIONS")
    public String update(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @PathVariable long contestId,
            @Valid ContestTranslationBean contestTranslationBean, BindingResult result) {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (result.hasErrors()) {
            AlertMessage.danger("Error while updating.").flash(request);
            return showTranslationTab(request, response, model, member, contestId);
        }

        final ContestWrapper contest = contestClient.getContest(contestId);

        contestTranslationBean.persist(contest);
        return showTranslationTab(request, response, model, member, contestId);
    }
}
