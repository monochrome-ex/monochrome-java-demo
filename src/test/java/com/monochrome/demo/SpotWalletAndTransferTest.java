package com.monochrome.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monochrome.api.util.MonochromeHttpUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SpotWalletAndTransferTest {

    private static final long TEST_ACCOUNT_ID = 9920622693124L;
    private static final String BNB_SMART_CHAIN = "BNB Smart Chain";

    @Test
    public void queryDepositAddress() {
        Map<String, Object> depositAddressParam = new HashMap<>();
        depositAddressParam.put("currency", "usdt");
        depositAddressParam.put("chain", BNB_SMART_CHAIN);
        String depositAddressResponse = MonochromeHttpUtil.get("/v4/deposit/address", depositAddressParam);
        System.out.println("depositAddressResponse====" + depositAddressResponse);
        assertSuccess("query deposit address failed", depositAddressResponse);
    }

    @Test
    public void queryDepositHistory() {
        Map<String, Object> depositHistoryParam = new HashMap<>();
        depositHistoryParam.put("currency", "usdt");
        depositHistoryParam.put("chain", BNB_SMART_CHAIN);
        depositHistoryParam.put("limit", 10);
        String depositHistoryResponse = MonochromeHttpUtil.get("/v4/deposit/history", depositHistoryParam);
        System.out.println("depositHistoryResponse====" + depositHistoryResponse);
        assertSuccess("query deposit history failed", depositHistoryResponse);
    }

    @Test
    public void queryWithdrawRecords() {
        Map<String, Object> withdrawHistoryParam = new HashMap<>();
        withdrawHistoryParam.put("currency", "usdt");
        withdrawHistoryParam.put("limit", 10);
        String withdrawHistoryResponse = MonochromeHttpUtil.get("/v4/withdraw/history", withdrawHistoryParam);
        System.out.println("withdrawHistoryResponse====" + withdrawHistoryResponse);
        assertSuccess("query withdraw history failed", withdrawHistoryResponse);

        Map<String, Object> withdrawInfoParam = new HashMap<>();
        withdrawInfoParam.put("clientOrderId", "not_exist_" + System.currentTimeMillis());
        String withdrawInfoResponse = MonochromeHttpUtil.get("/v4/withdraw", withdrawInfoParam);
        System.out.println("withdrawInfoResponse====" + withdrawInfoResponse);
        assertSuccess("query withdraw info failed", withdrawInfoResponse);
    }

    @Test
    public void applyInternalWithdrawWithSmallAmount() {
        Map<String, Object> param = new HashMap<>();
        param.put("currency", "usdt");
        param.put("amount", "1");
        param.put("clientOrderId", "wd" + System.currentTimeMillis());
        param.put("toAccountId", TEST_ACCOUNT_ID);

        String requestBody = JSON.toJSONString(param);
        System.out.println("internalWithdrawRequest====" + requestBody);
        String response = MonochromeHttpUtil.post("/v4/withdraw", requestBody);
        System.out.println("internalWithdrawResponse====" + response);
        assertOpenApiResponse("apply internal withdraw did not return openapi response", response);
    }

    @Test
    public void transferRequestsReturnBusinessResponse() {
        Map<String, Object> transferParam = new HashMap<>();
        transferParam.put("bizId", "transfer" + System.currentTimeMillis());
        transferParam.put("bizType", "FUTURES_U");
        transferParam.put("toBizType", "SPOT");
        transferParam.put("currency", "usdt");
        transferParam.put("amount", "1");
        String transferBody = JSON.toJSONString(transferParam);
        System.out.println("transferRequest====" + transferBody);
        String transferResponse = MonochromeHttpUtil.post("/v4/balance/transfer", transferBody);
        System.out.println("transferResponse====" + transferResponse);
        assertSuccess("balance transfer failed", transferResponse);

        Map<String, Object> accountTransferParam = new HashMap<>();
        accountTransferParam.put("bizId", "accountTransfer" + System.currentTimeMillis());
        accountTransferParam.put("bizType", "SPOT");
        accountTransferParam.put("toBizType", "SPOT");
        accountTransferParam.put("currency", "usdt");
        accountTransferParam.put("amount", "1");
        accountTransferParam.put("toAccountId", TEST_ACCOUNT_ID);
        String accountTransferBody = JSON.toJSONString(accountTransferParam);
        System.out.println("accountTransferRequest====" + accountTransferBody);
        String accountTransferResponse = MonochromeHttpUtil.post("/v4/balance/account/transfer", accountTransferBody);
        System.out.println("accountTransferResponse====" + accountTransferResponse);
        assertResponseReturned("account transfer did not return response", accountTransferResponse);
    }

    private void assertSuccess(String message, String response) {
        JSONObject json = assertOpenApiResponse(message, response);
        Assert.assertEquals(message + ": " + response, 0, json.getIntValue("rc"));
    }

    private JSONObject assertOpenApiResponse(String message, String response) {
        JSONObject json = JSON.parseObject(response);
        Assert.assertTrue(message + ": " + response, json.containsKey("rc"));
        Assert.assertTrue(message + ": " + response, json.containsKey("mc"));
        return json;
    }

    private void assertResponseReturned(String message, String response) {
        Assert.assertNotNull(message, response);
        Assert.assertFalse(message, response.isBlank());
    }
}
