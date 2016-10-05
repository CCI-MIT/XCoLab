package org.xcolab.service.contest.domain.impacttemplatefocusarealist;


import org.xcolab.model.tables.pojos.ImpactTemplateFocusAreaList;
import org.xcolab.service.contest.exceptions.NotFoundException;

public interface ImpactTemplateFocusAreaListDao {
    ImpactTemplateFocusAreaList get(Long focusAreaListId) throws NotFoundException;
}
