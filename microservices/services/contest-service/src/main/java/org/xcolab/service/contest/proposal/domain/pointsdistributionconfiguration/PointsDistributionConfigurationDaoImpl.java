package org.xcolab.service.contest.proposal.domain.pointsdistributionconfiguration;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;
import org.xcolab.client.contest.pojo.tables.pojos.PointsDistributionConfiguration;
import org.xcolab.model.tables.records.PointsDistributionConfigurationRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.POINTS_DISTRIBUTION_CONFIGURATION;

@Repository
public class PointsDistributionConfigurationDaoImpl implements PointsDistributionConfigurationDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public IPointsDistributionConfiguration create(
            IPointsDistributionConfiguration pointsDistributionConfiguration) {

        PointsDistributionConfigurationRecord ret = this.dslContext.insertInto(POINTS_DISTRIBUTION_CONFIGURATION)
                .set(POINTS_DISTRIBUTION_CONFIGURATION.ID, pointsDistributionConfiguration.getId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.PROPOSAL_ID, pointsDistributionConfiguration.getProposalId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.POINT_TYPE_ID, pointsDistributionConfiguration.getPointTypeId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.TARGET_USER_ID, pointsDistributionConfiguration.getTargetUserId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.TARGET_SUB_PROPOSAL_ID, pointsDistributionConfiguration.getTargetSubProposalId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.TARGET_PROPOSAL_TEMPLATE_SECTION_DEFINITION_ID,
                        pointsDistributionConfiguration.getTargetProposalTemplateSectionDefinitionId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.PERCENTAGE, pointsDistributionConfiguration.getPercentage())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.AUTHOR_USER_ID, pointsDistributionConfiguration.getAuthorUserId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.CREATED_AT, pointsDistributionConfiguration.getCreatedAt())
                .returning(POINTS_DISTRIBUTION_CONFIGURATION.ID)
                .fetchOne();
        if (ret != null) {
            pointsDistributionConfiguration.setId(ret.getValue(POINTS_DISTRIBUTION_CONFIGURATION.ID));
            return pointsDistributionConfiguration;
        } else {
            return null;
        }

    }

    @Override
    public boolean update(IPointsDistributionConfiguration pointsDistributionConfiguration) {
        return dslContext.update(POINTS_DISTRIBUTION_CONFIGURATION)
                .set(POINTS_DISTRIBUTION_CONFIGURATION.ID, pointsDistributionConfiguration.getId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.PROPOSAL_ID, pointsDistributionConfiguration.getProposalId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.POINT_TYPE_ID, pointsDistributionConfiguration.getPointTypeId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.TARGET_USER_ID, pointsDistributionConfiguration.getTargetUserId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.TARGET_SUB_PROPOSAL_ID, pointsDistributionConfiguration.getTargetSubProposalId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.TARGET_PROPOSAL_TEMPLATE_SECTION_DEFINITION_ID,
                        pointsDistributionConfiguration.getTargetProposalTemplateSectionDefinitionId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.PERCENTAGE, pointsDistributionConfiguration.getPercentage())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.AUTHOR_USER_ID, pointsDistributionConfiguration.getAuthorUserId())
                .set(POINTS_DISTRIBUTION_CONFIGURATION.CREATED_AT, pointsDistributionConfiguration.getCreatedAt())
                .where(POINTS_DISTRIBUTION_CONFIGURATION.ID.eq(pointsDistributionConfiguration.getId()))
                .execute() > 0;
    }

    @Override
    public List<IPointsDistributionConfiguration> findByGiven(Long proposalId, Long pointTypeId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(POINTS_DISTRIBUTION_CONFIGURATION).getQuery();

        if (proposalId != null) {
            query.addConditions(POINTS_DISTRIBUTION_CONFIGURATION.PROPOSAL_ID.eq(proposalId));
        }
        if (pointTypeId != null) {
            query.addConditions(POINTS_DISTRIBUTION_CONFIGURATION.POINT_TYPE_ID.eq(pointTypeId));
        }
        return query.fetchInto(PointsDistributionConfiguration.class);
    }

    @Override
    public int deleteByProposalId(Long proposalId) {
        return dslContext.deleteFrom(POINTS_DISTRIBUTION_CONFIGURATION)
                .where(POINTS_DISTRIBUTION_CONFIGURATION.PROPOSAL_ID.eq(proposalId))
                .execute();
    }
    @Override
    public int delete(Long id) {
        return dslContext.deleteFrom(POINTS_DISTRIBUTION_CONFIGURATION)
                .where(POINTS_DISTRIBUTION_CONFIGURATION.ID.eq(id))
                .execute();
    }

    @Override
    public IPointsDistributionConfiguration get(Long id) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(POINTS_DISTRIBUTION_CONFIGURATION)
                .where(POINTS_DISTRIBUTION_CONFIGURATION.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("PointsDistributionConfiguration with id " + id + " does not exist");
        }
        return record.into(PointsDistributionConfiguration.class);

    }

    @Override
    public IPointsDistributionConfiguration getByProposalTemplateSectionDefinitionId(Long targetProposalTemplateSectionDefinitionId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(POINTS_DISTRIBUTION_CONFIGURATION)
                .where(POINTS_DISTRIBUTION_CONFIGURATION.TARGET_PROPOSAL_TEMPLATE_SECTION_DEFINITION_ID
                        .eq(targetProposalTemplateSectionDefinitionId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("PointsDistributionConfiguration with targetProposalTemplateSectionDefinitionId " + targetProposalTemplateSectionDefinitionId + " does not exist");
        }
        return record.into(PointsDistributionConfiguration.class);

    }
}
