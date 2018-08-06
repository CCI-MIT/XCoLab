package org.xcolab.service.contest.domain.plantemplate;

import org.xcolab.model.tables.pojos.ProposalTemplate;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;


public interface ProposalTemplateDao {

    ProposalTemplate get(Long id) throws NotFoundException;

    boolean update(ProposalTemplate planTemplate);

    ProposalTemplate create(ProposalTemplate planTemplate);

    List<ProposalTemplate> findByGiven();

    int delete(Long id);
}
