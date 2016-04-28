package org.xcolab.portlets.contentdisplay.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller
@RequestMapping("view")
public class ContentDisplayController {

    @RenderMapping
    public String contentEditor() {
        return "content";
    }
}
