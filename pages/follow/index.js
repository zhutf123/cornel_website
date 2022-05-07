const app = getApp()

Page({
    data: {
        safeareaTop: app.globalData.safeareaTop
    },
    onLoad() {
    },
    clickWatched(e) {
        console.log(e.target);
    }
})
