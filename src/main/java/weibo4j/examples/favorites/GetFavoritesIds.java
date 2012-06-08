package weibo4j.examples.favorites;

import weibo4j.Favorite;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

public class GetFavoritesIds {

	public static void main(String[] args) {
		String access_token ="2.00RQs9XCjP8_PC27953e0bc62cwKCE";
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		Favorite fm = new Favorite();
		try {
			JSONObject ids = fm.getFavoritesIds();
			Log.logInfo(ids.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

}
