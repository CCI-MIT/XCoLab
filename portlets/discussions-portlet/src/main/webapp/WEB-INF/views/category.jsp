<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:collab="http://climatecolab.org/tags/collab_1.0"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">

    <portlet:defineObjects />

    <div id="bread" class="pro">
        <a href="/web/guest/community">Community</a>&#160;
        <img src="/climatecolab-theme/images/arrow.gif" width="8" height="8" />&#160;
        <a href="/web/guest/discussion">Discussion</a>
        <c:if test="${not empty currentCategory}">
            &#160;
            <img src="/climatecolab-theme/images/arrow.gif" width="8" height="8" />&#160;
            <a href="${currentCategory.linkUrl}">${currentCategory.title}</a>&#160;
        </c:if>
    </div>

    <div class="threads-container">
        <div class="main-content">
            <div class="comm_disc-head">
                <div class="comm_disc-title">
                    <h2>${empty currentCategory ? 'All Discussions' : currentCategory.title}</h2>
                </div>
                <div class="comm_info-left">
                    <span>${threads.size()}</span> discussions
                    <a class="startDiscussion" href="/web/guest/discussion/-/discussion/threads/create">
                        <img class="spc" height="36" src="/climatecolab-theme/images/icon_new-topic.gif" width="37" /> new discussion
                    </a>
                </div>
            </div>

            <div class="description">
                This set of discussions is where Climate CoLab members can engage around any topics that interest them.
            </div>

            <table class="thread-list">
                <thead class="blueheaderbar">
                <tr>
                    <th>
                        <portlet:renderURL var="sortURL">
                            <portlet:param name="action" value="showCategory" />
                            <portlet:param name="sortColumn" value="TITLE" />
                        </portlet:renderURL>
                        <a href="${sortURL }">Title</a>
                        <div class="tooltip">
                            click to sort by title
                            <div class="tt-arrow"><!-- --></div>
                        </div>
                        <collab:sortArrow sortAscending="true" sortColumn="TITLE" currentColumn="TITLE" />
                    </th>

                    <th>
                        <a href="${sortURL }">Replies</a>
                        <div class="tooltip">
                            click to sort by replies
                            <div class="tt-arrow"><!-- --></div>
                        </div>
                    </th>

                    <th>
                        <a href="${sortURL }">Last Commenter</a>
                        <div class="tooltip">
                            click to sort by last commenter
                            <div class="tt-arrow"><!-- --></div>
                        </div>
                    </th>

                    <th>
                        <a href="${sortURL }">Date</a>
                        <div class="tooltip">
                            click to sort by date
                            <div class="tt-arrow"><!-- --></div>
                        </div>
                    </th>
                </tr>
                </thead>
                <c:forEach var="thread" items="${threads}">
                    <tr>
                        <td><a href="${thread.linkUrl}">${thread.title}</a></td>
                        <td>${thread.commentsCount}</td>
                        <td><collab:userLinkSimple userId="${thread.author.userId}" text="${thread.author.screenName}"/></td>
                        <td>${thread.modifiedDate}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="sidebar">
            <a class="subscribeLinkSmall">
                <img src="/climatecolab-theme/images/subscribe_small_2.png" /> Subscribe
            </a>
            <div class="comm_list">
                <h2>Categories</h2>
                <ul class="discussionCategories">
                    <li><a href="/web/guest/discussion">All Discussions</a></li>
                    <c:forEach var="category" items="${categories}">
                        <li><a href="${category.linkUrl}">${category.title}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>

</jsp:root>
