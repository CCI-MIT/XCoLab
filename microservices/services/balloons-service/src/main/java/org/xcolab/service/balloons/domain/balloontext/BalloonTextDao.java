package org.xcolab.service.balloons.domain.balloontext;

import org.xcolab.model.tables.pojos.BalloonText;
import org.xcolab.service.balloons.exceptions.NotFoundException;

import java.util.List;

public interface BalloonTextDao {
    BalloonText getBallonText(Long id) throws NotFoundException;

    BalloonText create(BalloonText balloonText);

    boolean update(BalloonText balloonText);

    boolean delete(Long id) ;

    List<BalloonText> getEnabledBalloonTexts();
}
