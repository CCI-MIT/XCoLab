package org.xcolab.portlets.contestmanagement.utils;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thomas on 2/16/2015.
 */
public class RequestParameterParser {


    public static List<Long> parseStringParameterFromRequestToLongList(PortletRequest request, String paramterName) throws NumberFormatException{
        List<String>  stringList = Arrays.asList(request.getParameter(paramterName).split("\\s*,\\s*"));
        return parseStringListToLongList(stringList);
    }

    private static List<Long> parseStringListToLongList(List<String> stringList) throws NumberFormatException{
        List<Long> longList = new ArrayList<>();
        for (String string : stringList) {
            if(!string.isEmpty()) {
                longList.add(Long.parseLong(string));
            }
        }
        return longList;
    }

}
