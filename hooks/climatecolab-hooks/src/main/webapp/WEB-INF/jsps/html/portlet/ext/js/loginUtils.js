/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

function deferUntilLogin(fn) {
        if (Liferay.ThemeDisplay.isSignedIn()) {
            if (fn) fn();
            return true;
        } else {
            _user_info_showLoginPopup();
            return false;
        }
}