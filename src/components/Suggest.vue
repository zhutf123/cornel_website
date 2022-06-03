<template>
    <div>
        <el-select
            v-model="value"
            :multiple="multiple"
            remote
            filterable
            :disabled="disabled"
            :remote-method="queryChannel"
            :loading="loading"
            :placeholder="placeholder"
            @change="onChange"
        >
            <el-option
                v-for="(item) in options"
                :key="item.id"
                :label="item[valueKey]"
                :value="item.id"
            >
            </el-option>
        </el-select>
    </div>
</template>

<script>
import {suggestChannel, suggestEpisode} from '../apis';

export default {
    props: {
        deep: {
            type: Number,
            default: 2
        },
        disabled: {
            type: Boolean,
            default: false
        },
        type: {
            type: String,
            default: 'channel'
        },
        displayValue: {
            type: String,
            default: ''
        },
        valueKey: {
            type: String,
            default: 'name'
        },
        multiple: {
            type: Boolean,
            default: true
        },
        placeholder: {
            type: String,
            default: ''
        },
        onSelect: {
            type: Function,
            default: () => {}
        }
    },
    watch: {
        displayValue() {
            this.input = this.displayValue
        }
    },
    data() {
        return {
            value: [],
            loading: false,
            options: [],
            input: this.displayValue
        };
    },
    methods: {
        onChange(data) {
            let id = data;
            if (this.multiple) {
                id = data.slice(-1)[0];
            }
            const selected = this.cacheOptions.find(item => item.id === id);
            this.onSelect(selected);
            this.$emit('select', selected);
        },
        queryChannel(input) {
            if (!input) {
                this.options = [];
                return;
            }
            this.loading = true;
            switch(this.type) {
                case 'episode':
                    suggestEpisode(input).then(res => {
                        if (res.data) {
                            const data = this.depp === 2 ? res.data.reduce((res, item) => {
                                const {title, videoList} = item;
                                videoList.forEach(item => {
                                    item.title = title + '-' + item.title;
                                });
                                return res.concat(videoList);
                            }, []) : res.data;

                            this.cacheOptions = data;
                            this.options = data;
                            this.loading = false;
                        }
                    });
                    break;
                default:
                    suggestChannel(input).then(res => {
                        this.cacheOptions = res.data;
                        this.options = res.data;
                        this.loading = false;
                    });
            }
        }
    }
}
</script>