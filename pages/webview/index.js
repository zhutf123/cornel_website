// 获取应用实例
const app = getApp()

Page({
    data: {
        url: ''
    },
    onLoad(queryParams) {
        const {title, url} = queryParams;
        if (title) {
            wx.setNavigationBarTitle({
                title
            });
        }
        if (url) {
            this.setData({
                url: decodeURIComponent(url)
            });
        }
    }
});
