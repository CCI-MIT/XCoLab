package org.xcolab.service.proposal.domain.group;

import org.xcolab.model.tables.pojos.Group_;

public interface GroupDao {
    Group_ create(Group_ group_);
    boolean update(Group_ group_);
}
