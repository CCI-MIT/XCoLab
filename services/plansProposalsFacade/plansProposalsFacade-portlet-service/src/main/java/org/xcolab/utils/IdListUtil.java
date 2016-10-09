package org.xcolab.utils;


import org.apache.commons.lang.StringUtils;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for common conversions of ids lists to strings or models.
 */
public final class IdListUtil {
    private final static Log _log = LogFactoryUtil.getLog(IdListUtil.class);

    private IdListUtil() { }

    /**
     * Utility class to convert between lists of Contests, their ids, and comma separated id strings
     */
    public final static IdListObjectConverter<Contest> CONTESTS = new IdListObjectConverter<Contest>() {
        @Override
        public Contest getObject(long id) {
            try {
                return ContestClientUtil.getContest(id);
            }catch (ContestNotFoundException ignored){
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
            try{
                return ProposalsClient.getProposal(id);
            }catch (ProposalNotFoundException ignored){
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
        public ContestType getObject(long id) throws SystemException {
            return ContestClientUtil.getContestType(id);
        }

        @Override
        public long getId(ContestType object) {
            return object.getId_();
        }
    };

    /**
     * Converts a string representation of id lists into an actual list
     * The list should only consists of numbers, commas, and (optionally) any amount of spaces.
     */
    public static List<Long> getIdsFromString(String commaSeparated) {
        if (StringUtils.isEmpty(commaSeparated)) {
            return Collections.emptyList();
        }
        String[] stringIds = commaSeparated.trim().split("\\s*,\\s*");
        List<Long> longsIds = new ArrayList<>(stringIds.length);
        for (String stringId : stringIds) {
            try {
                longsIds.add(Long.parseLong(stringId));
            } catch (NumberFormatException e) {
                _log.error(String.format("Could not parse id %s in id list %s", stringId, Arrays.asList(stringIds)), e);
            }
        }
        return longsIds;
    }

    /**
     * Converts a list of ids into a comma separated string
     */
    public static String getStringFromIds(List<Long> ids) {
        return StringUtils.join(ids, ',');
    }

    public static abstract class IdListObjectConverter<T> {

        public final List<T> fromIdList(List<Long> idList) {
            List<T> objects = new ArrayList<>(idList.size());
            for (long id : idList) {
                try {
                    objects.add(getObject(id));
                } catch (SystemException e) {
                    _log.error(String.format("Error converting id list to object list: object with id %d does not exist",
                            id), e);
                }
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
            return getStringFromIds(toIdList(objects));
        }

        public final List<T> fromIdString(String idString) {
            return fromIdList(getIdsFromString(idString));
        }

        /**
         * Retrieves a single object of type T from an id.
         * @param id the id of the object to be fetched
         * @return an object of type T
         * @throws SystemException when the object is not found
         */
        public abstract T getObject(long id) throws SystemException;

        /**
         * Gets the id of a single object of type T
         * @param object the object
         * @return the object's id
         */
        public abstract long getId(T object);
    }
}