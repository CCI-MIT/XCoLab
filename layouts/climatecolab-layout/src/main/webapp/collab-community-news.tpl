<div class="lrContentPlaceholder lfr-column">
        $processor.processColumn("column-1")
</div>

<div id="content">
    <div class="lrContentPlaceholder lfr-column">
        $processor.processColumn("column-2")
    </div>
    
    <div class="community-left ">
        <div id="communityHeading">
            <div class="community-left-left lrContentPlaceholder lfr-column ">
                <div class="smallBoxInner">
                    $processor.processColumn("column-8")
                </div>
            </div>
        
            <div class="community-left-right lrContentPlaceholder lfr-column ">
                <div class="smallBoxInner">
                    $processor.processColumn("column-7")
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
        <div>
            <div>
                $processor.processColumn("column-3")
            </div>
        </div>
    </div> <!-- .col-left -->
    <div class="community-right lrContentPlaceholder lfr-column">
        $processor.processColumn("column-4")
    </div> <!-- .col-right -->
</div>


<div id="c-Footer">
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
    </div> <!-- #c-Footer__menu -->
    
    <div class="inner lrContentPlaceholder lfr-column ">
        $processor.processColumn("column-5")
    </div> <!-- .inner -->
</div> <!-- #c-Footer -->