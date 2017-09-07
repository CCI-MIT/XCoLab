// Globally required scripts

function deferUntilLogin(source) {
    if (_isLoggedIn) {
        return true;
    } else {
        if (source != null) {
            var $source = $(source);
            jQuery("#signInForm_form").find("input[name=redirect]").val($source.attr('href'));
        }
        $('#loginModal').modal();
    }
}

function deferUntilLoginTargeted(targetLocation) {

    if (_isLoggedIn) {
        return true;
    } else {
        if (targetLocation != null) {
            jQuery("#signInForm_form").find("input[name=redirect]").val(targetLocation);
        }
        $('#loginModal').modal();
    }
}

function showForgotPasswordModal() {
    jQuery('#loginModal').modal('hide');
    jQuery('#forgotPasswordModal').modal();
}


function enableDirtyCheck() {
    window.oldOnBeforeUnload = window.onbeforeunload;
    window.onbeforeunload = function() {
        if (jQuery(".editorDirty").length > 0) {
            return 'You have modified this page but have not saved your changes.';
        }
        return null;
    };
}

function disableDirtyCheck() {
    if ('oldOnBeforeUnload' in window) {
        window.onbeforeunload = window.oldOnBeforeUnload;
    }
    delete window.onbeforeunload;
}

(function () {
    var POLLING_INITIAL_DELAY_SECONDS = 2;
    var POLLING_INTERVAL_MINUTES = 3;

    function poll() {
        $.ajax({
            type: "GET",
            url: "/notificationMessage",
            data: null,
            success: function (result) {

                var cookieId = Cookies.get("notificationId");

                if (result.notificationId > 0 && result.notificationId != cookieId) {
                    noty({text: result.notificationText, type: 'success'});
                    Cookies.set('notificationId', result.notificationId, {expires: 1});
                }
            },
            error: function (result) {
                console.error('Error retrieving notifications: ' + result)
            }
        });
        setTimeout(poll, POLLING_INTERVAL_MINUTES * 60 * 1000);
    }

    jQuery(function() {
        setTimeout(poll, POLLING_INITIAL_DELAY_SECONDS * 1000);
    });
}());

jQuery(function () {
    // TODO: figure out where this is used - do we still need it?
    initTreeWithDynatree();

    function initTreeWithDynatree() {
        var treeContainer = jQuery(".jsTreeContainer");
        if (treeContainer.length > 0) {
            var nodeKey = location.hash.toString();

            if (nodeKey.length > 0) {
                var node = jQuery(nodeKey);
                if (node.length > 0) {
                    // remove expanded, active, selected from all nodes
                    jQuery(".active, .expanded, .selected", treeContainer).removeClass(
                            "active").removeClass("expanded").removeClass("selected");
                    node.addClass("expanded").addClass("selected");
                    node.parents("li").addClass("expanded");
                }
            }
            treeContainer.dynatree({
                onActivate: function (node) {
                    if (node.data.href) {
                        //   use href attribute
                        window.location.href = node.data.href;
                    }
                }
            });

            treeContainer.find(".externallink").each(function () {
                var linkSpan = jQuery(this);
                var linkAnchor = jQuery("<a href=\"" + linkSpan.attr("href")
                        + "\" class=\"externallink\">" + linkSpan.text() + "</a>");
                linkAnchor.click(function () {
                    window.location.href = linkAnchor.attr("href");
                });
                linkSpan.replaceWith(linkAnchor);
            });
            jQuery(".jsTreeExpandDefaultToggle a").click(expandDynatree);
        }
    }

    function expandDynatree() {
        var treeContainer = jQuery(".jsTreeContainer");
        if (treeContainer.hasClass('expanded')) {
            window.location.reload();
        }
        treeContainer.addClass("expanded");
        treeContainer.dynatree("getTree").visit(function (node) {
            node.expand(true);
        }, true);
        jQuery(".jsTreeExpandDefaultToggle").toggle();
        treeContainer.find(".externallink").each(function () {
            var linkSpan = jQuery(this);
            var linkAnchor = jQuery("<a href=\"" + linkSpan.attr("href") + "\" class=\"externallink\">"
                    + linkSpan.text() + "</a>");
            linkAnchor.click(function () {
                window.location.href = linkAnchor.attr("href");
            });
            linkSpan.replaceWith(linkAnchor);
        });

        return false;
    }
});

// TODO: do we need this?
function submitenter(myfield, e) {
    var keycode;
    if (window.event) {
        keycode = window.event.keyCode;
    } else if (e) {
        keycode = e.which;
    } else {
        return true;
    }

    if (keycode == 13) {
        jQuery(myfield.form).submit();
        return false;
    } else {
        return true;
    }
}
