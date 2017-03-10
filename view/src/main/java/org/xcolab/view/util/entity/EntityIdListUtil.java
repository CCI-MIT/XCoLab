package org.xcolab.view.util.entity;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.IdListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for common conversions of ids lists to strings or models.
 */
public final class EntityIdListUtil {

    private EntityIdListUtil() { }

    /**
     * Utility class to convert between lists of Contests, their ids, and comma separated id strings
     */
    public final static IdListObjectConverter<Contest> CONTESTS = new IdListObjectConverter<Contest>() {
        @Override
        public Contest getObject(long id) {
            try {
                return ContestClientUtil.getContest(id);
            } catch (ContestNotFoundException ignored) {
                return null;
            }
        }

        @Override
        public long getId(Contest object) {
            return object.getContestPK();
        }
    };

    /**
     * Utility class to convert between lists of Proposals, their ids, and comma separated id strings
     */
    public final static IdListObjectConverter<Proposal> PROPOSALS = new IdListObjectConverter<Proposal>() {
        @Override
        public Proposal getObject(long id) {
            try {
                return ProposalClientUtil.getProposal(id);
            } catch (ProposalNotFoundException ignored) {
                return null;
            }
        }

        @Override
        public long getId(Proposal object) {
            return object.getProposalId();
        }
    };

    /**
     * Utility class to convert between lists of ContestTypes, their ids, and comma separated id strings
     */
    public final static IdListObjectConverter<ContestType> CONTEST_TYPES = new IdListObjectConverter<ContestType>() {
        @Override
        public ContestType getObject(long id)  {
            return ContestClientUtil.getContestType(id);
        }

        @Override
        public long getId(ContestType object) {
            return object.getId_();
        }
    };

    public static abstract class IdListObjectConverter<T> {

        public final List<T> fromIdList(List<Long> idList) {
            List<T> objects = new ArrayList<>(idList.size());
            for (long id : idList) {
                    objects.add(getObject(id));
            }
            return objects;
        }

        public final List<Long> toIdList(List<T> objectList) {
            List<Long> ids = new ArrayList<>(objectList.size());
            for (T object : objectList) {
                ids.add(getId(object));
            }
            return ids;
        }

        public final String toIdString(List<T> objects) {
            return IdListUtil.getStringFromIds(toIdList(objects));
        }

        public final List<T> fromIdString(String idString) {
            return fromIdList(IdListUtil.getIdsFromString(idString));
        }

        /**
         * Retrieves a single object of type T from an id.
         * @param id the id of the object to be fetched
         * @return an object of type T
         */
        public abstract T getObject(long id) ;

        /**
         * Gets the id of a single object of type T
         * @param object the object
         * @return the object's id
         */
        public abstract long getId(T object);
    }
}