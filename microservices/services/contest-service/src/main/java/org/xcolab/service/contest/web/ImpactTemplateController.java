package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.IImpactClient;
import org.xcolab.client.contest.pojo.IImpactIteration;
import org.xcolab.client.contest.pojo.IImpactTemplateFocusAreaList;
import org.xcolab.client.contest.pojo.IImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.IImpactTemplateSeries;
import org.xcolab.service.contest.domain.impactiteration.ImpactIterationDao;
import org.xcolab.service.contest.domain.impacttemplatefocusarealist.ImpactTemplateFocusAreaListDao;
import org.xcolab.service.contest.domain.impacttemplatemaxfocusarea.ImpactTemplateMaxFocusAreaDao;
import org.xcolab.service.contest.domain.impacttemplateseries.ImpactTemplateSeriesDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.util.List;

@RestController
public class ImpactTemplateController implements IImpactClient {

    private final ImpactTemplateSeriesDao impactTemplateSeriesDao;
    private final ImpactIterationDao impactIterationDao;
    private final ImpactTemplateFocusAreaListDao impactTemplateFocusAreaListDao;
    private final ImpactTemplateMaxFocusAreaDao impactTemplateMaxFocusAreaDao;

    @Autowired
    public ImpactTemplateController(
            ImpactTemplateSeriesDao impactTemplateSeriesDao,
            ImpactIterationDao impactIterationDao,
            ImpactTemplateFocusAreaListDao impactTemplateFocusAreaListDao,
            ImpactTemplateMaxFocusAreaDao impactTemplateMaxFocusAreaDao) {
        this.impactTemplateSeriesDao = impactTemplateSeriesDao;
        this.impactIterationDao = impactIterationDao;
        this.impactTemplateFocusAreaListDao = impactTemplateFocusAreaListDao;
        this.impactTemplateMaxFocusAreaDao = impactTemplateMaxFocusAreaDao;
    }

    @Override
    @GetMapping("/impactTemplateSeries/{impactTemplateSeriesId}")
    public IImpactTemplateSeries getImpactTemplateSeries(
            @PathVariable Long impactTemplateSeriesId) {
        if (impactTemplateSeriesId == null || impactTemplateSeriesId == 0) {
            throw new RuntimeEntityNotFoundException("impactTemplateSeriesId not given");
        } else {
            try {
                return impactTemplateSeriesDao.get(impactTemplateSeriesId);
            } catch (NotFoundException e) {
                throw new RuntimeEntityNotFoundException(
                        "ImpactTemplateSeries not found with id " + impactTemplateSeriesId);
            }
        }
    }

    @Override
    @GetMapping("/impactIterations")
    public List<IImpactIteration> getContestImpactIterations(
            @RequestParam(required = false) Long iterationId) {
        return impactIterationDao.findByGiven(iterationId);
    }

    @Override
    @GetMapping("/impactTemplateFocusAreaLists/{impactTemplateFocusAreaListId}")
    public IImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(
            @PathVariable Long impactTemplateFocusAreaListId) {
        if (impactTemplateFocusAreaListId == null || impactTemplateFocusAreaListId == 0) {
            throw new RuntimeEntityNotFoundException("impactTemplateFocusAreaListId not given");
        } else {
            try {
                return impactTemplateFocusAreaListDao.get(impactTemplateFocusAreaListId);
            } catch (NotFoundException e) {
                throw new RuntimeEntityNotFoundException(
                        "ImpactTemplateFocusAreaList not found with id "
                                + impactTemplateFocusAreaListId);
            }
        }
    }

    @Override
    @GetMapping("/impactTemplateMaxFocusAreas")
    public List<IImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusArea(
            @RequestParam(required = false) Long focusAreaListId) {
        return impactTemplateMaxFocusAreaDao.findByGiven(focusAreaListId);
    }
}
