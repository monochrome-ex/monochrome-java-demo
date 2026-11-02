package com.monochrome.api.client.user;

import com.monochrome.api.dto.CommonResponse;

import java.util.List;


/**
 * @author zhouzhuang
 * @create 2023/9/20 11:48
 */
public interface MonochromeUserCenterApiClient {

    CommonResponse getUserStatistics(String uid, String startTime, String endTime) ;

    CommonResponse getUserInfo(Long uid);

    CommonResponse getBatchUserInfo(List<Long> uidList, Long fromId, String direction, Integer limit);

    CommonResponse getUserDetail(Long startTime, Long endTime, String inviteCode, Integer type, Long fromId, String direction, Integer limit);

    CommonResponse getUserState(Long uid);
}
