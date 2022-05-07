import route from '../../utils/route';

const app = getApp()

Page({
    data: {
        safeareaTop: app.globalData.safeareaTop
    },
    onLoad() {
    },
    clickWatched(e) {
        route.go('watchedHistory');
    }
})
