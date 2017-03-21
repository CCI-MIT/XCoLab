package org.xcolab.view.config.rewrite.rules.legacy;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;

import org.xcolab.view.config.rewrite.rules.RewriteRuleProvider;

public class LegacyMembersRewriteRules implements RewriteRuleProvider {

    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
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
}
