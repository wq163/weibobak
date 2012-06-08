package org.github.weibobak;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.github.weibobak.utils.ResourceUtils;

import weibo4j.util.WeiboConfig;


/**
 * 
 * @author 无花
 * @since 2012-6-6 下午06:21:59
 */

public class Config {
    private static Properties props = new Properties();

    static {
        try {
            props.putAll(ResourceUtils.getResourceAsProperties("weibobak.properties", "UTF-8"));
            if (StringUtils.isNotBlank(props.getProperty("client_ID"))) {
                WeiboConfig.updateProperties("client_ID", props.getProperty("client_ID").trim());
            }

            if (StringUtils.isNotBlank(props.getProperty("client_SERCRET"))) {
                WeiboConfig.updateProperties("client_SERCRET", props.getProperty("client_SERCRET").trim());
            }
        }
        catch (Throwable e) {
            throw new RuntimeException("加载配置文件失败", e);
        }
    }


    public static String getValue(String key) {
        String value = props.getProperty(key);
        if (StringUtils.isBlank(value)) {
            value = WeiboConfig.getValue(key);
        }
        return value;
    }
}
