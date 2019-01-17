package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.pojo.IImpactIteration;
import org.xcolab.client.contest.pojo.IImpactTemplateSeries;
import org.xcolab.service.contest.domain.impactiteration.ImpactIterationDao;
import org.xcolab.service.contest.domain.impacttemplateseries.ImpactTemplateSeriesDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

@RestController
public class ImpactTemplateController {


    @Autowired
    private ImpactTemplateSeriesDao impactTemplateSeriesDao;

    @Autowired
    private ImpactIterationDao impactIterationDao;

    @RequestMapping(value = "/impactTemplateSeries/{impactTemplateSeriesId}", method = RequestMethod.GET)
    public IImpactTemplateSeries getImpactTemplateSeries(@PathVariable("impactTemplateSeriesId") Long impactTemplateSeriesId) throws NotFoundException {
        if (impactTemplateSeriesId == null || impactTemplateSeriesId == 0) {
            throw new NotFoundException("No impactTemplateSeriesId given");
        } else {
            return impactTemplateSeriesDao.get(impactTemplateSeriesId);
        }
    }

    @RequestMapping(value = "/impactIterations", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<IImpactIteration> getImpactIterations(
            @RequestParam(required = false) Long iterationId
    ) {
        return impactIterationDao.findByGiven(iterationId);
    }
}
