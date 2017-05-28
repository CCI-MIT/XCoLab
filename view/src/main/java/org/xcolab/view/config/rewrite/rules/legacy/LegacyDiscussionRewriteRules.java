package org.xcolab.view.config.rewrite.rules.legacy;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;

import org.xcolab.view.config.rewrite.rules.RewriteRuleProvider;

public class LegacyDiscussionRewriteRules implements RewriteRuleProvider {

    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/web/guest/discussion")))
                    .perform(Redirect.permanent("/discussion"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/discussion/-/discussion/thread/{threadId}")
                                .or(Path.matches("/web/guest/discussion/thread/{threadId}"))))
                    .perform(Redirect.permanent("/discussion/thread/{threadId}"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/discussion/-/discussion/category/{categoryId}")
                                 .or(Path.matches("/web/guest/discussion/category/{categoryId}"))))
                    .perform(Redirect.permanent("/discussion/category/{categoryId}"));
    }
}
