<template>
    <div class="m-episodes">
        <el-form ref="form" :model="form" :inline="true">
            <el-form-item label="频道名称">
                <el-row>
                    <el-col :span="15">
                        <el-input v-model="form.name" />
                    </el-col>
                    <el-col :span="8" :offset="1">
                        <el-button type="primary" size="small"
                            @click="add"
                        >添加频道</el-button>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item label="标签类型">
                <el-select v-model="form.type">
                    <el-option
                        v-for="item in CHANNEL_TAGS"
                        :key="item.value"
                        :label="item.name"
                        :value="item.value"
                    />
                </el-select>
            </el-form-item>
            <el-form-item label="标签状态">
                <el-select v-model="form.status">
                    <el-option label="在线" :value="1" />
                    <el-option label="下线" :value="2" />
                </el-select>
            </el-form-item>
            <el-button type="primary" @click="search">查询</el-button>
            <el-button @click="exportData">导出excel</el-button>
        </el-form>
        <el-table
            :data="list"
        >
            <el-table-column
                label="标题名称"
                prop="name"
            ></el-table-column>
            <el-table-column
                label="标签类型"
                prop="type"
                :formatter="channelTagFormatter"
            ></el-table-column>
            <el-table-column
                label="标签状态"
                prop="statusDesc"
            ></el-table-column>
            <el-table-column
                label="关联剧集"
            >
            </el-table-column>
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
                    <el-button
                        type="text"
                        @click="edit(scope.$index, scope.row)"
                    >修改</el-button>
                    <el-button
                        type="text"
                        @click="del(scope.$index, scope.row)"
                    >删除</el-button>
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

        <el-dialog :title="dialogTitle"
            v-if="editingData"
        >
            <el-form ref="editingForm" :model="editingData">
                <el-form-item label="频道名称">
                    <el-input v-model="editingData.name" />
                </el-form-item>
                <el-form-item label="标签类型">
                    <el-select v-model="editingData.type">
                        <el-option
                            v-for="tag in CHANNEL_TAGS"
                            :key="tag.value"
                            :label="tag.name" :value="tag.value"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="标签权重">
                    <el-input type="number" v-model="editingData.weight" />
                </el-form-item>
                <el-form-item label="标签状态">
                    <el-select v-model="editingData.status">
                        <el-option label="在线" :value="1" />
                        <el-option label="下线" :value="0" />
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
import {getChannelList, delChannel, updateChannel} from '../../apis';
import {CHANNEL_TAGS} from '../../utils/constant';
import {channelTagFormatter} from '../../utils/formatter';

export default {
    name: 'channels',
    data() {
        return {
            dialogTitle: '',
            CHANNEL_TAGS,
            form: {
                name: '',
                type: '',
                weight: 0,
                status: '',
                pageSize: 10,
                pageNum: 1
            },
            list: [],
            total: 0,
            editingData: null
        };
    },
    methods: {
        channelTagFormatter,
        onPageChange(pageNum) {
            this.form.pageNum = pageNum;
            this.search();
        },
        search() {
            getChannelList({
                pageSize: 10,
                pageNum: 1
            }).then(res => {
                if (res.status === 0) {
                    this.list = res.data;
                    this.total = res.allNum;
                }
            })
        },
        exportData() {

        },
        add() {
            this.dialogTitle = '添加频道';
            this.editingData = {
                name: '',
                type: 0,
                weight: 0,
                status: 1,
            };
        },
        edit(index, data) {
            const {type, status, weight, name} = data;
            this.dialogTitle = '编辑频道';
            this.editingData = {
                type,
                status,
                weight,
                name
            };
        },
        onConfirmEdit() {
            updateChannel(this.editingData).then(res => {
                if (res.status === 0) {
                    this.$message.success(res.msg);
                    this.editingData = null;
                } else if (res.msg) {
                    this.$message.error(res.msg);
                }
            });
        },
        del(index, data) {
            delChannel(data.id).then(res => {
                if (res.status === 0) {
                    this.$message.success(res.msg || '删除成功');
                    this.search();
                }
            });
        }
    }
}
</script>