
<div id="content">
    <div class="about-left lrContentPlaceholder lfr-column ">
        $processor.processColumn("column-1")
    </div> <!-- .col-left -->
    <div class="about-right lrContentPlaceholder lfr-column">
        $processor.processColumn("column-2")
    </div> <!-- .col-right -->
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
                <li><a href="/web/guest/loginregister" class="openreg">Register</a></li>
                <li><a href="javascript:return false;" onclick="deferUntilLogin();">Sign In</a></li>
            #end
        </ul>
    </div> <!-- #footmenu -->
    
    <div class="inner lrContentPlaceholder lfr-column ">
        $processor.processColumn("column-3")
    </div> <!-- .inner -->
</div> <!-- #footer -->