package com.jrmapp.lucene.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import com.jrmapp.lucene.index.IndexManager;

/**
 * This class is used to search the 
 * Lucene index and return search results
 */
public class SearchManager {
	
    private String searchWord;
    
    private IndexManager indexManager;
    
    private Analyzer analyzer;
    
    public SearchManager(String searchWord){
    	this.searchWord   =  searchWord;
    	this.indexManager =  new IndexManager();
    	this.analyzer     =  new StandardAnalyzer();
    }
    
    /**
     * do search
     */
    public List search(){
    	List searchResult = new ArrayList();
    	if(false == indexManager.ifIndexExist()){
    		try {
				if(false == indexManager.createIndex()){
					return searchResult;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return searchResult;
			}
    	}
    	
    	IndexSearcher indexSearcher = null;
    	
    	try{
    		indexSearcher = new IndexSearcher(indexManager.getIndexDir());
    	}catch(IOException ioe){
    		ioe.printStackTrace();
    	}
        
    	QueryParser queryParser = new QueryParser("content",analyzer);
    	Query query = null;
    	try {
			query = queryParser.parse(searchWord);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(null != query && null != indexSearcher){			
			try {
				Hits hits = indexSearcher.search(query);
				for(int i = 0; i < hits.length(); i ++){
					SearchResultBean resultBean = new SearchResultBean();
					resultBean.setHtmlPath(hits.doc(i).get("path"));
					resultBean.setHtmlTitle(hits.doc(i).get("title"));
					resultBean.setContents(hits.doc(i).get("content"));
					searchResult.add(resultBean);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	return searchResult;
    }
}
