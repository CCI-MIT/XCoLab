package org.xcolab.view.config.rewrite;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestType;

import javax.servlet.ServletContext;

public class RewriteConfigProvider extends HttpConfigurationProvider {

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public Configuration getConfiguration(ServletContext servletContext) {
        final ConfigurationBuilder configurationBuilder = ConfigurationBuilder.begin();
        redirectLegacyRegistration(configurationBuilder);
        redirectLegacyWikiPages(configurationBuilder);
        redirectLegacyUserProfile(configurationBuilder);
        redirectLegacyProposals(configurationBuilder);
        redirectLegacyMembers(configurationBuilder);
        redirectLegacyDiscussions(configurationBuilder);

        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/feedback")))
                    .perform(Redirect.permanent("/feedback"));
        return configurationBuilder;
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
                    .when(Direction.isInbound().and(Path.matches("/web/guest/member/-/member/userId/{memberId}")))
                    .perform(Redirect.permanent("/members/profile/{memberId}"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/member/-/member/userId/{memberId}/page/edit")))
                    .perform(Redirect.permanent("/members/profile/{memberId}/edit"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/member/-/member/userId/{memberId}/page/subscriptions")))
                    .perform((Redirect.permanent("/members/profile/{memberId}/subscriptions")))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/member/-/member/userId/{memberId}/page/subscriptionsManage")))
                    .perform((Redirect.permanent("/members/profile/{memberId}/subscriptions/manage")));
    }

    private void redirectLegacyMembers(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/messaging")))
                    .perform(Redirect.permanent("/messaging"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/messaging/-/messaging/message/{messageId}")))
                    .perform(Redirect.permanent("/messaging/message/{messageId}"));
    }

    private void redirectLegacyDiscussions(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/discussion")))
                    .perform(Redirect.permanent("/discussion"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/discussion/-/discussion/thread/{threadId}")))
                    .perform(Redirect.permanent("/discussion/thread/{threadId}"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("http://climatecolab.org/web/guest/discussion/-/discussion/category/{categoryId}")))
                    .perform(Redirect.permanent("/discussion/category/{categoryId}"));
    }

    private void redirectLegacyProposals(ConfigurationBuilder configurationBuilder) {
        final ContestType contestType = ContestClientUtil
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
                    .when(Direction.isInbound().and(Path.matches("/web/guest/plans/-/plans/contestsType/prior")))
                    .perform(Redirect.permanent(contestType.getPortletUrl() + "?viewType=GRID&filter=&showActiveContests=false"))
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/plans/-/plans/contestsType/all")))
                    .perform(Redirect.permanent(contestType.getPortletUrl() + "?viewType=GRID&filter=&showActiveContests=false&showAllContests=true"));
    }
}
