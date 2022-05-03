<template>
    <div class="m-publish">
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
    </div>
</template>

<script>

export default {
    name: 'publish',
    data() {
        return {
            form: {
                name: '',
                type: '',
                weight: 0,
                status: '',
                pageSize: 10,
                pageNum: 1
            }
        };
    },
    methods: {
        
    }
}
</script>