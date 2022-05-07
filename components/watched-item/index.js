const app = getApp()
Component({
    properties: {
        item: {
            type: Object,
            value: {}
        },
        more: {
            type: Boolean,
            value: false
        }
    },
    methods: {
        onClick(e) {
            this.triggerEvent('onClick', e);
        }
    }
});