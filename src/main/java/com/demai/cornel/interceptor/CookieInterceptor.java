package com.demai.cornel.interceptor;

import com.demai.cornel.constant.ContextConsts;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.util.CookieAuthUtils;
import com.demai.cornel.util.CookieUtils;
import com.demai.cornel.util.StringUtil;
import com.demai.cornel.util.json.JsonUtil;
import com.demai.cornel.vo.JsonResult;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.demai.cornel.util.CookieAuthUtils.KEY_USER_NAME;
import static com.demai.cornel.vo.JsonResult.SHOULD_LOGIN_CODE;

/**
 * Created by tfzhu on 2019/1/4.
 * 获取用户信息
 */
@Slf4j @CustomInterceptor(order = 2, addPathPatterns = { "/admin/**" }, excludePathPatterns = { "/check.jsp",
        "/admin_web/","/admin_web","/admin/login.json", "/user/login/json", "/user/login/json", "register.json" }) public class CookieInterceptor implements HandlerInterceptor {

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o)
            throws Exception {
        try {
            String cKey = CookieUtils.getCookieValue(request, CookieAuthUtils.COOKIE_ADMIN_USER);
            if (StringUtil.isBlank(cKey)) {
                log.info("check cookie is null please login");
                response.getWriter()
                        .println(JsonUtil.toJson(new JsonResult(Boolean.TRUE, SHOULD_LOGIN_CODE, "请登录")));
                return false;
            }
            if (!cKey.contains("u=")) {
                log.info("admin user info");
                Map<String, String> defaultUserMap = Maps.newHashMap();
                defaultUserMap.put(KEY_USER_NAME, cKey);
                MDC.put(ContextConsts.MDC_USER, cKey);
                UserHolder.set(defaultUserMap);
            } else {
                Map<String, String> userInfoMap = CookieAuthUtils.getUserFromCKey(cKey);
                if (MapUtils.isNotEmpty(userInfoMap)) {
                    UserHolder.set(userInfoMap);
                    MDC.put(ContextConsts.MDC_USER, userInfoMap.get(KEY_USER_NAME));
                }
            }
        } catch (Exception e) {
            log.error("parse cookie info occur exception", e);
        }
        return true;
    }

    @Override public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override public void afterCompletion(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        UserHolder.remove();
    }

}
