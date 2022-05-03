<template>
    <el-upload
        :action="'//wx.ydwlys.com/admin/file/upload.json?type=' + sourceType"
        :multiple="multiple"
        :show-file-list="false"
        :on-success="onSuccess"
    >
        <el-button size="small" type="primary">本地上传</el-button>
    </el-upload>
</template>

<script>
export default {
    props: {
        sourceType: {
            type: Number,
            default: 1
        },
        multiple: {
            type: Boolean,
            default: false
        },
        onSuccess: {
            type: Function,
            default: () => {}
        }
    }
}
</script>