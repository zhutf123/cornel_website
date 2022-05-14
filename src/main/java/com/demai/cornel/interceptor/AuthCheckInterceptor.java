package com.demai.cornel.interceptor;

import com.demai.cornel.annotation.Authority;
import com.demai.cornel.constant.ConfigProperties;
import com.demai.cornel.dao.UserInfoDao;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.model.UserInfo;
import com.demai.cornel.service.impl.UrlAclServiceImpl;
import com.demai.cornel.util.CookieAuthUtils;
import com.demai.cornel.util.StringDealUtils;
import com.demai.cornel.util.json.JsonUtil;
import com.hp.gagawa.java.elements.B;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * zhutf 2019
 */
@Slf4j @CustomInterceptor(order = 3, addPathPatterns = { "/admin/**", "/user/**" }, excludePathPatterns = { "/check.jsp",
        "/admin_web","/admin/login.json", "/user/login/json", "/user/login/json", "register.json" }) public class AuthCheckInterceptor
        implements HandlerInterceptor {

    @Resource private UrlAclServiceImpl urlAclService;
    @Resource private UserInfoDao userInfoDao;
    @Resource private ConfigProperties configProperties;

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();
        HandlerMethod method = (HandlerMethod) handler;
        Map<String, String[]> params = request.getParameterMap();
        String userId = UserHolder.getValue(CookieAuthUtils.KEY_USER_NAME);

        log.info("usern [{}] ,query path :{} ,params:{}", userId, url, JsonUtil.toJson(params));
        if (checkUrl(url)) {
            return true;
        }
        
        UserInfo userInfo = userInfoDao.getUserInfoByUserId(UserHolder.getValue(CookieAuthUtils.KEY_USER_NAME));
        if (userInfo == null) {
            log.info("url [{}] no user required ", url);
            return false;
        }
        UserHolder.add("uid",String.valueOf(userInfo.getId()));
        UserHolder.add("name",userInfo.getNickName());

        Authority methodAnnotation = method.getMethodAnnotation(Authority.class);
        if (methodAnnotation == null) {
            log.info("url [{}] No permission authentication required ", url);
            return true;
        }
        return urlAclService.checkUserUrlAcls(userId, url);
    }

    @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
    }

    @Override public void afterCompletion(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    private boolean checkUrl(String url){
        List<String> startWith = StringDealUtils.SPLITTER_COMMA.splitToList(configProperties.noLoginStartWith);
        List<String> fullPath = StringDealUtils.SPLITTER_COMMA.splitToList(configProperties.noLoginFullPath);
        for (int i = 0; i < startWith.size() ; i++) {
            if (url.startsWith(startWith.get(i))){
                return Boolean.TRUE;
            }
        }

        for (int i = 0; i < fullPath.size(); i++) {
            if (url.equals(fullPath.get(i))) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;

    }
}
