package org.xcolab.view.config.rewrite.rules.legacy;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;

import org.xcolab.view.config.rewrite.SimpleRewriteBuilder;
import org.xcolab.view.config.rewrite.rules.RewriteRuleProvider;

public class LegacyPlatformRewriteRules implements RewriteRuleProvider {

    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        SimpleRewriteBuilder.of(configurationBuilder)
                .redirectFrom("/home")
                    .andFrom("/web/guest")
                    .to("/");

        // Liferay's mobile page
        addWildcardRewrite(configurationBuilder, "m");

        // Liferay's alternative language pages indexed by google
        addLanguageRewrites(configurationBuilder);
    }

    private void addLanguageRewrites(ConfigurationBuilder configurationBuilder) {
        addWildcardRewrite(configurationBuilder, "ca");
        addWildcardRewrite(configurationBuilder, "de");
        addWildcardRewrite(configurationBuilder, "es");
        addWildcardRewrite(configurationBuilder, "hu");
        addWildcardRewrite(configurationBuilder, "ja");
        addWildcardRewrite(configurationBuilder, "fr");
        addWildcardRewrite(configurationBuilder, "zh");
        addWildcardRewrite(configurationBuilder, "iw");
        addWildcardRewrite(configurationBuilder, "fi");
        addWildcardRewrite(configurationBuilder, "pt");
        addWildcardRewrite(configurationBuilder, "tr");
        addWildcardRewrite(configurationBuilder, "nl");
    }

    private void addWildcardRewrite(ConfigurationBuilder configurationBuilder, String lang) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/" + lang + "/{path}")))
                    .perform(Redirect.permanent("/{path}"))
                    .where("path").matches(".*")
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/" + lang)))
                    .perform(Redirect.permanent("/"));
    }
}
