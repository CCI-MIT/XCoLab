package org.xcolab.service.filtering.utils.filteringprocessor;


import org.xcolab.model.tables.pojos.FilteredEntry;
import org.xcolab.service.filtering.enums.FilteringStatus;
import org.xcolab.service.filtering.utils.HttpClientUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;


public class PurgoMalumFilteringProcessor implements EntryFilteringProcessor {

    private final static String PURGOMALUMDEFAULTURL = "http://www.purgomalum.com/service/";
    private final static String PROFANITYCHECKSERVICE = "containsprofanity";
    private final static String PROFANITYJSONREPLACEMENT = "json";
    private final static String TEXTVARIABLE = "?text=";

    @Override
    public FilteredEntry processEntry(FilteredEntry memberContentEntry) {

        try {
            String checkReturn = HttpClientUtils.getUrlContents(PURGOMALUMDEFAULTURL + PROFANITYCHECKSERVICE +
                    TEXTVARIABLE + URLEncoder.encode(memberContentEntry.getFullText(), "UTF-8"));
            if (checkReturn.equals("false")) {
                memberContentEntry.setAnsweredAt(new Timestamp(new Date().getTime()));
                memberContentEntry.setResponseFullText(null);
                memberContentEntry.setStatus(FilteringStatus.APPROVED.getId());
                return memberContentEntry;
            } else {
                String filteredText = HttpClientUtils.getUrlContents(PURGOMALUMDEFAULTURL + PROFANITYJSONREPLACEMENT +
                        TEXTVARIABLE + URLEncoder.encode(memberContentEntry.getFullText(),"UTF-8"));
                memberContentEntry.setAnsweredAt(new Timestamp(new Date().getTime()));
                memberContentEntry.setResponseFullText(filteredText);
                memberContentEntry.setStatus(FilteringStatus.REJECTED.getId());
                return memberContentEntry;
            }
        }catch (UnsupportedEncodingException ignored){

        }
        return null;
    }
}
