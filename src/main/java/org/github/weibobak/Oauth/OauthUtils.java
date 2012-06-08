package org.github.weibobak.Oauth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.github.weibobak.Config;
import org.github.weibobak.Request;

import weibo4j.Oauth;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.http.Response;
import weibo4j.model.PostParameter;


/**
 * 
 * @author 无花
 * @since 2012-6-6 上午11:20:37
 */

public class OauthUtils {

    private static Logger log = Logger.getLogger(OauthUtils.class.getName());


    public static boolean doOauth2() {
        try {
            PostParameter[] params =
                    new PostParameter[] { new PostParameter("withOfficalFlag", 0),
                                         new PostParameter("response_type", "code"),
                                         new PostParameter("redirect_uri", Config.getValue("redirect_URI").trim()),
                                         new PostParameter("client_id", Config.getValue("client_ID").trim()),
                                         new PostParameter("action", "submit"),
                                         new PostParameter("userId", Config.getValue("userId").trim()),
                                         new PostParameter("passwd", Config.getValue("passwd").trim()),
                                         new PostParameter("isLoginSina", ""), new PostParameter("regCallback", ""),
                                         new PostParameter("state", ""), new PostParameter("from", "") };
            Response response = post(Config.getValue("authorizeURL").trim(), params);
            if (response.getStatusCode() != 302) {
                return false;
            }
            String location = response.getResponseHeader("Location");
            String code = getCode(location);
            if (StringUtils.isBlank(code)) {
                log.error("invalid code");
                return false;
            }

            debug("code=" + code);
            Oauth oauth = new Oauth();
            AccessToken accessToken = oauth.getAccessTokenByCode(code);
            Weibo weibo = new Weibo();
            weibo.setToken(accessToken.getAccessToken());
            return true;
        }
        catch (Throwable e) {
            log.error("some problem on doOauth2", e);
            return false;
        }

    }


    static Response post(String url, PostParameter[] params) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        HttpMethodParams param = postMethod.getParams();
        param.setContentCharset("UTF-8");
        postMethod
            .addRequestHeader("Referer",
                "https://api.weibo.com/oauth2/authorize?client_id=2671507095&redirect_uri=http://jenwang.org&response_type=code");
        postMethod
            .addRequestHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.151 Safari/535.19");

        for (int i = 0; i < params.length; i++) {
            postMethod.addParameter(params[i].getName(), params[i].getValue());
        }

        client.executeMethod(postMethod);

        int responseCode = -1;
        Header[] resHeader = postMethod.getResponseHeaders();
        responseCode = postMethod.getStatusCode();
        debug("Response:");
        debug("https StatusCode:" + String.valueOf(responseCode));

        Response0 response = new Response0();
        for (Header header : resHeader) {
            response.addHeader(header.getName(), header.getValue());
            debug(header.getName() + ":" + header.getValue());
        }
        response.setResponseAsString(postMethod.getResponseBodyAsString());
        response.setStatusCode(responseCode);
        debug(response.toString() + "\n");
        return response;
    }


    private static void debug(String string) {
        if (log.isDebugEnabled()) {
            log.debug(string);
        }
    }

    static class Response0 extends Response {
        private Map<String, String> headers;


        @Override
        public String getResponseHeader(String name) {
            String value;
            value = super.getResponseHeader(name);
            if (value == null) {
                value = this.headers.get(name);
            }
            return value;
        }


        public void addHeader(String name, String value) {
            if (this.headers == null && name != null) {
                this.headers = new HashMap<String, String>();
            }
            if (name != null) {
                this.headers.put(name, value);
            }
        }
    }


    private static String getCode(String location) {
        return new Request(location).getParameter("code");
    }
}
