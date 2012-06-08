package weibo4j.examples.account;

import java.util.List;

import weibo4j.Account;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.School;
import weibo4j.model.WeiboException;

public class GetAccountPrpfileSchoolList {

	public static void main(String[] args) {
		Weibo weibo = new Weibo();
		String access_token = args[0];// 输入授权后的AccessToken
		weibo.setToken(access_token);
		Account am = new Account();
		try {
			List<School> schools = am.getAccountPrpfileSchoolList();
			for (School school : schools) {
				Log.logInfo(school.toString());
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
