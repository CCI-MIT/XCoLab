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

import org.xcolab.client.contest.IOntologyClient;
import org.xcolab.client.contest.pojo.IFocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.IImpactDefaultSeries;
import org.xcolab.client.contest.pojo.IImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.service.contest.domain.focusarea.FocusAreaDao;
import org.xcolab.service.contest.domain.focusareaontologyterm.FocusAreaOntologyTermDao;
import org.xcolab.service.contest.domain.impactdefaultseries.ImpactDefaultSeriesDao;
import org.xcolab.service.contest.domain.impactdefaultseriesdata.ImpactDefaultSeriesDataDao;
import org.xcolab.service.contest.domain.ontologyspace.OntologySpaceDao;
import org.xcolab.service.contest.domain.ontologyterm.OntologyTermDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.ontology.OntologyService;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OntologyController implements IOntologyClient {

    private final OntologyTermDao ontologyTermDao;
    private final OntologySpaceDao ontologySpaceDao;
    private final FocusAreaDao focusAreaDao;
    private final FocusAreaOntologyTermDao focusAreaOntologyTermDao;
    private final ImpactDefaultSeriesDao impactDefaultSeriesDao;
    private final ImpactDefaultSeriesDataDao impactDefaultSeriesDataDao;
    private final OntologyService ontologyService;

    @Autowired
    public OntologyController(
            OntologyTermDao ontologyTermDao,
            OntologySpaceDao ontologySpaceDao,
            FocusAreaDao focusAreaDao,
            FocusAreaOntologyTermDao focusAreaOntologyTermDao,
            ImpactDefaultSeriesDao impactDefaultSeriesDao,
            ImpactDefaultSeriesDataDao impactDefaultSeriesDataDao,
            OntologyService ontologyService) {
        this.ontologyTermDao = ontologyTermDao;
        this.ontologySpaceDao = ontologySpaceDao;
        this.focusAreaDao = focusAreaDao;
        this.focusAreaOntologyTermDao = focusAreaOntologyTermDao;
        this.impactDefaultSeriesDao = impactDefaultSeriesDao;
        this.impactDefaultSeriesDataDao = impactDefaultSeriesDataDao;
        this.ontologyService = ontologyService;
    }

    @Override
    @GetMapping("/ontologyTerms/getAllOntologyTermDescendant")
    public List<OntologyTermWrapper> getAllOntologyTermDescendant(
            @RequestParam Long ontologyTermId) {
        try {
            OntologyTermWrapper ontologyTerm = ontologyTermDao.get(ontologyTermId);
            return ontologyService.getAllOntologyTermDescendantTerms(ontologyTerm);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "OntologyTerm not found with id " + ontologyTermId);
        }
    }

    @Override
    @GetMapping("/ontologyTerms/getOntologyTermsByFocusAreaOntologySpaceName")
    public List<OntologyTermWrapper> getOntologyTermsByFocusAreaOntologySpaceName(
            @RequestParam Long focusAreaId, @RequestParam String ontologySpaceName) {
        return ontologyTermDao
                .getOntologyTermByFocusAreaAndOntologySpaceName(focusAreaId, ontologySpaceName);
    }

    @Override
    @GetMapping("/ontologyTerms")
    public List<OntologyTermWrapper> getOntologyTerms(@RequestParam(required = false) String name,
            @RequestParam(required = false) Long parentId,
            @RequestParam(required = false) Long ontologySpaceId) {
        return ontologyTermDao.findByGiven(name, parentId, ontologySpaceId);
    }

    @Override
    @GetMapping("/ontologyTerms/{ontologyTermId}")
    public OntologyTermWrapper getOntologyTerm(@PathVariable Long ontologyTermId) {
        if (ontologyTermId == null || ontologyTermId == 0) {
            return null;
        } else {
            try {
                return ontologyTermDao.get(ontologyTermId);
            } catch (NotFoundException e) {
                return null;
            }
        }
    }

    @Override
    @PutMapping("/ontologyTerms")
    public boolean updateOntologyTerm(@RequestBody OntologyTermWrapper ontologyTerm) {
        Long id = ontologyTerm.getId();
        try {
            if (id == null || id == 0 || ontologyTermDao.get(id) == null) {
                throw new RuntimeEntityNotFoundException("OntologyTerm not found with id " + id);
            } else {
                return ontologyTermDao.update(ontologyTerm);
            }
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException("OntologyTerm not found with id " + id);
        }
    }

    @Override
    @DeleteMapping("/ontologyTerms/{ontologyTermId}")
    public boolean deleteOntologyTerm(@PathVariable Long ontologyTermId) {
        if (ontologyTermId == null || ontologyTermId == 0) {
            throw new RuntimeEntityNotFoundException("ontologyTermId not given");
        } else {
            try {
                OntologyTermWrapper ontologyTerm = this.ontologyTermDao.get(ontologyTermId);
                if (ontologyTerm != null) {
                    this.ontologyTermDao.delete(ontologyTerm.getId());
                    return true;
                }
            } catch (NotFoundException e) {}
            throw new RuntimeEntityNotFoundException(
                    "OntologyTerm not found with id " + ontologyTermId);
        }
    }

    @Override
    @PostMapping("/ontologyTerms")
    public OntologyTermWrapper createOntologyTerm(@RequestBody OntologyTermWrapper ontologyTerm) {
        return this.ontologyTermDao.create(ontologyTerm);
    }

    @Override
    @GetMapping("/focusAreas/{focusAreaId}")
    public FocusAreaWrapper getFocusArea(@PathVariable Long focusAreaId) {
        /*
        if(focusAreaId == 0 ){
            focusAreaId = 2l; //This is done because a lot of old contests use focus area id = 0
            that the auto increment no longer allows.
        }
        if (focusAreaId == null ) {
            throw new NotFoundException("No focusAreaId given");
        } else {
            return focusAreaDao.get(focusAreaId);
        }
        */
        try {
            return focusAreaDao.get(focusAreaId);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException("FocusArea not found with id " + focusAreaId);
        }
    }

    @Override
    @GetMapping("/focusAreas")
    public List<FocusAreaWrapper> getAllFocusAreas() {
        List<FocusAreaWrapper> focusAreas = new ArrayList<>();
        for (IFocusAreaOntologyTerm term : focusAreaOntologyTermDao.findByGiven(null, null)) {
            Long focusAreaId = term.getFocusAreaId();
            try {
                focusAreas.add(focusAreaDao.get(focusAreaId));
            } catch (NotFoundException e) {
                throw new RuntimeEntityNotFoundException(
                        "FocusArea not found with id " + focusAreaId);
            }
        }
        return focusAreas;
    }

    @Override
    @PostMapping("/focusAreas")
    public FocusAreaWrapper createFocusArea(@RequestBody FocusAreaWrapper focusArea) {
        return this.focusAreaDao.create(focusArea);
    }

    @Override
    @PutMapping("/focusAreas")
    public boolean updateFocusArea(@RequestBody FocusAreaWrapper focusArea) {
        Long id = focusArea.getId();
        try {
            if (id == null || id == 0 || focusAreaDao.get(id) == null) {
                throw new RuntimeEntityNotFoundException("FocusArea not found with id " + id);
            } else {
                return focusAreaDao.update(focusArea);
            }
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException("FocusArea not found with id " + id);
        }
    }

    @Override
    @DeleteMapping("/focusAreas/{focusAreaId}")
    public boolean deleteFocusArea(@PathVariable Long focusAreaId) {
        if (focusAreaId == null || focusAreaId == 0) {
            throw new RuntimeEntityNotFoundException("focusAreaId not given");
        } else {
            try {
                FocusAreaWrapper focusArea = this.focusAreaDao.get(focusAreaId);
                if (focusArea != null) {
                    this.focusAreaDao.delete(focusArea.getId());
                    return true;
                }
            } catch (NotFoundException e) {}
            throw new RuntimeEntityNotFoundException("FocusArea not found with id " + focusAreaId);
        }
    }

    @Override
    @DeleteMapping("/focusAreaOntologyTerms/deleteFocusAreaOntologyTerm")
    public boolean deleteFocusAreaOntologyTerm(@RequestParam Long focusAreaId,
            @RequestParam Long ontologyTermId) {
        if (focusAreaId == null || focusAreaId == 0) {
            throw new RuntimeEntityNotFoundException("focusAreaId not given");
        } else {
            this.focusAreaOntologyTermDao
                    .deleteAllFocusAreasOntologyTerms(focusAreaId, ontologyTermId);
            return true;
        }
    }

    @Override
    @GetMapping("/ontologySpaces")
    public List<OntologySpaceWrapper> getAllOntologySpaces() {
        return ontologySpaceDao.findByGiven();
    }

    @Override
    @GetMapping("/focusAreaOntologyTerms")
    public List<IFocusAreaOntologyTerm> getFocusAreaOntologyTerms(
            @RequestParam(required = false) Long focusAreaId,
            @RequestParam(required = false) Long ontologyTermId) {
        return focusAreaOntologyTermDao.findByGiven(focusAreaId, ontologyTermId);
    }

    @Override
    @PostMapping("/focusAreaOntologyTerms")
    public IFocusAreaOntologyTerm createFocusAreaOntologyTerm(
            @RequestBody IFocusAreaOntologyTerm focusAreaOntologyTerm) {
        return this.focusAreaOntologyTermDao.create(focusAreaOntologyTerm);
    }

    @Override
    @GetMapping("/ontologySpaces/{ontologySpaceId}")
    public OntologySpaceWrapper getOntologySpace(@PathVariable Long ontologySpaceId) {
        if (ontologySpaceId == null || ontologySpaceId == 0) {
            throw new RuntimeEntityNotFoundException("ontologySpaceId not given");
        } else {
            try {
                return ontologySpaceDao.get(ontologySpaceId);
            } catch (NotFoundException e) {
                throw new RuntimeEntityNotFoundException(
                        "ontologySpace not found with id " + ontologySpaceId);
            }
        }
    }

    @Override
    @GetMapping("/impactDefaultSeries")
    public List<IImpactDefaultSeries> getImpactDefaultSeries(
            @RequestParam(required = false) Long focusAreaId,
            @RequestParam(required = false) String name) {
        return impactDefaultSeriesDao.findByGiven(focusAreaId, name);
    }

    @Override
    @GetMapping("/impactDefaultSeriesDatas")
    public List<IImpactDefaultSeriesData> getImpactDefaultSeriesData(
            @RequestParam(required = false) Long seriesId,
            @RequestParam(required = false) Integer year) {
        return impactDefaultSeriesDataDao.findByGiven(seriesId, year);
    }
}
