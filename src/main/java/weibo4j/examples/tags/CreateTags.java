package weibo4j.examples.tags;

import weibo4j.Tags;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;

public class CreateTags {

	public static void main(String[] args){
		String access_token = args[0];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		String tag = args[1];
		Tags tm  = new Tags();
		try {
			JSONArray tags = tm.createTags(tag);
			Log.logInfo(tags.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
