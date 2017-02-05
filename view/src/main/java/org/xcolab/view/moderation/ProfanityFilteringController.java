package org.xcolab.view.moderation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.filtering.FilteringClient;
import org.xcolab.client.filtering.enums.FilteringSource;
import org.xcolab.client.filtering.enums.FilteringStatus;
import org.xcolab.client.filtering.pojo.FilteredEntry;
import org.xcolab.client.members.pojo.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/profanityfiltering")
public class ProfanityFilteringController {

    @PostMapping
    public ResponseJson filter(HttpServletRequest request, HttpServletResponse response,
            @RequestParam String fullText, @RequestParam String source,
            Member loggedInMember) {

        FilteredEntry filteredEntry = new FilteredEntry();
        filteredEntry.setAuthorId(loggedInMember.getId_());
        filteredEntry.setFullText(fullText);
        if (source.equals(FilteringSource.DISCUSSION.getSource())){
            filteredEntry.setSource(FilteringSource.DISCUSSION.getId());
        } else {
            filteredEntry.setSource(FilteringSource.PROPOSAL.getId());
        }

        filteredEntry = FilteringClient.createFilteredEntry(filteredEntry);

        final boolean isValid = filteredEntry.getStatus().equals(FilteringStatus.APPROVED.getId());
        return new ResponseJson(isValid, filteredEntry.getUuid());
    }

    private static class ResponseJson {
        private final boolean valid;
        private final String uuid;

        private ResponseJson(boolean valid, String uuid) {
            this.valid = valid;
            this.uuid = uuid;
        }

        public boolean isValid() {
            return valid;
        }

        public String getUuid() {
            return uuid;
        }
    }
}
