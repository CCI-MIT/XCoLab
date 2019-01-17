package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.IFocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.IImpactDefaultSeries;
import org.xcolab.client.contest.pojo.IImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.OntologySpaceWrapper;
import org.xcolab.client.contest.pojo.OntologyTermWrapper;
import org.xcolab.client.contest.pojo.tables.pojos.FocusAreaOntologyTerm;
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

    private final RestResource1<OntologySpaceWrapper, Long> ontologySpaceResource = null; // ontologySpaces

    private final RestResource1<OntologyTermWrapper, Long> ontologyTermResource = null; // ontologyTerms

    private final RestResource1<FocusAreaWrapper, Long> focusAreaResource = null; // focusAreas

    private final RestResource1<IFocusAreaOntologyTerm, Long> focusAreaOntologyTermResource = null; // focusAreaOntologyTerms

    private final RestResource1<IImpactDefaultSeries, Long> impactDefaultSeriesResource = null; // impactDefaultSeries

    private final RestResource1<IImpactDefaultSeriesData, Long> impactDefaultSeriesDataResource = null; // impactDefaultSeriesDatas

    public List<OntologySpaceWrapper> getAllOntologySpaces() {
        return ontologySpaceResource.list()
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute();
    }

    public List<OntologyTermWrapper> getAllOntologyTerms() {
        return ontologyTermResource.list()
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute();
    }
    public List<OntologyTermWrapper> getOntologyTerms(Long parentId, Long ontologySpaceId) {
        return ontologyTermResource.list()
                .queryParam("parentId", parentId)
                .queryParam("ontologySpaceId",ontologySpaceId)
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute();
    }

    public List<FocusAreaWrapper> getAllFocusAreas() {
        return focusAreaResource.list()
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute();
    }

    public FocusAreaWrapper createFocusArea(FocusAreaWrapper focusArea) {
        final FocusAreaWrapper result = focusAreaResource.create(new FocusAreaWrapper(focusArea))
                .execute();
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_ONTOLOGY);
        return result;
    }

    public void addOntologyTermsToFocusAreaByOntologyTermId(Long focusAreaId, Long ontologyTermId) {
        IFocusAreaOntologyTerm faot = new FocusAreaOntologyTerm();
        faot.setFocusAreaId(focusAreaId);
        faot.setOntologyTermId(ontologyTermId);
        faot.setSortOrder((int) new Date().getTime());
        focusAreaOntologyTermResource.create(faot).execute();

    }

    public List<IFocusAreaOntologyTerm> getAllFocusAreaOntologyTerms() {
        return focusAreaOntologyTermResource.list()
                .withCache(CacheName.CONTEST_ONTOLOGY)
                .execute();
    }

    public OntologyTermWrapper getOntologyTermParent(OntologyTermWrapper ontologyTerm) {
        if (ontologyTerm.getParentId() > 0) {
            return getOntologyTerm(ontologyTerm.getParentId());
        }
        return null;
    }

    public OntologyTermWrapper getOntologyTerm(long Id) {
         try {
             return ontologyTermResource.get(Id)
                     .withCache(CacheName.CONTEST_ONTOLOGY)
                     .executeChecked();
         } catch (EntityNotFoundException ignored) {
             return null;
         }
    }

    public OntologyTermWrapper createOntologyTerm(OntologyTermWrapper ontologyTerm) {
        final OntologyTermWrapper result =
                ontologyTermResource.create(new OntologyTermWrapper(ontologyTerm))
                        .execute();
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_ONTOLOGY);
        return result;
    }

    public boolean updateOntologyTerm(OntologyTermWrapper ontologyTerm) {
        final Boolean result = ontologyTermResource
                .update(new OntologyTermWrapper(ontologyTerm), ontologyTerm.getId())
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

    public List<IFocusAreaOntologyTerm> getFocusAreaOntologyTermsByFocusArea(Long focusAreaId) {
        if (focusAreaId == null) {
            return Collections.emptyList();
        }
        return focusAreaOntologyTermResource.list()
                .queryParam("focusAreaId", focusAreaId)
                .execute();
    }

    public List<OntologyTermWrapper> getAllOntologyTermDescendant(Long ontologyTermId) {
        return ontologyTermResource
                .collectionService("getAllOntologyTermDescendant", OntologyTermWrapper.TYPES.getTypeReference())
                .queryParam("ontologyTermId", ontologyTermId)
                .getList();
    }

    public List<OntologyTermWrapper> getOntologyTermsByFocusAreaOntologySpaceName(Long focusAreaId, String ontologySpaceName) {
        return ontologyTermResource
                .collectionService("getOntologyTermsByFocusAreaOntologySpaceName", OntologyTermWrapper.TYPES.getTypeReference())
                .queryParam("focusAreaId", focusAreaId)
                .queryParam("ontologySpaceName", ontologySpaceName)
                .getList();
    }

    public List<OntologyTermWrapper> getOntologyTermsByName(String name) {
        return ontologyTermResource.list()
                .optionalQueryParam("name", name)
                .execute();
    }

    public List<OntologyTermWrapper> getChildOntologyTerms(Long ontologyTermId) {
        return ontologyTermResource.list()
                .optionalQueryParam("parentId", ontologyTermId)
                .execute();
    }

    public boolean updateFocusArea(FocusAreaWrapper focusArea) {
        return focusAreaResource.update(new FocusAreaWrapper(focusArea), focusArea.getId())
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

    public FocusAreaWrapper getFocusArea(long Id) {
        return focusAreaResource.get(Id)
                .execute();
    }

    public OntologySpaceWrapper getOntologySpace(long id) {
        return ontologySpaceResource.get(id)
                .execute();
    }

    public List<OntologyTermWrapper> getAllOntologyTermsFromFocusAreaWithOntologySpace(
            FocusAreaWrapper focusArea, OntologySpaceWrapper ontologySpace) {
        List<OntologyTermWrapper> list = new ArrayList<>();
        for (OntologyTermWrapper term : getOntologyTermsForFocusArea(focusArea)) {
            if (term.getOntologySpaceId() == ontologySpace.getId().longValue()) {
                list.add(term);
            }
        }

        return list;
    }

    public List<OntologyTermWrapper> getOntologyTermsForFocusArea(FocusAreaWrapper focusArea) {
        List<OntologyTermWrapper> ret = new ArrayList<>();
        for (IFocusAreaOntologyTerm faot : getFocusAreaOntologyTermsByFocusArea(
                focusArea.getId())) {
            ret.add(getOntologyTerm(faot.getOntologyTermId()));
        }
        return ret;
    }

    public OntologyTermWrapper getOntologyTermFromFocusAreaWithOntologySpace(FocusAreaWrapper focusArea,
            OntologySpaceWrapper ontologySpace) {
        for (OntologyTermWrapper term : getOntologyTermsForFocusArea(focusArea)) {
            if (term.getOntologySpaceId() == ontologySpace.getId().longValue()) {
                return term;
            }
        }

        return null;
    }

    public IImpactDefaultSeries getImpactDefaultSeriesByFocusAreaName(Long focusAreaId,
            String seriesName) {
        List<IImpactDefaultSeries> allImpactDefaultSeriesWithFocusAreaName =
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

    public List<IImpactDefaultSeries> getAllmpactDefaultSeriesByFocusArea(Long focusAreaId) {
        return impactDefaultSeriesResource.list()
                .optionalQueryParam("focusAreaId", focusAreaId)
                .execute();
    }

    public List<IImpactDefaultSeriesData> getImpactDefaultSeriesDataBySeries(Long seriesId) {
        return impactDefaultSeriesDataResource.list()
                .optionalQueryParam("seriesId", seriesId)
                .execute();
    }

    public IImpactDefaultSeriesData getImpactDefaultSeriesDataBySeriesIdAndYear(Long seriesId,
            Integer year) {
        List<IImpactDefaultSeriesData> ret =
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
