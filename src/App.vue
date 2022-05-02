<template>
  <div class="main">
    <div class="sidebar">
      <div class="logo">
        <img src="./assets/logo.png" />
      </div>
      <div class="name">蜂巢小剧场</div>
      <el-button type="primary">发布<i class="el-icon-caret-bottom el-icon-right"></i></el-button>

      <el-collapse class="entry" v-model="collapse">
        <el-collapse-item name="manage">
          <template slot="title">
            <i class="header-icon el-icon-document"></i>管理
          </template>
          <div
            :class="['entry-item', {
              active: item.path === currentPath.path
            }]"
            v-for="item in manageEntries" :key="item.path"
            @click="goto(item)"
          >{{item.name}}</div>
        </el-collapse-item>

        <el-collapse-item name="data">
          <template slot="title">
            <i class="header-icon el-icon-s-data"></i>数据
          </template>
          <div
            :class="['entry-item', {
              active: item.path === currentPath.path
            }]"
            v-for="item in dataAnalyEntries" :key="item.path"
            @click="goto(item)"
          >{{item.name}}</div>
        </el-collapse-item>
      </el-collapse>
    </div>
    <div class="content">
      <div class="title">{{currentPath.name}}</div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import './reset.scss';
import './app.scss';
export default {
  name: 'app',
  data() {
    return {
      collapse: ['manage'],
      entries: [{
        path: '/episodes',
        type: 'manage',
        name: '剧情管理'
      }, {
        path: '/comments',
        type: 'manage',
        name: '评论管理'
      }, {
        path: '/channels',
        type: 'manage',
        name: '频道管理'
      }, {
        path: '/users',
        type: 'manage',
        name: '用户管理'
      }, {
        path: '/contentAnalyse',
        type: 'data',
        name: '内容分析'
      }],
      contentTitle: '',
      currentPath: ''
    };
  },
  computed: {
    manageEntries() {
      return this.entries.filter(item => item.type === 'manage');
    },
    dataAnalyEntries() {
      return this.entries.filter(item => item.type === 'data');
    }
  },
  mounted() {
    const {path} = this.$route;
    let currentPath;
    if (!path || path === '/') {
      currentPath = this.manageEntries[0];
    } else {
      currentPath = this.entries.find(item => item.path === path);
    }
    if (currentPath) {
      this.collapse = [currentPath.type];
      this.goto(currentPath);
    }
  },
  methods: {
    goto(item) {
      this.currentPath = item;
      this.$router.push(item.path);
    }
  }
}
</script>