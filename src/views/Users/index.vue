<template>
    <div class="m-publish">
        <el-form ref="form" :model="form" inline>
            <el-form-item label="昵称">
                <el-input v-model="form.name" />
            </el-form-item>
            <el-form-item label="会员">
                <el-select v-model="form.vip">
                    <el-option label="是" :value="1" />
                    <el-option label="否" :value="2" />
                </el-select>
            </el-form-item>
            <el-button type="primary" @click="search">查询</el-button>
        </el-form>
        <el-table
            :data="list"
        >
            <el-table-column
                label="昵称"
                prop="nickName"
            ></el-table-column>
            <el-table-column
                label="性别"
                prop="gender"
                :formatter="genderFormatter"
            ></el-table-column>
            <el-table-column
                label="手机号"
                prop="mobile"
            >
            </el-table-column>
            <el-table-column
                label="会员"
                prop="vip"
                :formatter="vipFormatter"
            ></el-table-column>
            <el-table-column
                label="追剧数"
                prop=""
            ></el-table-column>
            <el-table-column
                label="入驻时间"
                prop="createTime"
            ></el-table-column>
            <el-table-column
                label="累计消费"
                prop="allPayMoney"
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
            @current-change="search"
        >
        </el-pagination>

        <router-view />
    </div>
</template>

<script>
import { getUserList } from '../../apis';
import { methodsMixins } from '../../utils/mixins';

export default {
    name: 'users',
    mixins: [methodsMixins],
    data() {
        return {
            form: {
                name: '',
                vip: '',
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
        search(pageNum = 1) {
            getUserList({
                ...this.form,
                pageNum
            }).then(res => {
                if (res.data) {
                    this.list = res.data;
                    this.total = res.allNum;
                }
            });
        },
        goToDetail(index, data) {
            const {id, nickName} = data;
            this.$router.push({
                path: '/users/detail',
                query: {
                    id,
                    nickName
                }
            });
        }
    }
}
</script>