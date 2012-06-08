package weibo4j.examples.trends;

import weibo4j.Trend;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

public class TrendDestroy {

	public static void main(String[] args){
		String access_token = args[0];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		Trend tm = new Trend();
		int trendId = Integer.parseInt(args[1]);
		try {
			JSONObject result = tm.trendsDestroy(trendId);
			Log.logInfo(String.valueOf(result));
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
