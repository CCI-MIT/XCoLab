package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.IImpactDefaultSeries;
import org.xcolab.client.contest.pojo.IImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.IFocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;

import java.util.List;

public final class OntologyClientUtil {

    private static final OntologyClient client = new OntologyClient();

    private OntologyClientUtil() {
    }

    public static OntologyClient getClient() {
        return client;
    }

    public static List<OntologySpaceWrapper> getAllOntologySpaces() {
        return client.getAllOntologySpaces();
    }

    public static List<OntologyTermWrapper> getAllOntologyTerms() {
        return client.getAllOntologyTerms();
    }
    public static List<OntologyTermWrapper> getOntologyTerms(Long parentId, Long ontologySpaceId){
        return client.getOntologyTerms(parentId,ontologySpaceId);
    }

    public static List<FocusAreaWrapper> getAllFocusAreas() {
        return client.getAllFocusAreas();
    }

    public static FocusAreaWrapper createFocusArea(
            FocusAreaWrapper focusArea) {
        return client.createFocusArea(focusArea);
    }

    public static void addOntologyTermsToFocusAreaByOntologyTermId(Long focusAreaId,
            Long ontologyTermId) {
        client.addOntologyTermsToFocusAreaByOntologyTermId(focusAreaId, ontologyTermId);
    }

    public static List<IFocusAreaOntologyTerm> getAllFocusAreaOntologyTerms() {
        return client.getAllFocusAreaOntologyTerms();
    }

    public static OntologyTermWrapper getOntologyTermParent(
            OntologyTermWrapper ontologyTerm) {
        return client.getOntologyTermParent(ontologyTerm);
    }

    public static OntologyTermWrapper getOntologyTerm(long Id) {
        return client.getOntologyTerm(Id);
    }

    public static OntologyTermWrapper createOntologyTerm(OntologyTermWrapper ontologyTerm) {
        return client.createOntologyTerm(ontologyTerm);
    }

    public static boolean updateOntologyTerm(OntologyTermWrapper ontologyTerm) {
        return client.updateOntologyTerm(ontologyTerm);
    }

    public static boolean deleteOntologyTerm(Long id) {
        return  client.deleteOntologyTerm(id);
    }



    public static Boolean isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(
            Long focusAreaId, Long ontologyTermId) {
        if (focusAreaId == null) {
            return false;
        }
        return client.isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(focusAreaId,
                        ontologyTermId);
    }

    public static List<IFocusAreaOntologyTerm> getFocusAreaOntologyTermsByFocusArea(
            Long focusAreaId) {
        return client.getFocusAreaOntologyTermsByFocusArea(focusAreaId);
    }

    public static List<OntologyTermWrapper> getOntologyTermsByFocusAreaOntologySpaceName(Long focusAreaId, String ontologySpaceName) {
        return client.getOntologyTermsByFocusAreaOntologySpaceName(focusAreaId,ontologySpaceName);
    }

    public static List<OntologyTermWrapper> getAllOntologyTermDescendant(
            Long ontologyTermId) {
        return client.getAllOntologyTermDescendant(ontologyTermId);
    }

    public static List<OntologyTermWrapper> getOntologyTermsByName(
            String name) {
        return client.getOntologyTermsByName(name);
    }

    public static List<OntologyTermWrapper> getChildOntologyTerms(Long ontologyTermId) {
        return client.getChildOntologyTerms(ontologyTermId);
    }

    public static FocusAreaWrapper getFocusArea(long Id) {
        return client.getFocusArea(Id);
    }

    public static  Boolean deleteFocusArea(Long id) {
        return  client.deleteFocusArea(id);
    }

    public static  Boolean deleteFocusAreaOntologyTerm(Long focusAreaId, Long ontologyTermId) {
        return  client.deleteFocusAreaOntologyTerm(focusAreaId, ontologyTermId);
    }
    public static boolean updateFocusArea(FocusAreaWrapper focusArea) {
        return client.updateFocusArea(focusArea);
    }


    public static OntologySpaceWrapper getOntologySpace(long id) {
        return client.getOntologySpace(id);
    }

    public static List<OntologyTermWrapper> getAllOntologyTermsFromFocusAreaWithOntologySpace(
            FocusAreaWrapper focusArea,
            OntologySpaceWrapper ontologySpace) {
        return client.getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, ontologySpace);
    }

    public static List<OntologyTermWrapper> getOntologyTermsForFocusArea(
            FocusAreaWrapper focusArea) {
        return client.getOntologyTermsForFocusArea(focusArea);
    }

    public static OntologyTermWrapper getOntologyTermFromFocusAreaWithOntologySpace(
            FocusAreaWrapper focusArea,
            OntologySpaceWrapper ontologySpace) {
        return client.getOntologyTermFromFocusAreaWithOntologySpace(focusArea, ontologySpace);
    }

    public static IImpactDefaultSeries getImpactDefaultSeriesByFocusAreaAndSeriesName(
            Long focusAreaId, String name) {
        return client.getImpactDefaultSeriesByFocusAreaName(focusAreaId, name);
    }

    public static List<IImpactDefaultSeries> getAllmpactDefaultSeriesByFocusArea(
            Long focusAreaId) {
        return client.getAllmpactDefaultSeriesByFocusArea(focusAreaId);
    }

    public static List<IImpactDefaultSeriesData> getImpactDefaultSeriesDataBySeries(
            Long seriesId) {
        return client.getImpactDefaultSeriesDataBySeries(seriesId);
    }

    public static IImpactDefaultSeriesData getImpactDefaultSeriesDataBySeriesIdAndYear(
            Long seriesId, Integer year) {
        return client.getImpactDefaultSeriesDataBySeriesIdAndYear(seriesId, year);
    }

}
