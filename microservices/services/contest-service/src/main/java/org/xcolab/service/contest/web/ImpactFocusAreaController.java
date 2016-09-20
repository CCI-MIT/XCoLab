package org.xcolab.service.contest.web;

import com.netflix.discovery.converters.Auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.xcolab.client.contest.pojo.ImpactTemplateFocusAreaList;
import org.xcolab.model.tables.pojos.ImpactTemplateMaxFocusArea;
import org.xcolab.model.tables.pojos.FocusArea;
import org.xcolab.service.contest.domain.focusarea.FocusAreaDao;
import org.xcolab.service.contest.domain.impacttemplatefocusarealist.ImpactTemplateFocusAreaListDao;
import org.xcolab.service.contest.domain.impacttemplatemaxfocusarea.ImpactTemplateMaxFocusAreaDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;


@RestController
public class ImpactFocusAreaController {

    @Autowired
    private ImpactTemplateMaxFocusAreaDao impactTemplateMaxFocusAreaDao;

    @Autowired
    private ImpactTemplateFocusAreaListDao impactTemplateFocusAreaListDao;

    @Autowired
    private FocusAreaDao focusAreaDao;

    @RequestMapping(value = "/impactTemplateMaxFocusAreas", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusAreas(
            @RequestParam(required = false) Long focusAreaListId
    ) {
        return impactTemplateMaxFocusAreaDao.findByGiven(focusAreaListId);
    }

    @RequestMapping(value = "/impactTemplateFocusAreaLists/{impactTemplateFocusAreaListId}", method = RequestMethod.GET)
    public ImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(@PathVariable("impactTemplateFocusAreaListId") Long impactTemplateFocusAreaListId) throws NotFoundException {
        if (impactTemplateFocusAreaListId == null || impactTemplateFocusAreaListId == 0) {
            throw new NotFoundException("No impactTemplateFocusAreaListId given");
        } else {
            return impactTemplateFocusAreaListDao.get(impactTemplateFocusAreaListId);
        }
    }

    @RequestMapping(value = "/focusAreas/{focusAreaId}", method = RequestMethod.GET)
    public FocusArea getFocusArea(@PathVariable("focusAreaId") Long focusAreaId) throws NotFoundException {
        if (focusAreaId == null || focusAreaId == 0) {
            throw new NotFoundException("No focusAreaId given");
        } else {
            return focusAreaDao.get(focusAreaId);
        }
    }

    @RequestMapping(value = "/focusAreas", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<FocusArea> getFocusAreas() {
        return focusAreaDao.findByGiven();
    }
}
