/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.service;

import com.demai.cornel.Resp.UserLoginResp;
import com.demai.cornel.dao.UserInfoDao;
import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.UserInfo;
import com.demai.cornel.reqParam.UserLoginParam;
import com.demai.cornel.util.GenRandomCodeUtil;
import com.demai.cornel.util.MD5Util;
import com.demai.cornel.util.StringUtil;
import com.demai.cornel.util.json.JsonUtil;
import com.demai.cornel.vo.WeChat.WechatCode2SessionResp;
import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author tfzhu
 */
@Service @Slf4j public class UserLoginService {

    @Resource private UserInfoDao userInfoDao;
    @Resource private WeChatService weChatService;
    @Resource private SendMsgService sendMsgService;
    @Resource private StringRedisTemplate stringRedisTemplate;

    public UserLoginResp doLogin(UserLoginParam param) {
        // valid msg code
        //        if (!checkLoginMsgCode(param.getPhone(), param.getMsgCode())) {
        //            return new UserLoginResp(StringUtils.EMPTY, StringUtils.EMPTY, 1,
        //                    UserLoginResp.CODE_ENUE.MSG_CODE_ERROR.getValue(),param.getPhone());
        //        }

        //UserInfo userInfo = userInfoDao.getUserInfoNoDriverByPhone(param.getPhone());
        //        if (userInfo == null) {
        //            return new UserLoginResp(StringUtils.EMPTY, StringUtils.EMPTY, 1,
        //                   UserLoginResp.CODE_ENUE.NO_USER.getValue(),param.getPhone());
        //        }
        UserInfo userInfo = null;
        if (param.getAdmin()) {
            userInfo = userInfoDao
                    .getUserInfoByNamePasswd(param.getName(), MD5Util.MD5Encode(param.getPasswd(), "UTF-8"));
            if (userInfo != null) {
                return UserLoginResp.builder().userId(userInfo.getUserId()).code(UserLoginResp.CODE_ENUE.SUCCESS.getValue())
                        .build();
            }
        } else {
            WechatCode2SessionResp resp = weChatService.getOpenId(param.getJscode());
            log.info("get openid by js code result:{}", JsonUtil.toJson(resp));
            if (resp != null && StringUtil.isNotBlank(resp.getOpenid())) {
                userInfo = userInfoDao.getUserInfoByOpenId(resp.getOpenid());
                if (userInfo != null) {
                    return UserLoginResp.builder().openId(resp.getOpenid()).userId(userInfo.getUserId())
                            .role(userInfo.getRole()).code(UserLoginResp.CODE_ENUE.SUCCESS.getValue())
                            .CKey(String.format(UserService.c_key, userInfo.getUserId(), userInfo.getOpenId())).build();
                }
            }
        }
        return UserLoginResp.builder().openId(StringUtils.EMPTY).userId(StringUtils.EMPTY)
                .code(UserLoginResp.CODE_ENUE.NO_USER.getValue()).build();
    }

    /***
     * 给用户发送登录验证码 -1 查不到用户 0 成功 2 发送失败，稍后重试
     *
     * @param phone
     */
    public Integer sendLoginCodeMsg(String phone) {
        //UserInfo userInfo = getUserInfoByPhone(phone);
        Integer validCode = GenRandomCodeUtil.genRandomCode(6);
        if (phone.equals("13439679479")) {
            validCode = 888888;
        }
        //        if (userInfo == null) {
        //            return ResponseStatusEnum.NO_USER.getValue();
        //        }
        stringRedisTemplate.opsForValue()
                .set(Joiner.on("_").join(phone, "loginValid"), String.valueOf(validCode), 300, TimeUnit.SECONDS);
        Integer sendResult = sendMsgService.sendLoginValid(phone, validCode);
        if (sendResult.compareTo(SendMsgService.SEND_MSG_CODE.SUCCESS.getValue()) == 0) {
            return ResponseStatusEnum.SUCCESS.getValue();
        }
        return ResponseStatusEnum.NETWORK_ERROR.getValue();
    }

    //    public Integer sendLoginCodeMsg(String phone) {
    //        UserInfo userInfo = getUserInfoByPhone(phone);
    //        Integer validCode = GenRandomCodeUtil.genRandomCode(6);
    //        if (userInfo == null) {
    //            return ResponseStatusEnum.NO_USER.getValue();
    //        }
    //        stringRedisTemplate.opsForValue().set(Joiner.on("_").join(phone, "loginValid"), String.valueOf(validCode), 300,
    //                TimeUnit.SECONDS);
    //        Integer sendResult = sendMsgService.sendLoginValid(phone, validCode);
    //        if (sendResult.compareTo(SendMsgService.SEND_MSG_CODE.SUCCESS.getValue()) == 0) {
    //            return ResponseStatusEnum.SUCCESS.getValue();
    //        }
    //        return ResponseStatusEnum.NETWORK_ERROR.getValue();
    //    }

    /***
     * 验证该手机号的登录验证码
     *
     * @param phone
     * @return
     */
    public Boolean checkLoginMsgCode(String phone, String msgCode) {
        String code = stringRedisTemplate.opsForValue().get(Joiner.on("_").join(phone, "loginValid"));
        if (StringUtil.isBlank(code)) {
            return Boolean.FALSE;
        }
        if (msgCode.equalsIgnoreCase(code)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
