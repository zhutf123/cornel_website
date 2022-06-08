import route from '../../utils/route';

Page({
    data: {
        hasSearched: false,
        list: []
    },
    onLoad() {
    },
    onInput(e) {
        console.log(e.detail.value)
        this.inputValue = e.detail.value;
    },
    reset() {
        this.setData({
            list: []
        });
    },
    search(e) {
        console.log(e)
        this.hasSearched = true;
        this.setData({
            hasSearched: true
        });
    },
    jumpToRecm() {
        route.relaunch('recommend');
    },
    jumpToVideo(e) {
        const {id} = e.currentTarget.dataset;
        route.replace('videoDetail', {
            videoId: id
        });
    }
});
