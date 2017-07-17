package org.xcolab.logwatcher;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ParsedException implements Serializable{

    public String header;
    public List<String> stackTrace;

    public ParsedException(String head){
        header = head;
        stackTrace = new LinkedList<String>();
    }

    public boolean equals(Object o){
        if (o instanceof ParsedException) {
            String m1 = ((ParsedException) o).header.replaceAll("\\d","");
            String m2 = this.header.replaceAll("\\d","");
            return JaroWinklerSimilarity.score(m1,m2) > 0.9;
        }
        return false;
    }


}
