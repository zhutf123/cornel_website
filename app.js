import { initCookie } from "./utils/cookies";
import { getUserInfo, login } from "./utils/user";

// app.js
App({
    onLaunch() {
        this.setNavBarInfo();
        initCookie();
        login();
        getUserInfo();
    },
    globalData: {
        userInfo: null,
        safeareaTop: 0
    },
    setNavBarInfo() {
        const menuButtonInfo = wx.getMenuButtonBoundingClientRect();
        console.log(menuButtonInfo)
        this.globalData.safeareaTop = menuButtonInfo.top;
    }
})
