package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.FocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.impact.ImpactDefaultSeries;
import org.xcolab.client.contest.pojo.impact.ImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.ontology.OntologySpace;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.contest.pojo.ontology.FocusAreaDto;
import org.xcolab.client.contest.pojo.ontology.FocusAreaOntologyTermDto;
import org.xcolab.client.contest.pojo.impact.ImpactDefaultSeriesDataDto;
import org.xcolab.client.contest.pojo.impact.ImpactDefaultSeriesDto;
import org.xcolab.client.contest.pojo.ontology.OntologySpaceDto;
import org.xcolab.client.contest.pojo.ontology.OntologyTermDto;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OntologyClient {

    private static final Map<RestService, OntologyClient> instances = new HashMap<>();

    private final RestService contestService;

    private final RestResource1<OntologySpaceDto, Long> ontologySpaceResource;

    private final RestResource1<OntologyTermDto, Long> ontologyTermResource;

    private final RestResource1<FocusAreaDto, Long> focusAreaResource;

    private final RestResource1<FocusAreaOntologyTermDto, Long> focusAreaOntologyTermResource;

    private final RestResource1<ImpactDefaultSeriesDto, Long> impactDefaultSeriesResource;

    private final RestResource1<ImpactDefaultSeriesDataDto, Long> impactDefaultSeriesDataResource;

    private OntologyClient(RestService contestService) {
        this.contestService = contestService;
        ontologySpaceResource = new RestResource1<>(this.contestService,
                "ontologySpaces", OntologySpaceDto.TYPES);
        ontologyTermResource = new RestResource1<>(this.contestService,
                "ontologyTerms", OntologyTermDto.TYPES);
        focusAreaResource = new RestResource1<>(this.contestService,
                "focusAreas", FocusAreaDto.TYPES);
        focusAreaOntologyTermResource = new RestResource1<>(this.contestService,
                "focusAreaOntologyTerms", FocusAreaOntologyTermDto.TYPES);
        impactDefaultSeriesResource = new RestResource1<>(this.contestService,
                "impactDefaultSeriesDatas", ImpactDefaultSeriesDto.TYPES);
        impactDefaultSeriesDataResource = new RestResource1<>(this.contestService,
                "impactDefaultSeriesDatas", ImpactDefaultSeriesDataDto.TYPES);
    }

    public static OntologyClient fromService(RestService contestService) {
        OntologyClient client = instances.get(contestService);
        if (client == null) {
            client = new OntologyClient(contestService);
            instances.put(contestService, client);
        }
        return client;
    }

    public List<OntologySpace> getAllOntologySpaces() {
        return DtoUtil.toPojos(ontologySpaceResource.list().execute(), contestService);
    }

    public List<OntologyTerm> getAllOntologyTerms() {
        return DtoUtil.toPojos(ontologyTermResource.list()
                .execute(), contestService);
    }

    public List<FocusArea> getAllFocusAreas() {
        return DtoUtil.toPojos(focusAreaResource.list()
                .execute(), contestService);
    }

    public FocusArea createFocusArea(FocusArea focusArea) {
        return focusAreaResource.create(new FocusAreaDto(focusArea))
                .execute().toPojo(contestService);
    }

    public void addOntologyTermsToFocusAreaByOntologyTermId(Long focusAreaId, Long ontologyTermId) {
        FocusAreaOntologyTermDto faot = new FocusAreaOntologyTermDto();
        faot.setFocusAreaId(focusAreaId);
        faot.setOntologyTermId(ontologyTermId);
        faot.setOrder_((int) new Date().getTime());
        focusAreaOntologyTermResource.create(faot);

    }

    public List<FocusAreaOntologyTerm> getAllFocusAreaOntologyTerms() {
        return DtoUtil.toPojos(focusAreaOntologyTermResource.list()
                .execute(), contestService);
    }

    public OntologyTerm getOntologyTermParent(OntologyTerm ontologyTerm) {
        if (ontologyTerm.getParentId() > 0) {
            return getOntologyTerm(ontologyTerm.getParentId());
        }
        return null;
    }

    public OntologyTerm getOntologyTerm(long Id_) {
        return ontologyTermResource.get(Id_)
                .execute().toPojo(contestService);
    }

    public Boolean isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(
            Long focusAreaId, Long ontologyTermId) {

        OntologyTerm ontologyParentTerm = getOntologyTerm(ontologyTermId);
        List<OntologyTerm> ontologyTermList =
                getAllOntologyTermDescendant(ontologyParentTerm.getId_());
        ontologyTermList.add(ontologyParentTerm);
        List<FocusAreaOntologyTerm> focusAreaOntologyTerms =
                getFocusAreaOntologyTermsByFocusArea(focusAreaId);

        Set<Long> ontologyTermIds = new HashSet<>();

        for (OntologyTerm ontologyTerm : ontologyTermList) {
            ontologyTermIds.add(ontologyTerm.getId_());
        }

        for (FocusAreaOntologyTerm focusAreaOntologyTerm : focusAreaOntologyTerms) {
            if (ontologyTermIds.contains(focusAreaOntologyTerm.getOntologyTermId())) {
                return true;
            }
        }
        return false;
    }

    public List<FocusAreaOntologyTerm> getFocusAreaOntologyTermsByFocusArea(Long focusAreaId) {
        return DtoUtil.toPojos(focusAreaOntologyTermResource.list()
                .queryParam("focusAreaId", focusAreaId)
                .execute(), contestService);
    }

    public List<OntologyTerm> getAllOntologyTermDescendant(Long ontologyTermId) {
        return DtoUtil.toPojos(ontologyTermResource
                .service("getAllOntologyTermDescendant", OntologyTermDto.TYPES.getTypeReference())
                .queryParam("ontologyTermId", ontologyTermId)
                .getList(), contestService);
    }

    public List<OntologyTerm> getOntologyTermsByName(String name) {
        return DtoUtil.toPojos(ontologyTermResource.list()
                .optionalQueryParam("name", name)
                .execute(), contestService);
    }


    public FocusArea getFocusArea(long Id_) {
        return focusAreaResource.get(Id_)
                .execute().toPojo(contestService);
    }

    public OntologySpace getOntologySpace(long id_) {
        return ontologySpaceResource.get(id_)
                .execute().toPojo(contestService);
    }

    public List<OntologyTerm> getAllOntologyTermsFromFocusAreaWithOntologySpace(
            FocusArea focusArea, OntologySpace ontologySpace) {
        List<OntologyTerm> list = new ArrayList<>();
        for (OntologyTerm term : getOntologyTermsForFocusArea(focusArea)) {
            if (term.getOntologySpaceId() == ontologySpace.getId_().longValue()) {
                list.add(term);
            }
        }

        return list;
    }

    public List<OntologyTerm> getOntologyTermsForFocusArea(FocusArea focusArea) {
        List<OntologyTerm> ret = new ArrayList<>();
        for (FocusAreaOntologyTerm faot : getFocusAreaOntologyTermsByFocusArea(
                focusArea.getId_())) {
            ret.add(getOntologyTerm(faot.getOntologyTermId()));
        }
        return ret;
    }

    public OntologyTerm getOntologyTermFromFocusAreaWithOntologySpace(FocusArea focusArea,
            OntologySpace ontologySpace) {
        for (OntologyTerm term : getOntologyTermsForFocusArea(focusArea)) {
            if (term.getOntologySpaceId() == ontologySpace.getId_().longValue()) {
                return term;
            }
        }

        return null;
    }

    public ImpactDefaultSeries getImpactDefaultSeriesByFocusAreaName(Long focusAreaId,
            String name) {
        List<ImpactDefaultSeries> allImpactDefaultSeriesWithFocusAreaName = DtoUtil.toPojos(
                impactDefaultSeriesResource.list()
                        .optionalQueryParam("focusAreaId", focusAreaId)
                        .optionalQueryParam("name", name)
                        .execute(),
                contestService);
        if (allImpactDefaultSeriesWithFocusAreaName != null
                && !allImpactDefaultSeriesWithFocusAreaName
                .isEmpty()) {
            return allImpactDefaultSeriesWithFocusAreaName.get(0);
        } else {
            return null;
        }
    }

    public List<ImpactDefaultSeries> getAllmpactDefaultSeriesByFocusArea(Long focusAreaId) {
        return DtoUtil.toPojos(impactDefaultSeriesResource.list()
                .optionalQueryParam("focusAreaId", focusAreaId)
                .execute(), contestService);
    }

    public List<ImpactDefaultSeriesData> getImpactDefaultSeriesDataBySeries(Long seriesId) {
        return DtoUtil.toPojos(impactDefaultSeriesDataResource.list()
                .optionalQueryParam("seriesId", seriesId)
                .execute(), contestService);
    }

    public ImpactDefaultSeriesData getImpactDefaultSeriesDataBySeriesIdAndYear(Long seriesId,
            Integer year) {
        List<ImpactDefaultSeriesData> ret = DtoUtil.toPojos(
                impactDefaultSeriesDataResource.list()
                        .optionalQueryParam("seriesId", seriesId)
                        .optionalQueryParam("year", year)
                        .execute(),
                contestService);
        if (ret != null && !ret.isEmpty()) {
            return ret.get(0);
        } else {
            return null;
        }
    }

}
