<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:collab="http://climatecolab.org/tags/collab_1.0"
          version="2.0">

    <div id="bread" class="pro">
        <a href="/web/guest/community">Community</a>&#160;
        <img src="/climatecolab-theme/images/arrow.gif" width="8" height="8" />&#160;
        <a href="/web/guest/discussion">Discussion</a>&#160;
        <img src="/climatecolab-theme/images/arrow.gif" width="8" height="8" />&#160;
        <a href="${thread.category.linkUrl}">${thread.category.title}</a>&#160;
        <img src="/climatecolab-theme/images/arrow.gif" width="8" height="8" />&#160;
        <a href="${thread.linkUrl}">${thread.title}</a>&#160;
    </div>

    <h1>${thread.title}</h1>

    <collab:discussionComments thread="${thread}"/>

</jsp:root>
