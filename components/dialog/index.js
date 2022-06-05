const app = getApp()

const CLOSE_MASK = false;

Component({
    properties: {
        show: {
            type: Boolean,
            default: false
        },
        config: {
            type: Object,
            default: {
                title: '提示',
                content: '',
                cancelText: '取消',
                confirmText: '确定',
                highlight: 'right',
                closeMask: CLOSE_MASK
            }
        }
    },
    methods: {
        onCancel() {
            this.triggerEvent('onCancel');
        },
        onConfirm() {
            this.triggerEvent('onConfirm');
        },
        clickMask(evt) {
            const {currentTarget, target} = evt;
            const {closeMask = CLOSE_MASK} = this.data.config;
            if (closeMask && currentTarget.id === 'mask' && currentTarget.id === target.id) {
                this.triggerEvent('onCancel');
            }
        }
    }
});