package org.xcolab.pojo.generator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.pojo.generator.tables.pojos.Location;

@JsonDeserialize(as = Location.class)
public interface ILocation {

    String getCity();

    void setCity(String city);
}
