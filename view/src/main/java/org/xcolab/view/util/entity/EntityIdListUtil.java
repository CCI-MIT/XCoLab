package org.xcolab.view.util.entity;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.IdListUtil;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class for common conversions of ids lists to strings or models.
 */
public final class EntityIdListUtil {

    private EntityIdListUtil() { }

    /**
     * Utility class to convert between lists of Contests, their ids, and comma separated id
     * strings
     */
    public final static IdListObjectConverter<ContestWrapper> CONTESTS =
            new IdListObjectConverter<>(StaticContestContext.getContestClient()::getContest,
                    ContestWrapper::getId);

    /**
     * Utility class to convert between lists of Proposals, their ids, and comma separated id
     * strings
     */
    public final static IdListObjectConverter<ProposalWrapper> PROPOSALS =
            new IdListObjectConverter<>(StaticProposalContext.getProposalClient()::getProposal,
                    ProposalWrapper::getId);

    /**
     * Utility class to convert between lists of ContestTypes, their ids, and comma separated id
     * strings
     */
    public final static IdListObjectConverter<ContestType> CONTEST_TYPES =
            new IdListObjectConverter<>(StaticAdminContext.getContestTypeClient()::getContestType, ContestType::getId);

    public final static IdListObjectConverter<UserWrapper> MEMBERS =
            new IdListObjectConverter<>(StaticUserContext.getUserClient()::getMemberUnchecked, UserWrapper::getId);


    public static class IdListObjectConverter<T> {

        private final Function<Long, T> toObjectFunction;
        private final Function<T, Long> toIdFunction;

        protected IdListObjectConverter(Function<Long, T> toObjectFunction,
                Function<T, Long> toIdFunction) {
            this.toObjectFunction = toObjectFunction;

            this.toIdFunction = toIdFunction;
        }

        public final List<T> fromIdList(List<Long> idList) {
            return idList.stream().map(toObjectFunction).collect(Collectors.toList());
        }

        public final List<Long> toIdList(List<T> objectList) {
            return objectList.stream().map(toIdFunction).collect(Collectors.toList());
        }

        public final String toIdString(List<T> objects) {
            return IdListUtil.getStringFromIds(toIdList(objects));
        }

        public final List<T> fromIdString(String idString) {
            return fromIdList(IdListUtil.getIdsFromString(idString));
        }
    }
}
