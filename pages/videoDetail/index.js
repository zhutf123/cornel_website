import { addFollow, getVideoInfo } from '../../utils/apis';
import { Toast } from '../../utils/util';

const app = getApp()

Page({
    data: {
        safeareaTop: app.globalData.safeareaTop,
        dialogConfig: {
            title: "加入追剧",
            content: "喜欢本剧就加入追剧吧",
            cancelText: "不用了",
            confirmText: "加入追剧"
        },
        showDialog: false,
        showSelectPanel: false,
        showUnlockPanel: false,
        showVipPanel: false,
        videoInfo: {},
        playInfo: {},
        playIndex: 0
    },
    onLoad(query) {
        this.initPage(query);
        wx.setNavigationBarColor({
            frontColor: '#ffffff'
        });
    },
    initPage(query) {
        const {videoId, teleplayId} = query;
        if (!videoId && !teleplayId) {
            return;
        }
        let params = videoId ? {
            videoId
        } : {
            teleplayId
        };
        getVideoInfo(params).then(res => {
            if (res.data) {
                this.setData({
                    videoInfo: res.data,
                    playInfo: res.data.videoList[0]
                });
            }
        });
    },
    onSwiperChange(e) {
        const {current} = e.detail;
        this.setData({
            playIndex: current,
            playInfo: this.data.videoInfo.videoList[current]
        });
    },
    onVideoEnd() {
        const {videoList} = this.data.videoInfo;
        const index = videoList.findIndex(item => item.id === this.data.playInfo.id);
        if (index > -1 && videoList[index + 1]) {
            this.play(videoList[index + 1]);
        }
    },
    changeVideo(evt) {
        const {index} = evt.currentTarget.dataset;
        const playInfo = this.data.videoInfo.videoList[index];

        if (playInfo.id === this.data.playInfo.id) {
            return;
        }
        this.play(playInfo);
        this.toggleSelectPanel();
    },
    play(playInfo) {
        if (playInfo.lock) {
            return;
        }
        const playIndex = this.data.videoInfo.videoList.findIndex(item => item.id === playInfo.id);
        this.setData({
            playInfo,
            playIndex
        });
    },

    // 开通vip操作
    toggleVipPanel() {
        this.setData({
            showVipPanel: !this.data.showVipPanel
        });
    },

    // 解锁剧集
    toggleUnlockPanel() {
        this.setData({
            showUnlockPanel: !this.data.showUnlockPanel
        });
    },
    unlockVideo(playInfo) {

    },
    openVipPanel() {
        this.setData({
            showVipPanel: !this.data.showVipPanel
        });
    },

    // 追剧
    toggleDialog() {
        this.setData({
            showDialog: !this.data.showDialog
        });
    },
    addFollow() {
        addFollow(this.data.playInfo.id).then(res => {
            if (res.status === 0) {
                this.closeDialog();
                Toast.show(res.msg || '追剧成功');
            } else {
                Toast.show(res.msg || '追剧失败，请稍候再试');
            }
        });
    },

    // 选集弹层
    toggleSelectPanel() {
        this.setData({
            showSelectPanel: !this.data.showSelectPanel
        });
    },

    // 侧边栏操作
    shareVideo() {
        console.log(3333)
    },
    likeVideo() {
        wx.getUserProfile({
            desc: '用于订阅更新消息',
            success: res => {
                const userInfo = res.userInfo;
                console.log(333, res);
            }
        });
    },
    saveVideo() {
        wx.getUserProfile({
            desc: '用于订阅更新消息',
            success: res => {
                const userInfo = res.userInfo;
                console.log(333, res);
            }
        });
    }
});
