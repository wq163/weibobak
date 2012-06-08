package weibo4j.examples.comment;

import weibo4j.Comments;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;

public class DestroyCommentBatcn {

	public static void main(String[] args) {
		String access_token = args[0];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		String cids = args[1];
		Comments cm = new Comments(); 
		try {
			JSONArray com = cm.destoryCommentBatch(cids);
			Log.logInfo(com.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
