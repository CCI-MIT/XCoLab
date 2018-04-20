package org.xcolab.view.pages.search.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.view.pages.search.SearchBean;
import org.xcolab.view.util.pagination.PageNavigation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String showCategories(HttpServletRequest request, HttpServletResponse response,
            Model model, @RequestParam(required = false) String searchPhrase,
            @RequestParam(required = false) String searchLocation,
            @RequestParam(required = false, defaultValue = "1") Integer page) {

        final SearchBean searchBean = new SearchBean(searchPhrase, searchLocation, page);
        model.addAttribute("searchBean", searchBean);

        final String currentUrl = new UriBuilder(UriComponentsBuilder.fromUriString("/search")).cloneBuilder()
                .optionalQueryParam("searchPhrase", searchPhrase)
                .optionalQueryParam("searchLocation", searchLocation)
                .buildAndExpandString();
        model.addAttribute("pageNavigation", new PageNavigation(currentUrl,
                page, searchBean.getDataPage().getNumberOfPages()));

        model.addAttribute("_activePageLink", "search");
        return "search/search";
    }
}
