package org.xcolab.service.modeling.domain;

import org.xcolab.client.modeling.pojo.IModelDiscussion;

import java.util.List;
import java.util.Optional;

public interface ModelDiscussionDao {

    Optional<IModelDiscussion> get(long id);

    List<IModelDiscussion> list();

    IModelDiscussion create(IModelDiscussion pojo);

    boolean update(IModelDiscussion pojo);

    boolean delete(long id);
}
