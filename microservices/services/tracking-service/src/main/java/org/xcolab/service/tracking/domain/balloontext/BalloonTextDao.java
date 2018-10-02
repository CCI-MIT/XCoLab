package org.xcolab.service.tracking.domain.balloontext;

import org.xcolab.client.tracking.pojo.IBalloonText;
import org.xcolab.service.tracking.exceptions.NotFoundException;

import java.util.List;

public interface BalloonTextDao {

    IBalloonText getBalloonText(Long id) throws NotFoundException;

    IBalloonText create(IBalloonText balloonText);

    boolean update(IBalloonText balloonText);

    boolean delete(Long id) ;

    List<IBalloonText> getEnabledBalloonTexts();
}
