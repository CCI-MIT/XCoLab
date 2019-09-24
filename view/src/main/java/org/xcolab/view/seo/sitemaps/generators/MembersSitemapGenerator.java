package org.xcolab.view.seo.sitemaps.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.user.IUserCategoryClient;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.pojo.wrapper.MemberCategoryWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
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

    @Autowired
    private IUserCategoryClient userCategoryClient;

    @Autowired
    private IUserClient userClient;

    public XmlUrlSet generateForTeamMembers() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        final List<Long> roleIds = userCategoryClient.listMemberCategories().stream()
                // Remove members
                .filter(category -> category.getRoleId() != 10122)
                // Remove admins
                .filter(category -> category.getRoleId() != 10118)
                .map(MemberCategoryWrapper::getRoleId)
                .collect(Collectors.toList());
        List<UserWrapper> members = userClient.listMembersWithRoles(roleIds);
        for (UserWrapper member : members) {
            xmlUrlSet.addUrl(XmlUrl.Builder
                    .forLocation(siteUrl + member.getProfileLinkUrl())
                    .lastModified(DateUtil.toLocalDateTime(member.getUpdatedAt()))
                    .changeFrequency(ChangeFrequency.MONTHLY)
                    .priority(Priority.MEDIUM)
                    .build());
        }
        return xmlUrlSet;
    }
}
