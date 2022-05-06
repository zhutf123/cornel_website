<template>
    <panel>
        <template slot="header">
            {{title}}
            <el-button size="mini" type="primary" @click="add">添加</el-button>
        </template>
        <el-table
            :data="list"
            row-key="id"
        >
            <el-table-column
                label="序号"
                prop="id"
            ></el-table-column>
            <el-table-column
                label="剧集名"
                prop="teleplayName"
            ></el-table-column>
            <el-table-column
                label="权重"
                prop="weight"
            ></el-table-column>
            <el-table-column
                label="操作"
                fixed="right"
            >
                <template slot-scope="scope">
                    <el-button
                        type="text"
                        @click="edit(scope.$index, scope.row)"
                    >编辑</el-button>
                    <el-button
                        class="color-danger"
                        type="text"
                        @click="del(scope.$index, scope.row)"
                    >删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- <el-pagination
            background
            layout="prev, pager, next"
            :total="total"
            @current-change="search"
        >
        </el-pagination> -->

        <el-dialog :title="(editingData && editingData.name ? '编辑' : '添加') + '榜单剧集'"
            :visible.sync="showDialog"
            :show-close="false"
            :close-on-click-modal="false"
            append-to-body
        >
            <el-form ref="editingForm" :model="editingData"
                v-if="editingData"
                label-width="100px"
            >
                <el-form-item label="剧集名称" prop="name">
                    <suggest
                        type="episode"
                        valueKey="title"
                        :displayValue="editingData.teleplayName"
                        :multiple="false"
                        :onSelect="onSelectEpisode"
                    />
                </el-form-item>
                <el-form-item label="首页权重" prop="weight">
                    <el-input-number :max="10" v-model="editingData.weight" />
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editingData = null">取消</el-button>
                <el-button type="primary" @click="onConfirmEdit">提交</el-button>
            </span>
        </el-dialog>
    </panel>
</template>

<script>
import { getRankDetail, updateRankVideoInfo } from '../../apis';
import { methodsMixins } from '../../utils/mixins';
import Panel from '../../components/Panel.vue';
import Suggest from '../../components/Suggest.vue';

export default {
    name: 'rankDetail',
    components: {
        Panel,
        Suggest
    },
    mixins: [methodsMixins],
    data() {
        const {id, title} = this.$route.query;
        return {
            form: id,
            title,
            list: [],
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
            // this.form.pageNum = pageNum;
            getRankDetail(this.form).then(res => {
                if (res.data) {
                    this.list = res.data;
                }
            });
        },
        add() {
            this.editingData = {
                teleplayName: '',
                rankInfoId: this.$route.query.id,
                teleplayId: '',
                status: 1,
                weight: 0
            }
        },
        edit(index, data) {
            this.editingData = {
                ...data
            };
        },
        del(index, data) {
            updateRankVideoInfo({
                ...data,
                status: 2
            }).then(res => {
                if (res.status === 0) {
                    this.$message.success(res.msg);
                    this.search(this.form.pageNum);
                    this.editingData = null;
                } else {
                    this.$message.error(res.msg);
                }
            });
        },
        onConfirmEdit() {
            this.$refs.editingForm.validate().then(() => {
                updateRankVideoInfo(this.editingData).then(res => {
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
        onSelectEpisode(item) {
            this.editingData.teleplayId = item.teleplayId;
            this.editingData.teleplayName = item.name;
        }
    }
}
</script>