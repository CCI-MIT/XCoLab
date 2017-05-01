package org.xcolab.view.config.rewrite.rules;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.Path;

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
                    ))
                    .perform(Forward.to("/contests{path}"))
                    .where("path").matches(".*");

        configurationBuilder
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches(PROPOSAL_IN_PHASE_PATH + "/tab/{tab}")))
                    .perform(Forward.to(PROPOSAL_PATH + "?phaseId={phaseId}&tab={tab}"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches(PROPOSAL_PATH + "/tab/{tab}")))
                    .perform(Forward.to(PROPOSAL_PATH + "?tab={tab}"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches(PROPOSAL_IN_PHASE_PATH + "/tab/{tab}/edit")))
                    .perform(Forward.to(PROPOSAL_PATH + "?phaseId={phaseId}&tab={tab}&edit=true"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches(PROPOSAL_PATH + "/tab/{tab}/edit")))
                    .perform(Forward.to(PROPOSAL_PATH + "?tab={tab}&edit=true"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches(PROPOSAL_IN_PHASE_PATH)))
                    .perform(Forward.to(PROPOSAL_PATH + "?phaseId={phaseId}"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches(PROPOSAL_PATH + "/version/{version}")
                    ))
                    .perform(Forward.to(PROPOSAL_PATH + "?version={version}"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches(PROPOSAL_PATH + "/edit")
                    ))
                    .perform(Forward.to(PROPOSAL_PATH + "?edit=true"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches(PROPOSAL_PATH + "/voted")
                    ))
                    .perform(Forward.to(PROPOSAL_PATH + "?voted=true"))
                .addRule()
                    .when(Direction.isInbound().and(
                            Path.matches(PROPOSAL_PATH
                                + "/moveFromContestPhaseId/{moveFromContestPhaseId}/move/{moveType}")
                    ))
                    .perform(Forward.to(PROPOSAL_PATH
                        + "?moveFromContestPhaseId={moveFromContestPhaseId}&moveType={moveType}"));

    }
}
