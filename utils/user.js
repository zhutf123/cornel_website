import { getStorage, setStorage } from "./storage";

let userInfo;

export function initUserInfo() {
    return getStorage('userInfo').then(data => {
        userInfo = data;
    });
}

export function getUserInfo() {
    if (userInfo) {
        return Promise.resolve(userInfo);
    }
    return new Promise(resolve => {
        wx.getUserInfo({
            success: res => {
                /*
                    var userInfo = res.userInfo
                    var nickName = userInfo.nickName
                    var avatarUrl = userInfo.avatarUrl
                    var gender = userInfo.gender //性别 0：未知、1：男、2：女
                    var province = userInfo.province
                    var city = userInfo.city
                    var country = userInfo.country
                */
                userInfo = res.userInfo;
                resolve(userInfo);
                setStorage('userInfo', userInfo);
            }
        });
    });
};

export function login() {
    return new Promise(resolve => {
        wx.login({
            success: res => {
                resolve();
            }
        });
    });
}

export function checkLogin() {

}