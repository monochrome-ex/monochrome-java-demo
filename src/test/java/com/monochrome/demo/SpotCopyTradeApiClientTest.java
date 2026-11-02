package com.monochrome.demo;

import com.monochrome.api.client.copytrade.spot.MonochromeSpotCopyTradeApiClientImpl;
import com.monochrome.api.dto.CommonResponse;
import com.monochrome.api.dto.copytrade.spot.CopyTradeProfitUpdateReqDTO;
import com.monochrome.api.dto.copytrade.spot.FollowLeaderDTO;
import com.monochrome.api.dto.copytrade.spot.LeaderSettingsDTO;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xielina
 * @version 1.0
 * @create 2024/6/11 13:52
 */
public class SpotCopyTradeApiClientTest {
    MonochromeSpotCopyTradeApiClientImpl monochromeSpotApiClient = new MonochromeSpotCopyTradeApiClientImpl(null);

    @Test
    public void testGetCurLeaderOrder() {
        Map<String, String> params = new HashMap<>();
        params.put("type","1");
        CommonResponse commonResponse = monochromeSpotApiClient.getCurLeaderOrder(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetCurFollowerOrder() {
        Map<String, String> params = new HashMap<>();
        params.put("type","1");
        CommonResponse commonResponse = monochromeSpotApiClient.getCurFollowerOrder(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetHisFollowerOrder() {
        Map<String, String> params = new HashMap<>();
        params.put("symbol","btc_usdt");
        params.put("direction","NEXT");
        params.put("limit","10");
        CommonResponse commonResponse = monochromeSpotApiClient.getHisFollowerOrder(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetHisLeaderOrder() {
        Map<String, String> params = new HashMap<>();
        params.put("symbol","btc_usdt");
        params.put("direction","NEXT");
        params.put("limit","10");
        CommonResponse commonResponse = monochromeSpotApiClient.getHisLeaderOrder(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testStopProfitLoss() {
        CopyTradeProfitUpdateReqDTO request = CopyTradeProfitUpdateReqDTO.builder()
                .symbol("ont_usdt")
                .leaderOrderId(370761691765619904L)
                .triggerProfitPrice(new BigDecimal("15.0"))
                .triggerStopPrice(new BigDecimal("0.1"))
                .build();
        CommonResponse commonResponse = monochromeSpotApiClient.stopProfitLoss(request);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testSell() {
        Map<String, String> params = new HashMap<>();
        params.put("leaderOrderId","370788011182603456");
        params.put("symbol","ont_usdt");
        CommonResponse commonResponse = monochromeSpotApiClient.sell(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testSellAll() {
        Map<String, String> params = new HashMap<>();
        params.put("leaderOrder","true");
        CommonResponse commonResponse = monochromeSpotApiClient.sellAll(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testClose() {
        Map<String, String> params = new HashMap<>();
        params.put("leaderOrderId","370800430378335424");
        params.put("symbol","ont_usdt");
        CommonResponse commonResponse = monochromeSpotApiClient.close(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testCloseAll() {
        Map<String, String> params = new HashMap<>();
        params.put("symbol","ont_usdt");
        CommonResponse commonResponse = monochromeSpotApiClient.closeAll(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetCopyTradeSymbol(){
        Map<String, String> params = new HashMap<>();
        CommonResponse commonResponse = monochromeSpotApiClient.getCopyTradeSymbol(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetUserStatus(){
        Map<String, String> params = new HashMap<>();
        CommonResponse commonResponse = monochromeSpotApiClient.getUserStatus(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetLeaderSettings(){
        Map<String, String> params = new HashMap<>();
        CommonResponse commonResponse = monochromeSpotApiClient.getLeaderSettings(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testUpdateLeaderSetting(){
        LeaderSettingsDTO request = LeaderSettingsDTO.builder()
                .leaderIntro("测试修改带单设置")
                .symbols("xt_usdt,ont_usdt,btc3l_usdt,btc_usdt,eth_usdt,etc_usdt,elf_usdt,sc_usdt,dcr_usdt,xrp_usdt,xlm2_usdt,doge_usdt,fil6_usdt,sol_usdt,bsv_usdt,btx_usdt,xtt_usdt")
                .displayQuantity(true)
                .displayEquity(true)
                .build();
        CommonResponse commonResponse = monochromeSpotApiClient.updateLeaderSetting(request);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetShareProfitHis(){
        Map<String, String> params = new HashMap<>();
        params.put("direction","NEXT");
        params.put("limit","2");
        CommonResponse commonResponse = monochromeSpotApiClient.getShareProfitHis(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetShareProfitHisDetail(){
        Map<String, String> params = new HashMap<>();
        params.put("time","1718150400000");
        params.put("direction","NEXT");
        params.put("limit","2");
        CommonResponse commonResponse = monochromeSpotApiClient.getShareProfitHisDetail(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetShareProfitExpectedDetail(){
        Map<String, String> params = new HashMap<>();
        params.put("direction","NEXT");
        params.put("limit","2");
        CommonResponse commonResponse = monochromeSpotApiClient.getShareProfitExpectedDetail(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetShareProfitExpected(){
        Map<String, String> params = new HashMap<>();
        CommonResponse commonResponse = monochromeSpotApiClient.getShareProfitExpected(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testCopyTradeApply(){
        CommonResponse commonResponse = monochromeSpotApiClient.copyTradeApply();
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void cancelLeader(){
        CommonResponse commonResponse = monochromeSpotApiClient.cancelLeader();
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetLeaderDetail(){
        Map<String, String> params = new HashMap<>();
        params.put("accountId","5589391507863");
        CommonResponse commonResponse = monochromeSpotApiClient.getLeaderDetail(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetMyLeaders(){
        Map<String, String> params = new HashMap<>();
        params.put("type","1");
        CommonResponse commonResponse = monochromeSpotApiClient.getMyLeaders(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetFollowConfig(){
        Map<String, String> params = new HashMap<>();
        params.put("leaderAccountId","5589391507863");
        CommonResponse commonResponse = monochromeSpotApiClient.getFollowLeaderConfig(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testFollow(){
        FollowLeaderDTO request = FollowLeaderDTO.builder()
                .leaderAccountId(5589391507863L)
                .followType("RATE")
                .followVal(new BigDecimal("1"))
                .autoFollowSymbol(true)
                .followLoss(null)
                .followLossOperate("LEADER_CLOSE")
                .symbols("xt_usdt,ont_usdt,btc3l_usdt,btc_usdt,eth_usdt,etc_usdt,elf_usdt,sc_usdt,dcr_usdt,xrp_usdt,xlm2_usdt,doge_usdt,fil6_usdt,sol_usdt,bsv_usdt,btx_usdt,xtt_usdt")
                .triggerStopRate(null)
                .triggerProfitRate(null)
                .build();
        CommonResponse commonResponse = monochromeSpotApiClient.follow(request);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void cancelFollow(){
        Map<String, String> params = new HashMap<>();
        params.put("leaderAccountId","5589391507863");
        CommonResponse commonResponse = monochromeSpotApiClient.cancelFollow(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetLeaderStats(){
        Map<String, String> params = new HashMap<>();
        params.put("accountId","5589391507863");
        params.put("days","7");
        CommonResponse commonResponse = monochromeSpotApiClient.getLeaderStats(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetLeaderIncomeRate(){
        Map<String, String> params = new HashMap<>();
        params.put("accountId","5589391507863");
        CommonResponse commonResponse = monochromeSpotApiClient.getLeaderIncomeRate(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetLeaderIncom(){
        Map<String, String> params = new HashMap<>();
        params.put("accountId","5589391507863");
        CommonResponse commonResponse = monochromeSpotApiClient.getLeaderIncom(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetLeaderTradingPrefer(){
        Map<String, String> params = new HashMap<>();
        params.put("accountId","5589391507863");
        CommonResponse commonResponse = monochromeSpotApiClient.getLeaderTradingPrefer(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetLeaderOrderPage(){
        Map<String, String> params = new HashMap<>();
        params.put("leaderAccountId","5589391507863");
        params.put("symbol","btc_usdt");
        params.put("type","1");
        CommonResponse commonResponse = monochromeSpotApiClient.getLeaderOrderPage(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetLeaderOrderHistory(){
        Map<String, String> params = new HashMap<>();
        params.put("leaderAccountId","5589391507863");
        params.put("symbol","btc_usdt");
        params.put("direction","NEXT");
        params.put("limit","2");
        CommonResponse commonResponse = monochromeSpotApiClient.getLeaderOrderHistory(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void testGetLeaderFollowers(){
        Map<String, String> params = new HashMap<>();
        params.put("direction","NEXT");
        params.put("limit","2");
        CommonResponse commonResponse = monochromeSpotApiClient.getLeaderFollowers(params);
        System.out.println("result:"+commonResponse);
    }
}
