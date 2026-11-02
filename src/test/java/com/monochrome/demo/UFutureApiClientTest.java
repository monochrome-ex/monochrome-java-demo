package com.monochrome.demo;

import com.google.gson.Gson;
import com.monochrome.api.client.future.MonochromeFutureApiClient;
import com.monochrome.api.client.future.MonochromeUFutureApiClientImpl;
import com.monochrome.api.dto.FutureCommonResponse;
import com.monochrome.api.dto.future.FuturePostOrderRequest;
import org.junit.Test;

import java.util.*;

/**
 * USDT BASE Future
 * @author zhouzhuang
 * @create 2023/9/20 16:15
 */
public class UFutureApiClientTest {
    Gson gson = new Gson();
    MonochromeFutureApiClient client = new MonochromeUFutureApiClientImpl(null);

    @Test
    public void postOrder(){
        FuturePostOrderRequest request = FuturePostOrderRequest.builder().symbol("doge_usdt")
                .origQty("100")
                .orderType("LIMIT")
                .timeInForce("GTC")
                .price("0.01")
                .orderSide("BUY")
                .positionSide("LONG")
                .build();
        FutureCommonResponse commonResponse = client.postOrder(request);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void marketOpenLongOrder(){
        FuturePostOrderRequest request = FuturePostOrderRequest.builder().symbol("doge_usdt")
                .origQty("100")
                .orderType("MARKET")
                .timeInForce("IOC")
                .orderSide("BUY")
                .positionSide("LONG")
                .build();
        FutureCommonResponse commonResponse = client.postOrder(request);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void marketCloseLongOrder(){
        FuturePostOrderRequest request = FuturePostOrderRequest.builder().symbol("doge_usdt")
                .origQty("100")
                .orderType("MARKET")
                .timeInForce("IOC")
                .orderSide("SELL")
                .positionSide("LONG")
                .build();
        FutureCommonResponse commonResponse = client.postOrder(request);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void batchOrder(){
        FuturePostOrderRequest request = FuturePostOrderRequest.builder().symbol("doge_usdt")
                .origQty("100")
                .orderType("LIMIT")
                .timeInForce("GTC")
                .price("0.01")
                .orderSide("BUY")
                .positionSide("LONG")
                .build();
        FuturePostOrderRequest request2 = FuturePostOrderRequest.builder().symbol("doge_usdt")
                .origQty("100")
                .orderType("LIMIT")
                .timeInForce("GTC")
                .price("0.01")
                .orderSide("BUY")
                .positionSide("LONG")
                .build();
        List<FuturePostOrderRequest> requestList = new ArrayList<>();
        requestList.add(request);
        requestList.add(request2);
        FutureCommonResponse commonResponse = client.batchOrder(requestList);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void orderListHistory() {
        Map<String, String> params = new HashMap<>();
        FutureCommonResponse commonResponse = client.orderListHistory(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void orderTradeList() {
        Map<String, String> params = new HashMap<>();
        params.put("orderId", "645871156502529600");
        params.put("page", "1");
        params.put("size", "20");
        FutureCommonResponse commonResponse = client.orderTradeList(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void orderDetail() {
        FutureCommonResponse commonResponse = client.orderDetail(645869942645786176L);
        System.out.println("result:"+commonResponse);
    }
    @Test
    public void orderList() {
        Map<String, String> params = new HashMap<>();
        FutureCommonResponse commonResponse = client.orderList(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void orderCancel() {
        FutureCommonResponse commonResponse = client.orderCancel(645869942645786176L);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void allCancel() {
        FutureCommonResponse commonResponse = client.allCancel("doge_usdt");
        System.out.println("result:"+commonResponse);
    }


    @Test
    public void entrustCreatePlan() {
        Map<String, String> params = new HashMap<>();
        params.put("symbol","doge_usdt");
        params.put("orderSide","BUY");
        params.put("entrustType","TAKE_PROFIT");
        params.put("origQty","100");
        params.put("price","0.20");
        params.put("timeInForce","GTC");
        params.put("triggerPriceType","LATEST_PRICE");
        params.put("positionSide","LONG");
        params.put("stopPrice","0.20");
        FutureCommonResponse commonResponse = client.entrustCreatePlan(params);
        System.out.println("result:"+commonResponse);
    }
    @Test
    public void entrustCancelPlan() {
        FutureCommonResponse commonResponse = client.entrustCancelPlan(645870759448741440L);
        System.out.println("result:"+commonResponse);
    }
    @Test
    public void entrustCancelAllPlan() {
        FutureCommonResponse commonResponse = client.entrustCancelAllPlan("doge_usdt");
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void accountInfo() {
        FutureCommonResponse commonResponse = client.accountInfo();
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void balanceDetail() {
        FutureCommonResponse commonResponse = client.balanceDetail("usdt");
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void listenKey() {
        FutureCommonResponse commonResponse = client.listenKey();
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void adjustLeverage() {
        FutureCommonResponse commonResponse = client.adjustLeverage("btc_usdt","LONG",5);
        System.out.println("result:"+commonResponse);
    }

}
