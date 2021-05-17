package org.xcolab.commons;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import org.xcolab.commons.html.LabelStringValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class CountryUtil {
    private static final BidiMap<String, String> codeToCountryMap = new DualHashBidiMap<>();
    private static final List<LabelStringValue> selectOptions;

    // Country list adapted from countries.csv by Google licensed under CC BY 3.0
    // Material: https://developers.google.com/public-data/docs/canonical/countries_csv
    // License: https://creativecommons.org/licenses/by/3.0/
    static {
        codeToCountryMap.put("AF", "Afghanistan");
        codeToCountryMap.put("AD", "Andorra");
        codeToCountryMap.put("AE", "United Arab Emirates");
        codeToCountryMap.put("AG", "Antigua and Barbuda");
        codeToCountryMap.put("AI", "Anguilla");
        codeToCountryMap.put("AL", "Albania");
        codeToCountryMap.put("AM", "Armenia");
        codeToCountryMap.put("AO", "Angola");
        codeToCountryMap.put("AQ", "Antarctica");
        codeToCountryMap.put("AR", "Argentina");
        codeToCountryMap.put("AS", "American Samoa");
        codeToCountryMap.put("AT", "Austria");
        codeToCountryMap.put("AU", "Australia");
        codeToCountryMap.put("AW", "Aruba");
        codeToCountryMap.put("AX", "Åland Islands");
        codeToCountryMap.put("AZ", "Azerbaijan");
        codeToCountryMap.put("BA", "Bosnia and Herzegovina");
        codeToCountryMap.put("BB", "Barbados");
        codeToCountryMap.put("BD", "Bangladesh");
        codeToCountryMap.put("BE", "Belgium");
        codeToCountryMap.put("BF", "Burkina Faso");
        codeToCountryMap.put("BG", "Bulgaria");
        codeToCountryMap.put("BH", "Bahrain");
        codeToCountryMap.put("BI", "Burundi");
        codeToCountryMap.put("BJ", "Benin");
        codeToCountryMap.put("BL", "Saint Barthélemy");
        codeToCountryMap.put("BM", "Bermuda");
        codeToCountryMap.put("BN", "Brunei");
        codeToCountryMap.put("BO", "Bolivia");
        codeToCountryMap.put("BQ", "Bonaire, Sint Eustatius and Saba");
        codeToCountryMap.put("BR", "Brazil");
        codeToCountryMap.put("BS", "Bahamas");
        codeToCountryMap.put("BT", "Bhutan");
        codeToCountryMap.put("BV", "Bouvet Island");
        codeToCountryMap.put("BW", "Botswana");
        codeToCountryMap.put("BY", "Belarus");
        codeToCountryMap.put("BZ", "Belize");
        codeToCountryMap.put("CA", "Canada");
        codeToCountryMap.put("CC", "Cocos [Keeling] Islands");
        codeToCountryMap.put("CD", "Congo [DRC]");
        codeToCountryMap.put("CF", "Central African Republic");
        codeToCountryMap.put("CG", "Congo [Republic]");
        codeToCountryMap.put("CH", "Switzerland");
        codeToCountryMap.put("CI", "Côte d'Ivoire");
        codeToCountryMap.put("CK", "Cook Islands");
        codeToCountryMap.put("CL", "Chile");
        codeToCountryMap.put("CM", "Cameroon");
        codeToCountryMap.put("CN", "China");
        codeToCountryMap.put("CO", "Colombia");
        codeToCountryMap.put("CR", "Costa Rica");
        codeToCountryMap.put("CU", "Cuba");
        codeToCountryMap.put("CV", "Cape Verde");
        codeToCountryMap.put("CW", "Curaçao");
        codeToCountryMap.put("CX", "Christmas Island");
        codeToCountryMap.put("CY", "Cyprus");
        codeToCountryMap.put("CZ", "Czech Republic");
        codeToCountryMap.put("DE", "Germany");
        codeToCountryMap.put("DJ", "Djibouti");
        codeToCountryMap.put("DK", "Denmark");
        codeToCountryMap.put("DM", "Dominica");
        codeToCountryMap.put("DO", "Dominican Republic");
        codeToCountryMap.put("DZ", "Algeria");
        codeToCountryMap.put("EC", "Ecuador");
        codeToCountryMap.put("EE", "Estonia");
        codeToCountryMap.put("EG", "Egypt");
        codeToCountryMap.put("EH", "Western Sahara");
        codeToCountryMap.put("ER", "Eritrea");
        codeToCountryMap.put("ES", "Spain");
        codeToCountryMap.put("ET", "Ethiopia");
        codeToCountryMap.put("FI", "Finland");
        codeToCountryMap.put("FJ", "Fiji");
        codeToCountryMap.put("FK", "Falkland Islands [Islas Malvinas]");
        codeToCountryMap.put("FM", "Micronesia");
        codeToCountryMap.put("FO", "Faroe Islands");
        codeToCountryMap.put("FR", "France");
        codeToCountryMap.put("GA", "Gabon");
        codeToCountryMap.put("GB", "United Kingdom");
        codeToCountryMap.put("GD", "Grenada");
        codeToCountryMap.put("GE", "Georgia");
        codeToCountryMap.put("GF", "French Guiana");
        codeToCountryMap.put("GG", "Guernsey");
        codeToCountryMap.put("GH", "Ghana");
        codeToCountryMap.put("GI", "Gibraltar");
        codeToCountryMap.put("GL", "Greenland");
        codeToCountryMap.put("GM", "Gambia");
        codeToCountryMap.put("GN", "Guinea");
        codeToCountryMap.put("GP", "Guadeloupe");
        codeToCountryMap.put("GQ", "Equatorial Guinea");
        codeToCountryMap.put("GR", "Greece");
        codeToCountryMap.put("GS", "South Georgia and the South Sandwich Islands");
        codeToCountryMap.put("GT", "Guatemala");
        codeToCountryMap.put("GU", "Guam");
        codeToCountryMap.put("GW", "Guinea-Bissau");
        codeToCountryMap.put("GY", "Guyana");
        codeToCountryMap.put("HK", "Hong Kong");
        codeToCountryMap.put("HM", "Heard Island and McDonald Islands");
        codeToCountryMap.put("HN", "Honduras");
        codeToCountryMap.put("HR", "Croatia");
        codeToCountryMap.put("HT", "Haiti");
        codeToCountryMap.put("HU", "Hungary");
        codeToCountryMap.put("ID", "Indonesia");
        codeToCountryMap.put("IE", "Ireland");
        codeToCountryMap.put("IL", "Israel");
        codeToCountryMap.put("IM", "Isle of Man");
        codeToCountryMap.put("IN", "India");
        codeToCountryMap.put("IO", "British Indian Ocean Territory");
        codeToCountryMap.put("IQ", "Iraq");
        codeToCountryMap.put("IR", "Iran");
        codeToCountryMap.put("IS", "Iceland");
        codeToCountryMap.put("IT", "Italy");
        codeToCountryMap.put("JE", "Jersey");
        codeToCountryMap.put("JM", "Jamaica");
        codeToCountryMap.put("JO", "Jordan");
        codeToCountryMap.put("JP", "Japan");
        codeToCountryMap.put("KE", "Kenya");
        codeToCountryMap.put("KG", "Kyrgyzstan");
        codeToCountryMap.put("KH", "Cambodia");
        codeToCountryMap.put("KI", "Kiribati");
        codeToCountryMap.put("KM", "Comoros");
        codeToCountryMap.put("KN", "Saint Kitts and Nevis");
        codeToCountryMap.put("KP", "North Korea");
        codeToCountryMap.put("KR", "South Korea");
        codeToCountryMap.put("KW", "Kuwait");
        codeToCountryMap.put("KY", "Cayman Islands");
        codeToCountryMap.put("KZ", "Kazakhstan");
        codeToCountryMap.put("LA", "Laos");
        codeToCountryMap.put("LB", "Lebanon");
        codeToCountryMap.put("LC", "Saint Lucia");
        codeToCountryMap.put("LI", "Liechtenstein");
        codeToCountryMap.put("LK", "Sri Lanka");
        codeToCountryMap.put("LR", "Liberia");
        codeToCountryMap.put("LS", "Lesotho");
        codeToCountryMap.put("LT", "Lithuania");
        codeToCountryMap.put("LU", "Luxembourg");
        codeToCountryMap.put("LV", "Latvia");
        codeToCountryMap.put("LY", "Libya");
        codeToCountryMap.put("MA", "Morocco");
        codeToCountryMap.put("MC", "Monaco");
        codeToCountryMap.put("MD", "Moldova");
        codeToCountryMap.put("ME", "Montenegro");
        codeToCountryMap.put("MF", "Saint Martin (French part)");
        codeToCountryMap.put("MG", "Madagascar");
        codeToCountryMap.put("MH", "Marshall Islands");
        codeToCountryMap.put("MK", "Macedonia [FYROM]");
        codeToCountryMap.put("ML", "Mali");
        codeToCountryMap.put("MM", "Myanmar [Burma]");
        codeToCountryMap.put("MN", "Mongolia");
        codeToCountryMap.put("MO", "Macau");
        codeToCountryMap.put("MP", "Northern Mariana Islands");
        codeToCountryMap.put("MQ", "Martinique");
        codeToCountryMap.put("MR", "Mauritania");
        codeToCountryMap.put("MS", "Montserrat");
        codeToCountryMap.put("MT", "Malta");
        codeToCountryMap.put("MU", "Mauritius");
        codeToCountryMap.put("MV", "Maldives");
        codeToCountryMap.put("MW", "Malawi");
        codeToCountryMap.put("MX", "Mexico");
        codeToCountryMap.put("MY", "Malaysia");
        codeToCountryMap.put("MZ", "Mozambique");
        codeToCountryMap.put("NA", "Namibia");
        codeToCountryMap.put("NC", "New Caledonia");
        codeToCountryMap.put("NE", "Niger");
        codeToCountryMap.put("NF", "Norfolk Island");
        codeToCountryMap.put("NG", "Nigeria");
        codeToCountryMap.put("NI", "Nicaragua");
        codeToCountryMap.put("NL", "Netherlands");
        codeToCountryMap.put("NO", "Norway");
        codeToCountryMap.put("NP", "Nepal");
        codeToCountryMap.put("NR", "Nauru");
        codeToCountryMap.put("NU", "Niue");
        codeToCountryMap.put("NZ", "New Zealand");
        codeToCountryMap.put("OM", "Oman");
        codeToCountryMap.put("PA", "Panama");
        codeToCountryMap.put("PE", "Peru");
        codeToCountryMap.put("PF", "French Polynesia");
        codeToCountryMap.put("PG", "Papua New Guinea");
        codeToCountryMap.put("PH", "Philippines");
        codeToCountryMap.put("PK", "Pakistan");
        codeToCountryMap.put("PL", "Poland");
        codeToCountryMap.put("PM", "Saint Pierre and Miquelon");
        codeToCountryMap.put("PN", "Pitcairn Islands");
        codeToCountryMap.put("PR", "Puerto Rico");
        codeToCountryMap.put("PS", "Palestinian Territories");
        codeToCountryMap.put("PT", "Portugal");
        codeToCountryMap.put("PW", "Palau");
        codeToCountryMap.put("PY", "Paraguay");
        codeToCountryMap.put("QA", "Qatar");
        codeToCountryMap.put("RE", "Réunion");
        codeToCountryMap.put("RO", "Romania");
        codeToCountryMap.put("RS", "Serbia");
        codeToCountryMap.put("RU", "Russia");
        codeToCountryMap.put("RW", "Rwanda");
        codeToCountryMap.put("SA", "Saudi Arabia");
        codeToCountryMap.put("SB", "Solomon Islands");
        codeToCountryMap.put("SC", "Seychelles");
        codeToCountryMap.put("SD", "Sudan");
        codeToCountryMap.put("SE", "Sweden");
        codeToCountryMap.put("SG", "Singapore");
        codeToCountryMap.put("SH", "Saint Helena");
        codeToCountryMap.put("SI", "Slovenia");
        codeToCountryMap.put("SJ", "Svalbard and Jan Mayen");
        codeToCountryMap.put("SK", "Slovakia");
        codeToCountryMap.put("SL", "Sierra Leone");
        codeToCountryMap.put("SM", "San Marino");
        codeToCountryMap.put("SN", "Senegal");
        codeToCountryMap.put("SO", "Somalia");
        codeToCountryMap.put("SR", "Suriname");
        codeToCountryMap.put("SS", "South Sudan");
        codeToCountryMap.put("ST", "São Tomé and Príncipe");
        codeToCountryMap.put("SV", "El Salvador");
        codeToCountryMap.put("SX", "Sint Maarten (Dutch part)");
        codeToCountryMap.put("SY", "Syria");
        codeToCountryMap.put("SZ", "Swaziland");
        codeToCountryMap.put("TC", "Turks and Caicos Islands");
        codeToCountryMap.put("TD", "Chad");
        codeToCountryMap.put("TF", "French Southern Territories");
        codeToCountryMap.put("TG", "Togo");
        codeToCountryMap.put("TH", "Thailand");
        codeToCountryMap.put("TJ", "Tajikistan");
        codeToCountryMap.put("TK", "Tokelau");
        codeToCountryMap.put("TL", "Timor-Leste");
        codeToCountryMap.put("TM", "Turkmenistan");
        codeToCountryMap.put("TN", "Tunisia");
        codeToCountryMap.put("TO", "Tonga");
        codeToCountryMap.put("TR", "Turkey");
        codeToCountryMap.put("TT", "Trinidad and Tobago");
        codeToCountryMap.put("TV", "Tuvalu");
        codeToCountryMap.put("TW", "Taiwan");
        codeToCountryMap.put("TZ", "Tanzania");
        codeToCountryMap.put("UA", "Ukraine");
        codeToCountryMap.put("UG", "Uganda");
        codeToCountryMap.put("UM", "U.S. Minor Outlying Islands");
        codeToCountryMap.put("US", "United States");
        codeToCountryMap.put("UY", "Uruguay");
        codeToCountryMap.put("UZ", "Uzbekistan");
        codeToCountryMap.put("VA", "Vatican City");
        codeToCountryMap.put("VC", "Saint Vincent and the Grenadines");
        codeToCountryMap.put("VE", "Venezuela");
        codeToCountryMap.put("VG", "British Virgin Islands");
        codeToCountryMap.put("VI", "U.S. Virgin Islands");
        codeToCountryMap.put("VN", "Vietnam");
        codeToCountryMap.put("VU", "Vanuatu");
        codeToCountryMap.put("WF", "Wallis and Futuna");
        codeToCountryMap.put("WS", "Samoa");
        codeToCountryMap.put("YE", "Yemen");
        codeToCountryMap.put("YT", "Mayotte");
        codeToCountryMap.put("ZA", "South Africa");
        codeToCountryMap.put("ZM", "Zambia");
        codeToCountryMap.put("ZW", "Zimbabwe");

        List<LabelStringValue> rawSelectOptions = new ArrayList<>(
                LabelStringValue.fromMap(codeToCountryMap));
        rawSelectOptions.sort(Comparator.comparing(LabelStringValue::getLable));
        selectOptions = Collections.unmodifiableList(rawSelectOptions);
    }

    private CountryUtil() { }

    public static String getCountryForCode(String code) {
    	if (codeToCountryMap.containsKey(code)) {
    		return codeToCountryMap.get(code);
    	}
    	return "";
    }

    public static String getCodeForCounty(String country) {
        if (codeToCountryMap.containsValue(country)) {
            return codeToCountryMap.getKey(country);
        }
        return "";
    }

    public static List<LabelStringValue> getSelectOptions() {
        return selectOptions;
    }
}
