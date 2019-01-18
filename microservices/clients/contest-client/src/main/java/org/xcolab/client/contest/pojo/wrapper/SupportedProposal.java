package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

// TODO COLAB-2356: rethink inheritance structure
// the inheritance structure between this SupportedProposal(Dto) and Proposal(Dto) is limited
// by single inheritance as they both (should) inherit from abstract classes

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SupportedProposal extends ProposalWrapper implements Serializable {

    public static final TypeProvider<SupportedProposal> TYPES =
            new TypeProvider<>(SupportedProposal.class,
                    new ParameterizedTypeReference<List<SupportedProposal>>() {});

    private Timestamp supportDate;
    private Long supporterUserId;

    public SupportedProposal() {
    }

    public SupportedProposal(SupportedProposal value) {
        super(value);
        this.supportDate = value.getSupportDate();
        this.supporterUserId = value.getSupporterUserId();
    }

    public Long getSupporterUserId() {
        return supporterUserId;
    }

    public void setSupporterUserId(Long supporterUserId) {
        this.supporterUserId = supporterUserId;
    }

    public Timestamp getSupportDate() {
        return supportDate;
    }

    public void setSupportDate(Timestamp supportDate) {
        this.supportDate = supportDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("proposalId", getId())
                .append("supportDate", supportDate)
                .append("supporterUserId", supporterUserId).toString();
    }
}
