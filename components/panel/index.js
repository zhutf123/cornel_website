Component({
    properties: {
        className: {
            type: String,
            default: ''
        }
    },
    methods: {
        clickMask() {
            this.triggerEvent('clickMask');
        }
    }
});