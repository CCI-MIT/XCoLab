package org.xcolab.view.api.members;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/members/stats")
public class MembersStatsController {


    private final IUserClient userClient;


    @Autowired
    public MembersStatsController(IUserClient userClient){
        this.userClient = userClient;
    }

    @GetMapping
    public MembersStats getStats(HttpServletResponse response) {
        response.setHeader(HttpHeaders.CACHE_CONTROL,
                CacheControl.maxAge(1, TimeUnit.DAYS).getHeaderValue());
        List<UserWrapper> members = userClient.listAllMembers();
        final Map<String, Long> countryCounts = members.stream()
                .filter(member -> StringUtils.isNotBlank(member.getCountry()))
                .collect(Collectors.groupingBy(UserWrapper::getCountry, Collectors.counting()));
        return new MembersStats(members.size(), countryCounts);
    }
}
