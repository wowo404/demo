package org.liu.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SearchFactory {
	
	private SearchFactory(){}
	
	public static Search newSearch(){
		Search search = null;
		ServiceLoader<Search> loader = ServiceLoader.load(Search.class);
		Iterator<Search> iter = loader.iterator();
		while (iter.hasNext()) {
			search = iter.next();
		}
		return search;
	}

}
