/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    config.toolbar = 'Colab';
     
    config.toolbar_Colab =
    [
        { name: 'controls', items : [ 'Bold', 'Italic', 'Underline','NumberedList', 'BulletedList', 'Anchor', 'Link', 'Image', 'ProposalLink'] }
    ];
    config.toolbar = 'Colab';
    config.skin = "colab";
    config.toolbarCanCollapse = false;
    config.contentsCss = '/css/themes/climateColab/main.css';
    config.resize_enabled = 'true';
    config.extraPlugins = 'resize,proposalLink,scayt,menubutton,dialog';
    config.scayt_autoStartup = true;
};
CKEDITOR.pasteFilterConfig = {
};

CONTENT_FILTER_OPTIONS = {
        rules: {
				heading: true,
				table: false,
				inlineCSS: true,
				/*
				 * rmAttr       - { "all" | object with names } remove all
				 *                attributes or attributes with following names
				 *
				 * rmWhenEmpty  - if element contains no text or { \s, \n, <br>, <br/> }
				 *                then it will be removed
				 *
				 * rmWhenNoAttr - if element contains no attributes (i.e. <span>Some Text</span>)
				 *                then it will be removed
				 */
				allowedTags: {
					strong: true,
					b: true,
					em: true,
					i: true,
					ul: true,
					ol: true,
					u: true,
					img: true,
					a: true,
					body: true,
					p: true,
					br: true,
                    li: true,
                    span: true

				},
				msWordMarkup: {
					enabled: false,
					tags: {
						"a": {
							rmAttr: {
								"style": "",
								"class": ""
							},
							rmWhenEmpty: true
						},

						"b": {
							rmAttr: "all",
							rmWhenEmpty: true
						},

						"div": {
							rmAttr: "all",
							rmWhenEmpty: true,
							rmWhenNoAttr: true
						},

						"em": {
							rmAttr: "all",
							rmWhenEmpty: true
						},

						"font": {
							rmAttr: "all",
							rmWhenEmpty: true,
							rmWhenNoAttr: true
						},

						"h1": {
							rmAttr: "all",
							rmWhenEmpty: true
						},
						"h2": {
							rmAttr: "all",
							rmWhenEmpty: true
						},
						"h3": {
							rmAttr: "all",
							rmWhenEmpty: true
						},
						"h4": {
							rmAttr: "all",
							rmWhenEmpty: true
						},
						"h5": {
							rmAttr: "all",
							rmWhenEmpty: true
						},
						"h6": {
							rmAttr: "all",
							rmWhenEmpty: true
						},

						"i": {
							rmAttr: "all",
							rmWhenEmpty: true
						},

						"p": {
							rmAttr: "all",
							rmWhenEmpty: true
						},

						"span": {
							rmAttr: "all",
							rmWhenEmpty: true,
							rmWhenNoAttr: true
						},

						"strong": {
							rmAttr: "all",
							rmWhenEmpty: true
						},

						"u": {
							rmAttr: "all",
							rmWhenEmpty: true
						}
					}
                }
                }

};

CONTENT_FILTER = {
    removeMsWordFormatting: function(text) {
        var tagName, attrName, rules, reg, regAttr, found, attrs;

    	// @link https://github.com/akzhan/jwysiwyg/issues/165
	    text = text.replace(/&lt;/g, "<").replace(/&gt;/g, ">");

    	text = text.replace(/<meta\s[^>]+>/g, "");
	    text = text.replace(/<link\s[^>]+>/g, "");
    	text = text.replace(/<title>[\s\S]*?<\/title>/g, "");
		text = text.replace(/<style(?:\s[^>]*)?>[\s\S]*?<\/style>/gm, "");
		text = text.replace(/<w:([^\s>]+)(?:\s[^\/]+)?\/>/g, "");
		text = text.replace(/<w:([^\s>]+)(?:\s[^>]+)?>[\s\S]*?<\/w:\1>/gm, "");
		text = text.replace(/<m:([^\s>]+)(?:\s[^\/]+)?\/>/g, "");
		text = text.replace(/<m:([^\s>]+)(?:\s[^>]+)?>[\s\S]*?<\/m:\1>/gm, "");

		// after running the above.. it still needed these
		text = text.replace(/<xml>[\s\S]*?<\/xml>/g, "");
		text = text.replace(/<object(?:\s[^>]*)?>[\s\S]*?<\/object>/g, "");
		text = text.replace(/<o:([^\s>]+)(?:\s[^\/]+)?\/>/g, "");
		text = text.replace(/<o:([^\s>]+)(?:\s[^>]+)?>[\s\S]*?<\/o:\1>/gm, "");
		text = text.replace(/<st1:([^\s>]+)(?:\s[^\/]+)?\/>/g, "");
		text = text.replace(/<st1:([^\s>]+)(?:\s[^>]+)?>[\s\S]*?<\/st1:\1>/gm, "");
		// ----
		text = text.replace(/<!--[^>]+>\s*<[^>]+>/gm, ""); // firefox needed this 1

						
		text = text.replace(/^[\s\n]+/gm, "");

		if (this.options.rules.msWordMarkup.tags) {
			for (tagName in this.options.rules.msWordMarkup.tags) {
				rules = this.options.rules.msWordMarkup.tags[tagName];
				
				if ("string" === typeof (rules)) {
					if ("" === rules) {
						reg = new RegExp("<" + tagName + "(?:\\s[^>]+)?/?>", "gmi");
						text = text.replace(reg, "<" + tagName + ">");
					}
				} else if ("object" === typeof (rules)) {
                    reg = new RegExp("<" + tagName + "(\\s[^>]+)?/?>", "gmi");

                    //loop over all tags found and remove attributes according to the rules
                    while (found = reg.exec(text)) {
                        attrs = "";

                        if (found && found[1]) {
                            attrs = found[1];
                        }

                        if (rules.rmAttr) {
                            if ("all" === rules.rmAttr) {
                                attrs = "";
                            } else if ("object" === typeof (rules.rmAttr) && attrs) {
                                for (attrName in rules.rmAttr) {
                                    regAttr = new RegExp(attrName + '="[^"]*"', "mi");
                                    attrs = attrs.replace(regAttr, "");
                                }
                            }
                        }

                        if (attrs) {
                            attrs = attrs.replace(/[\s\n]+/gm, " ");

                            if (" " === attrs) {
                                attrs = "";
                            }
                        }
                        if (found && found[0]) {
                            text = text.replace(found[0], "<" + tagName + attrs + ">");
                        }
                    }
				}
			}

			for (tagName in this.options.rules.msWordMarkup.tags) {
				rules = this.options.rules.msWordMarkup.tags[tagName];

				if ("string" === typeof (rules)) {
					//
				} else if ("object" === typeof (rules)) {
					if (rules.rmWhenEmpty) {
						reg = new RegExp("<" + tagName + "(\\s[^>]+)?>(?:[\\s\\n]|<br/?>)*?</" + tagName + ">", "gmi");
						text = text.replace(reg, "");
					}

					if (rules.rmWhenNoAttr) {
						reg = new RegExp("<" + tagName + ">(?!<" + tagName + ">)([\\s\\S]*?)</" + tagName + ">", "mi");
						found = reg.exec(text);
						while (found) {
							text = text.replace(reg, found[1]);

							found = reg.exec(text);
						}
					}
				}
			}
		}

		return text;
    },
        domRemove: function (node) {
        	// replace h1-h6 with p
			if (this.options.rules.heading) {
				if (node.nodeName.toLowerCase().match(/^h[1-6]$/)) {
					// in chromium change this to
					// jQuery(node).replaceWith(jQuery('<p/>').html(node.innerHTML));
					// to except DOM error: also try in other browsers
					jQuery(node).replaceWith(jQuery('<p />').html("<strong>" + jQuery(node).html() + "</strong>"));
					return true;

				}
			}
			

			// remove tables not smart enough )
			if (this.options.rules.table) {
				if (node.nodeName.toLowerCase().match(/^(table|t[dhr]|t(body|foot|head))$/)) {
					jQuery(node).replaceWith(jQuery(node).contents());

					return true;
				}
			}
			
			if (! (node.nodeName.toLowerCase() in this.options.rules.allowedTags )) {
				// in chromium change this to
				// jQuery(node).replaceWith(jQuery('<p/>').html(node.innerHTML));
				// to except DOM error: also try in other browsers
				jQuery(node).replaceWith(jQuery('<span/>').html(jQuery(node).contents()));

				return true;
			}
			
			if (node.nodeName)

			return false;
		},
		
		removeStyle: function(node) {
		  if (this.options.rules.inlineCSS && ! node.nodeName.toLowerCase().match(/^img$/)) {
		    jQuery(node).removeAttr('style');
		  }
		},
		domTraversing: function (el, canRemove, cnt) {
			if (el == null) return;
			
			function nextElementSibling( el ) {
			    do { el = el.nextSibling; } while ( el && el.nodeType !== 1 );
			    return el;
			}
			
			if (null === canRemove) {
				canRemove = false;
			}
			

			var current = el.childNodes[0];
			if (current && current.nodeType != 1) {
				current = nextElementSibling(current);
			}
            while (current) {
				var tmp = current;
				current = nextElementSibling(current);
				
				this.domTraversing(tmp, canRemove, cnt + 1);

			}
			this.domRemove(el);
			this.removeStyle(el);
		},
    init: function(options) {
        this.options = options;
    }
};

CONTENT_FILTER.init(CONTENT_FILTER_OPTIONS);

CKEDITOR.on('instanceReady', function (ev) {
    ev.editor.on('paste', function (ev) {
        var tmp = CONTENT_FILTER.removeMsWordFormatting(ev.data.html);
        var par = jQuery("<p />").html(tmp);
        CONTENT_FILTER.domTraversing(par.get(0), true, 1);
        ev.data.html = par.html();
    });
    ev.editor.on('afterPaste', function (ev) {
//        CONTENT_FILTER.domTraversing(ev.editor.document['$'].body, true, 1);
    });
});

