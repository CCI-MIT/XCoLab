package org.xcolab.view.config.rewrite.rules;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

public class ProposalRewriteRules implements RewriteRuleProvider {

    private static final String CONTEST_PATH = "/contests/{year}/{urlName}";
    private static final String PROPOSAL_IN_PHASE_PATH = CONTEST_PATH
        + "/phase/{phaseId}/{proposalUrlString}/{proposalId}";
    private static final String PROPOSAL_PATH = CONTEST_PATH
        + "/c/{proposalUrlString}/{proposalId}";

    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(Path.matches("/challenges{path}")
                            .or(Path.matches("/events{path}"))
                            .or(Path.matches("/trends{path}"))
                            .or(Path.matches("/dialogues{path}"))
                            .or(Path.matches("/contributions{path}"))
                    ))
                    .perform(Forward.to("/contests{path}"))
                    .where("path").matches(".*");

        configureEditUrls(configurationBuilder);
        configureTabUrls(configurationBuilder);

        configurationBuilder
                .addRule(Join.path(PROPOSAL_PATH + "/version/{version}")
                        .to(PROPOSAL_PATH))
                .addRule(Join.path(PROPOSAL_PATH + "/voted")
                        .to(PROPOSAL_PATH + "?voted=true"))
                .addRule(Join.path(PROPOSAL_PATH
                            + "/moveFromContestPhaseId/{moveFromContestPhaseId}/move/{moveType}")
                        .to(PROPOSAL_PATH));
    }

    private void configureEditUrls(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule(Join.path(PROPOSAL_PATH + "/edit")
                        .to(PROPOSAL_PATH + "?edit=true"))
                .addRule(Join.path(PROPOSAL_IN_PHASE_PATH + "/edit")
                        .to(PROPOSAL_PATH + "?edit=true"));
    }

    private void configureTabUrls(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule(Join.path(PROPOSAL_IN_PHASE_PATH + "/tab/{tab}")
                        .to(PROPOSAL_PATH))
                .addRule(Join.path(PROPOSAL_PATH + "/tab/{tab}")
                        .to(PROPOSAL_PATH))
                .addRule(Join.path(PROPOSAL_PATH + "/tab/{tab}/edit")
                        .to(PROPOSAL_PATH + "?edit=true"))
                .addRule(Join.path(PROPOSAL_IN_PHASE_PATH + "/tab/{tab}/edit")
                        .to(PROPOSAL_PATH + "?edit=true"));
    }
}
