package org.xcolab.view.pages.members.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MembersMapController {

    @RequestMapping("/members/map")
    public String showMap() {
        return "members/map/page";
    }

    @RequestMapping("/widgets/membersMap")
    public String showWidget() {
        return "members/map/widget";
    }
}
