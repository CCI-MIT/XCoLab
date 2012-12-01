<div id="content_wrap">
    <div id="content_bdr"></div>
        <div id="content_shade">
            <div id="content_home">
                <div id="content_main" class="lfr-column" style="min-width: 100px; min-height: 50px">
                    $processor.processColumn("column-1")
                </div>      
        
                <div id="footer">
                    <div id="footdiv"></div>
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
                    </div>
                    $processor.processColumn("column-2")
                </div> <!-- /footer -->
            </div> <!-- /content_home -->
        </div> <!-- /content_shade -->
        <div id="footshade"><div class="wht"></div></div>
  </div> <!-- /content_wrap -->