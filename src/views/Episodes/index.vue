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
                    <el-option label="已审核" value="1"/>
                </el-select>
            </el-form-item>
            <el-form-item label="频道类型">
                <el-select v-model="form.type">
                    <el-option label="已审核" value="1" />
                </el-select>
            </el-form-item>
            <el-form-item label="VIP剧集">
                <el-input v-model="form.id" />
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
            ></el-table-column>
            <el-table-column
                label="标题"
            ></el-table-column>
            <el-table-column
                label="描述"
            ></el-table-column>
            <el-table-column
                label="剧集"
            ></el-table-column>
            <el-table-column
                label="频道"
            ></el-table-column>
            <el-table-column
                label="VIP剧集"
            ></el-table-column>
            <el-table-column
                label="审核状态"
            ></el-table-column>
            <el-table-column
                label="发布时间"
            ></el-table-column>
            <el-table-column
                label="发布者"
            ></el-table-column>
            <el-table-column
                label="追剧数"
            ></el-table-column>
            <el-table-column
                label="播放量"
            ></el-table-column>
            <el-table-column
                label="点赞量"
            ></el-table-column>
            <el-table-column
                label="转发量"
            ></el-table-column>
            <el-table-column
                label="评论数"
            ></el-table-column>
            <el-table-column
                label="是否推荐到首页"
            ></el-table-column>
            <el-table-column
                label="置顶操作"
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
export default {
    name: 'episodes',
    data() {
        return {
            form: {
                id: ''
            },
            list: [],
            total: 0,
            editingData: null
        };
    },
    methods: {
        search() {

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