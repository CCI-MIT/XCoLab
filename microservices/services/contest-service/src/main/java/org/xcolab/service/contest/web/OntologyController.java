package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.FocusArea;
import org.xcolab.model.tables.pojos.FocusAreaOntologyTerm;
import org.xcolab.model.tables.pojos.ImpactDefaultSeries;
import org.xcolab.model.tables.pojos.ImpactDefaultSeriesData;
import org.xcolab.model.tables.pojos.ImpactTemplateFocusAreaList;
import org.xcolab.model.tables.pojos.ImpactTemplateMaxFocusArea;
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
import org.xcolab.service.contest.service.ontology.OntologyService;

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

    @Autowired
    private OntologyService ontologyService;

    @RequestMapping(value = "/ontologyTerms/getAllOntologyTermDescendant", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<OntologyTerm> getOntologyTerms(@RequestParam Long ontologyTermId) throws  NotFoundException {
        OntologyTerm ontologyTerm = ontologyTermDao.get(ontologyTermId);
        return ontologyService.getAllOntologyTermDescendantTerms(ontologyTerm);
    }


    @RequestMapping(value = "/ontologyTerms", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<OntologyTerm> getOntologyTerms(@RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "0") Long parentId,
            @RequestParam(required = false) Long ontologySpaceId) {
        return ontologyTermDao.findByGiven(name,parentId, ontologySpaceId);
    }


    @RequestMapping(value = "/ontologyTerms/{ontologyTermId}", method = RequestMethod.GET)
    public OntologyTerm getOntologyTerm(@PathVariable("ontologyTermId") Long ontologyTermId) throws NotFoundException {
        if (ontologyTermId == null || ontologyTermId == 0) {
            throw new NotFoundException("No ontologyTermId given");
        } else {
            return ontologyTermDao.get(ontologyTermId);
        }
    }

    @RequestMapping(value = "/ontologyTerms/{id_}", method = RequestMethod.PUT)
    public boolean updateOntologyTerm(@RequestBody OntologyTerm ontologyTerm,
            @PathVariable("id_") Long id_) throws NotFoundException {

        if (id_ == null || id_ == 0 || ontologyTermDao.get(id_) == null) {
            throw new NotFoundException("No OntologyTerm with id " + id_);
        } else {
            return ontologyTermDao.update(ontologyTerm);
        }
    }

    @RequestMapping(value = "/ontologyTerms/{id_}", method = RequestMethod.DELETE)
    public String deleteOntologyTerm(@PathVariable("id_") Long id_)
            throws NotFoundException {

        if (id_ == null || id_ == 0) {
            throw new NotFoundException("No OntologyTerm with id given");
        } else {
            OntologyTerm ontologyTerm = this.ontologyTermDao.get(id_);
            if (ontologyTerm != null) {
                this.ontologyTermDao.delete(ontologyTerm.getId_());
                return "OntologyTerm deleted successfully";
            } else {
                throw new NotFoundException("No OntologyTerm with id given");
            }
        }
    }

    @RequestMapping(value = "/ontologyTerms", method = RequestMethod.POST)
    public OntologyTerm createOntologyTerm(@RequestBody OntologyTerm ontologyTerm) {
        return this.ontologyTermDao.create(ontologyTerm);
    }

    @RequestMapping(value = "/focusAreas/{focusAreaId}", method = RequestMethod.GET)
    public FocusArea getFocusArea(@PathVariable("focusAreaId") Long focusAreaId) throws NotFoundException {
        /*
        if(focusAreaId == 0 ){
            focusAreaId = 2l; //This is done because a lot of old contests use focus area id = 0 that the auto increment no longer allows.
        }
        if (focusAreaId == null ) {
            throw new NotFoundException("No focusAreaId given");
        } else {
            return focusAreaDao.get(focusAreaId);
        }
        */
        return focusAreaDao.get(focusAreaId);
    }

    @RequestMapping(value = "/focusAreas", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<FocusArea> getFocusAreas(
    ) {
        return focusAreaDao.findByGiven();
    }

    @RequestMapping(value = "/focusAreas", method = RequestMethod.POST)
    public FocusArea createFocusArea(@RequestBody FocusArea focusArea) {
        return this.focusAreaDao.create(focusArea);
    }


    @RequestMapping(value = "/ontologySpaces", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<OntologySpace> getOntologySpaces(
    ) {
        return ontologySpaceDao.findByGiven();
    }

    @RequestMapping(value = "/focusAreaOntologyTerms", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<FocusAreaOntologyTerm> getFocusAreaOntologyTerms(@RequestParam(required = false) Long focusAreaId,
                                                                 @RequestParam(required = false) Long ontologTermId
    ) {
        return focusAreaOntologyTermDao.findByGiven(focusAreaId,ontologTermId);
    }
    @RequestMapping(value = "/focusAreaOntologyTerms", method = RequestMethod.POST)
    public FocusAreaOntologyTerm createFocusAreaOntologyTerm(@RequestBody FocusAreaOntologyTerm focusAreaOntologyTerm) {
        return this.focusAreaOntologyTermDao.create(focusAreaOntologyTerm);
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

    @RequestMapping(value = "/impactDefaultSeries", method = {RequestMethod.GET, RequestMethod.HEAD})
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
