package org.xcolab.view.seo.sitemaps.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.pojo.IContentPage;
import org.xcolab.view.seo.sitemaps.xml.XmlUrl;
import org.xcolab.view.seo.sitemaps.xml.XmlUrl.ChangeFrequency;
import org.xcolab.view.seo.sitemaps.xml.XmlUrl.Priority;
import org.xcolab.view.seo.sitemaps.xml.XmlUrlSet;

import java.util.List;

@Service
public class MainSitemapGenerator {

    @Autowired
    private IContentClient contentClient;

    @Autowired
    private IContestTypeClient contestTypeClient;

    private final String siteUrl = PlatformAttributeKey.COLAB_URL.get();

    public XmlUrlSet generate() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        addLandingPageUrl(xmlUrlSet);
        for (ContestType contestType : contestTypeClient.getActiveContestTypes()) {
            addContestType(xmlUrlSet, contestType.getId());
        }
        addContentPages(xmlUrlSet);

        return xmlUrlSet;
    }

    private void addLandingPageUrl(XmlUrlSet xmlUrlSet) {
        xmlUrlSet.addUrl(XmlUrl.Builder
                .forLocation(siteUrl)
                .changeFrequency(ChangeFrequency.DAILY)
                .priority(Priority.HIGHEST)
                .build());
    }

    private void addContestType(XmlUrlSet xmlUrlSet, long contestTypeId) {
        ContestType contestType = new ContestType(contestTypeId);
        xmlUrlSet.addUrl(XmlUrl.Builder
                .forLocation(siteUrl + contestType.getContestBaseUrl())
                .priority(Priority.HIGHEST)
                .changeFrequency(ChangeFrequency.DAILY)
                .build());
    }

    private void addContentPages(XmlUrlSet xmlUrlSet) {
        final List<IContentPage> contentPages = contentClient.getContentPages(null);
        for (IContentPage contentPage : contentPages) {
            xmlUrlSet.addUrl(XmlUrl.Builder
                    .forLocation(siteUrl + "/page/" + contentPage.getTitle())
                    .changeFrequency(ChangeFrequency.DAILY)
                    .priority(Priority.HIGH)
                    .build());
        }
    }
}
