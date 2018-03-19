package org.xcolab.view.api.members;

import org.xcolab.commons.CountryUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MembersStats {

    private long membersCount;
    private List<CountryCount> countries;

    public MembersStats(long membersCount, Map<String, Long> countryCounts) {
        this.membersCount = membersCount;
        this.countries = countryCounts.entrySet().stream()
                .map(entry -> new CountryCount(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<CountryCount> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryCount> countries) {
        this.countries = countries;
    }

    public long getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(long membersCount) {
        this.membersCount = membersCount;
    }

    public static class CountryCount {

        private String countryCode;
        private String countryName;
        private long membersCount;

        public CountryCount(String countryCode, long membersCount) {
            this.countryCode = countryCode;
            this.membersCount = membersCount;
            this.countryName = CountryUtil.getCountryForCode(countryCode);
        }

        public String getCountryCode() {
            return countryCode;
        }

        public long getMembersCount() {
            return membersCount;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public void setMembersCount(long membersCount) {
            this.membersCount = membersCount;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }
    }
}
