package org.xcolab.view.seo.sitemaps.generators;

import org.springframework.stereotype.Service;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.view.seo.sitemaps.xml.XmlUrl;
import org.xcolab.view.seo.sitemaps.xml.XmlUrl.ChangeFrequency;
import org.xcolab.view.seo.sitemaps.xml.XmlUrl.Priority;
import org.xcolab.view.seo.sitemaps.xml.XmlUrlSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.xcolab.commons.time.DateUtil.toLocalDateTime;

@Service
public class ProposalSitemapGenerator {

    private final String siteUrl = PlatformAttributeKey.COLAB_URL.get();

    public XmlUrlSet generateForActiveProposals() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        final List<ProposalWrapper> proposals = StaticProposalContext.getProposalClient()
                .listProposalsInActiveContests();
        addProposals(xmlUrlSet, proposals, ChangeFrequency.DAILY, Priority.MEDIUM);
        return xmlUrlSet;
    }

    public XmlUrlSet generateForAwardedProposals() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        final List<ProposalWrapper> proposals =
                StaticProposalContext.getProposalClient()
                        .listProposalsInCompletedContests(Arrays.asList(1, 2, 3));
        addProposals(xmlUrlSet, proposals, ChangeFrequency.NEVER, Priority.HIGH);
        return xmlUrlSet;
    }

    public XmlUrlSet generateForOtherProposals() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        final List<ProposalWrapper> proposals = StaticProposalContext.getProposalClient()
                .listProposalsInCompletedContests(Collections.singletonList(0));
        addProposals(xmlUrlSet, proposals, ChangeFrequency.NEVER, Priority.LOW);

        return xmlUrlSet;
    }

    private void addProposals(XmlUrlSet xmlUrlSet, List<ProposalWrapper> proposals,
            ChangeFrequency changeFrequency, Priority priority) {
        for (ProposalWrapper proposal : proposals) {
            xmlUrlSet.addUrl(XmlUrl.Builder
                    .forLocation(siteUrl + proposal.getProposalUrl())
                    .lastModified(toLocalDateTime(proposal.getLastupdatedAt()))
                    .changeFrequency(changeFrequency)
                    .priority(priority)
                    .build());
        }
    }
}
