<div id="content_wrap">
    <div id="content_bdr"></div>
        <div id="content_shade">
            <div id="content_home">

<div class="custom-3-column" id="content-wrapper">
	<table class="lfr-grid" id="layout-grid">
       
        <tr>
            <td class="lfr-column" id="column-2" valign="top">
                $processor.processColumn("column-2")
            </td>

        </tr>
        <tr id="column-center">
            <td class="lfr-column" id="column-3" valign="top">
                $processor.processColumn("column-3")
            </td>
        </tr>
	</table>
</div>

<div id="c-Footer" class="lfr-column">
                    <div id="footdiv"></div>
                    <div id="c-Footer__menu">
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
                    </div>

                    $processor.processColumn("column-4")    
                </div> <!-- /c-Footer -->
            </div> <!-- /content_home_other -->
        </div> <!-- /content_shade -->
        <div id="footshade"><div class="wht"></div></div>
  </div> <!-- /content_wrap -->
