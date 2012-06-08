package org.github.weibobak.Favorites;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import weibo4j.model.Favorites;


/**
 * 备份成原始的json文本格式
 * 
 * @author jenwang<mailto:wq163@163.com>
 * @since 2012-6-6 下午12:37:11
 */

public class JsonTextFavoritesProcessor implements FavoritesProcessor {

    private final static Logger log = Logger.getLogger(JsonTextFavoritesProcessor.class);
    private final static String APPEND_NAME = "JsonTextAppender";
    private final static Pattern CRLF = Pattern.compile("\r\n|\r|\n");

    private final Logger writer = Logger.getLogger("JsonTextLog");


    public JsonTextFavoritesProcessor() {
        if (this.writer.getAppender(APPEND_NAME) == null) {
            log.info("init JsonTextAppender...");
            try {
                FileAppender appender =
                        new FileAppender(new PatternLayout("%m%n"), "bakdir/jsonTextBak_" + System.currentTimeMillis()
                                + ".txt");
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


    @Override
    public boolean process(Favorites favorites) {
        String jsonString = favorites.toString();
        // 内容中的换行符用char 2替换,以便后续的解析等
        this.writer.info(replaceCRLF(jsonString, "\2"));
        return true;
    }


    public static String replaceCRLF(String input, String replacement) {
        if (input != null) {
            Matcher m = CRLF.matcher(input);
            return m.replaceAll(replacement);
        }
        return input;
    }
}
