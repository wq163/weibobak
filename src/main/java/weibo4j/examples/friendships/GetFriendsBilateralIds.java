package weibo4j.examples.friendships;

import weibo4j.Friendships;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;

public class GetFriendsBilateralIds {

	public static void main(String[] args) {
		String access_token = args[0];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		String uid = args[1];
		Friendships fm = new Friendships();
		try {
			String[] ids = fm.getFriendsBilateralIds(uid);
			for(String s : ids){
				Log.logInfo(s);
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
}


