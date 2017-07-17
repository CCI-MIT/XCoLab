package org.xcolab.view.config.rewrite;

import org.ocpsoft.rewrite.config.ConditionBuilder;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleRewriteBuilder {

    private final ConfigurationBuilder configurationBuilder;

    private SimpleRewriteBuilder(ConfigurationBuilder configurationBuilder) {
        this.configurationBuilder = configurationBuilder;
    }

    public static SimpleRewriteBuilder of(ConfigurationBuilder configurationBuilder) {
        return new SimpleRewriteBuilder(configurationBuilder);
    }

    public SimpleRewriteBuilder redirect(String from, String to) {
        configurationBuilder.addRule()
                .when(Direction.isInbound().and(Path.matches(from)))
                .perform(Redirect.permanent(to));
        return this;
    }

    public SimpleRewriteBuilder redirect(List<String> fromList, String to) {
        Assert.notEmpty(fromList, "fromList cannot be empty");

        Iterator<String> fromIt = fromList.iterator();
        ConditionBuilder condition = Path.matches(fromIt.next());
        while (fromIt.hasNext()) {
            condition = condition.or(Path.matches(fromIt.next()));
        }

        configurationBuilder.addRule()
                .when(Direction.isInbound().and(condition))
                .perform(Redirect.permanent(to));
        return this;
    }

    public FromStep redirectFrom(String from) {
        return new FromStep(from);
    }

    public class FromStep {

        private final List<String> fromList = new ArrayList<>();

        private FromStep(String from) {
            fromList.add(from);
        }

        public FromStep andFrom(String from) {
            fromList.add(from);
            return this;
        }

        public SimpleRewriteBuilder to(String to) {
            return redirect(fromList, to);
        }
    }
}
