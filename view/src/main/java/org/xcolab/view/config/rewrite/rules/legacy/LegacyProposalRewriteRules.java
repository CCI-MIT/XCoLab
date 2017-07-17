package org.xcolab.view.config.rewrite.rules.legacy;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;

import org.xcolab.view.config.rewrite.rules.RewriteRuleProvider;

public class LegacyProposalRewriteRules implements RewriteRuleProvider {

    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound()
                            .and(Path.matches("/web/guest/{portletName}")))
                    .perform(Forward.to("/contests"))
                    .where("portletName").matches("(plans|dialogues|challenges|trends)")
                .addRule()
                    .when(Direction.isInbound()
                            .and(Path.matches("/web/guest/plans/-/plans/contestsType/prior")))
                    .perform(Forward.to("/contests?viewType=GRID&filter=&showActiveContests=false"))
                .addRule()
                    .when(Direction.isInbound()
                            .and(Path.matches("/web/guest/plans/-/plans/contestsType/all")))
                    .perform(Forward.to("/contests?viewType=GRID&filter="
                            + "&showActiveContests=false&showAllContests=true"))
                .addRule()
                    .when(Direction.isInbound()
                            .and(Path.matches("/plans")))
                    .perform(Redirect.permanent("/contests"));

        // legacy urls
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound()
                            .and(Path.matches("/web/guest/{portletName}/-/plans/contestId/{contestId}")))
                    .perform(Forward.to("/contests/legacy/contest/{contestId}"))
                    .where("portletName").matches("(plans|dialogues|challenges|trends)")
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/{portletName}/-/plans/contestId/{contestId}/phase/{phaseId}")))
                    .perform(Forward.to("/contests/legacy/contest/{contestId}?phaseId={phaseId}"))
                    .where("portletName").matches("(plans|dialogues|challenges|trends)")
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/{portletName}/-/plans/contestId/{contestId}/planId/{proposalId}")
                                    .or(Path.matches("/web/guest/{portletName}/-/plans/contestId/{contestId}/planId/{proposalId}/{path}"))))
                    .perform(Forward.to("/contests/legacy/contest/{contestId}/proposal/{proposalId}"))
                    .where("portletName").matches("(plans|dialogues|challenges|trends)")
                    .where("path").matches(".*")
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches("/web/guest/{portletName}/-/plans/contestId/{contestId}/phaseId/{phaseId}/planId/{proposalId}")
                                    .or(Path.matches("/web/guest/{portletName}/-/plans/contestId/{contestId}/phaseId/{phaseId}/planId/{proposalId}/{path}"))))
                    .perform(Forward.to("/contests/legacy/contest/{contestId}/proposal/{proposalId}?phaseId={phaseId}"))
                    .where("portletName").matches("(plans|dialogues|challenges|trends)")
                    .where("path").matches(".*")
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/plans/-/plans/contests{path}")))
                    .perform(Redirect.permanent("/contests{path}"))
                    .where("path").matches(".*");
    }
}
