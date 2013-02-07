/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

function initiailzeEditTrigger(isLoggedIn, canEdit, isPlanEditable, alwaysshow) {
    if (isPlanEditable) {
        var message = "Log in to edit";
        var callback = loginClickCallback;
        if (isLoggedIn) {
            if (canEdit) {
                message = "Click to edit";
                callback = editCallback;
            }
            else {
                message = "Join the team to edit";
                callback = joinTeamCallback;
            }
        }

        jQuery(".editTrigger .message").text(message);
        clearEditTrigger();
        if (!alwaysshow) {
         
            jQuery(".editTriggerContainer").hover(function() {
                jQuery(".editTrigger").show();
            }, function() {
                jQuery(".editTrigger").hide();
            }
                    );

        } else {
            jQuery(".editTrigger").show();
        }


        jQuery(".editTrigger").click(function() {
            callback()
        });
    }
}

function clearEditTrigger() {
    jQuery(".editTriggerContainer").unbind('mouseenter mouseleave');
    jQuery(".editTrigger").unbind("click");
    jQuery(".editTrigger").hide();

}

function loginClickCallback() {
    deferUntilLogin();
}

function joinTeamCallback() {
    Collab.nav.navigateAddParams("plans", {tab: "team"}, false);
}

function editCallback() {
    jQuery(".editStateTrigger").click();
}