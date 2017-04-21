package org.xcolab.view.pages.members.map;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MembersMapController {

    @RequestMapping("/members/map")
    public String showMap() {
        return "members/map/page";
    }

    @RequestMapping("/widgets/membersMap")
    public String showWidget(HttpServletResponse response) {
        response.setHeader(HttpHeaders.CACHE_CONTROL,
            CacheControl.maxAge(12, TimeUnit.HOURS).getHeaderValue());
        return "members/map/widget";
    }
}
