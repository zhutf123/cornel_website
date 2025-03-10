package com.demai.cornel.controller;

import com.demai.cornel.annotation.AccessControl;
import com.demai.cornel.constant.ConfigProperties;
import com.demai.cornel.service.WeChatService;
import com.demai.cornel.util.AliStaticSourceUtil;
import com.demai.cornel.util.MD5Util;
import com.demai.cornel.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
@Slf4j
public class DomeController {

    @Resource
    private ConfigProperties configProperties;
    @Resource
    private WeChatService weChatService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/getSourceUrl", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult demo(String sourceId,Integer type) {
        if (type == 0){
           return JsonResult.success(AliStaticSourceUtil.getVideoUrl(sourceId));
        }else{
            return JsonResult.success(AliStaticSourceUtil.getImageUrl(sourceId));
        }
    }

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult demo() {
        return JsonResult.success(configProperties.weChatCode2SessionPath);
    }

    @RequestMapping(value = "/demo_get", method = RequestMethod.GET)
    @ResponseBody
    @AccessControl(value = "10_5")
    public JsonResult dealGet() {
        weChatService.getOpenId("xx");
        return JsonResult.success("");
    }

    @RequestMapping(value = "/demo_redis", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult dealRedis(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return JsonResult.success("");
    }

    @RequestMapping(value = "/genPasswd", method = RequestMethod.GET)
    @ResponseBody public JsonResult genPasswd(String key) {
        return JsonResult.success(MD5Util.MD5Encode(key, "UTF-8"));
    }

//    @RequestMapping(value = "/confirm.json", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResult deliveryConfirm(@RequestBody GetOrderInfoReq param) {
//        try {
//            String curUser = Optional.ofNullable(UserHolder.getValue(CookieAuthUtils.KEY_USER_NAME)).orElse("UNKNOW_");
//            OperationOrderResp result = deliveryTaskService.deliveryStart(curUser, param.getOrderId());
//            if (log.isDebugEnabled()) {
//                log.debug("delivery task start user:{} result:{}", curUser, JsonUtil.toJson(result));
//            }
//            return JsonResult.success(result);
//        }catch (Exception e){
//            log.error("delivery task start exception ！{}", e);
//        }
//        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
//    }

}
