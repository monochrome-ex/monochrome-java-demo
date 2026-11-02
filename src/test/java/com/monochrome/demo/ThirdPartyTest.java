package com.monochrome.demo;

import com.alibaba.fastjson.JSON;
import com.monochrome.api.util.MonochromeHttpUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Third Business
 *
 * @author zhouzhuang
 * @create 2023/9/20 16:15
 */
public class ThirdPartyTest {

    @Test
    public void getToken() {
        String uri = "/public/uaa/oauth2/token";
        Map<String, Object> param = new HashMap<>();
        param.put("client_id", System.getenv().getOrDefault("MONOCHROME_OAUTH_CLIENT_ID", ""));
        param.put("client_secret", System.getenv().getOrDefault("MONOCHROME_OAUTH_CLIENT_SECRET", ""));
        param.put("grant_type", "authorization_code");
        param.put("code", System.getenv().getOrDefault("MONOCHROME_OAUTH_CODE", ""));
        System.out.println("json====" + JSON.toJSONString(param));
        System.out.println("result====" + MonochromeHttpUtil.get(uri, param));
    }

    @Test
    public void refreshToken() {
        String uri = "/public/uaa/oauth2/token";
        Map<String, Object> param = new HashMap<>();
        param.put("client_id", System.getenv().getOrDefault("MONOCHROME_OAUTH_CLIENT_ID", ""));
        param.put("client_secret", System.getenv().getOrDefault("MONOCHROME_OAUTH_CLIENT_SECRET", ""));
        param.put("grant_type", "refresh_token");
        param.put("refresh_token", System.getenv().getOrDefault("MONOCHROME_OAUTH_REFRESH_TOKEN", ""));
        System.out.println("json====" + JSON.toJSONString(param));
        System.out.println("result====" + MonochromeHttpUtil.get(uri, param));
    }

    @Test
    public void getOrder() {
        String uri = "/spot/v4/order/156201996458139136";
        System.out.println("result====" + MonochromeHttpUtil.get(uri, null));
    }

    @Test
    public void queryOrder() {
        String uri = "/spot/v4/order";
        Map<String, Object> param = new HashMap<>();
        param.put("orderId", 335861468006563328L);
        System.out.println("result====" + MonochromeHttpUtil.get(uri, param));
    }

    @Test
    public void postOrder() {
        String uri = "/spot/v4/order";
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", "btc_usdt");
        param.put("side", "BUY");
        param.put("type", "LIMIT");
        param.put("timeInForce", "GTC");
        param.put("bizType", "SPOT");
        param.put("price", "3");
        param.put("quantity", "2");
        System.out.println("json====" + JSON.toJSONString(param));
        System.out.println("result====" + MonochromeHttpUtil.post(uri, JSON.toJSONString(param)));
    }

    @Test
    public void delOrder() {
        String uri = "/spot/v4/order/156201996458139136";
        System.out.println("result====" + MonochromeHttpUtil.delete(uri, null));
    }

    @Test
    public void batchOrderGet() {
        String uri = "/spot/v4/batch-order";
        Map<String, Object> param = new HashMap<>();
        param.put("orderIds", "156201996458139136,12312313212");
        System.out.println("result====" + MonochromeHttpUtil.get(uri, param));
    }

    @Test
    public void batchOrderDel() {
        String uri = "/spot/v4/batch-order";
        Map<String, Object> param = new HashMap<>();
        param.put("clientBatchId", "123123111");
        param.put("orderIds", List.of(156201996458139136L, 12312313212L));
        System.out.println("result====" + MonochromeHttpUtil.deleteWithBody(uri, JSON.toJSONString(param)));
    }

    @Test
    public void getOpenOrder() {
        String uri = "/spot/v4/open-order";
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", "cmcx_usdt");
        param.put("bizType", "SPOT");
        System.out.println("result====" + MonochromeHttpUtil.get(uri, param));
    }

    @Test
    public void delOpenOrder() {
        String uri = "/spot/v4/open-order";
        Map<String, Object> param = new HashMap<>();
        param.put("bizType", "SPOT");
        System.out.println("result====" + MonochromeHttpUtil.deleteWithBody(uri, JSON.toJSONString(param)));
    }

    @Test
    public void getHistoryOrder() {
        String uri = "/spot/v4/history-order";
        Map<String, Object> param = new HashMap<>();
        param.put("bizType", "SPOT");
        System.out.println("result====" + MonochromeHttpUtil.get(uri, param));
    }

    @Test
    public void getTrade() {
        String uri = "/spot/v4/trade";
        Map<String, Object> param = new HashMap<>();
        param.put("bizType", "SPOT");
        System.out.println("result====" + MonochromeHttpUtil.get(uri, param));
    }

    @Test
    public void getSpotBalance() {
        String uri = "/spot/v4/balance";
        Map<String, Object> param = new HashMap<>();
        param.put("currency", "usdt");
        System.out.println("result====" + MonochromeHttpUtil.get(uri, param));
    }

    @Test
    public void getSpotBalances() {
        String uri = "/spot/v4/balances";
        Map<String, Object> param = new HashMap<>();
        param.put("currencies", "usdt,btc");
        System.out.println("result====" + MonochromeHttpUtil.get(uri, param));
    }

    @Test
    public void applyApiKey() {
        String uri = "/user/account/api-key";
        Map<String, Object> param = new HashMap<>();
        System.out.println("json====" + JSON.toJSONString(param));
        System.out.println("result====" + MonochromeHttpUtil.post(uri, null));
    }
}
