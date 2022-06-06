import route from '../../utils/route';
import { getWatchedHistory, getFollowList, removeFollow, getTabs } from '../../utils/apis';

const app = getApp()

Page({
    data: {
        safeareaTop: app.globalData.safeareaTop,
        activeIndex: 1,
        list: {}
    },
    onLoad() {
        this.initPage();
    },
    initPage() {
        getTabs().then(res => {
            if (res.data) {
                this.setData({
                    tabList: res.data
                });
            }
        });
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
