package org.xcolab.pojo.generator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.pojo.generator.tables.pojos.Location;

import java.sql.Timestamp;

@JsonDeserialize(as = Location.class)
public interface ILocation {

    String getCity();

    void setCity(String city);

    @Override
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
