import { getFollowList } from '../../utils/apis';
import { handleWaterfall } from '../../utils/util';

function empty() {}

const methods = {
    getFollowList
};

Page({
    data: {
        list: {}
    },
    onLoad(queryParams) {
        this.pageNum = 1;
        this.queryParams = queryParams;
        this.request = methods[queryParams.method] || empty;
        this.extraParams = queryParams.extraParams || {};

        wx.setNavigationBarTitle({
            title: queryParams.title
        });
    },
    loadData() {
        if (this.isLoading) {
            return;
        }
        this.isLoading = true;
        this.request({...this.extraParams, pageNum: this.pageNum})
            .then(res => {
                if (res.data) {
                    this.setData({
                        list: handleWaterfall(list, res.data)
                    });
                    this.isLoading = res.data.length < 10;
                }
            });
    },
    onReachBottom() {
        this.loadData();
    }
});
