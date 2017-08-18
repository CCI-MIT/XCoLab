package org.xcolab.view.seo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xcolab.view.seo.sitemaps.generators.ContestSitemapGenerator;
import org.xcolab.view.seo.sitemaps.generators.MainSitemapGenerator;
import org.xcolab.view.seo.sitemaps.generators.MembersSitemapGenerator;
import org.xcolab.view.seo.sitemaps.generators.ProposalSitemapGenerator;
import org.xcolab.view.seo.sitemaps.xml.XmlUrlSet;

@Controller
@ResponseBody
@RequestMapping("/sitemaps")
public class SitemapController {

    private final MainSitemapGenerator mainSitemapGenerator;
    private final ContestSitemapGenerator contestSitemapGenerator;
    private final ProposalSitemapGenerator proposalSitemapGenerator;
    private final MembersSitemapGenerator membersSitemapGenerator;

    @Autowired
    public SitemapController(MainSitemapGenerator mainSitemapGenerator,
            ContestSitemapGenerator contestSitemapGenerator,
            ProposalSitemapGenerator proposalSitemapGenerator,
            MembersSitemapGenerator membersSitemapGenerator) {
        this.mainSitemapGenerator = mainSitemapGenerator;
        this.contestSitemapGenerator = contestSitemapGenerator;
        this.proposalSitemapGenerator = proposalSitemapGenerator;
        this.membersSitemapGenerator = membersSitemapGenerator;
    }

    @GetMapping("main.xml")
    public XmlUrlSet showMainSitemap() {
        return mainSitemapGenerator.generate();
    }

    @GetMapping("active-contests.xml")
    public XmlUrlSet showActiveContestSitemap() {
        return contestSitemapGenerator.generateForActiveContests();
    }

    @GetMapping("completed-contests.xml")
    public XmlUrlSet showCompletedContestSitemap() {
        return contestSitemapGenerator.generateForCompletedContests();
    }

    @GetMapping("team-members.xml")
    public XmlUrlSet showMembersSitemap() {
        return membersSitemapGenerator.generateForTeamMembers();
    }

    @GetMapping("active-proposals.xml")
    public XmlUrlSet showActiveProposalsSitemap() {
        return proposalSitemapGenerator.generateForActiveProposals();
    }

    @GetMapping("awarded-proposals.xml")
    public XmlUrlSet showCompletedProposalsWithRibbonsSitemap() {
        return proposalSitemapGenerator.generateForAwardedProposals();
    }

    @GetMapping("other-proposals.xml")
    public XmlUrlSet showCompletedProposalsWithoutRibbonsSitemap() {
        return proposalSitemapGenerator.generateForOtherProposals();
    }

}
