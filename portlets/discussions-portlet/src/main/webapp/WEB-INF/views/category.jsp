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

            <c:if test="${not empty currentCategory}">
                <div class="description">
                        ${currentCategory.description}
                </div>
            </c:if>


            <table class="thread-list">
                <thead class="blueheaderbar">
                <tr>
                    <th>
                        <portlet:actionURL var="sortURL">
                            <portlet:param name="action" value="sortCategory" />
                            <portlet:param name="categoryId" value="${currentCategory.id}" />
                            <portlet:param name="sortColumn" value="TITLE" />
                            <portlet:param name="currentSortColumn" value="${sortColumn}" />
                            <portlet:param name="currentSortAscending" value="${sortAscending}" />
                        </portlet:actionURL>
                        <a href="${sortURL }">Title</a>
                        <div class="tooltip">
                            click to sort by title
                            <div class="tt-arrow"><!-- --></div>
                        </div>
                        <collab:sortArrow sortAscending="${sortAscending}" sortColumn="${sortColumn}" currentColumn="TITLE" />
                    </th>

                    <th>
                        <portlet:actionURL var="sortURL">
                            <portlet:param name="action" value="sortCategory" />
                            <portlet:param name="categoryId" value="${currentCategory.id}" />
                            <portlet:param name="sortColumn" value="REPLIES" />
                            <portlet:param name="currentSortColumn" value="${sortColumn}" />
                            <portlet:param name="currentSortAscending" value="${sortAscending}" />
                        </portlet:actionURL>
                        <a href="${sortURL }">Replies</a>
                        <div class="tooltip">
                            click to sort by replies
                            <div class="tt-arrow"><!-- --></div>
                        </div>
                        <collab:sortArrow sortAscending="${sortAscending}" sortColumn="${sortColumn}" currentColumn="REPLIES" />
                    </th>

                    <th>
                        <portlet:actionURL var="sortURL">
                            <portlet:param name="action" value="sortCategory" />
                            <portlet:param name="categoryId" value="${currentCategory.id}" />
                            <portlet:param name="sortColumn" value="LAST_COMMENTER" />
                            <portlet:param name="currentSortColumn" value="${sortColumn}" />
                            <portlet:param name="currentSortAscending" value="${sortAscending}" />
                        </portlet:actionURL>
                        <a href="${sortURL }">Last Commenter</a>
                        <div class="tooltip">
                            click to sort by last commenter
                            <div class="tt-arrow"><!-- --></div>
                        </div>
                        <collab:sortArrow sortAscending="${sortAscending}" sortColumn="${sortColumn}" currentColumn="LAST_COMMENTER" />
                    </th>

                    <th>
                        <portlet:actionURL var="sortURL">
                            <portlet:param name="action" value="sortCategory" />
                            <portlet:param name="categoryId" value="${currentCategory.id}" />
                            <portlet:param name="sortColumn" value="DATE" />
                            <portlet:param name="currentSortColumn" value="${sortColumn}" />
                            <portlet:param name="currentSortAscending" value="${sortAscending}" />
                        </portlet:actionURL>
                        <a href="${sortURL }">Date</a>
                        <div class="tooltip">
                            click to sort by date
                            <div class="tt-arrow"><!-- --></div>
                        </div>
                        <collab:sortArrow sortAscending="${sortAscending}" sortColumn="${sortColumn}" currentColumn="DATE" />
                    </th>
                </tr>
                </thead>
                <c:forEach var="thread" items="${threads}">
                    <tr>
                        <td><a href="${thread.linkUrl}">${thread.title}</a></td>
                        <td>${thread.commentsCount}</td>
                        <td><collab:userLinkSimple userId="${thread.lastActivityAuthor.userId}" text="${thread.lastActivityAuthor.screenName}"/></td>
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
