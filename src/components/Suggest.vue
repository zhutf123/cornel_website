<template>
    <div>
        <el-select
            v-model="input"
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
                v-for="(item, index) in options"
                :key="item.id"
                :label="item[valueKey]"
                :value="index"
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
            loading: false,
            options: [],
            input: this.displayValue
        };
    },
    methods: {
        onChange(data) {
            if (!this.multiple) {
                this.onSelect(this.cacheOptions[data]);
                return;
            }
            const selected = data.map(i => this.cacheOptions[i]);
            this.onSelect(selected);
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
                        this.cacheOptions = data;
                        this.options = res.data;
                        this.loading = false;
                    });
            }
        }
    }
}
</script>