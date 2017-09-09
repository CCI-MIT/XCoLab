package org.xcolab.view.widgets.feeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.view.util.pagination.SortFilterPage;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FeedsController {

    private final List<FeedTypeDataProvider> dataProviderList;

    @Autowired
    public FeedsController(List<FeedTypeDataProvider> dataProviderList) {
        this.dataProviderList = dataProviderList;
    }

    @GetMapping("/activities")
    public String showFeedActivities(HttpServletRequest request, HttpServletResponse response,
            SortFilterPage sortFilterPage, Model model,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        sortFilterPage.setPage(page);
        model.addAttribute("communityTopContentArticleId",
                ConfigurationAttributeKey.MEMBERS_CONTENT_ARTICLE_ID.get());

        return showFeed(request, response, sortFilterPage, model, false);
    }

    private String showFeed(HttpServletRequest request, HttpServletResponse response,
            SortFilterPage sortFilterPage, Model model, Boolean isWidget) {

        Locale locale = LocaleContextHolder.getLocale();
        FeedsPreferences preferences = new FeedsPreferences("fullfeed", locale.getLanguage());
        if (!isWidget) {
            preferences.setFeedStyle("FULL");
            preferences.setFeedMaxLength(25);
            preferences.setFeedSize(25);
            preferences.setSeeMoreLinkShown(false);
        }

        model.addAttribute("feedType", preferences.getFeedType());
        model.addAttribute("feedStyle", preferences.getFeedStyle());
        model.addAttribute("portletTitle", preferences.getPortletTitle());
        model.addAttribute("seeMoreLinkShown", preferences.getSeeMoreLinkShown());
        for (FeedTypeDataProvider ftpdp : dataProviderList) {
            if (ftpdp.getFeedTypeName().equals(preferences.getFeedType().getDescription())) {
                return ftpdp.populateModel(request, response, sortFilterPage, preferences, model);
            }
        }
        return null;
    }

    @GetMapping("/feedswidget")
    public String showFeedWidget(HttpServletRequest request, HttpServletResponse response,
            SortFilterPage sortFilterPage, Model model) {
        return showFeed(request, response, sortFilterPage, model, true);
    }
}
