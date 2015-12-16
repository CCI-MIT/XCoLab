<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:collab="http://climatecolab.org/tags/collab_1.0"
          xmlns:portlet="http://java.sun.com/portlet_2_0"
          version="2.0">

    <div id="bread" class="pro">
        <a href="/web/guest/members">Community</a>&#160;
        <img src="/climatecolab-theme/images/arrow.gif" width="8" height="8" />&#160;
        <a href="/web/guest/discussion">Discussion</a>&#160;
        <img src="/climatecolab-theme/images/arrow.gif" width="8" height="8" />&#160;
        <a href="/web/guest/discussion/-/discussion/categories/create">Create Category</a>&#160;
    </div>

    <h1>Create new category</h1>
    <div class="addthread">
        <div id="thecomment">
            <portlet:actionURL var="addCategoryUrl">
                <portlet:param name="action" value="createCategory" />
            </portlet:actionURL>
            <form id="addThreadForm" action="${addCategoryUrl }" method="post">
                <div>
                    <label class="portlet-form-label">Title: <br />
                        <input class="portlet-form-input-field input_long" name="title" maxlength="255" style="width:660px;" type="text" value="" />
                    </label>
                </div>
                <div>
                    <label class="portlet-form-label">Description: <br />
                        <textarea class="portlet-form-input-field profile_about" name="description" cols="20" rows="2" style="width: 660px; height: 100px;"><!-- --></textarea>
                    </label>
                </div>
                <div>
                    <input type="submit" class="primary-button" value="Add" />
                    <a class="grey-button" href="/web/guest/discussion">Cancel</a>
                </div>
            </form>
        </div>
    </div>

</jsp:root>
