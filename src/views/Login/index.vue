<template>
    <div class="m-login">
        <el-form ref="form" :model="form">
            <el-form-item label="用户名">
                <el-input v-model="form.name" />
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="form.passwd" type="password" />
            </el-form-item>
        </el-form>
        <el-button type="primary" @click="login">登录</el-button>
    </div>
</template>

<script>
import './index.scss';
import {login} from '../../apis';

export default {
    name: "login",
    data() {
        return {
            form: {
                name: '',
                passwd: ''
            }
        };
    },
    methods: {
        login() {
            login(this.form).then(res => {
                if (res.status === 0) {
                    this.$router.push('/episodes');
                }
            });
        }
    }
}
</script>