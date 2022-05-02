package com.demai.cornel.interceptor;

import com.demai.cornel.constant.ContextConsts;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.util.CookieAuthUtils;
import com.demai.cornel.util.CookieUtils;
import com.demai.cornel.util.StringUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.security.krb5.internal.AuthContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.demai.cornel.util.CookieAuthUtils.KEY_USER_NAME;

/**
 * Created by tfzhu on 2019/1/4.
 * 获取用户信息
 */
@Slf4j @CustomInterceptor(order = 2, addPathPatterns = { "/**" }, excludePathPatterns = { "/check.jsp",
        "/admin/login.json", "/user/login/json", "/user/login/json", "register.json" }) public class CookieInterceptor implements HandlerInterceptor {

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o)
            throws Exception {
        try {
            String cKey = CookieUtils.getCookieValue(request, CookieAuthUtils.COOKIE_ADMIN_USER);
            if (StringUtil.isBlank(cKey)) {
                log.info("check cookie is null please login");
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
