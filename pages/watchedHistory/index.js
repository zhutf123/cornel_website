const app = getApp()

import {getWatchedHistory} from '../../utils/apis';
import { getWindowInfo } from '../../utils/util';

Page({
    data: {
        pageNum: 1,
        isLoading: false,
        list: [],
        safeareaTop: app.globalData.safeareaTop
    },
    onLoad() {
        this.loadData();
        this.windowHeight = getWindowInfo().windowHeight;
    },
    loadData() {
        if (this.isLoading) {
            return;
        }
        this.isLoading = true;
        getWatchedHistory(this.pageNum)
            .then(res => {
                const {data = []} = res;
                this.isLoading = data.length < 10;
                this.setData({
                    list: [...list, ...data],
                    pageNum: this.pageNum + 1
                });
            });
    },
    onScroll(e) {
        const {scrollTop, scrollHeight} = e.detail;

        if (scrollTop + this.windowHeight >= scrollHeight - 200) {
            this.loadData();
        }
    },
    clickWatched(item) {
        console.log(item);
    }
})
