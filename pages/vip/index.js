import { getWatchedHistory } from '../../utils/apis';
import route from '../../utils/route';
// 获取应用实例
const app = getApp()

Page({
    data: {
        userInfo: {},
        watchedListTotal: 0,
        watchedList: []
    },
    onLoad() {
        this.getMyWatched();
    },
    onGetVip() {

    },
    getMyWatched() {
        getWatchedHistory().then(res => {
            const {data: watchedList = [], allNum: watchedListTotal = 0} = res;
            this.setData({
                watchedList,
                watchedListTotal
            });
        });
    },
    jumpToConsult() {
        console.log('to consult');
    },
    jumpToFollow() {
        route.relaunch('follow');
    }
});
