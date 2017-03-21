package org.xcolab.view.config.rewrite.rules;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;

@FunctionalInterface
public interface RewriteRuleProvider {

    void configure(ConfigurationBuilder configurationBuilder);

}
