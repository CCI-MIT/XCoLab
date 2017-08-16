package org.xcolab.view.config.rewrite.rules.legacy;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;

import org.xcolab.view.config.rewrite.SimpleRewriteBuilder;
import org.xcolab.view.config.rewrite.rules.RewriteRuleProvider;

public class MiscLegacyRewriteRules implements RewriteRuleProvider {

    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        rewriteSocialNetworkPrizeUrls(configurationBuilder);
    }

    private void rewriteSocialNetworkPrizeUrls(ConfigurationBuilder configurationBuilder) {
        SimpleRewriteBuilder.of(configurationBuilder)
                .redirectFrom("/socialnetworkprize")
                    .andFrom("/socialnetworkprize2016")
                    .to("/snp/socialnetworkprize");
    }
}
