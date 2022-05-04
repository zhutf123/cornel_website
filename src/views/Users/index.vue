<template>
    <div class="m-publish">
        <el-form ref="form" :model="form" :inline="true">
            <el-form-item label="昵称">
                <el-input v-model="form.name" />
            </el-form-item>
            <el-form-item label="会员">
                <el-select v-model="form.vip">
                    <el-option label="是" :value="1" />
                    <el-option label="否" :value="0" />
                </el-select>
            </el-form-item>
            <el-button type="primary" @click="startSearch">查询</el-button>
        </el-form>
        <el-table
            :data="list"
        >
            <el-table-column
                label="昵称"
                prop="name"
            ></el-table-column>
            <el-table-column
                label="性别"
                prop="statusDesc"
            ></el-table-column>
            <el-table-column
                label="手机号"
            >
            </el-table-column>
            <el-table-column
                label="会员"
                prop="recommend"
            ></el-table-column>
            <el-table-column
                label="追剧数"
                prop="operatorName"
            ></el-table-column>
            <el-table-column
                label="入驻时间"
                prop="operateTime"
            ></el-table-column>
            <el-table-column
                label="累计消费"
                prop="operateTime"
            ></el-table-column>
            <el-table-column
                label="操作"
                fixed="right"
            >
                <template slot-scope="scope">
                    <el-button
                        type="text"
                        @click="goToDetail(scope.$index, scope.row)"
                    >详情</el-button>
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
</template>

<script>
import { getUserList } from '../../apis';

export default {
    name: 'users',
    data() {
        return {
            form: {
                name: '',
                vip: '',
                pageSize: 10,
                pageNum: 1
            },
            total: 0
        };
    },
    mounted() {
        this.startSearch();
    },
    methods: {
        startSearch() {
            this.form.pageNum = 1;
            this.search();
        },
        search() {
            getUserList(this.form).then(res => {
                if (res.data) {
                    this.list = res.data;
                    this.total = res.allNum;
                }
            });
        },
        onPageChange(pageNum) {
            this.form.pageNum = pageNum;
            this.search();
        },
        goToDetail(index, data) {

        }
    }
}
</script>