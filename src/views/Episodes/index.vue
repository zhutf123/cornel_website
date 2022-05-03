<template>
    <div class="m-episodes">
        <el-form ref="form" :model="form" :inline="true">
            <el-form-item label="内容ID">
                <el-input v-model="form.id" />
            </el-form-item>
            <el-form-item label="标题">
                <el-input v-model="form.title" />
            </el-form-item>
            <el-form-item label="审核状态">
                <el-select v-model="form.status">
                    <el-option label="已审核" :value="1"/>
                </el-select>
            </el-form-item>
            <el-form-item label="频道类型">
                <el-select v-model="form.channel">
                    <el-option label="已审核" value="1" />
                </el-select>
            </el-form-item>
            <el-form-item label="VIP剧集">
                <el-select v-model="form.vip">
                    <el-option label="是" :value="1" />
                    <el-option label="否" :value="0" />
                </el-select>
            </el-form-item>
            <el-button type="primary" @click="search">查询</el-button>
            <el-button @click="reset">清空</el-button>
            <el-button @click="exportData">导出excel</el-button>
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
                    <el-button
                        type="text"
                        @click="edit(scope.$index, scope.row)"
                    >修改</el-button>
                    <el-button
                        type="text"
                        @click="preview(scope.$index, scope.row)"
                    >详细数据</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            background
            layout="prev, pager, next"
            :total="total"
            @current-change="onPageChange"
        >
        </el-pagination>

        <el-dialog title="编辑剧集"
            v-if="editingData"
        >
            <el-form ref="editingForm" :model="editingData">
                <el-form-item label="标题">
                    <el-input v-model="editingData.title" />
                </el-form-item>
                <el-form-item label="描述">
                    <el-input type="textarea" :rows="4" v-model="editingData.desc" />
                </el-form-item>
                <el-form-item label="封面">
                    <el-row>
                        <el-col :span="15">
                            <el-input v-model="editingData.thumb" />
                        </el-col>
                        <el-col :span="8" :offset="1">
                            <el-upload>
                                <el-button size="small" type="primary">本地上传</el-button>
                            </el-upload>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item label="频道">
                    <el-input v-model="editingData.desc" />
                </el-form-item>
                <el-form-item label="VIP剧集">
                    <el-select v-model="editingData.status">
                        <el-option label="是" :value="1" />
                        <el-option label="否" :value="0" />
                    </el-select>
                </el-form-item>
                <el-form-item label="推荐首页">
                    <el-input v-model="editingData.recommend" />
                </el-form-item>
                <el-form-item label="频道置顶">
                    <el-input v-model="editingData.top" />
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

import {getEpisodesList} from '../../apis';
import {vipFormatter} from '../../utils/formatter';

export default {
    name: 'episodes',
    data() {
        return {
            form: {
                id: '',
                vip: 0,
                channel: '',
                status: '',
                title: '',
                pageSize: 10,
                pageNum: 1,
                offset: 0
            },
            list: [],
            total: 0,
            editingData: null
        };
    },
    methods: {
        vipFormatter,
        onPageChange(pageNum) {
            this.form.pageNum = pageNum;
            this.search();
        },
        search() {
            getEpisodesList({
                pageSize: 10,
                pageNum: 1
            }).then(res => {
                if (res.status === 0) {
                    this.list = res.data;
                    this.total = res.allNum;
                }
            })
        },
        reset() {
            this.$refs.form.reset();
        },
        exportData() {

        },
        edit(index, data) {
            this.editingData = data;
        },
        onConfirmEdit() {

        },
        preview(index, data) {

        }
    }
}
</script>