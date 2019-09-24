package org.xcolab.service.moderation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.comment.ICommentClient;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.IComment;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.moderation.pojo.IReport;
import org.xcolab.client.moderation.pojo.IReportTarget;
import org.xcolab.service.moderation.domain.report.ReportDao;
import org.xcolab.service.moderation.domain.reportTarget.ReportTargetDao;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.moderation.ManagerAction;
import org.xcolab.util.enums.moderation.TargetType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ModerationService {

    private final ReportDao reportDao;
    private final ReportTargetDao reportTargetDao;
    private final ICommentClient commentClient;
    private final IProposalClient proposalClient;

    @Autowired
    public ModerationService(ReportDao reportDao, ReportTargetDao reportTargetDao,
            ICommentClient commentClient, IProposalClient proposalClient) {
        this.reportDao = reportDao;
        this.reportTargetDao = reportTargetDao;
        this.commentClient = commentClient;
        this.proposalClient = proposalClient;
    }

    public IReport createReport(IReport report) {
        report.setCreatedAt(new Timestamp(new Date().getTime()));
        report = reportDao.create(report);
        int totalWeight = reportDao.getTotalWeight(report.getTargetType(), report.getTargetId(),
                report.getTargetAdditionalId());
        IReportTarget reportTarget = reportTargetDao.get(report.getTargetType(), report.getReason());

        if (reportTarget.getScreeningThreshold() != -1
                && totalWeight >= reportTarget.getScreeningThreshold()) {
            if (TargetType.PROPOSAL.name().equals(report.getTargetType())) {
                removeProposal(report.getTargetId());
            } else {
                removeComment(report.getTargetId());
            }
        }
        //TODO COLAB-2604: implement notification
        // else if (totalWeight >= reportTarget.getNotificationThreshold()) {
        return report;
    }

    public boolean handleReport(long reportId, long manageruserId, ManagerAction managerAction) {
        IReport report = reportDao.get(reportId);
        final TargetType targetType = TargetType.valueOf(report.getTargetType());

        List<IReport> equivalentReports = reportDao.findByGiven(PaginationHelper.EVERYTHING, null,
                null, targetType.name(), report.getTargetId(), report.getTargetAdditionalId(),
                ManagerAction.PENDING.name());

        final Timestamp actionDate = new Timestamp(new Date().getTime());

        try {
            switch (managerAction) {
                case APPROVE:
                    if (targetType == TargetType.PROPOSAL) {
                        approveProposal(report.getTargetId());
                    } else {
                        approveComment(report.getTargetId());
                    }
                    break;
                case REVERT:
                    if (targetType == TargetType.PROPOSAL) {
                        revertProposal(report.getTargetId());
                    } else {
                        removeComment(report.getTargetId());
                    }
                    break;
                case REMOVE:
                    if (targetType == TargetType.PROPOSAL) {
                        removeProposal(report.getTargetId());
                    } else {
                        removeComment(report.getTargetId());
                    }
                    break;
                default:
            }
        } catch (CommentNotFoundException | ProposalNotFoundException e) {
            return false;
        }

        for (IReport singleReport : equivalentReports) {
            singleReport.setManagerAction(managerAction.name());
            singleReport.setManagerUserId(manageruserId);
            singleReport.setManagerActionDate(actionDate);
            reportDao.update(singleReport);
        }

        return true;
    }

    private void approveProposal(long proposalId) throws ProposalNotFoundException {
        final ProposalWrapper proposal = proposalClient.getProposal(proposalId, true);
        if (!proposal.isVisible()) {
            proposal.setVisible(true);
            proposalClient.updateProposal(proposal);
        }
    }

    private void revertProposal(long proposalId) {
        //TODO COLAB-2605: implement
        removeProposal(proposalId);
    }

    private void removeProposal(long proposalId) {
        proposalClient.deleteProposal(proposalId);
    }

    private void approveComment(long commentId) throws CommentNotFoundException {
        final IComment comment = commentClient.getComment(commentId, true);
        if (comment.getDeletedAt() != null) {
            comment.setDeletedAt(null);
            commentClient.updateComment(comment);
        }
    }

    private void removeComment(long commentId) {
        commentClient.deleteComment(commentId);
    }
}
