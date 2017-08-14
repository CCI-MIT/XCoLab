package org.xcolab.view.taglibs.xcolab.functions;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.translate.CharSequenceTranslator;

public class StringEscapeTagFunctions {

    private static final CharSequenceTranslator ESCAPE_JSON = StringEscapeUtils.ESCAPE_ECMASCRIPT;

    public static String escapeStringForJavascript(String string){
        return ESCAPE_JSON.translate(string);
    }
}
