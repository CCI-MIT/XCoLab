package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.FocusArea;
import org.xcolab.client.contest.pojo.FocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.ImpactDefaultSeries;
import org.xcolab.client.contest.pojo.ImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.OntologySpace;
import org.xcolab.client.contest.pojo.OntologyTerm;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OntologyClient {

    private static final RestService contestService = new RestService("contest-service");

    private static final RestResource1<OntologySpace,Long> ontologySpaceResource = new RestResource1<>(contestService,
            "ontologySpaces", OntologySpace.TYPES);

    private static final RestResource1<OntologyTerm,Long> ontologyTermResource = new RestResource1<>(contestService,
            "ontologyTerms", OntologyTerm.TYPES);

    private static final RestResource1<FocusArea,Long> focusAreaResource = new RestResource1<>(contestService,
            "focusAreas", FocusArea.TYPES);

    private static final RestResource1<FocusAreaOntologyTerm,Long> focusAreaOntologyTermResource = new RestResource1<>(contestService,
            "focusAreaOntologyTerms", FocusAreaOntologyTerm.TYPES);

    private static final RestResource1<ImpactDefaultSeries,Long> impactDefaultSeriesResource = new RestResource1<>(contestService,
            "impactDefaultSeriesDatas", ImpactDefaultSeries.TYPES);

    private static final RestResource1<ImpactDefaultSeriesData,Long> impactDefaultSeriesDataResource = new RestResource1<>(contestService,
            "impactDefaultSeriesDatas", ImpactDefaultSeriesData.TYPES);

    public static List<OntologySpace> getAllOntologySpaces() {
        return ontologySpaceResource.list().execute();
    }

    public static List<OntologyTerm> getAllOntologyTerms() {
        return ontologyTermResource.list()
                .execute();
    }

    public static List<FocusArea> getAllFocusAreas() {
        return focusAreaResource.list()
                .execute();
    }

    public static FocusArea createFocusArea(FocusArea focusArea) {
        return focusAreaResource.create(focusArea).execute();
    }

    public static void addOntologyTermsToFocusAreaByOntologyTermId(Long focusAreaId, Long ontologyTermId) {
        FocusAreaOntologyTerm faot = new FocusAreaOntologyTerm();
        faot.setFocusAreaId(focusAreaId);
        faot.setOntologyTermId(ontologyTermId);
        faot.setOrder_((int) new Date().getTime());
        focusAreaOntologyTermResource.create(faot);

    }

    public static List<FocusAreaOntologyTerm> getAllFocusAreaOntologyTerms() {
        return focusAreaOntologyTermResource.list()
                .execute();
    }

    public static List<FocusAreaOntologyTerm> getFocusAreaOntologyTermsByFocusArea(Long focusAreaId) {
        return focusAreaOntologyTermResource.list()
                .queryParam("focusAreaId", focusAreaId)
                .execute();
    }

    public static OntologyTerm getOntologyTermParent(OntologyTerm ontologyTerm) {
        if (ontologyTerm.getParentId() > 0) {
            return getOntologyTerm(ontologyTerm.getParentId());
        }
        return null;
    }
    public static List<OntologyTerm> getAllOntologyTermDescendant(Long ontologyTermId) {
        return ontologyTermResource.service("getAllOntologyTermDescendant", OntologyTerm.TYPES.getTypeReference())
                .queryParam("ontologyTermId", ontologyTermId)
                .getList();
    }

    public static OntologyTerm getOntologyTerm(long Id_) {
        return ontologyTermResource.get(Id_)
                .execute();
    }

    public static Boolean isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(
            Long focusAreaId, Long ontologyTermId) {

        OntologyTerm ontologyParentTerm = getOntologyTerm(ontologyTermId);
        List<OntologyTerm> ontologyTermList = getAllOntologyTermDescendant(ontologyParentTerm.getId_());
        ontologyTermList.add(ontologyParentTerm);
        List<FocusAreaOntologyTerm> focusAreaOntologyTerms = getFocusAreaOntologyTermsByFocusArea(focusAreaId);

        Set<Long> ontologyTermIds = new HashSet<>();

        for(OntologyTerm ontologyTerm : ontologyTermList) {
            ontologyTermIds.add(ontologyTerm.getId_());
        }

        for (FocusAreaOntologyTerm focusAreaOntologyTerm : focusAreaOntologyTerms) {
            if (ontologyTermIds.contains(focusAreaOntologyTerm.getOntologyTermId())) {
                return true;
            }
        }
        return false;
    }

    public static List<OntologyTerm> getOntologyTermsByName(String name) {
        return ontologyTermResource.list()
                .optionalQueryParam("name", name)
                .execute();
    }


    public static FocusArea getFocusArea(long Id_) {
        return focusAreaResource.get(Id_)
                .execute();
    }

    public static OntologySpace getOntologySpace(long id_) {
        return ontologySpaceResource.get(id_)
                .execute();

    }

    public static List<OntologyTerm> getAllOntologyTermsFromFocusAreaWithOntologySpace(
            FocusArea focusArea, OntologySpace ontologySpace) {
        List<OntologyTerm> list = new ArrayList<>();
        for (OntologyTerm term : getOntologyTermsForFocusArea(focusArea)) {
            if (term.getOntologySpaceId() == ontologySpace.getId_().longValue()) {
                list.add(term);
            }
        }

        return list;
    }

    public static OntologyTerm getOntologyTermFromFocusAreaWithOntologySpace(FocusArea focusArea, OntologySpace ontologySpace) {
        for (OntologyTerm term : getOntologyTermsForFocusArea(focusArea)) {
            if (term.getOntologySpaceId() == ontologySpace.getId_().longValue()) {
                return term;
            }
        }

        return null;
    }

    public static List<OntologyTerm> getOntologyTermsForFocusArea(FocusArea focusArea) {
        List<OntologyTerm> ret = new ArrayList<>();
        for (FocusAreaOntologyTerm faot : getFocusAreaOntologyTermsByFocusArea(focusArea.getId_())) {
            ret.add(getOntologyTerm(faot.getOntologyTermId()));
        }
        return ret;
    }

    public static ImpactDefaultSeries getImpactDefaultSeriesByFocusAreaName(Long focusAreaId, String name) {
        List<ImpactDefaultSeries> allImpactDefaultSeriesWithFocusAreaName = impactDefaultSeriesResource.list()
                .optionalQueryParam("focusAreaId", focusAreaId)
                .optionalQueryParam("name", name)
                .execute();
        if (allImpactDefaultSeriesWithFocusAreaName != null && !allImpactDefaultSeriesWithFocusAreaName
                .isEmpty()) {
            return allImpactDefaultSeriesWithFocusAreaName.get(0);
        } else {
            return null;
        }
    }

    public static List<ImpactDefaultSeries> getAllmpactDefaultSeriesByFocusArea(Long focusAreaId) {
        return impactDefaultSeriesResource.list()
                .optionalQueryParam("focusAreaId", focusAreaId)
                .execute();
    }

    public static List<ImpactDefaultSeriesData> getImpactDefaultSeriesDataBySeries(Long seriesId) {
        return impactDefaultSeriesDataResource.list()
                .optionalQueryParam("seriesId", seriesId)
                .execute();
    }

    public static ImpactDefaultSeriesData getImpactDefaultSeriesDataBySeriesIdAndYear(Long seriesId, Integer year) {
        List<ImpactDefaultSeriesData> ret =
                impactDefaultSeriesDataResource.list()
                        .optionalQueryParam("seriesId", seriesId)
                        .optionalQueryParam("year", year)
                        .execute();
        if (ret != null && !ret.isEmpty()) {
            return ret.get(0);
        } else {
            return null;
        }
    }

}
