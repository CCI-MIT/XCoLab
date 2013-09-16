package com.ext.portlet.model.impl;

import com.ext.portlet.model.Proposal;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Proposal in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Proposal
 * @generated
 */
public class ProposalCacheModel implements CacheModel<Proposal>, Serializable {
    public long proposalId;
    public long createDate;
    public int currentVersion;
    public long authorId;
    public boolean visible;
    public long discussionId;
    public long judgeDiscussionId;
    public long fellowDiscussionId;
    public long advisorDiscussionId;
    public long groupId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{proposalId=");
        sb.append(proposalId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", currentVersion=");
        sb.append(currentVersion);
        sb.append(", authorId=");
        sb.append(authorId);
        sb.append(", visible=");
        sb.append(visible);
        sb.append(", discussionId=");
        sb.append(discussionId);
        sb.append(", judgeDiscussionId=");
        sb.append(judgeDiscussionId);
        sb.append(", fellowDiscussionId=");
        sb.append(fellowDiscussionId);
        sb.append(", advisorDiscussionId=");
        sb.append(advisorDiscussionId);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append("}");

        return sb.toString();
    }

    public Proposal toEntityModel() {
        ProposalImpl proposalImpl = new ProposalImpl();

        proposalImpl.setProposalId(proposalId);

        if (createDate == Long.MIN_VALUE) {
            proposalImpl.setCreateDate(null);
        } else {
            proposalImpl.setCreateDate(new Date(createDate));
        }

        proposalImpl.setCurrentVersion(currentVersion);
        proposalImpl.setAuthorId(authorId);
        proposalImpl.setVisible(visible);
        proposalImpl.setDiscussionId(discussionId);
        proposalImpl.setJudgeDiscussionId(judgeDiscussionId);
        proposalImpl.setFellowDiscussionId(fellowDiscussionId);
        proposalImpl.setAdvisorDiscussionId(advisorDiscussionId);
        proposalImpl.setGroupId(groupId);

        proposalImpl.resetOriginalValues();

        return proposalImpl;
    }
}
