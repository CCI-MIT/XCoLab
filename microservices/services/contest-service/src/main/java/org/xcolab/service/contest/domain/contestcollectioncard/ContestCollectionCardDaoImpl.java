package org.xcolab.service.contest.domain.contestcollectioncard;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ContestCollectionCard;
import org.xcolab.model.tables.records.ContestCollectionCardRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST_COLLECTION_CARD;

@Repository
public class ContestCollectionCardDaoImpl implements ContestCollectionCardDao {

    @Autowired
    private DSLContext dslContext;


    @Override
    public ContestCollectionCard create(ContestCollectionCard contestCollectionCard) {

        ContestCollectionCardRecord ret = this.dslContext.insertInto(CONTEST_COLLECTION_CARD)
                .set(CONTEST_COLLECTION_CARD.PARENT, contestCollectionCard.getParent())
                .set(CONTEST_COLLECTION_CARD.BIG_ONTOLOGY_TERM, contestCollectionCard.getBig_ontology_term())
                .set(CONTEST_COLLECTION_CARD.SMALL_ONTOLOGY_TERM, contestCollectionCard.getSmall_ontology_term())
                .set(CONTEST_COLLECTION_CARD.DESCRIPTION, contestCollectionCard.getDescription())
                .set(CONTEST_COLLECTION_CARD.VISIBLE, contestCollectionCard.getVisible())
                .set(CONTEST_COLLECTION_CARD.ORDER, contestCollectionCard.getOrder())
                .set(CONTEST_COLLECTION_CARD.ONTOLOGY_TERM_TO_LOAD, contestCollectionCard.getOntology_term_to_load())
                .set(CONTEST_COLLECTION_CARD.ONLY_FEATURED, contestCollectionCard.getOnly_featured())
                .returning(CONTEST_COLLECTION_CARD.ID_)
                .fetchOne();
        if (ret != null) {
            contestCollectionCard.setId_(ret.getValue(CONTEST_COLLECTION_CARD.ID_));
            return contestCollectionCard;
        } else {
            return null;
        }

    }

    @Override
    public boolean update(ContestCollectionCard contestCollectionCard) {

        return dslContext.update(CONTEST_COLLECTION_CARD)
                .set(CONTEST_COLLECTION_CARD.PARENT, contestCollectionCard.getParent() != null ? contestCollectionCard.getParent() : null)
                .set(CONTEST_COLLECTION_CARD.BIG_ONTOLOGY_TERM, contestCollectionCard.getBig_ontology_term())
                .set(CONTEST_COLLECTION_CARD.SMALL_ONTOLOGY_TERM, contestCollectionCard.getSmall_ontology_term())
                .set(CONTEST_COLLECTION_CARD.DESCRIPTION, contestCollectionCard.getDescription())
                .set(CONTEST_COLLECTION_CARD.VISIBLE, contestCollectionCard.getVisible())
                .set(CONTEST_COLLECTION_CARD.ORDER, contestCollectionCard.getOrder())
                .set(CONTEST_COLLECTION_CARD.ONTOLOGY_TERM_TO_LOAD, contestCollectionCard.getOntology_term_to_load())
                .set(CONTEST_COLLECTION_CARD.ONLY_FEATURED, contestCollectionCard.getOnly_featured())
                .where(CONTEST_COLLECTION_CARD.ID_.eq(contestCollectionCard.getId_()))
                .execute() > 0;
    }


    @Override
    public ContestCollectionCard get(Long contestCollectionCardId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(CONTEST_COLLECTION_CARD)
                .where(CONTEST_COLLECTION_CARD.ID_.eq(contestCollectionCardId)).fetchOne();

        if (record == null) {
            throw new NotFoundException("ContestCollectionCard with id " + contestCollectionCardId + " does not exist");
        }
        return record.into(ContestCollectionCard.class);

    }

    @Override
    public List<ContestCollectionCard> findByGiven(Long parentCollectionCardId) throws NotFoundException {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_COLLECTION_CARD).getQuery();

        if (parentCollectionCardId != null) {
            query.addConditions(CONTEST_COLLECTION_CARD.PARENT.eq(parentCollectionCardId));
        }

        query.addOrderBy(CONTEST_COLLECTION_CARD.ORDER.asc());
        return query.fetchInto(ContestCollectionCard.class);
    }
}
