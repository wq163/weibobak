package weibo4j.examples.favorites;

import weibo4j.Favorite;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Favorites;
import weibo4j.model.WeiboException;

public class CreateFavorites {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String access_token = "2.00RQs9XCjP8_PC27953e0bc62cwKCE";
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		String id = "3406785442845028";
		Favorite fm = new Favorite();
		try {
			Favorites favors = fm.createFavorites(id);
			Log.logInfo(favors.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
