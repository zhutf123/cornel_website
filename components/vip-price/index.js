Component({
    data: {
        activeIndex: 0,
        list: [
            {discount: '6.6', time: '1个月', price: 12, origin: 18},
            {discount: '5.1', time: '3个月', price: 28, origin: 54},
            {discount: '4.5', time: '12个月', price: 98, origin: 216}
        ]
    },
    methods: {
        changeItem(e) {
            const {index} = e.currentTarget.dataset;
            this.setData({
                activeIndex: index
            });
        },
        getVip() {
            this.triggerEvent('onGetVip');
        }
    }
});