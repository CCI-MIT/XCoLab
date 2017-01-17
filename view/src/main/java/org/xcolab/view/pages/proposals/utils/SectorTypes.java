package org.xcolab.view.pages.proposals.utils;

/**
 * Created by Thomas on 6/14/2015.
 */
public enum SectorTypes {
    BUILDINGS_ONTOLOGY_TERM_ID("Buildings", 1300378L),
    ENERGY_SUPPLY_ONTOLOGY_TERM_ID("Energy Supply", 409L),
    INDUSTRY_ONTOLOGY_TERM_ID("Industry", 1300382L),
    TRANSPORTATION_ONTOLOGY_TERM_ID("Transportation", 1300379L),
    OTHERS_ONTOLOGY_TERM_ID("Land use & other sectors", 1300359L);

    private final String sectorName;
    private final Long sectorOntologyTermId;

    SectorTypes(String sectorName, Long sectorOntologyTermId) {
        this.sectorName = sectorName;
        this.sectorOntologyTermId = sectorOntologyTermId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public Long getSectorOntologyTermId() {
        return sectorOntologyTermId;
    }

}
