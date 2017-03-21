package org.xcolab.view.config.rewrite.rules.legacy;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;

import org.xcolab.view.config.rewrite.rules.RewriteRuleProvider;
import org.xcolab.view.config.rewrite.SimpleRewriteBuilder;

public class LegacyNewsRewriteRules implements RewriteRuleProvider {

    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        BlogRewriteBuilder.of(configurationBuilder)
                .fromLiferayUrlTitle("/community/-/blogs/2016-climate-colab-conference-in-review")
                    .toPath("/2016/10/2016-crowds-climate-conference-in-review/")
        ;
    }

    private static class BlogRewriteBuilder {

        private final SimpleRewriteBuilder rewriteBuilder;

        private BlogRewriteBuilder(ConfigurationBuilder configurationBuilder) {
            this.rewriteBuilder = SimpleRewriteBuilder.of(configurationBuilder);
        }

        public static BlogRewriteBuilder of(ConfigurationBuilder configurationBuilder) {
            return new BlogRewriteBuilder(configurationBuilder);
        }

        public ToStep fromLiferayUrlTitle(String urlTitle) {
            return new ToStep(urlTitle);
        }

        private class ToStep {

            private final String urlTitle;

            public ToStep(String urlTitle) {
                this.urlTitle = urlTitle;
            }

            public BlogRewriteBuilder toPath(String newUriPath) {
                rewriteBuilder
                        .redirectFrom("/community/-/blogs/" + urlTitle)
                            .andFrom("/news/-/blogs/" + urlTitle)
                            .to("http://news.climatecolab.com" + newUriPath);
                return BlogRewriteBuilder.this;
            }
        }
    }
}
