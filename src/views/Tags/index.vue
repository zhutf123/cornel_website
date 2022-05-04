<template>
    <div class="m-tags">
        <el-form ref="form" :model="form" :inline="true">
            <el-form-item label="聚合名称">
                <el-row>
                    <el-col :span="15">
                        <el-input v-model="form.name" />
                    </el-col>
                    <el-col :span="8" :offset="1">
                        <el-button type="primary" size="small"
                            @click="add"
                        >添加标签</el-button>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item label="添加到首页">
                <el-select v-model="form.recommend">
                    <el-select v-model="form.status">
                        <el-option label="是" :value="1" />
                        <el-option label="否" :value="0" />
                    </el-select>
                </el-select>
            </el-form-item>
            <el-form-item label="标签状态">
                <el-select v-model="form.status">
                    <el-option label="在线" :value="1" />
                    <el-option label="下线" :value="2" />
                    <el-option label="审核中" :value="3" />
                    <el-option label="删除" :value="4" />
                </el-select>
            </el-form-item>
            <el-button type="primary" @click="search">查询</el-button>
            <el-button @click="exportData">导出excel</el-button>
        </el-form>
        
        <el-table
            :data="list"
            row-key="id"
        >
            <el-table-column type="expand">
                <template slot-scope="scope">
                    <el-table :data="scope.row.channelList" class="subTable">
                        <el-table-column
                            label=""
                            prop=""
                            width="48px"
                        ></el-table-column>
                        <el-table-column
                            label=""
                            prop="name"
                        ></el-table-column>
                        <el-table-column
                            label=""
                            prop="statusDesc"
                        >
                            <template slot-scope="{row, $index}">
                                {{row.statusDesc}}
                                |
                                <el-button type="text" :disabled="row.status === 2"
                                    @click="offlineSubTag('offline', scope.row.id, row, scope.$index, $index)"
                                >下架</el-button>
                            </template>
                        </el-table-column>
                        <el-table-column
                            label=""
                            prop="channelSize"
                        ></el-table-column>
                        <el-table-column
                            label=""
                            prop=""
                        ></el-table-column>
                        <el-table-column
                            label=""
                            prop="operatorName"
                        ></el-table-column>
                        <el-table-column
                            label=""
                            prop="operateTime"
                        ></el-table-column>
                        <el-table-column
                            label=""
                            prop=""
                        ></el-table-column>
                    </el-table>
                </template>
            </el-table-column>
            <el-table-column
                label="聚合名称"
                prop="name"
            ></el-table-column>
            <el-table-column
                label="标签状态"
                prop="statusDesc"
            >
            </el-table-column>
            <el-table-column
                label="关联标签数"
                prop="channelSize"
            ></el-table-column>
            <el-table-column
                label="添加到首页"
                prop="recommend"
                :formatter="recommendFormatter"
            ></el-table-column>
            <el-table-column
                label="添加人"
                prop="operatorName"
            ></el-table-column>
            <el-table-column
                label="添加时间"
                prop="operateTime"
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
                    </div><div>
                        <el-button
                            type="text"
                            @click="del(scope.$index, scope.row)"
                        >删除</el-button>
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

        <el-dialog title="聚合标签"
            :visible.sync="showDialog"
            :show-close="false"
            :close-on-click-modal="false"
        >
            <el-form ref="editingForm" :model="editingData" v-if="editingData">
                <el-form-item label="聚合名称" required prop="name">
                    <el-row>
                        <el-col :span="12">
                            <el-input placeholder="请输入聚合名称" v-model="editingData.name" />
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item label="首页权重" required prop="weight">
                    <el-input-number :max="10" v-model="editingData.weight" />
                </el-form-item>
                <el-form-item label="关联标签" required prop="channelList">
                    <el-tag
                        v-for="(tag, index) in editingData.channelList"
                        :key="tag.id"
                        size="mini"
                        closable
                        @close="handleCloseChannelTag(index)"
                    >{{tag.name}}</el-tag>
                    <suggest
                        placeholder="请输入频道"
                        :onSelect="handleSelectChannel"
                    ></suggest>
                </el-form-item>
                <el-form-item label="标签状态" required prop="status">
                    <el-select v-model="editingData.status">
                        <el-option label="在线" :value="1" />
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
import { delTag, getTagsList, updateTag, offlineTag } from '../../apis';
import Suggest from '../../components/Suggest.vue';
import { methodsMixins } from '../../utils/mixins';

export default {
    name: 'tags',
    components: {
        Suggest
    },
    mixins: [methodsMixins],
    data() {
        return {
            CHANNEL_TAGS,
            showDialog: false,
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
        dialog() {
            return !!this.editingData;
        }
    },
    watch: {
        dialog() {
            this.showDialog = this.dialog;
            if (!this.showDialog) {
                this.editingData = null;
            }
        }
    },
    mounted() {
        this.search();
    },
    methods: {
        search(pageNum = 1) {
            this.form.pageNum = pageNum;
            getTagsList(this.form).then(res => {
                if (res.data) {
                    this.list = res.data;
                    this.total = res.allNum;
                }
            });
        },
        add() {
            this.editingData = {
                name: '',
                recommend: '',
                weight: '',
                channelList: ''
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
        handleSelectChannel(item) {
            const index = this.editingData.channel.indexOf(item.id);
            if (index === -1) {
                this.editingData.channel.push(item.id);
                this.editingData.channelDesc.push(item.name);
            }
        },
        handleCloseChannelTag(index) {
            Vue.delete(this.editingData.channel, index);
            Vue.delete(this.editingData.channelList, index);
        },
        onConfirmEdit() {
            this.$refs.editingForm.validate().then(() => {
                updateTag(this.editingData).then(res => {
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
        offlineSubTag(type, groupId, data, pIndex, index) {
            const {id: channelId} = data;

            offlineTag({groupId, channelId}).then(res => {
                if (res.status === 0) {
                    this.$message.success(res.msg);
                    Vue.delete(this.list[pIndex].channelList, index);
                }
            });
        }
    }
}
</script>