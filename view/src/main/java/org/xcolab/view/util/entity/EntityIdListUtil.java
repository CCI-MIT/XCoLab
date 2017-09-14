package org.xcolab.view.util.entity;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.IdListUtil;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class for common conversions of ids lists to strings or models.
 */
public final class EntityIdListUtil {

    private EntityIdListUtil() { }

    /**
     * Utility class to convert between lists of Contests, their ids, and comma separated id strings
     */
    public final static IdListObjectConverter<Contest> CONTESTS =
            new IdListObjectConverter<>(ContestClientUtil::getContest, Contest::getContestPK);

    /**
     * Utility class to convert between lists of Proposals, their ids, and comma separated id strings
     */
    public final static IdListObjectConverter<Proposal> PROPOSALS =
            new IdListObjectConverter<>(ProposalClientUtil::getProposal, Proposal::getProposalId);

    /**
     * Utility class to convert between lists of ContestTypes, their ids, and comma separated id strings
     */
    public final static IdListObjectConverter<ContestType> CONTEST_TYPES =
            new IdListObjectConverter<>(ContestTypeClient::getContestType, ContestType::getId);


    public static class IdListObjectConverter<T> {

        private final Function<Long, T> toObjectFunction;
        private final Function<T, Long> toIdFunction;

        protected IdListObjectConverter(Function<Long, T> toObjectFunction,
                Function<T, Long> toIdFunction) {
            this.toObjectFunction = toObjectFunction;

            this.toIdFunction = toIdFunction;
        }

        public final List<T> fromIdList(List<Long> idList) {
            return idList.stream()
                    .map(toObjectFunction)
                    .collect(Collectors.toList());
        }

        public final List<Long> toIdList(List<T> objectList) {
            return objectList.stream()
                    .map(toIdFunction)
                    .collect(Collectors.toList());
        }

        public final String toIdString(List<T> objects) {
            return IdListUtil.getStringFromIds(toIdList(objects));
        }

        public final List<T> fromIdString(String idString) {
            return fromIdList(IdListUtil.getIdsFromString(idString));
        }
    }
}
