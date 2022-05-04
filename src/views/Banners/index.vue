<template>
    <div class="m-tags">
        <el-form>
            <el-form-item>
                <el-button type="primary" @click="add">添加轮播图</el-button>
                <el-button @click="exportData">导出excel</el-button>
            </el-form-item>
        </el-form>
        
        <el-table
            :data="list"
            row-key="id"
        >
            <el-table-column
                label="类型"
                prop="type"
                :formatter="bannerTypeFormatter"
            ></el-table-column>
            <el-table-column
                label="背景图"
            >
            </el-table-column>
            <el-table-column
                label="封面图"
            >
                <template slot-scope="scope">
                    <img class="thumb" :src="scope.row.mainImage" />
                </template>
            </el-table-column>
            <el-table-column
                label="标题"
                prop="title"
            ></el-table-column>
            <el-table-column
                label="描述"
                prop="depict"
            ></el-table-column>
            <el-table-column
                label="权重"
                prop="weight"
            ></el-table-column>
            <el-table-column
                label="弹幕"
                prop="operateTime"
            ></el-table-column>
            <el-table-column
                label="曝光"
                prop="operateTime"
            ></el-table-column>
            <el-table-column
                label="点击数"
                prop="operateTime"
            ></el-table-column>
            <el-table-column
                label="发布人"
                prop="operatorName"
            ></el-table-column>
            <el-table-column
                label="发布时间"
                prop="operateTime"
            ></el-table-column>
            <el-table-column
                label="状态"
                prop="statusDesc"
            ></el-table-column>
            <el-table-column
                label="操作"
                fixed="right"
            >
                <template slot-scope="scope">
                    <div>
                        <el-button
                            type="text"
                            @click="edit(scope.$index, scope.row)"
                        >编辑</el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            background
            layout="prev, pager, next"
            :total="total"
            @current-change="search"
        >
        </el-pagination>

        <el-dialog title="添加轮播图"
            :visible.sync="showDialog"
            :show-close="false"
            :close-on-click-modal="false"
        >
            <el-form ref="editingForm" :model="editingData"
                v-if="editingData"
                label-width="100px"
            >
                <el-form-item label="轮播图模式" prop="type">
                    <el-select v-model="editingData.type">
                        <el-option label="剧集" :value="1" />
                        <el-option label="广告" :value="2" />
                    </el-select>
                </el-form-item>
                <el-form-item label="标题" prop="title">
                    <el-row>
                        <el-col :span="15">
                            <el-input v-model="editingData.title" />
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item label="剧集信息" prop="videoId">
                    <el-tag
                        v-if="editingData.videoId"
                    >{{tag.name}}</el-tag>
                    <suggest
                        type="episode"
                        valueKey="title"
                        placeholder="请输入剧集名称"
                        :onSelect="handleSelectEpisode"
                    ></suggest>
                </el-form-item>
                <el-form-item label="描述" prop="depict">
                    <el-row>
                        <el-col :span="15">
                            <el-input type="textarea" autosize :rows="3" v-model="editingData.depict" />
                        </el-col>
                    </el-row>
                </el-form-item>
                <!-- <el-form-item label="背景图" prop="mainImage">
                    <uploader
                        type="avatar"
                        :onSuccess="onUploadImg"
                    />
                </el-form-item> -->
                <el-form-item label="封面图" prop="videoUrl"
                    v-if="editingData.type === 1"
                >
                    <uploader
                        type="avatar"
                        :onSuccess="onUploadImg"
                    />
                </el-form-item>
                <el-form-item label="视频" prop="videoUrl"
                    v-else
                >
                    <uploader
                        type="avatar"
                        sourceType="video"
                        :onSuccess="onUploadVideo"
                    />
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-select v-model="editingData.status">
                        <el-option label="上线" :value="1" />
                        <el-option label="下线" :value="2" />
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editingData = null">取消</el-button>
                <el-button type="primary" @click="onConfirmEdit">提交</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import './index.scss';

import {CHANNEL_TAGS} from '../../utils/constant';
import { getBannerList, updateBanner } from '../../apis';
import Suggest from '../../components/Suggest.vue';
import { methodsMixins } from '../../utils/mixins';
import Uploader from '../../components/Uploader.vue';

export default {
    name: 'tags',
    components: {
        Suggest,
        Uploader
    },
    mixins: [methodsMixins],
    data() {
        return {
            CHANNEL_TAGS,
            form: {
                name: '',
                recommend: '',
                status: '',
                pageSize: 10,
                pageNum: 1
            },
            list: [],
            total: 0,
            suggestChannelInput: '',
            editingData: null
        };
    },
    computed: {
        showDialog() {
            return !!this.editingData;
        }
    },
    mounted() {
        this.search();
    },
    methods: {
        search(pageNum = 1) {
            this.form.pageNum = pageNum;
            getBannerList(this.form).then(res => {
                if (res.data) {
                    this.list = res.data;
                    this.total = res.allNum;
                }
            });
        },
        add() {
            this.editingData = {
                type: 2,
                title: '',
                depict: '',
                mainImage: '',
                imageSourceId: '',
                status: 1
            };
        },
        edit(index, data) {
            this.editingData = data;
        },
        del(index, data) {
            delTag(data.id).then(res => {
                this.$message[res.status === 0 ? 'success' : 'error'](res.msg);
                this.search(this.form.pageNum);
            });
        },
        exportData() {

        },
        onConfirmEdit() {
            this.$refs.editingForm.validate().then(() => {
                updateBanner(this.editingData).then(res => {
                    if (res.status === 0) {
                        this.$message.success(res.msg);
                        this.search(this.form.pageNum);
                        this.editingData = null;
                    } else {
                        this.$message.error(res.msg);
                    }
                });
            });
        },
        handleSelectEpisode(item) {
            this.editingData.videoId = item.teleplayId;
        },
        onUploadImg(res) {
            const {data} = res;
            this.editingData.mainImage = data.url;
            this.editingData.mainSource = data.sourceId;
        },
        onUploadVideo(res) {
            const {data} = res;
            this.editingData.videoUrl = data.url;
            this.editingData.videoSource = data.sourceId;
        }
    }
}
</script>