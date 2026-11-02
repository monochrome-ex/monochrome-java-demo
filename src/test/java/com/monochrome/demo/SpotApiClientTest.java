package com.monochrome.demo;

import com.monochrome.api.client.spot.MonochromeSpotApiClientImpl;
import com.monochrome.api.dto.CommonResponse;
import com.monochrome.api.dto.nft.NftDepositRequest;
import com.monochrome.api.dto.nft.NftWithdrawRequest;
import com.monochrome.api.dto.spot.NetworthUpdateRequest;
import com.monochrome.api.dto.spot.SpotPostOrderRequest;
import com.monochrome.api.dto.spot.SpotUpdateOrderRequest;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * SPOT Business
 * @author zhouzhuang
 * @create 2023/9/20 16:15
 */
public class SpotApiClientTest {

    MonochromeSpotApiClientImpl monochromeSpotApiClient = new MonochromeSpotApiClientImpl(null);

    @Test
    public void testpostOrder() {
        SpotPostOrderRequest request = SpotPostOrderRequest.builder().symbol("btc_usdt")
                .side("BUY")
                .type("LIMIT")
                .timeInForce("GTC")
                .bizType("SPOT")
                .price("63000")
                .quantity("0.001")
                .media("btok")
                .mediaChannel("btok123")
                .build();
        CommonResponse commonResponse = monochromeSpotApiClient.postOrder(request);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void getOrder() {
        CommonResponse commonResponse = monochromeSpotApiClient.getOrder(351569051824340480L);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void queryOrder() {
        CommonResponse commonResponse = monochromeSpotApiClient.queryOrder(274722413139647104L);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void delOrder() {
        CommonResponse commonResponse = monochromeSpotApiClient.delOrder(274722413139647104L);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void netWorth() {
        NetworthUpdateRequest request = NetworthUpdateRequest.builder().symbol("btc3l_usdt").netWorth(BigDecimal.valueOf(1.2)).build();
        CommonResponse commonResponse = monochromeSpotApiClient.netWorth(request);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void updateOrder(){
        CommonResponse commonResponse = monochromeSpotApiClient.updateOrder(407309222976613568L, SpotUpdateOrderRequest.builder().quantity("0.002").price("63010").build());
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void nftDeposit() {
        NftDepositRequest nftDepositRequest = NftDepositRequest.builder()
                .currency("BTCsn")
                .serial("123")
                .passcode("123")
                .build();
        CommonResponse commonResponse = monochromeSpotApiClient.nftDeposit(nftDepositRequest);
        System.out.println("result:" + commonResponse);
    }

    @Test
    public void nftWithdraw() {
        NftWithdrawRequest nftWithdrawRequest = NftWithdrawRequest.builder()
                .currency("BTCsn")
                .serial("123")
                .build();
        CommonResponse commonResponse = monochromeSpotApiClient.nftWithdraw(nftWithdrawRequest);
        System.out.println("result:" + commonResponse);
    }

    @Test
    public void nftHistoryOrder() {
        CommonResponse commonResponse = monochromeSpotApiClient.nftHistoryOrder(2, null, null, null, null, 1, 10);
        System.out.println("result:" + commonResponse);
    }
}
