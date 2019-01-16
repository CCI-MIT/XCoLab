package org.xcolab.view.seo.sitemaps.generators;

import org.springframework.stereotype.Service;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.user.pojo.MemberCategory;
import org.xcolab.commons.time.DateUtil;
import org.xcolab.view.seo.sitemaps.xml.XmlUrl;
import org.xcolab.view.seo.sitemaps.xml.XmlUrl.ChangeFrequency;
import org.xcolab.view.seo.sitemaps.xml.XmlUrl.Priority;
import org.xcolab.view.seo.sitemaps.xml.XmlUrlSet;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MembersSitemapGenerator {

    private final String siteUrl = PlatformAttributeKey.COLAB_URL.get();

    public XmlUrlSet generateForTeamMembers() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        final List<Long> roleIds = MembersClient.listMemberCategories().stream()
                // Remove members
                .filter(category -> category.getRoleId() != 10122)
                // Remove admins
                .filter(category -> category.getRoleId() != 10118)
                .map(MemberCategory::getRoleId)
                .collect(Collectors.toList());
        List<Member> members = MembersClient.listMembersWithRoles(roleIds);
        for (Member member : members) {
            xmlUrlSet.addUrl(XmlUrl.Builder
                    .forLocation(siteUrl + member.getProfileLinkUrl())
                    .lastModified(DateUtil.toLocalDateTime(member.getupdatedAt()))
                    .changeFrequency(ChangeFrequency.MONTHLY)
                    .priority(Priority.MEDIUM)
                    .build());
        }
        return xmlUrlSet;
    }
}
