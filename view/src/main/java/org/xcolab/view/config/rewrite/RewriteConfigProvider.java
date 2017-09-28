package org.xcolab.view.config.rewrite;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;

import org.xcolab.view.config.rewrite.rules.ContentRewriteRules;
import org.xcolab.view.config.rewrite.rules.DeprecatedMappingRewriteRules;
import org.xcolab.view.config.rewrite.rules.ProposalRewriteRules;
import org.xcolab.view.config.rewrite.rules.RewriteRuleProvider;
import org.xcolab.view.config.rewrite.rules.legacy.LegacyContentRewriteRules;
import org.xcolab.view.config.rewrite.rules.legacy.LegacyDiscussionRewriteRules;
import org.xcolab.view.config.rewrite.rules.legacy.LegacyMembersRewriteRules;
import org.xcolab.view.config.rewrite.rules.legacy.LegacyNewsRewriteRules;
import org.xcolab.view.config.rewrite.rules.legacy.LegacyPlatformRewriteRules;
import org.xcolab.view.config.rewrite.rules.legacy.LegacyProposalRewriteRules;
import org.xcolab.view.config.rewrite.rules.legacy.LegacyRegistrationRewriteRules;
import org.xcolab.view.config.rewrite.rules.legacy.LegacyThemeRewriteRules;
import org.xcolab.view.config.rewrite.rules.legacy.MiscLegacyRewriteRules;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

public class RewriteConfigProvider extends HttpConfigurationProvider {

    private final List<RewriteRuleProvider> ruleProviders = new ArrayList<>();

    public RewriteConfigProvider() {
        ruleProviders.add(new ProposalRewriteRules());
        ruleProviders.add(new ContentRewriteRules());

        ruleProviders.add(new DeprecatedMappingRewriteRules());

        ruleProviders.add(new LegacyPlatformRewriteRules());
        ruleProviders.add(new LegacyContentRewriteRules());
        ruleProviders.add(new LegacyDiscussionRewriteRules());
        ruleProviders.add(new LegacyMembersRewriteRules());
        ruleProviders.add(new LegacyNewsRewriteRules());
        ruleProviders.add(new LegacyProposalRewriteRules());
        ruleProviders.add(new LegacyRegistrationRewriteRules());
        ruleProviders.add(new LegacyThemeRewriteRules());
        ruleProviders.add(new MiscLegacyRewriteRules());
    }

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public Configuration getConfiguration(ServletContext servletContext) {
        final ConfigurationBuilder configurationBuilder = ConfigurationBuilder.begin();

        ruleProviders.forEach(
                rewriteRuleProvider -> rewriteRuleProvider.configure(configurationBuilder));
        return configurationBuilder;
    }

}
