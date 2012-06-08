package weibo4j.examples.suggestions;

import weibo4j.Suggestion;
import weibo4j.Weibo;
import weibo4j.model.WeiboException;

public class SuggestionsUsersNot_interested {

	public static void main(String[] args) {
		Weibo weibo = new Weibo();
		weibo.setToken(args[0]);
		Suggestion suggestion = new Suggestion();
		try {
			suggestion.suggestionsUsersNot_interested(args[1]);
		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

}
