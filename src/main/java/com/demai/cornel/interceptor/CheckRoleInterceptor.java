/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.interceptor;

import com.demai.cornel.annotation.CheckUserRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create By zhutf 19-11-9 上午10:37
 * @author tfzhu
 */
@Slf4j
@Component
@CustomInterceptor(addPathPatterns = { "/**" }, excludePathPatterns = { "/check.jsp",
        "/admin_web","/admin/login.json", "/user/login/json", "/user/login/json", "register.json" })
public class CheckRoleInterceptor implements HandlerInterceptor {

    //@Resource private UserRoleInfoDao userRoleInfoDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod method = (HandlerMethod) handler;
        CheckUserRole accessControl = method.getMethodAnnotation(CheckUserRole.class);
        if (accessControl == null) {
            return Boolean.TRUE;
        }
        String url = request.getRequestURI();
        String checkRole = accessControl.checkRole();
        if (StringUtils.isBlank(checkRole)) {
            return Boolean.FALSE;
        }
        if (log.isDebugEnabled()) {
            log.debug("url:{} method：{} checkRole:{}", url, method.getMethod().getName(), checkRole);
        }

//        List<UserRoleInfo> userRoleInfos = userRoleInfoDao.getRolesByUserId(CookieAuthUtils.getCurrentUser());
//        if (CollectionUtils.isEmpty(userRoleInfos)) {
//            return Boolean.FALSE;
//        }
//        List<String> roleIds = userRoleInfos.stream().map(role ->role.getRoleId()).collect(Collectors.toList());
        Boolean result = Boolean.FALSE;

//        switch (checkRole){
//            case "bu_op":
//                if (roleIds.contains("4")){
//                    result = Boolean.TRUE;
//                }
//                break;
//
//            case "fin_op":
//                if (roleIds.contains("5")){
//                    result = Boolean.TRUE;
//                }
//        }
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object o, Exception e) throws Exception {
    }


}
