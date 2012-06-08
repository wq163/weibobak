package weibo4j.examples.tags;

import weibo4j.Tags;
import weibo4j.Weibo;
import weibo4j.model.TagWapper;
import weibo4j.model.WeiboException;

public class GetTagsBatch {

	public static void main(String[] args) {
		String access_token = args[0];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		Tags tm = new Tags();
		TagWapper tags = null;
		String uids = args[1];
		try {;
			tags = tm.getTagsBatch(uids);
			System.out.println(tags.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

}
