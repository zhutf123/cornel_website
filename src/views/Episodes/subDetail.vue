<template>
    <panel>
        <template slot="header">
            <span class="name"></span>
        </template>
        <el-table
            :data="list"
        >
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
    </panel>
</template>

<script>
import {getSubEpisodeDetail} from '../../apis';
import { methodsMixins } from '../../utils/mixins';
import Panel from '../../components/Panel.vue';

export default {
    name: 'sub-episode-detail',
    components: {
        Panel
    },
    mixins: [methodsMixins],
    data() {
        return {
            form: {
                pageSize: 10,
                pageNum: 1
            },
            list: [],
            total: 0
        };
    },
    mounted() {
        this.search();
    },
    methods: {
        onPageChange(pageNum) {
            this.form.pageNum = pageNum;
            this.search();
        },
        search() {
            getSubEpisodeDetail({
                pageSize: 10,
                pageNum: 1
            }).then(res => {
                if (res.status === 0) {
                    this.list = res.data;
                    this.total = res.allNum;
                }
            });
        }
    }
}
</script>