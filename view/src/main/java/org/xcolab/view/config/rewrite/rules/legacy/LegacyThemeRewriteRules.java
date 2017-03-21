package org.xcolab.view.config.rewrite.rules.legacy;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.Path;

import org.xcolab.view.config.rewrite.rules.RewriteRuleProvider;

public class LegacyThemeRewriteRules implements RewriteRuleProvider {

    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                .when(Direction.isInbound().and(
                        Path.matches("/{colabName}-theme/images/{path}")))
                .perform(Forward.to("/images/{path}"))
                .where("path").matches(".*")
                .where("colabName").matches("(climatecolab|fow|solve|rd)");
    }
}
