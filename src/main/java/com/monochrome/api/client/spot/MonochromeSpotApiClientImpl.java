package com.monochrome.api.client.spot;

import com.monochrome.api.client.HttpProxyProperties;
import com.monochrome.api.client.MonochromeOkHttpClientBuilder;
import com.monochrome.api.dto.CommonResponse;

import com.monochrome.api.dto.nft.NftDepositRequest;
import com.monochrome.api.dto.nft.NftWithdrawRequest;
import com.monochrome.api.dto.spot.NetworthUpdateRequest;
import com.monochrome.api.dto.spot.SpotPostOrderRequest;
import com.monochrome.api.dto.spot.SpotUpdateOrderRequest;
import com.monochrome.api.interceptor.MonochromeSpotOkHttpInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * @author zhouzhuang
 * @create 2023/9/20 12:18
 */
public class MonochromeSpotApiClientImpl implements MonochromeSpotApiClient{

    private final static String API_URL = "https://sapi.monochromedev.click";

    private final MonochromeSpotApiService service;

    public MonochromeSpotApiClientImpl(HttpProxyProperties proxyProperties){
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .client(MonochromeOkHttpClientBuilder.build(proxyProperties,new MonochromeSpotOkHttpInterceptor()))
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
        service = retrofit.create(MonochromeSpotApiService.class);
    }

    @Override
    public CommonResponse postOrder(SpotPostOrderRequest request) {
        return executeSync(service.postOrder(request));
    }

    @Override
    public CommonResponse getOrder(Long id) {
        return executeSync(service.getOrder(id));
    }

    @Override
    public CommonResponse queryOrder(Long orderId) {
        return executeSync(service.queryOrder(orderId));
    }

    @Override
    public CommonResponse delOrder(Long id) {
        return executeSync(service.delOrder(id));
    }

    @Override
    public CommonResponse updateOrder(Long id, SpotUpdateOrderRequest request) {
        return executeSync(service.updateOrder(id,request));
    }

    @Override
    public CommonResponse netWorth(NetworthUpdateRequest request) {
        return executeSync(service.netWorth(request));
    }

    @Override
    public CommonResponse nftDeposit(NftDepositRequest request){
        return executeSync(service.nftDeposit(request));
    }

    @Override
    public CommonResponse nftWithdraw(NftWithdrawRequest request){
        return executeSync(service.nftWithdraw(request));
    }

    @Override
    public CommonResponse nftHistoryOrder(Integer type, Integer status, String currency, Long startTime, Long endTime, Integer page, Integer size) {
        return executeSync(service.nftHistoryOrder(type, status, currency, startTime, endTime, page, size));
    }

    public CommonResponse executeSync(Call<CommonResponse> call) {
        try {
            Response<CommonResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }else {
                System.err.println(String.format("failed to call interface:%s,%s",response.code(),response.toString()));
                return CommonResponse.failure(response.toString());
            }
        }catch (Exception e){
            System.err.println("call interface exception:"+e);
            throw new RuntimeException(e);
        }
    }
}
