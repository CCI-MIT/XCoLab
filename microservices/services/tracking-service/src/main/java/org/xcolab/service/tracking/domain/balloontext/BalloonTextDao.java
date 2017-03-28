package org.xcolab.service.tracking.domain.balloontext;

import org.xcolab.model.tables.pojos.BalloonText;
import org.xcolab.service.tracking.exceptions.NotFoundException;

import java.util.List;

public interface BalloonTextDao {

    BalloonText getBalloonText(Long id) throws NotFoundException;

    BalloonText create(BalloonText balloonText);

    boolean update(BalloonText balloonText);

    boolean delete(Long id) ;

    List<BalloonText> getEnabledBalloonTexts();
}
