package org.xcolab.service.emails.domain;

import org.xcolab.model.tables.pojos.ColabEmail;
import org.xcolab.service.emails.exceptions.NotFoundException;

import java.util.List;

public interface ColabEmailDao {

    ColabEmail create(ColabEmail colabEmail);
    ColabEmail get(Long colabEmailId) throws NotFoundException;
    boolean update(ColabEmail colabEmail);
    List<ColabEmail> findByGiven(String emailSubject, String emailTo, Long referenceId, String emailBodyHash);


}
