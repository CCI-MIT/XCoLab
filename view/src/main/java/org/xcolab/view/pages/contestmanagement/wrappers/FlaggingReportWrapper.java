package org.xcolab.view.pages.contestmanagement.wrappers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.flagging.IFlaggingClient;
import org.xcolab.client.flagging.pojo.AggregatedReport;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.enums.flagging.ManagerAction;
import org.xcolab.util.enums.flagging.TargetType;

import java.util.Date;

public class FlaggingReportWrapper {

    private static final Logger _log = LoggerFactory.getLogger(FlaggingReportWrapper.class);

    private final AggregatedReport report;
    private ManagerAction managerAction = ManagerAction.PENDING;

    private static IFlaggingClient flaggingClient;

    public static void setFlaggingClient(IFlaggingClient flaggingClient) {
        FlaggingReportWrapper.flaggingClient = flaggingClient;
    }
    public FlaggingReportWrapper() {
        report = new AggregatedReport();
    }

    public FlaggingReportWrapper(AggregatedReport report) {
        this.report = report;
    }

    public String getTargetName() {
        switch (TargetType.valueOf(report.getTargetType())) {
            case PROPOSAL:
                final Proposal proposal = getTargetProposal();
                return proposal != null ? proposal.getName() : "unknown proposal";
            case COMMENT:
                final Comment commentTarget = getTargetComment();
                if (commentTarget != null) {
                    final CommentThread thread = commentTarget.getThread();
                    return "Comment on " + thread.getTitle();
                } else {
                    return "unknown comment";
                }
            default:
                return "unknown target";
        }
    }

    private Proposal getTargetProposal() {
        try {
            return ProposalClientUtil.getProposal(report.getTargetId());
        } catch (ProposalNotFoundException e) {
            return null;
        }
    }

    private Comment getTargetComment() {
        try {
            return CommentClient.instance().getComment(report.getTargetId(), true);
        } catch (CommentNotFoundException e) {
            return null;
        }
    }

    public String getTargetLink() {
        switch (TargetType.valueOf(report.getTargetType())) {
            case PROPOSAL:
                final Proposal proposal = getTargetProposal();
                if (proposal != null) {
                    return (proposal).getProposalUrl();
                }
                break;
            case COMMENT:
                final Comment commentTarget = getTargetComment();
                if (commentTarget != null) {
                    return commentTarget.getLinkUrl();
                }
                break;
            default:
        }
        return "";
    }

    public void approveContent(long userId) {
        flaggingClient.handleReport(userId, ManagerAction.APPROVE, report.getFirstReportId());
    }

    public void removeContent(long userId) {
        flaggingClient.handleReport(userId, ManagerAction.REMOVE, report.getFirstReportId());
    }

    public long getFirstReportId() {
        return report.getFirstReportId();
    }

    public void setFirstReportId(long firstReportId) {
        report.setFirstReportId(firstReportId);
    }

    public Long getReporteruserId() {
        return report.getReporteruserId();
    }

    public void setReporteruserId(Long reporteruserId) {
        report.setReporteruserId(reporteruserId);
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
