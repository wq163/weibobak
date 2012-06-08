package weibo4j.examples.tags;

import weibo4j.Tags;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

public class DestroyTag {

	public static void main(String[] args) {
		String access_token =args[0];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		Tags tm = new Tags();
		int tag_id = Integer.parseInt(args[1]);
		try {
			JSONObject result = tm.destoryTag(tag_id);
			Log.logInfo(String.valueOf(result));
		} catch (WeiboException e) {

			e.printStackTrace();
		}
	}

}
