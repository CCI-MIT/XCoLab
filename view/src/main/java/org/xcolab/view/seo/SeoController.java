package org.xcolab.view.seo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SeoController {

    @GetMapping("/robots.txt")
    public void handleRenderRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        StringBuilder sb = new StringBuilder();
        final ServerEnvironment serverEnvironment =
                PlatformAttributeKey.SERVER_ENVIRONMENT.get();
        if (serverEnvironment == ServerEnvironment.PRODUCTION) {
            sb.append("User-agent: *\n");
            sb.append("Disallow: \n");
            sb.append("Crawl-delay: 20\n");
        } else {
            sb.append("User-agent: *\n");
            sb.append("Disallow: /");
        }

        response.getOutputStream().write(sb.toString().getBytes());
        response.setContentType("text/plain");
    }

}
