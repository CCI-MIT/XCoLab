package org.xcolab.view.api.members;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members/stats")
public class MembersStatsController {

    @GetMapping
    public MembersStats getStats(Member loggedInMember) {
        List<Member> members = MembersClient.listAllMembers();
        final Map<String, Long> countryCounts = members.stream()
                .filter(member -> StringUtils.isNotBlank(member.getCountry()))
                .collect(Collectors.groupingBy(Member::getCountry, Collectors.counting()));
        return new MembersStats(members.size(), countryCounts);
    }
}
