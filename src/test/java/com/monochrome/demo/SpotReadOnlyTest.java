package com.monochrome.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monochrome.api.util.MonochromeHttpUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SpotReadOnlyTest {

    @Test
    public void querySpotBalance() {
        Map<String, Object> param = new HashMap<>();
        param.put("currency", "usdt");
        String response = MonochromeHttpUtil.get("/v4/balance", param);
        System.out.println("spotBalanceResponse====" + response);
        assertSuccess("query spot balance failed", response);
    }

    @Test
    public void querySpotBalances() {
        Map<String, Object> param = new HashMap<>();
        param.put("currencies", "usdt,btc");
        String response = MonochromeHttpUtil.get("/v4/balances", param);
        System.out.println("spotBalancesResponse====" + response);
        assertSuccess("query spot balances failed", response);
    }

    @Test
    public void querySpotHistoryOrders() {
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", "btc_usdt");
        param.put("bizType", "SPOT");
        param.put("limit", 20);
        String response = MonochromeHttpUtil.get("/v4/history-order", param);
        System.out.println("spotHistoryOrdersResponse====" + response);
        assertSuccess("query spot history orders failed", response);
    }

    @Test
    public void querySpotTrades() {
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", "btc_usdt");
        param.put("bizType", "SPOT");
        param.put("limit", 20);
        String response = MonochromeHttpUtil.get("/v4/trade", param);
        System.out.println("spotTradesResponse====" + response);
        assertSuccess("query spot trades failed", response);
    }

    private void assertSuccess(String message, String response) {
        JSONObject json = JSON.parseObject(response);
        Assert.assertEquals(message + ": " + response, 0, json.getIntValue("rc"));
    }
}
