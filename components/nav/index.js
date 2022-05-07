const app = getApp()
Component({
    properties: {
        title: {
            type: String,
            value: ''
        }
    },
    data: {
        safeareaTop: app.globalData.safeareaTop || 44
    }
});