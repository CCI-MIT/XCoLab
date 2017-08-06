package org.xcolab.view.config.rewrite.rules.legacy;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;

import org.xcolab.view.config.rewrite.SimpleRewriteBuilder;
import org.xcolab.view.config.rewrite.rules.RewriteRuleProvider;

public class LegacyContentRewriteRules implements RewriteRuleProvider {

    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        redirectContentPagesClimateCoLab(configurationBuilder);
        redirectContentPagesSolveCoLab(configurationBuilder);
        redirectContentPagesCrowdsensor(configurationBuilder);
        redirectContentPagesResilienceDialogues(configurationBuilder);
        redirectLegacyWikiPages(configurationBuilder);
    }

    private void redirectContentPagesClimateCoLab(ConfigurationBuilder configurationBuilder) {
        SimpleRewriteBuilder.of(configurationBuilder)
                .redirectFrom("/web/guest/about")
                    .andFrom("/about")
                    .to("/page/about")
                .redirectFrom("/web/guest/crowdsourcing")
                    .andFrom("/crowdsourcing")
                    .to("/page/crowdsourcing")
                .redirectFrom("/web/guest/contests")
                    .to("/page/why-contests")
                .redirectFrom("/web/guest/get-involved")
                    .andFrom("/get-involved")
                    .to("/page/get-involved");

        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/conferences")
                            .or(Path.matches("/conferences"))))
                    .perform(Redirect.permanent("/page/conferences"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/faqs")
                            .or(Path.matches("/faqs"))
                            .or(Path.matches("/resources"))))
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

        redirectClimateCoLabStaffMemberPages(configurationBuilder);
        redirectClimateCoLabConferencePages(configurationBuilder);
    }

    private void redirectClimateCoLabConferencePages(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/conference{conferenceYear}")
                        .or(Path.matches("/web/guest/conference{conferenceYear}"))))
                .perform(Redirect.permanent("/page/conference{conferenceYear}"))
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/conference{conferenceYear}/{subPage}")
                        .or(Path.matches("/web/guest/conference{conferenceYear}/{subPage}"))))
                .perform(Redirect.permanent("/page/conference{conferenceYear}-{subPage}"));
    }

    private void redirectClimateCoLabStaffMemberPages(ConfigurationBuilder configurationBuilder) {
        SimpleRewriteBuilder.of(configurationBuilder)
                .redirectFrom("/web/guest/project-staff1/-/wiki/Main/Project staff")
                        .andFrom("/project-staff1/-/wiki/Main/Project staff")
                        .andFrom("/web/guest/project-staff1")
                        .andFrom("/project-staff1")
                    .to("/page/project-staff")
                .redirectFrom("/web/guest/impact_fellows")
                        .andFrom("/impact_fellows")
                    .to("/page/people-impact-fellows")
                .redirectFrom("/web/guest/advisors/-/wiki/Main/Climate+CoLab+Advisors")
                        .andFrom("advisors/-/wiki/Main/Climate+CoLab+Advisors")
                        .andFrom("/web/guest/advisors")
                        .andFrom("/advisors")
                    .to("/page/people-advisors")
                .redirectFrom("/web/guest/judges/-/wiki/Main/Climate+CoLab+Judges")
                        .andFrom("/judges/-/wiki/Main/Climate+CoLab+Judges")
                        .andFrom("/web/guest/judges")
                        .andFrom("/judges")
                    .to("/page/people-judges")
                .redirectFrom("/web/guest/fellows/-/wiki/Main/Climate+CoLab+Fellows")
                        .andFrom("/fellows/-/wiki/Main/Climate+CoLab+Fellows")
                        .andFrom("/web/guest/fellows")
                        .andFrom("/fellows")
                    .to("/page/people-fellows")
                .redirectFrom("/web/guest/expert-advisory-board/-/wiki/Main/Expert advisory board")
                        .andFrom("/expert-advisory-board/-/wiki/Main/Expert advisory board")
                        .andFrom("/web/guest/expert-advisory-board")
                        .andFrom("/expert-advisory-board")
                    .to("/page/people-expert-advisory-board")
                .redirectFrom("/web/guest/expert-council/-/wiki/Main/Expert Council")
                        .andFrom("/expert-council/-/wiki/Main/Expert Council")
                        .andFrom("/web/guest/expert-council")
                        .andFrom("/expert-council")
                    .to("/page/people-expert-council");
    }

    private void redirectContentPagesSolveCoLab(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/web/guest/why-these-challenges")))
                .perform(Redirect.permanent("/page/why-these-challenges"));
    }

    private void redirectContentPagesCrowdsensor(ConfigurationBuilder configurationBuilder) {
        SimpleRewriteBuilder.of(configurationBuilder)
                .redirectFrom("/web/guest/smart-nation")
                    .to("/page/smart-nation");
    }

    private void redirectContentPagesResilienceDialogues(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/community")
                                                .or(Path.matches("/community"))))
                    .perform(Redirect.permanent("/page/community"))
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

        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/socialmediaguide")))
                    .perform(Redirect.permanent("/wiki/Social+Media+Guide"));
    }
}
