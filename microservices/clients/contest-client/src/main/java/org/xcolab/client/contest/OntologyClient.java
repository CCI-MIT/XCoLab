package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.ImpactDefaultSeries;
import org.xcolab.client.contest.pojo.ImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.FocusArea;
import org.xcolab.client.contest.pojo.FocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.OntologySpace;
import org.xcolab.client.contest.pojo.OntologyTerm;
import org.xcolab.client.contest.resources.ImpactResource;
import org.xcolab.client.contest.resources.OntologyResource;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OntologyClient {

    private final RestResource1<OntologySpace, Long> ontologySpaceResource;

    private final RestResource1<OntologyTerm, Long> ontologyTermResource;

    private final RestResource1<FocusArea, Long> focusAreaResource;

    private final RestResource1<FocusAreaOntologyTerm, Long> focusAreaOntologyTermResource;

    private final RestResource1<ImpactDefaultSeries, Long> impactDefaultSeriesResource;

    private final RestResource1<ImpactDefaultSeriesData, Long> impactDefaultSeriesDataResource;

    public OntologyClient() {
        ontologySpaceResource = new RestResource1<>(OntologyResource.ONTOLOGY_SPACE,
                OntologySpace.TYPES);
        ontologyTermResource = new RestResource1<>(OntologyResource.ONTOLOGY_TERM,
                OntologyTerm.TYPES);
        focusAreaResource = new RestResource1<>(OntologyResource.FOCUS_AREA, FocusArea.TYPES);
        focusAreaOntologyTermResource = new RestResource1<>(
                OntologyResource.FOCUS_AREA_ONTOLOGY_TERM, FocusAreaOntologyTerm.TYPES);
        impactDefaultSeriesResource = new RestResource1<>(ImpactResource.IMPACT_DEFAULT_SERIES,
                ImpactDefaultSeries.TYPES);
        impactDefaultSeriesDataResource = new RestResource1<>(
                ImpactResource.IMPACT_DEFAULT_SERIES_DATA, ImpactDefaultSeriesData.TYPES);
    }

    public List<OntologySpace> getAllOntologySpaces() {
        return ontologySpaceResource.list()
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute();
    }

    public List<OntologyTerm> getAllOntologyTerms() {
        return ontologyTermResource.list()
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute();
    }
    public List<OntologyTerm> getOntologyTerms(Long parentId, Long ontologySpaceId) {
        return ontologyTermResource.list()
                .queryParam("parentId", parentId)
                .queryParam("ontologySpaceId",ontologySpaceId)
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute();
    }

    public List<FocusArea> getAllFocusAreas() {
        return focusAreaResource.list()
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute();
    }

    public FocusArea createFocusArea(FocusArea focusArea) {
        final FocusArea result = focusAreaResource.create(new FocusArea(focusArea))
                .execute();
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_ONTOLOGY);
        return result;
    }

    public void addOntologyTermsToFocusAreaByOntologyTermId(Long focusAreaId, Long ontologyTermId) {
        FocusAreaOntologyTerm faot = new FocusAreaOntologyTerm();
        faot.setFocusAreaId(focusAreaId);
        faot.setOntologyTermId(ontologyTermId);
        faot.setSortOrder((int) new Date().getTime());
        focusAreaOntologyTermResource.create(faot).execute();

    }

    public List<FocusAreaOntologyTerm> getAllFocusAreaOntologyTerms() {
        return focusAreaOntologyTermResource.list()
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute();
    }

    public OntologyTerm getOntologyTermParent(OntologyTerm ontologyTerm) {
        if (ontologyTerm.getParentId() > 0) {
            return getOntologyTerm(ontologyTerm.getParentId());
        }
        return null;
    }

    public OntologyTerm getOntologyTerm(long Id) {
         try {
             return ontologyTermResource.get(Id)
                     .withCache(CacheName.CONTEST_ONTOLOGY)
                     .executeChecked();
         } catch (EntityNotFoundException ignored) {
             return null;
         }
    }

    public OntologyTerm createOntologyTerm(OntologyTerm ontologyTerm) {
        final OntologyTerm result =
                ontologyTermResource.create(new OntologyTerm(ontologyTerm))
                        .execute();
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_ONTOLOGY);
        return result;
    }

    public boolean updateOntologyTerm(OntologyTerm ontologyTerm) {
        final Boolean result = ontologyTermResource
                .update(new OntologyTerm(ontologyTerm), ontologyTerm.getId())
                .cacheName(CacheName.CONTEST_ONTOLOGY)
                .execute();
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_ONTOLOGY);
        return result;
    }

    public boolean deleteOntologyTerm(Long id) {
        final Boolean result = ontologyTermResource.delete(id)
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
                getAllOntologyTermDescendant(ontologyParentTerm.getId());
        ontologyTermList.add(ontologyParentTerm);
        List<FocusAreaOntologyTerm> focusAreaOntologyTerms =
                getFocusAreaOntologyTermsByFocusArea(focusAreaId);

        Set<Long> ontologyTermIds = new HashSet<>();

        for (OntologyTerm ontologyTerm : ontologyTermList) {
            ontologyTermIds.add(ontologyTerm.getId());
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
        return focusAreaOntologyTermResource.list()
                .queryParam("focusAreaId", focusAreaId)
                .execute();
    }

    public List<OntologyTerm> getAllOntologyTermDescendant(Long ontologyTermId) {
        return ontologyTermResource
                .collectionService("getAllOntologyTermDescendant", OntologyTerm.TYPES.getTypeReference())
                .queryParam("ontologyTermId", ontologyTermId)
                .getList();
    }

    public List<OntologyTerm> getOntologyTermsByFocusAreaOntologySpaceName(Long focusAreaId, String ontologySpaceName) {
        return ontologyTermResource
                .collectionService("getOntologyTermsByFocusAreaOntologySpaceName", OntologyTerm.TYPES.getTypeReference())
                .queryParam("focusAreaId", focusAreaId)
                .queryParam("ontologySpaceName", ontologySpaceName)
                .getList();
    }

    public List<OntologyTerm> getOntologyTermsByName(String name) {
        return ontologyTermResource.list()
                .optionalQueryParam("name", name)
                .execute();
    }

    public List<OntologyTerm> getChildOntologyTerms(Long ontologyTermId) {
        return ontologyTermResource.list()
                .optionalQueryParam("parentId", ontologyTermId)
                .execute();
    }

    public boolean updateFocusArea(FocusArea focusArea) {
        return focusAreaResource.update(new FocusArea(focusArea), focusArea.getId())
                .execute();
    }

    public boolean deleteFocusArea(Long id) {
        return  focusAreaResource.delete(id).execute();
    }

    public boolean deleteFocusAreaOntologyTerm(Long focusAreaId,Long ontologyTermId) {
        return  focusAreaOntologyTermResource.collectionService("deleteFocusAreaOntologyTerm",Boolean.class)
                .queryParam("focusAreaId",focusAreaId)
                .queryParam("ontologyTermId", ontologyTermId).delete();
    }

    public FocusArea getFocusArea(long Id) {
        return focusAreaResource.get(Id)
                .execute();
    }

    public OntologySpace getOntologySpace(long id) {
        return ontologySpaceResource.get(id)
                .execute();
    }

    public List<OntologyTerm> getAllOntologyTermsFromFocusAreaWithOntologySpace(
            FocusArea focusArea, OntologySpace ontologySpace) {
        List<OntologyTerm> list = new ArrayList<>();
        for (OntologyTerm term : getOntologyTermsForFocusArea(focusArea)) {
            if (term.getOntologySpaceId() == ontologySpace.getId().longValue()) {
                list.add(term);
            }
        }

        return list;
    }

    public List<OntologyTerm> getOntologyTermsForFocusArea(FocusArea focusArea) {
        List<OntologyTerm> ret = new ArrayList<>();
        for (FocusAreaOntologyTerm faot : getFocusAreaOntologyTermsByFocusArea(
                focusArea.getId())) {
            ret.add(getOntologyTerm(faot.getOntologyTermId()));
        }
        return ret;
    }

    public OntologyTerm getOntologyTermFromFocusAreaWithOntologySpace(FocusArea focusArea,
            OntologySpace ontologySpace) {
        for (OntologyTerm term : getOntologyTermsForFocusArea(focusArea)) {
            if (term.getOntologySpaceId() == ontologySpace.getId().longValue()) {
                return term;
            }
        }

        return null;
    }

    public ImpactDefaultSeries getImpactDefaultSeriesByFocusAreaName(Long focusAreaId,
            String seriesName) {
        List<ImpactDefaultSeries> allImpactDefaultSeriesWithFocusAreaName = 
                impactDefaultSeriesResource.list()
                        .optionalQueryParam("focusAreaId", focusAreaId)
                        .optionalQueryParam("name", seriesName)
                        .execute();
        if (allImpactDefaultSeriesWithFocusAreaName != null
                && !allImpactDefaultSeriesWithFocusAreaName
                .isEmpty()) {
            return allImpactDefaultSeriesWithFocusAreaName.get(0);
        } else {
            return null;
        }
    }

    public List<ImpactDefaultSeries> getAllmpactDefaultSeriesByFocusArea(Long focusAreaId) {
        return impactDefaultSeriesResource.list()
                .optionalQueryParam("focusAreaId", focusAreaId)
                .execute();
    }

    public List<ImpactDefaultSeriesData> getImpactDefaultSeriesDataBySeries(Long seriesId) {
        return impactDefaultSeriesDataResource.list()
                .optionalQueryParam("seriesId", seriesId)
                .execute();
    }

    public ImpactDefaultSeriesData getImpactDefaultSeriesDataBySeriesIdAndYear(Long seriesId,
            Integer year) {
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
