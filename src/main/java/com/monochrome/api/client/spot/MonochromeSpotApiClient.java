package com.monochrome.api.client.spot;

import com.monochrome.api.dto.CommonResponse;
import com.monochrome.api.dto.nft.NftDepositRequest;
import com.monochrome.api.dto.nft.NftWithdrawRequest;
import com.monochrome.api.dto.spot.NetworthUpdateRequest;
import com.monochrome.api.dto.spot.SpotPostOrderRequest;
import com.monochrome.api.dto.spot.SpotUpdateOrderRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Query;


/**
 * @author zhouzhuang
 * @create 2023/9/20 11:48
 */
public interface MonochromeSpotApiClient {


    CommonResponse postOrder(SpotPostOrderRequest request);

    CommonResponse getOrder(Long id);

    CommonResponse queryOrder(Long orderId);

    CommonResponse delOrder(Long id);

    CommonResponse updateOrder(Long id, SpotUpdateOrderRequest request);

    CommonResponse netWorth(NetworthUpdateRequest request);

    CommonResponse nftDeposit(NftDepositRequest request);

    CommonResponse nftWithdraw(NftWithdrawRequest request);

    CommonResponse nftHistoryOrder(Integer type, Integer status, String currency,
                                         Long startTime, Long endTime,
                                         Integer pageIndex, Integer pageSize);
}
