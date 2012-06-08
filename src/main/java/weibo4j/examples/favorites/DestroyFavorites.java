package weibo4j.examples.favorites;

import weibo4j.Favorite;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Favorites;
import weibo4j.model.WeiboException;

public class DestroyFavorites {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String access_token = "2.00RQs9XCjP8_PC27953e0bc62cwKCE";
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		Favorite fm = new Favorite();
		String id = "3406785442845028";
		try {
			Favorites favors =fm.destroyFavorites(id);
			Log.logInfo(favors.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
