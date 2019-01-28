package org.xcolab.client.contest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.IFocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.IImpactDefaultSeries;
import org.xcolab.client.contest.pojo.IImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.tables.pojos.FocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FeignClient("xcolab-contest-service")
public interface IOntologyClient {

    @GetMapping("/ontologySpaces")
    List<OntologySpaceWrapper> getAllOntologySpaces();

    default List<OntologyTermWrapper> getAllOntologyTerms() {
        return getOntologyTerms(null, null, null);
    }

    default List<OntologyTermWrapper> getOntologyTerms(Long parentId, Long ontologySpaceId) {
        return getOntologyTerms(null, parentId, ontologySpaceId);
    }

    @GetMapping("/ontologyTerms")
    List<OntologyTermWrapper> getOntologyTerms(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "parentId", required = false) Long parentId,
            @RequestParam(value = "ontologySpaceId", required = false) Long ontologySpaceId);

    @GetMapping("/focusAreas")
    List<FocusAreaWrapper> getAllFocusAreas();

    @PostMapping("/focusAreas")
    FocusAreaWrapper createFocusArea(@RequestBody FocusAreaWrapper focusArea);

    @PostMapping("/focusAreaOntologyTerms")
    IFocusAreaOntologyTerm createFocusAreaOntologyTerm(
            @RequestBody IFocusAreaOntologyTerm focusAreaOntologyTerm);

    default void addOntologyTermsToFocusAreaByOntologyTermId(Long focusAreaId,
            Long ontologyTermId) {
        IFocusAreaOntologyTerm faot = new FocusAreaOntologyTerm();
        faot.setFocusAreaId(focusAreaId);
        faot.setOntologyTermId(ontologyTermId);
        faot.setSortOrder((int) new Date().getTime());
        createFocusAreaOntologyTerm(faot);
    }

    @GetMapping("/focusAreaOntologyTerms")
    List<IFocusAreaOntologyTerm> getFocusAreaOntologyTerms(
            @RequestParam(value = "focusAreaId", required = false) Long focusAreaId,
            @RequestParam(value = "ontologyTermId", required = false) Long ontologyTermId);

    default List<IFocusAreaOntologyTerm> getAllFocusAreaOntologyTerms() {
        return getFocusAreaOntologyTerms(null, null);
    }

    default OntologyTermWrapper getOntologyTermParent(OntologyTermWrapper ontologyTerm) {
        if (ontologyTerm.getParentId() > 0) {
            return getOntologyTerm(ontologyTerm.getParentId());
        }
        return null;
    }

    @GetMapping("/ontologyTerms/{ontologyTermId}")
    OntologyTermWrapper getOntologyTerm(@PathVariable("ontologyTermId") Long ontologyTermId);

    @PostMapping("/ontologyTerms")
    OntologyTermWrapper createOntologyTerm(@RequestBody OntologyTermWrapper ontologyTerm);

    @PutMapping("/ontologyTerms")
    boolean updateOntologyTerm(@RequestBody OntologyTermWrapper ontologyTerm);

    @DeleteMapping("/ontologyTerms/{ontologyTermId}")
    boolean deleteOntologyTerm(@PathVariable("ontologyTermId") Long ontologyTermId);

    default Boolean isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(Long focusAreaId,
            Long ontologyTermId) {
        OntologyTermWrapper ontologyParentTerm = getOntologyTerm(ontologyTermId);
        List<OntologyTermWrapper> ontologyTermList =
                getAllOntologyTermDescendant(ontologyParentTerm.getId());
        ontologyTermList.add(ontologyParentTerm);
        List<IFocusAreaOntologyTerm> focusAreaOntologyTerms =
                getFocusAreaOntologyTermsByFocusArea(focusAreaId);

        Set<Long> ontologyTermIds = new HashSet<>();

        for (OntologyTermWrapper ontologyTerm : ontologyTermList) {
            ontologyTermIds.add(ontologyTerm.getId());
        }

        for (IFocusAreaOntologyTerm focusAreaOntologyTerm : focusAreaOntologyTerms) {
            if (ontologyTermIds.contains(focusAreaOntologyTerm.getOntologyTermId())) {
                return true;
            }
        }
        return false;
    }

    default List<IFocusAreaOntologyTerm> getFocusAreaOntologyTermsByFocusArea(Long focusAreaId) {
        if (focusAreaId == null) {
            return Collections.emptyList();
        }
        return getFocusAreaOntologyTerms(focusAreaId, null);
    }

    @GetMapping("/ontologyTerms/getAllOntologyTermDescendant")
    List<OntologyTermWrapper> getAllOntologyTermDescendant(
            @RequestParam("ontologyTermId") Long ontologyTermId);

    @GetMapping("/ontologyTerms/getOntologyTermsByFocusAreaOntologySpaceName")
    List<OntologyTermWrapper> getOntologyTermsByFocusAreaOntologySpaceName(
            @RequestParam("focusAreaId") Long focusAreaId,
            @RequestParam("ontologySpaceName") String ontologySpaceName);

    default List<OntologyTermWrapper> getOntologyTermsByName(String name) {
        return getOntologyTerms(name, null, null);
    }

    default List<OntologyTermWrapper> getChildOntologyTerms(Long ontologyTermId) {
        return getOntologyTerms(null, null, ontologyTermId);
    }

    @PutMapping("/focusAreas")
    boolean updateFocusArea(@RequestBody FocusAreaWrapper focusArea);

    @DeleteMapping("/focusAreas/{focusAreaId}")
    boolean deleteFocusArea(@PathVariable("focusAreaId") Long focusAreaId);

    @DeleteMapping("/focusAreaOntologyTerms/deleteFocusAreaOntologyTerm")
    boolean deleteFocusAreaOntologyTerm(@RequestParam("focusAreaId") Long focusAreaId,
            @RequestParam("ontologyTermId") Long ontologyTermId);

    @GetMapping("/focusAreas/{focusAreaId}")
    FocusAreaWrapper getFocusArea(@PathVariable("focusAreaId") Long focusAreaId);

    @GetMapping("/ontologySpaces/{ontologySpaceId}")
    OntologySpaceWrapper getOntologySpace(@PathVariable("ontologySpaceId") Long ontologySpaceId);

    default List<OntologyTermWrapper> getAllOntologyTermsFromFocusAreaWithOntologySpace(
            FocusAreaWrapper focusArea, OntologySpaceWrapper ontologySpace) {
        List<OntologyTermWrapper> list = new ArrayList<>();
        for (OntologyTermWrapper term : getOntologyTermsForFocusArea(focusArea)) {
            if (term.getOntologySpaceId() == ontologySpace.getId().longValue()) {
                list.add(term);
            }
        }
        return list;
    }

    default List<OntologyTermWrapper> getOntologyTermsForFocusArea(FocusAreaWrapper focusArea) {
        List<OntologyTermWrapper> ret = new ArrayList<>();
        for (IFocusAreaOntologyTerm faot : getFocusAreaOntologyTermsByFocusArea(
                focusArea.getId())) {
            ret.add(getOntologyTerm(faot.getOntologyTermId()));
        }
        return ret;
    }

    default OntologyTermWrapper getOntologyTermFromFocusAreaWithOntologySpace(
            FocusAreaWrapper focusArea, OntologySpaceWrapper ontologySpace) {
        for (OntologyTermWrapper term : getOntologyTermsForFocusArea(focusArea)) {
            if (term.getOntologySpaceId() == ontologySpace.getId().longValue()) {
                return term;
            }
        }
        return null;
    }

    @GetMapping("/impactDefaultSeries")
    List<IImpactDefaultSeries> getImpactDefaultSeries(
            @RequestParam(value = "focusAreaId", required = false) Long focusAreaId,
            @RequestParam(value = "name", required = false) String name);

    default IImpactDefaultSeries getImpactDefaultSeriesByFocusAreaName(Long focusAreaId,
            String seriesName) {
        List<IImpactDefaultSeries> allImpactDefaultSeriesWithFocusAreaName =
                getImpactDefaultSeries(focusAreaId, seriesName);
        if (allImpactDefaultSeriesWithFocusAreaName != null
                && !allImpactDefaultSeriesWithFocusAreaName.isEmpty()) {
            return allImpactDefaultSeriesWithFocusAreaName.get(0);
        } else {
            return null;
        }
    }

    default List<IImpactDefaultSeries> getAllmpactDefaultSeriesByFocusArea(Long focusAreaId) {
        return getImpactDefaultSeries(focusAreaId, null);
    }

    @GetMapping("/impactDefaultSeriesDatas")
    List<IImpactDefaultSeriesData> getImpactDefaultSeriesData(
            @RequestParam(value = "seriesId", required = false) Long seriesId,
            @RequestParam(value = "year", required = false) Integer year);

    default List<IImpactDefaultSeriesData> getImpactDefaultSeriesDataBySeries(Long seriesId) {
        return getImpactDefaultSeriesData(seriesId, null);
    }

    default IImpactDefaultSeriesData getImpactDefaultSeriesDataBySeriesIdAndYear(Long seriesId,
            Integer year) {
        List<IImpactDefaultSeriesData> ret = getImpactDefaultSeriesData(seriesId, year);
        if (ret != null && !ret.isEmpty()) {
            return ret.get(0);
        } else {
            return null;
        }
    }
}
