package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;
import java.util.Map;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class LastMessageId implements Serializable {

    public static final TypeProvider<LastMessageId> TYPES =
            new TypeProvider<>(LastMessageId.class, new ParameterizedTypeReference<List<LastMessageId>>() {});

    private Long lastMessageId;

    public LastMessageId(){
    }

    @JsonCreator
    private LastMessageId(Map<String,Object> properties) {
        try {
            this.lastMessageId = (Long) properties.get("id");
        } catch (ClassCastException e) {
            this.lastMessageId = ((Integer) properties.get("id")).longValue();
        }
    }

    public LastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }


}
