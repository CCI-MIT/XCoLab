package org.xcolab.portlets.userprofile.utils;

import com.liferay.portal.kernel.servlet.SessionErrors;

import javax.portlet.ActionRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Helper {

        private static final String COLLAB_URL_PARAMETER_PREFIX = "_collab_param";

    public static String removeParamFromRequestStr(String requestStr, String param) {
        return requestStr == null ? null : requestStr.replaceAll("&?" + param + "=[^&#]*", "");
    }

        public static String getUrlParameterKey(String key) {
                return COLLAB_URL_PARAMETER_PREFIX + key;
        }

    public static String modifyRedirectUrl(String redirect, ActionRequest actionRequest, Map<String, String> parameters) 
    throws UnsupportedEncodingException {

        // add error messages to redirect url
        StringBuilder sb = new StringBuilder();
        // remove error info from url
        String[] locationAndHash = redirect.split("#");
        String[] locationAndQueryString = locationAndHash[0].split("\\?");
        
        sb.append(locationAndQueryString[0]);
        sb.append("?");
        

        if (locationAndQueryString.length > 1 && locationAndQueryString[1].length() > 0) {
            sb.append(locationAndQueryString[1]);
            sb.append("&");
        }
        
        
        Iterator<String> iter = SessionErrors.iterator(actionRequest);
        boolean appendAnd = false;
        
        while (iter.hasNext()) {
            if (appendAnd) {
                sb.append("&");
            }
            
            sb.append("signinRegError=");
            String exception = iter.next();
            sb.append(exception.substring(exception.lastIndexOf(".") + 1));
            
            appendAnd = true;
        }
        
        for (String key: parameters.keySet()) {
            if (appendAnd) {
                sb.append("&");
            }
            appendAnd = true;
            sb.append(key + "=" + URLEncoder.encode(parameters.get(key), "UTF-8"));
        }
        
        if (locationAndHash.length > 1) {
            sb.append("#");
            sb.append(locationAndHash[1]);
        }

        redirect = sb.toString();
        
        return redirect;
    }
    
    private final static Map<String, String> codeToCountryMap = new HashMap<String, String>();
    static {
            codeToCountryMap.put("AF", "Afghanistan");
            codeToCountryMap.put("AX", "Åland Islands");
            codeToCountryMap.put("AL", "Albania");
            codeToCountryMap.put("DZ", "Algeria");
            codeToCountryMap.put("AS", "American Samoa");
            codeToCountryMap.put("AD", "Andorra");
            codeToCountryMap.put("AO", "Angola");
            codeToCountryMap.put("AI", "Anguilla");
            codeToCountryMap.put("AQ", "Antarctica");
            codeToCountryMap.put("AG", "Antigua and Barbuda");
            codeToCountryMap.put("AR", "Argentina");
            codeToCountryMap.put("AM", "Armenia");
            codeToCountryMap.put("AW", "Aruba");
            codeToCountryMap.put("AU", "Australia");
            codeToCountryMap.put("AT", "Austria");
            codeToCountryMap.put("AZ", "Azerbaijan");
            codeToCountryMap.put("BS", "Bahamas");
            codeToCountryMap.put("BH", "Bahrain");
            codeToCountryMap.put("BD", "Bangladesh");
            codeToCountryMap.put("BB", "Barbados");
            codeToCountryMap.put("BY", "Belarus");
            codeToCountryMap.put("BE", "Belgium");
            codeToCountryMap.put("BZ", "Belize");
            codeToCountryMap.put("BJ", "Benin");
            codeToCountryMap.put("BM", "Bermuda");
            codeToCountryMap.put("BT", "Bhutan");
            codeToCountryMap.put("BO", "Bolivia, Plurinational State of");
            codeToCountryMap.put("BQ", "Bonaire, Sint Eustatius and Saba");
            codeToCountryMap.put("BA", "Bosnia and Herzegovina");
            codeToCountryMap.put("BW", "Botswana");
            codeToCountryMap.put("BV", "Bouvet Island");
            codeToCountryMap.put("BR", "Brazil");
            codeToCountryMap.put("IO", "British Indian Ocean Territory");
            codeToCountryMap.put("BN", "Brunei Darussalam");
            codeToCountryMap.put("BG", "Bulgaria");
            codeToCountryMap.put("BF", "Burkina Faso");
            codeToCountryMap.put("BI", "Burundi");
            codeToCountryMap.put("KH", "Cambodia");
            codeToCountryMap.put("CM", "Cameroon");
            codeToCountryMap.put("CA", "Canada");
            codeToCountryMap.put("CV", "Cape Verde");
            codeToCountryMap.put("KY", "Cayman Islands");
            codeToCountryMap.put("CF", "Central African Republic");
            codeToCountryMap.put("TD", "Chad");
            codeToCountryMap.put("CL", "Chile");
            codeToCountryMap.put("CN", "China");
            codeToCountryMap.put("CX", "Christmas Island");
            codeToCountryMap.put("CC", "Cocos (Keeling) Islands");
            codeToCountryMap.put("CO", "Colombia");
            codeToCountryMap.put("KM", "Comoros");
            codeToCountryMap.put("CG", "Congo");
            codeToCountryMap.put("CD", "Congo, the Democratic Republic of the");
            codeToCountryMap.put("CK", "Cook Islands");
            codeToCountryMap.put("CR", "Costa Rica");
            codeToCountryMap.put("CI", "Côte d'Ivoire");
            codeToCountryMap.put("HR", "Croatia");
            codeToCountryMap.put("CU", "Cuba");
            codeToCountryMap.put("CW", "Curaçao");
            codeToCountryMap.put("CY", "Cyprus");
            codeToCountryMap.put("CZ", "Czech Republic");
            codeToCountryMap.put("DK", "Denmark");
            codeToCountryMap.put("DJ", "Djibouti");
            codeToCountryMap.put("DM", "Dominica");
            codeToCountryMap.put("DO", "Dominican Republic");
            codeToCountryMap.put("EC", "Ecuador");
            codeToCountryMap.put("EG", "Egypt");
            codeToCountryMap.put("SV", "El Salvador");
            codeToCountryMap.put("GQ", "Equatorial Guinea");
            codeToCountryMap.put("ER", "Eritrea");
            codeToCountryMap.put("EE", "Estonia");
            codeToCountryMap.put("ET", "Ethiopia");
            codeToCountryMap.put("FK", "Falkland Islands (Malvinas)");
            codeToCountryMap.put("FO", "Faroe Islands");
            codeToCountryMap.put("FJ", "Fiji");
            codeToCountryMap.put("FI", "Finland");
            codeToCountryMap.put("FR", "France");
            codeToCountryMap.put("GF", "French Guiana");
            codeToCountryMap.put("PF", "French Polynesia");
            codeToCountryMap.put("TF", "French Southern Territories");
            codeToCountryMap.put("GA", "Gabon");
            codeToCountryMap.put("GM", "Gambia");
            codeToCountryMap.put("GE", "Georgia");
            codeToCountryMap.put("DE", "Germany");
            codeToCountryMap.put("GH", "Ghana");
            codeToCountryMap.put("GI", "Gibraltar");
            codeToCountryMap.put("GR", "Greece");
            codeToCountryMap.put("GL", "Greenland");
            codeToCountryMap.put("GD", "Grenada");
            codeToCountryMap.put("GP", "Guadeloupe");
            codeToCountryMap.put("GU", "Guam");
            codeToCountryMap.put("GT", "Guatemala");
            codeToCountryMap.put("GG", "Guernsey");
            codeToCountryMap.put("GN", "Guinea");
            codeToCountryMap.put("GW", "Guinea-Bissau");
            codeToCountryMap.put("GY", "Guyana");
            codeToCountryMap.put("HT", "Haiti");
            codeToCountryMap.put("HM", "Heard Island and McDonald Islands");
            codeToCountryMap.put("VA", "Holy See (Vatican City State)");
            codeToCountryMap.put("HN", "Honduras");
            codeToCountryMap.put("HK", "Hong Kong");
            codeToCountryMap.put("HU", "Hungary");
            codeToCountryMap.put("IS", "Iceland");
            codeToCountryMap.put("IN", "India");
            codeToCountryMap.put("ID", "Indonesia");
            codeToCountryMap.put("IR", "Iran, Islamic Republic of");
            codeToCountryMap.put("IQ", "Iraq");
            codeToCountryMap.put("IE", "Ireland");
            codeToCountryMap.put("IM", "Isle of Man");
            codeToCountryMap.put("IL", "Israel");
            codeToCountryMap.put("IT", "Italy");
            codeToCountryMap.put("JM", "Jamaica");
            codeToCountryMap.put("JP", "Japan");
            codeToCountryMap.put("JE", "Jersey");
            codeToCountryMap.put("JO", "Jordan");
            codeToCountryMap.put("KZ", "Kazakhstan");
            codeToCountryMap.put("KE", "Kenya");
            codeToCountryMap.put("KI", "Kiribati");
            codeToCountryMap.put("KP", "Korea, Democratic People's Republic of");
            codeToCountryMap.put("KR", "Korea, Republic of");
            codeToCountryMap.put("KW", "Kuwait");
            codeToCountryMap.put("KG", "Kyrgyzstan");
            codeToCountryMap.put("LA", "Lao People's Democratic Republic");
            codeToCountryMap.put("LV", "Latvia");
            codeToCountryMap.put("LB", "Lebanon");
            codeToCountryMap.put("LS", "Lesotho");
            codeToCountryMap.put("LR", "Liberia");
            codeToCountryMap.put("LY", "Libya");
            codeToCountryMap.put("LI", "Liechtenstein");
            codeToCountryMap.put("LT", "Lithuania");
            codeToCountryMap.put("LU", "Luxembourg");
            codeToCountryMap.put("MO", "Macao");
            codeToCountryMap.put("MK", "Macedonia, the former Yugoslav Republic of");
            codeToCountryMap.put("MG", "Madagascar");
            codeToCountryMap.put("MW", "Malawi");
            codeToCountryMap.put("MY", "Malaysia");
            codeToCountryMap.put("MV", "Maldives");
            codeToCountryMap.put("ML", "Mali");
            codeToCountryMap.put("MT", "Malta");
            codeToCountryMap.put("MH", "Marshall Islands");
            codeToCountryMap.put("MQ", "Martinique");
            codeToCountryMap.put("MR", "Mauritania");
            codeToCountryMap.put("MU", "Mauritius");
            codeToCountryMap.put("YT", "Mayotte");
            codeToCountryMap.put("MX", "Mexico");
            codeToCountryMap.put("FM", "Micronesia, Federated States of");
            codeToCountryMap.put("MD", "Moldova, Republic of");
            codeToCountryMap.put("MC", "Monaco");
            codeToCountryMap.put("MN", "Mongolia");
            codeToCountryMap.put("ME", "Montenegro");
            codeToCountryMap.put("MS", "Montserrat");
            codeToCountryMap.put("MA", "Morocco");
            codeToCountryMap.put("MZ", "Mozambique");
            codeToCountryMap.put("MM", "Myanmar");
            codeToCountryMap.put("NA", "Namibia");
            codeToCountryMap.put("NR", "Nauru");
            codeToCountryMap.put("NP", "Nepal");
            codeToCountryMap.put("NL", "Netherlands");
            codeToCountryMap.put("NC", "New Caledonia");
            codeToCountryMap.put("NZ", "New Zealand");
            codeToCountryMap.put("NI", "Nicaragua");
            codeToCountryMap.put("NE", "Niger");
            codeToCountryMap.put("NG", "Nigeria");
            codeToCountryMap.put("NU", "Niue");
            codeToCountryMap.put("NF", "Norfolk Island");
            codeToCountryMap.put("MP", "Northern Mariana Islands");
            codeToCountryMap.put("NO", "Norway");
            codeToCountryMap.put("OM", "Oman");
            codeToCountryMap.put("PK", "Pakistan");
            codeToCountryMap.put("PW", "Palau");
            codeToCountryMap.put("PS", "Palestinian Territory, Occupied");
            codeToCountryMap.put("PA", "Panama");
            codeToCountryMap.put("PG", "Papua New Guinea");
            codeToCountryMap.put("PY", "Paraguay");
            codeToCountryMap.put("PE", "Peru");
            codeToCountryMap.put("PH", "Philippines");
            codeToCountryMap.put("PN", "Pitcairn");
            codeToCountryMap.put("PL", "Poland");
            codeToCountryMap.put("PT", "Portugal");
            codeToCountryMap.put("PR", "Puerto Rico");
            codeToCountryMap.put("QA", "Qatar");
            codeToCountryMap.put("RE", "Réunion");
            codeToCountryMap.put("RO", "Romania");
            codeToCountryMap.put("RU", "Russian Federation");
            codeToCountryMap.put("RW", "Rwanda");
            codeToCountryMap.put("BL", "Saint Barthélemy");
            codeToCountryMap.put("SH", "Saint Helena, Ascension and Tristan da Cunha");
            codeToCountryMap.put("KN", "Saint Kitts and Nevis");
            codeToCountryMap.put("LC", "Saint Lucia");
            codeToCountryMap.put("MF", "Saint Martin (French part)");
            codeToCountryMap.put("PM", "Saint Pierre and Miquelon");
            codeToCountryMap.put("VC", "Saint Vincent and the Grenadines");
            codeToCountryMap.put("WS", "Samoa");
            codeToCountryMap.put("SM", "San Marino");
            codeToCountryMap.put("ST", "Sao Tome and Principe");
            codeToCountryMap.put("SA", "Saudi Arabia");
            codeToCountryMap.put("SN", "Senegal");
            codeToCountryMap.put("RS", "Serbia");
            codeToCountryMap.put("SC", "Seychelles");
            codeToCountryMap.put("SL", "Sierra Leone");
            codeToCountryMap.put("SG", "Singapore");
            codeToCountryMap.put("SX", "Sint Maarten (Dutch part)");
            codeToCountryMap.put("SK", "Slovakia");
            codeToCountryMap.put("SI", "Slovenia");
            codeToCountryMap.put("SB", "Solomon Islands");
            codeToCountryMap.put("SO", "Somalia");
            codeToCountryMap.put("ZA", "South Africa");
            codeToCountryMap.put("GS", "South Georgia and the South Sandwich Islands");
            codeToCountryMap.put("SS", "South Sudan");
            codeToCountryMap.put("ES", "Spain");
            codeToCountryMap.put("LK", "Sri Lanka");
            codeToCountryMap.put("SD", "Sudan");
            codeToCountryMap.put("SR", "Suriname");
            codeToCountryMap.put("SJ", "Svalbard and Jan Mayen");
            codeToCountryMap.put("SZ", "Swaziland");
            codeToCountryMap.put("SE", "Sweden");
            codeToCountryMap.put("CH", "Switzerland");
            codeToCountryMap.put("SY", "Syrian Arab Republic");
            codeToCountryMap.put("TW", "Taiwan, Province of China");
            codeToCountryMap.put("TJ", "Tajikistan");
            codeToCountryMap.put("TZ", "Tanzania, United Republic of");
            codeToCountryMap.put("TH", "Thailand");
            codeToCountryMap.put("TL", "Timor-Leste");
            codeToCountryMap.put("TG", "Togo");
            codeToCountryMap.put("TK", "Tokelau");
            codeToCountryMap.put("TO", "Tonga");
            codeToCountryMap.put("TT", "Trinidad and Tobago");
            codeToCountryMap.put("TN", "Tunisia");
            codeToCountryMap.put("TR", "Turkey");
            codeToCountryMap.put("TM", "Turkmenistan");
            codeToCountryMap.put("TC", "Turks and Caicos Islands");
            codeToCountryMap.put("TV", "Tuvalu");
            codeToCountryMap.put("UG", "Uganda");
            codeToCountryMap.put("UA", "Ukraine");
            codeToCountryMap.put("AE", "United Arab Emirates");
            codeToCountryMap.put("GB", "United Kingdom");
            codeToCountryMap.put("US", "United States");
            codeToCountryMap.put("UM", "United States Minor Outlying Islands");
            codeToCountryMap.put("UY", "Uruguay");
            codeToCountryMap.put("UZ", "Uzbekistan");
            codeToCountryMap.put("VU", "Vanuatu");
            codeToCountryMap.put("VE", "Venezuela, Bolivarian Republic of");
            codeToCountryMap.put("VN", "Viet Nam");
            codeToCountryMap.put("VG", "Virgin Islands, British");
            codeToCountryMap.put("VI", "Virgin Islands, U.S.");
            codeToCountryMap.put("WF", "Wallis and Futuna");
            codeToCountryMap.put("EH", "Western Sahara");
            codeToCountryMap.put("YE", "Yemen");
            codeToCountryMap.put("ZM", "Zambia");
            codeToCountryMap.put("ZW", "Zimbabwe");
    }
    
    public static String getCountryForCode(String code) {
    	if (codeToCountryMap.containsKey(code)) {
    		return codeToCountryMap.get(code);
    	}
    	return code;
    }

        public static String getCodeForCounty(String country) {

        for(Map.Entry entry: codeToCountryMap.entrySet()){
                if(country.equals(entry.getValue())){
                        return (String) entry.getKey();
                }
        }
        return null;
        }
}
