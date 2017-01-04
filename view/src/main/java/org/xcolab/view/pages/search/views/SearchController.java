package org.xcolab.view.pages.search.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.view.pages.search.SearchBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String showCategories(HttpServletRequest request, HttpServletResponse response, Model model,
                                 @RequestParam(required = false) String searchPhrase,
                                 @RequestParam(required = false) String searchLocation,
                                 @RequestParam(required = false) Integer pageNumber) {

        model.addAttribute("searchBean", new SearchBean(searchPhrase, searchLocation, pageNumber));

        return "search/search";
    }
}
