package org.github.weibobak;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * @author jenwang<mailto:wq163@163.com>
 * @since 2012-6-6 上午10:20:36
 */

public class Request {

    Map<String, List<String>> parameterMap;
    String url;


    public Request(String url) {
        this.url = url;
        this.parameterMap = new LinkedHashMap<String, List<String>>();
        this.parseQueryString(this.url.substring(this.url.indexOf('?') + 1), "UTF-8");
    }


    public String getParameter(String name) {
        List<String> values = this.parameterMap.get(name);
        if (values != null && values.size() > 0) {
            return values.get(0);
        }
        return null;
    }


    private void parseQueryString(String queryString, String characterEncoding) {
        queryString = StringUtils.trimToNull(queryString);

        if (queryString == null) {
            return;
        }

        if (characterEncoding == null) {
            characterEncoding = "ISO-8859-1";
        }

        int startIndex = 0;
        int ampIndex = queryString.indexOf("&");

        while (ampIndex >= 0) {
            this.addKeyValue(queryString.substring(startIndex, ampIndex), characterEncoding);

            startIndex = ampIndex + 1;
            ampIndex = queryString.indexOf("&", startIndex);
        }

        this.addKeyValue(queryString.substring(startIndex), characterEncoding);
    }


    @SuppressWarnings("deprecation")
    private void addKeyValue(String keyValue, String characterEncoding) {
        int index = keyValue.indexOf("=");
        String key;
        String value;

        if (index < 0) {
            key = keyValue;
            value = null;
        }
        else {
            key = keyValue.substring(0, index);
            value = keyValue.substring(index + 1);
        }

        if (!StringUtils.isEmpty(key)) {
            try {
                key = URLDecoder.decode(key, characterEncoding);
                value = URLDecoder.decode(value, characterEncoding);
            }
            catch (UnsupportedEncodingException e) {
                key = URLDecoder.decode(key);
                value = URLDecoder.decode(value);
            }

            this.add(key, this.defaultIfNull(value, ""));
        }
    }


    private void add(String key, String value) {
        List<String> values = this.parameterMap.get(key);
        if (values == null) {
            values = new LinkedList<String>();
            this.parameterMap.put(key, values);
        }
        values.add(value);
    }


    private String defaultIfNull(String value, String string) {
        return value != null ? value : string;
    }
}
