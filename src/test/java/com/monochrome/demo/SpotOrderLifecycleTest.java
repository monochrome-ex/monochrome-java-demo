package com.monochrome.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.monochrome.api.util.MonochromeHttpUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpotOrderLifecycleTest {

    @Test
    public void placeBatchLimitOrdersThenCancel() {
        String clientBatchId = "batch" + System.currentTimeMillis();
        Map<String, Object> batchParam = new HashMap<>();
        batchParam.put("clientBatchId", clientBatchId);
        batchParam.put("items", List.of(
                limitBuyOrder("batchA" + System.currentTimeMillis()),
                limitBuyOrder("batchB" + System.currentTimeMillis())
        ));

        String requestBody = JSON.toJSONString(batchParam);
        System.out.println("placeBatchOrderRequest====" + requestBody);
        String batchOrderResponse = MonochromeHttpUtil.post("/v4/batch-order", requestBody);
        System.out.println("placeBatchOrderResponse====" + batchOrderResponse);

        JSONObject batchOrderJson = JSON.parseObject(batchOrderResponse);
        Assert.assertEquals("place batch order failed", 0, batchOrderJson.getIntValue("rc"));

        JSONObject result = batchOrderJson.getJSONObject("result");
        Assert.assertNotNull("batch order result is null", result);
        JSONArray items = result.getJSONArray("items");
        Assert.assertNotNull("batch order items is null", items);
        Assert.assertEquals("batch order item size mismatch", 2, items.size());

        List<Long> orderIds = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            JSONObject item = items.getJSONObject(i);
            Assert.assertFalse("batch order item rejected: " + item, item.getBooleanValue("rejected"));
            String orderId = item.getString("orderId");
            Assert.assertNotNull("batch orderId is null", orderId);
            orderIds.add(Long.valueOf(orderId));
        }

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("orderIds", joinOrderIds(orderIds));
        String batchQueryResponse = MonochromeHttpUtil.get("/v4/batch-order", queryParam);
        System.out.println("queryBatchOrderResponse====" + batchQueryResponse);

        JSONObject batchQueryJson = JSON.parseObject(batchQueryResponse);
        Assert.assertEquals("query batch order failed", 0, batchQueryJson.getIntValue("rc"));
        Assert.assertNotNull("query batch order result is null", batchQueryJson.get("result"));

        Map<String, Object> cancelParam = new HashMap<>();
        cancelParam.put("clientBatchId", clientBatchId);
        cancelParam.put("orderIds", orderIds);
        String cancelBody = JSON.toJSONString(cancelParam);
        System.out.println("cancelBatchOrderRequest====" + cancelBody);
        String cancelResponse = MonochromeHttpUtil.deleteWithBody("/v4/batch-order", cancelBody);
        System.out.println("cancelBatchOrderResponse====" + cancelResponse);

        JSONObject cancelJson = JSON.parseObject(cancelResponse);
        Assert.assertEquals("cancel batch order failed", 0, cancelJson.getIntValue("rc"));
    }

    @Test
    public void queryAndModifyLimitOrderThenCancel() {
        String clientOrderId = "queryModify" + System.currentTimeMillis();
        String orderId = placeLimitBuyOrder(clientOrderId);

        String orderByPathResponse = MonochromeHttpUtil.get("/v4/order/" + orderId, null);
        System.out.println("orderByPathResponse====" + orderByPathResponse);
        JSONObject orderByPathJson = JSON.parseObject(orderByPathResponse);
        Assert.assertEquals("query order by path failed", 0, orderByPathJson.getIntValue("rc"));
        Assert.assertNotNull("query order by path result is null", orderByPathJson.get("result"));

        Map<String, Object> orderIdQuery = new HashMap<>();
        orderIdQuery.put("orderId", orderId);
        String orderByQueryResponse = MonochromeHttpUtil.get("/v4/order", orderIdQuery);
        System.out.println("orderByQueryResponse====" + orderByQueryResponse);
        JSONObject orderByQueryJson = JSON.parseObject(orderByQueryResponse);
        Assert.assertEquals("query order by orderId failed", 0, orderByQueryJson.getIntValue("rc"));
        Assert.assertNotNull("query order by orderId result is null", orderByQueryJson.get("result"));

        String orderByClientResponse = queryOrderByClientOrderId(clientOrderId);
        System.out.println("orderByClientResponse====" + orderByClientResponse);
        JSONObject orderByClientJson = JSON.parseObject(orderByClientResponse);
        Assert.assertEquals("query order by clientOrderId failed", 0, orderByClientJson.getIntValue("rc"));
        Assert.assertNotNull("query order by clientOrderId result is null", orderByClientJson.get("result"));

        Map<String, Object> modifyParam = new HashMap<>();
        modifyParam.put("price", "4");
        modifyParam.put("quantity", "2");
        String modifyResponse = MonochromeHttpUtil.put("/v4/order/" + orderId, JSON.toJSONString(modifyParam));
        System.out.println("modifyOrderResponse====" + modifyResponse);
        JSONObject modifyJson = JSON.parseObject(modifyResponse);
        Assert.assertEquals("modify order failed", 0, modifyJson.getIntValue("rc"));

        JSONObject modifyResult = modifyJson.getJSONObject("result");
        String cancelOrderId = modifyResult == null || modifyResult.getString("orderId") == null
                ? orderId
                : modifyResult.getString("orderId");
        cancelOrder(cancelOrderId);
    }

    @Test
    public void queryOpenOrderThenCancelOpenOrders() {
        placeLimitBuyOrder("openA" + System.currentTimeMillis());
        placeLimitBuyOrder("openB" + System.currentTimeMillis());

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("symbol", "btc_usdt");
        queryParam.put("bizType", "SPOT");
        queryParam.put("side", "BUY");
        queryParam.put("limit", 20);
        String queryOpenOrderResponse = MonochromeHttpUtil.get("/v4/open-order", queryParam);
        System.out.println("queryOpenOrderResponse====" + queryOpenOrderResponse);

        JSONObject queryOpenOrderJson = JSON.parseObject(queryOpenOrderResponse);
        Assert.assertEquals("query open order failed", 0, queryOpenOrderJson.getIntValue("rc"));
        Assert.assertNotNull("query open order result is null", queryOpenOrderJson.get("result"));

        Map<String, Object> cancelParam = new HashMap<>();
        cancelParam.put("symbol", "btc_usdt");
        cancelParam.put("side", "BUY");
        cancelParam.put("bizType", "SPOT");
        cancelParam.put("mode", "CMD");
        String cancelOpenOrderResponse = MonochromeHttpUtil.deleteWithBody("/v4/open-order", JSON.toJSONString(cancelParam));
        System.out.println("cancelOpenOrderResponse====" + cancelOpenOrderResponse);

        JSONObject cancelOpenOrderJson = JSON.parseObject(cancelOpenOrderResponse);
        Assert.assertEquals("cancel open order failed", 0, cancelOpenOrderJson.getIntValue("rc"));
    }

    @Test
    public void placeMarketBuyOrder() {
        String orderUri = "/v4/order";
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", "btc_usdt");
        param.put("side", "BUY");
        param.put("type", "MARKET");
        param.put("timeInForce", "FOK");
        param.put("bizType", "SPOT");
        param.put("quoteQty", "6");

        String requestBody = JSON.toJSONString(param);
        System.out.println("placeMarketBuyOrderRequest====" + requestBody);
        String placeOrderResponse = MonochromeHttpUtil.post(orderUri, requestBody);
        System.out.println("placeMarketBuyOrderResponse====" + placeOrderResponse);

        JSONObject placeOrderJson = JSON.parseObject(placeOrderResponse);
        Assert.assertEquals("place market buy order failed", 0, placeOrderJson.getIntValue("rc"));

        JSONObject result = placeOrderJson.getJSONObject("result");
        Assert.assertNotNull("place market buy order result is null", result);
        Assert.assertNotNull("market orderId is null", result.getString("orderId"));
    }

    @Test
    public void placeLimitOrderThenCancel() {
        String orderUri = "/v4/order";
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", "btc_usdt");
        param.put("side", "BUY");
        param.put("type", "LIMIT");
        param.put("timeInForce", "GTC");
        param.put("bizType", "SPOT");
        param.put("price", "3");
        param.put("quantity", "2");

        String requestBody = JSON.toJSONString(param);
        System.out.println("placeOrderRequest====" + requestBody);
        String placeOrderResponse = MonochromeHttpUtil.post(orderUri, requestBody);
        System.out.println("placeOrderResponse====" + placeOrderResponse);

        JSONObject placeOrderJson = JSON.parseObject(placeOrderResponse);
        Assert.assertEquals("place order failed", 0, placeOrderJson.getIntValue("rc"));

        JSONObject result = placeOrderJson.getJSONObject("result");
        Assert.assertNotNull("place order result is null", result);
        String orderId = result.getString("orderId");
        Assert.assertNotNull("orderId is null", orderId);

        String cancelUri = "/v4/order/" + orderId;
        System.out.println("cancelOrderRequest====DELETE " + cancelUri);
        String cancelOrderResponse = MonochromeHttpUtil.delete(cancelUri, null);
        System.out.println("cancelOrderResponse====" + cancelOrderResponse);

        JSONObject cancelOrderJson = JSON.parseObject(cancelOrderResponse);
        Assert.assertEquals("cancel order failed", 0, cancelOrderJson.getIntValue("rc"));
    }

    private Map<String, Object> limitBuyOrder(String clientOrderId) {
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", "btc_usdt");
        param.put("clientOrderId", clientOrderId);
        param.put("side", "BUY");
        param.put("type", "LIMIT");
        param.put("timeInForce", "GTC");
        param.put("bizType", "SPOT");
        param.put("price", "3");
        param.put("quantity", "2");
        return param;
    }

    private String placeLimitBuyOrder(String clientOrderId) {
        String requestBody = JSON.toJSONString(limitBuyOrder(clientOrderId));
        System.out.println("placeLimitBuyOrderRequest====" + requestBody);
        String placeOrderResponse = MonochromeHttpUtil.post("/v4/order", requestBody);
        System.out.println("placeLimitBuyOrderResponse====" + placeOrderResponse);

        JSONObject placeOrderJson = JSON.parseObject(placeOrderResponse);
        Assert.assertEquals("place limit buy order failed", 0, placeOrderJson.getIntValue("rc"));
        JSONObject result = placeOrderJson.getJSONObject("result");
        Assert.assertNotNull("place limit buy order result is null", result);
        String orderId = result.getString("orderId");
        Assert.assertNotNull("orderId is null", orderId);
        return orderId;
    }

    private String queryOrderByClientOrderId(String clientOrderId) {
        Map<String, Object> clientOrderQuery = new HashMap<>();
        clientOrderQuery.put("clientOrderId", clientOrderId);
        String response = null;
        for (int i = 0; i < 10; i++) {
            response = MonochromeHttpUtil.get("/v4/order", clientOrderQuery);
            JSONObject responseJson = JSON.parseObject(response);
            if (responseJson.getIntValue("rc") == 0) {
                return response;
            }
            sleepBeforeRetry();
        }
        return response;
    }

    private void sleepBeforeRetry() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError("interrupted while waiting for order read consistency", e);
        }
    }

    private void cancelOrder(String orderId) {
        String cancelUri = "/v4/order/" + orderId;
        System.out.println("cancelOrderRequest====DELETE " + cancelUri);
        String cancelOrderResponse = MonochromeHttpUtil.delete(cancelUri, null);
        System.out.println("cancelOrderResponse====" + cancelOrderResponse);

        JSONObject cancelOrderJson = JSON.parseObject(cancelOrderResponse);
        Assert.assertEquals("cancel order failed", 0, cancelOrderJson.getIntValue("rc"));
    }

    private String joinOrderIds(List<Long> orderIds) {
        StringBuilder builder = new StringBuilder();
        for (Long orderId : orderIds) {
            if (builder.length() > 0) {
                builder.append(",");
            }
            builder.append(orderId);
        }
        return builder.toString();
    }
}
