package org.xcolab.portlets.contestmanagement.wrappers;


import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.flagging.FlaggingClient;
import org.xcolab.client.flagging.pojo.AggregatedReport;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.enums.flagging.ManagerAction;
import org.xcolab.util.enums.flagging.TargetType;
import org.xcolab.wrappers.BaseProposalWrapper;

import java.util.Date;

public class FlaggingReportWrapper {
    private final static Log _log = LogFactoryUtil.getLog(FlaggingReportWrapper.class);

    private final AggregatedReport report;
    private ManagerAction managerAction = ManagerAction.PENDING;

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
                return new BaseProposalWrapper(proposal).getName();
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

    public String getTargetLink() {
        switch (TargetType.valueOf(report.getTargetType())) {
            case PROPOSAL:
                final Proposal proposal = getTargetProposal();
                if (proposal != null) {
                    return new BaseProposalWrapper(proposal).getProposalUrl();
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

    private Proposal getTargetProposal() {
        try {
            return ProposalClientUtil.getProposal(report.getTargetId());
        } catch (ProposalNotFoundException  e) {
            return null;
        }
    }

    private Comment getTargetComment() {
        try {
            return CommentClientUtil.getComment(report.getTargetId(), true);
        } catch (CommentNotFoundException e) {
            return null;
        }
    }

    public void approveContent(long memberId) {
        FlaggingClient.handleReport(memberId, ManagerAction.APPROVE, report.getFirstReportId());
    }

    public void removeContent(long memberId) {
        FlaggingClient.handleReport(memberId, ManagerAction.REMOVE, report.getFirstReportId());
    }

    public long getFirstReportId() {
        return report.getFirstReportId();
    }

    public void setFirstReportId(long firstReportId) {
        report.setFirstReportId(firstReportId);
    }

    public Long getReporterMemberId() {
        return report.getReporterMemberId();
    }

    public void setReporterMemberId(Long reporterMemberId) {
        report.setReporterMemberId(reporterMemberId);
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
