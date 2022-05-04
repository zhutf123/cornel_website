<template>
    <el-autocomplete
        :value-key="valueKey"
        v-model="input"
        :fetch-suggestions="queryChannel"
        placeholder="请输入频道"
        @select="onSelect"
        :trigger-on-focus="false"
    ></el-autocomplete>
</template>

<script>
import {suggestChannel, suggestEpisode} from '../apis';

export default {
    props: {
        type: {
            type: String,
            default: 'channel'
        },
        valueKey: {
            type: String,
            default: 'name'
        },
        fetch: {
            type: Function,
            default: () => {}
        },
        onSelect: {
            type: Function,
            default: () => {}
        }
    },
    data() {
        return {
            input: ''
        };
    },
    methods: {
        queryChannel(input, cb) {
            switch(this.type) {
                case 'episode':
                    suggestEpisode(input).then(res => cb(res.data));
                    break;
                default:
                    suggestChannel(input).then(res => cb(res.data));
            }
        }
    }
}
</script>