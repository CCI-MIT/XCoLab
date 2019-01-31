package org.xcolab.client.user.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PlatformTeamWrapper  extends org.xcolab.client.user.pojo.tables.pojos.PlatformTeam {


    public PlatformTeamWrapper() {}

    public PlatformTeamWrapper(PlatformTeamWrapper value) {
        this.setId(value.getId());
        this.setName(value.getName());
    }

    public PlatformTeamWrapper(
            Long   id,
            String name
    ) {
        this.setId(id);
        this.setName(name);
    }

}

