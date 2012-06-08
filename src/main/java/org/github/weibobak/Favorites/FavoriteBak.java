package org.github.weibobak.Favorites;

import java.util.List;

import org.apache.log4j.Logger;
import org.github.weibobak.Config;
import org.github.weibobak.Oauth.OauthUtils;

import weibo4j.Weibo;
import weibo4j.http.Response;
import weibo4j.model.Favorites;
import weibo4j.model.Paging;
import weibo4j.util.WeiboConfig;


/**
 * 
 * @author jenwang<mailto:wq163@163.com>
 * @since 2012-6-6 上午11:47:22
 */

public class FavoriteBak {
    private static Logger log = Logger.getLogger(FavoriteBak.class.getName());

    private final FavoritesProcessor processor;


    public FavoriteBak(FavoritesProcessor processor) {
        this.processor = processor;
    }


    public static void main(String[] args) {
        if (!OauthUtils.doOauth2()) {
            log.error("authorize failed !");
        }

        new FavoriteBak(loadProcessor()).doBak();
    }


    private static FavoritesProcessor loadProcessor() {
        try {
            return (FavoritesProcessor) Class.forName(Config.getValue("favorsProcessor")).newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException("Load FavoritesProcessor failed !", e);
        }

    }


    private void doBak() {
        // Favorite fm = new Favorite();

        List<Favorites> favors;
        int pageSize = 50;
        int pageNo = 1;
        int count = 0;

        int totalNumber = -1;
        try {
            do {
                Response response =
                        Weibo.client.get(WeiboConfig.getValue("baseURL") + "favorites.json", null, new Paging(pageNo,
                            pageSize));
                favors = Favorites.constructFavorites(response);

                if (favors != null && !favors.isEmpty()) {
                    log.info("获取第" + pageNo + "页," + favors.size() + "条.");
                    this.processFavors(favors);
                    count += favors.size();
                    if (totalNumber == -1) {
                        totalNumber = response.asJSONObject().getInt("total_number");
                    }
                }
                else {
                    log.info("获取第" + pageNo + "页,0条.");
                }

                ++pageNo;
            } while ((favors != null && favors.size() >= pageSize) || (count < totalNumber));

            log.info("备份完成,共" + count + "条");
        }
        catch (Throwable e) {
            log.error("备份过程中失败.", e);
        }
    }


    private void processFavors(List<Favorites> favors) {
        for (Favorites favorites : favors) {
            this.processor.process(favorites);
        }

    }
}
