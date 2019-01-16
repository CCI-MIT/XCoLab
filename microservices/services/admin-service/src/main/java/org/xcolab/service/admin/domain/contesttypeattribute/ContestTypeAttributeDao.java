package org.xcolab.service.admin.domain.contesttypeattribute;

import org.xcolab.client.admin.pojo.IContestTypeAttribute;

import java.util.List;
import java.util.Optional;

public interface ContestTypeAttributeDao {

    IContestTypeAttribute create(IContestTypeAttribute pojo);

    Optional<IContestTypeAttribute> get(String attributeName, long additionalId, String locale);

    boolean update(IContestTypeAttribute pojo);

    List<IContestTypeAttribute> list();

}
