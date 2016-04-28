package org.xcolab.portlets.wiki.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.xcolab.portlets.wiki.util.WikiPreferences;

import javax.portlet.RenderRequest;

@Controller
@RequestMapping("view")
public class WikiController {

    @RenderMapping
    public String wiki(RenderRequest request, Model model) {
        final WikiPreferences preferences = new WikiPreferences(request);

        return "wiki";
    }
}
