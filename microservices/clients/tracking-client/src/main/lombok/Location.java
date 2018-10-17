package org.xcolab.client.tracking.pojo.tables.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.xcolab.client.tracking.pojo.ILocation;

import java.util.Locale;

@Data
@ToString(includeFieldNames = true)
public class Location implements ILocation {

    private static final long serialVersionUID = -1158131870;

    private int locId;
    private String country;
    private String countryName;
    private String region;

    private String city;
    private String postalCode;
    private double latitude;
    private double longitude;
    private String metroCode;
    private String areaCode;

    public Location() {}

    public Location(int locId, String country, String region, String city, String postalCode,
            double latitude, double longitude, String metroCode, String areaCode) {
        this.locId = locId;
        this.country = country;
        this.region = region;
        this.city = city;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.metroCode = metroCode;
        this.areaCode = areaCode;

        Locale l = new Locale("", country);
        countryName = l.getDisplayCountry();
    }

    public String getCountryNameInEnglish() {
        Locale l = new Locale("en", this.country);
        return l.getDisplayCountry();
    }
}
