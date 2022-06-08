import { getFollowList, getTeleplayList } from '../../utils/apis';
import { handleWaterfall } from '../../utils/util';

function empty() {}

const methods = {
    getFollowList,
    getTeleplayList
};

Page({
    data: {
        list: {}
    },
    onLoad(queryParams) {
        this.pageNum = 1;
        this.isLoading = false;
        this.queryParams = queryParams;
        this.request = methods[queryParams.method] || empty;
        try {
            this.extraParams = JSON.parse(queryParams.extraParams);
        } catch {
            this.extraParams = queryParams.extraParams;
        }
        wx.setNavigationBarTitle({
            title: queryParams.title || ''
        });

        this.loadData();
    },
    loadData() {
        if (this.isLoading) {
            return;
        }
        this.isLoading = true;
        this.request({...this.extraParams, pageNum: this.pageNum})
            .then(res => {
                if (res.data) {
                    this.pageNum++;
                    this.setData({
                        list: handleWaterfall(this.data.list, res.data)
                    });
                    this.isLoading = res.data.length < 10;
                }
            });
    },
    onReachBottom() {
        this.loadData();
    }
});
