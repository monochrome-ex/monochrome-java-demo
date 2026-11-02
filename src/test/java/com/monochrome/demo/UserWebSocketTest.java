package com.monochrome.demo;

import com.google.gson.Gson;
import com.monochrome.api.client.MonochromeWebSocketClient;
import com.monochrome.api.dto.websocket.Method;
import com.monochrome.api.dto.websocket.RequestMessage;
import com.monochrome.api.dto.websocket.Topic;
import org.java_websocket.client.WebSocketClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouzhuang
 * @create 2023/12/12 16:36
 */
public class UserWebSocketTest {
    Gson gson = new Gson();
    WebSocketClient webSocketClient = null;
    @Before
    public void init(){
        try {
            webSocketClient = new MonochromeWebSocketClient("wss://fstream.monochromedev.click/ws/market");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert webSocketClient != null;
        webSocketClient.connect();
        while (!webSocketClient.isOpen()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @After
    public void after(){
        while (true){
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("send msg ping");
            webSocketClient.send("ping");
        }
    }

    @Test
    public void subscribeAll(){
        String listenKey=System.getenv().getOrDefault("MONOCHROME_LISTEN_KEY", "");
        List<String> params = new ArrayList<>();
        params.add("kline@btc_usdt,1m");

        RequestMessage requestMessage = RequestMessage.builder().id("001").method(Method.SUBSCRIBE.name()).params(params).build();
        webSocketClient.send(gson.toJson(requestMessage));
    }


}
