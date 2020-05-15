package org.xcolab.view.pages.contestmanagement.entities;

import org.xcolab.view.pages.contestmanagement.entities.massactions.ActiveMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.DeleteWithPhasesMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.FeaturedMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.FlagMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.HideRibbonsMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.LaunchContestMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.MessageAllAuthorsMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.MessageActivePhaseAuthorsMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.MessageAllUsersMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.ModelSettingsMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.OrderMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.PrivateMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.ReportPeopleInCurrentPhaseMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.ShowInListViewMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.ShowInOutlineViewMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.ShowInTileViewMassAction;
import org.xcolab.view.pages.contestmanagement.entities.massactions.SubscribeMassAction;


public enum ContestMassActions {
    ORDER(new OrderMassAction()),
    MESSAGE(new MessageActivePhaseAuthorsMassAction()),
    MESSAGE_ALL_USERS(new MessageAllUsersMassAction()),
    MESSAGE_ALL_AUTHORS(new MessageAllAuthorsMassAction()),
    REPORT_PEOPLE_IN_CURRENT_PHASE(new ReportPeopleInCurrentPhaseMassAction()),
    DELETE_WITH_PHASES(new DeleteWithPhasesMassAction()),
    LAUNCH(new LaunchContestMassAction()),
    ACTIVE(new ActiveMassAction(true)),
    PRIOR(new ActiveMassAction(false)),
    PRIVATE(new PrivateMassAction(true)),
    PUBLIC(new PrivateMassAction(false)),
    FEATURED(new FeaturedMassAction(true)),
    UNFEATURED(new FeaturedMassAction(false)),
    FLAG(new FlagMassAction()),
    MODEL_SETTINGS(new ModelSettingsMassAction()),
    SUBSCRIBE(new SubscribeMassAction(true)),
    UNSUBSCRIBE(new SubscribeMassAction(false)),
    SHOW_IN_TILE_VIEW(new ShowInTileViewMassAction(true)),
    HIDE_IN_TILE_VIEW(new ShowInTileViewMassAction(false)),
    SHOW_IN_LIST_VIEW(new ShowInListViewMassAction(true)),
    HIDE_IN_LIST_VIEW(new ShowInListViewMassAction(false)),
    SHOW_IN_OUTLINE_VIEW(new ShowInOutlineViewMassAction(true)),
    HIDE_IN_OUTLINE_VIEW(new ShowInOutlineViewMassAction(false)),
    HIDE_RIBBONS(new HideRibbonsMassAction(true)),
    SHOW_RIBBONS(new HideRibbonsMassAction(false));

    private final ContestMassAction action;

    ContestMassActions(ContestMassAction action) {
        this.action = action;
    }

    public ContestMassAction getAction() {
        return action;
    }
}
