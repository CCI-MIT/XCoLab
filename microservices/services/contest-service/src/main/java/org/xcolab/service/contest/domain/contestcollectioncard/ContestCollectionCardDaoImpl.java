package org.xcolab.service.contest.domain.contestcollectioncard;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IContestCollectionCard;
import org.xcolab.client.contest.pojo.tables.pojos.ContestCollectionCard;
import org.xcolab.model.tables.records.ContestCollectionCardRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST_COLLECTION_CARD;

@Repository
public class ContestCollectionCardDaoImpl implements ContestCollectionCardDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public IContestCollectionCard create(IContestCollectionCard contestCollectionCard) {
        ContestCollectionCardRecord ret = this.dslContext.insertInto(CONTEST_COLLECTION_CARD)
                .set(CONTEST_COLLECTION_CARD.PARENT, contestCollectionCard.getParent())
                .set(CONTEST_COLLECTION_CARD.BIG_ONTOLOGY_TERM,
                        contestCollectionCard.getBigOntologyTerm())
                .set(CONTEST_COLLECTION_CARD.SMALL_ONTOLOGY_TERM,
                        contestCollectionCard.getSmallOntologyTerm())
                .set(CONTEST_COLLECTION_CARD.DESCRIPTION, contestCollectionCard.getDescription())
                .set(CONTEST_COLLECTION_CARD.SHORT_NAME, contestCollectionCard.getShortName())
                .set(CONTEST_COLLECTION_CARD.VISIBLE, contestCollectionCard.isVisible())
                .set(CONTEST_COLLECTION_CARD.SORT_ORDER, contestCollectionCard.getSortOrder())
                .set(CONTEST_COLLECTION_CARD.ONTOLOGY_TERM_TO_LOAD,
                        contestCollectionCard.getOntologyTermToLoad())
                .set(CONTEST_COLLECTION_CARD.ONLY_FEATURED, contestCollectionCard.isOnlyFeatured())
                .returning(CONTEST_COLLECTION_CARD.ID)
                .fetchOne();

        if (ret != null) {
            contestCollectionCard.setId(ret.getValue(CONTEST_COLLECTION_CARD.ID));
            return contestCollectionCard;
        } else {
            return null;
        }
    }

    @Override
    public boolean update(IContestCollectionCard contestCollectionCard) {

        return dslContext.update(CONTEST_COLLECTION_CARD)
                .set(CONTEST_COLLECTION_CARD.PARENT, contestCollectionCard.getParent())
                .set(CONTEST_COLLECTION_CARD.BIG_ONTOLOGY_TERM,
                        contestCollectionCard.getBigOntologyTerm())
                .set(CONTEST_COLLECTION_CARD.SMALL_ONTOLOGY_TERM,
                        contestCollectionCard.getSmallOntologyTerm())
                .set(CONTEST_COLLECTION_CARD.DESCRIPTION, contestCollectionCard.getDescription())
                .set(CONTEST_COLLECTION_CARD.SHORT_NAME, contestCollectionCard.getShortName())
                .set(CONTEST_COLLECTION_CARD.VISIBLE, contestCollectionCard.isVisible())
                .set(CONTEST_COLLECTION_CARD.SORT_ORDER, contestCollectionCard.getSortOrder())
                .set(CONTEST_COLLECTION_CARD.ONTOLOGY_TERM_TO_LOAD,
                        contestCollectionCard.getOntologyTermToLoad())
                .set(CONTEST_COLLECTION_CARD.ONLY_FEATURED, contestCollectionCard.isOnlyFeatured())
                .where(CONTEST_COLLECTION_CARD.ID.eq(contestCollectionCard.getId()))
                .execute() > 0;
    }

    @Override
    public boolean delete(Long contestCollectionCardId) throws NotFoundException {
        return dslContext.deleteFrom(CONTEST_COLLECTION_CARD)
                .where(CONTEST_COLLECTION_CARD.ID.eq(contestCollectionCardId))
                .execute() > 0;
    }

    @Override
    public IContestCollectionCard get(Long contestCollectionCardId) throws NotFoundException {
        final Record record = this.dslContext.selectFrom(CONTEST_COLLECTION_CARD)
                .where(CONTEST_COLLECTION_CARD.ID.eq(contestCollectionCardId)).fetchOne();

        if (record == null) {
            throw new NotFoundException(
                    "ContestCollectionCard with id " + contestCollectionCardId + " does not exist");
        }
        return record.into(ContestCollectionCard.class);
    }

    @Override
    public List<IContestCollectionCard> findByGiven(Long parentCollectionCardId)
            throws NotFoundException {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_COLLECTION_CARD).getQuery();

        if (parentCollectionCardId != null) {
            query.addConditions(CONTEST_COLLECTION_CARD.PARENT.eq(parentCollectionCardId));
        }

        query.addOrderBy(CONTEST_COLLECTION_CARD.SORT_ORDER.asc());
        return query.fetchInto(ContestCollectionCard.class);
    }
}
