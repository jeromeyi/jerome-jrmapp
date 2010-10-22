package com.jrmapp.lucene.search;

public class SearchResultBean {
    private String htmlPath;
    
    private String htmlTitle;
    
    private String contents;

	public String getHtmlPath() {
		return htmlPath;
	}

	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}

	public String getHtmlTitle() {
		return htmlTitle;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
	}
}
