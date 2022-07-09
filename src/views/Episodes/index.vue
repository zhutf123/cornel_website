<template>
    <div class="m-episodes">
        <el-form ref="form" :model="form" :inline="true">
            <el-form-item label="内容ID" prop="id">
                <el-input v-model="form.id" />
            </el-form-item>
            <el-form-item label="标题" prop="title">
                <el-input v-model="form.title" />
            </el-form-item>
            <el-form-item label="审核状态" prop="status">
                <el-select v-model="form.status">
                    <el-option label="已上架" :value="1"/>
                    <el-option label="未上架" :value="0"/>
                </el-select>
            </el-form-item>
            <el-form-item label="频道类型" prop="channel">
                <suggest
                    :key="refresh"
                    type="channel"
                    :multiple="false"
                    valueKey="name"
                    placeholder="请输入剧集名称"
                    @select="handleSelectChannel($event, 'form')"
                />
            </el-form-item>
            <el-form-item label="VIP剧集" prop="vip">
                <el-select v-model="form.vip">
                    <el-option label="是" :value="1" />
                    <el-option label="否" :value="0" />
                </el-select>
            </el-form-item>
            <el-button type="primary" @click="search(1)">查询</el-button>
            <el-button @click="reset">清空</el-button>
            <el-button @click="exportData">导出excel</el-button>
            <el-button type="success" @click="addEpisode">添加剧集</el-button>
        </el-form>
        <el-table
            :data="list"
        >
            <el-table-column
                label="封面"
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
                label="剧集"
            ></el-table-column>
            <el-table-column
                label="频道"
            >
                <template slot-scope="scope">
                    <el-tag
                        v-for="channel in scope.row.channelDesc"
                        :key="channel"
                    >{{channel}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column
                label="VIP剧集"
                prop="vip"
                :formatter="vipFormatter"
            ></el-table-column>
            <el-table-column
                label="审核状态"
                prop="statusDesc"
            ></el-table-column>
            <el-table-column
                label="发布时间"
                prop="createTime"
                width="90px"
            ></el-table-column>
            <el-table-column
                label="发布者"
                prop="operatorName"
            ></el-table-column>
            <el-table-column
                label="追剧数"
                prop="followNum"
            ></el-table-column>
            <el-table-column
                label="播放量"
                prop="playNum"
            ></el-table-column>
            <el-table-column
                label="点赞量"
                prop="likeNum"
            ></el-table-column>
            <el-table-column
                label="转发量"
                prop="shareNum"
            ></el-table-column>
            <!-- <el-table-column
                label="评论数"
            ></el-table-column> -->
            <el-table-column
                label="是否推荐到首页"
                prop="recommend"
            ></el-table-column>
            <el-table-column
                label="置顶操作"
                prop="top"
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
                        >修改</el-button>
                    </div>
                    <div>
                        <el-button
                            type="text"
                            @click="openSubEpisode(scope.$index, scope.row)"
                        >详细数据</el-button>
                    </div>
                    <div v-if="scope.row.status === 1">
                        <el-button
                            type="text"
                            class="color-danger"
                            @click="offline(scope.$index, scope.row)"
                        >下线</el-button>
                    </div>
                    <div v-else>
                        <el-button
                            type="text"
                            class="color-success"
                            @click="online(scope.$index, scope.row)"
                        >上线</el-button>
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

        <el-dialog title="编辑剧集"
            :visible.sync="editingDialog"
            :show-close="false"
            :close-on-click-modal="false"
        >
            <el-form ref="editingForm" :model="editingData" v-if="editingData">
                <el-form-item label="标题">
                    <el-input v-model="editingData.title" />
                </el-form-item>
                <el-form-item label="描述">
                    <el-input type="textarea" :rows="4" v-model="editingData.depict" />
                </el-form-item>
                <el-form-item label="封面">
                    <el-row>
                        <el-col :span="15">
                            <el-input v-model="editingData.mainImage" />
                        </el-col>
                        <el-col :span="4" :offset="1">
                            <uploader
                                :onSuccess="onUploadThumb"
                            />
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item label="频道">
                    <el-tag
                        v-for="tag in editingData.channelDesc"
                        :key="tag"
                        size="mini"
                        closable
                        @close="handleCloseChannelTag(tag)"
                    >{{tag}}</el-tag>
                    <suggest
                        class="inline-input"
                        :key="refresh"
                        type="channel"
                        valueKey="name"
                        placeholder="请输入频道名称"
                        @select="handleSelectChannel($event, 'editingData')"
                    />
                </el-form-item>
                <el-form-item label="VIP剧集">
                    <el-select v-model="editingData.vip">
                        <el-option label="是" :value="1" />
                        <el-option label="否" :value="2" />
                    </el-select>
                </el-form-item>
                <el-form-item label="推荐首页">
                    <el-input-number v-model="editingData.recommend"
                        :min="0"
                    />
                </el-form-item>
                <el-form-item label="频道置顶">
                    <el-input-number v-model="editingData.top" :min="0" />
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editingData = null">取消</el-button>
                <el-button type="primary" @click="onConfirmEdit">提交</el-button>
            </span>
        </el-dialog>
        <router-view></router-view>
    </div>
</template>

<script>
import {getEpisodeList, suggestChannel, updateEpisode} from '../../apis';
import { methodsMixins } from '../../utils/mixins';
import Uploader from '../../components/Uploader.vue';
import Suggest from '../../components/Suggest.vue';

export default {
    name: 'episodes',
    mixins: [methodsMixins],
    components: {
        Suggest,
        Uploader
    },
    computed: {
        editingDialog() {
            return !!this.editingData;
        }
    },
    data() {
        return {
            refresh: 0,
            form: {
                id: '',
                vip: '',
                channel: '',
                status: '',
                title: '',
                pageSize: 10,
                pageNum: 1
            },
            list: [],
            total: 0,
            searchChannel: '',
            dialogChannel: '',
            editingData: null,
            subParentData: null
        };
    },
    mounted() {
        this.search();
    },
    methods: {
        search(pageNum = 1) {
            this.form.pageNum = pageNum;
            getEpisodeList(this.form).then(res => {
                if (res.status === 0) {
                    this.list = res.data;
                    this.total = res.allNum;
                }
            });
        },
        reset() {
            this.$refs.form.resetFields();
            this.refresh = Date.now();
        },
        exportData() {

        },
        addEpisode() {
            this.editingData = {
                title: '',
                depict: '',
                mainImage: '',
                mainSource: '',
                channel: [],
                vip: 2,
                recommend: 0,
                top: 0
            };
        },
        edit(index, data) {
            this.editingData = data;
        },
        online(index, data) {
            updateEpisode({
                ...data,
                status: 1
            }).then(res => {
                if (res.status === 0) {
                    this.search(this.form.pageNum);
                } else {
                    this.$message.error(res.msg || '删除失败');
                }
            });
        },
        offline(index, data) {
            updateEpisode({
                ...data,
                status: 2
            }).then(res => {
                if (res.status === 0) {
                    this.search(this.form.pageNum);
                } else {
                    this.$message.error(res.msg || '删除失败');
                }
            });
        },
        onConfirmEdit() {
            updateEpisode(this.editingData).then(res => {
                if (res.status === 0) {
                    this.$message.success(res.msg || '编辑成功');
                    this.editingData = null;
                    this.search(this.form.pageNum);
                } else if (res.msg) {
                    this.$message.error(res.msg);
                }
            });
        },
        openSubEpisode(index, data) {
            this.$router.push({
                path: '/episodes/subList',
                query: {
                    tId: data.id
                }
            });
        },
        onUploadThumb(res) {
            const {data, status, msg} = res;
            if (status !== 0) {
                this.$$message.error(msg);
                return;
            }
            this.editingData.mainImage = data.url;
            this.editingData.sourceId = data.sourceId;
        },
        queryChannel(input, cb) {
            suggestChannel(input).then(res => {
                cb(res.data);
            })
        },
        handleSelectChannel(item, type) {
            if (type === 'form') {
                this.form.channel = item.id;
            } else {
                const index = (this.editingData.channel || []).findIndex(ch => +ch === item.id);
                if (index === -1) {
                    if (!this.editingData.channel) {
                        this.editingData.channel = [];
                    }
                    if (!this.editingData.channelDesc) {
                        this.editingData.channelDesc = [];
                    }
                    this.editingData.channel.push(item.id);
                    this.editingData.channelDesc.push(item.name);
                }
            }
        },
        handleCloseChannelTag(tag) {
            const index = this.editingData.channelDesc.indexOf(tag);
            Vue.delete(this.editingData.channel, index);
            Vue.delete(this.editingData.channelDesc, index);
        }
    }
}
</script>