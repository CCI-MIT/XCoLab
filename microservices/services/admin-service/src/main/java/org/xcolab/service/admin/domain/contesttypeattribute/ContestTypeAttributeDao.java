package org.xcolab.service.admin.domain.contesttypeattribute;

import org.xcolab.model.tables.pojos.ContestTypeAttribute;

import java.util.List;
import java.util.Optional;

public interface ContestTypeAttributeDao {

    ContestTypeAttribute create(ContestTypeAttribute pojo);

    Optional<ContestTypeAttribute> get(String attributeName, long additionalId, String locale);

    boolean update(ContestTypeAttribute pojo);

    List<ContestTypeAttribute> list();

}
