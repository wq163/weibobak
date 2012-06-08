package weibo4j.examples.favorites;

import java.util.List;

import weibo4j.Favorite;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.FavoritesTag;
import weibo4j.model.WeiboException;

public class GetFavoritesTags {

	public static void main(String[] args) {
		String access_token = args[0];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		Favorite fm = new Favorite();
		try {
			List<FavoritesTag> favors = fm.getFavoritesTags();
			for(FavoritesTag s : favors){
				Log.logInfo(s.toString());
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}