package weibo4j.examples.favorites;

import java.util.List;

import weibo4j.Favorite;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Favorites;
import weibo4j.model.WeiboException;

public class GetFavoritesByTags {

	public static void main(String[] args) {
		String access_token = args[0];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		Favorite fm = new Favorite();
		String tid = args[1];
		try {
			List<Favorites> favors = fm.getFavoritesByTags(tid);
			for(Favorites s : favors){
				Log.logInfo(s.toString());
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
