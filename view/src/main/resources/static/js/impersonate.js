$("#impersonateLogout").click(function () {
    $.post('/impersonate/logout', null, function() {
        location.reload();
    });
});

function impersonate(userId) {
    url = "/impersonate?memberId=" + userId;
    $.post(url, null, function() {
        location.reload();
    });
}

function impersonateLogout() {
    url = "/impersonate/logout";
    $.post(url, null, function() {
        location.reload();
    });
    
}
