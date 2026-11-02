package com.monochrome.api.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.GlobalHeaders;
import cn.hutool.http.HttpRequest;
import org.apache.commons.codec.digest.HmacUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author zhouzhuang
 * @create 2022/10/28 10:48
 */
public class MonochromeHttpUtil {
    private static final boolean PRINT_DEBUG = false;
    private static final int TIMEOUT_MILLIS = 10000;
    private static final String appKey = System.getenv().getOrDefault("MONOCHROME_API_KEY", "");
    private static final String secretKey = System.getenv().getOrDefault("MONOCHROME_SECRET_KEY", "");
    private static final String encry = "HmacSHA256";
    private static final String contentType = "application/json";
    private static final String spotApiUrl = "https://sapi.monochromedev.click";
    private static final String uFutureApiUrl = "https://fapi.monochromedev.click";
    private static final String bFutureApiUrl = "https://dapi.monochromedev.click";
    private static final String openApiUrl = "https://api.monochromedev.click";
    private static final String window = "6000";
    private static final String accessToken = System.getenv().getOrDefault("MONOCHROME_ACCESS_TOKEN", "");
    static {
        GlobalHeaders.INSTANCE.clearHeaders();
    }

    public static String get(String uri, Map<String, Object> queryMap) {
        return getOrDel(uri, queryMap, "GET");
    }

    public static String delete(String uri, Map<String, Object> queryMap) {
        return getOrDel(uri, queryMap, "DELETE");
    }

    public static String deleteWithBody(String uri, String jsonBody) {
        Long time = System.currentTimeMillis();
        String url = resolveBaseUrl(uri) + uri;
        String signature = generateSign(time + "", window, "DELETE", uri, null, jsonBody);
        HttpRequest httpRequest = HttpRequest.delete(url);
        config(httpRequest, time + "", signature);
        httpRequest.body(jsonBody);
        printRequest(httpRequest);
        return httpRequest.execute().body();
    }

    public static String post(String uri, String jsonBody) {
        Long time = System.currentTimeMillis();
        String url = resolveBaseUrl(uri) + uri;
        String signature = generateSign(time + "", window, "POST", uri, null, jsonBody);
        HttpRequest httpRequest = HttpRequest.post(url);
        config(httpRequest, time + "", signature);
        httpRequest.body(jsonBody);
        printRequest(httpRequest);
        return httpRequest.execute().body();
    }

    public static String put(String uri, String jsonBody) {
        Long time = System.currentTimeMillis();
        String url = resolveBaseUrl(uri) + uri;
        String signature = generateSign(time + "", window, "PUT", uri, null, jsonBody);
        HttpRequest httpRequest = HttpRequest.put(url);
        config(httpRequest, time + "", signature);
        httpRequest.body(jsonBody);
        printRequest(httpRequest);
        return httpRequest.execute().body();
    }

    private static String getOrDel(String uri, Map<String, Object> queryMap, String method) {
        Long time = System.currentTimeMillis();
        String url = resolveBaseUrl(uri) + uri;
        StringBuilder querySb = new StringBuilder();
        String query = null;
        if (!CollectionUtil.isEmpty(queryMap)) {
            TreeMap<String, Object> treeMap = new TreeMap(queryMap);
            for (String key : treeMap.keySet()) {
                querySb.append(key).append("=").append(queryMap.get(key)).append("&");
            }
            String substring = querySb.substring(0, querySb.lastIndexOf("&"));
            url += "?" + substring;
            query = substring;
        }
        String signature = generateSign(time + "", window, method, uri, query, null);
        HttpRequest httpRequest = null;
        if ("GET".equalsIgnoreCase(method)) {
            httpRequest = HttpRequest.get(url);
        } else if ("DELETE".equalsIgnoreCase(method)) {
            httpRequest = HttpRequest.delete(url);
        }
        config(httpRequest, time + "", signature);
        printRequest(httpRequest);
        return httpRequest.execute().body();
    }

    private static void printRequest(HttpRequest httpRequest) {
        if (PRINT_DEBUG) {
            System.out.println("request====" + httpRequest.getMethod() + " " + httpRequest);
        }
    }

    private static String resolveBaseUrl(String uri) {
        if (uri.startsWith("/spot/") || uri.startsWith("/user/") || uri.startsWith("/public/")) {
            return openApiUrl;
        }
        if (uri.startsWith("/future/")) {
            return uFutureApiUrl;
        }
        if (uri.startsWith("/delivery/")) {
            return bFutureApiUrl;
        }
        return spotApiUrl;
    }

    private static void config(HttpRequest httpRequest, String time, String sign) {
        httpRequest
                .contentType(contentType)
                .timeout(TIMEOUT_MILLIS)
                .header("validate-algorithms", encry)
                .header("validate-appkey", appKey)
                .header("validate-recvwindow", window)
                .header("validate-timestamp", time)
                .header("validate-signature", sign);
//                .header("access-token", accessToken);
//                .setHttpProxy("127.0.0.1",7890);
    }


    private static String generateSign(String timestamp, String window, String method, String uri, String query, String jsonBody) {
        String x = String.format("validate-algorithms=%s&validate-appkey=%s&validate-recvwindow=%s&validate-timestamp=%s", encry, appKey, window, timestamp);
        String y = String.format("#%s#%s", method, uri);
        if (query != null && query.length() > 0) {
            y += "#" + query;
        }
        if (jsonBody != null && jsonBody.length() > 0) {
            y += "#" + jsonBody;
        }
        String origin = x + y;
        if (PRINT_DEBUG) {
            System.out.println("origin===" + origin);
        }
        return HmacUtils.hmacSha256Hex(secretKey, origin);
    }


}
