package org.xcolab.view.caching;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.http.ServiceRequestUtils;
import org.xcolab.commons.http.caching.CacheName;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/caching")
public class CachingController {

    @PostMapping("clear")
    public void evict(HttpServletRequest request, HttpServletResponse response,
            Member loggedInMember, @RequestParam(required = false) CacheName cacheName)
            throws IOException {
        if (!PermissionsClient.canAdminAll(loggedInMember)) {
            ErrorText.ACCESS_DENIED.flashAndRedirect(request, response);
            return;
        }

        if (cacheName != null) {
            ServiceRequestUtils.clearCache(cacheName);
        } else {
            ServiceRequestUtils.clearCache();
        }
        AlertMessage.success("Cache cleared successfully").flash(request);
        response.sendRedirect(ContestManagerTabs.ADMIN.getTabUrl());
    }
}
