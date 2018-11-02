package org.xcolab.pojo.generator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.pojo.generator.tables.pojos.SubLocation;

import java.sql.Timestamp;

@JsonDeserialize(as = SubLocation.class)
public interface ISubLocation {

    String getCity();

    void setCity(String city);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);
}
