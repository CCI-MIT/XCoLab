package org.xcolab.portlets.contenteditor.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("view")
public class ContentEditorController {

    @RequestMapping
    public String contentEditor() {
        return "editor";
    }
}
