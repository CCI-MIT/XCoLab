package org.xcolab.service.emails.domain;

import org.xcolab.model.tables.pojos.OutgoingEmail;
import org.xcolab.service.emails.exceptions.NotFoundException;

import java.util.List;

public interface OutgoingEmailDao {

    OutgoingEmail create(OutgoingEmail outgoingEmail);

    OutgoingEmail get(Long colabEmailId) throws NotFoundException;

    boolean update(OutgoingEmail outgoingEmail);

    List<OutgoingEmail> findByGiven(String emailSubject, String emailTo, Long referenceId,
            String emailBodyHash);
}
