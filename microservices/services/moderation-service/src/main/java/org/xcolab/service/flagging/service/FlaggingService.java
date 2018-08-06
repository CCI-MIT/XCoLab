package org.xcolab.service.flagging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.model.tables.pojos.Report;
import org.xcolab.model.tables.pojos.ReportTarget;
import org.xcolab.service.flagging.domain.report.ReportDao;
import org.xcolab.service.flagging.domain.reportTarget.ReportTargetDao;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.flagging.ManagerAction;
import org.xcolab.util.enums.flagging.TargetType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class FlaggingService {

    private final ReportDao reportDao;
    private final ReportTargetDao reportTargetDao;

    @Autowired
    public FlaggingService(ReportDao reportDao, ReportTargetDao reportTargetDao) {
        this.reportDao = reportDao;
        this.reportTargetDao = reportTargetDao;
    }

    public Report createReport(Report report) {
        report.setCreatedAt(new Timestamp(new Date().getTime()));
        report = reportDao.create(report);
        int totalWeight = reportDao.getTotalWeight(report.getTargetType(), report.getTargetId(),
                report.getTargetAdditionalId());
        ReportTarget reportTarget = reportTargetDao.get(report.getTargetType(), report.getReason());

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
        Report report = reportDao.get(reportId);
        final TargetType targetType = TargetType.valueOf(report.getTargetType());

        List<Report> equivalentReports = reportDao.findByGiven(PaginationHelper.EVERYTHING, null,
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

        for (Report singleReport : equivalentReports) {
            singleReport.setManagerAction(managerAction.name());
            singleReport.setManagerUserId(manageruserId);
            singleReport.setManagerActionDate(actionDate);
            reportDao.update(singleReport);
        }

        return true;
    }

    private void approveProposal(long proposalId) throws ProposalNotFoundException {
        final Proposal proposal = ProposalClientUtil.getProposal(proposalId, true);
        if (!proposal.getVisible()) {
            proposal.setVisible(true);
            ProposalClientUtil.updateProposal(proposal);
        }
    }

    private void revertProposal(long proposalId) {
        //TODO COLAB-2605: implement
        removeProposal(proposalId);
    }

    private void removeProposal(long proposalId) {
        ProposalClientUtil.deleteProposal(proposalId);
    }

    private void approveComment(long commentId) throws CommentNotFoundException {
        final Comment comment = CommentClientUtil.getComment(commentId, true);
        if (comment.getDeletedAt() != null) {
            comment.setDeletedAt(null);
            CommentClientUtil.updateComment(comment);
        }
    }

    private void removeComment(long commentId) {
        CommentClientUtil.deleteComment(commentId);
    }
}
