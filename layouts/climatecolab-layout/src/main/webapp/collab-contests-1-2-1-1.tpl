<div class="lrContentPlaceholder lfr-column ">
    $processor.processColumn("column-1")
</div>

<div id="suggestContestPopupContainer" >
    <div class="popup-wrap p1" id="suggestContestPopup">
        <div class="popup">
            <h4>Please enter your suggestion below.  A message will be sent to the CoLab staff and they will follow up with you soon.</h4>
            <div id="suggestContestPopupContent" class="lrContentPlaceholder lfr-column ">
                $processor.processColumn("column-4")
            </div>
        </div>
    </div>
</div>
        
<div id="footer">
    <div id="footmenu">
        <ul>
            <li><a href="/web/guest/feedback">Contact</a></li>
            <li><a href="/web/guest/resources/-/wiki/Main/Help">Help</a></li>
            #if ($themeDisplay.signedIn)
                <li><a href="/c/portal/logout">Sign out</a></li>
                <li><a href="/web/guest/member/-/member/userId/$themeDisplay.user.userId">My profile</a></li>
            #else 
                <li><a href="javascript:return false;" class="openreg">Register</a></li>
                <li><a href="javascript:return false;" onclick="deferUntilLogin();">Sign In</a></li>
            #end
        </ul>
    </div> <!-- #footmenu -->
    
    <div class="inner lrContentPlaceholder lfr-column ">
                $processor.processColumn("column-2")
    </div> <!-- .inner -->
</div> <!-- #footer -->
