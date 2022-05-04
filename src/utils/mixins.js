export const methodsMixins = {
    methods: {
        goBack() {
            this.$router.back();
        },
        vipFormatter(row) {
            if (row.vip === 1) {
                return '是';
            }
            return '否';
        }
    }
};