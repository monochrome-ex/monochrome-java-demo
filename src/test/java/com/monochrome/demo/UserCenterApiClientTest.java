package com.monochrome.demo;

import com.monochrome.api.client.user.MonochromeUserCenterApiClientImpl;
import com.monochrome.api.dto.CommonResponse;
import org.junit.Test;

import java.util.List;

/**
 * SPOT Business
 * @author zhouzhuang
 * @create 2023/9/20 16:15
 */
public class UserCenterApiClientTest {

    MonochromeUserCenterApiClientImpl monochromeUserCenterApiClient = new MonochromeUserCenterApiClientImpl(null);

    @Test
    public void getUserStatistics() {
        CommonResponse commonResponse = monochromeUserCenterApiClient.getUserStatistics("2795815175054","2023-05-01","2023-05-02");
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void getUserInfo() {
        CommonResponse commonResponse = monochromeUserCenterApiClient.getUserInfo(9920622693124L);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void getBatchUserInfo(){
        CommonResponse commonResponse = monochromeUserCenterApiClient.getBatchUserInfo(List.of(6975805522411L),1L,"NEXT", 20);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void getUserDetail(){
        CommonResponse commonResponse = monochromeUserCenterApiClient.getUserDetail(1735574400000L,1735574400000L,"123",1,1L,"NEXT", 20);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void getUserState() {
        CommonResponse commonResponse = monochromeUserCenterApiClient.getUserState(6975805522411L);
        System.out.println("result:"+commonResponse);
    }
}
