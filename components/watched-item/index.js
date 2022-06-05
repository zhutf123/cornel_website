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
        onClick() {
            this.triggerEvent('onClick', this.item);
        }
    }
});