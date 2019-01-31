package org.xcolab.view.pages.contestmanagement.wrappers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.IComment;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.moderation.StaticModerationContext;
import org.xcolab.client.moderation.pojo.IAggregatedReport;
import org.xcolab.client.moderation.pojo.tables.pojos.AggregatedReport;
import org.xcolab.util.enums.moderation.ManagerAction;
import org.xcolab.util.enums.moderation.TargetType;

import java.util.Date;

public class ModerationReportWrapper {

    private static final Logger _log = LoggerFactory.getLogger(ModerationReportWrapper.class);

    private final IAggregatedReport report;

    private ManagerAction managerAction = ManagerAction.PENDING;

    public ModerationReportWrapper() {
        report = new AggregatedReport();
    }

    public ModerationReportWrapper(IAggregatedReport report) {
        this.report = report;
    }

    public String getTargetName() {
        switch (TargetType.valueOf(report.getTargetType())) {
            case PROPOSAL:
                final ProposalWrapper proposal = getTargetProposal();
                return proposal != null ? proposal.getName() : "unknown proposal";
            case COMMENT:
                final IComment commentTarget = getTargetComment();
                if (commentTarget != null) {
                    final IThread thread;
                    try {
                        thread = StaticCommentContext.getThreadClient()
                                .getThread(commentTarget.getThreadId());
                    } catch (ThreadNotFoundException e) {
                        return "unknown thread";
                    }
                    return "Comment on " + thread.getTitle();
                } else {
                    return "unknown comment";
                }
            default:
                return "unknown target";
        }
    }

    private ProposalWrapper getTargetProposal() {
        try {
            return StaticProposalContext.getProposalClient().getProposal(report.getTargetId());
        } catch (ProposalNotFoundException e) {
            return null;
        }
    }

    private IComment getTargetComment() {
        try {
            return StaticCommentContext.getCommentClient().getComment(report.getTargetId(), true);
        } catch (CommentNotFoundException e) {
            return null;
        }
    }

    public String getTargetLink() {
        switch (TargetType.valueOf(report.getTargetType())) {
            case PROPOSAL:
                final ProposalWrapper proposal = getTargetProposal();
                if (proposal != null) {
                    return (proposal).getProposalUrl();
                }
                break;
            case COMMENT:
                final IComment commentTarget = getTargetComment();
                if (commentTarget != null) {
                    return commentTarget.getLinkUrl();
                }
                break;
            default:
        }
        return "";
    }

    public void approveContent(long userId) {
        StaticModerationContext.getModerationClient()
                .handleReport(userId, ManagerAction.APPROVE, report.getFirstReportId());
    }

    public void removeContent(long userId) {
        StaticModerationContext.getModerationClient()
                .handleReport(userId, ManagerAction.REMOVE, report.getFirstReportId());
    }

    public long getFirstReportId() {
        return report.getFirstReportId();
    }

    public void setFirstReportId(long firstReportId) {
        report.setFirstReportId(firstReportId);
    }

    public Long getReporteruserId() {
        return report.getReporterUserId();
    }

    public void setReporteruserId(Long reporteruserId) {
        report.setReporterUserId(reporteruserId);
    }

    public String getTargetType() {
        return report.getTargetType();
    }

    public void setTargetType(String targetType) {
        report.setTargetType(targetType);
    }

    public Long getTargetId() {
        return report.getTargetId();
    }

    public void setTargetId(Long targetId) {
        report.setTargetId(targetId);
    }

    public String getReason() {
        return report.getReason();
    }

    public void setReason(String reason) {
        report.setReason(reason);
    }

    public String getComment() {
        return report.getComment();
    }

    public void setComment(String comment) {
        report.setComment(comment);
    }

    public Integer getAggregatedWeight() {
        return report.getAggregatedWeight();
    }

    public Integer getCount() {
        return report.getCount();
    }

    public Date getFirstReportDate() {
        return new Date(report.getFirstReportDate().getTime());
    }

    public Date getLastReportDate() {
        return new Date(report.getLastReportDate().getTime());
    }

    public Long getTargetAdditionalId() {
        return report.getTargetAdditionalId();
    }

    public void setTargetAdditionalId(Long targetAdditionalId) {
        report.setTargetAdditionalId(targetAdditionalId);
    }

    public ManagerAction getManagerAction() {
        return managerAction;
    }

    public void setManagerAction(ManagerAction managerAction) {
        this.managerAction = managerAction;
    }
}
