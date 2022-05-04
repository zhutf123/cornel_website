<template>
    <div class="m-sub-episodes">
        <div class="sub-main">
            <el-page-header
                @back="goBack"
            >
                <template slot="content">
                    <span class="name"></span>
                    <el-button
                        size="mini"
                        type="primary"
                        @click="gotoPublish"
                    >发布</el-button>
                </template>
            </el-page-header>
            <el-table
                :data="list"
            >
                <el-table-column
                    label="剧集"
                    prop="title"
                ></el-table-column>
                <el-table-column
                    label="封面"
                >
                    <template slot-scope="scope">
                        <img class="thumb" :src="scope.row.mainImage" />
                    </template>
                </el-table-column>
                <el-table-column
                    label="VIP剧集"
                    prop="vip"
                    :formatter="vipFormatter"
                ></el-table-column>
                <el-table-column
                    label="发布时间"
                    prop="createTime"
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
                <el-table-column
                    label="操作"
                    fixed="right"
                >
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            @click="openSubEpisodeDetail(scope.$index, scope.row)"
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
        </div>
        <router-view />
    </div>
</template>

<script>
import './sub.scss';
import {getSubEpisodeList} from '../../apis';
import {methodsMixins} from '../../utils/mixins';

export default {
    name: 'sub-episode-list',
    mixins: [methodsMixins],
    data() {
        return {
            form: {
                teleplayId: '',
                pageSize: 10,
                pageNum: 1
            },
            list: [],
            total: 0
        };
    },
    mounted() {
        const {tId} = this.$route.query;
        this.form.teleplayId = tId;
        this.search();
    },
    methods: {
        onPageChange(pageNum) {
            this.form.pageNum = pageNum;
            this.search();
        },
        search() {
            getSubEpisodeList(this.form).then(res => {
                if (res.status === 0) {
                    this.list = res.data;
                    this.total = res.allNum;
                }
            });
        },
        openSubEpisodeDetail(index, data) {
            this.$router.push({
                path: '/episodes/subList',
                query: {
                    videoId: data.id
                }
            });
        },
        gotoPublish() {
            const {tId} = this.$route.query;
            this.$router.push({
                path: '/publish',
                query: {
                    tId
                }
            });
        }
    }
}
</script>