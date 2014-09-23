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
        lastName = row.getCell(4).getStringCellValue();
        firstName = row.getCell(2).getStringCellValue();
        middleName = row.getCell(3).getStringCellValue();
        eMail = row.getCell(5).getStringCellValue();
        secondaryEmail = row.getCell(6).getStringCellValue();
//            regDate = row.getCell(10).getDateCellValue();
        isMember = YES.equals(row.getCell(7).getStringCellValue());
        colabEmail = row.getCell(8).getStringCellValue();
        if (row.getCell(9) != null) {
            joinColab = YES.equals(row.getCell(9).getStringCellValue());
        } else {
            joinColab = false;
        }
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
