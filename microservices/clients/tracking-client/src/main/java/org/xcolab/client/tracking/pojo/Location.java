package org.xcolab.client.tracking.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * Represents a location on the globe (longitude, latitude and all other information that can be discovered by ip).
 * @author janusz
 *
 */
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final TypeProvider<Location> TYPES = new TypeProvider<>(Location.class,
			new ParameterizedTypeReference<List<Location>>() {});

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
	
	public Location(int locId, String country, String region, String city,
			String postalCode, double latitude, double longitude,
			String metroCode, String areaCode) {
		super();
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
	public Location() {
	}
	@Override
	public String toString() {
		return "Location [locId=" + locId + ", country=" + country
				+ ", countryName=" + countryName + ", region=" + region
				+ ", city=" + city + ", postalCode=" + postalCode
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", metroCode=" + metroCode + ", areaCode=" + areaCode
				+ "]";
	}
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryNameInEnglish() {
        Locale l = new Locale("en", this.country);
        return l.getDisplayCountry();
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getMetroCode() {
		return metroCode;
	}
	public void setMetroCode(String metroCode) {
		this.metroCode = metroCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
}
