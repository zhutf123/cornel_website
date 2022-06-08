import route from '../../utils/route';

Component({
    data: {
    },
    methods: {
        clickMask() {
            this.triggerEvent('clickMask');
        },
        jumpToVip() {
            route.go('vip');
        },
        onGetVip() {
            
        }
    }
});