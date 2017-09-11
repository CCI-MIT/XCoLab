function setLanguage(lang) {
    insertParamAndGo('lang', lang);
}

function insertParamAndGo(key, value) {
    key = encodeURI(key);
    value = encodeURI(value);
    var loc = window.location;

    var finalUrl = loc.protocol + "//" + loc.host + loc.pathname;
    var paramString = loc.search.replace("?", "");
    var params = paramString.split("&");
    var finalParams = [];

    for (var param in params) {
        if (params[param].indexOf(key + "=") == -1) {
            finalParams.push(params[param]);
        }
    }
    finalParams.push(key + "=" + value);
    var allParams = "";
    for (var finalParam in finalParams) {
        allParams += ((finalParam == 0) ? ("?") : ("&")) + finalParams[finalParam];
    }
    finalUrl = finalUrl + allParams;
    window.location.href = finalUrl + loc.hash;
}
