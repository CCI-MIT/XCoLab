package org.xcolab.service.contest.web;

import com.netflix.discovery.converters.Auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import org.xcolab.model.tables.pojos.ImpactDefaultSeries;
import org.xcolab.model.tables.pojos.ImpactDefaultSeriesData;
import org.xcolab.model.tables.pojos.ImpactTemplateFocusAreaList;
import org.xcolab.model.tables.pojos.ImpactTemplateMaxFocusArea;
import org.xcolab.model.tables.pojos.FocusArea;
import org.xcolab.model.tables.pojos.FocusAreaOntologyTerm;
import org.xcolab.model.tables.pojos.OntologySpace;
import org.xcolab.model.tables.pojos.OntologyTerm;
import org.xcolab.service.contest.domain.focusarea.FocusAreaDao;
import org.xcolab.service.contest.domain.focusareaontologyterm.FocusAreaOntologyTermDao;
import org.xcolab.service.contest.domain.impactdefaultseries.ImpactDefaultSeriesDao;
import org.xcolab.service.contest.domain.impactdefaultseriesdata.ImpactDefaultSeriesDataDao;
import org.xcolab.service.contest.domain.impacttemplatefocusarealist.ImpactTemplateFocusAreaListDao;
import org.xcolab.service.contest.domain.impacttemplatemaxfocusarea.ImpactTemplateMaxFocusAreaDao;
import org.xcolab.service.contest.domain.ontologyspace.OntologySpaceDao;
import org.xcolab.service.contest.domain.ontologyterm.OntologyTermDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

@RestController
public class OntologyController {

    @Autowired
    private OntologyTermDao ontologyTermDao;

    @Autowired
    private OntologySpaceDao ontologySpaceDao;

    @Autowired
    private FocusAreaDao focusAreaDao;

    @Autowired
    private FocusAreaOntologyTermDao focusAreaOntologyTermDao;

    @Autowired
    private ImpactTemplateMaxFocusAreaDao impactTemplateMaxFocusAreaDao;

    @Autowired
    private ImpactTemplateFocusAreaListDao impactTemplateFocusAreaListDao;

    @Autowired
    private ImpactDefaultSeriesDao impactDefaultSeriesDao;

    @Autowired
    private ImpactDefaultSeriesDataDao impactDefaultSeriesDataDao;


    @RequestMapping(value = "/ontologyTerms", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<OntologyTerm> getOntologyTerms(@RequestParam String name) {
        return ontologyTermDao.findByGiven(name);
    }


    @RequestMapping(value = "/ontologyTerms/{ontologyTermId}", method = RequestMethod.GET)
    public OntologyTerm getOntologyTerm(@PathVariable("ontologyTermId") Long ontologyTermId) throws NotFoundException {
        if (ontologyTermId == null || ontologyTermId == 0) {
            throw new NotFoundException("No ontologyTermId given");
        } else {
            return ontologyTermDao.get(ontologyTermId);
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
    public List<FocusArea> getFocusAreas(
    ) {
        return focusAreaDao.findByGiven();
    }


    @RequestMapping(value = "/ontologySpaces", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<OntologySpace> getOntologySpaces(
    ) {
        return ontologySpaceDao.findByGiven();
    }

    @RequestMapping(value = "/focusAreaOntologyTerms", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<FocusAreaOntologyTerm> getFocusAreaOntologyTerms(@RequestParam Long focusAreaId,
                                                                 @RequestParam Long ontologTermId
    ) {
        return focusAreaOntologyTermDao.findByGiven(focusAreaId,ontologTermId);
    }
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

    @RequestMapping(value = "/ontologySpaces/{ontologySpaceId}", method = RequestMethod.GET)
    public OntologySpace getOntologySpace(@PathVariable("ontologySpaceId") Long ontologySpaceId) throws NotFoundException {
        if (ontologySpaceId == null || ontologySpaceId == 0) {
            throw new NotFoundException("No ontologySpaceId given");
        } else {
            return ontologySpaceDao.get(ontologySpaceId);
        }
    }

    @RequestMapping(value = "/impactDefaultSeriesDatas", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ImpactDefaultSeries> getImpactDefaultSeries(
            @RequestParam(required = false) Long focusAreaId,
            @RequestParam(required = false) String name
    ) {
        return impactDefaultSeriesDao.findByGiven(focusAreaId,name);
    }

    @RequestMapping(value = "/impactDefaultSeriesDatas", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ImpactDefaultSeriesData> getImpactDefaultSeriesDatas(
            @RequestParam(required = false) Long seriesId,
            @RequestParam(required = false) Integer year
    ) {
        return impactDefaultSeriesDataDao.findByGiven(seriesId,year);
    }


}
