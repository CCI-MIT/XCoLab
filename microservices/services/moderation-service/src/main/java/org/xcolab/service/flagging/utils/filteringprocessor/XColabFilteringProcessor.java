package org.xcolab.service.flagging.utils.filteringprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import org.xcolab.model.tables.pojos.FilteredEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class XColabFilteringProcessor extends EntryFilteringProcessor {

    private static final Logger log = LoggerFactory.getLogger(XColabFilteringProcessor.class);

    private static final Map<String, String> profanitiesWordMap;
    private static final Map<String, String> profanitiesPhraseMap;

    static {
        profanitiesWordMap = new HashMap<>();
        profanitiesPhraseMap = new HashMap<>();
        try {
            Resource profanitiesResource = new ClassPathResource("/profanities.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    profanitiesResource.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(" ")) {
                    profanitiesWordMap.put(line.toLowerCase(), line.toLowerCase());
                } else {
                    profanitiesPhraseMap.put(line.toLowerCase(), line.toLowerCase());
                }
            }
        } catch (IOException e) {
            log.error("Could not load profanities list: {}", e.getMessage());
        }
    }

    @Override
    public FilteredEntry processEntry(FilteredEntry memberContentEntry) {
        String memberInput = memberContentEntry.getFullText().toLowerCase();
        String[] tokensInUserContent = memberInput.split("[\\p{Punct}\\s]+");
        for (String token : tokensInUserContent) {
            if (profanitiesWordMap.containsKey(token)) {
                return setFailedFiltering(memberContentEntry, token);
            }
        }
        for (String profItem : profanitiesPhraseMap.keySet()) {
            if (memberInput.contains(profItem)) {
                return setFailedFiltering(memberContentEntry, profItem);
            }
        }
        return setSuccessFiltering(memberContentEntry);
    }
}
