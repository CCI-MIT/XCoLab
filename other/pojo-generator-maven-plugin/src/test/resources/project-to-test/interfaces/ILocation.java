package org.xcolab.pojo.generator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.tracking.pojo.tables.pojos.Location;

@JsonDeserialize(as = Location.class)
public interface ILocation {

    String getCity();

    void setCity(String city);
}
