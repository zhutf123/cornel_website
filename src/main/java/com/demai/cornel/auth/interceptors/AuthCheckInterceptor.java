package com.demai.cornel.auth.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demai.cornel.interceptor.CustomInterceptor;

/**
 * zhutf 2019
 */
@Slf4j
@CustomInterceptor(order = 3, addPathPatterns = {"/**"})
public class AuthCheckInterceptor implements HandlerInterceptor {

//    @Autowired
//    private UrlAclServiceImpl urlAclService;


//    @Autowired
//    private UserInfoDao userInfoDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String url = request.getRequestURI();
//        HandlerMethod method = (HandlerMethod) handler;
//        Map<String, String[]> params = request.getParameterMap();
//        String userId = UserHolder.getValue(CookieAuthUtils.KEY_USER_NAME);
//
//        log.info("usern [{}] ,query path :{} ,params:{}",userId,url, JsonUtil.toJson(params));
//        if(url.equalsIgnoreCase("/user/sendCode.json") || url.equalsIgnoreCase("/user/login.json")){
//            return true;
//        }
//        UserInfo userInfo = userInfoDao.getUserInfoByUserId(UserHolder.getValue(CookieAuthUtils.KEY_USER_NAME));
//        if(userInfo==null){
//            return false;
//        }
//        Authority methodAnnotation = method.getMethodAnnotation(Authority.class);
//        if (methodAnnotation == null) {
//            log.info("url [{}] No permission authentication required ", url);
//            return true;
//        }
//        return urlAclService.checkUserUrlAcls(userId, url);
        return true;
    }


    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
