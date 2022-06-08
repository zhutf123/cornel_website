import route from '../../utils/route';
// 获取应用实例
const app = getApp()

Page({
    data: {
        userInfo: {}
    },
    onLoad() {
    },
    jumpToFollow() {
        route.relaunch('follow');
    }
})
