package org.xcolab.view.widgets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WidgetListController {

    @GetMapping("/widgets")
    public String showWidgetShowcase() {
        return "widgets/widgetList";
    }
}
