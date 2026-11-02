package com.monochrome.api.client.future;

import com.monochrome.api.client.HttpProxyProperties;
import com.monochrome.api.client.MonochromeOkHttpClientBuilder;
import com.monochrome.api.interceptor.MonochromeFutureOkHttpInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * B-BASE Future
 * @author zhouzhuang
 * @create 2023/9/20 12:18
 */
public class MonochromeBFutureApiClientImpl extends AbstractMonochromeFutureApiClient{

    private final static String API_URL = "https://dapi.monochromedev.click";

    private final MonochromeFutureApiService service;

    public MonochromeBFutureApiClientImpl(HttpProxyProperties proxyProperties){
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .client(MonochromeOkHttpClientBuilder.build(proxyProperties,new MonochromeFutureOkHttpInterceptor()))
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
        service = retrofit.create(MonochromeFutureApiService.class);
    }

    @Override
    MonochromeFutureApiService getService() {
        return service;
    }
}
