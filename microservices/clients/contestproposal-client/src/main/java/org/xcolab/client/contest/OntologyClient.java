package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.impact.ImpactDefaultSeries;
import org.xcolab.client.contest.pojo.impact.ImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.impact.ImpactDefaultSeriesDataDto;
import org.xcolab.client.contest.pojo.impact.ImpactDefaultSeriesDto;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.FocusAreaDto;
import org.xcolab.client.contest.pojo.ontology.FocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.ontology.FocusAreaOntologyTermDto;
import org.xcolab.client.contest.pojo.ontology.OntologySpace;
import org.xcolab.client.contest.pojo.ontology.OntologySpaceDto;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.contest.pojo.ontology.OntologyTermDto;
import org.xcolab.client.contest.resources.ImpactResource;
import org.xcolab.client.contest.resources.OntologyResource;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OntologyClient {

    private static final Map<ServiceNamespace, OntologyClient> instances = new HashMap<>();

    private final ServiceNamespace serviceNamespace;

    private final RestResource1<OntologySpaceDto, Long> ontologySpaceResource;

    private final RestResource1<OntologyTermDto, Long> ontologyTermResource;

    private final RestResource1<FocusAreaDto, Long> focusAreaResource;

    private final RestResource1<FocusAreaOntologyTermDto, Long> focusAreaOntologyTermResource;

    private final RestResource1<ImpactDefaultSeriesDto, Long> impactDefaultSeriesResource;

    private final RestResource1<ImpactDefaultSeriesDataDto, Long> impactDefaultSeriesDataResource;

    private OntologyClient(ServiceNamespace serviceNamespace) {
        this.serviceNamespace = serviceNamespace;
        ontologySpaceResource = new RestResource1<>(OntologyResource.ONTOLOGY_SPACE,
                OntologySpaceDto.TYPES, serviceNamespace);
        ontologyTermResource = new RestResource1<>(OntologyResource.ONTOLOGY_TERM,
                OntologyTermDto.TYPES, serviceNamespace);
        focusAreaResource = new RestResource1<>(OntologyResource.FOCUS_AREA, FocusAreaDto.TYPES,
                serviceNamespace);
        focusAreaOntologyTermResource = new RestResource1<>(
                OntologyResource.FOCUS_AREA_ONTOLOGY_TERM, FocusAreaOntologyTermDto.TYPES,
                serviceNamespace);
        impactDefaultSeriesResource = new RestResource1<>(ImpactResource.IMPACT_DEFAULT_SERIES,
                ImpactDefaultSeriesDto.TYPES);
        impactDefaultSeriesDataResource = new RestResource1<>(
                ImpactResource.IMPACT_DEFAULT_SERIES_DATA, ImpactDefaultSeriesDataDto.TYPES,
                serviceNamespace);
    }

    public static OntologyClient fromService(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, k -> new OntologyClient(serviceNamespace));
    }

    public List<OntologySpace> getAllOntologySpaces() {
        return DtoUtil.toPojos(ontologySpaceResource.list()
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute(), serviceNamespace);
    }

    public List<OntologyTerm> getAllOntologyTerms() {
        return DtoUtil.toPojos(ontologyTermResource.list()
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute(), serviceNamespace);
    }
    public List<OntologyTerm> getOntologyTerms(Long parentId, Long ontologySpaceId) {
        return DtoUtil.toPojos(ontologyTermResource.list()
                .queryParam("parentId", parentId)
                .queryParam("ontologySpaceId",ontologySpaceId)
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute(), serviceNamespace);
    }

    public List<FocusArea> getAllFocusAreas() {
        return DtoUtil.toPojos(focusAreaResource.list()
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute(), serviceNamespace);
    }

    public FocusArea createFocusArea(FocusArea focusArea) {
        final FocusArea result = focusAreaResource.create(new FocusAreaDto(focusArea))
                .execute().toPojo(serviceNamespace);
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_ONTOLOGY);
        return result;
    }

    public void addOntologyTermsToFocusAreaByOntologyTermId(Long focusAreaId, Long ontologyTermId) {
        FocusAreaOntologyTermDto faot = new FocusAreaOntologyTermDto();
        faot.setFocusAreaId(focusAreaId);
        faot.setOntologyTermId(ontologyTermId);
        faot.setOrder_((int) new Date().getTime());
        focusAreaOntologyTermResource.create(faot).execute();

    }

    public List<FocusAreaOntologyTerm> getAllFocusAreaOntologyTerms() {
        return DtoUtil.toPojos(focusAreaOntologyTermResource.list()
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute(), serviceNamespace);
    }

    public OntologyTerm getOntologyTermParent(OntologyTerm ontologyTerm) {
        if (ontologyTerm.getParentId() > 0) {
            return getOntologyTerm(ontologyTerm.getParentId());
        }
        return null;
    }

    public OntologyTerm getOntologyTerm(long Id_) {
         try {
             return ontologyTermResource.get(Id_)
                     .withCache(CacheName.CONTEST_ONTOLOGY)
                     .executeChecked().toPojo(serviceNamespace);
         } catch (EntityNotFoundException ignored) {
             return null;
         }
    }

    public OntologyTerm createOntologyTerm(OntologyTerm ontologyTerm) {
        final OntologyTerm result =
                ontologyTermResource.create(new OntologyTermDto(ontologyTerm))
                        .execute().toPojo(serviceNamespace);
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_ONTOLOGY);
        return result;
    }

    public boolean updateOntologyTerm(OntologyTerm ontologyTerm) {
        final Boolean result = ontologyTermResource
                .update(new OntologyTermDto(ontologyTerm), ontologyTerm.getId_())
                .cacheName(CacheName.CONTEST_ONTOLOGY)
                .execute();
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_ONTOLOGY);
        return result;
    }

    public boolean deleteOntologyTerm(Long id_) {
        final Boolean result = ontologyTermResource.delete(id_)
                .cacheName(CacheName.CONTEST_ONTOLOGY)
                .execute();
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_ONTOLOGY);
        return result;
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
        if (focusAreaId == null) {
            return Collections.emptyList();
        }
        return DtoUtil.toPojos(focusAreaOntologyTermResource.list()
                .queryParam("focusAreaId", focusAreaId)
                .execute(), serviceNamespace);
    }

    public List<OntologyTerm> getAllOntologyTermDescendant(Long ontologyTermId) {
        return DtoUtil.toPojos(ontologyTermResource
                .service("getAllOntologyTermDescendant", OntologyTermDto.TYPES.getTypeReference())
                .queryParam("ontologyTermId", ontologyTermId)
                .getList(), serviceNamespace);
    }

    public List<OntologyTerm> getOntologyTermsByFocusAreaOntologySpaceName(Long focusAreaId, String ontologySpaceName) {
        return DtoUtil.toPojos(ontologyTermResource
                .service("getOntologyTermsByFocusAreaOntologySpaceName", OntologyTermDto.TYPES.getTypeReference())
                .queryParam("focusAreaId", focusAreaId)
                .queryParam("ontologySpaceName", ontologySpaceName)
                .getList(), serviceNamespace);
    }

    public List<OntologyTerm> getOntologyTermsByName(String name) {
        return DtoUtil.toPojos(ontologyTermResource.list()
                .optionalQueryParam("name", name)
                .execute(), serviceNamespace);
    }

    public List<OntologyTerm> getChildOntologyTerms(Long ontologyTermId) {
        return DtoUtil.toPojos(ontologyTermResource.list()
                .optionalQueryParam("parentId", ontologyTermId)
                .execute(), serviceNamespace);
    }

    public boolean updateFocusArea(FocusArea focusArea) {
        return focusAreaResource.update(new FocusAreaDto(focusArea), focusArea.getId_())
                .execute();
    }

    public boolean deleteFocusArea(Long id_) {
        return  focusAreaResource.delete(id_).execute();
    }

    public boolean deleteFocusAreaOntologyTerm(Long focusAreaId,Long ontologyTermId) {
        return  focusAreaOntologyTermResource.service("deleteFocusAreaOntologyTerm",Boolean.class)
                .queryParam("focusAreaId",focusAreaId)
                .queryParam("ontologyTermId", ontologyTermId).delete();
    }

    public FocusArea getFocusArea(long Id_) {
        return focusAreaResource.get(Id_)
                .execute().toPojo(serviceNamespace);
    }

    public OntologySpace getOntologySpace(long id_) {
        return ontologySpaceResource.get(id_)
                .execute().toPojo(serviceNamespace);
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
            String seriesName) {
        List<ImpactDefaultSeries> allImpactDefaultSeriesWithFocusAreaName = DtoUtil.toPojos(
                impactDefaultSeriesResource.list()
                        .optionalQueryParam("focusAreaId", focusAreaId)
                        .optionalQueryParam("name", seriesName)
                        .execute(),
                serviceNamespace);
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
                .execute(), serviceNamespace);
    }

    public List<ImpactDefaultSeriesData> getImpactDefaultSeriesDataBySeries(Long seriesId) {
        return DtoUtil.toPojos(impactDefaultSeriesDataResource.list()
                .optionalQueryParam("seriesId", seriesId)
                .execute(), serviceNamespace);
    }

    public ImpactDefaultSeriesData getImpactDefaultSeriesDataBySeriesIdAndYear(Long seriesId,
            Integer year) {
        List<ImpactDefaultSeriesData> ret = DtoUtil.toPojos(
                impactDefaultSeriesDataResource.list()
                        .optionalQueryParam("seriesId", seriesId)
                        .optionalQueryParam("year", year)
                        .execute(),
                serviceNamespace);
        if (ret != null && !ret.isEmpty()) {
            return ret.get(0);
        } else {
            return null;
        }
    }

}
