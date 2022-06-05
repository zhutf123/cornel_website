import { getStorage, setStorage } from "./storage";

let cookies = {};

export function initCookie() {
    return getStorage('cookies').then(data => {
        cookies = data;
    });
}

export function setCookie(key, value) {
    cookies[key] = value;
    setStorage('cookies', cookies);
}

export function getCookie(key) {
    return cookies[key];
}

export function getCookies(keys = []) {
    if (keys.length > 0) {
        return keys.reduce((res, key) => {
            return {
                ...res,
                [key]: cookies[key]
            };
        }, {});
    }
    return cookies;
}