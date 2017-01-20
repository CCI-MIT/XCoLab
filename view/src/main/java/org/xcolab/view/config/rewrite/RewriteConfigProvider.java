package org.xcolab.view.config.rewrite;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;

import javax.servlet.ServletContext;

public class RewriteConfigProvider extends HttpConfigurationProvider {

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public Configuration getConfiguration(ServletContext servletContext) {

        final ConfigurationBuilder configurationBuilder = ConfigurationBuilder.begin();
        redirectLegacyRegistration(configurationBuilder);
        redirectLegacyWikiPages(configurationBuilder);
        redirectLegacyUserProfile(configurationBuilder);
        redirectLegacyProposals(configurationBuilder);
        return configurationBuilder;

    }

    private void redirectLegacyRegistration(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Direction.isInbound()
                            .and(Path.matches("/web/guest/loginregister{path}")
                                    .or(Path.matches("/loginregister{path}"))))
                    .perform(Redirect.permanent("/register"))
                    .where("path").matches(".*");
    }

    private void redirectLegacyWikiPages(ConfigurationBuilder configurationBuilder) {
        //TODO: incomplete - these are just the redirects from the hooks! Rewrite for new wiki implementation
        configurationBuilder
                .addRule()
                    .when(Path.matches("/web/guest/resources/-/wiki/Main/{page}")
                        .or(Path.matches("/resources/-/wiki/Main/{page}")))
                    .perform(Forward.to("/web/guest/wiki/-/wiki/page/{page}"))
                    .where("page").matches(".*")
                .addRule()
                    .when(Path.matches("/handbook/-/wiki/Main/{page}")
                        .or(Path.matches("/web/guest/handbook/-/wiki/Main/{page}")))
                    .perform(Forward.to("/web/guest/handbook/-/wiki/page/{page}"))
                    .where("page").matches(".*");
    }

    private void redirectLegacyUserProfile(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .addRule()
                    .when(Path.matches("/web/guest/member/-/member/userId/{memberId}"))
                    .perform(Redirect.permanent("/members/profile/{memberId}"))
                .addRule()
                    .when(Path.matches("/web/guest/member/-/member/userId/{memberId}/page/edit"))
                    .perform(Redirect.permanent("/members/profile/{memberId}/edit"));
    }

    //TODO: when proposals portlet is ported, we need compatibility with these old redirects:

    private void redirectLegacyProposals(ConfigurationBuilder configurationBuilder) {

        configurationBuilder
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/challenges{path}")
                        .or(Path.matches("/events{path}"))
                        .or(Path.matches("/trends{path}"))
                        .or(Path.matches("/dialogues{path}"))
                ))
                .perform(Forward.to("/contests{path}"))
                .where("path").matches(".*");

    }

//    <rule>
//        <from>^/contests(.*)$</from>
//        <to>/web/guest/plans/-/plans/contests$1</to>
//    </rule>
//
//    <rule>
//        <from>^/events(.*)$</from>
//        <to>/web/guest/events/-/plans/contests$1</to>
//    </rule>
//
//    <rule>
//        <from>^/trends(.*)$</from>
//        <to>/web/guest/trends/-/plans/contests$1</to>
//    </rule>
//
//    <rule>
//        <from>^/challenges(.*)$</from>
//        <to>/web/guest/challenges/-/plans/contests$1</to>
//    </rule>
//
//    <rule>
//        <from>^/dialogues(.*)$</from>
//        <to>/web/guest/dialogues/-/plans/contests$1</to>
//    </rule>
//
//    <rule>
//        <from>^(.*)plans/contestId/1302903/phaseId/1313504(.*)$</from>
//        <to>$1plans/contestId/1302903$2</to>
//    </rule>

}
