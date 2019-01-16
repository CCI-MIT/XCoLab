package org.xcolab.pojo.generator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.pojo.generator.tables.pojos.Location;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonDeserialize(as = Location.class)
public interface ILocation extends Serializable {

    String getCity();

    void setCity(String city);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    default Set<Date> testDefaultGetter() {
        return new Date();
    }

    default void testDefaultSetter(List<File> strings) {
        // do something here
    }
}
