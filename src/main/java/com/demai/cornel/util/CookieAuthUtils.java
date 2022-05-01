package com.demai.cornel.util;

import com.demai.cornel.holder.UserHolder;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by tfzhu on 2019/1/4.
 */
@Slf4j
public class CookieAuthUtils {

    public static final String KEY_USER_NAME = "u"; //userId

    public static final String COOKIE_ADMIN_USER = "uid";

    public static final String KEY_USER_DOMAIN = "o"; //openid

    public static String c_key = "u=%s&o=%s";

    public static final String CKEY_SPLIT = "&";

    public static final String CKEY_KEY_JOINER = "=";


    /**
     * u=lilulucas.li&o=aHHYA8SADA87DSVADS&t=13551151111
     *
     * @param cKey
     * @return
     */
    public static Map<String, String> getUserFromCKey(String cKey) {
        String decodeCkey = new String(Base64Utils.decode(cKey));
        log.debug("decode cKey is {}", decodeCkey);
        if (StringUtils.isBlank(decodeCkey)){
            return Maps.newHashMap();
        }
        return Splitter.on(CKEY_SPLIT).trimResults().withKeyValueSeparator(CKEY_KEY_JOINER).split(decodeCkey);
    }

    public static String getCurrentUser() {
        return UserHolder.getValue(KEY_USER_NAME);
    }

}
