<template>
    <panel>
        <template slot="header">
            {{nickName}} 消费记录
        </template>
        <el-table
            :data="list"
        >
            <el-table-column
                label="消费时间"
                prop="payTime"
            ></el-table-column>
            <el-table-column
                label="消费内容"
                prop="productName"
            ></el-table-column>
            <el-table-column
                label="消费金额"
                prop="money"
            >
            </el-table-column>
        </el-table>
    </panel>
</template>

<script>
import { getUserPayList } from '../../apis';
import { methodsMixins } from '../../utils/mixins';
import Panel from '../../components/Panel.vue';

export default {
    name: 'user-detail',
    components: {
        Panel
    },
    mixins: [methodsMixins],
    data() {
        const {nickName} = this.$route.query;
        return {
            nickName,
            list: [],
        };
    },
    mounted() {
        this.search();
    },
    methods: {
        search() {
            getUserPayList(this.$route.query.id).then(res => {
                if (res.data) {
                    this.list = res.data;
                }
            });
        }
    }
}
</script>