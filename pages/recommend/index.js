import route from '../../utils/route';
import { getTabs, getBannerList, getTeleplayList, addFollow } from '../../utils/apis';
import { getWindowInfo, handleWaterfall } from '../../utils/util';

const {safeareaTop} = getApp().globalData;

Page({
    data: {
        safeareaTop,
        tabContHeight: getWindowInfo().windowHeight - safeareaTop - 44 - 44,
        activeIndex: 0,
        tabList: [],
        list: {},
        banners: []
    },
    onLoad() {
        this.initPage();
        this.listPage = [0];
    },
    initPage() {
        getTabs().then(res => {
            if (res.data) {
                this.setData({
                    tabList: [{name: '推荐'}].concat(res.data)
                });
            }
        });
        this.getRecommendData();
    },
    onTabChange(e) {
        console.log(e);
        const {index} = e.detail;
        this.setData({
            activeIndex: index,
            list: {}
        });
        this.isLoading = false;
        this.pageNum = 1;

        this.getTeleplayList(index);
    },
    getTeleplayList(index) {
        const curIndex = index === undefined ? this.data.activeIndex : index;
        const {id: channel} = this.data.tabList[curIndex];
        if (this.isLoading) {
            return;
        }
        this.isLoading = true;
        getTeleplayList(channel, this.pageNum).then(res => {
            if (res.data) {
                this.setData({
                    list: handleWaterfall(this.data.list, res.data),
                    activeIndex: index
                });
                this.isLoading = res.data.length < 10;
            }
        });
    },

    // 推荐tab的数据
    getRecommendData() {
        getBannerList().then(res => {
            if (res.data) {
                this.setData({
                    banners: res.data
                });
            }
        });
    },
    onScroll(e) {
        const {scrollTop, scrollHeight} = e.detail;

        if (this.data.tabContHeight + scrollTop >= scrollHeight - 200) {
            this.getTeleplayList();
        }
    },

    jumpToVideo(e) {
        const {id} = e.target.dataset;
        route.go('videoDetail', {
            videoId: id
        });
    },

    changeFollow() {
        addFollow(this.followId).then(res => {
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
