package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.commons.spring.web.annotation.ListMapping;
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

import java.util.ArrayList;
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

    @ListMapping("/ontologyTerms/getAllOntologyTermDescendant")
    public List<OntologyTerm> getOntologyTerms(@RequestParam Long ontologyTermId) throws  NotFoundException {
        OntologyTerm ontologyTerm = ontologyTermDao.get(ontologyTermId);
        return ontologyService.getAllOntologyTermDescendantTerms(ontologyTerm);
    }

    @ListMapping("/ontologyTerms/getOntologyTermsByFocusAreaOntologySpaceName")
    public List<OntologyTerm> getOntologyTermsByFocusAreaOntologySpaceName(@RequestParam Long focusAreaId, @RequestParam String ontologySpaceName) {
        return ontologyTermDao.getOntologyTermByFocusAreaAndOntologySpaceName(focusAreaId,ontologySpaceName );
    }

    @ListMapping("/ontologyTerms")
    public List<OntologyTerm> getOntologyTerms(@RequestParam(required = false) String name,
            @RequestParam(required = false) Long parentId,
            @RequestParam(required = false) Long ontologySpaceId) {
        return ontologyTermDao.findByGiven(name,parentId, ontologySpaceId);
    }

    @GetMapping("/ontologyTerms/{ontologyTermId}")
    public OntologyTerm getOntologyTerm(@PathVariable("ontologyTermId") Long ontologyTermId) throws NotFoundException {
        if (ontologyTermId == null || ontologyTermId == 0) {
            throw new NotFoundException("No ontologyTermId given");
        } else {
            return ontologyTermDao.get(ontologyTermId);
        }
    }

    @PutMapping("/ontologyTerms/{id}")
    public boolean updateOntologyTerm(@RequestBody OntologyTerm ontologyTerm,
            @PathVariable("id") Long id) throws NotFoundException {

        if (id == null || id == 0 || ontologyTermDao.get(id) == null) {
            throw new NotFoundException("No OntologyTerm with id " + id);
        } else {
            return ontologyTermDao.update(ontologyTerm);
        }
    }

    @DeleteMapping("/ontologyTerms/{id}")
    public String deleteOntologyTerm(@PathVariable("id") Long id)
            throws NotFoundException {

        if (id == null || id == 0) {
            throw new NotFoundException("No OntologyTerm with id given");
        } else {
            OntologyTerm ontologyTerm = this.ontologyTermDao.get(id);
            if (ontologyTerm != null) {
                this.ontologyTermDao.delete(ontologyTerm.getId());
                return "OntologyTerm deleted successfully";
            } else {
                throw new NotFoundException("No OntologyTerm with id given");
            }
        }
    }

    @PostMapping("/ontologyTerms")
    public OntologyTerm createOntologyTerm(@RequestBody OntologyTerm ontologyTerm) {
        return this.ontologyTermDao.create(ontologyTerm);
    }

    @GetMapping("/focusAreas/{focusAreaId}")
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

    @ListMapping("/focusAreas")
    public List<FocusArea> getFocusAreas(
        @RequestParam(required = false) Long ontologyTermId) throws NotFoundException{
        List<FocusArea> focusAreas = new ArrayList<>();
        for (FocusAreaOntologyTerm term: focusAreaOntologyTermDao.findByGiven(null, ontologyTermId)) {
            focusAreas.add(focusAreaDao.get(term.getFocusAreaId()));
        }
        return focusAreas;
    }

    @PostMapping("/focusAreas")
    public FocusArea createFocusArea(@RequestBody FocusArea focusArea) {
        return this.focusAreaDao.create(focusArea);
    }

    @PutMapping("/focusAreas/{id}")
    public boolean updateFocusArea(@RequestBody FocusArea focusArea,
            @PathVariable("id") Long id) throws NotFoundException {

        if (id == null || id == 0 || focusAreaDao.get(id) == null) {
            throw new NotFoundException("No FocusArea with id " + id);
        } else {
            return focusAreaDao.update(focusArea);
        }
    }


    @DeleteMapping("/focusAreas/{id}")
    public String deleteFocusArea(@PathVariable("id") Long id)
            throws NotFoundException {

        if (id == null || id == 0) {
            throw new NotFoundException("No FocusArea with id given");
        } else {
            FocusArea focusArea = this.focusAreaDao.get(id);
            if (focusArea != null) {
                this.focusAreaDao.delete(focusArea.getId());
                return "FocusArea deleted successfully";
            } else {
                throw new NotFoundException("No FocusArea with id given");
            }
        }
    }

    @DeleteMapping("/focusAreaOntologyTerms/deleteFocusAreaOntologyTerm")
    public String deleteFocusAreaOntologyTerm(@RequestParam("focusAreaId") Long focusAreaId, @RequestParam Long ontologyTermId)
            throws NotFoundException {

        if (focusAreaId == null || focusAreaId == 0) {
            throw new NotFoundException("No FocusAreaOntologyTerm with id given");
        } else {
            this.focusAreaOntologyTermDao.deleteAllFocusAreasOntologyTerms(focusAreaId,ontologyTermId);
            return "FocusAreaOntologyTerm deleted successfully";
        }
    }

    @ListMapping("/ontologySpaces")
    public List<OntologySpace> getOntologySpaces(
    ) {
        return ontologySpaceDao.findByGiven();
    }

    @ListMapping("/focusAreaOntologyTerms")
    public List<FocusAreaOntologyTerm> getFocusAreaOntologyTerms(@RequestParam(required = false) Long focusAreaId,
                                                                 @RequestParam(required = false) Long ontologTermId
    ) {
        return focusAreaOntologyTermDao.findByGiven(focusAreaId,ontologTermId);
    }

    @PostMapping("/focusAreaOntologyTerms")
    public FocusAreaOntologyTerm createFocusAreaOntologyTerm(@RequestBody FocusAreaOntologyTerm focusAreaOntologyTerm) {
        return this.focusAreaOntologyTermDao.create(focusAreaOntologyTerm);
    }


    @ListMapping("/impactTemplateMaxFocusAreas")
    public List<ImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusAreas(
            @RequestParam(required = false) Long focusAreaListId
    ) {
        return impactTemplateMaxFocusAreaDao.findByGiven(focusAreaListId);
    }

    @GetMapping("/impactTemplateFocusAreaLists/{impactTemplateFocusAreaListId}")
    public ImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(@PathVariable("impactTemplateFocusAreaListId") Long impactTemplateFocusAreaListId) throws NotFoundException {
        if (impactTemplateFocusAreaListId == null || impactTemplateFocusAreaListId == 0) {
            throw new NotFoundException("No impactTemplateFocusAreaListId given");
        } else {
            return impactTemplateFocusAreaListDao.get(impactTemplateFocusAreaListId);
        }
    }

    @GetMapping("/ontologySpaces/{ontologySpaceId}")
    public OntologySpace getOntologySpace(@PathVariable("ontologySpaceId") Long ontologySpaceId) throws NotFoundException {
        if (ontologySpaceId == null || ontologySpaceId == 0) {
            throw new NotFoundException("No ontologySpaceId given");
        } else {
            return ontologySpaceDao.get(ontologySpaceId);
        }
    }

    @ListMapping("/impactDefaultSeries")
    public List<ImpactDefaultSeries> getImpactDefaultSeries(
            @RequestParam(required = false) Long focusAreaId,
            @RequestParam(required = false) String name
    ) {
        return impactDefaultSeriesDao.findByGiven(focusAreaId,name);
    }

    @ListMapping("/impactDefaultSeriesDatas")
    public List<ImpactDefaultSeriesData> getImpactDefaultSeriesDatas(
            @RequestParam(required = false) Long seriesId,
            @RequestParam(required = false) Integer year
    ) {
        return impactDefaultSeriesDataDao.findByGiven(seriesId, year);
    }
}
