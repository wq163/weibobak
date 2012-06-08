package weibo4j.examples.tags;

import java.util.List;

import weibo4j.Tags;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Tag;
import weibo4j.model.WeiboException;

public class GetTagsSuggestions {

	public static void main(String[] args) {
		String access_token = args[0];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		Tags tm = new Tags();
		List<Tag> tags = null;
		try {
			tags = tm.getTagsSuggestions();
			for(Tag tag : tags){				
				Log.logInfo(tag.toString());
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
