package org.xcolab.service.modeling.domain;

import org.xcolab.model.tables.pojos.ModelDiscussion;

import java.util.List;
import java.util.Optional;

public interface ModelDiscussionDao {

    Optional<ModelDiscussion> get(long id);

    List<ModelDiscussion> list();

    ModelDiscussion create(ModelDiscussion pojo);

    boolean update(ModelDiscussion pojo);

    boolean delete(long id);
}
