function initZeroClipboard() {
	
	// first find correct path to swf file
	var moviePath = "swf/ZeroClipboard.swf";
	$("script").each(function() {
		if (this.src.indexOf("ZeroClipboard") >= 0) {
			moviePath = this.src.substring(0, this.src.indexOf("ZeroClipboard")) + "ZeroClipboard.swf";
		}
	});
	// bind zero clipboard
	ZeroClipboard.setDefaults( { moviePath: moviePath});
    var clip = new ZeroClipboard($("#copyButton"));
    clip.on("load", function() { $("#copyButton").click(function() {console.log('false?'); return false;});});
    
    clip.setHandCursor( true );
    
}
