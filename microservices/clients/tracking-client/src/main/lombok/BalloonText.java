package org.xcolab.client.tracking.pojo.tables.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.xcolab.client.tracking.pojo.IBalloonText;

@Data
@ToString(includeFieldNames = true)
@NoArgsConstructor
@AllArgsConstructor
public class BalloonText implements IBalloonText {

    private static final long serialVersionUID = -1825617241;

    private Long id;
    private String name;
    private String textBeforeForm;
    private String textBeforeShareButtons;
    private String emailTemplate;
    private String emailSubjectTemplate;
    private String shareTitle;
    private String shareDescription;
    private Boolean enabled;
}
