package org.xcolab.mailhandler.pojo;

import org.xcolab.mailhandler.config.MailProperties.Domain;
import org.xcolab.mailhandler.config.MailProperties.Mapping;

public class MatchedMapping {

    private final Domain domain;
    private final Mapping mapping;

    public MatchedMapping(Domain domain, Mapping mapping) {
        this.domain = domain;
        this.mapping = mapping;
    }

    public Domain getDomain() {
        return domain;
    }

    public Mapping getMapping() {
        return mapping;
    }

    public String getEmailAddress() {
        return mapping.getUsername() + "@" + domain.getDomain();
    }
}
