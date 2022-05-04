<template>
    <el-upload
        :class="type === 'avatar' ? 'avatar-uploader' : ''"
        :action="'//wx.ydwlys.com/admin/file/upload.json?type=' + apiType"
        :multiple="multiple"
        :show-file-list="false"
        :on-success="onUploaded"
    >
        <el-button size="small" type="primary" v-if="type === 'button'">本地上传</el-button>
        <template v-else-if="type === 'avatar'">
            <div v-if="data.url">
                <img v-if="sourceType === 'pic'" :src="data.url" class="avatar" />
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
            data: {}
        };
    },
    methods: {
        onUploaded(res) {
            console.log(res);
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