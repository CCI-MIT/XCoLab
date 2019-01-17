package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.ImpactDefaultSeries;
import org.xcolab.client.contest.pojo.ImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.FocusArea;
import org.xcolab.client.contest.pojo.FocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.OntologySpace;
import org.xcolab.client.contest.pojo.OntologyTerm;

import java.util.List;

public final class OntologyClientUtil {

    private static final OntologyClient client = new OntologyClient();

    private OntologyClientUtil() {
    }

    public static OntologyClient getClient() {
        return client;
    }

    public static List<OntologySpace> getAllOntologySpaces() {
        return client.getAllOntologySpaces();
    }

    public static List<OntologyTerm> getAllOntologyTerms() {
        return client.getAllOntologyTerms();
    }
    public static List<OntologyTerm> getOntologyTerms(Long parentId, Long ontologySpaceId){
        return client.getOntologyTerms(parentId,ontologySpaceId);
    }

    public static List<FocusArea> getAllFocusAreas() {
        return client.getAllFocusAreas();
    }

    public static FocusArea createFocusArea(
            FocusArea focusArea) {
        return client.createFocusArea(focusArea);
    }

    public static void addOntologyTermsToFocusAreaByOntologyTermId(Long focusAreaId,
            Long ontologyTermId) {
        client.addOntologyTermsToFocusAreaByOntologyTermId(focusAreaId, ontologyTermId);
    }

    public static List<FocusAreaOntologyTerm> getAllFocusAreaOntologyTerms() {
        return client.getAllFocusAreaOntologyTerms();
    }

    public static OntologyTerm getOntologyTermParent(
            OntologyTerm ontologyTerm) {
        return client.getOntologyTermParent(ontologyTerm);
    }

    public static OntologyTerm getOntologyTerm(long Id) {
        return client.getOntologyTerm(Id);
    }

    public static  OntologyTerm createOntologyTerm(OntologyTerm ontologyTerm) {
        return client.createOntologyTerm(ontologyTerm);
    }

    public static boolean updateOntologyTerm(OntologyTerm ontologyTerm) {
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

    public static List<FocusAreaOntologyTerm> getFocusAreaOntologyTermsByFocusArea(
            Long focusAreaId) {
        return client.getFocusAreaOntologyTermsByFocusArea(focusAreaId);
    }

    public static List<OntologyTerm> getOntologyTermsByFocusAreaOntologySpaceName(Long focusAreaId, String ontologySpaceName) {
        return client.getOntologyTermsByFocusAreaOntologySpaceName(focusAreaId,ontologySpaceName);
    }

    public static List<OntologyTerm> getAllOntologyTermDescendant(
            Long ontologyTermId) {
        return client.getAllOntologyTermDescendant(ontologyTermId);
    }

    public static List<OntologyTerm> getOntologyTermsByName(
            String name) {
        return client.getOntologyTermsByName(name);
    }

    public static List<OntologyTerm> getChildOntologyTerms(Long ontologyTermId) {
        return client.getChildOntologyTerms(ontologyTermId);
    }

    public static FocusArea getFocusArea(long Id) {
        return client.getFocusArea(Id);
    }

    public static  Boolean deleteFocusArea(Long id) {
        return  client.deleteFocusArea(id);
    }

    public static  Boolean deleteFocusAreaOntologyTerm(Long focusAreaId, Long ontologyTermId) {
        return  client.deleteFocusAreaOntologyTerm(focusAreaId, ontologyTermId);
    }
    public static boolean updateFocusArea(FocusArea focusArea) {
        return client.updateFocusArea(focusArea);
    }


    public static OntologySpace getOntologySpace(long id) {
        return client.getOntologySpace(id);
    }

    public static List<OntologyTerm> getAllOntologyTermsFromFocusAreaWithOntologySpace(
            FocusArea focusArea,
            OntologySpace ontologySpace) {
        return client.getAllOntologyTermsFromFocusAreaWithOntologySpace(focusArea, ontologySpace);
    }

    public static List<OntologyTerm> getOntologyTermsForFocusArea(
            FocusArea focusArea) {
        return client.getOntologyTermsForFocusArea(focusArea);
    }

    public static OntologyTerm getOntologyTermFromFocusAreaWithOntologySpace(
            FocusArea focusArea,
            OntologySpace ontologySpace) {
        return client.getOntologyTermFromFocusAreaWithOntologySpace(focusArea, ontologySpace);
    }

    public static ImpactDefaultSeries getImpactDefaultSeriesByFocusAreaAndSeriesName(
            Long focusAreaId, String name) {
        return client.getImpactDefaultSeriesByFocusAreaName(focusAreaId, name);
    }

    public static List<ImpactDefaultSeries> getAllmpactDefaultSeriesByFocusArea(
            Long focusAreaId) {
        return client.getAllmpactDefaultSeriesByFocusArea(focusAreaId);
    }

    public static List<ImpactDefaultSeriesData> getImpactDefaultSeriesDataBySeries(
            Long seriesId) {
        return client.getImpactDefaultSeriesDataBySeries(seriesId);
    }

    public static ImpactDefaultSeriesData getImpactDefaultSeriesDataBySeriesIdAndYear(
            Long seriesId, Integer year) {
        return client.getImpactDefaultSeriesDataBySeriesIdAndYear(seriesId, year);
    }

}
