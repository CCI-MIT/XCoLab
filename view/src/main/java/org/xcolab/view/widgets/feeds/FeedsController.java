package org.xcolab.view.widgets.feeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.util.pagination.SortFilterPage;
import org.xcolab.view.widgets.AbstractWidgetController;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FeedsController extends AbstractWidgetController<FeedsPreferences> {

    private static final String BASE_URL = "/widgets/feeds";

    private final List<FeedTypeDataProvider> dataProviderList;

    @Autowired
    protected FeedsController(List<FeedTypeDataProvider> dataProviderList) {
        super(BASE_URL, FeedsPreferences::new);
        this.dataProviderList = dataProviderList;
    }

    @GetMapping(BASE_URL + AbstractWidgetController.PREFERENCES_URL_PATH)
    public String showPreferences(HttpServletResponse response, Model model, UserWrapper member,
            @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) String language) {

        // populate feed types
        Map<String, String> feedTypes = new HashMap<>();
        for (FeedType feedType : FeedType.values()) {
            feedTypes.put(feedType.name(), feedType.name());
        }
        model.addAttribute("feedTypes", feedTypes);

        Map<String, String> feedDisplayStyles = new HashMap<>();
        feedDisplayStyles.put("FULL", "FULL");
        feedDisplayStyles.put("SHORT", "SHORT");
        model.addAttribute("feedDisplayStyles", feedDisplayStyles);

        return showPreferencesInternal(response, model, member, preferenceId, language,
                "/feedswidget/editPreferences");
    }


    @PostMapping(BASE_URL + AbstractWidgetController.PREFERENCES_URL_PATH)
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            UserWrapper member, FeedsPreferences preferences) {
        return savePreferencesInternal(request, response, member, preferences);
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
        model.addAttribute("title", preferences.getTitle());
        model.addAttribute("seeMoreLinkShown", preferences.getSeeMoreLinkShown());
        for (FeedTypeDataProvider dataProvider : dataProviderList) {
            if (dataProvider.getFeedType() == preferences.getFeedType()) {
                dataProvider.populateModel(request, response, sortFilterPage, preferences, model);
                return dataProvider.getViewName();
            }
        }
        return null;
    }

    @GetMapping(BASE_URL)
    public String showFeedWidget(HttpServletRequest request, HttpServletResponse response,
            SortFilterPage sortFilterPage, Model model) {
        return showFeed(request, response, sortFilterPage, model, true);
    }
}
