<template>
    <div class="m-tags">
        <el-form ref="form" :form="form" inline>
            <el-form-item label="榜单名称">
                <el-input v-model="form.name" />
            </el-form-item>
            <el-form-item label="状态">
                <el-select v-model="form.status">
                    <el-option label="上架" :value="1" />
                    <el-option label="下架" :value="2" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="search(1)">查询</el-button>
                <el-button type="primary" @click="add">新建</el-button>
            </el-form-item>
        </el-form>
        
        <el-table
            :data="list"
            row-key="id"
        >
            <el-table-column
                label="排行榜名称"
                prop="name"
            ></el-table-column>
            <el-table-column
                label="关联剧集"
                prop="teleplayNames"
            >
            </el-table-column>
            <el-table-column
                label="状态"
                prop="statusDesc"
            ></el-table-column>
            <el-table-column
                label="权重"
                prop="weight"
            ></el-table-column>
            <el-table-column
                label="发布时间"
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
                            @click="goToDetail(scope.$index, scope.row)"
                        >详情</el-button>
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

        <el-dialog title="新建榜单"
            :visible.sync="showDialog"
            :show-close="false"
            :close-on-click-modal="false"
        >
            <el-form ref="editingForm" :model="editingData"
                v-if="editingData"
                label-width="100px"
            >
                <el-form-item label="榜单名称" prop="name">
                    <el-input v-model="editingData.name" />
                </el-form-item>
                <el-form-item label="首页权重" prop="weight">
                    <el-input-number :max="10" v-model="editingData.weight" />
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-select v-model="editingData.status">
                        <el-option label="上架" :value="1" />
                        <el-option label="下架" :value="2" />
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editingData = null">取消</el-button>
                <el-button type="primary" @click="onConfirmEdit">提交</el-button>
            </span>
        </el-dialog>

        <router-view />
    </div>
</template>

<script>
import { getRankList, updateRank } from '../../apis';
import { methodsMixins } from '../../utils/mixins';

export default {
    name: 'rank',
    mixins: [methodsMixins],
    data() {
        return {
            form: {
                name: '',
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
            getRankList(this.form).then(res => {
                if (res.data) {
                    this.list = res.data;
                    this.total = res.allNum;
                }
            });
        },
        add() {
            this.editingData = {
                name: '',
                weight: 0,
                status: 1
            };
        },
        onConfirmEdit() {
            this.$refs.editingForm.validate().then(() => {
                updateRank(this.editingData).then(res => {
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
        goToDetail(index, data) {
            this.$router.push({
                path: '/rank/detail',
                query: {
                    title: data.name,
                    id: data.id
                }
            });
        }
    }
}
</script>