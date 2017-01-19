package org.xcolab.view.api.members;

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

    public void setCountries(
            List<CountryCount> countries) {
        this.countries = countries;
    }

    public long getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(long membersCount) {
        this.membersCount = membersCount;
    }

    public static class CountryCount {
        private String country;
        private long membersCount;

        public CountryCount(String country, long membersCount) {
            this.country = country;
            this.membersCount = membersCount;
        }

        public String getCountry() {
            return country;
        }

        public long getMembersCount() {
            return membersCount;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setMembersCount(long membersCount) {
            this.membersCount = membersCount;
        }
    }
}
