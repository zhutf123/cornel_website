package com.demai.cornel.service;

import com.demai.cornel.Resp.UserAddUserResp;
import com.demai.cornel.constant.ConfigProperties;
import com.demai.cornel.dao.UserInfoDao;
import com.demai.cornel.dao.UserPayInfoDao;
import com.demai.cornel.dao.UserRoleInfoDao;
import com.demai.cornel.model.RoleInfo;
import com.demai.cornel.model.UserInfo;
import com.demai.cornel.model.UserPayInfo;
import com.demai.cornel.model.UserRoleInfo;
import com.demai.cornel.reqParam.QueryUserParam;
import com.demai.cornel.reqParam.UserAddParam;
import com.demai.cornel.reqParam.UserRegisterParam;
import com.demai.cornel.util.CookieAuthUtils;
import com.demai.cornel.util.IDUtils;
import com.demai.cornel.util.JacksonUtils;
import com.demai.cornel.util.PhoneUtil;
import com.demai.cornel.util.StringUtil;
import com.demai.cornel.util.json.JsonUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.demai.cornel.util.CookieAuthUtils.c_key;

/**
 * @Author binz.zhang
 * @Date: 2020-01-07    13:11
 */
@Service @Slf4j public class UserService {

    @Resource private UserInfoDao userInfoDao;
    @Resource private UserRoleInfoDao userRoleInfoDao;
    @Resource private ConfigProperties configProperties;
    @Resource private UserPayInfoDao userPayInfoDao;

    //todo 这一块更新需要补全
    public UserAddUserResp updateUserInfo(UserAddParam userAddReq) {
        log.debug("update user info [{}]", JacksonUtils.obj2String(userAddReq));
        if(!Strings.isNullOrEmpty(userAddReq.getMobile()) && !PhoneUtil.isPhone(userAddReq.getMobile())){
            log.debug("update user fail due to tel illegal tel is [{}]",userAddReq.getMobile());
            return UserAddUserResp.builder().status(UserAddUserResp.CODE_ENUE.TEL_ERROR.getValue()).build();
        }
        if(!Strings.isNullOrEmpty(userAddReq.getIdCard()) && userAddReq.getIdCard().trim().length()!=18){
            log.debug("update user fail due to idcard illegal idcard is [{}]",userAddReq.getIdCard());
            return UserAddUserResp.builder().status(UserAddUserResp.CODE_ENUE.IDCARD_ERROR.getValue()).build();
        }
        UserInfo updateUserInfo = new UserInfo();
        UserInfo currentUserInfo = userInfoDao.getUserInfoByUserId(CookieAuthUtils.getCurrentUser());
        updateUserInfo.setName(userAddReq.getUserName());
        updateUserInfo.setIdType(1);
        updateUserInfo.setIdCard(userAddReq.getIdCard());
        updateUserInfo.setUserId(CookieAuthUtils.getCurrentUser());
        if (StringUtil.isBlank(currentUserInfo.getMobile()) || !currentUserInfo
                .getMobile().contains(userAddReq.getMobile())) {
            log.debug("update user info reset mobile ");
            updateUserInfo.setMobile(userAddReq.getMobile());
        }
        int optRes = userInfoDao.update(updateUserInfo);
        UserAddUserResp userAddUserResp = new UserAddUserResp();
        BeanUtils.copyProperties(userAddReq, userAddUserResp);
        if (optRes == 0) {
            log.error("update user info fail due to update db fail");
            userAddUserResp.setStatus(UserAddUserResp.CODE_ENUE.NETWORK_ERROR.getValue());
            return userAddUserResp;
        }
        userAddUserResp.setUserId(CookieAuthUtils.getCurrentUser());
        userAddUserResp.setStatus(UserAddUserResp.CODE_ENUE.SUCCESS.getValue());
        return userAddUserResp;

    }

    /***
     * 用户注册
     * @param userRegisterParam
     * @return
     */
    public String userRegister(UserRegisterParam userRegisterParam) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(IDUtils.getUUID());
        userInfo.setNickName(userRegisterParam.getNickName());
        userInfo.setHeadImg(userRegisterParam.getHeadImg());
        userInfo.setMobile(userRegisterParam.getMobile());
        userInfo.setOpenId(userRegisterParam.getOpenId());
        userInfo.setRole(Integer.parseInt(configProperties.userRole));
        userInfoDao.save(userInfo);
        return String.format(c_key, userInfo.getUserId(), userInfo.getOpenId());
    }

    public UserInfo getUserInfoResp(){
        UserInfo userInfo = userInfoDao.getUserInfoByUserId(CookieAuthUtils.getCurrentUser());
        return userInfo;
    }

    /**
     * 获取当前用户的角色id
     *
     * @return
     */
    public List<String> getUserRoleId(String userId) {
        List<UserRoleInfo> userRoleInfos = userRoleInfoDao.getRolesByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleInfos)) {
            return Lists.newArrayList();
        }
        return userRoleInfos.stream().map(role -> role.getRoleId()).collect(Collectors.toList());
    }


    /**
     * 获取当前用户的角色id
     * @param userId 用户id
     * @param type_enum 需要有权限的操作人
     * @return
     */
    public Boolean checkUserRole(String userId, RoleInfo.ROLE_TYPE_ENUM type_enum) {
        List<String> routeId = getUserRoleId(userId);
        Boolean result = Boolean.FALSE;
        log.info("check user role :{} -{}",userId, JsonUtil.toJson(routeId));
        if (CollectionUtils.isNotEmpty(routeId)){
            switch (type_enum){
            case BUS_OP:
                if (routeId.contains(RoleInfo.ROLE_TYPE_ENUM.BUS_OP.getRouteId())){
                    result = Boolean.TRUE;
                }
                break;
            case FIN_OP:
                if (routeId.contains(RoleInfo.ROLE_TYPE_ENUM.FIN_OP.getRouteId())){
                    result = Boolean.TRUE;
                }
                break;
            }
        }
        return result;
    }

    /***
     * 获取用户支付list
     * @param userId
     * @return
     */
    public List<UserPayInfo> getUserPayInfoListByUserId(Long userId) {
        List<UserPayInfo> userPayInfos = userPayInfoDao.getUserPayInfoList(userId);
        return userPayInfos;
    }

    /***
     * 获取用户list
     * @param param
     * @return
     */
    public List<UserInfo> getAllUserInfoList(QueryUserParam param){
        List<UserInfo> userInfos =  userInfoDao.getAllUserInfoList(param);
        if (CollectionUtils.isNotEmpty(userInfos)){
            userInfos.stream().forEach(user ->{
                List<UserPayInfo> userPayInfos = getUserPayInfoListByUserId(user.getId());
                final BigDecimal[] money = { new BigDecimal(0) };
                if (CollectionUtils.isNotEmpty(userPayInfos)) {
                    userPayInfos.stream().forEach(u -> money[0] = money[0].add(u.getMoney()));
                    user.setAllPayMoney(new BigDecimal(200));
                }
                user.setAllPayMoney(money[0]);

            });
        }
        return userInfos;
    }

    public Integer getAllUserInfoNum(QueryUserParam param){
        return userInfoDao.getAllUserInfoNum(param);
    }

}
