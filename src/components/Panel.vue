<template>
    <div class="m-panel">
        <div class="panel-main">
            <el-page-header
                @back="goBack"
            >
                <template slot="content">
                    <slot name="header" />
                </template>
            </el-page-header>
            <slot />
        </div>
    </div>
</template>

<script>
import './panel.scss';
export default {
    name: 'panel',
    methods: {
        goBack() {
            this.$router.back();
        }
    }
}
</script>