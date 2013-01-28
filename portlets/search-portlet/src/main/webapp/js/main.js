function goSearch() {
	var searchPhrase = escape(jQuery('.searchInput').val());
	Collab.nav.navigate("search", {searchPhrase: searchPhrase});
}