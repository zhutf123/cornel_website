<template>
    <el-row class="m-publish">
        <el-col :span="12">
            <el-form ref="form" :model="form"
                label-width="100px"
            >
                <el-form-item label="集数" prop="seq">
                    第
                    <el-input-number
                        v-model="form.seq"
                        :min="1"
                    />
                    集
                </el-form-item>
                <el-form-item label="标题" prop="title" required>
                    <el-input
                        v-model="form.title"
                        :maxlength="15"
                        placeholder="请输入描述，最多15个字"
                    />
                </el-form-item>
                <el-form-item label="描述" prop="depict">
                    <el-input type="textarea"
                        :rows="3"
                        v-model="form.depict"
                        :maxlength="50"
                        placeholder="请输入描述，最多50个字"
                    />
                </el-form-item>
                <el-form-item label="设置封面" prop="mainImage">
                    <uploader
                        :key="refresh"
                        className="uploader"
                        type="avatar"
                        :onSuccess="onUploadImg"
                    />
                </el-form-item>
                <el-form-item label="剧集信息" prop="teleplayId">
                    <suggest
                        :key="refresh"
                        :deep="1"
                        :multiple="false"
                        :disabled="!!$route.query.tId"
                        type="episode"
                        valueKey="title"
                        :displayValue="suggestValue"
                        placeholder="请输入剧集名称"
                        :onSelect="handleSelectEpisode"
                    ></suggest>
                </el-form-item>
                <el-form-item class="video" label="视频" prop="videoUrl">
                    <uploader
                        :key="refresh"
                        className="uploader"
                        type="avatar"
                        sourceType="video"
                        :onSuccess="onUploadVideo"
                    />
                </el-form-item>
                <el-form-item label="VIP剧集" prop="vip">
                    <el-select v-model="form.vip">
                        <el-option label="是" :value="1" />
                        <el-option label="否" :value="2" />
                    </el-select>
                </el-form-item>
                <el-form-item label="审核状态" prop="status">
                    <el-select v-model="form.status">
                        <el-option label="上架" :value="1" />
                        <el-option label="下架" :value="2" />
                    </el-select>
                </el-form-item>
            </el-form>
            <el-button @click="reset">清空</el-button>
            <el-button type="primary" @click="submit">提交</el-button>
        </el-col>
    </el-row>
</template>

<script>
import './index.scss';

import Uploader from '../../components/Uploader.vue';
import Suggest from '../../components/Suggest.vue';
import { updateSubEpisode, getEpisodeInfoById } from '../../apis';

const defaultForm = {
    title: '',
    depict: '',
    mainImage: '',
    mainSource: '',
    videoUrl: '',
    videoSource: '',
    videoTime: '',
    teleplayId: '',
    seq: 1,
    vip: 2,
    status: 1
};

export default {
    name: 'publish',
    components: {
        Suggest,
        Uploader
    },
    data() {
        return {
            refresh: 0,
            suggestValue: '',
            form: {
                ...defaultForm
            }
        };
    },
    mounted() {
        const {tId} = this.$route.query;
        if (tId) {
            this.getCurrentEpisodeInfo(tId);
        }
    },
    methods: {
        getCurrentEpisodeInfo(tId) {
            getEpisodeInfoById(tId).then(res => {
                if (res.status === 0 && res.data) {
                    this.suggestValue = res.data.title;
                }
            });
        },
        onUploadVideo(res) {
            const {url, sourceId, videoTime} = res.data;
            if (url) {
                this.form.videoUrl = url;
                this.form.videoSource = sourceId;
                this.form.videoTime = videoTime;
            }
        },
        onUploadImg(res) {
            const {url, sourceId} = res.data;
            if (url) {
                this.form.mainImage = url;
                this.form.mainSource = sourceId;
            }
        },
        handleSelectEpisode(data) {
            this.form.teleplayId = data.id;
        },
        reset() {
            this.form = {
                ...defaultForm
            };
            this.refresh = Date.now();
        },
        submit() {
            this.$refs.form.validate().then(() => {
                const form = {...this.form};
                const {tId} = this.$route.query;
                if (tId && !form.teleplayId) {
                    form.teleplayId = tId;
                }
                updateSubEpisode(form).then(res => {
                    if (res.status === 0) {
                        this.$message.success('发布成功');
                        this.reset();
                    } else {
                        this.$message.error(res.msg || '发布失败');
                    }
                })
            });
        }
    }
}
</script>