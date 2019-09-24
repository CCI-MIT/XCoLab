package org.xcolab.service.contest.domain.impacttemplatefocusarealist;


import org.xcolab.client.contest.pojo.IImpactTemplateFocusAreaList;
import org.xcolab.service.contest.exceptions.NotFoundException;

public interface ImpactTemplateFocusAreaListDao {
    IImpactTemplateFocusAreaList get(Long focusAreaListId) throws NotFoundException;
}
