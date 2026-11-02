package com.monochrome.demo;

import com.monochrome.api.client.copytrade.futures.MonochromeFuturesCopyTradeApiClientImpl;
import com.monochrome.api.dto.FutureCommonResponse;
import com.monochrome.api.dto.copytrade.futures.AdjustLeverageReqDTO;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fonda
 */
public class FuturesCopyTradeApiClientTest {

    MonochromeFuturesCopyTradeApiClientImpl monochromeFuturesApiClient = new MonochromeFuturesCopyTradeApiClientImpl(null);

    @Test
    public void testStopProfitLoss() {
        Map<String, String> params = new HashMap<>();
        params.put("trackNo", "372123709462611969");
        params.put("triggerPriceType", "MARK_PRICE");
        params.put("triggerProfitPrice", "70000");
        params.put("triggerStopPrice", "65000");
        params.put("leaderOrder", "1");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.stopProfitLoss(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testCloseAllOrders() {
        Map<String, String> params = new HashMap<>();
        // params.put("symbol", "btc_usdt");
        // params.put("closeLongShortType", "LONG");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.closeAllOrders(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testCloseOrder() {
        Map<String, String> params = new HashMap<>();
        params.put("trackNo", "372115029962852353");
        params.put("leaderOrder", "1");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.closeOrder(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetCurrLeaderOrderList() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "1");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getCurrLeaderOrderList(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetCurrLeaderOrderPage() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "1");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getCurrLeaderOrderPage(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetHisLeaderOrderPage() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "1");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getHisLeaderOrderPage(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetCurrFollowerOrderList() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "1");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getCurrFollowerOrderList(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetCurrFollowerOrderPage() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "1");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getCurrFollowerOrderPage(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetHisFollowerOrderPage() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "2");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getHisFollowerOrderPage(params);
        System.out.println("result: " + futureCommonResponse);
    }


    @Test
    public void testGetCopyTradeSymbol(){
        Map<String, String> params = new HashMap<>();
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getPublicAvailableSymbols(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetLeaderInfo(){
        Map<String, String> params = new HashMap<>();
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getLeaderInfo(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetPublicLeaderInfo(){
        Map<String, String> params = new HashMap<>();
        params.put("accountId", "5605502353728");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getPublicLeaderInfo(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetFollowerInfo(){
        Map<String, String> params = new HashMap<>();
        params.put("leaderAccountId", "5605502353728");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getFollowInfo(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetUserSettings(){
        Map<String, String> params = new HashMap<>();
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getUserSettings(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testApplyLeader(){
        Map<String, String> params = new HashMap<>();
        params.put("nickName", "123abc");
        params.put("images", "https://a.static-global.com/1/user/common/bf5f42f8-e815-4817-9c13-2b14b4af24e0-1709539990384.png");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.applyLeader(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testCancelLeader(){
        Map<String, String> params = new HashMap<>();
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.cancelLeader(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testChooseLeader(){
        Map<String, String> params = new HashMap<>();
        params.put("leaderAccountId", "5372510536645");
        params.put("followType", "FUND");
        params.put("followVal", "10");
        params.put("autoFollowSymbol", "1");
        params.put("followLossOperate", "LEADER_CLOSE");
        params.put("symbols", "btc_usdt,eth_usdt,xrp_usdt,bnb_usdt,trb_usdt,vsys_usdt");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.chooseLeader(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testCancelChooseLeader(){
        Map<String, String> params = new HashMap<>();
        params.put("leaderAccountId", "5372510536645");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.cancelChooseLeader(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetMyFollowLeaderList(){
        Map<String, String> params = new HashMap<>();
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getMyFollowLeaderList(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetMyFollowLeaderHistory(){
        Map<String, String> params = new HashMap<>();
        params.put("type", "1");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getMyFollowLeaderHistory(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetPublicLeaderStats(){
        Map<String, String> params = new HashMap<>();
        params.put("accountId", "5605502353728");
        params.put("days", "30");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getPublicLeaderStats(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetPublicLeaderDayIncome(){
        Map<String, String> params = new HashMap<>();
        params.put("accountId", "5605502353728");
        params.put("days", "7");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getPublicLeaderDayIncome(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetPublicLeaderDayIncomeRate(){
        Map<String, String> params = new HashMap<>();
        params.put("accountId", "5605502353728");
        params.put("days", "7");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getPublicLeaderDayIncomeRate(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetLeaderTradingPrefer(){
        Map<String, String> params = new HashMap<>();
        params.put("accountId", "5605502353728");
        params.put("recentDays", "30");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getPublicLeaderSymbolPrefer(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetPublicCurrLeaderOrderPage(){
        Map<String, String> params = new HashMap<>();
        params.put("leaderAccountId", "5605502353728");
        //params.put("symbol", "btc_usdt");
        params.put("type", "1");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getPublicCurrLeaderOrderPage(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetPublicHisLeaderOrderPage(){
        Map<String, String> params = new HashMap<>();
        params.put("leaderAccountId", "5605502353728");
        params.put("symbol", "btc_usdt");
        params.put("type", "1");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getPublicHisLeaderOrderPage(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetPublicLeaderFollowers(){
        Map<String, String> params = new HashMap<>();
        params.put("leaderAccountId","5605502353728");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getPublicLeaderFollowers(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetLeaderFutureProfitDetail(){
        Map<String, String> params = new HashMap<>();
        params.put("direction", "NEXT");
        params.put("limit", "2");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getLeaderFutureProfitDetail(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetLeaderFutureProfitTotal(){
        Map<String, String> params = new HashMap<>();
        params.put("direction", "NEXT");
        params.put("limit", "2");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getLeaderFutureProfitTotal(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetLeaderHistoryProfitDetail(){
        Map<String, String> params = new HashMap<>();
        params.put("direction", "NEXT");
        params.put("limit", "2");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getLeaderHistoryProfitDetail(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetLeaderHistoryProfitTotal(){
        Map<String, String> params = new HashMap<>();
        params.put("direction", "NEXT");
        params.put("limit", "2");
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getLeaderHistoryProfitTotal(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testGetLeverageList(){
        Map<String, String> params = new HashMap<>();
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.getLeverageList(params);
        System.out.println("result: " + futureCommonResponse);
    }

    @Test
    public void testAdjustLeverage(){
        AdjustLeverageReqDTO request = AdjustLeverageReqDTO.builder()
                .symbol("btc_usdt")
                .leverage(3)
                .positionSide("LONG")
                .build();
        FutureCommonResponse futureCommonResponse = monochromeFuturesApiClient.adjustLeverage(request);
        System.out.println("result: " + futureCommonResponse);
    }

}
