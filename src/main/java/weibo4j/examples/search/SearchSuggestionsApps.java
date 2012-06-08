package weibo4j.examples.search;

import weibo4j.Search;
import weibo4j.Weibo;
import weibo4j.model.WeiboException;

public class SearchSuggestionsApps {

	public static void main(String[] args) {
		Weibo weibo = new Weibo();
		weibo.setToken(args[0]);
		Search search = new Search();
		try {
			search.searchSuggestionsApps(args[1], 10);
		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

}
