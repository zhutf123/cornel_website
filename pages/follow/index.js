import route from '../../utils/route';
import { getWatchedHistory, getFollowList, removeFollow } from '../../utils/apis';

const app = getApp()

Page({
    data: {
        safeareaTop: app.globalData.safeareaTop,
        dialogConfig: {
            title: "停止订阅",
            content: "错失更多本剧精彩内容",
            cancelText: "再想想",
            confirmText: "停止订阅",
            highlight: 'left'
        },
        showDialog: false,
        watchedList: [],
        watchedListTotal: 0,
        followedList: []
    },
    onLoad() {
        this.initPage();
    },
    initPage() {
        Promise.all([getWatchedHistory(), getFollowList()])
            .then(([res1, res2]) => {
                const {data: watchedList = [], allNum: watchedListTotal = 0} = res1;
                const {data: followedList = []} = res2;
                this.setData({
                    watchedList,
                    watchedListTotal,
                    followedList
                });
            });
    },
    clickWatched() {
        route.go('watchedHistory');
    },
    jumpToVideo(e) {
        const {id} = e.target.dataset;
    },
    changeFollow() {
        removeFollow(this.followId).then(res => {
            console.log(res);
            if (res.status === 0) {
                this.setData({
                    followedList: this.data.followedList.filter(item => item.videoId !== this.followId)
                });
                this.closeDialog();
            }
        });
    },
    openDialog(e) {
        const {id} = e.target.dataset;
        this.followId = id;
        this.setData({
            showDialog: true
        });
    },
    closeDialog() {
        this.setData({
            showDialog: false
        });
    }
});
