package org.xcolab.client.tracking.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.tracking.pojo.tables.pojos.Location;

import java.io.Serializable;

@JsonDeserialize(as = Location.class)
public interface ILocation extends Serializable {

    int getLocId();

    void setLocId(int locId);

    String getCountry();

    void setCountry(String country);

    String getCountryNameInEnglish();

    String getCountryName();

    void setCountryName(String countryName);

    String getRegion();

    void setRegion(String region);

    String getCity();

    void setCity(String city);

    String getPostalCode();

    void setPostalCode(String postalCode);

    double getLatitude();

    void setLatitude(double latitude);

    double getLongitude();

    void setLongitude(double longitude);

    String getMetroCode();

    void setMetroCode(String metroCode);

    String getAreaCode();

    void setAreaCode(String areaCode);
}
