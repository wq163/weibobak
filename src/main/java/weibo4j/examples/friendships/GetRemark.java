package weibo4j.examples.friendships;

import weibo4j.Friendships;
import weibo4j.Weibo;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;

public class GetRemark {

	public static void main(String[] args) {
		String access_token = args[0];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		String uids = args[1];
		Friendships fm = new Friendships();
		try {
			JSONArray user = fm.getRemark(uids);
			System.out.println(user.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

}
