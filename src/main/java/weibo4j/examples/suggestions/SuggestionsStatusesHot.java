package weibo4j.examples.suggestions;

import weibo4j.Suggestion;
import weibo4j.Weibo;
import weibo4j.model.WeiboException;

public class SuggestionsStatusesHot {

	public static void main(String[] args) {
		Weibo weibo = new Weibo();
		weibo.setToken(args[0]);
		Suggestion suggestion = new Suggestion();
		try {
			suggestion.suggestionsStatusesHot(1, 1);
		} catch (WeiboException e) {
			e.printStackTrace();
		}


	}

}
