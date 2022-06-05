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
        changeFollow() {
            this.triggerEvent('onChangeFollow', this.item);
        }
    }
});