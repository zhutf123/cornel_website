const app = getApp()
Component({
    properties: {
        show: {
            type: Boolean,
            default: false
        },
        title: {
            type: String,
            value: '提示'
        },
        cancelText: {
            type: String,
            value: '取消'
        },
        confirmText: {
            type: String,
            value: '确定'
        }
    },
    methods: {
        onCancel() {
            this.triggerEvent('onCancel');
        },
        onConfirm() {
            this.triggerEvent('onConfirm');
        }
    }
});