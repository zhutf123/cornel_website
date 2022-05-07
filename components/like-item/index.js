const app = getApp()
Component({
    properties: {
        item: {
            type: Object,
            value: {}
        }
    },
    methods: {
        onClick(e) {
            this.triggerEvent('onClick', e);
        }
    }
});