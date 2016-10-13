package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.FocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.impact.ImpactDefaultSeries;
import org.xcolab.client.contest.pojo.impact.ImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.ontology.OntologySpace;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class OntologyClientUtil {

    private static final RestService contestService = new RestService("contest-service");

    private static final OntologyClient client = OntologyClient.fromService(contestService);

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

    public static OntologyTerm getOntologyTerm(long Id_) {
        return client.getOntologyTerm(Id_);
    }

    public static Boolean isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(
            Long focusAreaId,
            Long ontologyTermId) {
        return client
                .isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(focusAreaId,
                        ontologyTermId);
    }

    public static List<FocusAreaOntologyTerm> getFocusAreaOntologyTermsByFocusArea(
            Long focusAreaId) {
        return client.getFocusAreaOntologyTermsByFocusArea(focusAreaId);
    }

    public static List<OntologyTerm> getAllOntologyTermDescendant(
            Long ontologyTermId) {
        return client.getAllOntologyTermDescendant(ontologyTermId);
    }

    public static List<OntologyTerm> getOntologyTermsByName(
            String name) {
        return client.getOntologyTermsByName(name);
    }

    public static FocusArea getFocusArea(long Id_) {
        return client.getFocusArea(Id_);
    }

    public static OntologySpace getOntologySpace(long id_) {
        return client.getOntologySpace(id_);
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

    public static ImpactDefaultSeries getImpactDefaultSeriesByFocusAreaName(
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
