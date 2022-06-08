const app = getApp()
Component({
    properties: {
        list: {
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