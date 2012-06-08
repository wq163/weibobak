package org.github.weibobak.Favorites;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import weibo4j.model.Favorites;
import weibo4j.model.Status;
import weibo4j.model.User;


/**
 * 备份成易读的文本格式
 * 
 * @author 无花
 * @since 2012-6-6 下午05:19:01
 */

public class ReadableTextFavoritesProcessor implements FavoritesProcessor {
    private final static Logger log = Logger.getLogger(JsonTextFavoritesProcessor.class);
    private final static String APPEND_NAME = "ReadableTextAppender";

    private final Logger writer = Logger.getLogger("ReadableTextLog");


    public ReadableTextFavoritesProcessor() {
        if (this.writer.getAppender(APPEND_NAME) == null) {
            log.info("init ReadableTextAppender...");
            try {
                FileAppender appender =
                        new FileAppender(new PatternLayout("%m%n%n%n"), "bakdir/readableTextBak_"
                                + System.currentTimeMillis() + ".txt");
                appender.setEncoding("UTF-8");
                appender.setThreshold(Level.INFO);
                appender.activateOptions();
                appender.setName(APPEND_NAME);
                this.writer.addAppender(appender);
                this.writer.setAdditivity(false);

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private final static String FIELD_SPLIT = "\t";
    private final static String BR = "--------------------------------------\n";


    @Override
    public boolean process(Favorites favorites) {
        try {
            StringBuilder output = new StringBuilder();
            Status status = favorites.getStatus();
            this.inputTitle(output, status);
            output.append(BR);
            this.inputContent(output, status);

            Status retweetedStatus = status.getRetweetedStatus();
            if (retweetedStatus != null) {
                output.append(FIELD_SPLIT);
                this.inputTitle(output, retweetedStatus);
                this.inputContent(output, retweetedStatus);
            }
            output.append("收藏时间:" + favorites.getFavoritedTime());
            this.writer.info(output.toString());
            return true;
        }
        catch (RuntimeException e) {
            log.error("处理数据出错:" + favorites);
            throw e;
        }
    }


    private void inputContent(StringBuilder output, Status status) {
        output.append(status.getText()).append("\n");
        String pic = status.getOriginalPic();
        if (StringUtils.isNotBlank(pic)) {
            output.append("img:" + pic).append("\n");
        }
    }


    private void inputTitle(StringBuilder output, Status status) {
        User user = status.getUser();

        // user 为null可能是本条微博已删除
        output.append(user != null ? user.getScreenName() : "null").append(FIELD_SPLIT).append(status.getCreatedAt())
            .append("\n");

    }
}
