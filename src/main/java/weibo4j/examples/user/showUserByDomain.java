package weibo4j.examples.user;

import weibo4j.Users;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

public class showUserByDomain {

	public static void main(String[] args) {
		String access_token = args[0];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		String domain  = args[1];
		Users um = new Users();
		try {
			User user = um.showUserByDomain(domain);
			Log.logInfo(user.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
