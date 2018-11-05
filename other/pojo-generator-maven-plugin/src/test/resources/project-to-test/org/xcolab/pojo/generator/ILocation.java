package org.xcolab.pojo.generator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.pojo.generator.tables.pojos.Location;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonDeserialize(as = Location.class)
public interface ILocation extends Serializable {

    String getCity();

    void setCity(String city);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);
}
