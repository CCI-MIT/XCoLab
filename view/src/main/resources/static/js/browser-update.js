//Configure browser update
var $buoop = {
    notify: {   // Derived from browserslist in view/package.json
        i: 9,   // IE <= 9
        f: 38,  // Firefox <= 38
        o: 29,  // Opera <= 29
        s: 8,   // Safari <= 8
        c: 44   // Chrome <= 44
    },
    reminder: 0,
    style: "bottom",
    api: 5
};

//Load custom styles
link = document.createElement("link");
link.rel = "stylesheet";
link.href = '/css/browser-update.css';
document.head.appendChild(link);

//Load external update script
var e = document.createElement("script");
e.src = "https://browser-update.org/update.min.js";
document.body.appendChild(e);
