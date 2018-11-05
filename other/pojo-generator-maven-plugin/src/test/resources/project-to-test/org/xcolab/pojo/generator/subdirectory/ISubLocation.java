package org.xcolab.pojo.generator.subdirectory;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.pojo.generator.ILocation;
import org.xcolab.pojo.generator.tables.pojos.SubLocation;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonDeserialize(as = SubLocation.class)
public interface ISubLocation extends Serializable {

    ILocation getLocation();

    void setLocation(ILocation location);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);
}
