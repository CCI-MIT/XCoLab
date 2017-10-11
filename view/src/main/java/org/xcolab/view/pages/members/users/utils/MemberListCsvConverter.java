package org.xcolab.view.pages.members.users.utils;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.util.CsvConverter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MemberListCsvConverter extends CsvConverter {

    private static final List<String> COLUMN_NAMES = Arrays.asList("Screen name", "First name",
            "Last name", "Email address", "Country");
    private static final Function<Member, List<String>> COLUMN_EXTRACTION_FUNCTION
            = (member -> Arrays.asList(
                    member.getScreenName(),
                    member.getFirstName(),
                    member.getLastName(),
                    member.getEmailAddress(),
                    member.getCountry()
            ));

    public MemberListCsvConverter() {
        super(COLUMN_NAMES);
    }

    public void addMembers(List<Member> members) {
        members.stream()
                .map(COLUMN_EXTRACTION_FUNCTION)
                .forEach(this::addRow);
    }
}
