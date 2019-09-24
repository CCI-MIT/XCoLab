package org.xcolab.view.pages.members.users.utils;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.CsvResponseWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.HttpServletResponse;

public class MemberListCsvWriter extends CsvResponseWriter {

    private static final List<String> COLUMN_NAMES = Arrays.asList("Screen name", "First name",
            "Last name", "Email address", "User ID", "Country", "Bio", "Login date", "Image link");
    private static final Function<UserWrapper, List<String>> COLUMN_EXTRACTION_FUNCTION
            = (member -> Arrays.asList(
                    member.getScreenName(),
                    member.getFirstName(),
                    member.getLastName(),
                    member.getEmailAddress(),
                    Long.toString(member.getId()),
                    member.getCountry(),
                    member.getShortBio(),
                    member.getLoginDateString(),
                    member.getImageLinkUrl()
            ));

    public MemberListCsvWriter(HttpServletResponse response) throws IOException {
        super("membersList", COLUMN_NAMES, response);
    }

    public void writeMembers(List<UserWrapper> members) {
        members.stream()
                .map(COLUMN_EXTRACTION_FUNCTION)
                .forEach(this::writeRow);
    }
}
