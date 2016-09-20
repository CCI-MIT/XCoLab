package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.FocusArea;
import org.xcolab.model.tables.pojos.FocusAreaOntologyTerm;
import org.xcolab.model.tables.pojos.OntologySpace;
import org.xcolab.model.tables.pojos.OntologyTerm;
import org.xcolab.service.contest.domain.focusarea.FocusAreaDao;
import org.xcolab.service.contest.domain.focusareaontologyterm.FocusAreaOntologyTermDao;
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

    @RequestMapping(value = "/ontologyTerms", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<OntologyTerm> getOntologyTerms(
    ) {
        return ontologyTermDao.findByGiven();
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

}
