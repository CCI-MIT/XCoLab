package org.xcolab.view.config.rewrite.rules;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.Path;

public class ProposalRewriteRules implements RewriteRuleProvider {

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
                .where("path").matches(".*")
                .addRule()
                .when(Direction.isInbound().and(
                        Path.matches("/contests/{year}/{urlName}/phase/{phaseId}/{proposalUrlString}/{proposalId}/tab/{tab}")))
                .perform(Forward.to("/contests/{year}/{urlName}/c/{proposalUrlString}/{proposalId}?phaseId={phaseId}&tab={tab}"))
                .addRule()
                .when(Direction.isInbound().and(
                        Path.matches("/contests/{year}/{urlName}/c/{proposalUrlString}/{proposalId}/tab/{tab}")))
                .perform(Forward.to("/contests/{year}/{urlName}/c/{proposalUrlString}/{proposalId}?tab={tab}"))
                .addRule()
                .when(Direction.isInbound().and(
                        Path.matches("/contests/{year}/{urlName}/phase/{phaseId}/{proposalUrlString}/{proposalId}")))
                .perform(Forward.to("/contests/{year}/{urlName}/c/{proposalUrlString}/{proposalId}?phaseId={phaseId}"))
                .addRule()
                .when(Direction.isInbound().and(
                        Path.matches("/contests/{year}/{urlName}/c/{proposalUrlString}/{proposalId}/version/{version}")
                ))
                .perform(Forward.to("/contests/{year}/{urlName}/c/{proposalUrlString}/{proposalId}?version={version}"))
                .addRule()
                .when(Direction.isInbound().and(
                        Path.matches("/contests/{year}/{urlName}/c/{proposalUrlString}/{proposalId}/edit")
                ))
                .perform(Forward.to("/contests/{year}/{urlName}/c/{proposalUrlString}/{proposalId}?edit=true"))
                .addRule()
                .when(Direction.isInbound().and(
                        Path.matches("/contests/{year}/{urlName}/c/{proposalUrlString}/{proposalId}/voted")
                ))
                .perform(Forward.to("/contests/{year}/{urlName}/c/{proposalUrlString}/{proposalId}?voted=true"))
                .addRule()
                .when(Direction.isInbound().and(
                        Path.matches("/contests/{year}/{urlName}/c/{proposalUrlString}/{proposalId}/moveFromContestPhaseId/{moveFromContestPhaseId}/move/{moveType}")
                ))
                .perform(Forward.to("/contests/{year}/{urlName}/c/{proposalUrlString}/{proposalId}?moveFromContestPhaseId={moveFromContestPhaseId}&moveType={moveType}"));

    }
}
