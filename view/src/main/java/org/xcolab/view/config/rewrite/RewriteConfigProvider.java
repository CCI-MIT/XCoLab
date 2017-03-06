package org.xcolab.view.config.rewrite;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestType;

import javax.servlet.ServletContext;

public class RewriteConfigProvider extends HttpConfigurationProvider {
    private static final Logger log = LoggerFactory.getLogger(RewriteConfigProvider.class);

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public Configuration getConfiguration(ServletContext servletContext) {
        final ConfigurationBuilder configurationBuilder = ConfigurationBuilder.begin();
        redirectLegacyThemeImages(configurationBuilder);
        redirectLegacyRegistration(configurationBuilder);
        redirectLegacyWikiPages(configurationBuilder);
        redirectLegacyUserProfile(configurationBuilder);
        redirectLegacyProposals(configurationBuilder);
        redirectLegacyMembers(configurationBuilder);
        redirectLegacyDiscussions(configurationBuilder);
        redirectContentPages(configurationBuilder);

        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/feedback"))
                                                .or(Path.matches("/page/feedback")))
                    .perform(Redirect.permanent("/feedback"));
        return configurationBuilder;
    }

    private void redirectLegacyThemeImages(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/{colabName}-theme/images/{path}")))
                    .perform(Forward.to("/images/{path}"))
                    .where("path").matches(".*")
                    .where("colabName").matches("(climatecolab|fow|solve|rd)");
    }

    private void redirectLegacyRegistration(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/loginregister{path}")
                                    .or(Path.matches("/loginregister{path}"))))
                    .perform(Redirect.permanent("/register"))
                    .where("path").matches(".*");
    }

    private void redirectLegacyWikiPages(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/resources/-/wiki/Main/{page}")
                                    .or(Path.matches("/resources/-/wiki/Main/{page}"))
                                    .or(Path.matches("/resources/-/wiki/main/{page}"))
                                    .or(Path.matches("/web/guest/wiki/-/wiki/page/{page}"))
                            ))
                    .perform(Redirect.permanent("/wiki/{page}"))
                    .where("page").matches(".*")
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/handbook/-/wiki/Main/{page}")
                                    .or(Path.matches("/web/guest/handbook/-/wiki/Main/{page}"))
                                    .or(Path.matches("/web/guest/handbook/-/wiki/page/{page}"))
                    ))
                    //TODO: this page doesn't exist yet!
                    .perform(Redirect.permanent("/handbook/{page}"))
                    .where("page").matches(".*");
    }

    private void redirectLegacyUserProfile(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/member/-/member/userId/{memberId}")))
                    .perform(Redirect.permanent("/members/profile/{memberId}"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/member/-/member/userId/{memberId}/page/edit")))
                    .perform(Redirect.permanent("/members/profile/{memberId}/edit"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/member/-/member/userId/{memberId}/page/subscriptions")))
                    .perform((Redirect.permanent("/members/profile/{memberId}/subscriptions")))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/member/-/member/userId/{memberId}/page/subscriptionsManage")))
                    .perform((Redirect.permanent("/members/profile/{memberId}/subscriptions/manage")));
    }

    private void redirectLegacyMembers(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/members")))
                    .perform(Redirect.permanent("/members"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/messaging")))
                    .perform(Redirect.permanent("/messaging"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/messaging/-/messaging/message/{messageId}")))
                    .perform(Redirect.permanent("/messaging/message/{messageId}"));
    }

    private void redirectLegacyDiscussions(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/discussion")))
                    .perform(Redirect.permanent("/discussion"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/discussion/-/discussion/thread/{threadId}")))
                    .perform(Redirect.permanent("/discussion/thread/{threadId}"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/discussion/-/discussion/category/{categoryId}")))
                    .perform(Redirect.permanent("/discussion/category/{categoryId}"));
    }

    private void redirectLegacyProposals(ConfigurationBuilder configurationBuilder) {
        final ContestType defaultContestType = ContestClientUtil
                .getContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/challenges{path}")
                            .or(Path.matches("/events{path}"))
                            .or(Path.matches("/trends{path}"))
                            .or(Path.matches("/dialogues{path}"))
                    ))
                    .perform(Forward.to("/contests{path}"))
                    .where("path").matches(".*")
                .addRule()
                    .when(Direction.isInbound()
                            .and(Path.matches("/web/guest/{portletName}")))
                    .perform(Redirect.permanent(defaultContestType.getPortletUrl()))
                .where("portletName").matches("(plans|dialogues|challenges|trends)")
                .addRule()
                    .when(Direction.isInbound()
                            .and(Path.matches("/web/guest/plans/-/plans/contestsType/prior")))
                    .perform(Redirect.permanent(defaultContestType.getPortletUrl()
                            + "?viewType=GRID&filter=&showActiveContests=false"))
                .addRule()
                    .when(Direction.isInbound()
                            .and(Path.matches("/web/guest/plans/-/plans/contestsType/all")))
                    .perform(Redirect.permanent(defaultContestType.getPortletUrl()
                            + "?viewType=GRID&filter=&showActiveContests=false&showAllContests=true"));

        // legacy urls
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound()
                            .and(Path.matches("/web/guest/{portletName}/-/plans/contestId/{contestId}")))
                    .perform(Forward.to("/contests/legacy/contest/{contestId}"))
                    .where("portletName").matches("(plans|dialogues|challenges|trends)")
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/{portletName}/-/plans/contestId/{contestId}/phase/{phaseId}")))
                    .perform(Forward.to("/contests/legacy/contest/{contestId}?phaseId={phaseId}"))
                    .where("portletName").matches("(plans|dialogues|challenges|trends)")
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/{portletName}/-/plans/contestId/{contestId}/planId/{proposalId}")
                                .or(Path.matches("/web/guest/{portletName}/-/plans/contestId/{contestId}/planId/{proposalId}/{path}"))))
                    .perform(Forward.to("/contests/legacy/contest/{contestId}/proposal/{proposalId}"))
                    .where("portletName").matches("(plans|dialogues|challenges|trends)")
                    .where("path").matches(".*")
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/{portletName}/-/plans/contestId/{contestId}/phaseId/{phaseId}/planId/{proposalId}")
                                .or(Path.matches("/web/guest/{portletName}/-/plans/contestId/{contestId}/phaseId/{phaseId}/planId/{proposalId}/{path}"))))
                    .perform(Forward.to("/contests/legacy/contest/{contestId}/proposal/{proposalId}?phaseId={phaseId}"))
                    .where("portletName").matches("(plans|dialogues|challenges|trends)")
                    .where("path").matches(".*");
    }

    private void redirectContentPages(ConfigurationBuilder configurationBuilder) {
        switch (ConfigurationAttributeKey.COLAB_NAME.get()) {
            case "Climate CoLab":
                redirectContentPagesClimateCoLab(configurationBuilder);
                break;
            case "Solve CoLab":
                redirectContentPagesSolveCoLab(configurationBuilder);
                break;
            case "Crowdsensor":
                redirectContentPagesCrowdsensor(configurationBuilder);
                break;
            case "Resilience Dialogues":
                redirectContentPagesResilienceDialogues(configurationBuilder);
                break;
            default:
                log.warn("No Content Pages loaded for {}",
                        ConfigurationAttributeKey.COLAB_NAME.get());
        }
    }

    private void redirectContentPagesClimateCoLab(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/about")
                                                .or(Path.matches("/about"))))
                    .perform(Redirect.permanent("/page/about"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/crowdsourcing")
                                                .or(Path.matches("/crowdsourcing"))))
                    .perform(Redirect.permanent("/page/crowdsourcing"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/contests")))
                    .perform(Redirect.permanent("/page/why-contests"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/get-involved")
                                                .or(Path.matches("/get-involved"))))
                    .perform(Redirect.permanent("/page/get-involved"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/conferences")
                                                .or(Path.matches("/conferences"))))
                    .perform(Redirect.permanent("/page/conferences"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/faqs")
                                                .or(Path.matches("/faqs"))))
                    .perform(Redirect.permanent("/page/faqs"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/sponsors")
                                                .or(Path.matches("/sponsors"))))
                    .perform(Redirect.permanent("/page/sponsors"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/people")
                                                .or(Path.matches("/people"))))
                    .perform(Redirect.permanent("/page/people"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/press")
                                                .or(Path.matches("/press"))))
                    .perform(Redirect.permanent("/page/press"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/2016-contest-rules")))
                    .perform(Redirect.permanent("/wiki/2016+Contest+Rules"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/contest-rules")))
                    .perform(Redirect.permanent("/wiki/Contest+Rules"));

        //staff members pages
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/project-staff1/-/wiki/Main/Project staff")))
                    .perform(Redirect.permanent("/page/project-staff"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/impact_fellows")))
                    .perform(Redirect.permanent("/page/people-impact-fellows"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/advisors/-/wiki/Main/Climate+CoLab+Advisors")))
                    .perform(Redirect.permanent("/page/people-advisors"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/judges/-/wiki/Main/Climate+CoLab+Judges")))
                    .perform(Redirect.permanent("/page/people-judges"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/fellows/-/wiki/Main/Climate+CoLab+Fellows")))
                    .perform(Redirect.permanent("/page/people-fellows"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/expert-advisory-board/-/wiki/Main/Expert advisory board")))
                    .perform(Redirect.permanent("/page/people-expert-advisory-board"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/expert-council/-/wiki/Main/Expert Council")))
                    .perform(Redirect.permanent("/page/people-expert-council"));

        //Conferences
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/conference{conferenceYear}")
                            .or(Path.matches("/web/guest/conference{conferenceYear}"))))
                    .perform(Redirect.permanent("/page/conference{conferenceYear}"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/conference{conferenceYear}/{subPage}")
                            .or(Path.matches("/web/guest/conference{conferenceYear}/{subPage}"))))
                    .perform(Redirect.permanent("/page/conference{conferenceYear}-{subPage}"));

        //news
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/community/-/blogs/preparing-climate-colab-for-further-growth-developers-finish-liferay-transition")))
                    .perform(Redirect.permanent("http://news.climatecolab.com/2016/12/improvements-to-the-climate-colab-platform/"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/community/-/blogs/climate-colab-featured-in-climatewire")))
                    .perform(Redirect.permanent("http://news.climatecolab.com/2015/05/climate-colab-featured-in-climatewire/"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/community/-/blogs/climate-colab-featured-in-the-guardian")))
                    .perform(Redirect.permanent("http://news.climatecolab.com/2014/04/climate-colab-featured-in-the-guardian/"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/news/-/blogs/climate-colab-tedx-talk")))
                    .perform(Redirect.permanent("http://news.climatecolab.com/2015/11/climate-colab-tedx-talk/"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/news").or(Path.matches("/community"))))
                    .perform(Redirect.permanent("http://news.climatecolab.com"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/community/-/blogs/2016-climate-colab-winners-announced")))
                    .perform(Redirect.permanent("http://news.climatecolab.com/2016/08/2016-climate-colab-winners-announced/"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/community/-/blogs/press-release-grand-prize-winner-announced-2016")))
                    .perform(Redirect.permanent("http://news.climatecolab.com/2016/09/press-release-grand-prize-winner-announced-2016/"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/community/-/blogs/2016-climate-colab-conference-in-review")))
                    .perform(Redirect.permanent("http://news.climatecolab.com/2016/10/2016-crowds-climate-conference-in-review/"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/community/-/blogs/finalists-announced-vote-for-popular-choice-winners-")))
                    .perform(Redirect.permanent("http://news.climatecolab.com/2016/07/finalists-announced-vote-for-popular-choice-winners/"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/community/-/blogs/2015-climate-colab-winners-announced")))
                    .perform(Redirect.permanent("http://news.climatecolab.com/2015/09/2015-winners-announced-by-mit-climate-colab/"));

    }

    private void redirectContentPagesSolveCoLab(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/about")))
                    .perform(Redirect.permanent("/page/about"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/crowdsourcing")))
                    .perform(Redirect.permanent("/page/crowdsourcing"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/why-these-challenges")))
                    .perform(Redirect.permanent("/page/why-these-challenges"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/get-involved")))
                    .perform(Redirect.permanent("/page/get-involved"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/faqs")))
                    .perform(Redirect.permanent("/page/faqs"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/sponsors")
                                                .or(Path.matches("/sponsors"))))
                    .perform(Redirect.permanent("/page/sponsors"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/people")))
                    .perform(Redirect.permanent("/page/people"));
    }

    private void redirectContentPagesCrowdsensor(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/about")))
                    .perform(Redirect.permanent("/page/about"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/crowdsourcing")))
                    .perform(Redirect.permanent("/page/crowdsourcing"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/smart-nation")))
                    .perform(Redirect.permanent("/page/smart-nation"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/get-involved")))
                    .perform(Redirect.permanent("/page/get-involved"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/faqs")))
                    .perform(Redirect.permanent("/page/faqs"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/sponsors")))
                    .perform(Redirect.permanent("/page/sponsors"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/people")))
                    .perform(Redirect.permanent("/page/people"));

        // news
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/community/-/blogs/{articleTitle}")))
                    .perform(Redirect.permanent("/page/news-{articleTitle}"));
    }

    private void redirectContentPagesResilienceDialogues(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/about")))
                    .perform(Redirect.permanent("/page/about"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/crowdsourcing")))
                    .perform(Redirect.permanent("/page/crowdsourcing"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/community")))
                    .perform(Redirect.permanent("/page/community"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/get-involved")))
                    .perform(Redirect.permanent("/page/get-involved"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/partners")))
                    .perform(Redirect.permanent("/page/partners"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/participants1")))
                    .perform(Redirect.permanent("/page/participants"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/materials")))
                    .perform(Redirect.permanent("/page/materials"));

        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/Coral_Gables")))
                    .perform(Redirect.permanent("/pages/Coral_Gables"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/Dubuque")))
                    .perform(Redirect.permanent("/pages/Dubuque"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/Knoxville")))
                    .perform(Redirect.permanent("/pages/Knoxville"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/MARC")))
                    .perform(Redirect.permanent("/pages/MARC"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/Minneapolis")))
                    .perform(Redirect.permanent("/pages/Minneapolis"));
    }
}
