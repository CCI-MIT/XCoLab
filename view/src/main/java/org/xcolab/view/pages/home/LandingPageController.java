package org.xcolab.view.pages.home;

import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LandingPageController {

    private Long homeSpotContentArticleId;
    private Long bottomContentArticleId;

    @ModelAttribute("homeSpotContentArticleId")
    public Long homeSpotContentArticleId() {
        if (homeSpotContentArticleId == null) {
            homeSpotContentArticleId =
                    ConfigurationAttributeKey.LANDING_PAGE_BANNER_CONTENT_ARTICLE_ID.get();
        }
        return homeSpotContentArticleId;
    }

    @ModelAttribute("homeBottomContentArticleId")
    public Long homeBottomContentArticleId() {
        if (bottomContentArticleId == null) {
            bottomContentArticleId =
                    ConfigurationAttributeKey.LANDING_PAGE_BOTTOM_CONTENT_ARTICLE_ID.get();
        }
        return bottomContentArticleId;
    }

    @RequestMapping("/")
    public String hello(HttpServletRequest request, HttpServletResponse response) {

        Metrics.counter("xcolab-view","endpoint","/", "function", "/").increment();

        return "home/home";
    }
}
