package org.xcolab.portlets.loginregister.conference;

import org.apache.poi.ss.usermodel.Row;

import java.util.Date;

/**
* @author pdeboer
*         First created on 8/28/13 at 5:24 PM
*/
class ConferenceUser {
    private static final String YES = "Yes";
    private String lastName, firstName, middleName, eMail, secondaryEmail, colabEmail;
    private Date regDate;
    private Boolean isMember, joinColab;

    ConferenceUser(Row row) {
        lastName = getCellOrEmptyString(row, 4);
        firstName = getCellOrEmptyString(row, 2);
        middleName = getCellOrEmptyString(row, 3);
        eMail = getCellOrEmptyString(row, 5);
        secondaryEmail = getCellOrEmptyString(row, 6);
        isMember = YES.equals(getCellOrEmptyString(row, 7));
        colabEmail = getCellOrEmptyString(row, 8);
        if (row.getCell(9) != null) {
            joinColab = YES.equals(row.getCell(9).getStringCellValue());
        } else {
            joinColab = false;
        }
    }

    private static String getCellOrEmptyString(Row row, int cellNr) {
        return row.getCell(cellNr) != null ? row.getCell(cellNr).getStringCellValue() : "";
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String geteMail() {
        return eMail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public Date getRegDate() {
        return regDate;
    }

    String getColabEmail() {
        return colabEmail;
    }

    Boolean getMember() {
        return isMember;
    }

    Boolean getJoinColab() {
        return joinColab;
    }
}
