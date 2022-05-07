// index.js
// 获取应用实例
const app = getApp()

Page({
    data: {
        nbFrontColor: '#000000',
        nbBackgroundColor: '#ffffff',
    },
    onLoad() {
        this.setData({
            nbTitle: '新标题',
            nbLoading: true,
            nbFrontColor: '#ffffff',
            nbBackgroundColor: '#999',
        })
    }
})
