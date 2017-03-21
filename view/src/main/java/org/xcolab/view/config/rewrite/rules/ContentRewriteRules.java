package org.xcolab.view.config.rewrite.rules;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;

public class ContentRewriteRules implements RewriteRuleProvider {

    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/web/guest/feedback"))
                        .or(Path.matches("/page/feedback")))
                .perform(Redirect.permanent("/feedback"));
    }
}
