package org.xcolab.service.filtering.utils.filteringprocessor;


import org.xcolab.model.tables.pojos.FilteredEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XColabFilteringProcessor extends EntryFilteringProcessor{

    private static Map<String, String> profanitiesWordMap;
    private static Map<String, String> profanitiesPhraseMap;

    static{
        profanitiesWordMap = new HashMap<>();
        profanitiesPhraseMap = new HashMap<>();
        try {
            InputStream in = XColabFilteringProcessor.class.getClassLoader()
                    .getResourceAsStream("/profanities.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                if( !line.contains(" ")) {
                    profanitiesWordMap.put(line.toLowerCase(), line.toLowerCase());
                }else{
                    profanitiesPhraseMap.put(line.toLowerCase(), line.toLowerCase());
                }
            }
        }catch (IOException ignored){
            ignored.printStackTrace();

        }

    }

    @Override
    public FilteredEntry processEntry(FilteredEntry memberContentEntry) {
        String memberInput = memberContentEntry.getFullText().toLowerCase();
        String[] tokensInUserContent = memberInput.split("[\\p{Punct}\\s]+");
        for(String token : tokensInUserContent){
            if( profanitiesWordMap.containsKey(token)){
                return setFailedFiltering(memberContentEntry, token);
            }
        }
        Iterator<String> it =  profanitiesPhraseMap.keySet().iterator();
        while(it.hasNext()){
            String profItem = it.next();
            if(memberInput.contains(profItem)){
                return setFailedFiltering(memberContentEntry, profItem);
            }
        }
        return setSuccessFiltering(memberContentEntry);
    }
}
