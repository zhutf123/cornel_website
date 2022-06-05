const app = getApp()
Component({
    properties: {
        item: {
            type: Object,
            value: {}
        }
    },
    methods: {
        onClick() {
            this.triggerEvent('onClick', this.item);
        },
        handleFollow() {
            this.triggerEvent('handleFollow', this.item);
        }
    }
});