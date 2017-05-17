package org.xcolab.view.seo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@PropertySource({"file:${user.home}/.xcolab.application.properties"})
public class SeoController {

    @Autowired
    private Environment env;

    @GetMapping("/robots.txt")
    public void handleRenderRequest(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
        StringBuilder sb = new StringBuilder();
        Boolean isProduction = "production".equals(env.getProperty("environment"));
        if(isProduction){
            sb.append("User-agent: *\n");
            sb.append("Disallow: \n");
            sb.append("Crawl-delay: 60\n");

        }else{
            sb.append("User-agent: *\n" + "Disallow: /");
        }

        response.getOutputStream().write(sb.toString().getBytes());
        response.setContentType("text/plain");
    }

}
