package org.xcolab.view.config.rewrite.rules;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;

import org.xcolab.view.config.rewrite.SimpleRewriteBuilder;

/**
 * This class implements mappings for deprecated handler mappings that are pending removal.
 */
public class DeprecatedMappingRewriteRules implements RewriteRuleProvider {

    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        addWidgetRewrites(configurationBuilder);
    }

    /**
     * Add rewrited for deprecated widget mappings.
     *
     * These rewrites can be removed once the mappings on productions were updated.
     */
    @Deprecated
    private void addWidgetRewrites(ConfigurationBuilder configurationBuilder) {
        SimpleRewriteBuilder.of(configurationBuilder)
                .redirectFrom("/staffmemberswidget")
                    .to("/widgets/staffmembers")
                .redirectFrom("/contestswidget")
                    .to("/widgets/contests")
                .redirectFrom("/randomproposalswidget")
                    .to("/widgets/proposals")
                .redirectFrom("/feedswidget")
                    .to("/widgets/feeds");
    }
}
