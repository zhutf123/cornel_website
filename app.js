// app.js
App({
    onLaunch() {
        this.setNavBarInfo();
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
