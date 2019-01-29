package org.xcolab.view.seo.sitemaps.generators;

import org.springframework.stereotype.Service;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.view.seo.sitemaps.xml.XmlUrl;
import org.xcolab.view.seo.sitemaps.xml.XmlUrl.ChangeFrequency;
import org.xcolab.view.seo.sitemaps.xml.XmlUrl.Priority;
import org.xcolab.view.seo.sitemaps.xml.XmlUrlSet;

import java.util.List;

@Service
public class ContestSitemapGenerator {

    private final String siteUrl = PlatformAttributeKey.COLAB_URL.get();

    public XmlUrlSet generateForActiveContests() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        final List<ContestWrapper> activeContests =
                StaticContestContext.getContestClient().getContestsByActivePrivate(true, false);
        addContest(xmlUrlSet, activeContests, ChangeFrequency.DAILY, Priority.HIGHEST);
        return xmlUrlSet;
    }

    public XmlUrlSet generateForCompletedContests() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        final List<ContestWrapper> completedContests =
                StaticContestContext.getContestClient().getContestsByActivePrivate(false, false);
        addContest(xmlUrlSet, completedContests, ChangeFrequency.NEVER, Priority.HIGH);
        return xmlUrlSet;
    }

    private void addContest(XmlUrlSet xmlUrlSet, List<ContestWrapper> contests,
            ChangeFrequency changeFrequency, Priority priority) {
        for (ContestWrapper contest : contests) {
            xmlUrlSet.addUrl(XmlUrl.Builder
                    .forLocation(siteUrl + contest.getContestLinkUrl())
                    .changeFrequency(changeFrequency)
                    .priority(priority)
                    .build()
            );
        }
    }
}
