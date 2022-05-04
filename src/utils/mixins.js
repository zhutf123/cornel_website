import * as formatter from './formatter';

export const methodsMixins = {
    methods: {
        goBack() {
            this.$router.back();
        },
        ...formatter
    }
};