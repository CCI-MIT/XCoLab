package org.xcolab.commons.recaptcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RecaptchaResponse {
    private boolean success;

    @JsonProperty("challenge_ts")
    private Date challengeTimestamp;

    private String hostname;

    @JsonProperty("error-codes")
    private  List<String> errorCodes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Date getChallengeTimestamp() {
        return challengeTimestamp;
    }

    public void setChallengeTimestamp(Date challengeTimestamp) {
        this.challengeTimestamp = challengeTimestamp;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public List<String> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecaptchaResponse)) {
            return false;
        }
        RecaptchaResponse that = (RecaptchaResponse) o;
        return isSuccess() == that.isSuccess()
                && Objects.equals(getChallengeTimestamp(), that.getChallengeTimestamp())
                && Objects.equals(getHostname(), that.getHostname())
                && Objects.equals(getErrorCodes(), that.getErrorCodes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSuccess(), getChallengeTimestamp(), getHostname(), getErrorCodes());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("success", success)
                .append("challenge_ts", challengeTimestamp)
                .append("hostname", hostname)
                .append("errorCodes", errorCodes).toString();
    }
}
