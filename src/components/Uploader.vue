<template>
    <el-upload
        v-loading="loading"
        :class="[{'avatar-uploader': type === 'avatar'}, className]"
        :action="'/admin/file/upload.json?type=' + apiType"
        :multiple="multiple"
        :show-file-list="false"
        :on-progress="onProgress"
        :on-success="onUploaded"
    >
        <el-button size="small" type="primary" v-if="type === 'button'">本地上传</el-button>
        <template v-else-if="type === 'avatar'">
            <div v-if="data.url" class="preview">
                <img v-if="sourceType === 'pic'" :src="data.url" />
                <video controls :src="data.url" v-else />
            </div>
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </template>
    </el-upload>
</template>

<script>
import './uploader.scss';
export default {
    props: {
        className: {
            type: String,
            default: ''
        },
        type: {
            type: String,
            default: 'button'
        },
        sourceType: {
            type: String,
            default: 'pic'
        },
        multiple: {
            type: Boolean,
            default: false
        },
        onSuccess: {
            type: Function,
            default: () => {}
        }
    },
    computed: {
        apiType() {
            if (this.sourceType === 'pic') {
                return 1;
            }
            // 视频
            return 0;
        }
    },
    data() {
        return {
            loading: false,
            data: {}
        };
    },
    methods: {
        onProgress() {
            this.loading = true;
        },
        onUploaded(res) {
            this.loading = false;
            if (res.data) {
                this.data = {
                    url: res.data.url
                }
            }
            this.onSuccess(res);
        }
    }
}
</script>